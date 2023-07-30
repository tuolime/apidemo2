package com.ss.apidemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import com.ss.api.SsApi;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.dao.UserDao;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.ui.mode.ModeSelectFiveActivity;
import com.ss.apidemo.ui.mode.ModeSelectFourActivity;
import com.ss.apidemo.ui.mode.ModeSelectOneActivity;
import com.ss.apidemo.ui.mode.ModeSelectSixActivity;
import com.ss.apidemo.ui.mode.ModeSelectThreeActivity;
import com.ss.apidemo.ui.mode.ModeSelectTwoActivity;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ToastUtil;

/*
 * 患者配置页面  包含 用户创建 用户查询 自由人
 * */
public class UserCreateActivity extends BaseActivity {
    private String gender = "1"; //1 男 2 女
    private EditText et_id;
    private EditText et_name;
    private RadioGroup rg;
    private EditText et_age;
    private EditText et_tel;
    private EditText et_search;
    private TextView tv_search_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);
        LinearLayout ll_main = findViewById(R.id.ll_main);
        BackgroundChangeUtils.backgroundChange(this,ll_main);
        initView();
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideInput();
                //退出该页面
                startActivity(new Intent(UserCreateActivity.this, SplashActivity.class));
            }
        });
        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        rg = findViewById(R.id.rg);
        et_age = findViewById(R.id.et_age);
        et_tel = findViewById(R.id.et_tel);
        et_search = findViewById(R.id.et_search);
        tv_search_hint = findViewById(R.id.tv_search_hint);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                hideInput();
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                switch (i) {
                    case R.id.rd_man:
                        gender = "1";
                        break;
                    case R.id.rd_woman:
                        gender = "2";
                        break;
                }
            }
        });
        findViewById(R.id.tv_create).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                hideInput();
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                String id = et_id.getText().toString();
                String name = et_name.getText().toString();
                String age = et_age.getText().toString();
                String tel = et_tel.getText().toString();
                if (name == null || name.equals("")) {
                    ToastUtil.showLongToast(UserCreateActivity.this, getResources().getString(R.string.name_cannot));
                    return;
                }
                if (name.length() > 20) {
                    ToastUtil.showLongToast(UserCreateActivity.this, getResources().getString(R.string.name_count));
                    return;
                }
                if (tel == null || tel.equals("")) {
                    ToastUtil.showLongToast(UserCreateActivity.this, getResources().getString(R.string.phone_cannot));
                    return;
                }
                if (tel.length() > 13) {
                    ToastUtil.showLongToast(UserCreateActivity.this, getResources().getString(R.string.phone_count));
                    return;
                }
                List<User> user1 = UserDao.getInstance().getUser(tel);
                if (user1 == null || user1.size() > 0) {
                    ToastUtil.showLongToast(UserCreateActivity.this, getResources().getString(R.string.phone_already));
                    return;
                }
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setGender(gender);
                user.setAge(age);
                user.setTel(tel);
                UserDao.getInstance().createUser(user);
                HintDialog dialog = new HintDialog(UserCreateActivity.this);
                dialog.loadDialog(UserCreateActivity.this, new HintDialog.OnClickIsConfirm() {
                    @Override
                    public void OnClickIsConfirmListener() {//确定
                        et_search.setText(tel);
                    }

                }, getResources().getString(R.string.user_created));
//                }, getString(R.string.root_hint));
            }
        });
        findViewById(R.id.tv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideInput();
                String s = et_search.getText().toString();
                if (s != null && !s.equals("")) {
                    List<User> allUser = UserDao.getInstance().getLikeUser(s);
                    if (allUser != null && allUser.size() > 0) {
                        tv_search_hint.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(UserCreateActivity.this, UserListActivity.class);
                        intent.putExtra("tel", s);
                        startActivity(intent);
                    } else {
                        tv_search_hint.setVisibility(View.VISIBLE);
                    }
                } else {
                    Intent intent = new Intent(UserCreateActivity.this, UserListActivity.class);
                    intent.putExtra("tel", s);
                    startActivity(intent);
                }

            }
        });
        findViewById(R.id.tv_free).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isHandger();
            }
        });
    }

    public void isHandger() {
        int modeType = SharedPrefsUtil.getIntValue(AppConfig.MODETYPE, 1);
        if (modeType == 1){
            int isHandgear = SharedPrefsUtil.getIntValue(AppConfig.HANDGEAR, 0);
            if (isHandgear == 0) {//单手具
                Intent intent = new Intent(UserCreateActivity.this, ParameterActivity.class);
                intent.putExtra("gender", "1");//自由人默认为男性
                intent.putExtra("tel", "0");//自由人手机号默认为0
                startActivity(intent);

            } else if (isHandgear == 1) {//双手具
                Intent intent = new Intent(UserCreateActivity.this, HandgearActivity.class);
                intent.putExtra("gender", "1");//自由人默认为男性
                intent.putExtra("tel", "0");//自由人手机号默认为0
                startActivity(intent);

            }
        }else if (modeType == 2) {
//            Intent intent = new Intent(UserCreateActivity.this, ParameterActivity.class);
//            intent.putExtra("gender", "1");//自由人默认为男性
//            intent.putExtra("tel", "0");//自由人手机号默认为0
//            startActivity(intent);
            int intValue = SharedPrefsUtil.getIntValue(AppConfig.MODE_TWO_GB, 1);
            Intent intent = null;
//            if (intValue == 1) {
                intent = new Intent(UserCreateActivity.this, ModeSelectOneActivity.class);
//            } else if (intValue == 2) {
//                intent = new Intent(UserCreateActivity.this, ModeSelectTwoActivity.class);
//            } else if (intValue == 3) {
//                intent = new Intent(UserCreateActivity.this, ModeSelectThreeActivity.class);
//            } else if (intValue == 4) {
//                intent = new Intent(UserCreateActivity.this, ModeSelectFourActivity.class);
//            } else if (intValue == 5) {
//                intent = new Intent(UserCreateActivity.this, ModeSelectFiveActivity.class);
//            } else if (intValue == 6) {
//                intent = new Intent(UserCreateActivity.this, ModeSelectSixActivity.class);
//            }

            if (intent != null){
                startActivity(intent);
            }
        }

    }

    /**
     * 关闭软键盘
     */
    public void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        IBinder token = getWindow().getDecorView().getWindowToken();
        if (imm != null && imm.isActive() && token != null) {
            imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}