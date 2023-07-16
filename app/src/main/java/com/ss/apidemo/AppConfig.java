package com.ss.apidemo;

public class AppConfig {
    public static final String ENGINEERPS = "222318";//工程师密码
    public static final String AUTOSHEDPS = "208502";//自助脱毛密码
    public static final String USERSETTINGPS = "592072";//用户设置密码
    public static final String USERSTARTPS = "256011";//用户开启导航栏密码
    // TODO Socket 正式地址
    public static String SOCKET_HOST = "www.beatycp.com";
    // TODO Socket 正式端口
    public static final int SOCKET_PORT = 8888;

    /**
     * 锁定状态，0否1是
     */
    public static  int lockStatus = 0;
    /**
     * 使用限制标识，0否1是
     */
    public static  int useLimitedFlag = 0;
    /**
     * 使用限制类型,天数小时和天
     */
    public static String useLimitedType;

    /**
     * 总使用天，时和次数
     */
    public static Integer totalUseNum = 0;

    /**
     * 使用天，时和次数
     */
    public static Integer useNum = 0;
    /**
     * 剩余天，时和次数
     */
    public static Integer remainNum = 0;

//    public static String current_ctrlCode = "";
//    public static String current_message = "";


    public static int current_count = 0;

    public static String KEY = "key.mp3";
    public static String WARM = "warm.mp3";
    public static  int defaultBody = 0;//参数界面选择的身体部位，用于到工作界面默认展示
    public static  int handgearSelect = 1;//是否切换手具端口 1 left 0 right

    public static final String WIFI = "WIFI";
    public static final String HANDGEAR = "HANDGEAR";//手具
//    public static final String COUNT = "COUNT";//计数
    public static final String SHED = "SHED";//脱毛
    /*
     * 1 I
     * 2 II
     * 3 III
     * 4 IV
     * 5 V
     * 6 VI
     *
     * */
    public static final String MODE_TWO_GB = "MODE_TWO_BG";//工作模式2 背景选择
    /*
     * 1 QB-6-10
     * 2 QB-6-16
     * 3 QB-6-20
     * 4 QB-2-10
     * 5 QB-2-16
     * 6 QB-2-24
     * 7 QB-4-24
     * 8 QB-*-30
     * 9 QB-6-200
     * 10 QB-6-40
     *
     * */
    public static final String HAND_LEFT = "HAND_LEFT";//左手手具
    /*
     * 1 QB-6-10
     * 2 QB-6-16
     * 3 QB-6-20
     * 4 QB-2-10
     * 5 QB-2-16
     * 6 QB-2-24
     * 7 QB-4-24
     * 8 QB-*-30
     * 9 QB-6-200
     * 10 QB-6-40
     *
     * */
    public static final String HAND_RIGHT = "HAND_RIGHT";//右手手具
    /*
    * 1 DY1200J
    * 2 DY1200F
    * 3 DY2450F
    * 4 DY3250F
    * 5 DY2450J
    * 6 DY3250J
    * 7 DY1800J
    * 8 DY2000J
    * 9 DY2000x
    *
    * */
    public static final String POWER_TYPE = "POWER_TYPE";//电源类型

    /*
    * 模式
    * 1  SHR STACK
    * 2  SHR
    * 3  HR
    * */
//    public static final String MODE_TYPE = "MODE_TYPE";

    /*
     * 肤色
     * 1  I
     * 2  II
     * 3  III
     * 4  IV
     * 5  V
     * 6  VI
     * */
//    public static final String SKIN_TYPE = "SKIN_TYPE";

    /*
     * 尺码
     * 1  s 25cm
     * 2  m 50cm
     * 3  l 100cm
     * 4  xl 300cm
     * */
//    public static final String SIZE_TYPE = "SIZE_TYPE";

    //-----------------------用户设置 温度 水流 计数 ---------------------------

    public static final String TEMPERATURE_COUNT = "TEMPERATURE_COUNT";
    public static final String WATER_COUNT = "WATER_COUNT";
    public static final String TEMPERATURE = "TEMPERATURE";
    public static final String WATER = "WATER";
    public static final String USERDATADOWMLOAD = "USERDATADOWMLOAD";
    public static final String MODETYPE = "MODETYPE";
    public static final String WLAN = "WLAN";
    public static final String BACKGROUNDSELECT = "BACKGROUNDSELECT";
    public static final String BLUETOOTH = "BLUETOOTH";
    public static final String ENERGYUPPER = "ENERGYUPPER";
    public static final String ENERGYLOWER = "ENERGYLOWER";
    public static final String GENDER = "GENDER";//性别 1 男  2 女
//    public static final String COUNT_CLEAR = "COUNT_CLEAR";


    public static int AUTOSHEDTIME;
    public static int INFINITE = 10000;
    public static int isDisconnect = 0;

    /*
     * 背景色
     * */
    public static final String BACKGROUND_COLOR = "0";
}
