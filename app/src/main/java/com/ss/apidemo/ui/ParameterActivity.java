package com.ss.apidemo.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import com.ss.api.utils.LogUtil;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.adapter.MyFragmentPagerAdapter;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.bean.CommonBean;
import com.ss.apidemo.bean.CommonBodyBean;
import com.ss.apidemo.bean.QueueMessage;
import com.ss.apidemo.bean.SendTimeBean;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.db.dao.UserDao;
import com.ss.apidemo.db.dao.UserValueDao;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.fragment.ManBackFragment;
import com.ss.apidemo.fragment.ManFragment;
import com.ss.apidemo.fragment.ManHeadFragment;
import com.ss.apidemo.fragment.WoManBackFragment;
import com.ss.apidemo.fragment.WoManFragment;
import com.ss.apidemo.fragment.WoManHeadFragment;
import com.ss.apidemo.utils.DateUtil;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ToastUtil;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/*
 * 参数配置页面
 * */
public class ParameterActivity extends BaseActivity implements View.OnClickListener {
    ViewPager2 viewPager;
    ImageView[] imageViews = new ImageView[3];
    int current = 0;
    TextView tv_time;
    TextView tv_name;
    TextView tv_skin_1, tv_skin_2, tv_skin_3, tv_skin_4, tv_skin_5, tv_skin_6;
    TextView tv_mode1, tv_mode2, tv_mode3;
    TextView tv_size_s, tv_size_m, tv_size_l, tv_size_xl;

    public int handgearType;//手具类型

    public String gender;//性别 1 男  2 女

    public int mode;//模式 1 stack 2 shr  3 hr

    public int sink;//肤色  1 I 2 II 3 III 4 IV 5 V 6 VI

    public int size;//面积 1 s 2 m 3 x 4 xl

    public int body;//部位


//    public int bodyMode;//身体 0 头 1 身体  2 背面


    public String tel;//传递过来的用户手机号


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter);
        Intent intent = getIntent();
        if (intent != null) {
            gender = intent.getStringExtra("gender");
            tel = intent.getStringExtra("tel");
        }
        initView();

    }

    private void initView() {
        tv_time = findViewById(R.id.tv_time);
        boolean booleanValue = SharedPrefsUtil.getBooleanValue(AppConfig.SHED, false);
        if (booleanValue) {
            tv_time.setVisibility(View.VISIBLE);
            if (AppConfig.AUTOSHEDTIME == AppConfig.INFINITE) {
                tv_time.setText(getResources().getString(R.string.auto_shed_time) + "∞");
            }
        } else {
            tv_time.setVisibility(View.INVISIBLE);
        }
        tv_name = findViewById(R.id.tv_name);
        if (!tel.isEmpty()) {
            List<User> user = UserDao.getInstance().getUser(tel);
            if (user != null && user.size() > 0) {
                tv_name.setVisibility(View.VISIBLE);
                tv_name.setText(getResources().getString(R.string.name) + "：" + user.get(0).getName());
            }

        } else {
            tv_name.setVisibility(View.INVISIBLE);
        }
        tv_skin_1 = findViewById(R.id.tv_skin_1);
        tv_skin_2 = findViewById(R.id.tv_skin_2);
        tv_skin_3 = findViewById(R.id.tv_skin_3);
        tv_skin_4 = findViewById(R.id.tv_skin_4);
        tv_skin_5 = findViewById(R.id.tv_skin_5);
        tv_skin_6 = findViewById(R.id.tv_skin_6);
        tv_skin_1.getLayoutParams().width = 180;
//        SharedPrefsUtil.putIntValue(AppConfig.SKIN_TYPE,1);
        sink = 1;
        imageViews[0] = findViewById(R.id.head);
        imageViews[0].setOnClickListener(this);
        imageViews[1] = findViewById(R.id.body);
        imageViews[1].setOnClickListener(this);
        imageViews[2] = findViewById(R.id.body_reverse);
        imageViews[2].setOnClickListener(this);
        initViewPager();


        tv_mode1 = findViewById(R.id.tv_mode1);
        tv_mode2 = findViewById(R.id.tv_mode2);
        tv_mode3 = findViewById(R.id.tv_mode3);
        tv_mode1.setTextColor(getResources().getColor(R.color.tv_mode_select));
//        SharedPrefsUtil.putIntValue(AppConfig.MODE_TYPE,1);
        mode = 1;

        tv_size_s = findViewById(R.id.tv_size_s);
        tv_size_m = findViewById(R.id.tv_size_m);
        tv_size_l = findViewById(R.id.tv_size_l);
        tv_size_xl = findViewById(R.id.tv_size_xl);
        tv_size_s.setTextColor(getResources().getColor(R.color.tv_mode_select));
//        SharedPrefsUtil.putIntValue(AppConfig.SIZE_TYPE,1);
        size = 1;
        findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gender_int = Integer.parseInt(gender);
                int currentItem = viewPager.getCurrentItem();
                boolean bodyFlag = false;
                //性别 1 男  2 女
                if (gender_int == 1) {
                    if (currentItem == 0) {
                        //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                        // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                        // 13：后颈；14：后背；15：臀；16：肩；
                        switch (body) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                bodyFlag = true;
                                break;
                        }
                    } else if (currentItem == 1) {
                        switch (body) {
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                                bodyFlag = true;
                                break;
                        }
                    } else if (currentItem == 2) {
                        switch (body) {
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                                bodyFlag = true;
                                break;
                        }
                    }
                } else {
                    if (currentItem == 0) {
                        // 1：女性额头；2：女性面颊；3：女性嘴唇；4：女性脖子；
                        // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手臂；13：手；
                        // 14：后颈；15：后背；16：臀；17：肩；
                        switch (body) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                bodyFlag = true;
                                break;
                        }
                    } else if (currentItem == 1) {
                        switch (body) {
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                                bodyFlag = true;
                                break;
                        }
                    } else if (currentItem == 2) {
                        switch (body) {
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                                bodyFlag = true;
                                break;
                        }
                    }
                }


