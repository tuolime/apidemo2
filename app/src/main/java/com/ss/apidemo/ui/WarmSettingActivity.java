package com.ss.apidemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetWarningConfig;
import com.example.protocol.utils.ParserUtil;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;

import cn.hutool.core.lang.Console;

/*
 * 告警  设置温度、水流。。。
 * 温度最大值36度，最小值12度，默认30，步长1 ，水流调节0-3.0L/min，默认是2L/min 步长0.1
 * */
public class WarmSettingActivity extends BaseActivity {

    private TextView tv_temperature;
    private TextView tv_water;
    private int temperature_count = 30;
    private int water_count = 20;
    private double[] water_count_array = {0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,
    1.8,1.9,2.0,2.1,2.2,2.3,2.4,2.5,2.6,2.7,2.8,2.9,3.0};
    private boolean water;
    private boolean temperature;
    private boolean count_clear =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warm_setting);

        initView();
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        }
    }
}