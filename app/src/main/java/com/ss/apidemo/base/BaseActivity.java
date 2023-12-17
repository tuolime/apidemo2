package com.ss.apidemo.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ThreadUtils;
import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetWorkingStatus;
import com.example.protocol.frame.data.UploadWorkingInfo;
import com.example.protocol.frame.entity.DataItem;
import com.example.protocol.utils.ParserUtil;
import com.ss.api.HardwareCtrl;
import com.ss.api.serialport.SerialPort;
import com.ss.api.utils.StringUtils;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.bean.EventTipsBean;
import com.ss.apidemo.bean.QueueMessage;
import com.ss.apidemo.bean.SendMessage;
import com.ss.apidemo.bean.SendTimeBean;
import com.ss.apidemo.bean.StopWorkBean;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.ui.AutoShedActivity;
import com.ss.apidemo.ui.SplashActivity;
import com.ss.apidemo.ui.UserCreateActivity;
import com.ss.apidemo.utils.LocaleHelper;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.MyActivityManager;
import com.ss.apidemo.utils.NetworkUtil;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ToastUtil;
import com.ss.apidemo.utils.XactivityCollector;

import java.io.File;
import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.HexUtil;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public abstract class BaseActivity extends AppCompatActivity {


    private int current_count = 0;
    private String response_ctrlCode;
    private HintDialog dialog;
    public String current_ctrlCode = "";
    public String current_message = "";
    public String current_dialog = "";
    public Boolean current_dialog_flag;
    public int current_dialog_count = 0;

    public int current_warm_1_count = 0;//告警满十次 上报服务端一次
    public int current_warm_2_count = 0;//告警满十次 上报服务端一次
    public int current_warm_3_count = 0;//告警满十次 上报服务端一次
    public int current_warm_4_count = 0;//告警满十次 上报服务端一次
    public int current_warm_5_count = 0;//告警满十次 上报服务端一次
    public int current_warm_6_count = 0;//告警满十次 上报服务端一次
    public int current_warm_7_count = 0;//告警满十次 上报服务端一次
    //是否提示下发报文设置成功
    public Boolean isTips = false;
    Handler mHandlerWarm = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (current_dialog_flag) {
                showTipsWarm(current_dialog);
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(SendMessage sendMessage) {
        if (sendMessage != null) {
            analysisMessage(sendMessage.getMessage());
        }

    }

    Handler mHandlerTips = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (isTips) {
                int flag = msg.getData().getInt("flag");
                if (flag == 1) {//发送成功
                    showTips(getResources().getString(R.string.setting_succeeded));
                } else if (flag == 2) {//发送失败且已重发
                    showTips(getResources().getString(R.string.setting_failed));
                }
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(QueueMessage queueMessage) {
        if (queueMessage != null) {
            sendMessageData(queueMessage.getMessage(), queueMessage.getCtrlCode());
            Log.e("下发时间", "reslut" + queueMessage.toString());
        }

    }

    @Override
    protected void onCreate(Bundle arg0) {

        setStatusBarLight(true);
        LocaleHelper.setLocale(this, LocaleHelper.getLanguage(this));
        super.onCreate(arg0);
        XactivityCollector.addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (serialPort != null){
//            serialPort.closeSerialPort();
//        }
//注销EventBus
        EventBus.getDefault().unregister(this);
    }

    protected void setStatusBarLight(boolean light) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // >=5.0 背景为全透明
            /* >=5.0，this method(getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS));
            in some phone is half-transparent like vivo、nexus6p..
            in some phone is full-transparent
            so ...*/
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (light) {
//                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    window.getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                } else {
//                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    window.getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                if (light) {
                    window.setStatusBarColor(Color.BLACK);
                } else {
                    window.setStatusBarColor(Color.TRANSPARENT);
                }
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4背景为渐变半透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public void sendSerialPortHexMsg(String message, String ctrlCode) {
        try {
            MyApplication.instance().startQueue(message, ctrlCode);
        } catch (Exception e) {
            Log.e("下发异常", "e" + e.toString());
        }

    }

    public void sendMessageData(String message, String ctrlCode) {
        current_ctrlCode = ctrlCode;
        current_message = message;
        if (MyApplication.instance().serialPort != null) {
            boolean iswifi = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
            if (iswifi) {
                boolean wifi = NetworkUtil.isWifi();
                if (!wifi) {//没有wifi连接 要告警 且停止报文发送
                    ToastUtil.showToast(this, getResources().getString(R.string.netwrok_no));
                    return;
                } else {
                    HardwareCtrl.sendSerialPortHexMsg(MyApplication.instance().serialPort, message);
                }
            } else {
                HardwareCtrl.sendSerialPortHexMsg(MyApplication.instance().serialPort, message);
            }
        } else {
            ToastUtil.showToast(this, getResources().getString(R.string.port_error));
            return;
        }
    }


    //简析数据
    public void analysisMessage(String result) {

        Log.e("收到上报报文", "result = " + result);
        ProtocalHandler protocalHandler = new ProtocalHandler();
//        Frame resFrame = protocalHandler.recognizeFrame(HexUtil.decodeHex("AA40140001000400050000012E0000000000FD2000CC33"), 0);
        Frame resFrame = protocalHandler.recognizeFrame(HexUtil.decodeHex(result), 0);
        LogUtils.e(resFrame.toString());
        String ctrlCode = resFrame.getCtrlCode();
//        ToastUtil.showToast(MyApplication.instance(),""+ctrlCode);
        if (!ctrlCode.equals("40")) {//非40 是应答报文 处理是否和下发报文响应的一致
            response_ctrlCode = resFrame.getCtrlCode();
            SetCount();
        } else {

            // 40 是上报报文取lst, 55, 5A, 5B是应答报文
            Console.log(resFrame.getCtrlCode());
            UploadWorkingInfo uploadWorkingInfo = (UploadWorkingInfo) resFrame.getDataValue();
            Log.e("解析上报报文", "uploadWorkingInfo = " + uploadWorkingInfo.toString());
            Console.log(uploadWorkingInfo);
            EventBus.getDefault().post(uploadWorkingInfo);
//            responseMessage();
            if (AppConfig.current_count == 0) {
                AppConfig.current_count = uploadWorkingInfo.getToalCount();
            }
//            SetWarm(uploadWorkingInfo);
        }
        AppConfig.isDisconnect ++;//记录下位机响应

    }

    /*
     * 重发机制 报文无响应时 重发三次
     * */
    public void SetCount() {
        if (current_ctrlCode == null) {
            return;
        }
        if (!current_ctrlCode.equals("")) {
            if (current_ctrlCode.equals(response_ctrlCode)) {//说明响应了
                current_count = 0;
                current_ctrlCode = "";
                Message msg = Message.obtain();
                Bundle b = new Bundle();
                b.putInt("flag", 1);
                msg.setData(b);
                mHandlerTips.sendMessage(msg);
            } else {
                current_count++;
                if (current_count < 4) {//重发三次
                    LogUtils.e("重发" + current_count);
                    sendSerialPortHexMsg(current_message, current_ctrlCode);
                } else if (current_count >= 4) {//提示 连接失败
                    LogUtils.e("重发1111");
                    Message msg = Message.obtain();
                    Bundle b = new Bundle();
                    b.putInt("flag", 2);
                    msg.setData(b);
                    mHandlerTips.sendMessage(msg);
                    current_ctrlCode = "";
                    ToastUtil.showToast(BaseActivity.this, getResources().getString(R.string.send_fail));
                }
            }
        }
    }


    public void stopWork() {
        SetWorkingStatus setWorkingStatus = new SetWorkingStatus();
        setWorkingStatus.setTotalEnergy(0);
        setWorkingStatus.setWorkingStatus(0);
        LogUtils.e(setWorkingStatus.toString());
        ProtocalHandler protocalHandler = new ProtocalHandler();
        Frame frame = protocalHandler.buildSetWorkingStatusFrame(setWorkingStatus);
        Console.log(ParserUtil.toHexString(frame.getFrame()));
        String message = ParserUtil.toHexString(frame.getFrame());
        sendSerialPortHexMsg(message, frame.getCtrlCode());
        LogUtils.e("下发数据" + message);
    }
    /*
    * 下发制冷
    * */
    public void sendFan(int fanLevel) {
        SetWorkingStatus setWorkingStatus = new SetWorkingStatus();
        setWorkingStatus.setTotalEnergy(0);
        setWorkingStatus.setWorkingStatus(0);
        setWorkingStatus.setCoolLevel(fanLevel);
        LogUtils.e(setWorkingStatus.toString());
        ProtocalHandler protocalHandler = new ProtocalHandler();
        Frame frame = protocalHandler.buildSetWorkingStatusFrame(setWorkingStatus);
        Console.log(ParserUtil.toHexString(frame.getFrame()));
        String message = ParserUtil.toHexString(frame.getFrame());
        sendSerialPortHexMsg(message, frame.getCtrlCode());
        LogUtils.e("下发数据_制冷" + message);
    }
    //-------------------------收到下位机报文后 应答一下--------------------------

    public void responseMessage() {
        ProtocalHandler protocalHandler1 = new ProtocalHandler();
        Frame frame = protocalHandler1.buildReplyUploadWorkingInfoFrame();
        Console.log(ParserUtil.toHexString(frame.getFrame()));
        String message = ParserUtil.toHexString(frame.getFrame());
        sendSerialPortHexMsg(message, frame.getCtrlCode());
        LogUtils.e("下发应答报文" + message);
    }

    //-------------------------单独发送单脉冲能量--------------------------

    public void SetFluenceMessage(int fluence) {
        int current_handgear = 0;
        if (AppConfig.handgearSelect == 1) {//单手==左手
            current_handgear = SharedPrefsUtil.getIntValue(AppConfig.HAND_LEFT, 1);
        } else if (AppConfig.handgearSelect == 0) {//双手 =  左右手
            current_handgear = SharedPrefsUtil.getIntValue(AppConfig.HAND_RIGHT, 1);
        }
        //停止状态  工作状态给 0  总能量 也能 0
        ProtocalHandler protocalHandler = new ProtocalHandler();
        SetWorkingStatus setWorkingStatus = new SetWorkingStatus();
        setWorkingStatus.setWorkingModel(0); // 工作模式  1 2 3 4 5 shr 6 hr
        setWorkingStatus.setFluence(fluence); // 单脉冲能量
        setWorkingStatus.setFrequency(0); // 频率
        setWorkingStatus.setTotalEnergy(0);// 总能量
        setWorkingStatus.setWorkingTime(0); // 工作时间  秒
        setWorkingStatus.setQbConfig(current_handgear); // 手具选择 1-7
        setWorkingStatus.setChangeQbPortFlag(AppConfig.handgearSelect); // 是否切换手具端口 1 left 0 right
        setWorkingStatus.setWorkingStatus(0);// 工作状态 0  stby和停止  1 reading 2 working
        Frame frame = protocalHandler.buildSetWorkingStatusFrame(setWorkingStatus);
        Console.log(ParserUtil.toHexString(frame.getFrame()));
        String message = ParserUtil.toHexString(frame.getFrame());
        sendSerialPortHexMsg(message, frame.getCtrlCode());
        LogUtils.e("下发单脉冲能量" + message);
    }

    private void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        XactivityCollector.removeActivity(this);

        stopWork();
    }

    public void SetWarm(UploadWorkingInfo uploadWorkingInfo) {
        current_dialog_flag = false;
        current_dialog = "";
        if (uploadWorkingInfo.getShortCircuited() == 1) {//短板短路
            current_dialog = getResources().getString(R.string.short_board) + "\n";
            current_dialog_flag = true;
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.WARM);
            current_warm_1_count++;
            if (current_warm_1_count == 10){
                MyApplication.instance().sendWarmMessage(1,getResources().getString(R.string.short_board));
                current_warm_1_count = 0;
            }
        }
        if (uploadWorkingInfo.getQbConnFail() == 1) {//手具连接失败
            current_dialog = current_dialog + getResources().getString(R.string.hand_error) + "\n";
            current_dialog_flag = true;
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.WARM);
            current_warm_2_count++;
            if (current_warm_2_count == 10){
                MyApplication.instance().sendWarmMessage(2,getResources().getString(R.string.hand_error));
                current_warm_2_count = 0;
            }
        }
        if (uploadWorkingInfo.getTemperatureHi() == 1) {//水温高
            current_dialog = current_dialog + getResources().getString(R.string.temperature_high) + "\n";
            current_dialog_flag = true;
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.WARM);
            current_warm_3_count++;
            if (current_warm_3_count == 10){
                MyApplication.instance().sendWarmMessage(3,getResources().getString(R.string.temperature_high));
                current_warm_3_count = 0;
            }
        }
        if (uploadWorkingInfo.getTemperatureLow() == 1) {//水温低
            current_dialog = current_dialog + getResources().getString(R.string.temperature_low) + "\n";
            current_dialog_flag = true;
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.WARM);
            current_warm_4_count++;
            if (current_warm_4_count == 10){
                MyApplication.instance().sendWarmMessage(4,getResources().getString(R.string.temperature_low));
                current_warm_4_count = 0;
            }
        }
        if (uploadWorkingInfo.getFlowVelocityLow() == 1) {//水流低
            current_dialog = current_dialog + getResources().getString(R.string.water_low) + "\n";
            current_dialog_flag = true;
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.WARM);
            current_warm_5_count++;
            if (current_warm_5_count == 10){
                MyApplication.instance().sendWarmMessage(5,getResources().getString(R.string.water_low));
                current_warm_5_count = 0;
            }
        }
        if (uploadWorkingInfo.getNoFlowVelocity() == 1) {//无水流
            current_dialog = current_dialog + getResources().getString(R.string.water_no) + "\n";
            current_dialog_flag = true;
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.WARM);
            current_warm_6_count++;
            if (current_warm_6_count == 10){
                MyApplication.instance().sendWarmMessage(6,getResources().getString(R.string.water_no));
                current_warm_6_count = 0;
            }
        }

        if (uploadWorkingInfo.getCheckFilter() == 1) {//更换过滤器装置
            current_dialog = current_dialog + getResources().getString(R.string.replace_filter) + "\n";
            current_dialog_flag = true;
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.WARM);
            current_warm_7_count++;
            if (current_warm_7_count == 10){
                MyApplication.instance().sendWarmMessage(7,getResources().getString(R.string.replace_filter));
                current_warm_7_count = 0;
            }
        }

        if (current_dialog_flag) {
            current_dialog_count++;
            if (current_dialog_count == 1) {
                showTipsWarm(current_dialog);
            }
        }

    }

    public void showTips(String hint) {
        if (dialog != null) {
            dialog.closeDialog();
        } else {
            dialog = new HintDialog(BaseActivity.this);
            dialog.loadDialog(BaseActivity.this, new HintDialog.OnClickIsConfirm() {
                @Override
                public void OnClickIsConfirmListener() {//确定

                }

            }, hint);
        }
    }

    public void showTipsWarm(String hint) {
        HintDialog dialogWarm = new HintDialog(BaseActivity.this);
        dialogWarm.loadDialog(BaseActivity.this, new HintDialog.OnClickIsConfirm() {
            @Override
            public void OnClickIsConfirmListener() {//确定
                stopWork();//有告警 停止工作
                mHandlerWarm.sendMessageDelayed(new Message(), 10000);
            }

        }, hint);
    }

    public boolean getAutoShed() {
        boolean isAutoShed = SharedPrefsUtil.getBooleanValue(AppConfig.SHED, false);
        LogUtils.e("获取到的时间"+AppConfig.AUTOSHEDTIME);
        if (isAutoShed && AppConfig.AUTOSHEDTIME > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(StopWorkBean stopWorkBean) {//脱毛倒计时结束
        stopWork();
    }

    /*
    * 获取是否设置过最大值
    * */
    public int getEnergyUpper(){
        int current_energyUpper = SharedPrefsUtil.getIntValue(AppConfig.ENERGYUPPER, 0);
        if (current_energyUpper == 0){
            return 0;
        }else {
            return current_energyUpper;
        }

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(EventTipsBean eventTipsBean) {//Myapplication里不能使用文字资源 否则多语言不翻译
        if(eventTipsBean != null){
            switch (eventTipsBean.getType()){
                case 1:
                    showMyapplicationTips(getResources().getString(R.string.disconnect_tips));
                    break;
                case 2:
                    showMyapplicationTips(getResources().getString(R.string.no_network));
                    break;
                case 3:
                    showMyapplicationTips(getResources().getString(R.string.network_connection_exception));
                    break;
                case 4:
                    showLockTips(getResources().getString(R.string.lock_tips));
                    break;
                case 5:
                    showEndTimeTips(getResources().getString(R.string.ends_time));
                    break;
            }
        }

    }

    public void showMyapplicationTips(String hint) {
        if (dialog != null) {
            dialog.closeDialog();
            dialog.loadDialog(BaseActivity.this, new HintDialog.OnClickIsConfirm() {
                @Override
                public void OnClickIsConfirmListener() {//确定
                    if (hint.equals(getResources().getString(R.string.no_network))) {
                        MyApplication.instance().startSplashActivity();
                    }
                    if (hint.equals(getResources().getString(R.string.network_connection_exception))) {
                        MyApplication.instance().startSplashActivity();
                    }
                }

            }, hint);
        } else {
            dialog = new HintDialog(BaseActivity.this);
            dialog.loadDialog(BaseActivity.this, new HintDialog.OnClickIsConfirm() {
                @Override
                public void OnClickIsConfirmListener() {//确定
                    if (hint.equals(getResources().getString(R.string.no_network))) {
                        MyApplication.instance().startSplashActivity();
                    }
                    if (hint.equals(getResources().getString(R.string.network_connection_exception))) {
                        MyApplication.instance().startSplashActivity();
                    }
                }

            }, hint);
        }
    }
    public void showLockTips(String hint) {
        MyApplication.instance().lock_count ++;
        if (MyApplication.instance().lock_count == 1){
            HintDialog dialogTips = new HintDialog(BaseActivity.this);
            dialogTips.loadDialog(BaseActivity.this, new HintDialog.OnClickIsConfirm() {
                @Override
                public void OnClickIsConfirmListener() {//确定
                    MyApplication.instance().lock_count = 0;
                    MyApplication.instance().startSplashActivity();
                }

            }, hint);
        }

    }
    public void showEndTimeTips(String hint) {
        HintDialog dialog = new HintDialog(BaseActivity.this);
        dialog.loadDialog(BaseActivity.this, new HintDialog.OnClickIsConfirm() {
            @Override
            public void OnClickIsConfirmListener() {//确定
                MyApplication.instance().startSplashActivity();
//                ToastUtil.showToast(MyApplication.instance(),getResources().getString(R.string.ends_time));
            }

        }, hint);

    }
}

