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

public class ModeSelectSixActivity extends BaseActivity {
    private TextView tv_select1;
    private TextView tv_select2;

    public String tel;//传递过来的用户手机号
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select_six);
        Intent intent = getIntent();
        if (intent != null) {
            tel = intent.getStringExtra("tel");
            gender = intent.getStringExtra("gender");
        }
        initView();

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
                startActivity(new Intent(ModeSelectSixActivity.this, SplashActivity.class));
            }
        });
        tv_select1 = findViewById(R.id.tv_select1);
        tv_select2 = findViewById(R.id.tv_select2);
        //暂时先不要默认选中
//        ll_rounded1.setBackground(getResources().getDrawable(R.drawable.handgear_select_corners));
//        tv_select1.setBackground(getResources().getDrawable(R.drawable.handgear_bt_select_corners));
//        tv_content1.setTextColor(getResources().getColor(R.color.handgear_select));
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetMenuState();
    }

    public void tabClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuState();
        switch (view.getId()) {
            case R.id.tv_select1:
                tv_select1.setTextColor(getResources().getColor(R.color.white));
                startA(1);
                break;
            case R.id.tv_select2:
                tv_select2.setTextColor(getResources().getColor(R.color.white));
                startA(2);
                break;
        }
    }

    private void resetMenuState() {
        tv_select1.setTextColor(getResources().getColor(R.color.mode_six_bt_select));
        tv_select2.setTextColor(getResources().getColor(R.color.mode_six_bt_select));

    }

    public void startA(int flag) {
        Intent intent = null;
        if (flag == 1){//expert
            intent = new Intent(ModeSelectSixActivity.this, WorkSelectSixActivity.class);
            intent.putExtra("mode_type", 1);//1 专家  2 智能
        }else if (flag == 2){//smart
            intent = new Intent(ModeSelectSixActivity.this, SkinSelectSixActivity.class);
            intent.putExtra("tel", tel);
            intent.putExtra("gender", gender);//自由人默认为男性
        }
        if (intent != null){
            startActivity(intent);
        }
    }
}