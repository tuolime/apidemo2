package com.ss.apidemo.socketmanager;

import java.net.Socket;

public class Manager {

    private static Socket socket = null;

    private static SendMsgThread sendMsgThread = null;

    private  static SocketConnectorThread socketConnectorThread=null;

    private  static ReceiveMsgThread receiveMsgThread=null;

    public static Manager getManager() {
        return new Manager();
    }

    public Socket getSocket() {
        if (socket == null) {
            return null;
        } else {
            return socket;
        }

    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    public SendMsgThread getSendMsgThread(){
        if (sendMsgThread == null) {
            return null;
        } else {
            return sendMsgThread;
        }
    }

    public void setSendMsgThread(SendMsgThread sendMsgThread) {
        this.sendMsgThread = sendMsgThread;
    }


    public SocketConnectorThread getSocketConnectorThread(){
        if (socketConnectorThread == null) {
            return null;
        } else {
            return socketConnectorThread;
        }
    }

    public void setSocketConnectorThread(SocketConnectorThread socketConnectorThread) {
        this.sendMsgThread = sendMsgThread;
    }

    public ReceiveMsgThread getReceiveMsgThread(){
        if (receiveMsgThread == null) {
            return null;
        } else {
            return receiveMsgThread;
        }
    }

    public void setReceiveMsgThread(ReceiveMsgThread socketConnectorThread) {
        this.sendMsgThread = sendMsgThread;
    }
}
