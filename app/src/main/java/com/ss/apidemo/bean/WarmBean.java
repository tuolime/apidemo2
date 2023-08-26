package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* 上报告警
* */
public class WarmBean implements Serializable {
    /**
     * 告警类型 //warmType 1.短板短路  2.手具连接失败  3.水温高  4.水温低  5.水温低  6.无水流
     */
    private Integer warmType = 0;
    /**
     * 告警消息
     */
    private String warmMsg;

    public Integer getWarmType() {
        return warmType;
    }

    public void setWarmType(Integer warmType) {
        this.warmType = warmType;
    }

    public String getWarmMsg() {
        return warmMsg;
    }

    public void setWarmMsg(String warmMsg) {
        this.warmMsg = warmMsg;
    }
}
