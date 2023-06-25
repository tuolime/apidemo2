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
* 男性反面身体
* */
public class ManBackFragment extends Fragment implements View.OnClickListener{

    public boolean isPlayVoice;
    private static final String ARG_PARAM1 = "param1";
    View rootView;
    private int mParam1;

    // 男背面
    // 后颈
    private ImageView iv_man_nape;
    // 后背
    private ImageView iv_man_back;
    // 臀
    private ImageView iv_man_Buttocks;
    // 肩
    private ImageView iv_man_shoulder;



    // 男部位id
    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
    // 13：后颈；14：后背；15：臀；16：肩；

    private int partId = 0;



    public ManBackFragment() {
        // Required empty public constructor
    }

    public static ManBackFragment newInstance(int body) {
        ManBackFragment fragment = new ManBackFragment();
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
            rootView = inflater.inflate(R.layout.fragment_man_back, container, false);
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
        // 男背面后颈
        iv_man_nape = (ImageView) view.findViewById(R.id.iv_man_nape);
        // 男背面背
        iv_man_back = (ImageView) view.findViewById(R.id.iv_man_back);
        // 男背面臀
        iv_man_Buttocks = (ImageView) view.findViewById(R.id.iv_man_Buttocks);
        // 男背面肩
        iv_man_shoulder = (ImageView) view.findViewById(R.id.iv_man_shoulder);
       

    }
    /**
     * 设置男性正面点击监听
     */
    private void setManFrontOnClickListener(){
        // 男背面后颈
        iv_man_nape.setOnClickListener(this);
        // 男背面背
        iv_man_back.setOnClickListener(this);
        // 男背面臀
        iv_man_Buttocks.setOnClickListener(this);
        // 男背面肩
        iv_man_shoulder.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        isPlayVoice = true;
        // 男性正面点击监听
        onClickManBack(view);
        // 跳转到对应id身体区域
        gotoWhichBodyPart(view, partId);
    }

    private void onClickManBack(View v){
        if (isPlayVoice){
            PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);
            LogUtils.e("声音 back");
        }
        resetMenuState();
        switch (v.getId()) {
            case R.id.iv_man_nape:
                iv_man_nape.setImageDrawable(getResources().getDrawable(R.drawable.man_back_1_select));
                // 胸
                partId = 13;
                break;
            case R.id.iv_man_back:
                iv_man_back.setImageDrawable(getResources().getDrawable(R.drawable.man_back_2_select));
                // 腹
                partId = 14;
                break;
            case R.id.iv_man_Buttocks:
                iv_man_Buttocks.setImageDrawable(getResources().getDrawable(R.drawable.man_back_3_select));
                // 比基尼
                partId = 15;
                break;
            case R.id.iv_man_shoulder:
                iv_man_shoulder.setImageDrawable(getResources().getDrawable(R.drawable.man_back_4_select));
                // 大腿
                partId = 16;
                break;
        }
    }

    private void resetMenuState() {
        iv_man_nape.setImageDrawable(getResources().getDrawable(R.drawable.man_back_1));
        iv_man_back.setImageDrawable(getResources().getDrawable(R.drawable.man_back_2));
        iv_man_Buttocks.setImageDrawable(getResources().getDrawable(R.drawable.man_back_3));
        iv_man_shoulder.setImageDrawable(getResources().getDrawable(R.drawable.man_back_4));

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
        if (message.getCurrent_body_mode() != 2){//不是当前显示的身体部位
            resetMenuState();
        }
    }

    public void SetBodyDefaultType(){
        LogUtils.e("----背面 赋默认值");
        View view1 = new View(MyApplication.instance());
        mParam1 =R.id.iv_man_nape;
        view1.setId(mParam1);
        isPlayVoice = false;
        // 男性正面点击监听
        onClickManBack(view1);
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
                case 13:
                    mParam1 =R.id.iv_man_nape;
                    break;
                case 14:
                    mParam1 =R.id.iv_man_back;
                    break;
                case 15:
                    mParam1 =R.id.iv_man_Buttocks;
                    break;
                case 16:
                    mParam1 =R.id.iv_man_shoulder;
                    break;
            }
            View view1 = new View(MyApplication.instance());
            view1.setId(mParam1);
            isPlayVoice = false;
            // 男性正面点击监听
            onClickManBack(view1);
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
