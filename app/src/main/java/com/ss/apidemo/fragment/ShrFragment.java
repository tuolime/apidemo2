package com.ss.apidemo.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.protocol.frame.data.SetWorkingStatus;
import com.example.protocol.frame.data.UploadWorkingInfo;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseFragment;
import com.ss.apidemo.bean.CommonBean;
import com.ss.apidemo.bean.MessageBean;
import com.ss.apidemo.bean.SetFluenceBean;
import com.ss.apidemo.bean.ShrModeBean;
import com.ss.apidemo.bean.ShrModeHzOrFluenceBean;
import com.ss.apidemo.bean.ShrSkinBean;
import com.ss.apidemo.bean.StopWorkBean;
import com.ss.apidemo.ui.OtherActivity;
import com.ss.apidemo.ui.ParameterActivity;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ShrModeHzOrFluenceUtils;
import com.ss.apidemo.utils.ShrModeUtils;
import com.ss.apidemo.utils.ShrSkinTypeUtils;
import com.ss.apidemo.utils.ToastUtil;

import java.math.BigDecimal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/*
 * SHR  模式
 * */
public class ShrFragment extends BaseFragment implements View.OnClickListener {

    private static final String PARAM1 = "param1";
    View rootView;
    private CommonBean commonBean;

    private ShrModeBean shrModeBean;
    private ShrSkinBean shrSkinBean;
    private ShrModeHzOrFluenceBean shrModeHzOrFluenceBean;

    private TextView tv_fluence;
    private int current_fluence_progress = 0;
    private int current_fluence_min = 0;
    private int current_fluence_max = 0;
    private int current_energy_min = 0;
    private int current_energy_max = 30;
    private int current_energy_progress = 0;
    private int current_grid_size_min = 1;
    private int current_grid_size_max = 4;
    private int current_grid_size_progress = 0;

    private int current_hz_min = 0;
    private int current_hz_max = 0;
    private int current_hz_progress = 1;


    private TextView tv_time;
    private TextView tv_grid_size;
    private TextView tv_energy;
    private TextView tv_hz;
    private SeekBar sb_fluence;
    private SeekBar sb_energy;
    private SeekBar sb_grid_size;
    private SeekBar sb_hz;

    private String endTime;
    private String endEnergy;
    private double end_energy_progress;

    private boolean isHidden;
    private boolean isShowData = false;//是否显示原始数据
    private boolean isPlayAudio = true;//是否播放声音

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            sendFluence();
        }
    };
    private ShrModeHzOrFluenceUtils shrModeHzOrFluenceUtils;

    public ShrFragment() {
        // Required empty public constructor
    }

    public static ShrFragment newInstance(CommonBean commonBean) {
        ShrFragment fragment = new ShrFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM1, commonBean);
        fragment.setArguments(args);
        return fragment;
    }
    public static ShrFragment newInstance() {
        ShrFragment fragment = new ShrFragment();

        return fragment;
    }
    @Override
    public void onResume() {
        super.onResume();
        isHidden = true;
        handler.sendMessageDelayed(new Message(),500);
        LogUtils.e("=====获取焦点 shr");

    }

    @Override
    public void onPause() {
        super.onPause();
        isHidden = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            commonBean = (CommonBean) getArguments().getSerializable(PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_shr, container, false);
        }
        initView(rootView);
        initData();
        loadData(commonBean);
        return rootView;
    }

    private void loadData(CommonBean commonBean) {
        //SHR
        ShrModeUtils shrModeUtils = new ShrModeUtils();
        shrModeBean = shrModeUtils.modeType(commonBean.getGender(), commonBean.getHandgearType());
        ShrSkinTypeUtils shrSkinTypeUtils = new ShrSkinTypeUtils();
        shrSkinBean = shrSkinTypeUtils.modeType(commonBean.getGender(), commonBean.getSink(), commonBean.getSize(), commonBean.getHandgearType(), commonBean.getBody());
        shrModeHzOrFluenceUtils = new ShrModeHzOrFluenceUtils();
        shrModeHzOrFluenceBean = shrModeHzOrFluenceUtils.modeType(commonBean.getHandgearType(), shrSkinBean.getBodyType_HZ());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            current_fluence_min = shrModeBean.getFluenceMin();
            sb_fluence.setMin(shrModeBean.getFluenceMin());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            current_hz_min = shrModeHzOrFluenceBean.getHandgearHzMin();
            current_hz_max = shrModeHzOrFluenceBean.getHandgearHzMax();
            current_hz_progress = shrSkinBean.getBodyType_HZ();
            sb_hz.setMin(shrModeHzOrFluenceBean.getHandgearHzMin());
            sb_hz.setMax(shrModeHzOrFluenceBean.getHandgearHzMax());
            sb_hz.setProgress(shrSkinBean.getBodyType_HZ());

        }
        if (shrSkinBean.getBodyType_HZ() == 5) {
            current_fluence_max = shrModeBean.getFluence5HzMax();
            boolean b1 = setFluenceSettingMax(shrModeBean.getFluence5HzMax());
            if (!b1){
                sb_fluence.setMax(shrModeBean.getFluence5HzMax());
            }
        }
        if (shrSkinBean.getBodyType_HZ() == 10) {
            current_fluence_max = shrModeBean.getFluence10HzMax();
            boolean b1 = setFluenceSettingMax(shrModeBean.getFluence10HzMax());
            if (!b1){
                sb_fluence.setMax(shrModeBean.getFluence10HzMax());
            }
        }
        sb_fluence.setProgress(shrSkinBean.getFluenceProposal());
