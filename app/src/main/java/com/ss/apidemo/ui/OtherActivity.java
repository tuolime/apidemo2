package com.ss.apidemo.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetWorkingStatus;
import com.example.protocol.frame.data.UploadWorkingInfo;
import com.example.protocol.utils.ParserUtil;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.adapter.MyFragmentPagerAdapter;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.bean.CommonBean;
import com.ss.apidemo.bean.CommonBodyBean;
import com.ss.apidemo.bean.SendTimeBean;
import com.ss.apidemo.bean.SetFluenceBean;
import com.ss.apidemo.bean.StopWorkBean;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.db.dao.UserDao;
import com.ss.apidemo.db.dao.UserValueDao;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.fragment.HrFragment;
import com.ss.apidemo.fragment.ManBackFragment;
import com.ss.apidemo.fragment.ManFragment;
import com.ss.apidemo.fragment.ManHeadFragment;
import com.ss.apidemo.fragment.ShrFragment;
import com.ss.apidemo.fragment.StackFragment;
import com.ss.apidemo.fragment.WoManBackFragment;
import com.ss.apidemo.fragment.WoManFragment;
import com.ss.apidemo.fragment.WoManHeadFragment;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.ClickUtil;
import com.ss.apidemo.utils.DateUtil;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ToastUtil;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import cn.hutool.core.lang.Console;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/*
 * 主页面
 * */
public class OtherActivity extends BaseActivity implements View.OnClickListener {
    TextView[] textViews = new TextView[3];
    ViewPager2 viewPager_mode;
    ImageView[] imageViews = new ImageView[3];
    ViewPager2 viewPager;
    int current = 0;
    int currentMode = 0;
    CommonBean commonBean;
    private StackFragment stackFragment;
    private ShrFragment shrFragment;
    private HrFragment hrFragment;

    private int current_status = 0;

    private int current_count = 0;//首次work 触发声音

    SetWorkingStatus setWorkingStatusDb = null;
    private TextView tv_time;
    private TextView tv_name;
    private TextView tv_next;
    private TextView tv_total;
    private TextView tv_current;
    private LinearLayout ll_all;
    private LinearLayout ll_down_nav_visibility;

    public int count_mode_playVoice = 0;//防止首次进来播放声音 true 播放  false 不播放
    public int count_body_playVoice = 0;//防止首次进来播放声音 true 播放  false 不播放
    public int count_flag = 0;//防止首次进来播放声音 true 播放  false 不播放
    private ManHeadFragment manHeadFragment;
    private ManFragment manFragment;
    private ManBackFragment manBackFragment;
    private WoManHeadFragment woManHeadFragment;
    private WoManFragment woManFragment;
    private WoManBackFragment woManBackFragment;
    private int current_luminescence_count;
    private int current_luminescence_save_stop_count;
    private int current_luminescence_upload_stop_count;
    private boolean clear_count = false;
    UploadWorkingInfo uploadWorkingInfo_energy;
    private int current_luminescence_auto_save_stop_count;
    private int flag_number = 3;
    private int flag_count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        LinearLayout ll_main = findViewById(R.id.ll_main);
        BackgroundChangeUtils.backgroundChange(this,ll_main);
        Intent intent = getIntent();
        if (intent != null) {
            commonBean = (CommonBean) intent.getSerializableExtra("CommonBean");
        }

