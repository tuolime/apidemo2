package com.ss.apidemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.bean.CommonBodyBean;
import com.ss.apidemo.ui.OtherActivity;
import com.ss.apidemo.ui.ParameterActivity;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;

import androidx.fragment.app.Fragment;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/*
* 女性正面
* */
public class WoManFragment extends Fragment implements View.OnClickListener {
    public boolean isPlayVoice;

    private static final String ARG_PARAM1 = "param1";
    View rootView;
    private int mParam1;

    // 女性正面
    // 胸
    private ImageView iv_woman_chest;
    // 腹
    private ImageView iv_woman_abdomen;
    // 比基尼
    private ImageView iv_woman_Bikini;
    // 大腿
    private ImageView iv_woman_thigh;
    // 膝盖
    private ImageView iv_woman_knee;
    // 小腿
    private ImageView iv_woman_leg;
    // 腋下
    private ImageView iv_woman_armpit;
    // 手臂
    private ImageView iv_woman_arm;
    // 手
    private ImageView iv_woman_hand;


    // 部位id
    // 1：女性额头；2：女性面颊；3：女性嘴唇；4：女性脖子；
    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手臂；13：手；
    // 14：后颈；15：后背；16：臀；17：肩；
    private int partId = 0;



    public WoManFragment() {
        // Required empty public constructor
    }

