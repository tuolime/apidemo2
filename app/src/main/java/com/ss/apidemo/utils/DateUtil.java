package com.ss.apidemo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String YYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"; // 长日期格式

    public static String YYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS"; // 年月日时分秒毫秒

    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            //  format = "yyyy-MM-dd HH:mm:ss";
            //  format = "hh:mm:ss";
            format = "HH:mm";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        Date date = new Date(time * 1000);
        return simpleDateFormat.format(date);

       /* SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));*/
    }

    /*
     * 将时间戳转换为时间
     */
    public String stampToDate(long timeMillis){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(timeMillis*1000);
        return simpleDateFormat.format(date);
    }

    public static String timeStampWlDate(long time, String format) {
        if (format == null || format.isEmpty()) {
            //  format = "yyyy-MM-dd HH:mm:ss";
            //  format = "hh:mm:ss";
            format = "HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        Date date = new Date(time * 1000);
        return simpleDateFormat.format(date);

       /* SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));*/
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYY_MM_DD_HH_MM_SS);
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前时间及毫秒
     * @return
     */
    public static String getNowMsTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYY_MM_DD_HH_MM_SS_SSS);
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * date时间转成时间戳
     * @return
     */
    public static String date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 获取时间戳
     *
     * @return 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(YYY_MM_DD_HH_MM_SS);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    //时间转换
    public static String getCurrentMinuteString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(date);
    }

    //时间转换
    public static String getCurrentDayString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 获取时间戳
     *
     * @return 获取时间戳
     */
    public static long getTime() {
        long time = new Date().getTime();
        return time;
    }

    /**
     * 时间的处理
     *
     * @param time
     * @return
     */
    public static String getTimeFromInt(int time) {

        if (time <= 0) {
            return "0:00";
        }
        int secondnd = (time / 1000) / 60;
        int million = (time / 1000) % 60;
        String f = String.valueOf(secondnd);
        String m = million >= 10 ? String.valueOf(million) : "0"
                + String.valueOf(million);
        return f + ":" + m;
    }
}
