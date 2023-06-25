package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* SHR Stack SHR HR 模式 下 选择手具后 对应的 频率Hz和单脉冲能量fluence
* * hz对应的单脉冲 且单脉冲就是进度条的最大值

 * */
public class ShrModeHzOrFluenceBean implements Serializable {


    public int handgearType;//手具类型

    public int handgearHzMin;//手具对应的频率最小

    public int handgearHzMax;//手具对应的频率最大

    public int hz;//频率

    public int fluenceMax;//Hz单脉冲能量最大值

    public int fluenceMin;//单脉冲能量最小值

    public int getHandgearHzMin() {
        return handgearHzMin;
    }

    public void setHandgearHzMin(int handgearHzMin) {
        this.handgearHzMin = handgearHzMin;
    }

    public int getHandgearHzMax() {
        return handgearHzMax;
    }

    public void setHandgearHzMax(int handgearHzMax) {
        this.handgearHzMax = handgearHzMax;
    }

    public int getHandgearType() {
        return handgearType;
    }

    public void setHandgearType(int handgearType) {
        this.handgearType = handgearType;
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
