package com.ss.apidemo.utils;

/**
 * Created by wu on 2016/5/21.
 */

import android.content.Context;

import com.ss.apidemo.MyApplication;

/**
 * 偏好参数存储工具类
 */
public class SharedPrefsUtil {

    /**
     * 数据存储的XML名称
     **/
    private final static String NAME = "rs232";


    /**
     * 存储数据(Long)
     */
    public static void putLongValue(String key, long value) {
        MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).edit().putLong(key, value).commit();
    }

    /**
     * 存储数据(Int)
     */
    public static void putIntValue(String key, int value) {
        MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
    }

    /**
     * 存储数据(Int)
     */
    public static void putFloatValue(String key, float value) {
        MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).edit().putFloat(key, value).commit();
    }

    /**
     * 取出数据(Long)
     */
    public static float getFloatValue(String key, float defValue) {
        return MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).getFloat(key, defValue);
    }

    /**
     * 存储数据(String)
     */
    public static void putStringValue(String key, String value) {
        if (value == null) {
            value = "";
        }
        MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    /**
     * 存储数据(boolean)
     */
    public static void putBooleanValue(String key,
                                       boolean value) {
        MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
    }

    /**
     * 取出数据(String)
     */
    public static String getStringValue(String key,
                                        String defValue) {
        return MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).getString(key, defValue);
    }

    /**
     * 取出数据(Long)
     */
    public static long getLongValue(String key, long defValue) {
        return MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).getLong(key, defValue);
    }

    /**
     * 取出数据(int)
     */
    public static int getIntValue(String key, int defValue) {
        return MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).getInt(key, defValue);
    }

    /**
     * 取出数据(boolean)
     */
    public static boolean getBooleanValue(String key,
                                          boolean defValue) {
        return MyApplication.instance().getSharedPreferences(NAME, Context.MODE_PRIVATE).getBoolean(key, defValue);
    }


    /**
     * 清空所有数据
     */
    public static void clear(Context context) {
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit().clear().commit();
    }


}