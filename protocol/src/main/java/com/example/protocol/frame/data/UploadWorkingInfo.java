package com.example.protocol.frame.data;


import java.io.Serializable;

public class UploadWorkingInfo implements Serializable {

    /**
     * 工作模式
     */
    private int workingModel;

    /**
     * 单脉冲能量
     */
    private int fluence;

    /**
     * 频率
     */
    private int frequency;

    /**
     * 总计数
     */
    private int toalCount;

    /**
     * 本次工作时间
     */
    private int workingTime;

    /**
     * 总能量
     */
    private int totalEnergy;

    /**
     * 温度
     */
    private int temperature;

    /**
     * 水流
     */
    private int flowVelocity;

    /**
     * 工作状态
     */
    private int workingStatus;

    /**
     * 脚踏短路
     */
    private int shortCircuited;

    /**
     * 手具连接失败
     */
    private int qbConnFail;

    /**
     * 水温高
     */
    private int temperatureHi;

    /**
     * 水温低
     */
    private int temperatureLow;

    /**
     * 水流低
     */
    private int flowVelocityLow;


    /**
     * 无水流
     */
    private int noFlowVelocity;

    public int getWorkingModel() {
        return workingModel;
    }

    public void setWorkingModel(int workingModel) {
        this.workingModel = workingModel;
    }

    public int getFluence() {
        return fluence;
    }

    public void setFluence(int fluence) {
        this.fluence = fluence;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getToalCount() {
        return toalCount;
    }

    public void setToalCount(int toalCount) {
        this.toalCount = toalCount;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public int getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(int totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getFlowVelocity() {
        return flowVelocity;
    }

    public void setFlowVelocity(int flowVelocity) {
        this.flowVelocity = flowVelocity;
    }

    public int getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(int workingStatus) {
        this.workingStatus = workingStatus;
    }

    public int getShortCircuited() {
        return shortCircuited;
    }

    public void setShortCircuited(int shortCircuited) {
        this.shortCircuited = shortCircuited;
    }

    public int getQbConnFail() {
        return qbConnFail;
    }

    public void setQbConnFail(int qbConnFail) {
        this.qbConnFail = qbConnFail;
    }

    public int getTemperatureHi() {
        return temperatureHi;
    }

    public void setTemperatureHi(int temperatureHi) {
        this.temperatureHi = temperatureHi;
    }

    public int getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(int temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public int getFlowVelocityLow() {
        return flowVelocityLow;
    }

    public void setFlowVelocityLow(int flowVelocityLow) {
        this.flowVelocityLow = flowVelocityLow;
    }

    public int getNoFlowVelocity() {
        return noFlowVelocity;
    }

    public void setNoFlowVelocity(int noFlowVelocity) {
        this.noFlowVelocity = noFlowVelocity;
    }

    @Override
    public String toString() {
        return "UploadWorkingInfo{" +
                "workingModel=" + workingModel +
                ", fluence=" + fluence +
                ", frequency=" + frequency +
                ", toalCount=" + toalCount +
                ", workingTime=" + workingTime +
                ", totalEnergy=" + totalEnergy +
                ", temperature=" + temperature +
                ", flowVelocity=" + flowVelocity +
                ", workingStatus=" + workingStatus +
                ", shortCircuited=" + shortCircuited +
                ", qbConnFail=" + qbConnFail +
                ", temperatureHi=" + temperatureHi +
                ", temperatureLow=" + temperatureLow +
                ", flowVelocityLow=" + flowVelocityLow +
                ", noFlowVelocity=" + noFlowVelocity +
                '}';
    }
}
