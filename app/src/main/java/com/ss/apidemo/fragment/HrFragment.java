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

import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetWorkingStatus;
import com.example.protocol.utils.ParserUtil;
import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.bean.CommonBean;
import com.ss.apidemo.bean.HrModeBean;
import com.ss.apidemo.bean.HrSkinBean;
import com.ss.apidemo.bean.MessageBean;
import com.ss.apidemo.bean.SetFluenceBean;
import com.ss.apidemo.utils.HrModeUtils;
import com.ss.apidemo.utils.HrSkinTypeUtils;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import cn.hutool.core.lang.Console;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/*
 * HR  模式
 * */
public class HrFragment extends Fragment implements View.OnClickListener {

    private static final String PARAM1 = "param1";
    View rootView;
    private CommonBean commonBean;


    private HrModeBean hrModeBean;
    private HrSkinBean hrSkinBean;
    private int current_frequency_min = 1;
    private int current_frequency_common_max = 0;
    private int current_frequency_max = 10;
    private int current_frequency_max_3_7_8_9 = 11;
    private int current_frequency_progress = 1;


    private int current_fluence_progress = 0;
    private int current_fluence_min = 0;
    private int current_fluence_max = 0;

    private int current_handgear = 0;
    private SeekBar sb_frequency;
    private TextView tv_frequency;
    private SeekBar sb_fluence;
    private TextView tv_fluecne;

