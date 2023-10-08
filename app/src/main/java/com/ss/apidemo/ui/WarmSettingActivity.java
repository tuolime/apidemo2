package com.ss.apidemo.ui;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetDeviceConfig;
import com.example.protocol.frame.data.SetWarningConfig;
import com.example.protocol.utils.ParserUtil;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.bean.HrModeBean;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.HrModeUtils;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.NetworkUtil;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.HexUtil;

/*
 * 告警  设置温度、水流。。。
 * 温度最大值36度，最小值12度，默认30，步长1 ，水流调节0-3.0L/min，默认是2L/min 步长0.1
 * */
public class WarmSettingActivity extends BaseActivity {

    private TextView tv_temperature;
    private TextView tv_water;
    private TextView tv_up_enegry;
    private TextView tv_lower_energy;
    private int temperature_count = 30;
    private int water_count = 20;
    private double[] water_count_array = {0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,
    1.8,1.9,2.0,2.1,2.2,2.3,2.4,2.5,2.6,2.7,2.8,2.9,3.0};
    private boolean water;
    private boolean temperature;
    private boolean count_clear =false;
    private HrModeBean hrModeBean;
    int handgearType = 0;
    private int current_energy_max;
    private int current_energy_min;
    private int current_energyUpper;
    private int current_energyLower;
    private WifiManager  wifiManager;
    private AudioManager mAudioManager;
    int mediaVolume;
    private boolean clickFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warm_setting);
        LinearLayout ll_main = findViewById(R.id.ll_main);
        BackgroundChangeUtils.backgroundChange(this,ll_main);
        wifiManager = (WifiManager) WarmSettingActivity.this.getSystemService(Context.WIFI_SERVICE);

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
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);

