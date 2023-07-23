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

public class SkinSelectTwoActivity extends BaseActivity {
    private TextView tv_skin_1,tv_skin_2,tv_skin_3,tv_skin_4,tv_skin_5,tv_skin_6;
    private ImageView iv_body_head,iv_body_leg,iv_body_armpit,iv_body_waist,iv_body_back,iv_body_bikini;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_select_two);
        initView();
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出该页面
                finish();
            }
        });
        ImageView iv_main = findViewById(R.id.iv_main);
        iv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SkinSelectTwoActivity.this, SplashActivity.class));
            }
        });
        tv_skin_1 = findViewById(R.id.tv_skin_1);
        tv_skin_2 = findViewById(R.id.tv_skin_2);
        tv_skin_3 = findViewById(R.id.tv_skin_3);
        tv_skin_4 = findViewById(R.id.tv_skin_4);
        tv_skin_5 = findViewById(R.id.tv_skin_5);
        tv_skin_6 = findViewById(R.id.tv_skin_6);

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
                tv_skin_1.setBackground(getResources().getDrawable(R.drawable.mode_two_skin1_select_corners));
                break;
            case R.id.tv_skin_2:
                tv_skin_2.setBackground(getResources().getDrawable(R.drawable.mode_two_skin2_select_corners));
                break;
            case R.id.tv_skin_3:
                tv_skin_3.setBackground(getResources().getDrawable(R.drawable.mode_two_skin3_select_corners));
                break;
            case R.id.tv_skin_4:
                tv_skin_4.setBackground(getResources().getDrawable(R.drawable.mode_two_skin4_select_corners));
                break;
            case R.id.tv_skin_5:
                tv_skin_5.setBackground(getResources().getDrawable(R.drawable.mode_two_skin5_select_corners));
                break;
            case R.id.tv_skin_6:
                tv_skin_6.setBackground(getResources().getDrawable(R.drawable.mode_two_skin6_select_corners));
                break;
            case R.id.tv_skin_ok:
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
                iv_body_head.setBackground(getResources().getDrawable(R.drawable.mode_two_body_select_corners));
                break;
            case R.id.iv_body_leg:
                iv_body_leg.setBackground(getResources().getDrawable(R.drawable.mode_two_body_select_corners));
                break;
            case R.id.iv_body_armpit:
                iv_body_armpit.setBackground(getResources().getDrawable(R.drawable.mode_two_body_select_corners));
                break;
            case R.id.iv_body_waist:
                iv_body_waist.setBackground(getResources().getDrawable(R.drawable.mode_two_body_select_corners));
                break;
            case R.id.iv_body_back:
                iv_body_back.setBackground(getResources().getDrawable(R.drawable.mode_two_body_select_corners));
                break;
            case R.id.iv_body_bikini:
                iv_body_bikini.setBackground(getResources().getDrawable(R.drawable.mode_two_body_select_corners));
                break;
        }
    }

    private void resetMenuState() {
        tv_skin_1.setBackground(getResources().getDrawable(R.drawable.mode_two_skin1_unselect_corners));
        tv_skin_2.setBackground(getResources().getDrawable(R.drawable.mode_two_skin2_unselect_corners));
        tv_skin_3.setBackground(getResources().getDrawable(R.drawable.mode_two_skin3_unselect_corners));
        tv_skin_4.setBackground(getResources().getDrawable(R.drawable.mode_two_skin4_unselect_corners));
        tv_skin_5.setBackground(getResources().getDrawable(R.drawable.mode_two_skin5_unselect_corners));
        tv_skin_6.setBackground(getResources().getDrawable(R.drawable.mode_two_skin6_unselect_corners));
    }

    private void resetMenuBoydState() {
        iv_body_head.setBackground(getResources().getDrawable(R.drawable.mode_two_body_unselect_corners));
        iv_body_leg.setBackground(getResources().getDrawable(R.drawable.mode_two_body_unselect_corners));
        iv_body_armpit.setBackground(getResources().getDrawable(R.drawable.mode_two_body_unselect_corners));
        iv_body_waist.setBackground(getResources().getDrawable(R.drawable.mode_two_body_unselect_corners));
        iv_body_back.setBackground(getResources().getDrawable(R.drawable.mode_two_body_unselect_corners));
        iv_body_bikini.setBackground(getResources().getDrawable(R.drawable.mode_two_body_unselect_corners));

    }

    public void startA() {
        Intent intent = new Intent(SkinSelectTwoActivity.this, WorkSelectTwoActivity.class);
//        intent.putExtra("gender", "1");//自由人默认为男性
//        intent.putExtra("tel", "0");//自由人手机号默认为0
        startActivity(intent);
    }
}