    private boolean isHidden;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            sendFluence();
        }
    };

    public HrFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        isHidden = true;
        handler.sendMessageDelayed(new Message(),500);
        LogUtils.e("=====获取焦点 hr");

    }

    @Override
    public void onPause() {
        super.onPause();
        isHidden = false;
    }

    public static HrFragment newInstance(CommonBean commonBean) {
        HrFragment fragment = new HrFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM1, commonBean);
        fragment.setArguments(args);
        return fragment;
    }

    public static HrFragment newInstance() {
        HrFragment fragment = new HrFragment();

        return fragment;
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
            rootView = inflater.inflate(R.layout.fragment_hr, container, false);
        }
        initView(rootView);
        initData();
        loadData(commonBean);
        return rootView;
    }

    private void loadData(CommonBean commonBean) {
        //HR
        HrModeUtils hrModeUtils = new HrModeUtils();
        hrModeBean = hrModeUtils.modeType(commonBean.getGender(), commonBean.getHandgearType());
        HrSkinTypeUtils hrSkinTypeUtils = new HrSkinTypeUtils();
        hrSkinBean = hrSkinTypeUtils.modeType(commonBean.getGender(), commonBean.getSink(), commonBean.getHandgearType(), commonBean.getBody());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            current_fluence_min = hrModeBean.getFluenceMin();
            sb_fluence.setMin(hrModeBean.getFluenceMin());
        }
        setfluenctMax();
        current_fluence_progress = hrSkinBean.getFluenceProposal();
        setFluenctText();
        sb_fluence.setProgress(current_fluence_progress);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sb_frequency.setMin(current_frequency_min);
        }
        if (current_handgear == 3 || current_handgear == 7 || current_handgear == 8 || current_handgear == 9) {
            sb_frequency.setMax(current_frequency_max_3_7_8_9);
            current_frequency_common_max = current_frequency_max_3_7_8_9;
        } else {
            sb_frequency.setMax(current_frequency_max);
            current_frequency_common_max = current_frequency_max;
        }
        setTextContent();
        sb_frequency.setProgress(hrSkinBean.getFreQuencyProposal());
        current_frequency_progress = hrSkinBean.getFreQuencyProposal();
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

        ImageView iv_frequency_up = view.findViewById(R.id.iv_frequency_up);
        iv_frequency_up.setOnClickListener(this);
        ImageView iv_frequency_down = view.findViewById(R.id.iv_frequency_down);
        iv_frequency_down.setOnClickListener(this);

        TextView tv_trio = view.findViewById(R.id.tv_trio);

        if (AppConfig.handgearSelect == 1) {//单手==左手
            tv_trio.setText(getResources().getString(R.string.trio));
            current_handgear = SharedPrefsUtil.getIntValue(AppConfig.HAND_LEFT, 1);
        } else if (AppConfig.handgearSelect == 0) {//双手 =  左右手
            tv_trio.setText(getResources().getString(R.string.trio_3d));
            current_handgear = SharedPrefsUtil.getIntValue(AppConfig.HAND_RIGHT, 1);
        }

        tv_fluecne = view.findViewById(R.id.tv_fluecne);
        sb_fluence = view.findViewById(R.id.sb_fluence);

        sb_fluence.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //scrollToBottom(mScrillview,terminal,progress);
                current_fluence_progress = progress;
                setFluenctText();
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
        tv_frequency = view.findViewById(R.id.tv_frequency);
        sb_frequency = view.findViewById(R.id.sb_frequency);

        sb_frequency.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //scrollToBottom(mScrillview,terminal,progress);
                current_frequency_progress = progress;
                setTextContent();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        switch (view.getId()) {
            case R.id.iv_fluence_up://点击进度增加
                if (current_fluence_progress < current_fluence_max) {
                    current_fluence_progress++;
                    sb_fluence.setProgress(current_fluence_progress);
                    if (isHidden){
                        sendFluence();
                    }
                }
                break;
            case R.id.iv_fluence_down://点击进度减少
                if (current_fluence_progress > current_fluence_min) {
                    current_fluence_progress--;
                    sb_fluence.setProgress(current_fluence_progress);
                    if (isHidden){
                        sendFluence();
                    }
                }
                break;
            case R.id.iv_frequency_up://点击进度增加
                if (current_frequency_progress < current_frequency_common_max) {
                    current_frequency_progress++;
                    sb_frequency.setProgress(current_frequency_progress);
                }
                break;
            case R.id.iv_frequency_down://点击进度减少
                if (current_frequency_progress > current_frequency_min) {
                    current_frequency_progress--;
                    sb_frequency.setProgress(current_frequency_progress);
                }
                break;
        }
    }

    public void setFluenctText() {
        tv_fluecne.setText(current_fluence_progress + "");
    }

    public void setTextContent() {

        setfluenctMax();
        if (current_frequency_progress == 11) {
            tv_frequency.setText(20 + "");
        } else {
            tv_frequency.setText(current_frequency_progress + "");
        }
    }

    public void setfluenctMax() {
        if (isHidden) {
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
            LogUtils.e("=====获取焦点  hr声音");
        }
        switch (current_frequency_progress) {
            case 1:
                current_fluence_max = hrModeBean.getFluence1HzMax();
                sb_fluence.setMax(hrModeBean.getFluence1HzMax());
                break;
            case 2:
                current_fluence_max = hrModeBean.getFluence2HzMax();
                sb_fluence.setMax(hrModeBean.getFluence2HzMax());
                break;
            case 3:
                current_fluence_max = hrModeBean.getFluence3HzMax();
                sb_fluence.setMax(hrModeBean.getFluence3HzMax());
                break;
            case 4:
                current_fluence_max = hrModeBean.getFluence4HzMax();
                sb_fluence.setMax(hrModeBean.getFluence4HzMax());
                break;
            case 5:
                current_fluence_max = hrModeBean.getFluence5HzMax();
                sb_fluence.setMax(hrModeBean.getFluence5HzMax());
                break;
            case 6:
                current_fluence_max = hrModeBean.getFluence6HzMax();
                sb_fluence.setMax(hrModeBean.getFluence6HzMax());
                break;
            case 7:
                current_fluence_max = hrModeBean.getFluence7HzMax();
                sb_fluence.setMax(hrModeBean.getFluence7HzMax());
                break;
            case 8:
                current_fluence_max = hrModeBean.getFluence8HzMax();
                sb_fluence.setMax(hrModeBean.getFluence8HzMax());
                break;
            case 9:
                current_fluence_max = hrModeBean.getFluence9HzMax();
                sb_fluence.setMax(hrModeBean.getFluence9HzMax());
                break;
            case 10:
                current_fluence_max = hrModeBean.getFluence10HzMax();
                sb_fluence.setMax(hrModeBean.getFluence10HzMax());
                break;
            case 11:
                current_fluence_max = hrModeBean.getFluence20HzMax();
                sb_fluence.setMax(hrModeBean.getFluence20HzMax());
                break;
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

    public  SetWorkingStatus setValue(){
        int current_handgear = 0;
        if (AppConfig.handgearSelect == 1) {//单手==左手
            current_handgear = SharedPrefsUtil.getIntValue(AppConfig.HAND_LEFT, 1);
        } else if (AppConfig.handgearSelect == 0) {//双手 =  左右手
            current_handgear = SharedPrefsUtil.getIntValue(AppConfig.HAND_RIGHT, 1);
        }
        String fluenceString = tv_fluecne.getText().toString();
        int fluenceInt = Integer.parseInt(fluenceString);
        String frequencyString = tv_frequency.getText().toString();
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
   public void sendFluence(){
       //值改变 发送报文到下位机
       SetFluenceBean setFluenceBean = new SetFluenceBean();
       setFluenceBean.setFluence(current_fluence_progress);
       LogUtils.e("下发单脉冲hr"+current_fluence_progress);
       EventBus.getDefault().post(setFluenceBean);
   }
}
