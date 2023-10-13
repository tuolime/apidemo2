package com.ss.apidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.ClickUtil;
import com.ss.apidemo.utils.PlayVoiceUtils;

/*
* 脱毛设置页面
* */
public class ShedSettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_10;
    private RelativeLayout rl_20;
    private RelativeLayout rl_30;
    private RelativeLayout rl_40;
    private RelativeLayout rl_50;
    private RelativeLayout rl_60;
    private RelativeLayout rl_all;
    public int current_time = 0;

    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_setting);
        LinearLayout ll_main = findViewById(R.id.ll_main);
        BackgroundChangeUtils.backgroundChange(this,ll_main);
        initView();

    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);

                //退出时 下发报文选择好的温度和水流。。。。
                //退出该页面
                finish();
            }
        });
        rl_10 = findViewById(R.id.rl_10);
        rl_10.setOnClickListener(this);
        rl_20 = findViewById(R.id.rl_20);
        rl_20.setOnClickListener(this);
        rl_30 = findViewById(R.id.rl_30);
        rl_30.setOnClickListener(this);
        rl_40 = findViewById(R.id.rl_40);
        rl_40.setOnClickListener(this);
        rl_50 = findViewById(R.id.rl_50);
        rl_50.setOnClickListener(this);
        rl_60 = findViewById(R.id.rl_60);
        rl_60.setOnClickListener(this);
        rl_all = findViewById(R.id.rl_all);
        rl_all.setOnClickListener(this);

        findViewById(R.id.bt_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                //多次点击直接返回
                if (ClickUtil.isFastClick()) {
                    return;
                }
                if (current_time > 0){
                    if (current_time == AppConfig.INFINITE){
                        AppConfig.AUTOSHEDTIME = AppConfig.INFINITE;
                    }else {
                        int time = current_time * 60;
                        AppConfig.AUTOSHEDTIME = time;
                        MyApplication.instance().setTime(time);
                    }
                    startActivity(new Intent(ShedSettingActivity.this, UserCreateActivity.class));
                }
            }
        });
    }



    @Override
    public void onClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuState();
        switch (view.getId()) {
            case R.id.rl_10:
                rl_10.setBackground(getResources().getDrawable(R.drawable.shed_bt_select_rounded_corners));
                current_time = 10;
                break;
            case R.id.rl_20:
                rl_20.setBackground(getResources().getDrawable(R.drawable.shed_bt_select_rounded_corners));
                current_time = 20;
                break;
            case R.id.rl_30:
                rl_30.setBackground(getResources().getDrawable(R.drawable.shed_bt_select_rounded_corners));
                current_time = 30;
                break;
            case R.id.rl_40:
                rl_40.setBackground(getResources().getDrawable(R.drawable.shed_bt_select_rounded_corners));
                current_time = 40;
                break;
            case R.id.rl_50:
                rl_50.setBackground(getResources().getDrawable(R.drawable.shed_bt_select_rounded_corners));
                current_time = 50;
                break;
            case R.id.rl_60:
                rl_60.setBackground(getResources().getDrawable(R.drawable.shed_bt_select_rounded_corners));
                current_time = 60;
                break;
            case R.id.rl_all:
                rl_all.setBackground(getResources().getDrawable(R.drawable.shed_bt_select_rounded_corners));
                current_time = AppConfig.INFINITE;//AppConfig.INFINITE 代表无穷
                break;
        }
    }
    private void resetMenuState() {
        rl_10.setBackground(getResources().getDrawable(R.drawable.shed_bt_rounded_corners));
        rl_20.setBackground(getResources().getDrawable(R.drawable.shed_bt_rounded_corners));
        rl_30.setBackground(getResources().getDrawable(R.drawable.shed_bt_rounded_corners));
        rl_40.setBackground(getResources().getDrawable(R.drawable.shed_bt_rounded_corners));
        rl_50.setBackground(getResources().getDrawable(R.drawable.shed_bt_rounded_corners));
        rl_60.setBackground(getResources().getDrawable(R.drawable.shed_bt_rounded_corners));
        rl_all.setBackground(getResources().getDrawable(R.drawable.shed_bt_rounded_corners));
    }
}