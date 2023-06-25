package com.ss.apidemo.bean;

/*
* SHR Stack SHR HR 模式 下 选择手具后 对应的 单脉冲能量
* */
public class ModeType {

    public int modeType;//模式类型

    public int handgearType;//手具类型

    public int fluenceMax;//单脉冲能量最大值

    public int fluenceMin;//单脉冲能量最小值

    public int getModeType() {
        return modeType;
    }

    public void setModeType(int modeType) {
        this.modeType = modeType;
    }

    public int getHandgearType() {
        return handgearType;
    }

    public void setHandgearType(int handgearType) {
        this.handgearType = handgearType;
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
