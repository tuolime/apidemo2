package com.ss.apidemo.utils;

import android.content.Context;
import android.widget.LinearLayout;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.R;

/**
 * @Author gjl
 * @Date 2023-07-11 16:41
 * 背景图片切换
 **/
public class BackgroundChangeUtils {

    public static void backgroundChange(Context context,LinearLayout linearLayout){
        int intValue = SharedPrefsUtil.getIntValue(AppConfig.BACKGROUND_COLOR, 0);//第一次默认设置为亮色主题
        if (intValue == 0) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background));
        }else if (intValue == 1) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background));
        }else if (intValue == 2) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background));
        }else if (intValue == 3) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background));
        }

    }
}
