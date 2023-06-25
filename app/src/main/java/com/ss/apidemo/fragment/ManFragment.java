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
import com.ss.apidemo.utils.ToastUtil;

import androidx.fragment.app.Fragment;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/*
* 男性正面身体
* */
public class ManFragment extends Fragment implements View.OnClickListener {
    public boolean isPlayVoice;

    private static final String ARG_PARAM1 = "param1";
    View rootView;
    private int mParam1;

    // 男正面
    // 胸
    private ImageView iv_man_chest;
    // 腹
    private ImageView iv_man_abdomen;
    // 比基尼
    private ImageView iv_man_Bikini;
    // 大腿
    private ImageView iv_man_thigh;
    // 膝盖
    private ImageView iv_man_knee;
    // 小腿
    private ImageView iv_man_leg;
    // 腋下
    private ImageView iv_man_armpit;
    // 手
    private ImageView iv_man_hand;


    // 男部位id
    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
    // 13：后颈；14：后背；15：臀；16：肩；
   
    private int partId = 0;

   

    public ManFragment() {
        // Required empty public constructor
    }

    public static ManFragment newInstance(int body) {
        ManFragment fragment = new ManFragment();
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
            rootView = inflater.inflate(R.layout.fragment_man, container, false);
        }
        initView(rootView);
        initData();
        return rootView;
    }

    //获取布局文件中的文本内容使用传入的参数进行初始化
    private void initView(View view) {
        // 初始化男性正面view
        initManFront(view);
        // 设置男性正面点击监听
        setManFrontOnClickListener();

    }
    private void initManFront(View view){
        // 男正面胸
        iv_man_chest = (ImageView) view.findViewById(R.id.iv_man_chest);
        // 男正面腹
        iv_man_abdomen = (ImageView) view.findViewById(R.id.iv_man_abdomen);
        // 男正面比基尼
        iv_man_Bikini = (ImageView) view.findViewById(R.id.iv_man_Bikini);
        // 男正面大腿
        iv_man_thigh = (ImageView) view.findViewById(R.id.iv_man_thigh);
        // 男正面膝盖
        iv_man_knee = (ImageView) view.findViewById(R.id.iv_man_knee);
        // 男正面小腿
        iv_man_leg = (ImageView) view.findViewById(R.id.iv_man_leg);
        // 男正面腋下
        iv_man_armpit = (ImageView) view.findViewById(R.id.iv_man_armpit);
        // 男正面手
        iv_man_hand = (ImageView) view.findViewById(R.id.iv_man_hand);
       
    }
    /**
     * 设置男性正面点击监听
     */
    private void setManFrontOnClickListener(){
        // 男正面胸
        iv_man_chest.setOnClickListener(this);
        // 男正面腹
        iv_man_abdomen.setOnClickListener(this);
        // 男正面比基尼
        iv_man_Bikini.setOnClickListener(this);
        // 男正面大腿
        iv_man_thigh.setOnClickListener(this);
        // 男正面膝盖
        iv_man_knee.setOnClickListener(this);
        // 男正面小腿
        iv_man_leg.setOnClickListener(this);
        // 男正面腋下
        iv_man_armpit.setOnClickListener(this);
        // 男正面手
        iv_man_hand.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        isPlayVoice = true;
        // 男性正面点击监听
        onClickManFront(view);
        // 跳转到对应id身体区域
        gotoWhichBodyPart(view, partId);
    }

    private void onClickManFront(View v){
        if (isPlayVoice){
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
            LogUtils.e("声音 man");
        }
        resetMenuState();
        switch (v.getId()) {
            case R.id.iv_man_chest:
                iv_man_chest.setImageDrawable(getResources().getDrawable(R.drawable.man_1_select));
                // 胸
                partId = 5;
                break;
            case R.id.iv_man_abdomen:
                iv_man_abdomen.setImageDrawable(getResources().getDrawable(R.drawable.man_2_select));
                // 腹
                partId = 6;
                break;
            case R.id.iv_man_Bikini:
                iv_man_Bikini.setImageDrawable(getResources().getDrawable(R.drawable.man_3_select));
                // 比基尼
                partId = 7;
                break;
            case R.id.iv_man_thigh:
                iv_man_thigh.setImageDrawable(getResources().getDrawable(R.drawable.man_4_select));
                // 大腿
                partId = 8;
                break;
            case R.id.iv_man_knee:
                iv_man_knee.setImageDrawable(getResources().getDrawable(R.drawable.man_5_select));
                // 膝盖
                partId = 9;
                break;
            case R.id.iv_man_leg:
                iv_man_leg.setImageDrawable(getResources().getDrawable(R.drawable.man_6_select));
                // 小腿
                partId = 10;
                break;
            case R.id.iv_man_armpit:
                iv_man_armpit.setImageDrawable(getResources().getDrawable(R.drawable.man_7_select));
                // 腋下
                partId = 11;
                break;
            case R.id.iv_man_hand:
                iv_man_hand.setImageDrawable(getResources().getDrawable(R.drawable.man_8_select));
                // 手
                partId = 12;
                break;
        }
    }

    private void resetMenuState() {
        iv_man_chest.setImageDrawable(getResources().getDrawable(R.drawable.man_1));
        iv_man_abdomen.setImageDrawable(getResources().getDrawable(R.drawable.man_2));
        iv_man_Bikini.setImageDrawable(getResources().getDrawable(R.drawable.man_3));
        iv_man_thigh.setImageDrawable(getResources().getDrawable(R.drawable.man_4));
        iv_man_knee.setImageDrawable(getResources().getDrawable(R.drawable.man_5));
        iv_man_leg.setImageDrawable(getResources().getDrawable(R.drawable.man_6));
        iv_man_armpit.setImageDrawable(getResources().getDrawable(R.drawable.man_7));
        iv_man_hand.setImageDrawable(getResources().getDrawable(R.drawable.man_8));
        
    }

    /**
     * 跳转到对应id身体区域
     * @param v
     * @param bodyPartId 身体部位id
     */
    private void gotoWhichBodyPart(View v, int bodyPartId){
        if (partId == 0){
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
        mParam1 =R.id.iv_man_chest;
        view1.setId(mParam1);
        isPlayVoice = false;
        // 男性正面点击监听
        onClickManFront(view1);
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
                   mParam1 =R.id.iv_man_chest;
                    break;
                case 6:
                    mParam1 =R.id.iv_man_abdomen;
                    break;
                case 7:
                     mParam1 =R.id.iv_man_Bikini;
                    break;
                case 8:
                    mParam1 =R.id.iv_man_thigh;
                    break;
                case 9:
                    mParam1 =R.id.iv_man_knee;
                    break;
                case 10:
                     mParam1 =R.id.iv_man_leg;
                    break;
                case 11:
                    mParam1 =R.id.iv_man_armpit;
                    break;
                case 12:
                    mParam1 =R.id.iv_man_hand;
                    break;
            }
            View view1 = new View(MyApplication.instance());
            view1.setId(mParam1);
            isPlayVoice = false;
            // 男性正面点击监听
            onClickManFront(view1);
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
