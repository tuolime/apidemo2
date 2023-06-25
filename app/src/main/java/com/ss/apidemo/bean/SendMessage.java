package com.ss.apidemo.bean;

import java.io.Serializable;

public class SendMessage implements Serializable {
    public  String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    @Override
    public String toString() {
        return "SendMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
