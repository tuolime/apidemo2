package com.example.protocol.frame.data;


public class SetWarningConfig {
    /**
     * 温度
     */
    private int temperature;

    /**
     * 温度报警设置
     */
    private int temperatureWarningFlag;

    /**
     * 水流
     */
    private int flowVelocity;

    /**
     * 水流报警设置
     */
    private int flowVelocityWarningFlag;
    /**
     * 重置计数器
     */
    private int resetTotalCountFlag;


    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperatureWarningFlag() {
        return temperatureWarningFlag;
    }

    public void setTemperatureWarningFlag(int temperatureWarningFlag) {
        this.temperatureWarningFlag = temperatureWarningFlag;
    }

    public int getFlowVelocity() {
        return flowVelocity;
    }

    public void setFlowVelocity(int flowVelocity) {
        this.flowVelocity = flowVelocity;
    }

    public int getFlowVelocityWarningFlag() {
        return flowVelocityWarningFlag;
    }

    public void setFlowVelocityWarningFlag(int flowVelocityWarningFlag) {
        this.flowVelocityWarningFlag = flowVelocityWarningFlag;
    }

    public int getResetTotalCountFlag() {
        return resetTotalCountFlag;
    }

    public void setResetTotalCountFlag(int resetTotalCountFlag) {
        this.resetTotalCountFlag = resetTotalCountFlag;
    }

    @Override
    public String toString() {
        return "SetWarningConfig{" +
                "temperature=" + temperature +
                ", temperatureWarningFlag=" + temperatureWarningFlag +
                ", flowVelocity=" + flowVelocity +
                ", flowVelocityWarningFlag=" + flowVelocityWarningFlag +
                ", resetTotalCountFlag=" + resetTotalCountFlag +
                '}';
    }
}
