package com.ss.apidemo.fragment.xmpp;


import com.ss.apidemo.AppConfig;
import com.ss.apidemo.socket.AuthStateListener;
import com.ss.apidemo.socket.EMConnectionManager;
import com.ss.apidemo.utils.LogUtils;

public class SocketPingManager {

    private static final long MESSAGE_DELAY = 5 * 1000; // 延迟*之后ping一次服务器
    private static SocketPingManager instance;
    private EMConnectionManager mEMConnectionManager;
    private PingThread mPingThread;

    private SocketPingManager() {

    }

    public static synchronized SocketPingManager getInstance() {
        if (instance == null) {
            instance = new SocketPingManager();
        }
        return instance;
    }

    public void registerPing(EMConnectionManager connectionManager) {
        mEMConnectionManager = connectionManager;
        if (mPingThread == null) {
            mPingThread = new PingThread();
            mPingThread.start();
        } else {
            if (!mPingThread.isAlive()) {
                // 不能直接调用run, 会阻塞当前线程导致无法读取socket数据，
                mPingThread = new PingThread();
                mPingThread.start();
            }
        }
    }

    class PingThread extends Thread {

        public PingThread() {
        }

        @Override
        public void run() {
            super.run();
            LogUtils.e("ping", "Start ping..."+ AppConfig.SOCKET_HOST+":"+AppConfig.SOCKET_PORT);
            while (mEMConnectionManager.getCurrentState() == AuthStateListener.AUTH_STATE_ERROR) {
                mEMConnectionManager.sendPingMessage();
                try {
                    Thread.sleep(MESSAGE_DELAY);
                } catch (InterruptedException e) {
                    LogUtils.e("ping", "InterruptedException ping......");
                    e.printStackTrace();
                }
            }
            LogUtils.e("ping", "Stop ping......");
        }
    }
}
