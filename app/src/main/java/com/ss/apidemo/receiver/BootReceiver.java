package com.ss.apidemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.ss.apidemo.MyApplication;
import com.ss.apidemo.ui.SplashActivity;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) return;
        //开机完成后 启动应用
        if (TextUtils.equals(intent.getAction(), "android.intent.action.BOOT_COMPLETED")) {
//            Intent newIntent = new Intent(context, SplashActivity.class);
//            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            MyApplication.instance().startActivity(newIntent);
        }
    }
}
