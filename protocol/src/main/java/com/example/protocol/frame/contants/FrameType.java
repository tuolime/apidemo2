package com.example.protocol.frame.contants;


import java.util.ArrayList;
import java.util.List;

public enum FrameType {

    SET_WORDING_STATUS("55","setWorkingStatus", "设置工作状态"),
    SET_DEVICE_CONFIG("5A","setDeviceConfig", "工程师配置设备设置"),
    SET_WARNING_CONFIG("5B","setWarningConfig", "用户配置报警设置"),
    UPLOAD_WORKING_INFO("40","uploadWorkingInfo", "设备上传工作信息");

    private String key;
    private String code;
    private String name;

    FrameType(String key, String code, String name) {
        this.key = key;
        this.code = code;
        this.name = name;
    }

    public String getKey() {
        return key;
    }
    public String getInfo() {
        return code;
    }
    public static FrameType get(String key) {
        for (FrameType commProtocolType : FrameType.values()) {
            if (commProtocolType.getKey() == key) {
                return commProtocolType;
            }
        }
        return null;
    }

    public static String getInfo(String value) {
        for (FrameType commProtocolType : FrameType.values()) {
            if (commProtocolType.getKey() == value) {
                return commProtocolType.getInfo();
            }
        }
        return "";
    }

    public static String getKey(String info) {
        for (FrameType commProtocolType : FrameType.values()) {
            if (commProtocolType.getInfo().equals(info)) {
                return commProtocolType.getKey();
            }
        }
        return null;
    }

    public static List<String> getInfoList() {
        List<String> list = new ArrayList<>();
        for (FrameType e : FrameType.values()) {
            list.add(e.getInfo());
        }
        return list;
    }
}
