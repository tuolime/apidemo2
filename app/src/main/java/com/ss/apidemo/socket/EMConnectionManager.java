package com.ss.apidemo.socket;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.bean.Frame;
import com.ss.apidemo.fragment.xmpp.SocketPingManager;
import com.ss.apidemo.utils.DeviceInfoUtil;
import com.ss.apidemo.utils.LogUtils;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * XMPP连接类
 */

public class EMConnectionManager {

    Handler mMainHandler = new Handler(Looper.getMainLooper());// 用于线程中切换到主线程，进行ui操作
    private SocketThread mSocketThread;
    ByteArrayOutputStream bytesOut = null;
    String charSetType = "UTF-8";
    public Handler handler;
    public EMConnectionManager(Context context) {
        LogUtils.e("xuan", " new EMConnectionManager: " + " ,  socket ip: " + AppConfig.SOCKET_HOST);
        mSocketThread = new SocketThread(this, AppConfig.SOCKET_HOST, AppConfig.SOCKET_PORT);
        mSocketThread.start();
    }
    public void setMainHandler(Handler myhandler){
        this.handler = myhandler;
    }

    //重连
    public void reconnection() {
        if (mSocketThread == null) {
            LogUtils.e("xuan", "重连");
            mSocketThread = new SocketThread(this, AppConfig.SOCKET_HOST, AppConfig.SOCKET_PORT);
            mSocketThread.start();
        }
    }


    public int getCurrentState() {
        if (mSocketThread == null) {
            return AuthStateListener.AUTH_STATE_INIT;
        }
        return mSocketThread.mSocketConnectState;
    }

    public boolean isConnected() {
        if (getCurrentState() > AuthStateListener.AUTH_STATE_INIT) {
            return true;
        }
        return false;
    }

    public boolean isAuthenticated() {
        LogUtils.i("socket conn state", "isAuthenticated: " + getCurrentState());
        return getCurrentState() == AuthStateListener.AUTH_STATE_SUCCESS;
    }

    public void disconnect() {
        if (mSocketThread != null) {
            mSocketThread.disconnect();
            mSocketThread = null;
            LogUtils.e("xuan", "Socket disconnect success");
        } else {
            LogUtils.e("xuan", "SocketThread = null");
        }
    }

    /**
     * 主动断开socket长连接
     * <p>
     * 网络断开后，socket长连接已经失效，此时正常情况下decodeSocket方法内阻塞的read操作会抛出io异常
     * 但模拟器上调试发现，网络断开之后read操作处无任何变化，一直阻塞在那里，且此时判断socket对象的isClose
     * 当网络连接上以后，因为长连接状态还为已连接的状态，导致没有去重新创建一个socket长连接
     */


    public void socketConnectClose() {
        if (mSocketThread != null) {
            mSocketThread.close();
        }
    }

    public void sendMessage(String message) {
        mSocketThread.send(message);
    }

    public void sendPingMessage() {
        mSocketThread.ping();
    }


    /**
     * 连接监听
     *
     * @param connectionListener
     */


    /**
     * 核心
     */


    private class SocketThread extends Thread {
        private static final int MAX_SIZE = 2048; // max size 256
        private static final String TAG = "xuan";
        private String mIp;
        private int mPort;

        private Socket socket;
        //        private InputStream inputStream;
        private DataInputStream inputStream;
        private OutputStream outputStream;

        private boolean mLoginIng;
        private int mSocketConnectState = AuthStateListener.AUTH_STATE_INIT;
        // 输入缓冲区，
        private ByteBuffer dataBuffer = ByteBuffer.allocate(MAX_SIZE);

        private int pingFailedCount = 0;
        private EMConnectionManager mConnectionManager;
        // 用户退出登录等主动退出的情况，
        private boolean disconnected = false;

        public SocketThread(EMConnectionManager connectionManager, String ip, int port) {
            this.mIp = ip;
            this.mPort = port;
            this.mConnectionManager = connectionManager;
            // 准备连接
            notifyConnect(1, AuthStateListener.AUTH_STATE_ING);
        }

