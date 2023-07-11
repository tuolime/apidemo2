package com.ss.apidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetDeviceConfig;
import com.example.protocol.utils.ParserUtil;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.widget.NumberKeyboardView;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.HexUtil;

/*
 * 自动脱毛密码界面
 * */
public class AutoShedActivity extends BaseActivity implements NumberKeyboardView.OnNumberClickListener {

    private NumberKeyboardView mNkvKeyboard;
    private TextView mTvText;
    private TextView tv_ps1, tv_ps2, tv_ps3, tv_ps4, tv_ps5, tv_ps6;
    private String str = "";
    private TextView[] tvList;
    private List<String> valueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_shed);
        LinearLayout ll_main = findViewById(R.id.ll_main);
        BackgroundChangeUtils.backgroundChange(this,ll_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });
        tvList = new TextView[6];
        valueList = new ArrayList<>();
//        mTvText = (TextView) findViewById(R.id.am_tv_text);
        tvList[0] = (TextView) findViewById(R.id.tv_ps1);
        tvList[1] = (TextView) findViewById(R.id.tv_ps2);
        tvList[2] = (TextView) findViewById(R.id.tv_ps3);
        tvList[3] = (TextView) findViewById(R.id.tv_ps4);
        tvList[4] = (TextView) findViewById(R.id.tv_ps5);
        tvList[5] = (TextView) findViewById(R.id.tv_ps6);
        mNkvKeyboard = (NumberKeyboardView) findViewById(R.id.am_nkv_keyboard);
        mNkvKeyboard.setOnNumberClickListener(this);
    }

    @Override
    public void onNumberReturn(String number) {
//        if (number.contains("OK")){
//            if (str.equals(AppConfig.password)){
//                Toast.makeText(PassWordActivity.this,"密码正确",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, EngineerSettingActivity.class));
//                return;
//            }
//        }
        if (valueList.size() > 5) {
            return;
        }
        if (number.equals("")){
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
                if (content.equals(AppConfig.AUTOSHEDPS)) {
                    str = "";
                    for (int i = 0; i <valueList.size() ; i++) {
                        tvList[i].setText("");
                    }
                    valueList.clear();
                    startActivity(new Intent(this, ShedSettingActivity.class));
                    return;
                }else {
                    Toast.makeText(AutoShedActivity.this, getResources().getString(R.string.ps_error), Toast.LENGTH_SHORT).show();

                }
            }
        }

//        mTvText.setText(content);
    }
}