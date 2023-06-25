package com.ss.apidemo.bean;

import java.io.Serializable;

public class QueueMessage implements Serializable {
    public  String message;
    public String ctrlCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCtrlCode() {
        return ctrlCode;
    }

    public void setCtrlCode(String ctrlCode) {
        this.ctrlCode = ctrlCode;
    }

    @Override
    public String toString() {
        return "QueueMessage{" +
                "message='" + message + '\'' +
                ", ctrlCode='" + ctrlCode + '\'' +
                '}';
    }
}