        private void notifyConnect(int which, int authState) {
            LogUtils.e("xuan", "which：" + which);
            mSocketConnectState = authState;
            if (authState == AuthStateListener.AUTH_STATE_ING) {

            } else if (authState == AuthStateListener.AUTH_STATE_SUCCESS) {

            }
        }

        private void notifyClose() {
            mLoginIng = false;
            mSocketConnectState = AuthStateListener.AUTH_STATE_CLOSE;

        }

        private void notifyError(String exception) {
            mLoginIng = false;
            mSocketConnectState = AuthStateListener.AUTH_STATE_ERROR;
        }

        @Override
        public void run() {
            initSocket();
            try {
                startRead();
            } catch (IOException e) {
                LogUtils.e(TAG, "decodeSocket: read抛异常", e);
                closeAll();
                notifyError(SocketException.SELECTION_KEY_INVALID);
            }
        }

        private void initSocket() {
            try {
                // socket重新连接次数，
                int tryTimes = 3;
                // 重连间隔时间，避免连不上服务器时无限触发外部的重连机制，
                long durationTime = TimeUnit.SECONDS.toMillis(1);
                long startTime = System.currentTimeMillis();
                while (tryTimes-- > 0) {
                    try {
                        // 构造同时连接，
                        socket = new Socket(mIp, mPort);
                        break;
                    } catch (IOException e) {
                        // 连接失败，休息后重新连接，
                        // 计算休息时间，确保尝试连接一次的时间不小于durationTime,
                        // 避免无意义的重连，因为当服务器没有启动时socket连接会立即抛出异常，
                        LogUtils.d(TAG, "连接失败，剩余连接次数 " + tryTimes, e);
                        long sleepTime = startTime + durationTime - System.currentTimeMillis();
                        if (sleepTime > 0) {
                            try {
                                sleep(sleepTime);
                            } catch (InterruptedException e1) {
                                throw new IOException("连接中断", e1);
                            }
                        }
                        startTime = System.currentTimeMillis();
                    }
                }
                if (socket == null) {
                    Message msg = handler.obtainMessage();
                    msg.what = 0; //发送成功
                    msg.obj = "连接失败";
                    handler.sendMessage(msg);
                    throw new IOException("连接失败, host=" + mIp + ", port=" + mPort);
                }
                // 输出不需要缓冲，因为有自己维护缓冲区，
//                inputStream = socket.getInputStream();
                inputStream = new DataInputStream(this.socket.getInputStream());
                // 输出需要缓冲，否则会一个一个字节发送，
                outputStream = new BufferedOutputStream(socket.getOutputStream());
                // 已连接
                notifyConnect(2, AuthStateListener.AUTH_STATE_ING);
                LogUtils.e(TAG, "已连接服务器: " + mIp);
                Message msg = handler.obtainMessage();
                msg.what = 1; //发送成功
                msg.obj = "连接成功";
                handler.sendMessage(msg);
            } catch (IOException e) {
                LogUtils.e(TAG, "initSocket  : 连接服务器失败", e);
                notifyError(SocketException.FINISH_CONNECT_EXCEPTION);
                Message msg = handler.obtainMessage();
                msg.what = 0; //发送成功
                msg.obj = "连接失败";
                handler.sendMessage(msg);
            }
        }

        private void startRead() throws IOException {
            while (isConnected()) {
                decodeSocket();
            }
        }

        public void disconnect() {
            disconnected = true;
            mSocketThread.mLoginIng = false;
            closeAll();
            mSocketThread.interrupt();
            mSocketThread = null;//关键
        }

