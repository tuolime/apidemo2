package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* 脱毛倒计时时间
* */
public class SendTimeBean implements Serializable {
    public int time;

    public SendTimeBean(int time) {
        this.time = time;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
