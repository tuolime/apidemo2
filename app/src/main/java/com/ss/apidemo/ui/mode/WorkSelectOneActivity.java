package com.ss.apidemo.ui.mode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.view.arcseekbar.ArcSeekBar;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.ui.SplashActivity;
import com.ss.apidemo.utils.PlayVoiceUtils;

public class WorkSelectOneActivity extends BaseActivity {

    private TextView fan_1,fan_2,fan_3,fan_4,fan_5;
    private ImageView iv_auto,iv_30,iv_100,iv_400,iv_l,iv_m,iv_f;
    private TextView tv_fluence,tv_temprature,tv_flow,tv_total,tv_current,tv_id,tv_exact,tv_raedy;
    private ArcSeekBar arcSeekBar;
    private int fan_flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_select_one);
        initView();
        initData();
    }

    private void initView() {
        fan_1 = findViewById(R.id.fan_1);
        fan_2 = findViewById(R.id.fan_2);
        fan_3 = findViewById(R.id.fan_3);
        fan_4 = findViewById(R.id.fan_4);
        fan_5 = findViewById(R.id.fan_5);
        arcSeekBar = findViewById(R.id.arcSeekBar);
        tv_fluence = findViewById(R.id.tv_fluence);
        iv_auto = findViewById(R.id.iv_auto);
        iv_30 = findViewById(R.id.iv_30);
        iv_100 = findViewById(R.id.iv_100);
        iv_400 = findViewById(R.id.iv_400);

        iv_l = findViewById(R.id.iv_l);
        iv_m = findViewById(R.id.iv_m);
        iv_f = findViewById(R.id.iv_f);

        tv_temprature = findViewById(R.id.tv_temprature);
        tv_flow = findViewById(R.id.tv_flow);
        tv_total = findViewById(R.id.tv_total);
        tv_current = findViewById(R.id.tv_current);
        tv_id = findViewById(R.id.tv_id);
        tv_exact = findViewById(R.id.tv_exact);
        tv_raedy = findViewById(R.id.tv_raedy);
    }
    private void initData() {

    }

    public void tabClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        switch (view.getId()) {
            case R.id.iv_save:
                break;
            case R.id.iv_setting:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_main:
                startActivity(new Intent(WorkSelectOneActivity.this, SplashActivity.class));
                break;
        }
    }

    public void modeClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuModeState();
        switch (view.getId()) {
            case R.id.ll_auto:
                iv_auto.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_30:
                iv_30.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_100:
                iv_100.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_400:
                iv_400.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void resetMenuModeState() {
        iv_auto.setVisibility(View.INVISIBLE);
        iv_30.setVisibility(View.INVISIBLE);
        iv_100.setVisibility(View.INVISIBLE);
        iv_400.setVisibility(View.INVISIBLE);
    }

    public void rangeClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuRangeState();
        switch (view.getId()) {
            case R.id.ll_l:
                iv_l.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_m:
                iv_m.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_f:
                iv_f.setVisibility(View.VISIBLE);
                break;

        }
    }

    private void resetMenuRangeState() {
        iv_l.setVisibility(View.INVISIBLE);
        iv_m.setVisibility(View.INVISIBLE);
        iv_f.setVisibility(View.INVISIBLE);
    }

    public void selectClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuState();
        switch (view.getId()) {
            case R.id.iv_fan:
                fan_flag++;
                if (fan_flag == 1){
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                }if (fan_flag == 2){
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                    fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                }if (fan_flag == 3){
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                    fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_3.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                }if (fan_flag == 4){
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                    fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_3.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_4.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                }if (fan_flag == 5){
                        fan_flag = 0;
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                    fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_3.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_4.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_5.setBackground(getResources().getDrawable(R.drawable.work_one_fan_3_select_corners));
                }
                break;
        }
    }

    private void resetMenuState() {
        fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_unselect_corners));
        fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_unselect_corners));
        fan_3.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_unselect_corners));
        fan_4.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_unselect_corners));
        fan_5.setBackground(getResources().getDrawable(R.drawable.work_one_fan_3_unselect_corners));

    }
}

