package com.ss.apidemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.ss.api.HardwareCtrl;
import com.ss.api.serialport.SerialPort;
import com.ss.api.utils.StringUtils;
import com.ss.apidemo.utils.DateUtil;

import java.io.File;

import cn.hutool.core.util.HexUtil;

public class Rs485AndRs232Activity extends Activity implements SerialPort.Callback {

//    private static final android.R.attr R = ;
    private SerialPort serialPort;
    private Handler mHandler = new Handler();
    private Button sendBtn;
    private Button send_f;
    private TextView tv_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rs485_and_rs232);

        //485串口：/dev/ttyS7  ;  232串口：/dev/ttyS3
        serialPort = HardwareCtrl.openSerialPortSignal(new File("/dev/ttyS3"), 115200, this);

//        serialPort = HardwareCtrl.openRs485Signal(new File("/dev/ttyS7"), 115200, this);
        EditText et_enter = findViewById(R.id.et_enter);
        tv_response = (TextView) findViewById(R.id.tv_response);

        sendBtn = findViewById(R.id.send_rs485_rs232);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HardwareCtrl.sendSerialPortMsg(serialPort, "48562311");
                //然后通过cat /dev/ttyS4 或者 cat /dev/ttyS3 查看设置的值变化。

                if (serialPort != null){
                    String string = et_enter.getText().toString();
                    HardwareCtrl.sendSerialPortHexMsg(serialPort, "AA5A0A0001000000000000CC33");
                    Log.e("1234567", "result =111 ");

                }else {

                    Log.e("1234567", "result =222 ");
                }

                    HardwareCtrl.sendRs485HexMsg(serialPort, "48562311");
            }
        });
    }

    void refreshLogView(String msg){
        tv_response.append(msg+"\n");
        int offset=tv_response.getLineCount()*tv_response.getLineHeight();
        if(offset>tv_response.getHeight()){
            tv_response.scrollTo(0,offset-tv_response.getHeight());
        }
    }

    //rs485/232发送信号后接收返回值
    @Override
    public void onDataReceived(byte[] bytes, int size) {
        String s = HexUtil.encodeHexStr(bytes).toUpperCase();
//        String result = StringUtils.bytesToHexString(bytes, size);
        Log.e("收到响应", "result = "+s);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                tv_content.setText("响应报文："+s);
                String  s1 = DateUtil.getNowTime()+"::"+s;
                refreshLogView(s1);
            }
        });


    }
}
