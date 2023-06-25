package com.ss.apidemo.socketmanager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendMsgThread extends Thread {
    private Socket socket = null;
    private Handler handler;
    public DataOutputStream out = null;
    private byte[] message;    //用于暂存用户需要发向服务器的消息
    public int flag = 0;

    //构造方法(Socket, Handler)
    public SendMsgThread(Socket s, Handler handler) {
        this.socket = s;
        this.handler = handler;
    }

    //用于接受用户需要发向服务器的消息
    public void sendMessage(byte[] message) {
        this.message = message;
        flag = 1;
    }

    @Override
    public void run() {
        while (true) {
            if (flag == 1) {
                sendMsg(this.message);
                flag = 0;
            }
        }
    }
    //第一次发指令
    protected void firstSendMsg(byte[] message) {
        try {
            if (socket.isConnected()) {
                out = new DataOutputStream(socket.getOutputStream());
                // byte[]  by={0x08, 0x07 ,0x4C ,0x47 ,0x50 ,0x3D, 0x31 ,0x30 ,0x30 ,0x0D, 0x0A};
                out.write(message);
//                Log.d("xuan", "firstSendMsg: "+message);
                out.flush();
                Message msg = handler.obtainMessage();
                msg.what = 4; //发送成功
                msg.obj = "已发送";
                handler.sendMessage(msg);
            } else {
                Message msg = handler.obtainMessage();
                msg.what = 5; //发送消息断开标志
                msg.obj = "发送连接断开";
                handler.sendMessage(msg);
            }
            flag = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送消息的方法
    public void sendMsg(byte[] message) {
            ReceiveMsgThread.receiveByte=null;
        try {
            //(ReceiveMsgThread.receiveByte[2] == 117 && ReceiveMsgThread.receiveByte[3] == 107)
            //  while (ReceiveMsgThread.receiveByte==null) { }
            if (socket.isConnected()) {

                out = new DataOutputStream(socket.getOutputStream());
                // byte[]  by={0x08, 0x07 ,0x4C ,0x47 ,0x50 ,0x3D, 0x31 ,0x30 ,0x30 ,0x0D, 0x0A};
                out.write(message);
                out.flush();
                Message msg = handler.obtainMessage();
                msg.what = 4; //发送成功
                msg.obj = "已发送";
                handler.sendMessage(msg);
            } else {
                Message msg = handler.obtainMessage();
                msg.what = 5; //发送消息断开标志
                msg.obj = "发送连接断开";
                handler.sendMessage(msg);
            }
            flag = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

