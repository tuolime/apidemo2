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

public class ModeSelectFourActivity extends BaseActivity {
    private TextView tv_select1;
    private TextView tv_select2;
    private View vv_bg1;
    private View vv_bg2;
    public String tel;//传递过来的用户手机号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select_four);
        Intent intent = getIntent();
        if (intent != null) {
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
        ImageView iv_main = findViewById(R.id.iv_main);
        iv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ModeSelectFourActivity.this, SplashActivity.class));
            }
        });
        tv_select1 = findViewById(R.id.tv_select1);
        tv_select2 = findViewById(R.id.tv_select2);
        vv_bg1 = findViewById(R.id.vv_bg1);
        vv_bg2 = findViewById(R.id.vv_bg2);
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
                tv_select1.setTextColor(getResources().getColor(R.color.mode_four_bt_select));
                vv_bg1.setBackground(getResources().getDrawable(R.drawable.mode_four_bt_select_corners));
                startA(1);
                break;
            case R.id.tv_select2:
                tv_select2.setTextColor(getResources().getColor(R.color.mode_four_bt_select));
                vv_bg2.setBackground(getResources().getDrawable(R.drawable.mode_four_bt_select_corners));
                startA(2);
                break;
        }
    }

    private void resetMenuState() {
        tv_select1.setTextColor(getResources().getColor(R.color.mode_four_bt_unselect));
        tv_select2.setTextColor(getResources().getColor(R.color.mode_four_bt_unselect));
        vv_bg1.setBackground(getResources().getDrawable(R.drawable.mode_four_bt_unselect_corners));
        vv_bg2.setBackground(getResources().getDrawable(R.drawable.mode_four_bt_unselect_corners));

    }

    public void startA(int flag) {
        Intent intent = null;
        if (flag == 1){//expert
            intent = new Intent(ModeSelectFourActivity.this, WorkSelectFourActivity.class);
            intent.putExtra("mode_type", 1);//1 专家  2 智能
        }else if (flag == 2){//smart
            intent = new Intent(ModeSelectFourActivity.this, SkinSelectFourActivity.class);
            intent.putExtra("tel", tel);
        }
        if (intent != null){
            startActivity(intent);
        }
    }
}