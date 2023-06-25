package com.ss.apidemo.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * 获取系统设备信息的工具类
 *
 * @author dty
 */
public class DeviceInfoUtil {
    private static final String TAG = "DeviceInfoUtil";

    /* 获取手机唯一序列号 */
    public static String getDeviceId(Context context) {

        String deviceId = makeDeviceId(context);
        return deviceId;
    }

    @Nullable
    @SuppressLint("HardwareIds")
    private static String makeDeviceId(Context context) {
        String deviceId = null;
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = tm.getDeviceId();// 手机设备ID，这个ID会被用为用户访问统计
            }
        } catch (Exception ignored) {
            // deviceId越来越严格了，以防万一，
            deviceId = "";
        }
        LogUtils.d(TAG, "makeDeviceId() returned: " + deviceId);
        return deviceId;
    }

    public static String getSerialNumber(){

        String serial = null;

        try {

            Class<?> c =Class.forName("android.os.SystemProperties");

            Method get =c.getMethod("get", String.class);

            serial = (String)get.invoke(c, "ro.serialno");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return serial;

    }

    public static String getMac() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);


            for (; null != str;) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        String[] split = macSerial.split(":");
        String strMacSerial="";
        if (split.length >0){
            for (int i = 0; i <split.length ; i++) {
                strMacSerial+=split[i];
            }
        }

        return strMacSerial.trim();
    }

    /**
     * 获取CPU序列号
     *
     * @return CPU序列号(16位)
     * 读取失败为"0000000000000000"
     */
    public static String getCPUSerial() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Build.getSerial();
        }
        //读取CPU信息
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String cpu = null;
        try {
            Process process = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            inputStreamReader = new InputStreamReader(process.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((cpu = bufferedReader.readLine()) != null) {
                if (cpu.contains("Serial")) {
                    cpu = cpu.substring(cpu.indexOf(":") + 1).trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cpu != null ? cpu.toUpperCase() : "0000000000000000";
    }
}
