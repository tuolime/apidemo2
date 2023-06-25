package com.ss.apidemo.ui;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetDeviceConfig;
import com.example.protocol.frame.data.SetWarningConfig;
import com.example.protocol.utils.ParserUtil;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ToastUtil;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.HexUtil;

/*
 * 工程师设置界面
 * */
public class EngineerSettingActivity extends BaseActivity {
    private boolean isCount = true; //false 计数   true 不计数
    private Button bt_count;
    private RadioGroup rg_right;
    private RadioButton rb_right1, rb_right2, rb_right3, rb_right4, rb_right5, rb_right6, rb_right7;
    private AudioManager mAudioManager;
    int mediaVolume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer_setting);
        initView();
        initAudio();
    }

    private void initAudio() {
        //获取系统的Audio管理者
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //当前音量
        mediaVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int power = SharedPrefsUtil.getIntValue(AppConfig.POWER_TYPE, 1);
                int handgear = SharedPrefsUtil.getIntValue(AppConfig.HANDGEAR, 0);
                ProtocalHandler protocalHandler = new ProtocalHandler();
                SetDeviceConfig setDeviceConfig = new SetDeviceConfig();
                setDeviceConfig.setPowerType(power); // 电源类型
                setDeviceConfig.setQbflag(handgear); // 单手具 0 单  1 双
                if (mediaVolume == 0) {//当前音量
                    setDeviceConfig.setHornFlag(0); // 设备关 上位机开  喇叭 0 下关上开 1 下开上开 2 下开上关
                } else {
                    setDeviceConfig.setHornFlag(1); // 设备关 上位机开  喇叭 0 下关上开 1 下开上开 2 下开上关
                }
                Frame frame2 = protocalHandler.buildSetDeviceConfigFrame(setDeviceConfig);
                String message = HexUtil.encodeHexStr(frame2.getFrame()).toUpperCase();
                Console.log(ParserUtil.toHexString(frame2.getFrame()));
                sendSerialPortHexMsg(message,frame2.getCtrlCode());
                LogUtils.e("下发数据"+message);

                SetClearCount();

                Intent intent = new Intent(EngineerSettingActivity.this, SplashActivity.class);
                startActivity(intent);
            }
        });
        leftHandgear();
        rightHandgear();
        isCount();
        checkWifi();
        checkHandgear();
        isAutoShed();
        powerType();

    }
    public void SetClearCount(){
        ProtocalHandler protocalHandler = new ProtocalHandler();
        SetWarningConfig setWarningConfig = new SetWarningConfig();
        setWarningConfig.setFlowVelocityWarningFlag(0); // 是否开启告警 0 关闭  1 开启
        setWarningConfig.setFlowVelocity(0); // 水流下限
        setWarningConfig.setTemperatureWarningFlag(0); // 是否开启告警 0 关闭  1 开启
        setWarningConfig.setTemperature(0); // 温度上限
        if (!isCount){
            setWarningConfig.setResetTotalCountFlag(1); // 不重置计数器  0 不清空  1 清空
//            AppConfig.current_count = 0;
        }else {
            setWarningConfig.setResetTotalCountFlag(0); // 不重置计数器  0 不清空  1 清空

        }
        Frame frame1 = protocalHandler.buildSetWarningConfig(setWarningConfig);
        String message = ParserUtil.toHexString(frame1.getFrame());
        Console.log(ParserUtil.toHexString(frame1.getFrame()));
        sendSerialPortHexMsg(message,frame1.getCtrlCode());
        LogUtils.e(setWarningConfig.toString());
        LogUtils.e("下发数据"+message);
    }

    /*
     * 左手具
     * */
    private void leftHandgear() {
        RadioGroup rg_left = findViewById(R.id.rg_left);
        RadioButton rb_left1 = findViewById(R.id.rb_left1);
        RadioButton rb_left2 = findViewById(R.id.rb_left2);
        RadioButton rb_left3 = findViewById(R.id.rb_left3);
        RadioButton rb_left4 = findViewById(R.id.rb_left4);
        RadioButton rb_left5 = findViewById(R.id.rb_left5);
        RadioButton rb_left6 = findViewById(R.id.rb_left6);
        RadioButton rb_left7 = findViewById(R.id.rb_left7);
        rg_left.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_left1:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_LEFT, 1);
                        break;
                    case R.id.rb_left2:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_LEFT, 2);
                        break;
                    case R.id.rb_left3:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_LEFT, 3);
                        break;
                    case R.id.rb_left4:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_LEFT, 4);
                        break;
                    case R.id.rb_left5:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_LEFT, 5);
                        break;
                    case R.id.rb_left6:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_LEFT, 6);
                        break;
                    case R.id.rb_left7:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_LEFT, 7);
                        break;

                }
            }
        });
        int intValue = SharedPrefsUtil.getIntValue(AppConfig.HAND_LEFT, 1);
        if (intValue == 1) {
            rb_left1.setChecked(true);
        } else if (intValue == 2) {
            rb_left2.setChecked(true);
        } else if (intValue == 3) {
            rb_left3.setChecked(true);
        } else if (intValue == 4) {
            rb_left4.setChecked(true);
        } else if (intValue == 5) {
            rb_left5.setChecked(true);
        } else if (intValue == 6) {
            rb_left6.setChecked(true);
        } else if (intValue == 7) {
            rb_left7.setChecked(true);
        }
    }

    /*
     * 右手具
     * */
    private void rightHandgear() {
        rg_right = findViewById(R.id.rg_right);
        rb_right1 = findViewById(R.id.rb_right1);
        rb_right2 = findViewById(R.id.rb_right2);
        rb_right3 = findViewById(R.id.rb_right3);
        rb_right4 = findViewById(R.id.rb_right4);
        rb_right5 = findViewById(R.id.rb_right5);
        rb_right6 = findViewById(R.id.rb_right6);
        rb_right7 = findViewById(R.id.rb_right7);
        rg_right.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_right1:
//                        ToastUtil.showToast(EngineerSettingActivity.this, "rb_right1");
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_RIGHT, 1);
                        break;
                    case R.id.rb_right2:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_RIGHT, 2);
                        break;
                    case R.id.rb_right3:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_RIGHT, 3);
                        break;
                    case R.id.rb_right4:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_RIGHT, 4);
                        break;
                    case R.id.rb_right5:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_RIGHT, 5);
                        break;
                    case R.id.rb_right6:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_RIGHT, 6);
                        break;
                    case R.id.rb_right7:
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_RIGHT, 7);
                        break;

                }
            }
        });
        selectRightHandgear();
    }

    private void selectRightHandgear() {
        int booleanValue = SharedPrefsUtil.getIntValue(AppConfig.HANDGEAR, 0);
        int intValue = SharedPrefsUtil.getIntValue(AppConfig.HAND_RIGHT, 1);
        if (booleanValue == 0) {//单手==左手
            for (int i = 0; i < rg_right.getChildCount(); i++) {
                rg_right.getChildAt(i).setEnabled(false);
            }
            rg_right.clearCheck();//清除之前所有的选中状态 ，这个很重要
        } else {//双手 =  左右手
            for (int i = 0; i < rg_right.getChildCount(); i++) {
                rg_right.getChildAt(i).setEnabled(true);
            }
            if (intValue == 0){//选择过单手具 然后设置双手具（右手具）为第一项
                 intValue = 1;
            }
        }
        if (intValue == 1) {
            rb_right1.setChecked(true);
        } else if (intValue == 2) {
            rb_right2.setChecked(true);
        } else if (intValue == 3) {
            rb_right3.setChecked(true);
        } else if (intValue == 4) {
            rb_right4.setChecked(true);
        } else if (intValue == 5) {
            rb_right5.setChecked(true);
        } else if (intValue == 6) {
            rb_right6.setChecked(true);
        } else if (intValue == 7) {
            rb_right7.setChecked(true);
        }
    }


    /*
     * 是否开启计数
     * */
    private void isCount() {
        bt_count = findViewById(R.id.bt_count);
        bt_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                if (isCount) {
                    isCount = false;
                    bt_count.setBackground(getResources().getDrawable(R.drawable.engineer_bt_select_rounded_corners));
//                    SharedPrefsUtil.putBooleanValue(AppConfig.COUNT, true);

                } else {
                    isCount = true;
                    bt_count.setBackground(getResources().getDrawable(R.drawable.engineer_bt_rounded_corners));
//                    SharedPrefsUtil.putBooleanValue(AppConfig.COUNT, false);

                }
            }
        });