//        ToastUtil.showToast(ShrFragment.this.getActivity(),"默认单脉冲"+shrSkinBean.getFluenceProposal());


        current_energy_progress = shrSkinBean.getEnergy();
        sb_energy.setMax(current_energy_max);
        sb_energy.setProgress(current_energy_progress);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sb_grid_size.setMin(current_grid_size_min);
        }
        sb_grid_size.setMax(current_grid_size_max);
        /*
         * 尺码
         * 1  s 25cm
         * 2  m 50cm
         * 3  l 100cm
         * 4  xl 300cm
         * */
        if (commonBean.getSize() == 1){
            sb_grid_size.setProgress(1);
            current_grid_size_progress = 1;
        }else if (commonBean.getSize() == 2){
            sb_grid_size.setProgress(2);
            current_grid_size_progress = 2;
        }else if (commonBean.getSize() == 3){
            sb_grid_size.setProgress(3);
            current_grid_size_progress = 3;
        }else if (commonBean.getSize() == 4){
            sb_grid_size.setProgress(4);
            current_grid_size_progress = 4;
        }

        setCount();
        if (isHidden){
            sendFluence();
        }
    }

    //获取布局文件中的文本内容使用传入的参数进行初始化
    private void initView(View view) {
        ImageView iv_fluence_up = view.findViewById(R.id.iv_fluence_up);
        iv_fluence_up.setOnClickListener(this);
        ImageView iv_fluence_down = view.findViewById(R.id.iv_fluence_down);
        iv_fluence_down.setOnClickListener(this);

        ImageView iv_energy_up = view.findViewById(R.id.iv_energy_up);
        iv_energy_up.setOnClickListener(this);
        ImageView iv_energy_down = view.findViewById(R.id.iv_energy_down);
        iv_energy_down.setOnClickListener(this);

        ImageView iv_grid_size_up = view.findViewById(R.id.iv_grid_size_up);
        iv_grid_size_up.setOnClickListener(this);
        ImageView iv_grid_size_down = view.findViewById(R.id.iv_grid_size_down);
        iv_grid_size_down.setOnClickListener(this);

        ImageView iv_hz_up = view.findViewById(R.id.iv_hz_up);
        iv_hz_up.setOnClickListener(this);
        ImageView iv_hz_down = view.findViewById(R.id.iv_hz_down);
        iv_hz_down.setOnClickListener(this);

        TextView tv_trio = view.findViewById(R.id.tv_trio);
        int booleanValue = SharedPrefsUtil.getIntValue(AppConfig.HANDGEAR, 0);
        if (booleanValue == 0) {//单手==左手
            tv_trio.setText(getResources().getString(R.string.trio));
        } else if (booleanValue == 1) {//双手 =  左右手
            if (AppConfig.handgearSelect == 1){
                tv_trio.setText(getResources().getString(R.string.trio));
            }else {
                tv_trio.setText(getResources().getString(R.string.trio_3d));
            }
        }

        tv_time = view.findViewById(R.id.tv_time);

        tv_fluence = view.findViewById(R.id.tv_fluence);
        sb_fluence = view.findViewById(R.id.sb_fluence);

        //单脉冲
        sb_fluence.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //scrollToBottom(mScrillview,terminal,progress);
                current_fluence_progress = progress;
                setCount();
                if (isHidden) {
                    PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (isHidden){
                    sendFluence();
                }
            }
        });
        tv_energy = view.findViewById(R.id.tv_energy);
        sb_energy = view.findViewById(R.id.sb_energy);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sb_energy.setMin(current_energy_min);
        }


        //单脉冲
        sb_energy.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //scrollToBottom(mScrillview,terminal,progress);
                current_energy_progress = progress;
                setCount();
                if (isHidden && isPlayAudio) {
                    PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (!isShowData){
                    if (seekBar.getProgress() == 0){
                        sb_energy.setProgress(1);
                    }
                }

            }
        });


        tv_grid_size = view.findViewById(R.id.tv_grid_size);
        sb_grid_size = view.findViewById(R.id.sb_grid_size);

        //单脉冲
        sb_grid_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //scrollToBottom(mScrillview,terminal,progress);
                current_grid_size_progress = progress;
                setTextContent();