//                double waterDouble = water_count_array[water_count];
//                int waterInt = (int) (waterDouble *10);
                String waterString = tv_water.getText().toString();
                Double fromString = new Double(waterString);
                int waterInt = (int) (fromString *10);

                //退出时 下发报文选择好的温度和水流。。。。

                ProtocalHandler protocalHandler = new ProtocalHandler();
                SetWarningConfig setWarningConfig = new SetWarningConfig();
                boolean lastWater = SharedPrefsUtil.getBooleanValue(AppConfig.WATER, true);
                if (lastWater){
                    setWarningConfig.setFlowVelocityWarningFlag(1); // 是否开启告警 0 关闭  1 开启
                    setWarningConfig.setFlowVelocity(waterInt); // 水流下限
                }else {
                    setWarningConfig.setFlowVelocityWarningFlag(0); // 是否开启告警 0 关闭  1 开启
                    setWarningConfig.setFlowVelocity(waterInt); // 水流下限
                }
                String temperatureString = tv_temperature.getText().toString();
                int temperatureInt = Integer.parseInt(temperatureString);
                boolean lastTemperature = SharedPrefsUtil.getBooleanValue(AppConfig.TEMPERATURE, true);
                if (lastTemperature){
                    setWarningConfig.setTemperatureWarningFlag(1); // 是否开启告警 0 关闭  1 开启
                    setWarningConfig.setTemperature(temperatureInt); // 温度上限
                }else {
                    setWarningConfig.setTemperatureWarningFlag(0); // 是否开启告警 0 关闭  1 开启
                    setWarningConfig.setTemperature(temperatureInt); // 温度上限
                }
               if (count_clear){
                   setWarningConfig.setResetTotalCountFlag(1); // 不重置计数器  0 不清空  1 清空
//                   AppConfig.current_count = 0;
               }else {
                   setWarningConfig.setResetTotalCountFlag(0); // 不重置计数器  0 不清空  1 清空

               }
                Frame frame1 = protocalHandler.buildSetWarningConfig(setWarningConfig);
                String message = ParserUtil.toHexString(frame1.getFrame());
                Console.log(ParserUtil.toHexString(frame1.getFrame()));
                sendSerialPortHexMsg(message,frame1.getCtrlCode());
                LogUtils.e(setWarningConfig.toString());
                LogUtils.e("下发数据"+message);
                //下发蓝牙信息 和以上报文不是同一个
                int power = SharedPrefsUtil.getIntValue(AppConfig.POWER_TYPE, 1);
                int handgear = SharedPrefsUtil.getIntValue(AppConfig.HANDGEAR, 0);
                ProtocalHandler protocalHandler2 = new ProtocalHandler();
                SetDeviceConfig setDeviceConfig = new SetDeviceConfig();
                setDeviceConfig.setPowerType(power); // 电源类型
                setDeviceConfig.setQbflag(handgear); // 单手具 0 单  1 双
                if (mediaVolume == 0) {//当前音量
                    setDeviceConfig.setHornFlag(0); // 设备关 上位机开  喇叭 0 下关上开 1 下开上开 2 下开上关
                } else {
                    setDeviceConfig.setHornFlag(1); // 设备关 上位机开  喇叭 0 下关上开 1 下开上开 2 下开上关
                }
                boolean bluetooth = SharedPrefsUtil.getBooleanValue(AppConfig.BLUETOOTH, false);
                if (bluetooth){
                    setDeviceConfig.setBluetoothFlag(1); // 蓝牙 0 关闭  1 开启
                }else {
                    setDeviceConfig.setBluetoothFlag(0); // 蓝牙 0 关闭  1 开启
                }
                Frame frame2 = protocalHandler2.buildSetDeviceConfigFrame(setDeviceConfig);
                String message2 = HexUtil.encodeHexStr(frame2.getFrame()).toUpperCase();
                Console.log(ParserUtil.toHexString(frame2.getFrame()));
                sendSerialPortHexMsg(message2,frame2.getCtrlCode());
                LogUtils.e("下发数据"+message2);

                //退出该页面
                finish();
            }
        });
        tv_temperature = findViewById(R.id.tv_temperature);
        int temperature_shared = SharedPrefsUtil.getIntValue(AppConfig.TEMPERATURE_COUNT, 0);
        if (temperature_shared != 0){
            tv_temperature.setText(""+temperature_shared);
        }else {
            SharedPrefsUtil.putIntValue(AppConfig.TEMPERATURE_COUNT, temperature_count);
            tv_temperature.setText(""+temperature_count);
        }

        tv_water = findViewById(R.id.tv_water);
        int water_shared = SharedPrefsUtil.getIntValue(AppConfig.WATER_COUNT, 0);
        if (water_shared != 0){
            double doubleWater = (double) water_shared/10;
            tv_water.setText(""+doubleWater);
        }else {
            int waterInt = (int) (water_count_array[water_count] *10);
            SharedPrefsUtil.putIntValue(AppConfig.WATER_COUNT, waterInt);
            tv_water.setText(""+water_count_array[water_count]);
        }
        RadioGroup rg_temperature = findViewById(R.id.rg_temperature);
        RadioGroup rg_water = findViewById(R.id.rg_water);
        RadioGroup rg_count_clear = findViewById(R.id.rg_count_clear);

        RadioButton rb_temperature_on = findViewById(R.id.rb_temperature_on);
        RadioButton rb_temperature_off = findViewById(R.id.rb_temperature_off);
        temperature = SharedPrefsUtil.getBooleanValue(AppConfig.TEMPERATURE, true);
        if (temperature){
            rb_temperature_on.setChecked(true);
        }else {
            rb_temperature_off.setChecked(true);
        }

        RadioButton rb_water_on = findViewById(R.id.rb_water_on);
        RadioButton rb_water_off = findViewById(R.id.rb_water_off);
        water = SharedPrefsUtil.getBooleanValue(AppConfig.WATER, true);
        if (water){
            rb_water_on.setChecked(true);
        }else {
            rb_water_off.setChecked(true);
        }
        RadioButton rb_count_clear_on = findViewById(R.id.rb_count_clear_on);
        RadioButton rb_count_clear_off = findViewById(R.id.rb_count_clear_off);
