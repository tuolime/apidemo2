package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* SHR Stack SHR HR 模式 下 选择手具后 对应的 单脉冲能量
* */
public class CommonBean implements Serializable {

    public int handgearType;//手具类型

    public int gender;//性别

    public int mode;//模式

    public int sink;//肤色

    public int size;//面积

    public int body;//部位

    public String tel;//传递过来的用户手机号

    public int bodyMode;//身体 0 头 1 身体  2 背面

    public int getHandgearType() {
        return handgearType;
    }

    public void setHandgearType(int handgearType) {
        this.handgearType = handgearType;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getSink() {
        return sink;
    }

    public void setSink(int sink) {
        this.sink = sink;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getBodyMode() {
        return bodyMode;
    }

    public void setBodyMode(int bodyMode) {
        this.bodyMode = bodyMode;
    }
}
