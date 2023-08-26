package com.ss.apidemo;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ThreadUtils;
import com.example.protocol.frame.ProtocalHandler;
import com.google.gson.Gson;
import com.ss.api.HardwareCtrl;
import com.ss.api.serialport.SerialPort;
import com.ss.apidemo.bean.Frame;
import com.ss.apidemo.bean.QueueMessage;
import com.ss.apidemo.bean.SendMessage;
import com.ss.apidemo.bean.SendTimeBean;
import com.ss.apidemo.bean.StopWorkBean;
import com.ss.apidemo.bean.WarmBean;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.socket.EMConnectionManager;
import com.ss.apidemo.ui.SplashActivity;
import com.ss.apidemo.utils.ChjTimer;
import com.ss.apidemo.utils.DeviceInfoUtil;
import com.ss.apidemo.utils.LocaleHelper;
import com.ss.apidemo.utils.LocationUtils;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.MyActivityManager;
import com.ss.apidemo.utils.NetworkUtil;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import androidx.annotation.NonNull;
import cn.hutool.core.util.HexUtil;
import de.greenrobot.event.EventBus;

public class MyApplication extends Application implements ChjTimer.ChjTimerInter, SerialPort.Callback {
    private EMConnectionManager mConnection;

    private static MyApplication instance;
    private ChjTimer chjTimer;
    private HintDialog dialog;
    private Queue<QueueMessage> loopDatas = new ConcurrentLinkedQueue<>();//队列;
    private Queue<SendMessage> sendloopDatas = new ConcurrentLinkedQueue<>();//队列;
    public SerialPort serialPort;

    private String address_message;
    private int disconnection_count = 0;//断连计数
    private int lock_count = 0;//锁定计数
    private boolean isScheduledTasks = false;//是否开启定时心跳任务
    private int response_count = 0;//应答次数


    private Myhandler myhandler = new Myhandler();

//    private SocketConnectorThread connectorThread;

    private Handler handler = new Handler();