//        boolean booleanValue = SharedPrefsUtil.getBooleanValue(AppConfig.COUNT, false);
//        if (booleanValue) {
//            isCount = false;
//            bt_count.setBackground(getResources().getDrawable(R.drawable.engineer_bt_select_rounded_corners));
//            SharedPrefsUtil.putBooleanValue(AppConfig.COUNT, true);
//        } else {
//            isCount = true;
//            bt_count.setBackground(getResources().getDrawable(R.drawable.engineer_bt_rounded_corners));
//            SharedPrefsUtil.putBooleanValue(AppConfig.COUNT, false);
//        }
    }

    /*
     * 配置是否wifi
     * */
    private void checkWifi() {
        RadioGroup rg_wifi = findViewById(R.id.rg_wifi);
        RadioButton rb_wifi_on = findViewById(R.id.rb_wifi_on);
        RadioButton rb_wifi_off = findViewById(R.id.rb_wifi_off);
        rg_wifi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_wifi_on:
                        SharedPrefsUtil.putBooleanValue(AppConfig.WIFI, true);

                        break;
                    case R.id.rb_wifi_off:
                        SharedPrefsUtil.putBooleanValue(AppConfig.WIFI, false);
                        break;
                }
            }
        });
        boolean booleanValue = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
        if (booleanValue) {
            rb_wifi_on.setChecked(true);
        } else {
            rb_wifi_off.setChecked(true);
        }
    }

    /*
     * 配置手具单或者双
     * */
    private void checkHandgear() {
        RadioGroup rg_hangear = findViewById(R.id.rg_hangear);
        RadioButton rb_hangear_one = findViewById(R.id.rb_hangear_one);
        RadioButton rb_hangear_two = findViewById(R.id.rb_hangear_two);
        rg_hangear.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_hangear_one:
                        SharedPrefsUtil.putIntValue(AppConfig.HANDGEAR, 0);
                        SharedPrefsUtil.putIntValue(AppConfig.HAND_RIGHT, 0);
                        selectRightHandgear();
                        break;
                    case R.id.rb_hangear_two:
                        SharedPrefsUtil.putIntValue(AppConfig.HANDGEAR, 1);
                        selectRightHandgear();
                        break;
                }
            }
        });
        int booleanValue = SharedPrefsUtil.getIntValue(AppConfig.HANDGEAR, 0);
        if (booleanValue == 0) {//单手==左手
            rb_hangear_one.setChecked(true);
        } else {//双手 =  左右手
            rb_hangear_two.setChecked(true);

        }
    }

    /*
     * 是否自助脱毛
     * */
    private void isAutoShed() {
        RadioGroup rg_shed = findViewById(R.id.rg_shed);
        RadioButton rb_shed_on = findViewById(R.id.rb_shed_on);
        RadioButton rb_shed_off = findViewById(R.id.rb_shed_off);
        rg_shed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_shed_on:
                        SharedPrefsUtil.putBooleanValue(AppConfig.SHED, true);
                        break;
                    case R.id.rb_shed_off:
                        SharedPrefsUtil.putBooleanValue(AppConfig.SHED, false);
                        break;
                }
            }
        });
        boolean booleanValue = SharedPrefsUtil.getBooleanValue(AppConfig.SHED, false);
        if (booleanValue) {
            rb_shed_on.setChecked(true);
        } else {
            rb_shed_off.setChecked(true);
        }
    }

    /*
     * 电源类型
     * */
    private void powerType() {
        RadioGroup rg_power = findViewById(R.id.rg_power);
        RadioButton rb_power1 = findViewById(R.id.rb_power1);
        RadioButton rb_power2 = findViewById(R.id.rb_power2);
        RadioButton rb_power3 = findViewById(R.id.rb_power3);
        RadioButton rb_power4 = findViewById(R.id.rb_power4);
        RadioButton rb_power5 = findViewById(R.id.rb_power5);
        RadioButton rb_power6 = findViewById(R.id.rb_power6);
        rg_power.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_power1:
                        SharedPrefsUtil.putIntValue(AppConfig.POWER_TYPE, 1);
                        break;
                    case R.id.rb_power2:
                        SharedPrefsUtil.putIntValue(AppConfig.POWER_TYPE, 2);
                        break;
                    case R.id.rb_power3:
                        SharedPrefsUtil.putIntValue(AppConfig.POWER_TYPE, 3);
                        break;
                    case R.id.rb_power4:
                        SharedPrefsUtil.putIntValue(AppConfig.POWER_TYPE, 4);
                        break;
                    case R.id.rb_power5:
                        SharedPrefsUtil.putIntValue(AppConfig.POWER_TYPE, 5);
                        break;
                    case R.id.rb_power6:
                        SharedPrefsUtil.putIntValue(AppConfig.POWER_TYPE, 6);
                        break;

                }
            }
        });
        int intValue = SharedPrefsUtil.getIntValue(AppConfig.POWER_TYPE, 1);
        if (intValue == 1) {
            rb_power1.setChecked(true);
        } else if (intValue == 2) {
            rb_power2.setChecked(true);
        } else if (intValue == 3) {
            rb_power3.setChecked(true);
        } else if (intValue == 4) {
            rb_power4.setChecked(true);
        } else if (intValue == 5) {
            rb_power5.setChecked(true);
        } else if (intValue == 6) {
            rb_power6.setChecked(true);
        }
    }
}