//                if (isHidden) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
//                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tv_hz = view.findViewById(R.id.tv_hz);
        sb_hz = view.findViewById(R.id.sb_hz);

        //hz
        sb_hz.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //scrollToBottom(mScrillview,terminal,progress);
                current_hz_progress = progress;
                SetFluenceChange(progress);
//                if (isHidden) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
//                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        setTextContent();
    }
    /*
     * hz改变 对应的单脉冲改变 fluence
     * */
    public void SetFluenceChange(int hz){
        shrModeHzOrFluenceBean = shrModeHzOrFluenceUtils.modeType(commonBean.getHandgearType(), hz);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sb_fluence.setMin(shrModeHzOrFluenceBean.getFluenceMin());
            boolean b1 = setFluenceSettingMax(shrModeHzOrFluenceBean.getFluenceMax());
            if (!b1){
                sb_fluence.setMax(shrModeHzOrFluenceBean.getFluenceMax());
            }
//            if (shrSkinBean.getFluenceProposal()>=shrModeHzOrFluenceBean.getFluenceMax()){
//                sb_fluence.setProgress(shrSkinBean.getFluenceProposal());
//            }else {
//                sb_fluence.setProgress(shrModeHzOrFluenceBean.getFluenceMax());
//            }
        }
        setCount();
    }

    public boolean setFluenceSettingMax(int currentMax){
        int energyUpper = getEnergyUpper();
        if (energyUpper != 0){
            if (currentMax > energyUpper){//当前选择手具的最大值大于设置的最大值
                current_fluence_max = energyUpper;
                sb_fluence.setMax(current_fluence_max);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        switch (view.getId()) {
            case R.id.iv_fluence_up://点击进度增加
                if (current_fluence_progress < current_fluence_max){
                    current_fluence_progress++;
                    sb_fluence.setProgress(current_fluence_progress);
                    if (isHidden){
                        sendFluence();
                    }
                }
                break;
            case R.id.iv_fluence_down://点击进度减少
                if (current_fluence_progress > current_fluence_min){
                    current_fluence_progress--;
                    sb_fluence.setProgress(current_fluence_progress);
                    if (isHidden){
                        sendFluence();
                    }
                }
                break;
            case R.id.iv_energy_up://点击进度增加
                if (current_energy_progress < current_energy_max){
                    current_energy_progress++;
                    sb_energy.setProgress(current_energy_progress);
                }
                break;
            case R.id.iv_energy_down://点击进度减少
                if (current_energy_progress > current_energy_min){
                    if (current_energy_progress == 1){
                        sb_energy.setProgress(current_energy_progress);
                    }else {
                        current_energy_progress--;
                        sb_energy.setProgress(current_energy_progress);
                    }

                }
                break;
            case R.id.iv_grid_size_up://点击进度增加
                if (current_grid_size_progress < current_grid_size_max){
                    current_grid_size_progress++;
                    sb_grid_size.setProgress(current_grid_size_progress);
                }
                break;
            case R.id.iv_grid_size_down://点击进度减少
                if (current_grid_size_progress > current_grid_size_min){
                    current_grid_size_progress--;
                    sb_grid_size.setProgress(current_grid_size_progress);
                }
                break;
            case R.id.iv_hz_up://点击进度增加
                if (current_hz_progress < current_hz_max){
                    current_hz_progress++;
                    sb_hz.setProgress(current_hz_progress);
                }
                break;
            case R.id.iv_hz_down://点击进度减少
                if (current_hz_progress > current_hz_min){
                    current_hz_progress--;
                    sb_hz.setProgress(current_hz_progress);
                }
                break;
        }
    }

    public void setCount() {
        if (isShowData){//工作中 不赋值
            return;
        }
        if (current_hz_progress == 11){//部分手具有20hz,在实际数据中对应的数值是11，所以单独处理
            tv_hz.setText("20");
        }else {
            tv_hz.setText(current_hz_progress+"");
        }
        String hzString = tv_hz.getText().toString();
        int hzInt = Integer.parseInt(hzString);
        tv_fluence.setText(current_fluence_progress + "");
        tv_energy.setText(current_energy_progress + "");
        //time = energy*1000/(fluence *5)
        double time = current_energy_progress * 1000 / (current_fluence_progress * hzInt);
        Long roundTime = Math.round(time);

        tv_time.setText(roundTime.intValue() + "");

    }
    public void setTextContent(){
        if (current_grid_size_progress == 1){
            tv_grid_size.setText("25");
            if (commonBean!=null){
                if (commonBean.getGender() == 1){//男
                    sb_energy.setProgress(2);
                }else if (commonBean.getGender() == 2){//女
                    sb_energy.setProgress(1);
                }
            }
        }else if (current_grid_size_progress == 2){
            tv_grid_size.setText("50");
            if (commonBean!=null){
                if (commonBean.getGender() == 1){//男
                    sb_energy.setProgress(3);
                }else if (commonBean.getGender() == 2){//女
                    sb_energy.setProgress(2);
                }
            }
        }else if (current_grid_size_progress == 3){
            tv_grid_size.setText("100");
            if (commonBean!=null){
                if (commonBean.getGender() == 1){//男
                    sb_energy.setProgress(6);
                }else if (commonBean.getGender() == 2){//女
                    sb_energy.setProgress(5);
                }
            }
        }else if (current_grid_size_progress == 4){
            tv_grid_size.setText("300");
            if (commonBean!=null){
                if (commonBean.getGender() == 1){//男
                    sb_energy.setProgress(18);
                }else if (commonBean.getGender() == 2){//女
                    sb_energy.setProgress(17);
                }
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(CommonBean message) {
        loadData(message);
    }
    private void initData() {
        EventBus.getDefault().register(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销EventBus
        EventBus.getDefault().unregister(this);

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
        endEnergy = tv_energy.getText().toString();
        end_energy_progress = current_energy_progress;
        int totalEnergy = (int)current_energy_progress * 1000;
        endTime = tv_time.getText().toString();
        int timeInt = Integer.parseInt(endTime);
        String hzString = tv_hz.getText().toString();
        int hzInt = Integer.parseInt(hzString);
        SetWorkingStatus setWorkingStatus = new SetWorkingStatus();
        setWorkingStatus.setWorkingModel(5); // 工作模式  1 2 3 4 5 shr 6 hr
        setWorkingStatus.setFluence(fluenceInt); // 单脉冲能量
        setWorkingStatus.setFrequency(hzInt); // 频率
        setWorkingStatus.setTotalEnergy(totalEnergy);// 总能量
        setWorkingStatus.setWorkingTime(timeInt); // 工作时间  秒 该模式没有时间
        setWorkingStatus.setQbConfig(current_handgear); // 手具选择 1-7
        setWorkingStatus.setChangeQbPortFlag(AppConfig.handgearSelect); // 是否切换手具端口 1 left 0 right
        setWorkingStatus.setWorkingStatus(1);// 工作状态 0  stby和停止  1 reading 2 working
//        ToastUtil.showToast(ShrFragment.this.getActivity(),"下发时间"+endTime+"下发能效"+totalEnergy);

        return setWorkingStatus;
    }
    /*
    * 根据下位机工作时上报的数据显示
    * SHR模式下
        standby  time和energy 根据用户选择显示
        reading  time和energy不变
        working time和energy 根据下位机响应 改变
    * */
    public void SetResponseStatus(UploadWorkingInfo uploadWorkingInfo){
        if (uploadWorkingInfo!=null){
            if (uploadWorkingInfo.getWorkingStatus() == 0) {//stby
                if (isShowData) {//执行过工作状态 在赋值
                    tv_time.setText(endTime);
                    Long round = Math.round(end_energy_progress);
                    sb_energy.setProgress(round.intValue());
                    tv_energy.setText(endEnergy + "");
                    isShowData = false;
                }
                isPlayAudio = true;
            }else if (uploadWorkingInfo.getWorkingStatus() == 1){
                isPlayAudio = true;
            }else if (uploadWorkingInfo.getWorkingStatus() == 2){
                isShowData = true;
                //设置变化的时间
                int workingTime = uploadWorkingInfo.getWorkingTime();
                tv_time.setText(workingTime+"");

                //设置变化的能效
                int totalEnergy = uploadWorkingInfo.getTotalEnergy();
                double totalEnergyDouble = (double)totalEnergy/1000;
                Long round = Math.round(totalEnergyDouble);
                sb_energy.setProgress(round.intValue());
                String str = String.format("%.2f", totalEnergyDouble);
                tv_energy.setText(str);
//                ToastUtil.showToast(ShrFragment.this.getActivity(),"应答原始能效"+totalEnergy);
                isPlayAudio = false;
            }
        }

    }
    public void sendFluence(){
        //值改变 发送报文到下位机
        SetFluenceBean setFluenceBean = new SetFluenceBean();
        setFluenceBean.setFluence(current_fluence_progress);
        LogUtils.e("下发单脉冲shr"+current_fluence_progress);
        EventBus.getDefault().post(setFluenceBean);
    }
}
