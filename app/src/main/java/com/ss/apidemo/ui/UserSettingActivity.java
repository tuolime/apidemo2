package com.ss.apidemo.ui;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetDeviceConfig;
import com.example.protocol.frame.data.SetWarningConfig;
import com.example.protocol.utils.ParserUtil;
import com.ss.api.SsApi;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.db.dao.UserValueDao;
import com.ss.apidemo.dialog.CallDialog;
import com.ss.apidemo.utils.DeviceInfoUtil;
import com.ss.apidemo.utils.LocaleHelper;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ToastUtil;
import com.ss.apidemo.widget.BrightnessUtil;
import com.ss.apidemo.widget.NumberKeyboardView;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.HexUtil;

import static com.ss.apidemo.widget.BrightnessUtil.ModifySettingsScreenBrightness;

/*
 * 用户设置页面 包含：系统声音 系统屏幕亮度 多语言 密码验证
 * */
public class UserSettingActivity extends BaseActivity implements NumberKeyboardView.OnNumberClickListener {
    private NumberKeyboardView mNkvKeyboard;

    private String str = "";
    private TextView[] tvList;
    private List<String> valueList;
    private SeekBar mCarVolume;
    private AudioManager mAudioManager;
    int mediaMaxVolume = 10;
    int mediaVolume;
    private TextView tv_limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        initView();
    }

    private void initView() {
        tvList = new TextView[6];
        valueList = new ArrayList<>();
        tvList[0] = (TextView) findViewById(R.id.tv_ps1);
        tvList[1] = (TextView) findViewById(R.id.tv_ps2);
        tvList[2] = (TextView) findViewById(R.id.tv_ps3);
        tvList[3] = (TextView) findViewById(R.id.tv_ps4);
        tvList[4] = (TextView) findViewById(R.id.tv_ps5);
        tvList[5] = (TextView) findViewById(R.id.tv_ps6);
        mNkvKeyboard = (NumberKeyboardView) findViewById(R.id.am_nkv_keyboard);
        mNkvKeyboard.setOnNumberClickListener(this);
        initVolume();
        initBrightness();
        TextView tv_deviceId = findViewById(R.id.tv_deviceId);
//        String deviceId = DeviceInfoUtil.getMac(UserSettingActivity.this);
        String deviceId = DeviceInfoUtil.getMac();
        tv_deviceId.setText(getResources().getString(R.string.id) + ":" + deviceId);
        tv_limit = findViewById(R.id.tv_limit);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SsApi.getInstance().execCmd("am broadcast -a \"action.ACTION_API_HIDE_NAVIGATION\"");
//                SsApi.getInstance().execCmd("am broadcast -a \"action.ACTION_API_HIDE_STATUS_BAR\"");
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
                sendSerialPortHexMsg(message, frame2.getCtrlCode());
                LogUtils.e("下发数据bean" + setDeviceConfig.toString());
                LogUtils.e("下发数据" + message);
                //退出该页面
                finish();
            }
        });

        findViewById(R.id.tv_en).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                CallDialog dialog = new CallDialog(UserSettingActivity.this);
                dialog.loadDialog(UserSettingActivity.this, new CallDialog.OnClickIsConfirm() {
                    @Override
                    public void OnClickIsConfirmListener() {//确定
                        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                        // 切换语言
                        LocaleHelper.setLocale(UserSettingActivity.this, "en");
                        startA();
                    }

                }, getResources().getString(R.string.switching_languages));
            }
        });
        findViewById(R.id.tv_ru).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                CallDialog dialog = new CallDialog(UserSettingActivity.this);
                dialog.loadDialog(UserSettingActivity.this, new CallDialog.OnClickIsConfirm() {
                    @Override
                    public void OnClickIsConfirmListener() {//确定
                        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                        // 切换语言 俄语
                        LocaleHelper.setLocale(UserSettingActivity.this, "ru");
                        startA();
                    }

                }, getResources().getString(R.string.switching_languages));
            }
        });
        findViewById(R.id.tb_sp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                CallDialog dialog = new CallDialog(UserSettingActivity.this);
                dialog.loadDialog(UserSettingActivity.this, new CallDialog.OnClickIsConfirm() {
                    @Override
                    public void OnClickIsConfirmListener() {//确定
                        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                        // 切换语言 西班牙语
                        LocaleHelper.setLocale(UserSettingActivity.this, "sp");
                        startA();
                    }

                }, getResources().getString(R.string.switching_languages));
            }
        });
        findViewById(R.id.tv_de).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                CallDialog dialog = new CallDialog(UserSettingActivity.this);
                dialog.loadDialog(UserSettingActivity.this, new CallDialog.OnClickIsConfirm() {
                    @Override
                    public void OnClickIsConfirmListener() {//确定
                        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                        // 切换语言 德语
                        LocaleHelper.setLocale(UserSettingActivity.this, "de");
                        startA();
                    }

                }, getResources().getString(R.string.switching_languages));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AppConfig.useLimitedFlag == 0) {
            tv_limit.setVisibility(View.INVISIBLE);
        } else {
            tv_limit.setVisibility(View.VISIBLE);
            if (AppConfig.useLimitedType.equals("day")) {
                tv_limit.setText(getResources().getString(R.string.days_remaining) + ":" + AppConfig.remainNum);
            } else if (AppConfig.useLimitedType.equals("hour")) {
                tv_limit.setText(getResources().getString(R.string.hours_remaining) + ":" + AppConfig.remainNum);
            } else if (AppConfig.useLimitedType.equals("count")) {
                tv_limit.setText(getResources().getString(R.string.count_remaining) + ":" + AppConfig.remainNum);
            }
        }
    }

    @Override
    public void onNumberReturn(String number) {

        if (valueList.size() > 5) {
            return;
        }
        if (number.equals("")) {
            return;
        }
        str += number;
        valueList.add(number);
        setTextContent(false, str);
    }

    @Override
    public void onNumberDelete() {
        if (str.length() <= 1) {
            str = "";
            valueList.clear();
        } else {
            str = str.substring(0, str.length() - 1);
            valueList.remove(str.length() - 1);
        }
        setTextContent(true, str);
    }

    private void setTextContent(boolean falg, String content) {
        if (falg) {
            if (valueList.size() >= 0) {
                tvList[valueList.size()].setText("");
            }

        } else {
            if (valueList.size() > 0) {
                tvList[valueList.size() - 1].setText(valueList.get(valueList.size() - 1));
            }
            if (content.length() == 6) {
                if (content.equals(AppConfig.USERSTARTPS)) {
                    clearContent();
                    SsApi.getInstance().execCmd("am broadcast -a \"action.ACTION_API_SHOW_NAVIGATION\"");
                    SsApi.getInstance().execCmd("am broadcast -a \"action.ACTION_API_SHOW_STATUS_BAR\"");
                    ToastUtil.showToast(UserSettingActivity.this, getResources().getString(R.string.system_setting));
                } else if (content.equals(AppConfig.USERSETTINGPS)) {
                    clearContent();
                    startActivity(new Intent(this, WarmSettingActivity.class));
                    finish();
                    return;
                } else {
                    ToastUtil.showToast(UserSettingActivity.this, getResources().getString(R.string.ps_error));
                }
            }
        }

    }
    public void clearContent(){
        str = "";
        for (int i = 0; i <valueList.size() ; i++) {
            tvList[i].setText("");
        }
        valueList.clear();
    }

    private void initBrightness() {
        SeekBar mCarBrightness = findViewById(R.id.sb_brightness);
//        if (!Settings.System.canWrite(UserSettingActivity.this)) {
//            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS);
//        }
        //将系统最大屏幕亮度值设为seekbar的最大进度值
        mCarBrightness.setMax(BrightnessUtil.getMaxBrightness(UserSettingActivity.this));
        //将系统当前屏幕亮度值设为seekbar当前进度值
        Log.e("ccm=======>", "setOneProgress: " + BrightnessUtil.getBrightness(UserSettingActivity.this));
        int brightness = BrightnessUtil.getBrightness(UserSettingActivity.this);
        mCarBrightness.setProgress(brightness);
        mCarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.e("ccm=======>", "onProgressChanged: " + progress);
                ModifySettingsScreenBrightness(UserSettingActivity.this, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void initVolume() {
        mCarVolume = findViewById(R.id.sb_audio);

        //获取系统的Audio管理者
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //最大音量
        mediaMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //当前音量
        mediaVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //seekbar设置最大值为最大音量
        mCarVolume.setMax(mediaMaxVolume);
        //seekbar设置当前进度为当前音量
        setView();
        mCarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //设置媒体音量为当前seekbar进度
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                mediaVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                setView();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setView() {
        Log.e("ccm=======>", "mediaVolume: " + mediaVolume);
        mCarVolume.setProgress(mediaVolume);
    }

    /*
     * 事件会在用户按下一个键盘按键时发生
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (--mediaVolume >= 0) {
                    setView();
                } else {
                    mediaVolume = 0;
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (++mediaVolume <= mediaMaxVolume) {
                    setView();
                } else {
                    mediaVolume = mediaMaxVolume;
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_MUTE:
                setView();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void startA() {
        startActivity(new Intent(UserSettingActivity.this, SplashActivity.class));

    }
    /*
    * 预留调用方法 设置背景主题色
    * */
    private void SetBackgroundColor(int type){
        SharedPrefsUtil.putIntValue(AppConfig.BACKGROUND_COLOR, type);//设置主题
        startA();
    }
}