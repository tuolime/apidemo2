package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* 智能模式 下auto 30  100  400  对应的 单脉冲能量
* */
public class SmartModeBean implements Serializable {


    public int hzProposal;//Hz默认

    public int fluenceProposal;//单脉冲能量默认


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