//                if (body == 0) {//未选中身体部位
//                    ToastUtil.showToast(ParameterActivity.this, getResources().getString(R.string.select_body));
//                    return;
//                }
                if (!bodyFlag) {//未选中身体部位
                    ToastUtil.showToast(ParameterActivity.this, getResources().getString(R.string.select_body));
                    return;
                }
                CommonBean commonBean = new CommonBean();
                if (AppConfig.handgearSelect == 1) {//单手==左手
                    handgearType = SharedPrefsUtil.getIntValue(AppConfig.HAND_LEFT, 1);
                } else if (AppConfig.handgearSelect == 0) {//双手 =  左右手
                    handgearType = SharedPrefsUtil.getIntValue(AppConfig.HAND_RIGHT, 1);
                }
                LogUtils.e("手具" + handgearType);
                LogUtils.e("BODY选中" + body);
                commonBean.setHandgearType(handgearType);
                commonBean.setGender(gender_int);
                commonBean.setMode(mode);
                commonBean.setSink(sink);
                commonBean.setSize(size);
                commonBean.setBody(body);
                commonBean.setBodyMode(viewPager.getCurrentItem());
                commonBean.setTel(tel);
                Intent intent = new Intent(ParameterActivity.this, OtherActivity.class);
                intent.putExtra("CommonBean", commonBean);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SetBodyStatus();
    }

    public void initViewPager() {
        viewPager = findViewById(R.id.view_pager);
        List<Fragment> fragmentList = new LinkedList<>();
        if (gender.equals("1")) {//男
            fragmentList.add(ManHeadFragment.newInstance(0));
            fragmentList.add(ManFragment.newInstance(0));
            fragmentList.add(ManBackFragment.newInstance(0));
        } else {//女
            fragmentList.add(WoManHeadFragment.newInstance(0));
            fragmentList.add(WoManFragment.newInstance(0));
            fragmentList.add(WoManBackFragment.newInstance(0));
        }

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        viewPager.setAdapter(myFragmentPagerAdapter);
        //监听Fragment的界面变化
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //其中position为当前Fragment的索引
                changePager(position);
                LogUtils.e("ci");
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                LogUtils.e("ci11");
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                LogUtils.e("ci22");
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head:
                changePager(0);
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.body:
                changePager(1);
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.body_reverse:
                changePager(2);
                viewPager.setCurrentItem(2, false);
                break;
        }
    }

    public void mainClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        LogUtils.e("声音4");
        switch (view.getId()) {
            case R.id.iv_main:
                startActivity(new Intent(ParameterActivity.this, SplashActivity.class));
                break;
            case R.id.iv_main_back:
                finish();
                break;
            case R.id.iv_main_setting:
                startActivity(new Intent(ParameterActivity.this, UserSettingActivity.class));
                break;
        }
    }

    public void skinClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuState();
        switch (view.getId()) {
            case R.id.tv_skin_1:
                ViewGroup.LayoutParams layoutParams1 = tv_skin_1.getLayoutParams();
                layoutParams1.width = 180;
                tv_skin_1.setLayoutParams(layoutParams1);
//                SharedPrefsUtil.putIntValue(AppConfig.SKIN_TYPE,1);
                sink = 1;
                break;
            case R.id.tv_skin_2:
                ViewGroup.LayoutParams layoutParams2 = tv_skin_2.getLayoutParams();
                layoutParams2.width = 180;
                tv_skin_2.setLayoutParams(layoutParams2);
//                SharedPrefsUtil.putIntValue(AppConfig.SKIN_TYPE,2);
                sink = 2;
                break;
            case R.id.tv_skin_3:
                ViewGroup.LayoutParams layoutParams3 = tv_skin_3.getLayoutParams();
                layoutParams3.width = 180;
                tv_skin_3.setLayoutParams(layoutParams3);
//                SharedPrefsUtil.putIntValue(AppConfig.SKIN_TYPE,3);
                sink = 3;
                break;
            case R.id.tv_skin_4:
                ViewGroup.LayoutParams layoutParams4 = tv_skin_4.getLayoutParams();
                layoutParams4.width = 180;
                tv_skin_4.setLayoutParams(layoutParams4);
//                SharedPrefsUtil.putIntValue(AppConfig.SKIN_TYPE,4);
                sink = 4;
                break;
            case R.id.tv_skin_5:
                ViewGroup.LayoutParams layoutParams5 = tv_skin_5.getLayoutParams();
                layoutParams5.width = 180;
                tv_skin_5.setLayoutParams(layoutParams5);
//                SharedPrefsUtil.putIntValue(AppConfig.SKIN_TYPE,5);
                sink = 5;
                break;
            case R.id.tv_skin_6:
                ViewGroup.LayoutParams layoutParams6 = tv_skin_6.getLayoutParams();
                layoutParams6.width = 180;
                tv_skin_6.setLayoutParams(layoutParams6);
//                SharedPrefsUtil.putIntValue(AppConfig.SKIN_TYPE,6);
                sink = 6;
                break;
        }
    }

    public void modeClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetModeState();
        switch (view.getId()) {
            case R.id.tv_mode1:
                mode = 1;
                tv_mode1.setTextColor(getResources().getColor(R.color.tv_mode_select));
//                SharedPrefsUtil.putIntValue(AppConfig.MODE_TYPE,1);
                break;
            case R.id.tv_mode2:
                mode = 2;
                tv_mode2.setTextColor(getResources().getColor(R.color.tv_mode_select));
//                SharedPrefsUtil.putIntValue(AppConfig.MODE_TYPE,2);
                break;
            case R.id.tv_mode3:
                mode = 3;
                tv_mode3.setTextColor(getResources().getColor(R.color.tv_mode_select));
//                SharedPrefsUtil.putIntValue(AppConfig.MODE_TYPE,3);
                break;
        }
    }

    public void sizeClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetSizeState();
        switch (view.getId()) {
            case R.id.tv_size_s:
                size = 1;
                tv_size_s.setTextColor(getResources().getColor(R.color.tv_mode_select));
//                SharedPrefsUtil.putIntValue(AppConfig.SIZE_TYPE,1);
                break;
            case R.id.tv_size_m:
                size = 2;
                tv_size_m.setTextColor(getResources().getColor(R.color.tv_mode_select));
//                SharedPrefsUtil.putIntValue(AppConfig.SIZE_TYPE,2);
                break;
            case R.id.tv_size_l:
                size = 3;
                tv_size_l.setTextColor(getResources().getColor(R.color.tv_mode_select));
//                SharedPrefsUtil.putIntValue(AppConfig.SIZE_TYPE,3);
                break;
            case R.id.tv_size_xl:
                size = 4;
                tv_size_xl.setTextColor(getResources().getColor(R.color.tv_mode_select));
//                SharedPrefsUtil.putIntValue(AppConfig.SIZE_TYPE,4);
                break;
        }
    }

    public void selectClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                changePager(count(true));
                viewPager.setCurrentItem(count(true), false);
                break;
            case R.id.iv_right:
                changePager(count(false));
                viewPager.setCurrentItem(count(false), false);
                break;

        }
    }

    /*
     * flag ture left
     * flag false right
     * */
    public int count(boolean flag) {
        int currentItem = viewPager.getCurrentItem();
        int count = 0;
        if (flag) {//点击了left
            if (currentItem == 0) {
                count = 0;
            } else if (currentItem == 1) {
                count = 0;
            } else if (currentItem == 2) {
                count = 1;
            }
            return count;
        } else {//点击了right
            if (currentItem == 0) {
                count = 1;
            } else if (currentItem == 1) {
                count = 2;
            } else if (currentItem == 2) {
                count = 2;
            }
            return count;
        }
    }

    private void resetModeState() {
        tv_mode1.setTextColor(getResources().getColor(R.color.bt_gray));
        tv_mode2.setTextColor(getResources().getColor(R.color.bt_gray));
        tv_mode3.setTextColor(getResources().getColor(R.color.bt_gray));
    }

    private void resetSizeState() {
        tv_size_s.setTextColor(getResources().getColor(R.color.white));
        tv_size_m.setTextColor(getResources().getColor(R.color.white));
        tv_size_l.setTextColor(getResources().getColor(R.color.white));
        tv_size_xl.setTextColor(getResources().getColor(R.color.white));
    }

    private void resetMenuState() {
        ViewGroup.LayoutParams layoutParams1 = tv_skin_1.getLayoutParams();
        layoutParams1.width = 105;
        tv_skin_1.setLayoutParams(layoutParams1);
        ViewGroup.LayoutParams layoutParams2 = tv_skin_2.getLayoutParams();
        layoutParams2.width = 105;
        tv_skin_2.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = tv_skin_3.getLayoutParams();
        layoutParams3.width = 105;
        tv_skin_3.setLayoutParams(layoutParams3);
        ViewGroup.LayoutParams layoutParams4 = tv_skin_4.getLayoutParams();
        layoutParams4.width = 105;
        tv_skin_4.setLayoutParams(layoutParams4);
        ViewGroup.LayoutParams layoutParams5 = tv_skin_5.getLayoutParams();
        layoutParams5.width = 105;
        tv_skin_5.setLayoutParams(layoutParams5);
        ViewGroup.LayoutParams layoutParams6 = tv_skin_6.getLayoutParams();
        layoutParams6.width = 105;
        tv_skin_6.setLayoutParams(layoutParams6);

    }

    @SuppressLint("ResourceAsColor")
    private void changePager(int position) {
        imageViews[current].setImageDrawable(getResources().getDrawable(R.mipmap.ic_head_unselect));
        current = position;
        switch (position) {
            case 0:
                imageViews[current].setImageDrawable(getResources().getDrawable(R.mipmap.ic_head_select));
                break;
            case 1:
                imageViews[current].setImageDrawable(getResources().getDrawable(R.mipmap.ic_head_select));
                break;
            case 2:
                imageViews[current].setImageDrawable(getResources().getDrawable(R.mipmap.ic_head_select));
                break;
        }
    }

    /**
     * 将圆点add入，播放动画
     *
     * @param clickPos 点击部位
     */
    public void addIVandPlay(int clickPos) {
        // 男部位id
        //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
        // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
        // 13：后颈；14：后背；15：臀；16：肩；
        body = clickPos;
        CommonBodyBean commonBodyBean = new CommonBodyBean();
        commonBodyBean.setCurrent_body_mode(viewPager.getCurrentItem());
        EventBus.getDefault().post(commonBodyBean);

        AppConfig.defaultBody = body;
        LogUtils.e("BODY改变" + body);
    }

    public void SetBodyStatus() {
        AppConfig.current_count = 0;
        body = 0;
        CommonBodyBean commonBodyBean = new CommonBodyBean();
        commonBodyBean.setCurrent_body_mode(3);// 0 1 2 对应的头 身体 背面   设置3 恢复未选中状态
        EventBus.getDefault().post(commonBodyBean);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(SendTimeBean sendTimeBean) {//脱毛倒计时时间
        if (sendTimeBean != null) {
            int autoShedTime = sendTimeBean.getTime();
//            LogUtils.e("倒计时" + DateUtil.getTimeFromInt(autoShedTime * 1000));
            String timeFromInt = DateUtil.getTimeFromInt(autoShedTime * 1000);
            tv_time.setText(getResources().getString(R.string.auto_shed_time) + timeFromInt);
        }

    }
}