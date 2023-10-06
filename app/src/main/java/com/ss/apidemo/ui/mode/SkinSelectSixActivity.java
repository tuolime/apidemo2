package com.ss.apidemo.ui.mode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.ui.SplashActivity;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.ToastUtil;

public class SkinSelectSixActivity extends BaseActivity {
    private TextView tv_skin_1,tv_skin_2,tv_skin_3,tv_skin_4,tv_skin_5,tv_skin_6;
    private ImageView iv_body_head,iv_body_leg,iv_body_armpit,iv_body_waist,iv_body_back,iv_body_bikini;
    private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6;
    private int skin_type = 0;
    private int body_type = 0;
    public String tel;//传递过来的用户手机号
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_select_six);
        Intent intent = getIntent();
        if (intent != null) {
            tel = intent.getStringExtra("tel");
            gender = intent.getStringExtra("gender");
        }
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        skin_type = 0;
        body_type = 0;
        resetMenuState();
        resetMenuBoydState();
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                //退出该页面
                finish();
            }
        });
        ImageView iv_main = findViewById(R.id.iv_main);
        iv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                startActivity(new Intent(SkinSelectSixActivity.this, SplashActivity.class));
            }
        });
        tv_skin_1 = findViewById(R.id.tv_skin_1);
        tv_skin_2 = findViewById(R.id.tv_skin_2);
        tv_skin_3 = findViewById(R.id.tv_skin_3);
        tv_skin_4 = findViewById(R.id.tv_skin_4);
        tv_skin_5 = findViewById(R.id.tv_skin_5);
        tv_skin_6 = findViewById(R.id.tv_skin_6);

        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);
        tv_5 = findViewById(R.id.tv_5);
        tv_6 = findViewById(R.id.tv_6);

        iv_body_head = findViewById(R.id.iv_body_head);
        iv_body_leg = findViewById(R.id.iv_body_leg);
        iv_body_armpit = findViewById(R.id.iv_body_armpit);
        iv_body_waist = findViewById(R.id.iv_body_waist);
        iv_body_back = findViewById(R.id.iv_body_back);
        iv_body_bikini = findViewById(R.id.iv_body_bikini);


        //暂时先不要默认选中
//        ll_rounded1.setBackground(getResources().getDrawable(R.drawable.handgear_select_corners));
//        tv_select1.setBackground(getResources().getDrawable(R.drawable.handgear_bt_select_corners));
//        tv_content1.setTextColor(getResources().getColor(R.color.handgear_select));
    }

    public void tabClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuState();
        switch (view.getId()) {
            case R.id.tv_skin_1:
                tv_skin_1.setBackground(getResources().getDrawable(R.drawable.mode_six_skin1_select_corners));
                tv_1.setTextColor(getResources().getColor(R.color.mode_four_bt_select));
                skin_type = 1;
                break;
            case R.id.tv_skin_2:
                tv_skin_2.setBackground(getResources().getDrawable(R.drawable.mode_six_skin2_select_corners));
                tv_2.setTextColor(getResources().getColor(R.color.mode_four_bt_select));
                skin_type = 2;
                break;
            case R.id.tv_skin_3:
                tv_skin_3.setBackground(getResources().getDrawable(R.drawable.mode_six_skin3_select_corners));
                tv_3.setTextColor(getResources().getColor(R.color.mode_four_bt_select));
                skin_type = 3;
                break;
            case R.id.tv_skin_4:
                tv_skin_4.setBackground(getResources().getDrawable(R.drawable.mode_six_skin4_select_corners));
                tv_4.setTextColor(getResources().getColor(R.color.mode_four_bt_select));
                skin_type = 4;
                break;
            case R.id.tv_skin_5:
                tv_skin_5.setBackground(getResources().getDrawable(R.drawable.mode_six_skin5_select_corners));
                tv_5.setTextColor(getResources().getColor(R.color.mode_four_bt_select));
                skin_type = 5;
                break;
            case R.id.tv_skin_6:
                tv_skin_6.setBackground(getResources().getDrawable(R.drawable.mode_six_skin6_select_corners));
                tv_6.setTextColor(getResources().getColor(R.color.mode_four_bt_select));
                skin_type = 6;
                break;
        }
    }
    public void selectClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        switch (view.getId()) {
            case R.id.tv_skin_ok:
                if (skin_type == 0){
                    ToastUtil.showToast(SkinSelectSixActivity.this, getResources().getString(R.string.select_skin));
                    return;
                }
                if (body_type == 0){
                    ToastUtil.showToast(SkinSelectSixActivity.this, getResources().getString(R.string.select_body));
                    return;
                }
                startA();
                break;
            case R.id.tv_skin_cancel:
                finish();
                break;
        }
    }
    public void bodyClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuBoydState();
        switch (view.getId()) {
            case R.id.iv_body_head:
                iv_body_head.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_head_select));
                body_type = 1;
                break;
            case R.id.iv_body_leg:
                iv_body_leg.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_leg_select));
                body_type = 2;
                break;
            case R.id.iv_body_armpit:
                iv_body_armpit.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_armpit_select));
                body_type = 3;
                break;
            case R.id.iv_body_waist:
                iv_body_waist.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_waist_select));
                body_type = 4;
                break;
            case R.id.iv_body_back:
                iv_body_back.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_back_select));
                body_type = 5;
                break;
            case R.id.iv_body_bikini:
                iv_body_bikini.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_bikini_select));
                body_type = 6;
                break;
        }
    }

    private void resetMenuState() {
        tv_skin_1.setBackground(getResources().getDrawable(R.drawable.mode_three_skin1_unselect_corners));
        tv_skin_2.setBackground(getResources().getDrawable(R.drawable.mode_three_skin2_unselect_corners));
        tv_skin_3.setBackground(getResources().getDrawable(R.drawable.mode_three_skin3_unselect_corners));
        tv_skin_4.setBackground(getResources().getDrawable(R.drawable.mode_three_skin4_unselect_corners));
        tv_skin_5.setBackground(getResources().getDrawable(R.drawable.mode_three_skin5_unselect_corners));
        tv_skin_6.setBackground(getResources().getDrawable(R.drawable.mode_three_skin6_unselect_corners));
        tv_1.setTextColor(getResources().getColor(R.color.mode_three_bg));
        tv_2.setTextColor(getResources().getColor(R.color.mode_three_bg));
        tv_3.setTextColor(getResources().getColor(R.color.mode_three_bg));
        tv_4.setTextColor(getResources().getColor(R.color.mode_three_bg));
        tv_5.setTextColor(getResources().getColor(R.color.mode_three_bg));
        tv_6.setTextColor(getResources().getColor(R.color.mode_three_bg));
    }

    private void resetMenuBoydState() {
        iv_body_head.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_head));
        iv_body_leg.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_leg));
        iv_body_armpit.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_armpit));
        iv_body_waist.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_waist));
        iv_body_back.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_back));
        iv_body_bikini.setImageDrawable(getResources().getDrawable(R.mipmap.ic_mode_six_body_bikini));

    }

    public void startA() {
        Intent intent = new Intent(SkinSelectSixActivity.this, WorkSelectSixActivity.class);
        intent.putExtra("mode_type", 2);//1 专家  2 智能
        intent.putExtra("skin_type", skin_type);
        intent.putExtra("body_type", body_type);
        intent.putExtra("tel", tel);
        gender = intent.getStringExtra("gender");
        startActivity(intent);
    }
}