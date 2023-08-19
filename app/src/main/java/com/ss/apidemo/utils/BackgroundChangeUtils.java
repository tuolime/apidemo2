package com.ss.apidemo.utils;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.R;

/**
 * @Author gjl
 * @Date 2023-07-11 16:41
 * 背景图片切换
 **/
public class BackgroundChangeUtils {

    public static void backgroundChange(Context context,LinearLayout linearLayout){
        int intValue = SharedPrefsUtil.getIntValue(AppConfig.BACKGROUNDSELECT, 1);//第一次默认设置为亮色主题
        if (intValue == 1) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background1));
        }else if (intValue == 2) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background2));
        }else if (intValue == 3) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background3));
        }else if (intValue == 4) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background4));
        }

    }

    public static void backgroundLoginChange(Context context, RelativeLayout linearLayout){
        int intValue = SharedPrefsUtil.getIntValue(AppConfig.BACKGROUNDSELECT, 1);//第一次默认设置为亮色主题
        if (intValue == 1) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_login_background1));
        }else if (intValue == 2) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background2));
        }else if (intValue == 3) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background3));
        }else if (intValue == 4) {
            linearLayout.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_background4));
        }

    }
}
