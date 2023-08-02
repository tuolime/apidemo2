package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* 专家模式 下 选择手具后 对应的 单脉冲能量
* */
public class ExpertModeBean implements Serializable {


    public int handgearType;//手具类型

    public int hzProposal;//Hz默认

    public int fluenceProposal;//单脉冲能量默认

    public int getHandgearType() {
        return handgearType;
    }

    public void setHandgearType(int handgearType) {
        this.handgearType = handgearType;
    }

    public int getHzProposal() {
        return hzProposal;
    }

    public void setHzProposal(int hzProposal) {
        this.hzProposal = hzProposal;
    }

    public int getFluenceProposal() {
        return fluenceProposal;
    }

    public void setFluenceProposal(int fluenceProposal) {
        this.fluenceProposal = fluenceProposal;
    }
}
