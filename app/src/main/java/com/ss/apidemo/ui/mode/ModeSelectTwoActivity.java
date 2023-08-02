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

public class ModeSelectTwoActivity extends BaseActivity {
    private TextView tv_select1;
    private TextView tv_select2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select_two);

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
                startActivity(new Intent(ModeSelectTwoActivity.this, SplashActivity.class));
            }
        });
        tv_select1 = findViewById(R.id.tv_select1);
        tv_select2 = findViewById(R.id.tv_select2);
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
                tv_select1.setBackground(getResources().getDrawable(R.drawable.mode_two_bt_select_corners));
                tv_select1.setTextColor(getResources().getColor(R.color.white));
                startA(1);
                break;
            case R.id.tv_select2:
                tv_select2.setBackground(getResources().getDrawable(R.drawable.mode_two_bt_select_corners));
                tv_select2.setTextColor(getResources().getColor(R.color.white));
                startA(2);
                break;
        }
    }

    private void resetMenuState() {
        tv_select1.setBackground(getResources().getDrawable(R.drawable.mode_two_bt_unselect_corners));
        tv_select2.setBackground(getResources().getDrawable(R.drawable.mode_two_bt_unselect_corners));
        tv_select1.setTextColor(getResources().getColor(R.color.mode_two_bt_unselect));
        tv_select2.setTextColor(getResources().getColor(R.color.mode_two_bt_unselect));
//        iv_expert_select.setBackground(getResources().getDrawable(R.mipmap.ic_one_select));
//        iv_smart_select.setBackground(getResources().getDrawable(R.mipmap.ic_one_select));

    }

    public void startA(int flag) {
        Intent intent = null;
        if (flag == 1){//expert
            intent = new Intent(ModeSelectTwoActivity.this, WorkSelectTwoActivity.class);
            intent.putExtra("mode_type", 1);//1 专家  2 智能
        }else if (flag == 2){//smart
            intent = new Intent(ModeSelectTwoActivity.this, SkinSelectTwoActivity.class);
        }
        if (intent != null){
            startActivity(intent);
        }
    }
}