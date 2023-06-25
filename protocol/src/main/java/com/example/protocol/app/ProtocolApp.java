package com.example.protocol.app;

import android.content.Context;


/**
 * 全局context
 */
public class ProtocolApp {

    public static Context mContext;

    public static void setMContext(Context context) {
        mContext = context;
    }
}