//        count_clear = SharedPrefsUtil.getBooleanValue(AppConfig.COUNT_CLEAR, true);
        if (count_clear){
            rb_count_clear_on.setChecked(true);
        }else {
            rb_count_clear_off.setChecked(true);
        }
        //温度
        rg_temperature.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_temperature_on:
                        SharedPrefsUtil.putBooleanValue(AppConfig.TEMPERATURE,true);
                        break;
                    case R.id.rb_temperature_off:
                        SharedPrefsUtil.putBooleanValue(AppConfig.TEMPERATURE,false);
                        break;
                }
            }
        });
        //水流
        rg_water.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_water_on:
                        SharedPrefsUtil.putBooleanValue(AppConfig.WATER, true);
                        break;
                    case R.id.rb_water_off:
                        SharedPrefsUtil.putBooleanValue(AppConfig.WATER, false);
                        break;
                }
            }
        });
        //计数清除
        rg_count_clear.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_count_clear_on:
                        count_clear = true;
//                        SharedPrefsUtil.putBooleanValue(AppConfig.COUNT_CLEAR, true);
                        break;
                    case R.id.rb_count_clear_off:
                        count_clear = false;
//                        SharedPrefsUtil.putBooleanValue(AppConfig.COUNT_CLEAR, false);
                        break;
                }
            }
        });

        RadioGroup rg_user_down = findViewById(R.id.rg_user_down);
        RadioButton rb_user_down_on = findViewById(R.id.rb_user_down_on);
        RadioButton rb_user_down_off = findViewById(R.id.rb_user_down_off);
        boolean userDataDownLoad = SharedPrefsUtil.getBooleanValue(AppConfig.USERDATADOWMLOAD, false);
        if (userDataDownLoad){
            rb_user_down_on.setChecked(true);
        }else {
            rb_user_down_off.setChecked(true);
        }
        //用户数据下载
        rg_user_down.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_user_down_on:
                        SharedPrefsUtil.putBooleanValue(AppConfig.USERDATADOWMLOAD,true);
                        break;
                    case R.id.rb_user_down_off:
                        SharedPrefsUtil.putBooleanValue(AppConfig.USERDATADOWMLOAD,false);
                        break;
                }
            }
        });

        RadioGroup rg_mode_select = findViewById(R.id.rg_mode_select);
        RadioButton rb_mode_select_1 = findViewById(R.id.rb_mode_select_1);
        RadioButton rb_mode_select_2 = findViewById(R.id.rb_mode_select_2);
        int modeType = SharedPrefsUtil.getIntValue(AppConfig.MODETYPE, 1);
        if (modeType == 1){
            rb_mode_select_1.setChecked(true);
        }else if (modeType == 2){
            rb_mode_select_2.setChecked(true);
        }
        //模式选择
        rg_mode_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_mode_select_1:
                        SharedPrefsUtil.putIntValue(AppConfig.MODETYPE,1);
                        startA();
                        break;
                    case R.id.rb_mode_select_2:
                        SharedPrefsUtil.putIntValue(AppConfig.MODETYPE,2);
                        startA();
                        break;
                }
            }
        });

        RadioGroup rg_wlan = findViewById(R.id.rg_wlan);
        RadioButton rb_wlan_on = findViewById(R.id.rb_wlan_on);
        RadioButton rb_wlan_off = findViewById(R.id.rb_wlan_off);
//        boolean wlan = SharedPrefsUtil.getBooleanValue(AppConfig.WLAN, false);
//        boolean wlan = NetworkUtil.isWifi();
        boolean wlan = wifiManager.isWifiEnabled();
        if (wlan){
            rb_wlan_on.setChecked(true);
        }else {
            rb_wlan_off.setChecked(true);
        }
        //开启网络
        rg_wlan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_wlan_on:
                        if (clickFlag){
                            return;
                        }
//                        SharedPrefsUtil.putBooleanValue(AppConfig.WLAN,true);
                        if (wifiManager != null){
                            wifiManager.setWifiEnabled(true);
                            ShowDialog(R.string.wifi_open);
                            SharedPrefsUtil.putBooleanValue(AppConfig.WIFI, true);
                            MyApplication.instance().destroyTask();
                            MyApplication.instance().connetSocket();
                        }
                        break;
                    case R.id.rb_wlan_off:
                        clickFlag = false;
