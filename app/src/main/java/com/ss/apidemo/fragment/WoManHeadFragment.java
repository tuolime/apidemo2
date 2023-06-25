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
* 女性头部
* */
public class WoManHeadFragment extends Fragment implements View.OnClickListener{
    public boolean isPlayVoice;

    private static final String ARG_PARAM1 = "param1";
    View rootView;
    private int mParam1;

    // 女性额头
    private ImageView iv_woman_head_forehead;
    // 女性面颊
    private ImageView iv_woman_head_cheek;
    // 女性嘴唇
    private ImageView iv_woman_head_lip;
    // 女性脖子
    private ImageView iv_woman_head_neck;


    // 部位id
    // 1：女性额头；2：女性面颊；3：女性嘴唇；4：女性脖子；

    private int partId = 0;

    public WoManHeadFragment() {
        // Required empty public constructor
    }

    public static WoManHeadFragment newInstance(int body) {
        WoManHeadFragment fragment = new WoManHeadFragment();
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
            rootView = inflater.inflate(R.layout.fragment_woman_head, container, false);
        }
        initView(rootView);
        initData();
        return rootView;
    }

    //获取布局文件中的文本内容使用传入的参数进行初始化
    private void initView(View view) {
        // 初始化女性正面view
        initwomanFront(view);
        // 设置女性正面点击监听
        setwomanFrontOnClickListener();

    }
    private void initwomanFront(View view){
        // 女性额头
        iv_woman_head_forehead = (ImageView) view.findViewById(R.id.iv_woman_head_forehead);
        // 女性面颊
        iv_woman_head_cheek = (ImageView) view.findViewById(R.id.iv_woman_head_cheek);
        // 女性嘴唇
        iv_woman_head_lip = (ImageView) view.findViewById(R.id.iv_woman_head_lip);
        // 女性脖子
        iv_woman_head_neck = (ImageView) view.findViewById(R.id.iv_woman_head_neck);

    }
    /**
     * 设置女性正面点击监听
     */
    private void setwomanFrontOnClickListener(){
        // 女性额头
        iv_woman_head_forehead.setOnClickListener(this);
        // 女性面颊
        iv_woman_head_cheek.setOnClickListener(this);
        // 女性嘴唇
        iv_woman_head_lip.setOnClickListener(this);
        // 女性脖子
        iv_woman_head_neck.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        isPlayVoice = true;
        // 女性头部点击监听
        onClickwomanHead(view);
        // 跳转到对应id身体区域
        gotoWhichBodyPart(view, partId);
    }

    private void onClickwomanHead(View v){
        if (isPlayVoice){
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
        }
        resetMenuState();
        switch (v.getId()) {
            case R.id.iv_woman_head_forehead:
                iv_woman_head_forehead.setImageDrawable(getResources().getDrawable(R.drawable.woman_head_1_select));
                partId = 1;
                break;
            case R.id.iv_woman_head_cheek:
                iv_woman_head_cheek.setImageDrawable(getResources().getDrawable(R.drawable.woman_head_2_select));
                partId = 2;
                break;
            case R.id.iv_woman_head_lip:
                iv_woman_head_lip.setImageDrawable(getResources().getDrawable(R.drawable.woman_head_3_select));
                partId = 3;
                break;
            case R.id.iv_woman_head_neck:
                iv_woman_head_neck.setImageDrawable(getResources().getDrawable(R.drawable.woman_head_4_select));
                partId = 4;
                break;
        }
    }
    private void resetMenuState() {
        iv_woman_head_forehead.setImageDrawable(getResources().getDrawable(R.drawable.woman_head_1));
        iv_woman_head_cheek.setImageDrawable(getResources().getDrawable(R.drawable.woman_head_2));
        iv_woman_head_lip.setImageDrawable(getResources().getDrawable(R.drawable.woman_head_3));
        iv_woman_head_neck.setImageDrawable(getResources().getDrawable(R.drawable.woman_head_4));


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
        if (message.getCurrent_body_mode() != 0){//不是当前显示的身体部位
            resetMenuState();
        }
    }

    public void SetBodyDefaultType(){
        LogUtils.e("----头 赋默认值");
        View view1 = new View(MyApplication.instance());
        mParam1 =R.id.iv_woman_head_forehead;
        view1.setId(mParam1);
        isPlayVoice = false;
        // 女性头部点击监听
        onClickwomanHead(view1);
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
                case 1:
                    mParam1 =R.id.iv_woman_head_forehead;
                    break;
                case 2:
                    mParam1 =R.id.iv_woman_head_cheek;
                    break;
                case 3:
                    mParam1 =R.id.iv_woman_head_lip;
                    break;
                case 4:
                    mParam1 =R.id.iv_woman_head_neck;
                    break;
            }
            View view1 = new View(MyApplication.instance());
            view1.setId(mParam1);
            isPlayVoice = false;
            // 女性头部点击监听
            onClickwomanHead(view1);
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
