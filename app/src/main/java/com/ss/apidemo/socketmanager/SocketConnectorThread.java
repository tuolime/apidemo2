package com.ss.apidemo.socketmanager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ss.apidemo.utils.LogUtils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;


public class SocketConnectorThread extends Thread {
    private Manager manager = Manager.getManager();
    private int PORT;
    private String IP;
    Handler handler;
    private SendMsgThread sendMsgThread=null;
    private ReceiveMsgThread receiveMsgThread=null;
    private Socket socket = new Socket();
    private int current_count=0;
//    private Socket socket;



    //构造方法(ip, port)
    public SocketConnectorThread(String ip, int port, Handler handler) {
        this.IP = ip;
        this.PORT = port;
        this.handler = handler;
    }

    @Override
    public void run() {
//        initSocket();
        try {
            LogUtils.d("xuan", "开始连接 ");
            socket.connect(new InetSocketAddress(IP, PORT));
            if (socket.isConnected()) {
                manager.setSocket(socket);  //当Socket连接成功后将对象传给manager的引用
                Message msg = handler.obtainMessage();
                msg.what = 1; //发送成功
                msg.obj = "连接成功";
                handler.sendMessage(msg);
                message();
            } else {
                LogUtils.d("xuan", "连接失败 ");

                Message msg = handler.obtainMessage();
                msg.what = 0; //发送成功
                msg.obj = "连接失败";
                handler.sendMessage(msg);
            }
        } catch (IOException e) {
            LogUtils.d("xuan", "连接失败 try");

            Message msg = handler.obtainMessage();
            msg.what = 0; //发送成功
            msg.obj = "连接失败";
            handler.sendMessage(msg);
        }
    }
    /*
      * 断开连接
     */
    private void disSocket() {
        if (socket != null) {
            try {
                sendMsgThread.out.close();
                receiveMsgThread.in.close();
                socket.close();
                socket = null;
            } catch (Exception e) {
                Message msg = handler.obtainMessage();
                msg.what = 0; //发送成功
                msg.obj = "断开连接时发生错误";
                handler.sendMessage(msg);
            }
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
//                    socket = new Socket(IP, PORT);
                    socket.connect(new InetSocketAddress(IP, PORT));
                    break;
                } catch (IOException e) {
                    // 连接失败，休息后重新连接，
                    // 计算休息时间，确保尝试连接一次的时间不小于durationTime,
                    // 避免无意义的重连，因为当服务器没有启动时socket连接会立即抛出异常，
                    LogUtils.d("xuan", "连接失败，剩余连接次数 " + tryTimes, e);
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
                throw new IOException("连接失败, host=" + IP + ", port=" + PORT);
            }
            if (socket.isConnected()) {
                manager.setSocket(socket);  //当Socket连接成功后将对象传给manager的引用
                Message msg = handler.obtainMessage();
                msg.what = 1; //发送成功
                msg.obj = "连接成功";
                handler.sendMessage(msg);
                message();
                LogUtils.e("xuan", "已连接服务器: " + IP);
            } else {
                Message msg = handler.obtainMessage();
                msg.what = 0; //发送成功
                msg.obj = "连接失败";
                handler.sendMessage(msg);
            }

        } catch (IOException e) {
            LogUtils.e("xuan", "initSocket  : 连接服务器失败", e);
            Message msg = handler.obtainMessage();
            msg.what = 0; //发送成功
            msg.obj = "连接失败";
            handler.sendMessage(msg);
        }
    }

    public void message() {
            sendMsgThread = new SendMsgThread(manager.getSocket(), handler);
            receiveMsgThread = new ReceiveMsgThread(manager.getSocket(), handler);
            receiveMsgThread.start();
            sendMsgThread.start();
            manager.setSendMsgThread(sendMsgThread);
            manager.setReceiveMsgThread(receiveMsgThread);
            Log.d("xuan", "message: " + "打开消息通道");
//        String str = "{\"useNum\":0,\"terminalCode\":\"d592235ec3060edb\",\"type\":0,\"totalUseNum\":0,\"reportUseNum\":0}";
//        sendMsgThread.firstSendMsg(str.getBytes(StandardCharsets.UTF_8));
    }
    public void sendMessage(final String message){
            ThreadManager.getPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (sendMsgThread != null){
                            Log.d("xuan", "firstSendMsg: "+message);
                            sendMsgThread.firstSendMsg(message.getBytes(StandardCharsets.UTF_8));
                        }else {
                            current_count ++;
                            if (current_count>5){
                                current_count = 0;
                                Message msg = handler.obtainMessage();
                                msg.what = 0; //发送成功
                                msg.obj = "连接失败";
                                handler.sendMessage(msg);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
    }
}

