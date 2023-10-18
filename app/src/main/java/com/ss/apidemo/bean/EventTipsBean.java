package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* Myapplication里不能使用文字资源 否则多语言不翻译
* */
public class EventTipsBean implements Serializable {
    public int type;

    public EventTipsBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
