package com.ss.apidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ss.api.SsApi;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.Rs485AndRs232Activity;
import com.ss.apidemo.TestDialogActivity;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.DeviceInfoUtil;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.NetworkUtil;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.widget.LongPressView;

/*
 * 欢迎页
 * */
public class SplashActivity extends BaseActivity {
//public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private LongPressView lp;
    private Button bt_continue;
    private TextView tv_deviceId;
    private RelativeLayout rl_splash;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        显示导航栏：重启不会保存设置
//        adb shell am broadcast -a "action.ACTION_API_SHOW_NAVIGATION"
//        隐藏导航栏：重启不会保存设置
//        adb shell am broadcast -a "action.ACTION_API_HIDE_NAVIGATION"
//
//        显示状态栏：重启不会保存设置
//        adb shell am broadcast -a "action.ACTION_API_SHOW_STATUS_BAR"
//        隐藏状态栏：重启不会保存设置
//        adb shell am broadcast -a "action.ACTION_API_HIDE_STATUS_BAR"
//        SsApi.getInstance().execCmd("am broadcast -a \"action.ACTION_API_HIDE_NAVIGATION\"");
//        SsApi.getInstance().execCmd("am broadcast -a \"action.ACTION_API_HIDE_STATUS_BAR\"");
        initView();
    }

    private void initView() {
        tv_deviceId = findViewById(R.id.tv_deviceId);
        rl_splash = findViewById(R.id.rl_splash);
        lp = findViewById(R.id.lp);
        bt_continue = findViewById(R.id.bt_continue);
        bt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                boolean iswifi = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
                if (iswifi){
                    boolean wifi = NetworkUtil.isWifi();
                    if (!wifi){
                        //打开wifi
                        Intent i = new Intent();
                        i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(i);
                    }else {
                        if (AppConfig.lockStatus == 0){//未锁定
                            isAutoShed();
                        } else if(AppConfig.lockStatus == 1) {//锁定
                            ShowDialog();
                        } else if(AppConfig.lockStatus == 2) {//默认（没有获取到实际状态信息）
                            MyApplication.instance().destroyTask();
                            MyApplication.instance().connetSocket();
                        }

                    }
                }else {//工程师没有设置wifi
                    isAutoShed();
                }


            }
        });
        //动画效果参数直接定义
        animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(3000);

        //动画效果从XMl文件中定义
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);

        Button bt_232 = findViewById(R.id.bt_232);
        bt_232.setVisibility(View.GONE);
        bt_232.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, Rs485AndRs232Activity.class));
            }
        });
        Button bt_serial = findViewById(R.id.bt_serial);
        bt_serial.setVisibility(View.GONE);
        bt_serial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(SplashActivity.this, SerialPortActivity.class));
                startActivity(new Intent(SplashActivity.this, TestDialogActivity.class));
            }
        });
    }

    public void startSocket(){
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        boolean iswifi = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
        if (iswifi){
            boolean wifi = NetworkUtil.isWifi();
            if (!wifi){
            }else {
//                if (AppConfig.lockStatus == 0){//未锁定
                    AppConfig.lockStatus = 2;// 2 默认（没有获取到实际状态信息）
                    MyApplication.instance().destroyTask();
                    MyApplication.instance().connetSocket();
//                }
//                else {
//                    ShowDialog();
//                }

            }
        }
    }

    public void isAutoShed(){
        boolean isAutoShed = SharedPrefsUtil.getBooleanValue(AppConfig.SHED, false);
        if (isAutoShed){
            boolean autoShed = getAutoShed();
            if (autoShed){
                startActivity(new Intent(SplashActivity.this, UserCreateActivity.class));
            }else {
                startActivity(new Intent(SplashActivity.this, AutoShedActivity.class));
            }
        }else {
            startActivity(new Intent(SplashActivity.this, UserCreateActivity.class));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        BackgroundChangeUtils.backgroundLoginChange(this,rl_splash);
        if (animation != null) {
            rl_splash.setAnimation(animation);
        }
        LogUtils.e("onResume");
        String mac = DeviceInfoUtil.getMac();
        tv_deviceId.setText(getResources().getString(R.string.id)+":"+mac);
        startSocket();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        XactivityCollector.finishAll();
        LogUtils.e("onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e("onPause");
    }

    public void ShowDialog(){
        HintDialog dialog = new HintDialog(SplashActivity.this);
        dialog.loadDialog(SplashActivity.this, new HintDialog.OnClickIsConfirm() {
            @Override
            public void OnClickIsConfirmListener() {//确定
            }

        }, getResources().getString(R.string.lock_tips));
    }
}