package com.ss.apidemo.ui.mode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import cn.hutool.core.lang.Console;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetWorkingStatus;
import com.example.protocol.frame.data.UploadWorkingInfo;
import com.example.protocol.utils.ParserUtil;
import com.king.view.arcseekbar.ArcSeekBar;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.bean.AutoSkinBean;
import com.ss.apidemo.bean.CommonBean;
import com.ss.apidemo.bean.ExpertModeBean;
import com.ss.apidemo.bean.FourHundredModeBean;
import com.ss.apidemo.bean.HundredModeBean;
import com.ss.apidemo.bean.SendTimeBean;
import com.ss.apidemo.bean.SetFluenceBean;
import com.ss.apidemo.bean.ShrModeHzOrFluenceBean;
import com.ss.apidemo.bean.SmartModeBean;
import com.ss.apidemo.bean.ThirtyModeBean;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.db.dao.UserValueDao;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.ui.OtherActivity;
import com.ss.apidemo.ui.SplashActivity;
import com.ss.apidemo.ui.UserSettingActivity;
import com.ss.apidemo.utils.AutoSkinTypeUtils;
import com.ss.apidemo.utils.DateUtil;
import com.ss.apidemo.utils.DeviceInfoUtil;
import com.ss.apidemo.utils.ExpertModeUtils;
import com.ss.apidemo.utils.FourHundredModeUtils;
import com.ss.apidemo.utils.HundredModeUtils;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ShrModeHzOrFluenceUtils;
import com.ss.apidemo.utils.SmartModeUtils;
import com.ss.apidemo.utils.ThirtyModeUtils;
import com.ss.apidemo.utils.ToastUtil;

public class WorkSelectOneActivity extends BaseActivity {
    public int AUTOMODE = 6;
    public int THIRTYMODE = 7;
    public int HUNDREDMODE = 8;
    public int FOURHUNDREDMODE = 9;
    private int seekbar_count = 4;//因为弧形进度条不能设置最小值，默认最小值为0，而手具的最小值为4,

    private TextView fan_1, fan_2, fan_3, fan_4, fan_5;
    private ImageView iv_auto, iv_30, iv_100, iv_400, iv_l, iv_m, iv_f;
    private TextView tv_fluence, tv_hz, tv_temperature, tv_flow, tv_total, tv_current, tv_id, tv_exact,tv_raedy;
    private TextView tv_min, tv_max;
    private LinearLayout ll_raedy;
    private ArcSeekBar arcSeekBar;
    private int fan_flag = 0;
    private int mode_type;
    private int skin_type;
    private int body_type;
    public String tel;//传递过来的用户手机号

    public int handgearType;//手具类型
    private SmartModeUtils smartModeUtils;
    private AutoSkinTypeUtils autoSkinTypeUtils;
    private ShrModeHzOrFluenceUtils shrModeHzOrFluenceUtils;
    private ShrModeHzOrFluenceBean shrModeHzOrFluenceBean;
    private boolean isHidden;

