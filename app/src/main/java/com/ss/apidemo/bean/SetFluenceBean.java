package com.ss.apidemo.bean;

import java.io.Serializable;

/*
 * 单脉冲能量改变 下发报文
 * */
public class SetFluenceBean implements Serializable {
    private int fluence;

    public int getFluence() {
        return fluence;
    }

    public void setFluence(int fluence) {
        this.fluence = fluence;
    }
}
