package com.ss.apidemo.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.protocol.frame.data.SetWorkingStatus;
import com.example.protocol.frame.data.UploadWorkingInfo;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.base.BaseFragment;
import com.ss.apidemo.bean.CommonBean;
import com.ss.apidemo.bean.MessageBean;
import com.ss.apidemo.bean.SetFluenceBean;
import com.ss.apidemo.bean.StackModeBean;
import com.ss.apidemo.bean.StackSkinBean;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.StackModeUtils;
import com.ss.apidemo.utils.StackSkinTypeUtils;

import java.math.BigDecimal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/*
 * SHR STACK 模式
 * */
public class StackFragment extends BaseFragment implements View.OnClickListener {

    private static final String PARAM1 = "param1";
    View rootView;

    private ImageView iv_stack;
    private CommonBean commonBean;
    private StackModeBean stackModeBean;
    private StackSkinBean stackSkinBean;

    private int current_fluence_progress = 0;
    private int current_fluence_min = 0;
    private int current_fluence_max = 0;
    private int current_stack= 0;
    private int stack_mode= 0;
    private TextView tv_burst;
    private TextView tv_fluence;
    private SeekBar sb_fluence;
    private TextView tv_energy;

    private boolean isHidden;
    private boolean isShowData = false;//是否显示原始数据

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            sendFluence();
        }
    };


    public static StackFragment newInstance(CommonBean commonBean) {
        StackFragment fragment = new StackFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM1, commonBean);
        fragment.setArguments(args);
        return fragment;
    }

    public static StackFragment newInstance() {
        StackFragment fragment = new StackFragment();

        return fragment;
    }
    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("=====获取焦点 stack");
        isHidden = true;
        handler.sendMessageDelayed(new Message(),500);

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
            rootView = inflater.inflate(R.layout.fragment_stack, container, false);
        }
        initView(rootView);
        initData();
        loadData(commonBean);
        return rootView;
    }

    private void loadData(CommonBean commonBean) {
        //STACK
        StackModeUtils stackTypeUtils = new StackModeUtils();
        stackModeBean = stackTypeUtils.modeType(commonBean.getGender(), commonBean.getHandgearType());
        StackSkinTypeUtils stackSkinTypeUtils = new StackSkinTypeUtils();
        stackSkinBean = stackSkinTypeUtils.modeType(commonBean.getGender(), commonBean.getSink(), commonBean.getBody());
        current_stack = stackSkinBean.getStack();
        stack_mode = stackSkinBean.getStack();
        tv_energy.setText(stackSkinBean.getEnergy() + "");
        current_fluence_progress = stackSkinBean.getFluenceProposal();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            current_fluence_min =stackModeBean.getFluenceMin();
            sb_fluence.setMin(current_fluence_min);
        }
        current_fluence_max =stackModeBean.getFluenceMax();
        boolean b1 = setFluenceSettingMax(stackModeBean.getFluenceMax());
        if (!b1){
            sb_fluence.setMax(stackModeBean.getFluenceMax());
        }
        sb_fluence.setProgress(current_fluence_progress);
        selectStack(current_stack);
        setCount();
        if (isHidden){
            sendFluence();
        }
    }

    public boolean setFluenceSettingMax(int currentMax){
        int energyUpper = getEnergyUpper();
        if (energyUpper != 0){
            if (currentMax > energyUpper){//当前选择手具的最大值大于设置的最大值
                current_fluence_max = energyUpper;
                sb_fluence.setMax(current_fluence_max);
                if (energyUpper <= current_fluence_progress){
                    current_fluence_progress = energyUpper;
                }
                return true;
            }
        }
        return false;
    }

    //获取布局文件中的文本内容使用传入的参数进行初始化
    private void initView(View view) {
        ImageView iv_fluence_up = view.findViewById(R.id.iv_fluence_up);
        iv_fluence_up.setOnClickListener(this);
        ImageView iv_fluence_down = view.findViewById(R.id.iv_fluence_down);
        iv_fluence_down.setOnClickListener(this);

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
        tv_energy = view.findViewById(R.id.tv_energy);

        tv_burst = view.findViewById(R.id.tv_burst);
        TextView tv_burst_unit = view.findViewById(R.id.tv_burst_unit);
        tv_burst_unit.setText("J("+getResources().getString(R.string.burst)+")");

        iv_stack = view.findViewById(R.id.iv_stack);
        iv_stack.setOnClickListener(this);

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

    }

    @Override
    public void onClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        switch (view.getId()) {
            case R.id.iv_stack:
                current_stack++;
                selectStack(current_stack);
                break;
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
        }
    }

    public void selectStack(int stack) {
        if (isHidden){
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
            LogUtils.e("=====获取焦点  stack声音");
        }

        if (stack == 2) {
            iv_stack.setImageDrawable(getResources().getDrawable(R.mipmap.ic_stack_2));
            stack_mode = 2;
        } else if (stack == 3) {
            iv_stack.setImageDrawable(getResources().getDrawable(R.mipmap.ic_stack_3));
            stack_mode = 3;
        } else if (stack == 4) {
            stack_mode = 4;
            iv_stack.setImageDrawable(getResources().getDrawable(R.mipmap.ic_stack_4));
        } else if (stack == 5) {
            stack_mode = 5;
            current_stack = 1;
            iv_stack.setImageDrawable(getResources().getDrawable(R.mipmap.ic_stack_5));
        }
        setCount();
    }
    public void setCount(){
        tv_fluence.setText(current_fluence_progress+"");
        int burst = current_fluence_progress * stack_mode;
        tv_burst.setText(burst + "");
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
        SetWorkingStatus setWorkingStatus = new SetWorkingStatus();
        setWorkingStatus.setWorkingModel(stack_mode-1); // 工作模式  1 2 3 4 5 shr 6 hr
        setWorkingStatus.setFluence(fluenceInt); // 单脉冲能量
        setWorkingStatus.setFrequency(5); // 频率 固定的5hz
        setWorkingStatus.setTotalEnergy(0);// 总能量 固定的 0
        setWorkingStatus.setWorkingTime(0); // 工作时间  秒 该模式没有时间
        setWorkingStatus.setQbConfig(current_handgear); // 手具选择 1-7
        setWorkingStatus.setChangeQbPortFlag(AppConfig.handgearSelect); // 是否切换手具端口 1 left 0 right
        setWorkingStatus.setWorkingStatus(1);// 工作状态 0  stby和停止  1 reading 2 working
        return setWorkingStatus;
    }
    /*
     * 根据下位机工作时上报的数据显示
     * */
    public void SetResponseStatus(UploadWorkingInfo uploadWorkingInfo){
        if (uploadWorkingInfo!=null){
            if (uploadWorkingInfo.getWorkingStatus() == 0) {//stby
                if (isShowData) {//执行过工作状态 在赋值
                    tv_energy.setText("0");
                    isShowData = false;
                }
            }else if (uploadWorkingInfo.getWorkingStatus() == 2){
                isShowData = true;
                //设置变化的能效
                int totalEnergy = uploadWorkingInfo.getTotalEnergy();
                double totalEnergyDouble = (double)totalEnergy/1000;
                String str = String.format("%.2f", totalEnergyDouble);
                tv_energy.setText(str);
            }

        }

    }
    public void sendFluence(){
        //值改变 发送报文到下位机
        SetFluenceBean setFluenceBean = new SetFluenceBean();
        setFluenceBean.setFluence(current_fluence_progress);
        LogUtils.e("下发单脉冲stack"+current_fluence_progress);
        EventBus.getDefault().post(setFluenceBean);
    }

}
