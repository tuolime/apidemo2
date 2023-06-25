package com.ss.apidemo.utils;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.blankj.utilcode.util.ThreadUtils;
import com.ss.apidemo.bean.QueueMessage;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueUtil {
    private static QueueUtil instance = null;
    private static Queue<QueueMessage> loopDatas;

    public static final Queue<QueueMessage> getInstance() {
        if (instance == null) {
            synchronized (QueueUtil.class) {
                if (instance == null) {
                    instance = new QueueUtil();
                    loopDatas = new ConcurrentLinkedQueue<>();//队列
                }
            }
        }
        return loopDatas;
    }


}
