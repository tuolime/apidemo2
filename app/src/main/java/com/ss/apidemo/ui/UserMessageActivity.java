package com.ss.apidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.adapter.UserMessageAdapter;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.bean.SendTimeBean;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.db.dao.UserDao;
import com.ss.apidemo.db.dao.UserValueDao;
import com.ss.apidemo.dialog.CallDialog;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.DateUtil;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ToastUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/*
* 用户详情 信息
* */
public class UserMessageActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private UserMessageAdapter adapter;
    private String tel;
    private TextView tv_time;
    private TextView tv_name;
    private RadioButton rb_man;
    private RadioButton rb_woman;
    private TextView tv_age;
    private TextView tv_tel;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        LinearLayout ll_main = findViewById(R.id.ll_main);
        BackgroundChangeUtils.backgroundChange(this,ll_main);
        Intent intent = getIntent();
        if (intent != null){
            tel = intent.getStringExtra("tel");
        }

        initView();
        initData();
    }

    private void initData() {
        userList = getData();
        if (userList != null && userList.size()>0){
            tv_name.setText(userList.get(0).getName());
            String gender = userList.get(0).getGender();
            if (gender.equals("1")){
                rb_man.setChecked(true);
            }else {
                rb_woman.setChecked(true);
            }
            //赋值完成后禁止选择
            rb_man.setEnabled(false);
            rb_woman.setEnabled(false);
            tv_age.setText(userList.get(0).getAge());
            tv_tel.setText(userList.get(0).getTel());
        }
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出时 下发报文选择好的温度和水流。。。。
                //退出该页面
                finish();
            }
        });
        tv_time = findViewById(R.id.tv_time);
        boolean booleanValue = SharedPrefsUtil.getBooleanValue(AppConfig.SHED, false);
        if (booleanValue) {
            tv_time.setVisibility(View.VISIBLE);
            if (AppConfig.AUTOSHEDTIME == AppConfig.INFINITE){
                tv_time.setText(getResources().getString(R.string.auto_shed_time)+"∞");
            }
        } else {
            tv_time.setVisibility(View.INVISIBLE);
        }
        tv_name = findViewById(R.id.tv_name);
        rb_man = findViewById(R.id.rb_man);
        rb_woman = findViewById(R.id.rb_woman);
        tv_age = findViewById(R.id.tv_age);
        tv_tel = findViewById(R.id.tv_tel);


        recyclerView = findViewById(R.id.recyclerview);
        //设置LayoutManager，以LinearLayoutManager为例子进行线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //创建适配器
        adapter = new UserMessageAdapter(getArrayList(),this);
        adapter.setClickItem(new UserMessageAdapter.ClickItem() {
            @Override
            public void onClickItem(int id,String tel) {
                CallDialog dialog = new CallDialog(UserMessageActivity.this);
                dialog.loadDialog(UserMessageActivity.this, new CallDialog.OnClickIsConfirm() {
                    @Override
                    public void OnClickIsConfirmListener() {//确定
                        UserValueDao.getInstance().deleteUserValue(id,tel);
                        adapter.setDatas(getArrayList());
                    }

                }, getResources().getString(R.string.user_delete_record));
            }
        });
        //设置适配器
        recyclerView.setAdapter(adapter);
        findViewById(R.id.tv_conntinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                isHandger();

            }
        });
        //暂时不用
        findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallDialog dialog = new CallDialog(UserMessageActivity.this);
                dialog.loadDialog(UserMessageActivity.this, new CallDialog.OnClickIsConfirm() {
                    @Override
                    public void OnClickIsConfirmListener() {//确定
                        if (tel != null && !tel.equals("")) {
                            boolean b = UserValueDao.getInstance().deleteAllUserValue(tel);//删除所有记录
                            if (b){
                                UserDao.getInstance().deleteUser(tel);//删除该用户
                                finish();
                            }

                        }else {
                            ToastUtil.showToast(UserMessageActivity.this,getResources().getString(R.string.tel_no_hint));
                        }
                    }

                }, getResources().getString(R.string.user_delete));


            }
        });
    }

    private List<User> getData() {
        List<User> allUser = null;
        if (tel != null && !tel.equals("")) {
            allUser = UserDao.getInstance().getUser(tel);
        }
        return allUser;
    }
    private List<UserValue> getArrayList() {
        List<UserValue> allUserValue = null;
        if (tel != null && !tel.equals("")) {
            allUserValue = UserValueDao.getInstance().getUserValue(tel);
        }
        return allUserValue;
    }
    public void isHandger(){
        int isHandgear = SharedPrefsUtil.getIntValue(AppConfig.HANDGEAR, 0);
        if (isHandgear == 0){//单手具
            Intent intent = new Intent(UserMessageActivity.this, ParameterActivity.class);
            startA(intent);
        }else if (isHandgear == 1){//双手具
            Intent intent = new Intent(UserMessageActivity.this, HandgearActivity.class);
            startA(intent);
        }

    }
    public void startA(Intent intent){
        if (userList != null && userList.size()>0){
            String gender = userList.get(0).getGender();
            String tel = userList.get(0).getTel();
            intent.putExtra("gender", gender);//自由人默认为男性
            intent.putExtra("tel", tel);
        }
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(SendTimeBean sendTimeBean) {//脱毛倒计时时间
        if (sendTimeBean != null) {
            int autoShedTime = sendTimeBean.getTime();
//            LogUtils.e("倒计时" + DateUtil.getTimeFromInt(autoShedTime * 1000));
            String timeFromInt = DateUtil.getTimeFromInt(autoShedTime * 1000);
            tv_time.setText(getResources().getString(R.string.auto_shed_time)+timeFromInt);
        }

    }
}