        initView();
//        initData();
    }

    private void initView() {
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
        if (!commonBean.getTel().isEmpty()) {
            List<User> user = UserDao.getInstance().getUser(commonBean.getTel());
            if (user != null && user.size() > 0){
                tv_name.setVisibility(View.VISIBLE);
                tv_name.setText(getResources().getString(R.string.name)+"："+user.get(0).getName());
            }

        } else {
            tv_name.setVisibility(View.INVISIBLE);
        }
        ll_all = findViewById(R.id.ll_all);
        ll_down_nav_visibility = findViewById(R.id.ll_down_nav_visibility);

        textViews[0] = findViewById(R.id.tv_mode1);
        textViews[0].setOnClickListener(this);
        textViews[1] = findViewById(R.id.tv_mode2);
        textViews[1].setOnClickListener(this);
        textViews[2] = findViewById(R.id.tv_mode3);
        textViews[2].setOnClickListener(this);
        initViewPagerMode();

        imageViews[0] = findViewById(R.id.head);
        imageViews[0].setOnClickListener(this);
        imageViews[1] = findViewById(R.id.body);
        imageViews[1].setOnClickListener(this);
        imageViews[2] = findViewById(R.id.body_reverse);
        imageViews[2].setOnClickListener(this);
        tv_total = findViewById(R.id.tv_total);
        tv_current = findViewById(R.id.tv_current);
        tv_next = findViewById(R.id.tv_next);
        tv_next.setBackground(getResources().getDrawable(R.drawable.other_bt_stby_rounded_corners));
        tv_next.setText(getResources().getString(R.string.stby));
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                LogUtils.e("声音1");
                if (current_status == 0) {
                    current_status = 1;
                    ll_all.setVisibility(View.VISIBLE);
                    ll_down_nav_visibility.setVisibility(View.VISIBLE);
//                    tv_next.setBackground(getResources().getDrawable(R.drawable.other_bt_read_rounded_corners));
//                    tv_next.setText(getResources().getString(R.string.ready));
                    //准备 下发数据
                    int currentItem = viewPager_mode.getCurrentItem();
                    if (currentItem == 0) {//Stack
                        setWorkingStatusDb = stackFragment.setValue();
                    } else if (currentItem == 1) {//Shr
                        setWorkingStatusDb = shrFragment.setValue();
                    } else if (currentItem == 2) {//Hr
                        setWorkingStatusDb = hrFragment.setValue();
                    }
                    LogUtils.e("下发数据bean" + setWorkingStatusDb.toString());
                    ProtocalHandler protocalHandler = new ProtocalHandler();
                    Frame frame = protocalHandler.buildSetWorkingStatusFrame(setWorkingStatusDb);
                    Console.log(ParserUtil.toHexString(frame.getFrame()));
                    String message = ParserUtil.toHexString(frame.getFrame());
                    sendSerialPortHexMsg(message, frame.getCtrlCode());
                    LogUtils.e("下发数据" + message);
                } else if (current_status == 1 || current_status == 2) {
                    current_status = 0;
                    ll_all.setVisibility(View.GONE);
                    ll_down_nav_visibility.setVisibility(View.GONE);
//                    tv_next.setBackground(getResources().getDrawable(R.drawable.other_bt_stby_rounded_corners));
//                    tv_next.setText(getResources().getString(R.string.stby));
                    //停止 下发数据 能效 0  工作状态 0
                    ProtocalHandler protocalHandler = new ProtocalHandler();
                    SetWorkingStatus setWorkingStatus = new SetWorkingStatus();
                    setWorkingStatus.setWorkingModel(0); // 工作模式  1 2 3 4 5 shr 6 hr
                    setWorkingStatus.setFluence(0); // 单脉冲能量
                    setWorkingStatus.setFrequency(0); // 频率
                    setWorkingStatus.setTotalEnergy(0);// 总能量
                    setWorkingStatus.setWorkingTime(0); // 工作时间  秒
                    setWorkingStatus.setQbConfig(0); // 手具选择 1-7
                    setWorkingStatus.setChangeQbPortFlag(0); // 是否切换手具端口 1 left 0 right
                    setWorkingStatus.setWorkingStatus(0);// 工作状态 0  stby和停止  1 reading 2 working
                    Frame frame = protocalHandler.buildSetWorkingStatusFrame(setWorkingStatus);
                    Console.log(ParserUtil.toHexString(frame.getFrame()));
                    String message = ParserUtil.toHexString(frame.getFrame());
                    sendSerialPortHexMsg(message, frame.getCtrlCode());
                    LogUtils.e("下发停止" + message);
                }


            }
        });
        initViewPager();
    }

    @Override
    public void onResume() {
        super.onResume();
        current_luminescence_save_stop_count = 0;
        current_luminescence_auto_save_stop_count = 0;
        current_luminescence_upload_stop_count = 0;
    }

    public void initViewPagerMode() {
        viewPager_mode = findViewById(R.id.view_pager_mode);
        stackFragment = StackFragment.newInstance(commonBean);
        shrFragment = ShrFragment.newInstance(commonBean);
        hrFragment = HrFragment.newInstance(commonBean);
        List<Fragment> fragmentList = new LinkedList<>();
        fragmentList.add(stackFragment);
        fragmentList.add(shrFragment);
        fragmentList.add(hrFragment);
        viewPager_mode.setOffscreenPageLimit(fragmentList.size());
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        viewPager_mode.setAdapter(myFragmentPagerAdapter);
        viewPager_mode.setUserInputEnabled(false);
        //监听Fragment的界面变化
        viewPager_mode.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //其中position为当前Fragment的索引
                changePagerMode(position);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                count_mode_playVoice++;
                if (count_mode_playVoice >=2) {
                    PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                    LogUtils.e("声音2");
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        changePagerMode(commonBean.getMode() - 1);
        viewPager_mode.setCurrentItem(commonBean.getMode() - 1, false);
    }

    public void initViewPager() {
        viewPager = findViewById(R.id.view_pager);
        List<Fragment> fragmentList = new LinkedList<>();

        if (commonBean.getGender() == 1) {
            manHeadFragment = ManHeadFragment.newInstance(commonBean.getBody());
            manFragment = ManFragment.newInstance(commonBean.getBody());
            manBackFragment = ManBackFragment.newInstance(commonBean.getBody());
            fragmentList.add(manHeadFragment);
            fragmentList.add(manFragment);
            fragmentList.add(manBackFragment);
        } else {
            woManHeadFragment = WoManHeadFragment.newInstance(commonBean.getBody());
            woManFragment = WoManFragment.newInstance(commonBean.getBody());
            woManBackFragment = WoManBackFragment.newInstance(commonBean.getBody());
            fragmentList.add(woManHeadFragment);
            fragmentList.add(woManFragment);
            fragmentList.add(woManBackFragment);
        }
        viewPager.setOffscreenPageLimit(fragmentList.size());
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        viewPager.setAdapter(myFragmentPagerAdapter);

        //监听Fragment的界面变化
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //其中position为当前Fragment的索引
                changePager(position);


            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                count_body_playVoice++;
                if (count_body_playVoice >=2) {
                    PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                    LogUtils.e("声音3");
                }
                count_flag++;
                if (count_flag >= 2) {
                    LogUtils.e("-----22"+count_flag);
                    int currentItem = viewPager.getCurrentItem();
                    if (commonBean.getGender() == 1) {
                        if (currentItem == 0){
                            manHeadFragment.SetBodyDefaultType();
                        }else if (currentItem == 1){
                            manFragment.SetBodyDefaultType();
                        }else if (currentItem == 2){
                            manBackFragment.SetBodyDefaultType();
                        }
                    }else {
                        if (currentItem == 0){
                            woManHeadFragment.SetBodyDefaultType();
                        }else if (currentItem == 1){
                            woManFragment.SetBodyDefaultType();
                        }else if (currentItem == 2){
                            woManBackFragment.SetBodyDefaultType();
                        }
                    }

//                    CommonBodyBean commonBodyBean = new CommonBodyBean();
//                    commonBodyBean.setCurrent_body_mode(viewPager.getCurrentItem());
//                    commonBodyBean.setCurrent_default_body(true);
//                    EventBus.getDefault().post(commonBodyBean);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        changePager(commonBean.getBodyMode());
        viewPager.setCurrentItem(commonBean.getBodyMode(), false);
    }

    @SuppressLint("ResourceAsColor")
    private void changePagerMode(int position) {
        textViews[currentMode].setTextColor(getResources().getColor(R.color.bt_gray));
        currentMode = position;
        switch (position) {
            case 0:
                textViews[currentMode].setTextColor(getResources().getColor(R.color.tv_mode_select));
                break;
            case 1:
                textViews[currentMode].setTextColor(getResources().getColor(R.color.tv_mode_select));
                break;
            case 2:
                textViews[currentMode].setTextColor(getResources().getColor(R.color.tv_mode_select));
                break;
        }
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mode1:
                changePagerMode(0);
                viewPager_mode.setCurrentItem(0, false);
                break;
            case R.id.tv_mode2:
                changePagerMode(1);
                viewPager_mode.setCurrentItem(1, false);
                break;
            case R.id.tv_mode3:
                changePagerMode(2);
                viewPager_mode.setCurrentItem(2, false);
                break;
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

    public void selectClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                changePager(count(true));
                viewPager.setCurrentItem(count(true));
                break;
            case R.id.iv_right:
                changePager(count(false));
                viewPager.setCurrentItem(count(false));
                break;

        }
    }

    public void mainClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        LogUtils.e("声音4");
        switch (view.getId()) {
            case R.id.iv_main:
                //多次点击直接返回
                if (ClickUtil.isFastClick()) {
                    return;
                }
                startActivity(new Intent(OtherActivity.this, SplashActivity.class));
                break;
            case R.id.iv_main_back:
                finish();
                break;
            case R.id.iv_main_setting:
                //多次点击直接返回
                if (ClickUtil.isFastClick()) {
                    return;
                }
                startActivity(new Intent(OtherActivity.this, UserSettingActivity.class));
                break;
            case R.id.iv_main_upload://保存待定 保存当前参数数据
                if (current_status == 0 || current_status == 2){//stby 和 work 不允许保存记录
                    return;
                }
                if (commonBean.getTel() == null || commonBean.getTel().equals("") || commonBean.getTel().equals("0")) {//自由人不记录数据
                    return;
                }
                if (setWorkingStatusDb == null) {
                    ToastUtil.showToast(OtherActivity.this, getResources().getString(R.string.work_no));
                    return;
                }
                UserValue userValue1 = new UserValue();
                userValue1.setTel(commonBean.getTel());
                userValue1.setGender(commonBean.getGender() + "");
                userValue1.setMode(setWorkingStatusDb.getWorkingModel() + "");
                userValue1.setSkinType(commonBean.getSink() + "");
                userValue1.setBodyType(commonBean.getBody() + "");
                if (uploadWorkingInfo_energy != null){
                    userValue1.setEnergy(uploadWorkingInfo_energy.getTotalEnergy() + "");
                }
                userValue1.setFrequency(setWorkingStatusDb.getFrequency() + "");
                userValue1.setFluence(setWorkingStatusDb.getFluence() + "");

                String s = tv_current.getText().toString();
                int current_number = Integer.parseInt(s);
                int work_count= current_number - current_luminescence_save_stop_count;
                userValue1.setWorkCount(work_count+ "");
                UserValueDao.getInstance().createUserValue(userValue1);
                current_luminescence_save_stop_count = current_luminescence_count;
                HintDialog dialog = new HintDialog(OtherActivity.this);
                dialog.loadDialog(OtherActivity.this, new HintDialog.OnClickIsConfirm() {
                    @Override
                    public void OnClickIsConfirmListener() {//确定
                    }

                }, getResources().getString(R.string.user_information));
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
        // 女部位id
        //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
        // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：臂；13：手；
        // 14：后颈；15：后背；16：臀；17：肩；
//        body = clickPos;
//        ToastUtil.showToast(OtherActivity.this,"身体部位"+clickPos);
        commonBean.setBody(clickPos);
        EventBus.getDefault().post(commonBean);

        CommonBodyBean commonBodyBean = new CommonBodyBean();
        commonBodyBean.setCurrent_body_mode(viewPager.getCurrentItem());
        EventBus.getDefault().post(commonBodyBean);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(UploadWorkingInfo uploadWorkingInfo) {//結果集
        setSendUserValues(uploadWorkingInfo);//单独处理上报治疗信息
        setSendCountValues(uploadWorkingInfo);
        if (uploadWorkingInfo.getWorkingStatus() == 0) {//stby
            current_status = 0;
            ll_all.setVisibility(View.GONE);
            ll_down_nav_visibility.setVisibility(View.GONE);
            LogUtils.e("stby");
            tv_next.setBackground(getResources().getDrawable(R.drawable.other_bt_stby_rounded_corners));
            tv_next.setText(getResources().getString(R.string.stby));
        } else if (uploadWorkingInfo.getWorkingStatus() == 1) {//reading
            current_status = 1;
            ll_all.setVisibility(View.VISIBLE);
            ll_down_nav_visibility.setVisibility(View.VISIBLE);
            LogUtils.e("reading");
            tv_next.setBackground(getResources().getDrawable(R.drawable.other_bt_read_rounded_corners));
            tv_next.setText(getResources().getString(R.string.ready));
            current_count = 1;
        } else if (uploadWorkingInfo.getWorkingStatus() == 2) {//working
            uploadWorkingInfo_energy = uploadWorkingInfo;
            if (current_count == 1) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                LogUtils.e("声音5");
            }
            current_count++;
            current_status = 2;
            ll_all.setVisibility(View.VISIBLE);
            ll_down_nav_visibility.setVisibility(View.VISIBLE);
            LogUtils.e("working");
            tv_next.setBackground(getResources().getDrawable(R.drawable.other_bt_working_rounded_corners));
            tv_next.setText(getResources().getString(R.string.working));
            LogUtils.e("firstSendMsg","11="+current_luminescence_count+"22 ="+current_luminescence_upload_stop_count);

            current_luminescence_upload_stop_count = current_luminescence_count;
            LogUtils.e("firstSendMsg","33="+current_luminescence_upload_stop_count);

        }
        int currentItem = viewPager_mode.getCurrentItem();
        if (currentItem == 0) {//Stack
            stackFragment.SetResponseStatus(uploadWorkingInfo);
        } else if (currentItem == 1) {//Shr
            shrFragment.SetResponseStatus(uploadWorkingInfo);
        } else if (currentItem == 2) {//Hr
        }

        if (AppConfig.current_count >= 0) {//首次获取到的总次数
            if (uploadWorkingInfo.getToalCount() >= AppConfig.current_count){//现在收到的总次数大于等于首次获得的总次数
                if (!clear_count){
                    current_luminescence_count = uploadWorkingInfo.getToalCount() - AppConfig.current_count;
                    if (current_luminescence_count >= 0) {
                        tv_current.setText(getResources().getString(R.string.current)+":" + current_luminescence_count);
                        if (current_luminescence_save_stop_count == 0){
                            current_luminescence_save_stop_count = current_luminescence_count;
                        }
                        if (current_luminescence_auto_save_stop_count == 0){
                            current_luminescence_auto_save_stop_count = current_luminescence_count;
                        }
                        if (current_luminescence_upload_stop_count == 0){
                            current_luminescence_upload_stop_count = current_luminescence_count;
                        }
                    }
                }else {
                    int clearCount = current_luminescence_count + uploadWorkingInfo.getToalCount();
                    tv_current.setText(getResources().getString(R.string.current)+":" + clearCount);
                    clear_count = true;
                }
            }else {
                int clearCount = current_luminescence_count + uploadWorkingInfo.getToalCount();
                tv_current.setText(getResources().getString(R.string.current)+":" + clearCount);
                clear_count = true;
            }

        }
        tv_total.setText(getResources().getString(R.string.total)+":" + uploadWorkingInfo.getToalCount());

    }


    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(SetFluenceBean setFluenceBean) {//脱毛倒计时结束
        SetFluenceMessage(setFluenceBean.getFluence());
    }


    @Override
    protected void onPause() {//失去焦点 停止工作
        super.onPause();
        stopWork();
        LogUtils.e("声音onPause");
        AppConfig.defaultBody = 0;
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

    public void setSendUserValues(UploadWorkingInfo uploadWorkingInfo) {
        if (uploadWorkingInfo.getWorkingStatus() == 0) {//stby
            flag_number++;
            if (flag_number == 2){
                setUserValueData();
            }
        } else if (uploadWorkingInfo.getWorkingStatus() == 1) {//reading

        } else if (uploadWorkingInfo.getWorkingStatus() == 2) {//working
            flag_number = 1;
        }
    }
    public void setUserValueData(){
        if (commonBean.getTel() == null || commonBean.getTel().equals("") || commonBean.getTel().equals("0")) {//自由人不记录数据
            return;
        }
        if (setWorkingStatusDb == null) {
            ToastUtil.showToast(OtherActivity.this, getResources().getString(R.string.work_no));
            return;
        }
        UserValue userValue1 = new UserValue();
        userValue1.setTel(commonBean.getTel());
        userValue1.setGender(commonBean.getGender() + "");
        userValue1.setMode(setWorkingStatusDb.getWorkingModel() + "");
        userValue1.setSkinType(commonBean.getSink() + "");
        userValue1.setBodyType(commonBean.getBody() + "");
        if (uploadWorkingInfo_energy != null){
            userValue1.setEnergy(uploadWorkingInfo_energy.getTotalEnergy() + "");
        }
        userValue1.setFrequency(setWorkingStatusDb.getFrequency() + "");
        userValue1.setFluence(setWorkingStatusDb.getFluence() + "");
        String s = tv_current.getText().toString();
        int current_number = Integer.parseInt(s);
        int work_count= current_number - current_luminescence_auto_save_stop_count;
        userValue1.setWorkCount(work_count+ "");
        List<User> user = UserDao.getInstance().getUser(commonBean.getTel());
        if (user != null && user.size() > 0) {
            userValue1.setUserId(user.get(0).get_id());
            userValue1.setUserName(user.get(0).getName());
            userValue1.setAge(user.get(0).getAge());
        }
        UserValueDao.getInstance().createUserValue(userValue1);
        MyApplication.instance().sendUserValueMessage(userValue1);
        current_luminescence_auto_save_stop_count = current_luminescence_count;
    }

    public void setSendCountValues(UploadWorkingInfo uploadWorkingInfo) {
        if (uploadWorkingInfo.getWorkingStatus() == 0) {//stby
            flag_count++;
            if (flag_count == 2){
                LogUtils.e("firstSendMsg","44="+current_luminescence_count);
                String s = tv_current.getText().toString();
                int current_number = Integer.parseInt(s);
                int work_upload_count= current_number - current_luminescence_upload_stop_count;
                LogUtils.e("firstSendMsg","44="+work_upload_count);
//            if (AppConfig.useLimitedFlag == 1){//开启限制且限制的是次数，上报服务端发数次数
//                if (AppConfig.useLimitedType.equals("count")){
//                    MyApplication.instance().sendCountMessage(work_upload_count);
//                }
//            }
                if (work_upload_count > 0){
                    MyApplication.instance().sendCountMessage(work_upload_count);
                }
                current_luminescence_upload_stop_count = current_luminescence_count;
            }
        } else if (uploadWorkingInfo.getWorkingStatus() == 1) {//reading

        } else if (uploadWorkingInfo.getWorkingStatus() == 2) {//working
            flag_count = 1;
        }
    }

}