package com.ss.apidemo.socketmanager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiveMsgThread extends Thread {
    public DataInputStream in = null;
    private Socket socket;
    public String str;
    public Handler handler;
    private MessageChangedListener messageChangedListener;
    public static byte[] receiveByte;
    public static byte[] receiveByteSM=new byte[0];

    //构造方法(Socket)
    public ReceiveMsgThread(Socket s, Handler handler) {
        this.socket = s;
        this.handler = handler;
    }

    public static interface MessageChangedListener { //监听接口 未使用
        public void onMessageChanged(byte[] bytes);
    }

    public void setLoadingListener(MessageChangedListener listener) {
        messageChangedListener = listener;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (this.socket.isConnected()) {
                    byte[] bytes = new byte[in.available()];
                    this.receiveByteSM=bytes;
                    if (bytes.length != 0) {
                        in.read(bytes);
                        Message msg = handler.obtainMessage();
                        msg.what = 2; //接收消息标志
                        msg.obj = bytes;
                        handler.sendMessage(msg);
                        Log.d("xuan", "收到原始数据: "+bytes.length);
                    }
                } else {
                    Message msg = handler.obtainMessage();
                    msg.what = 3; //接收消息断开标志
                    msg.obj = "接收连接断开";
                    handler.sendMessage(msg);
                }
            } catch (IOException e) {
            }

        }
    }

}

