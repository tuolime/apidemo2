package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* SHR Stack SHR HR 模式 下 选择手具后 对应的 单脉冲能量
* 肤色 及 选中身体的部位  设置建议的脉冲最大值和最小值
* */
public class HrSkinBean implements Serializable {

    public int skinType;//肤色类型

    public int bodyType;//身体部位

    public int fluenceProposal;//单脉冲能量建议值

    public int freQuencyProposal;//HZ




    public int getSkinType() {
        return skinType;
    }

    public void setSkinType(int skinType) {
        this.skinType = skinType;
    }

    public int getBodyType() {
        return bodyType;
    }

    public void setBodyType(int bodyType) {
        this.bodyType = bodyType;
    }

    public int getFluenceProposal() {
        return fluenceProposal;
    }

    public void setFluenceProposal(int fluenceProposal) {
        this.fluenceProposal = fluenceProposal;
    }

    public int getFreQuencyProposal() {
        return freQuencyProposal;
    }

    public void setFreQuencyProposal(int freQuencyProposal) {
        this.freQuencyProposal = freQuencyProposal;
    }
}