        public void close() {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void closeAll() {
            if (inputStream != null) {
                finallyClose(inputStream);
            }
            if (outputStream != null) {
                finallyClose(outputStream);
            }
            if (socket != null) {
                finallyClose(socket);
            }
        }

        private void finallyClose(Closeable closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /********************************************************************************
         *   Todo 发送各种类型消息，与服务器进行交互
         ********************************************************************************/

        private boolean isConnected() {
            return socket != null && socket.isConnected() && !disconnected;
        }

        private boolean inSocket(String message) {
            boolean success = false;
            // 输出到通道
            try {
                if (isConnected() && outputStream != null) {
                    // 强行保留旧代码的ByteBuffer,
                    outputStream.write(message.getBytes(charSetType));
                    outputStream.flush();
                    dataBuffer.position(dataBuffer.limit());
                } else {
                    return false;
                }
                success = true;
            } catch (IOException e) {
                LogUtils.e(TAG, "initSocket  : 发送数据失败", e);
                notifyError(SocketException.SELECTION_KEY_INVALID);
                closeAll();
            }
            return success;
        }


        /**
         * //         * @param message 上传定位坐标
         */


        private void send(final String message) {
            ThreadManager.getPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        String requestData = setSplit(message, charSetType);
                        //                    String str = message.messageHead.getChatType() == 2 ? "群聊" : "单聊";
                        if (inSocket(requestData)) {
                            Message msg = handler.obtainMessage();
                            msg.what = 4; //发送成功
                            msg.obj = "已发送";
                            handler.sendMessage(msg);
                            LogUtils.e(TAG, "发送成功: " + requestData);
                        } else {
                            Message msg = handler.obtainMessage();
                            msg.what = 5; //发送消息断开标志
                            msg.obj = "发送连接断开";
                            handler.sendMessage(msg);
                            LogUtils.e(TAG, "发送失败: " + requestData);
                            notifyError(SocketException.SELECTION_KEY_INVALID);//发送失败
                            // 开始Ping 服务器  这里暂定  主页面延时调用
                            SocketPingManager.getInstance().registerPing(mConnectionManager);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        private void ping() {
            ThreadManager.getPool().execute(new Runnable() {
                @Override
                public void run() {
                    Frame frame = new Frame();
                    frame.setType(0);//心跳
                    String deviceId = DeviceInfoUtil.getMac();
                    frame.setTerminalCode(deviceId);
                    JSONObject jsonObject = (JSONObject) JSONObject.toJSON(frame);
                    String jsonString = jsonObject.toJSONString();
                    String reqData = null;
                    try {
                        reqData = setSplit(jsonString, charSetType);
                        LogUtils.e("ping", "数据，" + reqData.toString());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (reqData == null) {
                        return;
                    }
                    if (inSocket(reqData)) {
                        pingFailedCount = 0;
                        LogUtils.e("ping", "发送Ping消息给服务器 成功");
                    } else {
                        pingFailedCount++;
                        LogUtils.e("ping", "发送Ping消息给服务器 失败--->pingFailedCount==" + pingFailedCount);
                        if (pingFailedCount == 2) {
                            pingFailedCount = 0;
                            LogUtils.e("ping", "Ping失败两次，本地连接置为离线");
                            notifyError(SocketException.SOCKET_PING_FAILED);
                            disconnect();
                            reconnection();//重连
                            Message msg = handler.obtainMessage();
                            msg.what = 0; //发送成功
                            msg.obj = "连接失败";
                            handler.sendMessage(msg);
                        }
                    }
                }
            });
        }
        /*
         *******************************************************************************
         *  Todo 收到各种类型消息，解析分发
         ********************************************************************************/

        private void decodeSocket() throws IOException {
            while (true) {
                try {
                    if (isConnected()) {
                        byte[] bytes = new byte[inputStream.available()];
                        if (bytes.length != 0) {
                            inputStream.read(bytes);
                            String str = new String(bytes);
                            LogUtils.e("xuan", "收到原始数据长度: " + bytes.length);
                            LogUtils.e("xuan", "收到原始数据: " + str);
                            Message msg = handler.obtainMessage();
                            msg.what = 2; //接收消息标志
                            msg.obj = bytes;
                            handler.sendMessage(msg);
                        }
                    }
//                    else {
//                        Message msg = handler.obtainMessage();
//                        msg.what = 3; //接收消息断开标志
//                        msg.obj = "接收连接断开";
//                        handler.sendMessage(msg);
//                    }
                } catch (IOException e) {
                }
            }

        }

    }


    private String setLength(String str, String charSetType) throws UnsupportedEncodingException {
        // 完整报文：10位长度 + 报文内容
        return String.format("%010d", str.getBytes(charSetType).length) + str;
    }

    private String setSplit(String str, String charSetType) throws UnsupportedEncodingException {
        // 完整报文：设置服务端分割符 \r\nseparator
//        return str + "\r\nseparator";
        return str;
    }
}