    public static WoManFragment newInstance(int body) {
        WoManFragment fragment = new WoManFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, body);//暂时未用到该参数
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(R.layout.fragment_woman, container, false);
        }
        initView(rootView);
        initData();
        return rootView;
    }

    //获取布局文件中的文本内容使用传入的参数进行初始化
    private void initView(View view) {
        // 初始化女性正面view
        initManFront(view);
        // 设置女性正面点击监听
        setManFrontOnClickListener();

    }
    private void initManFront(View view){
        // 女正面胸
        iv_woman_chest = (ImageView) view.findViewById(R.id.iv_woman_chest);
        // 女正面腹
        iv_woman_abdomen = (ImageView) view.findViewById(R.id.iv_woman_abdomen);
        // 女正面比基尼
        iv_woman_Bikini = (ImageView) view.findViewById(R.id.iv_woman_Bikini);
        // 女正面大腿
        iv_woman_thigh = (ImageView) view.findViewById(R.id.iv_woman_thigh);
        // 女正面膝盖
        iv_woman_knee = (ImageView) view.findViewById(R.id.iv_woman_knee);
        // 女正面小腿
        iv_woman_leg = (ImageView) view.findViewById(R.id.iv_woman_leg);
        // 女正面腋下
        iv_woman_armpit = (ImageView) view.findViewById(R.id.iv_woman_armpit);
        // 女正面手臂
        iv_woman_arm = (ImageView) view.findViewById(R.id.iv_woman_arm);
        // 女正面手
        iv_woman_hand = (ImageView) view.findViewById(R.id.iv_woman_hand);

    }
    /**
     * 设置女性正面点击监听
     */
    private void setManFrontOnClickListener(){
        // 女正面胸
        iv_woman_chest.setOnClickListener(this);
        // 女正面腹
        iv_woman_abdomen.setOnClickListener(this);
        // 女正面比基尼
        iv_woman_Bikini.setOnClickListener(this);
        // 女正面大腿
        iv_woman_thigh.setOnClickListener(this);
        // 女正面膝盖
        iv_woman_knee.setOnClickListener(this);
        // 女正面小腿
        iv_woman_leg.setOnClickListener(this);
        // 女正面腋下
        iv_woman_armpit.setOnClickListener(this);
        // 女正面手臂
        iv_woman_arm.setOnClickListener(this);
        // 女正面手
        iv_woman_hand.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        isPlayVoice = true;
        // 女性正面点击监听
        onClickWoManFront(view);
        // 跳转到对应id身体区域
        gotoWhichBodyPart(view, partId);
    }

    private void onClickWoManFront(View v){
        if (isPlayVoice){
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        }
        resetMenuState();
        switch (v.getId()) {
            case R.id.iv_woman_chest:
                iv_woman_chest.setImageDrawable(getResources().getDrawable(R.drawable.woman_1_select));
                // 胸
                partId = 5;
                break;
            case R.id.iv_woman_abdomen:
                iv_woman_abdomen.setImageDrawable(getResources().getDrawable(R.drawable.woman_2_select));
                // 腹
                partId = 6;
                break;
            case R.id.iv_woman_Bikini:
                iv_woman_Bikini.setImageDrawable(getResources().getDrawable(R.drawable.woman_3_select));
                // 比基尼
                partId = 7;
                break;
            case R.id.iv_woman_thigh:
                iv_woman_thigh.setImageDrawable(getResources().getDrawable(R.drawable.woman_4_select));
                // 大腿
                partId = 8;
                break;
            case R.id.iv_woman_knee:
                iv_woman_knee.setImageDrawable(getResources().getDrawable(R.drawable.woman_5_select));
                // 膝盖
                partId = 9;
                break;
            case R.id.iv_woman_leg:
                iv_woman_leg.setImageDrawable(getResources().getDrawable(R.drawable.woman_6_select));
                // 小腿
                partId = 10;
                break;
            case R.id.iv_woman_armpit:
                iv_woman_armpit.setImageDrawable(getResources().getDrawable(R.drawable.woman_7_select));
                // 腋下
                partId = 11;
                break;
            case R.id.iv_woman_arm:
                iv_woman_arm.setImageDrawable(getResources().getDrawable(R.drawable.woman_8_select));
                // 手臂
                partId = 12;
                break;
            case R.id.iv_woman_hand:
                iv_woman_hand.setImageDrawable(getResources().getDrawable(R.drawable.woman_9_select));
                // 手
                partId = 13;
                break;
        }
    }

    private void resetMenuState() {
        iv_woman_chest.setImageDrawable(getResources().getDrawable(R.drawable.woman_1));
        iv_woman_abdomen.setImageDrawable(getResources().getDrawable(R.drawable.woman_2));
        iv_woman_Bikini.setImageDrawable(getResources().getDrawable(R.drawable.woman_3));
        iv_woman_thigh.setImageDrawable(getResources().getDrawable(R.drawable.woman_4));
        iv_woman_knee.setImageDrawable(getResources().getDrawable(R.drawable.woman_5));
        iv_woman_leg.setImageDrawable(getResources().getDrawable(R.drawable.woman_6));
        iv_woman_armpit.setImageDrawable(getResources().getDrawable(R.drawable.woman_7));
        iv_woman_arm.setImageDrawable(getResources().getDrawable(R.drawable.woman_8));
        iv_woman_hand.setImageDrawable(getResources().getDrawable(R.drawable.woman_9));

    }

    /**
     * 跳转到对应id身体区域
     * @param v
     * @param bodyPartId 身体部位id
     */
    private void gotoWhichBodyPart(View v, int bodyPartId){
        if (bodyPartId == 0){
            return;
        }
        if (getActivity() instanceof ParameterActivity){
            ((ParameterActivity) getActivity()).addIVandPlay(bodyPartId);
        }
        if (getActivity() instanceof OtherActivity){
            ((OtherActivity) getActivity()).addIVandPlay(bodyPartId);
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(CommonBodyBean message) {
        if (message.getCurrent_body_mode() != 1){//不是当前显示的身体部位
            resetMenuState();
        }
    }
    public void SetBodyDefaultType(){
        LogUtils.e("----身体 赋默认值");
        View view1 = new View(MyApplication.instance());
        mParam1 =R.id.iv_woman_chest;
        view1.setId(mParam1);
        isPlayVoice = false;
        // 女性正面点击监听
        onClickWoManFront(view1);
        // 跳转到对应id身体区域
        gotoWhichBodyPart(view1, partId);
    }
    private void initData() {
        EventBus.getDefault().register(this);
        setDefaultBody();
    }

    private void setDefaultBody(){
        if (AppConfig.defaultBody > 0){
            switch (AppConfig.defaultBody) {
                case 5:
                    mParam1 =R.id.iv_woman_chest;
                    break;
                case 6:
                    mParam1 =R.id.iv_woman_abdomen;
                    break;
                case 7:
                    mParam1 =R.id.iv_woman_Bikini;
                    break;
                case 8:
                    mParam1 =R.id.iv_woman_thigh;
                    break;
                case 9:
                    mParam1 =R.id.iv_woman_knee;
                    break;
                case 10:
                    mParam1 =R.id.iv_woman_leg;
                    break;
                case 11:
                    mParam1 =R.id.iv_woman_armpit;
                    break;
                case 12:
                    mParam1 =R.id.iv_woman_arm;
                    break;
                case 13:
                    mParam1 =R.id.iv_woman_hand;
                    break;
            }
            View view1 = new View(MyApplication.instance());
            view1.setId(mParam1);
            isPlayVoice = false;
            // 女性正面点击监听
            onClickWoManFront(view1);
            // 跳转到对应id身体区域
            gotoWhichBodyPart(view1, partId);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销EventBus
        EventBus.getDefault().unregister(this);

    }
}
