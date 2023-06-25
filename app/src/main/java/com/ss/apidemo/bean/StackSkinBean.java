package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* SHR Stack SHR HR 模式 下 选择手具后 对应的 单脉冲能量
* 肤色 及 选中身体的部位  设置建议的脉冲最大值和最小值
* */
public class StackSkinBean implements Serializable {

    public int skinType;//肤色类型

    public int bodyType;//身体部位

    public int stack;//

    public int energy;//能量

    public int bodyType_HZ;//身体部位对应的 HZ

    public int fluenceProposal;//单脉冲能量建议值


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

    public int getBodyType_HZ() {
        return bodyType_HZ;
    }

    public void setBodyType_HZ(int bodyType_HZ) {
        this.bodyType_HZ = bodyType_HZ;
    }

    public int getFluenceProposal() {
        return fluenceProposal;
    }

    public void setFluenceProposal(int fluenceProposal) {
        this.fluenceProposal = fluenceProposal;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