    private Runnable mDisconnectTips = new Runnable() {
        public void run() {
            //需要执行的代码
            MyActivityManager.getInstance().getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (AppConfig.isDisconnect == 0) {
                        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.WARM);
                        showTips(getResources().getString(R.string.disconnect_tips));
                    }
                    // TODOAuto-generated method stub
                    handler.postDelayed(this, 20000);//延迟20秒调用
                    AppConfig.isDisconnect = 0;
                }
            });

        }
    };

    private Runnable mDisconnectSocketTips = new Runnable() {
        public void run() {
            boolean iswifi = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
            if (!iswifi) return;
            boolean wifi = NetworkUtil.isWifi();
            if (wifi) {
                Log.d("xuan", "应答次数"+response_count);
                if (response_count == 0){
                    disconnectionCount();
                }else {
                    response_count = 0;
                }
                handler.postDelayed(this, 3 * 10000);//设置延迟时间，此处是30s
                //需要执行的代码
            } else {//无网络
                showTips(getResources().getString(R.string.no_network));
            }

        }
    };

    private Runnable sendSocket = new Runnable() {
        public void run() {
            boolean iswifi = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
            if (!iswifi) return;
            boolean wifi = NetworkUtil.isWifi();
            if (wifi) {
                startSendQueue(sendHeartMessage());
                Log.d("xuan", "handleMessage: 执行");
                handler.postDelayed(this, 5 * 1000);//设置延迟时间，此处是10s
                isScheduledTasks = true;
                //需要执行的代码
            } else {//无网络
                showTips(getResources().getString(R.string.no_network));
            }

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLanguage();
        initTimer();
        openSerialPort();
        startLoopListener();
        startSendLoopListener();
        getAppBackground();
        SetDisconnectTips();
        //待测试 excel导出
//        String[] title = {"母本名称", "母本小区", "父本名称", "父本小区", "母本珠号", "父本珠号", "授粉数量", "单位", "时间"};
//        String fileName = "test_" + System.currentTimeMillis() + ".xls";
//        ExcelUtil.initExcel(fileName, "授粉记录", title);
//        ExcelUtil.writeObjListToExcel(recordList, fileName, this);
    }

    /**
     * 连接Socket
     */
    public void connetSocket() {
//        initLocation();
        mConnection = new EMConnectionManager(this);
        mConnection.setMainHandler(myhandler);
        if (!isScheduledTasks) {
            handler.postDelayed(sendSocket, 3000);//延迟调用 3秒后开始执行
//            handler.postDelayed(mDisconnectSocketTips, 3 * 10000);//延迟30秒调用

        }

    }

    /**
     * 加载位置
     */
    private void initLocation() {
        LocationUtils.getInstance(this).setAddressCallback(new LocationUtils.AddressCallback() {
            @Override
            public void onGetAddress(Address address) {
                String countryName = address.getCountryName();//国家
                String adminArea = address.getAdminArea();//省
                String locality = address.getLocality();//市
                String subLocality = address.getSubLocality();//区
                String featureName = address.getFeatureName();//街道
                Log.d("定位地址", "" + countryName + "," + adminArea + "," + locality + "," + subLocality + "," + featureName);
                address_message = countryName + "," + adminArea + "," + locality + "," + subLocality + "," + featureName;

            }

            @Override
            public void onGetLocation(double lat, double lng, double altitude) {
                LogUtils.d("定位经纬度", "" + lat + "," + lng);
            }
        });
        LogUtils.d("定位经纬度", "");
    }

    /*
     * 发送心跳信息Socket
     * */
    public String sendHeartMessage() {
        if (mConnection != null) {
            Frame frame = new Frame();
            frame.setType(0);//心跳
            frame.setTerminalAddress(address_message);
            String deviceId = DeviceInfoUtil.getMac();
            frame.setTerminalCode(deviceId);
//            frame.setTerminalCode("d592235ec3060edb");
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(frame);
            String jsonString = jsonObject.toJSONString();

            return jsonString;
        }
        return null;
    }

    /*
     * 发送次数信息Socket
     * */
    public void sendCountMessage(int count) {
        boolean iswifi = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
        if (!iswifi) return;
        boolean wifi = NetworkUtil.isWifi();
        if (wifi) {
            if (mConnection != null) {
                Frame frame = new Frame();
                frame.setType(3);//次数上报
                frame.setTerminalAddress(address_message);
                String deviceId = DeviceInfoUtil.getMac();
                frame.setTerminalCode(deviceId);
                frame.setReportUseNum(count);
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(frame);
                String jsonString = jsonObject.toJSONString();
                startSendQueue(jsonString);
//                mConnection.sendMessage(jsonString);
            }
        } else {
            showTips(getResources().getString(R.string.no_network));
            startSplashActivity();
        }

    }
    /*
     * 发送用户Socket
     * */
    public void sendUserMessage(User user) {
        boolean iswifi = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
        if (!iswifi) return;
        boolean wifi = NetworkUtil.isWifi();
        if (wifi) {
            if (mConnection != null) {
                Frame frame = new Frame();
                frame.setType(4);//上报用户
                String deviceId = DeviceInfoUtil.getMac();
                frame.setTerminalCode(deviceId);
                JSONObject userObject = (JSONObject) JSONObject.toJSON(user);
                String userString = userObject.toJSONString();
                frame.setData(userString);
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(frame);
                String jsonString = jsonObject.toJSONString();
                startSendQueue(jsonString);
                Log.e("上报用户信息", "上报用户信息" + jsonString);
//                mConnection.sendMessage(jsonString);
            }
        } else {
            showTips(getResources().getString(R.string.no_network));
            startSplashActivity();
        }
    }
    /*
     * 发送用户治疗信息Socket
     * */
    public void sendUserValueMessage(UserValue value) {
        boolean iswifi = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
        if (!iswifi) return;
        boolean wifi = NetworkUtil.isWifi();
        if (wifi) {
            if (mConnection != null) {
                Frame frame = new Frame();
                frame.setType(5);//上报用户治疗信息
                String deviceId = DeviceInfoUtil.getMac();
                frame.setTerminalCode(deviceId);
                JSONObject userValueObject = (JSONObject) JSONObject.toJSON(value);
                String userValueString = userValueObject.toJSONString();
                frame.setData(userValueString);
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(frame);
                String jsonString = jsonObject.toJSONString();
                startSendQueue(jsonString);
                Log.e("上报用户治疗信息", "上报用户治疗信息" + jsonString);
//                mConnection.sendMessage(jsonString);
            }
        } else {
            showTips(getResources().getString(R.string.no_network));
            startSplashActivity();
        }
    }

    /*
     * 发送告警信息Socket
     * */
    public void sendWarmMessage(int warmType,String warmMsg) {
        boolean iswifi = SharedPrefsUtil.getBooleanValue(AppConfig.WIFI, false);
        if (!iswifi) return;
        boolean wifi = NetworkUtil.isWifi();
        if (wifi) {
            if (mConnection != null) {
                Frame frame = new Frame();
                frame.setType(6);//上报设备告警
                String deviceId = DeviceInfoUtil.getMac();
                frame.setTerminalCode(deviceId);
                WarmBean warmBean = new WarmBean();
                warmBean.setWarmType(warmType);
                warmBean.setWarmMsg(warmMsg);
                JSONObject warmObject = (JSONObject) JSONObject.toJSON(warmBean);
                String warmString = warmObject.toJSONString();
                frame.setData(warmString);
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(frame);
                String jsonString = jsonObject.toJSONString();
                startSendQueue(jsonString);
                Log.e("上报设备告警", "上报设备告警" + jsonString);
//                mConnection.sendMessage(jsonString);
            }
        } else {
            showTips(getResources().getString(R.string.no_network));
            startSplashActivity();
        }
    }

    /*
     * 下位机不响应的话 上位机做一个连接断开的提示
     * */
    private void SetDisconnectTips() {
        handler.postDelayed(mDisconnectTips, 10000);//延迟10秒调用
    }

    public void openSerialPort() {
        if (serialPort == null) {
            //485串口：/dev/ttyS7  ;  232串口：/dev/ttyS3
            serialPort = HardwareCtrl.openSerialPortSignal(new File("/dev/ttyS3"), 115200, this);
        }
    }

    //rs485/232发送信号后接收返回值
    @Override
    public void onDataReceived(byte[] bytes, int size) {
//        String result = HexUtil.encodeHexStr(bytes).toUpperCase();
        byte[] recognize = ProtocalHandler.getInstance().recognize(bytes);
        Log.e("收到报文字节", "recognize = " + recognize);
        String result = null;
        if (recognize == null) {
            return;
        }
        result = HexUtil.encodeHexStr(recognize).toUpperCase();
        Log.e("收到报文十六进制", "result = " + result);
        if (result == null) {
            return;
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setMessage(result);
        EventBus.getDefault().post(sendMessage);
    }

    public void startQueue(String message, String ctrlCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    QueueMessage queueMessage = new QueueMessage();
                    queueMessage.setMessage(message);
                    queueMessage.setCtrlCode(ctrlCode);
                    loopDatas.offer(queueMessage);
//                    LogUtils.e("入队列"+ctrlCode);
                } catch (Exception e) {
                    Log.e("下发异常", "e" + e.toString());
                }
            }
        }).start();
    }

    public void startSendQueue(String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setMessage(message);
                    sendloopDatas.offer(sendMessage);
//                    LogUtils.e("入队列"+ctrlCode);
                } catch (Exception e) {
                    Log.e("下发异常", "e" + e.toString());
                }
            }
        }).start();
    }

    public void startLoopListener() {
        ThreadUtils.getIoPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (loopDatas.size() > 0) {
                            QueueMessage value = loopDatas.poll();
                            if (value != null) {
                                Thread.sleep(300);//队列执行间隔
                                LogUtils.e("出队列" + value.getCtrlCode());
                                EventBus.getDefault().post(value);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startSendLoopListener() {
        ThreadUtils.getIoPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (sendloopDatas.size() > 0) {
                            SendMessage value = sendloopDatas.poll();
                            if (value != null) {
                                Thread.sleep(500);//队列执行间隔
                                LogUtils.e("出队列" + value.getMessage());
                                //需要执行的代码
                                MyActivityManager.getInstance().getCurrentActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (mConnection != null) {
                                            mConnection.sendMessage(value.getMessage());
                                        }
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initTimer() {
        chjTimer = new ChjTimer(this);
    }

    public static MyApplication instance() {

        return instance;
    }

    private void initLanguage() {
        // 设置语言
//        LocaleHelper.onAttach(this, "en");
        // 应用程序里设置的语言，否则程序杀死后重启又会是系统语言，
        LocaleHelper.setLocale(this, LocaleHelper.getLanguage(this));
//        LocaleHelper.setLocale(this, "en");
        // 当前语言
        String language = LocaleHelper.getLanguage(this);

        LogUtils.e("zq", "当前语言:" + language);

    }

    public void setTime(int time) {
        chjTimer.start(time);
    }

    @Override
    public void second(int time) {
        EventBus.getDefault().post(new SendTimeBean(time));
    }

    @Override
    public void expire() {
        AppConfig.AUTOSHEDTIME = 0;
        EventBus.getDefault().post(new StopWorkBean());
        HintDialog dialog = new HintDialog(MyActivityManager.getInstance().getCurrentActivity());
        dialog.loadDialog(MyActivityManager.getInstance().getCurrentActivity(), new HintDialog.OnClickIsConfirm() {
            @Override
            public void OnClickIsConfirmListener() {//确定
                startSplashActivity();
//                ToastUtil.showToast(MyApplication.instance(),getResources().getString(R.string.ends_time));
            }

        }, getResources().getString(R.string.ends_time));

    }

    public void startSplashActivity() {
        destroyTask();
        EventBus.getDefault().post(new StopWorkBean());
        Intent intent = new Intent(MyApplication.this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void stop(int time) {

    }


    private void getAppBackground() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    class Myhandler extends Handler {
        private byte[] bytes;

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:  //socket连接失败
                    Log.d("xuan", "handleMessage: " + "连接失败");
//                    ToastUtil.showToast(MyApplication.instance(), "连接失败");
                    disconnectionCount();
                    break;
                case 1:  //socket连接成功
                    Log.d("xuan", "handleMessage: " + "连接成功");
//                    ToastUtil.showToast(MyApplication.instance(), "连接成功");
//                    handler.postDelayed(sendSocket, 3000);//延迟调用 3秒后开始执行
                    break;
                case 2:
                    response_count ++;
                    //数据处理 自己业务逻辑
                    bytes = (byte[]) msg.obj;
                    String str = new String(bytes);
//                    String s = "";
//                    for (int i = 0; i < bytes.length; i++) {
//                        s = s + "" + bytes[i];
//                    }
                    Log.d("xuan", "收到数据:" + str);

                    LogUtils.d("收到数据", str);
//                    String str = "{\"type\":2,\"lockStatus\":\"unlock\",\"statt\":\"哈哈\"}{\"useLimitedType\":\"count\",\"type\":1,\"lockStatus\":\"unlock\",\"useNum\":0,\"terminalCode\":\"d592235ec3060edb\",\"totalUseNum\":10,\"useLimitedFlag\":\"1\",\"reportUseNum\":0}";
                    //假如 心跳应答和服务器下发一起回来，就取第一个解析
                    String strObject = str.substring(str.indexOf("{"), str.indexOf("}") + 1);
                    Frame frame = new Gson().fromJson(strObject, Frame.class);
                    if (frame != null) {
                        if (frame.getType() == 1) {//应答心跳
                            if (frame.getLockStatus().equals("lock")) { //锁定状态，0否1是
                                AppConfig.lockStatus = 1;
                                EventBus.getDefault().post(new StopWorkBean());
                                showLockTips(getResources().getString(R.string.lock_tips));
                            } else {
                                AppConfig.lockStatus = 0;
                            }
                            if (frame.getUseLimitedFlag().equals("1")) {//useLimitedFlag使用限制标识，0否1是
                                AppConfig.useLimitedFlag = 1;
                                //day/hour/count
                                if (frame.getUseLimitedType().equals("day")) {
                                    AppConfig.useLimitedType = "day";
                                    AppConfig.remainNum = frame.getRemainNum();
                                } else if (frame.getUseLimitedType().equals("hour")) {
                                    AppConfig.useLimitedType = "hour";
                                    AppConfig.remainNum = frame.getRemainNum();
                                } else if (frame.getUseLimitedType().equals("count")) {
                                    AppConfig.useLimitedType = "count";
                                    AppConfig.remainNum = frame.getRemainNum();
                                } else {
                                    AppConfig.useLimitedType = "";
                                    AppConfig.remainNum = 0;
                                }
                            } else {
                                AppConfig.useLimitedFlag = 0;
                            }
                        }else if (frame.getType() == 2) {//服务器下发
                            if (frame.getLockStatus().equals("lock")) { //锁定状态，0否1是
                                AppConfig.lockStatus = 1;
                                EventBus.getDefault().post(new StopWorkBean());
                                showLockTips(getResources().getString(R.string.lock_tips));
                            } else {
                                AppConfig.lockStatus = 0;
                            }
                        }
                    }
                    break;
                case 3: //接收消息断开标志
                    Log.d("xuan", "handleMessage: " + "接收消息断开");
                    disconnectionCount();
                    break;
                case 4: //发送成功
                    Log.d("xuan", "handleMessage: " + "发送成功");
                    break;
                case 5: //发送消息断开标志
                    Log.d("xuan", "handleMessage: " + "发送消息断开");
                    disconnectionCount();
                    break;
            }
        }
    }

    public void disconnectionCount() {
//        disconnection_count++;//socket连接服务器失败(三次每次一分钟后发起连接均失败) 会走该方法
//        if (disconnection_count >= 100){//累计100次提醒一下
//            disconnection_count = 0;
        showTips(getResources().getString(R.string.network_connection_exception));
//        }
    }


    public void showTips(String hint) {
        if (dialog != null) {
            dialog.closeDialog();
            dialog.loadDialog(MyActivityManager.getInstance().getCurrentActivity(), new HintDialog.OnClickIsConfirm() {
                @Override
                public void OnClickIsConfirmListener() {//确定
                    if (hint.equals(getResources().getString(R.string.no_network))) {
                        startSplashActivity();
                    }
                    if (hint.equals(getResources().getString(R.string.network_connection_exception))) {
                        startSplashActivity();
                    }
                }

            }, hint);
        } else {
            dialog = new HintDialog(MyActivityManager.getInstance().getCurrentActivity());
            dialog.loadDialog(MyActivityManager.getInstance().getCurrentActivity(), new HintDialog.OnClickIsConfirm() {
                @Override
                public void OnClickIsConfirmListener() {//确定
                    if (hint.equals(getResources().getString(R.string.no_network))) {
                        startSplashActivity();
                    }
                    if (hint.equals(getResources().getString(R.string.network_connection_exception))) {
                        startSplashActivity();
                    }
                }

            }, hint);
        }
    }

    public void showLockTips(String hint) {
        lock_count ++;
        if (lock_count == 1){
            HintDialog dialogTips = new HintDialog(MyActivityManager.getInstance().getCurrentActivity());
            dialogTips.loadDialog(MyActivityManager.getInstance().getCurrentActivity(), new HintDialog.OnClickIsConfirm() {
                @Override
                public void OnClickIsConfirmListener() {//确定
                    lock_count = 0;
                    startSplashActivity();
                }

            }, hint);
        }

    }

    /*
     * 销毁线程
     * */
    public void destroyTask() {
        handler.removeCallbacks(sendSocket);
        isScheduledTasks = false;
        handler.removeCallbacks(mDisconnectSocketTips);
        response_count = 0;
    }
}
