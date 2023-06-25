package com.example.protocol.frame.data;


public class SetWorkingStatus {

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
     * 总能量
     */
    private int totalEnergy;

    /**
     * 本次工作总时间值 SHR 模式下 Time 时间
     */
    private int workingTime;

    /**
     * 手具选择设置（低8位）
     */
    private int qbConfig;


    /**
     * 手具切换设置（高8位）
     */
    private int changeQbPortFlag;

    /**
     * 工作状态
     */
    private int workingStatus;

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

    public int getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(int totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public int getQbConfig() {
        return qbConfig;
    }

    public void setQbConfig(int qbConfig) {
        this.qbConfig = qbConfig;
    }

    public int getChangeQbPortFlag() {
        return changeQbPortFlag;
    }

    public void setChangeQbPortFlag(int changeQbPortFlag) {
        this.changeQbPortFlag = changeQbPortFlag;
    }

    public int getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(int workingStatus) {
        this.workingStatus = workingStatus;
    }

    @Override
    public String toString() {
        return "SetWorkingStatus{" +
                "workingModel=" + workingModel +
                ", fluence=" + fluence +
                ", frequency=" + frequency +
                ", totalEnergy=" + totalEnergy +
                ", workingTime=" + workingTime +
                ", qbConfig=" + qbConfig +
                ", changeQbPortFlag=" + changeQbPortFlag +
                ", workingStatus=" + workingStatus +
                '}';
    }
}