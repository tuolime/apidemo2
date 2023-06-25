package com.ss.apidemo.widget;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class BrightnessUtil {
    /**
     *获取系统屏幕亮度
     */
    public static int getBrightness(Context context) {
        int brightness = -1;
        ContentResolver resolver = context.getContentResolver();
        try {
            brightness = Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("ccm=======>","getBrightness: "+brightness);
        return brightness;
    }

    /**
     *获取系统最大屏幕亮度
     */
    public static int getMaxBrightness(Context context) {
        int brightnessSettingMaximumId = context.getResources().getIdentifier("config_screenBrightnessSettingMaximum", "integer", "android");
        int brightnessSettingMaximum = context.getResources().getInteger(brightnessSettingMaximumId);
        Log.e("ccm=======>","getMaxBrightness: "+brightnessSettingMaximum);
        return brightnessSettingMaximum;
    }

    /**
     *调节当前屏幕亮度
     */
    public static void SetSystemLight(int lightnumber, Activity activity){
        Window window = activity.getWindow();//对当前窗口进行设置
        WindowManager.LayoutParams layoutparams = window.getAttributes();//获取窗口属性为后面亮度做铺垫作用
        layoutparams.screenBrightness =lightnumber / 255.0f;//用窗口管理（自定义的）layoutparams获取亮度值，android亮度值处于在0-255之间的整形数值
        window.setAttributes(layoutparams);//设置当前窗口屏幕亮度
    }

    /**
     * 5.修改Setting 中屏幕亮度值
     *
     * 修改Setting的值需要动态申请权限 <uses-permission
     * android:name="android.permission.WRITE_SETTINGS"/>
     * **/
    public static void ModifySettingsScreenBrightness(Context context, int birghtessValue) {
        // 首先需要设置为手动调节屏幕亮度模式
        setScreenManualMode(context);

        ContentResolver contentResolver = context.getContentResolver();
        Log.e("ccm=======>", "birghtessValue: "+birghtessValue);
        Settings.System.putInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, birghtessValue);
    }


    /**
     * 3.关闭光感，设置手动调节背光模式
     *
     * SCREEN_BRIGHTNESS_MODE_AUTOMATIC 自动调节屏幕亮度模式值为1
     *
     * SCREEN_BRIGHTNESS_MODE_MANUAL 手动调节屏幕亮度模式值为0
     * **/
    public static void setScreenManualMode(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver,
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

}
