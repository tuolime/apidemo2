package com.ss.apidemo.bean;

import java.io.Serializable;

/*
* SHR Stack SHR HR 模式 下 选择手具后 对应的 单脉冲能量
* */
public class CommonBodyBean implements Serializable {


    public int current_body_mode;//当前身体选择是 头 正面 还是背面，关系互斥

    public int getCurrent_body_mode() {
        return current_body_mode;
    }

    public void setCurrent_body_mode(int current_body_mode) {
        this.current_body_mode = current_body_mode;
    }
}
