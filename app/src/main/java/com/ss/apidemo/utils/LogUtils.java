package com.ss.apidemo.utils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装一些日志打印，统一方式打印一些麻烦的对象，
 * 方便崩溃上报后排查问题，
 */
public final class LogUtils {
    private static final String TAG = "LogUtils";
    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void log(Object obj) {
        log(TAG, obj);
    }

    public static void log(String tag, Object obj) {
        if (obj instanceof Intent) {
            log(tag, (Intent) obj);
        } else if (obj instanceof Bundle) {
            log(tag, (Bundle) obj);
        } else if (obj instanceof CharSequence) {
            log(tag, obj.toString());
        } else {
            log(tag, JSON.toJSONString(obj));
        }
    }

    private static void log(String tag, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("intent = ");
        if (intent == null) {
            sb.append("null").append('\n');
        } else {
            sb.append(intent.toString()).append('\n');
            sb.append("action = ").append(intent.getAction()).append('\n');
            sb.append("data = ").append(intent.getDataString()).append('\n');
            sb.append("extra = ");
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                sb.append("null").append('\n');
            } else {
                Map<String, Object> map = new HashMap<>();
                for (String key : bundle.keySet()) {
                    Object value = bundle.get(key);
                    map.put(key, value);
                }
                sb.append(map.toString());
            }
        }
        realLog(tag, sb.toString());
    }

    private static void log(String tag, Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        sb.append("bundle = ");
        Map<String, Object> map = new HashMap<>();
        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            if (value != null) {
                map.put(key, value);
            }
        }
        sb.append(map.toString());
        realLog(tag, sb.toString());
    }

    private static void log(String tag, String str) {
        realLog(tag, str);
    }

    private static void realLog(String tag, String str) {
        // TAG长度不能超过23，否则崩溃，
        if (tag.length() > 23) {
            tag = tag.substring(0, 23);
        }
        if (Log.isLoggable(tag, Log.WARN)) {
            Log.w(tag, str);
        }
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    public static void w(String msg) {
        if (isDebug)
            Log.w(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            Log.w(tag, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg,Throwable throwable) {
        if (isDebug)
            Log.i(tag, msg,throwable);
    }

    public static void d(String tag, String msg,Throwable throwable) {
        if (isDebug)
            Log.d(tag, msg,throwable);
    }

    public static void e(String tag, String msg,Throwable throwable) {
        if (isDebug)
            Log.e(tag, msg,throwable);
    }

    public static void v(String tag, String msg,Throwable throwable) {
        if (isDebug)
            Log.v(tag, msg,throwable);
    }

    public static void w(String tag, String msg,Throwable throwable) {
        if (isDebug)
            Log.w(tag, msg,throwable);
    }
}
