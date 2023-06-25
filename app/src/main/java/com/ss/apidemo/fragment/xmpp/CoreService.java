package com.ss.apidemo.fragment.xmpp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;


import com.ss.apidemo.BuildConfig;
import com.ss.apidemo.socket.EMConnectionManager;
import com.ss.apidemo.utils.LogUtils;

import androidx.annotation.Nullable;

public class CoreService extends Service {
    static final boolean DEBUG = true;
    static final String TAG = "XmppCoreService";

    private static final Intent SERVICE_INTENT = new Intent();


    static {
        SERVICE_INTENT.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, CoreService.class.getName()));
    }


    private EMConnectionManager mConnection;


    public static Intent getIntent() {
        return SERVICE_INTENT;
    }

    // 要用ContextCompat.startForegroundService启动，否则安卓8.0以上可能崩溃，而且是不一定复现的那种，
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, CoreService.class);
        return intent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (CoreService.DEBUG) {
            LogUtils.e(CoreService.TAG, "CoreService OnCreate :" + android.os.Process.myPid());
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (CoreService.DEBUG) {
            LogUtils.e(CoreService.TAG, "CoreService onStartCommand");
        }

        init();

        return START_STICKY;
    }

    private void init() {
        if (mConnection == null) {
            initConnection();
        }
    }

    public void initConnection() {
        mConnection = new EMConnectionManager(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (CoreService.DEBUG) {
            LogUtils.e(CoreService.TAG, "CoreService onDestroy");
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
