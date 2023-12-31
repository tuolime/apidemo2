package com.ss.apidemo.ui.mode;

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
import com.ss.apidemo.ui.HandgearActivity;
import com.ss.apidemo.ui.OtherActivity;
import com.ss.apidemo.ui.ParameterActivity;
import com.ss.apidemo.ui.SplashActivity;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.ClickUtil;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author gjl
 * @Date 2023-07-17 16:47
 **/
public class ModeSelectOneActivity extends BaseActivity {
    private TextView tv_select1;
    private TextView tv_select2;
    private ImageView iv_expert_select;
    private ImageView iv_smart_select;

    public String tel;//传递过来的用户手机号
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select_one);
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
                //多次点击直接返回
                if (ClickUtil.isFastClick()) {
                    return;
                }
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                startActivity(new Intent(ModeSelectOneActivity.this, SplashActivity.class));
            }
        });
        tv_select1 = findViewById(R.id.tv_select1);
        tv_select2 = findViewById(R.id.tv_select2);
        iv_expert_select = findViewById(R.id.iv_expert_select);
        iv_smart_select = findViewById(R.id.iv_smart_select);
        //暂时先不要默认选中
//        ll_rounded1.setBackground(getResources().getDrawable(R.drawable.handgear_select_corners));
//        tv_select1.setBackground(getResources().getDrawable(R.drawable.handgear_bt_select_corners));
//        tv_content1.setTextColor(getResources().getColor(R.color.handgear_select));
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppConfig.current_count = 0;
        resetMenuState();
    }

    public void tabClick(View view) {
        //多次点击直接返回
        if (ClickUtil.isFastClick()) {
            return;
        }
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuState();
        switch (view.getId()) {
            case R.id.tv_select1:
                tv_select1.setBackground(getResources().getDrawable(R.drawable.mode_one_bt_select_corners));
                tv_select1.setTextColor(getResources().getColor(R.color.mode_one_bt_select));
                iv_expert_select.setVisibility(View.VISIBLE);
                iv_smart_select.setVisibility(View.INVISIBLE);
                startA(1);
                break;
            case R.id.tv_select2:
                tv_select2.setBackground(getResources().getDrawable(R.drawable.mode_one_bt_select_corners));
                tv_select2.setTextColor(getResources().getColor(R.color.mode_one_bt_select));
                iv_expert_select.setVisibility(View.INVISIBLE);
                iv_smart_select.setVisibility(View.VISIBLE);
                startA(2);
                break;
        }
    }

    private void resetMenuState() {
        tv_select1.setBackground(getResources().getDrawable(R.drawable.mode_one_bt_unselect_corners));
        tv_select2.setBackground(getResources().getDrawable(R.drawable.mode_one_bt_unselect_corners));
        tv_select1.setTextColor(getResources().getColor(R.color.mode_one_bt_unselect));
        tv_select2.setTextColor(getResources().getColor(R.color.mode_one_bt_unselect));
//        iv_expert_select.setBackground(getResources().getDrawable(R.mipmap.ic_one_select));
//        iv_smart_select.setBackground(getResources().getDrawable(R.mipmap.ic_one_select));

    }

    public void startA(int flag) {
        Intent intent = null;
        if (flag == 1){//expert
            intent = new Intent(ModeSelectOneActivity.this, WorkSelectOneActivity.class);
            intent.putExtra("mode_type", 1);//1 专家  2 智能
            intent.putExtra("tel", tel);
        }else if (flag == 2){//smart
            intent = new Intent(ModeSelectOneActivity.this, SkinSelectOneActivity.class);
            intent.putExtra("tel", tel);
            intent.putExtra("gender", gender);//自由人默认为男性
        }
        if (intent != null){
            startActivity(intent);
        }
    }
}