//                        SharedPrefsUtil.putBooleanValue(AppConfig.WLAN,false);
                        //useLimitedFlag 用限制标识，0否1是
                        if (AppConfig.useLimitedFlag == 0){
                            if (wifiManager != null){
                                wifiManager.setWifiEnabled(false);
                                ShowDialog(R.string.wifi_close);
                                SharedPrefsUtil.putBooleanValue(AppConfig.WIFI, false);
                                MyApplication.instance().destroyTask();
//                                MyApplication.instance().connetSocket();
                            }
                        }else {
                            clickFlag = true;
                            rb_wlan_on.setChecked(true);
                            ShowDialog(R.string.wifi_no_close);
                        }
                        break;
                }
            }
        });

        RadioGroup rg_background_select = findViewById(R.id.rg_background_select);
        RadioButton rb_background_select_1 = findViewById(R.id.rb_background_select_1);
        RadioButton rb_background_select_2 = findViewById(R.id.rb_background_select_2);
        RadioButton rb_background_select_3 = findViewById(R.id.rb_background_select_3);
        RadioButton rb_background_select_4 = findViewById(R.id.rb_background_select_4);
        int backgroundSelect = SharedPrefsUtil.getIntValue(AppConfig.BACKGROUNDSELECT, 1);
        if (backgroundSelect == 1){
            rb_background_select_1.setChecked(true);
        }else if (backgroundSelect == 2){
            rb_background_select_2.setChecked(true);
        }else if (backgroundSelect == 3){
            rb_background_select_3.setChecked(true);
        }else if (backgroundSelect == 4){
            rb_background_select_4.setChecked(true);
        }
        //背景主题选择
        rg_background_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_background_select_1:
                        SharedPrefsUtil.putIntValue(AppConfig.BACKGROUNDSELECT,1);
                        SetBackgroundColor(1);
                        startA();
                        break;
                    case R.id.rb_background_select_2:
                        SharedPrefsUtil.putIntValue(AppConfig.BACKGROUNDSELECT,2);
                        SetBackgroundColor(2);
                        startA();
                        break;
                    case R.id.rb_background_select_3:
                        SharedPrefsUtil.putIntValue(AppConfig.BACKGROUNDSELECT,3);
                        SetBackgroundColor(3);
                        startA();
                        break;
                    case R.id.rb_background_select_4:
                        SharedPrefsUtil.putIntValue(AppConfig.BACKGROUNDSELECT,4);
                        SetBackgroundColor(4);
                        startA();
                        break;
                }
            }
        });

        RadioGroup rg_bluetooth_select = findViewById(R.id.rg_bluetooth_select);
        RadioButton rb_bluetooth_select_on = findViewById(R.id.rb_bluetooth_select_on);
        RadioButton rb_bluetooth_select_off = findViewById(R.id.rb_bluetooth_select_off);
        boolean bluetooth = SharedPrefsUtil.getBooleanValue(AppConfig.BLUETOOTH, false);
        if (bluetooth){
            rb_bluetooth_select_on.setChecked(true);
        }else {
            rb_bluetooth_select_off.setChecked(true);
        }
        //蓝牙选择
        rg_bluetooth_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rb_bluetooth_select_on:
                        SharedPrefsUtil.putBooleanValue(AppConfig.BLUETOOTH,true);
                        startA();
                        break;
                    case R.id.rb_bluetooth_select_off:
                        SharedPrefsUtil.putBooleanValue(AppConfig.BLUETOOTH,false);
                        startA();
                        break;
                }
            }
        });

        //能量上限
        loadEnergy();
        tv_up_enegry = findViewById(R.id.tv_up_enegry);
        current_energyUpper = SharedPrefsUtil.getIntValue(AppConfig.ENERGYUPPER, 0);
        if (current_energyUpper == 0){
            current_energyUpper = current_energy_max;
            tv_up_enegry.setText(""+ current_energyUpper);
        }else {
            tv_up_enegry.setText(""+ current_energyUpper);
        }

        //能量下限
        tv_lower_energy = findViewById(R.id.tv_lower_energy);
        if (hrModeBean != null){
            current_energyLower = SharedPrefsUtil.getIntValue(AppConfig.ENERGYLOWER, current_energy_min);
            tv_lower_energy.setText(""+ current_energyLower);
        }
    }
    public void selectClick(View view){
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        switch (view.getId()) {
            case R.id.iv_temperature_up:
                if (temperature_count == 36){
                    return;
                }
                temperature_count++;
                tv_temperature.setText(""+temperature_count);
                SharedPrefsUtil.putIntValue(AppConfig.TEMPERATURE_COUNT, temperature_count);
                break;
            case R.id.iv_temperature_down:
                if (temperature_count == 12){
                    return;
                }
                temperature_count--;

                tv_temperature.setText(""+temperature_count);
                SharedPrefsUtil.putIntValue(AppConfig.TEMPERATURE_COUNT, temperature_count);
                break;
            case R.id.iv_water_up:
                if (water_count == 30){
                    return;
                }
                water_count++;

                tv_water.setText(""+water_count_array[water_count]);
                int waterInt = (int) (water_count_array[water_count] *10);
                SharedPrefsUtil.putIntValue(AppConfig.WATER_COUNT, waterInt);
                break;
            case R.id.iv_water_down:
                if (water_count == 0){
                    return;
                }
                water_count--;
                tv_water.setText(""+water_count_array[water_count]);
                int waterInts = (int) (water_count_array[water_count] *10);
                SharedPrefsUtil.putIntValue(AppConfig.WATER_COUNT, waterInts);
                break;
            case R.id.iv_energy_up_up:
                if (current_energyUpper == current_energy_max){
                    return;
                }
                current_energyUpper++;
                tv_up_enegry.setText(""+current_energyUpper);
                SharedPrefsUtil.putIntValue(AppConfig.ENERGYUPPER, current_energyUpper);
                break;
            case R.id.iv_energy_up_down:
                if (current_energyUpper == current_energy_min){
                    return;
                }
                current_energyUpper--;
                tv_up_enegry.setText(""+current_energyUpper);
                SharedPrefsUtil.putIntValue(AppConfig.ENERGYUPPER, current_energyUpper);
                break;
//            case R.id.iv_energy_lower_up:
//                if (current_energyLower == current_energy_max){
//                    return;
//                }
//                current_energyLower++;
//                tv_lower_energy.setText(""+current_energyLower);
//                SharedPrefsUtil.putIntValue(AppConfig.ENERGYLOWER, current_energyLower);
//                break;
//            case R.id.iv_energy_lower_down:
//                if (current_energyLower == current_energy_min){
//                    return;
//                }
//                current_energyLower--;
//                tv_lower_energy.setText(""+current_energyLower);
//                SharedPrefsUtil.putIntValue(AppConfig.ENERGYLOWER, current_energyLower);
//                break;
        }
    }

    public void loadEnergy(){
        HrModeUtils hrModeUtils = new HrModeUtils();

        if (AppConfig.handgearSelect == 1) {//单手==左手
            handgearType = SharedPrefsUtil.getIntValue(AppConfig.HAND_LEFT, 1);
        } else if (AppConfig.handgearSelect == 0) {//双手 =  左右手
            handgearType = SharedPrefsUtil.getIntValue(AppConfig.HAND_RIGHT, 1);
        }
        int gender = SharedPrefsUtil.getIntValue(AppConfig.GENDER, 1);
        hrModeBean = hrModeUtils.modeType(gender, handgearType);
//        if (handgearType == 3 || handgearType == 7 || handgearType == 8 || handgearType == 9) {
//            current_energy_max = hrModeBean.getFluence1HzMax();
//            current_energy_min = hrModeBean.getFluenceMin();
//        } else {
            current_energy_max = hrModeBean.getFluence1HzMax();
            current_energy_min = hrModeBean.getFluenceMin();

//        }
    }
    public void startA() {
        startActivity(new Intent(WarmSettingActivity.this, SplashActivity.class));
    }

    /*
     * 预留调用方法 设置背景主题色
     * */
    private void SetBackgroundColor(int type){
        SharedPrefsUtil.putIntValue(AppConfig.BACKGROUNDSELECT, type);//设置主题
        startA();
    }

    private  void ShowDialog(int text){
        HintDialog dialog = new HintDialog(WarmSettingActivity.this);
        dialog.loadDialog(WarmSettingActivity.this, new HintDialog.OnClickIsConfirm() {
            @Override
            public void OnClickIsConfirmListener() {//确定
            }

        }, getResources().getString(text));
    }
}