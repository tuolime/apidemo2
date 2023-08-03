package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* 智能模式 100  下 选择手具后 对应的 单脉冲能量
* */
public class HundredModeBean implements Serializable {


    public int handgearType;//手具类型

    public int modeType;//模式类型 8

    public int hz;//手具对应的频率最小

    public int fluenceMax;//5Hz单脉冲能量最大值

    public int fluenceMin;//单脉冲能量最小值

    public int getHandgearType() {
        return handgearType;
    }

    public void setHandgearType(int handgearType) {
        this.handgearType = handgearType;
    }

    public int getModeType() {
        return modeType;
    }

    public void setModeType(int modeType) {
        this.modeType = modeType;
    }

    public int getHz() {
        return hz;
    }

    public void setHz(int hz) {
        this.hz = hz;
    }

    public int getFluenceMax() {
        return fluenceMax;
    }

    public void setFluenceMax(int fluenceMax) {
        this.fluenceMax = fluenceMax;
    }

    public int getFluenceMin() {
        return fluenceMin;
    }

    public void setFluenceMin(int fluenceMin) {
        this.fluenceMin = fluenceMin;
    }
}
