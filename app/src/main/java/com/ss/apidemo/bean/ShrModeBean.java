package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* SHR Stack SHR HR 模式 下 选择手具后 对应的 单脉冲能量
* */
public class ShrModeBean implements Serializable {


    public int handgearType;//手具类型

    public int fluence5HzMax;//5Hz单脉冲能量最大值

    public int fluence10HzMax;//10Hz单脉冲能量最大值

    public int fluenceMin;//单脉冲能量最小值


    public int getHandgearType() {
        return handgearType;
    }

    public void setHandgearType(int handgearType) {
        this.handgearType = handgearType;
    }

    public int getFluence5HzMax() {
        return fluence5HzMax;
    }

    public void setFluence5HzMax(int fluence5HzMax) {
        this.fluence5HzMax = fluence5HzMax;
    }

    public int getFluence10HzMax() {
        return fluence10HzMax;
    }

    public void setFluence10HzMax(int fluence10HzMax) {
        this.fluence10HzMax = fluence10HzMax;
    }

    public int getFluenceMin() {
        return fluenceMin;
    }

    public void setFluenceMin(int fluenceMin) {
        this.fluenceMin = fluenceMin;
    }
}
