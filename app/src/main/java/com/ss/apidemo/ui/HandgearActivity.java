package com.ss.apidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;

/*
* 手具选择页面
* */
public class HandgearActivity extends BaseActivity {

    private LinearLayout ll_rounded1;
    private LinearLayout ll_rounded2;
    private TextView tv_select1;
    private TextView tv_select2;
    private TextView tv_content1;
    private TextView tv_content2;
    private ImageView iv_exact;
    private ImageView iv_3d;

    private String gender;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handgear);
        LinearLayout ll_main = findViewById(R.id.ll_main);
        BackgroundChangeUtils.backgroundChange(this,ll_main);
        Intent intent = getIntent();
        if (intent != null){
            gender = intent.getStringExtra("gender");
            tel = intent.getStringExtra("tel");
        }

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
        ll_rounded1 = findViewById(R.id.ll_rounded1);
        ll_rounded2 = findViewById(R.id.ll_rounded2);
        tv_select1 = findViewById(R.id.tv_select1);
        tv_select2 = findViewById(R.id.tv_select2);
        tv_content1 = findViewById(R.id.tv_content1);
        tv_content2 = findViewById(R.id.tv_content2);
        iv_exact = findViewById(R.id.iv_exact);
        iv_3d = findViewById(R.id.iv_3d);
        //暂时先不要默认选中
//        ll_rounded1.setBackground(getResources().getDrawable(R.drawable.handgear_select_corners));
//        tv_select1.setBackground(getResources().getDrawable(R.drawable.handgear_bt_select_corners));
//        tv_content1.setTextColor(getResources().getColor(R.color.handgear_select));
    }

    public void tabClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuState();
        switch (view.getId()) {
            case R.id.tv_select1:
                AppConfig.handgearSelect = 1;
                ll_rounded1.setBackground(getResources().getDrawable(R.drawable.handgear_select_corners));
                tv_select1.setBackground(getResources().getDrawable(R.drawable.handgear_bt_select_corners));
                tv_content1.setTextColor(getResources().getColor(R.color.handgear_select));
                iv_exact.setBackground(getResources().getDrawable(R.mipmap.ic_exact_select));
                startA();
                break;
            case R.id.tv_select2:
                AppConfig.handgearSelect = 0;
                ll_rounded2.setBackground(getResources().getDrawable(R.drawable.handgear_select_corners));
                tv_select2.setBackground(getResources().getDrawable(R.drawable.handgear_bt_select_corners));
                tv_content2.setTextColor(getResources().getColor(R.color.handgear_select));
                iv_3d.setBackground(getResources().getDrawable(R.mipmap.ic_quattro_3d_select));
                startA();
                break;
        }
    }

    private void resetMenuState() {
        ll_rounded1.setBackground(getResources().getDrawable(R.drawable.handgear_unselect_corners));
        tv_select1.setBackground(getResources().getDrawable(R.drawable.handgear_bt_unselect_corners));
        tv_content1.setTextColor(getResources().getColor(R.color.handgear_unselect));
        ll_rounded2.setBackground(getResources().getDrawable(R.drawable.handgear_unselect_corners));
        tv_select2.setBackground(getResources().getDrawable(R.drawable.handgear_bt_unselect_corners));
        tv_content2.setTextColor(getResources().getColor(R.color.handgear_unselect));
        iv_exact.setBackground(getResources().getDrawable(R.mipmap.ic_exact_unselect));
        iv_3d.setBackground(getResources().getDrawable(R.mipmap.ic_quattro_3d_unselect));

    }
    public void startA(){
        Intent intent = new Intent(HandgearActivity.this, ParameterActivity.class);
        intent.putExtra("gender", gender);//自由人默认为男性
        intent.putExtra("tel", tel);
        startActivity(intent);
    }
}