    private SmartModeBean smartModeBean;//只包含 30 100 400模式的默认值
    private AutoSkinBean autoSkinBean;
    private int current_fluence_progress = 0;
    private int current_fluence_min = 0;
    private int current_fluence_max = 0;
    private int current_hz_min = 0;
    private int current_hz_max = 0;
    private int current_hz_progress = 1;
    private int current_mode_type = 0;
    private ThirtyModeBean thirtyModeBean;
    private HundredModeBean hundredModeBean;
    private FourHundredModeBean fourHundredModeBean;
    int l_range_auto = 0;
    int m_range_auto = 3;//方便第一次默认为4
    int f_range_auto = 5;
    int l_range_30 = 0;
    int m_range_30 = 2;//方便第一次默认为4
    int f_range_30 = 5;
    int l_range_100 = 0;
    int m_range_100 = 2;//方便第一次默认为4
    int f_range_100 = 5;
    int l_range_400 = 0;
    int m_range_400 = 2;//方便第一次默认为4
    int f_range_400 = 5;
    SetWorkingStatus setWorkingStatusDb = null;
    private int current_status = 0;
    private LinearLayout ll_all;
    private int current_luminescence_count;
    private int current_luminescence_save_stop_count;
    private int current_luminescence_upload_stop_count;
    private boolean clear_count = false;
    private int current_count = 0;//首次work 触发声音
    UploadWorkingInfo uploadWorkingInfo_energy;



    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            sendFluence();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_select_one);
        initView();
        initIntentData();
        initData();
    }

    private void initView() {
        fan_1 = findViewById(R.id.fan_1);
        fan_2 = findViewById(R.id.fan_2);
        fan_3 = findViewById(R.id.fan_3);
        fan_4 = findViewById(R.id.fan_4);
        fan_5 = findViewById(R.id.fan_5);
        arcSeekBar = findViewById(R.id.arcSeekBar);
        tv_fluence = findViewById(R.id.tv_fluence);
        tv_hz = findViewById(R.id.tv_hz);
        tv_min = findViewById(R.id.tv_min);
        tv_max = findViewById(R.id.tv_max);
        iv_auto = findViewById(R.id.iv_auto);
        iv_30 = findViewById(R.id.iv_30);
        iv_100 = findViewById(R.id.iv_100);
        iv_400 = findViewById(R.id.iv_400);

        iv_l = findViewById(R.id.iv_l);
        iv_m = findViewById(R.id.iv_m);
        iv_f = findViewById(R.id.iv_f);

        tv_temperature = findViewById(R.id.tv_temperature);
        tv_flow = findViewById(R.id.tv_flow);
        tv_total = findViewById(R.id.tv_total);
        tv_current = findViewById(R.id.tv_current);
        tv_id = findViewById(R.id.tv_id);
        tv_exact = findViewById(R.id.tv_exact);
        tv_raedy = findViewById(R.id.tv_raedy);
        ll_raedy = findViewById(R.id.ll_raedy);
        ll_all = findViewById(R.id.ll_all);

    }

    private void initIntentData() {
        if (AppConfig.handgearSelect == 1) {//单手==左手
            handgearType = SharedPrefsUtil.getIntValue(AppConfig.HAND_LEFT, 1);
        } else if (AppConfig.handgearSelect == 0) {//双手 =  左右手
            handgearType = SharedPrefsUtil.getIntValue(AppConfig.HAND_RIGHT, 1);
        }
        Intent intent = getIntent();
        if (intent != null) {
            mode_type = intent.getIntExtra("mode_type", 0);
            if (mode_type == 1) {//1 专家  2 智能
                mode_type = 1;
            } else if (mode_type == 2) {
                mode_type = 2;
                skin_type = intent.getIntExtra("skin_type", 0);
                body_type = intent.getIntExtra("body_type", 0);
                tel = intent.getStringExtra("tel");


            }
        }
    }

    private void initData() {
        String mac = DeviceInfoUtil.getMac();
        tv_id.setText(mac);
        int booleanValue = SharedPrefsUtil.getIntValue(AppConfig.HANDGEAR, 0);
        if (booleanValue == 0) {//单手==左手
            tv_exact.setText(getResources().getString(R.string.trio));
        } else if (booleanValue == 1) {//双手 =  左右手
            if (AppConfig.handgearSelect == 1){
                tv_exact.setText(getResources().getString(R.string.trio));
            }else {
                tv_exact.setText(getResources().getString(R.string.trio_3d));
            }
        }
        ExpertModeUtils expertModeUtils = new ExpertModeUtils();
        autoSkinTypeUtils = new AutoSkinTypeUtils();
        if (mode_type == 1) {//1 专家  2 智能
            /*
             * 这里取到的值就是expert模式的默认值，因为expert下所有手具的默认值和smart下auto的肤色1和身体6及
             * 任何手具都一致，方便统一，所以这么取值了
             * */
            autoSkinBean = autoSkinTypeUtils.modeType(1, handgearType, 6);

        } else if (mode_type == 2) {
            autoSkinBean = autoSkinTypeUtils.modeType(skin_type, handgearType, body_type);
        }
        shrModeHzOrFluenceUtils = new ShrModeHzOrFluenceUtils();
        shrModeHzOrFluenceBean = shrModeHzOrFluenceUtils.modeType(handgearType, autoSkinBean.getHzProposal());
        smartModeUtils = new SmartModeUtils();
        ThirtyModeUtils thirtyModeUtils = new ThirtyModeUtils();
        thirtyModeBean = thirtyModeUtils.modeType(handgearType);
        HundredModeUtils hundredModeUtils = new HundredModeUtils();
        hundredModeBean = hundredModeUtils.modeType(handgearType);
        FourHundredModeUtils fourHundredModeUtils = new FourHundredModeUtils();
        fourHundredModeBean = fourHundredModeUtils.modeType(handgearType);

        //默认首次进来选中auto
        View defaultView = new View(this);
        defaultView.setId(R.id.ll_auto);
        modeClick(defaultView);


        arcSeekBar.setOnChangeListener(new ArcSeekBar.OnChangeListener() {
            @Override
            public void onStartTrackingTouch(boolean isCanDrag) {

            }

            @Override
            public void onProgressChanged(float progress, float max, boolean fromUser) {
                current_fluence_progress = (int)progress;
                setCount();
                if (isHidden) {
                    PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                }
            }

            @Override
            public void onStopTrackingTouch(boolean isCanDrag) {
                if (isHidden){
                    sendFluence();
                }
            }

            @Override
            public void onSingleTapUp() {

            }
        });
        ll_raedy.setBackground(getResources().getDrawable(R.drawable.other_bt_stby_rounded_corners));
        tv_raedy.setText(getResources().getString(R.string.stby));
        ll_raedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                LogUtils.e("声音1");
                if (current_status == 0) {
                    current_status = 1;
                    ll_all.setVisibility(View.VISIBLE);
//                    tv_next.setBackground(getResources().getDrawable(R.drawable.other_bt_read_rounded_corners));
//                    tv_next.setText(getResources().getString(R.string.ready));
                    //准备 下发数据
                    setWorkingStatusDb = setValue();
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
    }

    @Override
    public void onResume() {
        super.onResume();
        isHidden = true;
        handler.sendMessageDelayed(new Message(),500);
        LogUtils.e("=====获取焦点 shr");

    }

    public void setCount() {
        int show_fluence = current_fluence_progress + seekbar_count;
        tv_fluence.setText(show_fluence + "");
    }

    private void defaultData() {

    }

    public void tabClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        switch (view.getId()) {
            case R.id.iv_save:
                if (mode_type == 1){//专家模式
                    return;
                }
                if (current_status == 0 || current_status == 2){//stby 和 work 不允许保存记录
                    return;
                }
                if (tel == null || tel.equals("") || tel.equals("0")) {//自由人不记录数据
                    return;
                }
                if (setWorkingStatusDb == null) {
                    ToastUtil.showToast(WorkSelectOneActivity.this, getResources().getString(R.string.work_no));
                    return;
                }
                UserValue userValue1 = new UserValue();
                userValue1.setTel(tel);
//                userValue1.setGender(commonBean.getGender() + "");
                userValue1.setMode(setWorkingStatusDb.getWorkingModel() + "");
                userValue1.setSkinType(skin_type + "");
                userValue1.setBodyType(body_type + "");
                if (uploadWorkingInfo_energy != null){
                    userValue1.setEnergy(uploadWorkingInfo_energy.getTotalEnergy() + "");
                }
                userValue1.setFrequency(setWorkingStatusDb.getFrequency() + "");
                userValue1.setFluence(setWorkingStatusDb.getFluence() + "");

                int work_count= current_luminescence_count - current_luminescence_save_stop_count;
                userValue1.setWorkCount(work_count+ "");
                UserValueDao.getInstance().createUserValue(userValue1);
                current_luminescence_save_stop_count = current_luminescence_count;
                HintDialog dialog = new HintDialog(WorkSelectOneActivity.this);
                dialog.loadDialog(WorkSelectOneActivity.this, new HintDialog.OnClickIsConfirm() {
                    @Override
                    public void OnClickIsConfirmListener() {//确定
                    }

                }, getResources().getString(R.string.user_information));
                break;
            case R.id.iv_setting:
                startActivity(new Intent(WorkSelectOneActivity.this, UserSettingActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_main:
                startActivity(new Intent(WorkSelectOneActivity.this, SplashActivity.class));
                break;
        }
    }

    public void rangeFLuenceClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        switch (view.getId()) {
            case R.id.rl_reduce:
                if (current_fluence_progress > current_fluence_min) {
                    current_fluence_progress--;
                    arcSeekBar.setProgress(current_fluence_progress);
                    if (isHidden){
                        sendFluence();
                    }
                }
//                arcSeekBar
                break;
            case R.id.rl_add:
                if (current_fluence_progress < current_fluence_max) {
                    current_fluence_progress++;
                    arcSeekBar.setProgress(current_fluence_progress);
                    if (isHidden){
                        sendFluence();
                    }
                }
                break;
        }
    }

    public void modeClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuModeState();
        switch (view.getId()) {
            case R.id.ll_auto:
                current_mode_type = AUTOMODE;
                iv_auto.setVisibility(View.VISIBLE);
                getData(AUTOMODE);
                break;
            case R.id.ll_30:
                current_mode_type = THIRTYMODE;
                iv_30.setVisibility(View.VISIBLE);
                getData(THIRTYMODE);
                break;
            case R.id.ll_100:
                current_mode_type = HUNDREDMODE;
                iv_100.setVisibility(View.VISIBLE);
                getData(HUNDREDMODE);
                break;
            case R.id.ll_400:
                current_mode_type = FOURHUNDREDMODE;
                iv_400.setVisibility(View.VISIBLE);
                getData(FOURHUNDREDMODE);
                break;
        }
    }

    private void getData(int type) {
        if (type == AUTOMODE) {
            l_range_auto = 0;
            m_range_auto = 3;//方便第一次默认为4
            f_range_auto = 5;
            tv_fluence.setText(autoSkinBean.getFluenceProposal() + "");
            tv_hz.setText(autoSkinBean.getHzProposal() + "");
            current_fluence_progress = autoSkinBean.getFluenceProposal()-seekbar_count;
            current_fluence_min = shrModeHzOrFluenceBean.getFluenceMin()-seekbar_count;
            current_fluence_max = shrModeHzOrFluenceBean.getFluenceMax()-seekbar_count;
            current_hz_progress = shrModeHzOrFluenceBean.getHz();
            current_hz_min = shrModeHzOrFluenceBean.getHandgearHzMin();
            current_hz_max = shrModeHzOrFluenceBean.getHandgearHzMax();
            tv_min.setText(shrModeHzOrFluenceBean.getFluenceMin()+"");
            tv_max.setText(shrModeHzOrFluenceBean.getFluenceMax()+"");
            setBright(current_hz_progress);
        } else if (type == THIRTYMODE) {
            l_range_30 = 0;
            m_range_30 = 2;//方便第一次默认为4
            f_range_30 = 5;
            smartModeBean = smartModeUtils.modeType(type);
            tv_fluence.setText(smartModeBean.getFluenceProposal() + "");
            tv_hz.setText(smartModeBean.getHzProposal() + "");
            current_fluence_progress = smartModeBean.getFluenceProposal()-seekbar_count;

            current_fluence_min = thirtyModeBean.getFluenceMin()-seekbar_count;
            current_fluence_max = thirtyModeBean.getFluenceMax()-seekbar_count;
            current_hz_progress = smartModeBean.getHzProposal();
            current_hz_min = thirtyModeBean.getHzMin();
            current_hz_max = thirtyModeBean.getHzMax();
            tv_min.setText(thirtyModeBean.getFluenceMin()+"");
            tv_max.setText(thirtyModeBean.getFluenceMax()+"");
            setBright(current_hz_progress);
        } else if (type == HUNDREDMODE) {
            l_range_100 = 0;
            m_range_100 = 2;//方便第一次默认为4
            f_range_100 = 5;
            smartModeBean = smartModeUtils.modeType(type);
            tv_fluence.setText(smartModeBean.getFluenceProposal() + "");
            tv_hz.setText(smartModeBean.getHzProposal() + "");
            current_fluence_progress = smartModeBean.getFluenceProposal()-seekbar_count;

            current_fluence_min = hundredModeBean.getFluenceMin()-seekbar_count;
            current_fluence_max = hundredModeBean.getFluenceMax()-seekbar_count;
            current_hz_progress = smartModeBean.getHzProposal();
            current_hz_min = hundredModeBean.getHzMin();
            current_hz_max = hundredModeBean.getHzMax();
            tv_min.setText(hundredModeBean.getFluenceMin()+"");
            tv_max.setText(hundredModeBean.getFluenceMax()+"");
            setBright(current_hz_progress);
        } else if (type == THIRTYMODE) {
            l_range_400 = 0;
            m_range_400 = 2;//方便第一次默认为4
            f_range_400 = 5;
            smartModeBean = smartModeUtils.modeType(type);
            tv_fluence.setText(smartModeBean.getFluenceProposal() + "");
            tv_hz.setText(smartModeBean.getHzProposal() + "");
            current_fluence_progress = smartModeBean.getFluenceProposal()-seekbar_count;

            current_fluence_min = fourHundredModeBean.getFluenceMin()-seekbar_count;
            current_fluence_max = fourHundredModeBean.getFluenceMax()-seekbar_count;
            current_hz_progress = smartModeBean.getHzProposal();
            current_hz_min = fourHundredModeBean.getHzMin();
            current_hz_max = fourHundredModeBean.getHzMax();
            tv_min.setText(fourHundredModeBean.getFluenceMin()+"");
            tv_max.setText(fourHundredModeBean.getFluenceMax()+"");
            setBright(current_hz_progress);
        }
        arcSeekBar.setMax(current_fluence_max);
        arcSeekBar.setProgress(current_fluence_progress);

    }
    /*
    * 设置l m f 那个灯亮  根据hz的范围
    * */
    public void setBright(int currentHz){
        if (currentHz>=1 && currentHz<=2){
            View defaultView = new View(this);
            defaultView.setId(R.id.ll_l);
            rangeClick(defaultView);
        }else if (currentHz>=3 && currentHz<=5){
            View defaultView = new View(this);
            defaultView.setId(R.id.ll_m);
            rangeClick(defaultView);
        }else if (currentHz>=6 && currentHz<=11){//对应关系是 11 对应的20Hz
            View defaultView = new View(this);
            defaultView.setId(R.id.ll_f);
            rangeClick(defaultView);
        }
    }


    private void resetMenuModeState() {
        iv_auto.setVisibility(View.INVISIBLE);
        iv_30.setVisibility(View.INVISIBLE);
        iv_100.setVisibility(View.INVISIBLE);
        iv_400.setVisibility(View.INVISIBLE);
    }

    public void rangeClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuRangeState();
        switch (view.getId()) {
            case R.id.ll_l:
                setRangeData(R.id.ll_l);
                iv_l.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_m:
                setRangeData(R.id.ll_m);
                iv_m.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_f:
                setRangeData(R.id.ll_f);
                iv_f.setVisibility(View.VISIBLE);
                break;

        }
    }

    private void setRangeData(int range) {
        if (current_mode_type == AUTOMODE){//auto
            if (range == R.id.ll_l){
                if (current_hz_max > 2){
                    l_range_auto++;
                    if (l_range_auto == 1){
                        setAuto(1);
                    }else if (l_range_auto == 2){
                        setAuto(2);
                        l_range_auto = 0;
                    }
                }
            }else if (range == R.id.ll_m){
                if (current_hz_max > 5){
                    m_range_auto++;
                    if (m_range_auto == 3){
                        setAuto(3);
                    }else if (m_range_auto == 4){
                        setAuto(4);
                    }else if (m_range_auto == 5){
                        setAuto(5);
                        m_range_auto = 2;
                    }
                }
            }else if (range == R.id.ll_f){
                if (current_hz_max >= 10){
                    f_range_auto++;
                    if (f_range_auto == 6){
                        setAuto(6);
                    }else if (f_range_auto == 7){
                        setAuto(7);
                    }else if (f_range_auto == 8){
                        setAuto(8);
                    }else if (f_range_auto == 9){
                        setAuto(9);
                    }else if (f_range_auto == 10){
                        setAuto(10);
                        if (current_hz_max > 10){//说明有11 对应的hz20
                        }else {
                            f_range_auto = 5;
                        }
                    } else if (f_range_auto == 11){
                        setAuto(11);
                        f_range_auto = 5;
                    }
                }
            }

        }
        if (current_mode_type == THIRTYMODE) {//30
            /*
             * 1 QB-6-10
             * 2 QB-6-16
             * 3 QB-6-20
             * 4 QB-2-10
             * 5 QB-2-16
             * 6 QB-2-24
             * 7 QB-4-24
             * 8 QB-*-30
             * 9 QB-6-200
             * 10 QB-6-40
             *
             * */
            if (handgearType == 3  ||  handgearType == 7 ||  handgearType == 8 ||  handgearType == 9){//最大hz 8
                if (range == R.id.ll_l){
                    l_range_30++;
                    if (l_range_30 == 1){
                        setThirty(1);
                    }else if (l_range_30 == 2){
                        setThirty(2);
                        l_range_30 = 0;
                    }
                }else if (range == R.id.ll_m){
                    m_range_30++;
                    if (m_range_30 == 3){
                        setThirty(3);
                    }else if (m_range_30 == 4){
                        setThirty(4);
                    }else if (m_range_30 == 5){
                        setThirty(5);
                        m_range_30 = 2;
                    }

                }else if (range == R.id.ll_f){
                    f_range_30++;
                    if (f_range_auto == 6){
                        setThirty(6);
                    }else if (f_range_auto == 7){
                        setThirty(7);
                    }else if (f_range_auto == 8){
                        setThirty(8);
                        f_range_30 = 5;
                    }
                }
            }
            if (handgearType == 6){//最大hz 5
                if (range == R.id.ll_l){
                    l_range_30++;
                    if (l_range_30 == 1){
                        setThirty(1);
                    }else if (l_range_30 == 2){
                        setThirty(2);
                        l_range_30 = 0;
                    }
                }else if (range == R.id.ll_m){
                    m_range_30++;
                    if (m_range_30 == 3){
                        setThirty(3);
                    }else if (m_range_30 == 4){
                        setThirty(4);
                    }else if (m_range_30 == 5){
                        setThirty(5);
                        m_range_30 = 2;
                    }

                }else if (range == R.id.ll_f){
                    return;
                }
            }
            if (handgearType == 10){//最大hz 4
                if (range == R.id.ll_l){
                    l_range_30++;
                    if (l_range_30 == 1){
                        setThirty(1);
                    }else if (l_range_30 == 2){
                        setThirty(2);
                        l_range_30 = 0;
                    }
                }else if (range == R.id.ll_m){
                    m_range_30++;
                    if (m_range_30 == 3){
                        setThirty(3);
                    }else if (m_range_30 == 4){
                        setThirty(4);
                        m_range_30 = 2;
                    }
                }else if (range == R.id.ll_f){
                    return;
                }
            }
            if (handgearType == 1  ||  handgearType == 4 ||  handgearType == 2 ||  handgearType == 5){//最大hz 10
                if (range == R.id.ll_l){
                    l_range_30++;
                    if (l_range_30 == 1){
                        setThirty(1);
                    }else if (l_range_30 == 2){
                        setThirty(2);
                        l_range_30 = 0;
                    }
                }else if (range == R.id.ll_m){
                    m_range_30++;
                    if (m_range_30 == 3){
                        setThirty(3);
                    }else if (m_range_30 == 4){
                        setThirty(4);
                    }else if (m_range_30 == 5){
                        setThirty(5);
                        m_range_30 = 2;
                    }

                }else if (range == R.id.ll_f){
                    f_range_30++;
                    if (f_range_30 == 6){
                        setThirty(6);
                    }else if (f_range_30 == 7){
                        setThirty(7);
                    }else if (f_range_30 == 8){
                        setThirty(8);
                    } else if (f_range_30 == 9){
                        setThirty(9);
                    } else if (f_range_30 == 10){
                        setThirty(10);
                        f_range_30 = 5;
                    }
                }
            }
        }
        if (current_mode_type == HUNDREDMODE) {//100
            /*
             * 1 QB-6-10
             * 2 QB-6-16
             * 3 QB-6-20
             * 4 QB-2-10
             * 5 QB-2-16
             * 6 QB-2-24
             * 7 QB-4-24
             * 8 QB-*-30
             * 9 QB-6-200
             * 10 QB-6-40
             *
             * */
            if (handgearType == 3  ||  handgearType == 7 ||  handgearType == 8 ||  handgearType == 9
                    ||  handgearType == 6||  handgearType == 10){//最大hz 1
                if (range == R.id.ll_l){
                    l_range_100++;
                    if (l_range_100 == 1){
                        setHundred(1);
                        l_range_100 = 0;
                    }
                }else if (range == R.id.ll_m){
                    return;

                }else if (range == R.id.ll_f){
                    return;
                }
            }
            if (handgearType == 1  ||  handgearType == 4 ||  handgearType == 2 ||  handgearType == 5){//最大hz 3
                if (range == R.id.ll_l){
                    l_range_100++;
                    if (l_range_100 == 1){
                        setHundred(1);
                    }else if (l_range_100 == 2){
                        setHundred(2);
                        l_range_100 = 0;
                    }
                }else if (range == R.id.ll_m){
                    m_range_100++;
                    if (m_range_100 == 3){
                        setHundred(3);
                        m_range_100 = 2;
                    }

                }else if (range == R.id.ll_f){
                   return;
                }
            }
        }
        if (current_mode_type == FOURHUNDREDMODE) {//400
            /*
             * 1 QB-6-10
             * 2 QB-6-16
             * 3 QB-6-20
             * 4 QB-2-10
             * 5 QB-2-16
             * 6 QB-2-24
             * 7 QB-4-24
             * 8 QB-*-30
             * 9 QB-6-200
             * 10 QB-6-40
             *
             * */
            if (handgearType == 3  ||  handgearType == 7 ||  handgearType == 8 ||  handgearType == 9
                    || handgearType == 6 || handgearType == 10
                    || handgearType == 1 || handgearType == 4
                    || handgearType == 2 || handgearType == 5){//最大hz 1
                if (range == R.id.ll_l){
                    l_range_400++;
                    if (l_range_400 == 1){
                        setFourHundred(1);
                        l_range_400 = 0;
                    }
                }else if (range == R.id.ll_m){
                    return;

                }else if (range == R.id.ll_f){
                    return;
                }
            }

        }
    }

    public void setAuto(int hz){
        shrModeHzOrFluenceBean = shrModeHzOrFluenceUtils.modeType(handgearType, hz);
        current_fluence_min = shrModeHzOrFluenceBean.getFluenceMin()-seekbar_count;
        current_fluence_max = shrModeHzOrFluenceBean.getFluenceMax()-seekbar_count;
        tv_min.setText(shrModeHzOrFluenceBean.getFluenceMin()+"");
        tv_max.setText(shrModeHzOrFluenceBean.getFluenceMax()+"");
        if (arcSeekBar.getMax()>=current_fluence_max){
            arcSeekBar.setMax(current_fluence_max);
            arcSeekBar.setProgress(current_fluence_max);
        }

        if (hz == 11){//部分手具有20hz,在实际数据中对应的数值是11，所以单独处理
            tv_hz.setText("20");
        }else {
            tv_hz.setText(hz+"");
        }
        setCount();
    }

    public void setThirty(int hz){
        current_fluence_min = thirtyModeBean.getFluenceMin()-seekbar_count;
        current_fluence_max = thirtyModeBean.getFluenceMax()-seekbar_count;
        tv_min.setText(thirtyModeBean.getFluenceMin()+"");
        tv_max.setText(thirtyModeBean.getFluenceMax()+"");
        if (arcSeekBar.getMax()>=current_fluence_max){
            arcSeekBar.setMax(current_fluence_max);
            arcSeekBar.setProgress(current_fluence_max);
        }
        if (hz == 11){//部分手具有20hz,在实际数据中对应的数值是11，所以单独处理
            tv_hz.setText("20");
        }else {
            tv_hz.setText(hz+"");
        }
        setCount();
    }

    public void setHundred(int hz){
        current_fluence_min = hundredModeBean.getFluenceMin()-seekbar_count;
        current_fluence_max = hundredModeBean.getFluenceMax()-seekbar_count;
        tv_min.setText(hundredModeBean.getFluenceMin()+"");
        tv_max.setText(hundredModeBean.getFluenceMax()+"");
        if (arcSeekBar.getMax() >= current_fluence_max){
            arcSeekBar.setMax(current_fluence_max);
            arcSeekBar.setProgress(current_fluence_max);
        }

        if (hz == 11){//部分手具有20hz,在实际数据中对应的数值是11，所以单独处理
            tv_hz.setText("20");
        }else {
            tv_hz.setText(hz+"");
        }
        setCount();
    }

    public void setFourHundred(int hz){
        current_fluence_min = fourHundredModeBean.getFluenceMin()-seekbar_count;
        current_fluence_max = fourHundredModeBean.getFluenceMax()-seekbar_count;
        tv_min.setText(fourHundredModeBean.getFluenceMin()+"");
        tv_max.setText(fourHundredModeBean.getFluenceMax()+"");
        if (arcSeekBar.getMax()>=current_fluence_max){
            arcSeekBar.setMax(current_fluence_max);
            arcSeekBar.setProgress(current_fluence_max);
        }
        if (hz == 11){//部分手具有20hz,在实际数据中对应的数值是11，所以单独处理
            tv_hz.setText("20");
        }else {
            tv_hz.setText(hz+"");
        }
        setCount();
    }

    private void resetMenuRangeState() {
        iv_l.setVisibility(View.INVISIBLE);
        iv_m.setVisibility(View.INVISIBLE);
        iv_f.setVisibility(View.INVISIBLE);
    }

    public void selectClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        resetMenuState();
        switch (view.getId()) {
            case R.id.iv_fan:
                fan_flag++;
                if (fan_flag == 1) {
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                }
                if (fan_flag == 2) {
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                    fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                }
                if (fan_flag == 3) {
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                    fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_3.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                }
                if (fan_flag == 4) {
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                    fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_3.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_4.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                }
                if (fan_flag == 5) {
                    fan_flag = 0;
                    fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_select_corners));
                    fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_3.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_4.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_select_corners));
                    fan_5.setBackground(getResources().getDrawable(R.drawable.work_one_fan_3_select_corners));
                }
                break;
        }
    }

    private void resetMenuState() {
        fan_1.setBackground(getResources().getDrawable(R.drawable.work_one_fan_1_unselect_corners));
        fan_2.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_unselect_corners));
        fan_3.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_unselect_corners));
        fan_4.setBackground(getResources().getDrawable(R.drawable.work_one_fan_2_unselect_corners));
        fan_5.setBackground(getResources().getDrawable(R.drawable.work_one_fan_3_unselect_corners));

    }

    public void sendFluence(){
        //值改变 发送报文到下位机
        SetFluenceBean setFluenceBean = new SetFluenceBean();
        setFluenceBean.setFluence(current_fluence_progress);
        LogUtils.e("下发单脉冲hr"+current_fluence_progress);
        EventBus.getDefault().post(setFluenceBean);
    }

    public SetWorkingStatus setValue(){
        int current_handgear = 0;
        if (AppConfig.handgearSelect == 1) {//单手==左手
            current_handgear = SharedPrefsUtil.getIntValue(AppConfig.HAND_LEFT, 1);
        } else if (AppConfig.handgearSelect == 0) {//双手 =  左右手
            current_handgear = SharedPrefsUtil.getIntValue(AppConfig.HAND_RIGHT, 1);
        }
        String fluenceString = tv_fluence.getText().toString();
        int fluenceInt = Integer.parseInt(fluenceString);
        String frequencyString = tv_hz.getText().toString();
        int frequencyInt = Integer.parseInt(frequencyString);
        SetWorkingStatus setWorkingStatus = new SetWorkingStatus();
        setWorkingStatus.setWorkingModel(6); // 工作模式  1 2 3 4 5 shr 6 hr
        setWorkingStatus.setFluence(fluenceInt); // 单脉冲能量
        setWorkingStatus.setFrequency(frequencyInt); // 频率
        setWorkingStatus.setTotalEnergy(0);// 总能量
        setWorkingStatus.setWorkingTime(0); // 工作时间  秒 该模式没有时间
        setWorkingStatus.setQbConfig(current_handgear); // 手具选择 1-7
        setWorkingStatus.setChangeQbPortFlag(AppConfig.handgearSelect); // 是否切换手具端口 1 left 0 right
        setWorkingStatus.setWorkingStatus(1);// 工作状态 0  stby和停止  1 reading 2 working
        return setWorkingStatus;
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(UploadWorkingInfo uploadWorkingInfo) {//結果集
        if (uploadWorkingInfo.getWorkingStatus() == 0) {//stby
            current_status = 0;
            ll_all.setVisibility(View.GONE);
            LogUtils.e("stby");
            ll_raedy.setBackground(getResources().getDrawable(R.drawable.other_bt_stby_rounded_corners));
            tv_raedy.setText(getResources().getString(R.string.stby));
        } else if (uploadWorkingInfo.getWorkingStatus() == 1) {//reading
            current_status = 1;
            ll_all.setVisibility(View.VISIBLE);
            LogUtils.e("reading");
            ll_raedy.setBackground(getResources().getDrawable(R.drawable.other_bt_read_rounded_corners));
            tv_raedy.setText(getResources().getString(R.string.ready));
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
            LogUtils.e("working");
            ll_raedy.setBackground(getResources().getDrawable(R.drawable.other_bt_working_rounded_corners));
            tv_raedy.setText(getResources().getString(R.string.working));
            LogUtils.e("firstSendMsg","11="+current_luminescence_count+"22 ="+current_luminescence_upload_stop_count);
            int work_upload_count= current_luminescence_count - current_luminescence_upload_stop_count;
//            if (AppConfig.useLimitedFlag == 1){//开启限制且限制的是次数，上报服务端发数次数
//                if (AppConfig.useLimitedType.equals("count")){
//                    MyApplication.instance().sendCountMessage(work_upload_count);
//                }
//            }
            if (work_upload_count > 0){
                MyApplication.instance().sendCountMessage(work_upload_count);
            }
            current_luminescence_upload_stop_count = current_luminescence_count;
            LogUtils.e("firstSendMsg","33="+current_luminescence_upload_stop_count);

        }

        if (AppConfig.current_count >= 0) {//首次获取到的总次数
            if (uploadWorkingInfo.getToalCount() >= AppConfig.current_count){//现在收到的总次数大于等于首次获得的总次数
                if (!clear_count){
                    current_luminescence_count = uploadWorkingInfo.getToalCount() - AppConfig.current_count;
                    if (current_luminescence_count >= 0) {
                        tv_current.setText(current_luminescence_count+"");
                    }
                }else {
                    int clearCount = current_luminescence_count + uploadWorkingInfo.getToalCount();
                    tv_current.setText(clearCount+"");
                    clear_count = true;
                }
            }else {
                int clearCount = current_luminescence_count + uploadWorkingInfo.getToalCount();
                tv_current.setText(clearCount+"");
                clear_count = true;
            }

        }
        tv_total.setText(uploadWorkingInfo.getToalCount()+"");
        tv_flow.setText(uploadWorkingInfo.getFlowVelocity()+"L/min");
        tv_temperature.setText(uploadWorkingInfo.getTemperature()+"℃");
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
    }

}

