package com.example.protocol.frame.data;


public class SetDeviceConfig {

    /**
     * 电源设置
     */
    private int powerType;

    /**
     * 手具设置
     */
    private int qbflag;

    /**
     * 喇叭设置
     */
    private int hornFlag;

    /**
     * 蓝牙设置
     */
    private int bluetoothFlag;

    public int getPowerType() {
        return powerType;
    }

    public void setPowerType(int powerType) {
        this.powerType = powerType;
    }

    public int getQbflag() {
        return qbflag;
    }

    public void setQbflag(int qbflag) {
        this.qbflag = qbflag;
    }

    public int getHornFlag() {
        return hornFlag;
    }

    public void setHornFlag(int hornFlag) {
        this.hornFlag = hornFlag;
    }

    public int getBluetoothFlag() {
        return bluetoothFlag;
    }

    public void setBluetoothFlag(int bluetoothFlag) {
        this.bluetoothFlag = bluetoothFlag;
    }

    @Override
    public String toString() {
        return "SetDeviceConfig{" +
                "powerType=" + powerType +
                ", qbflag=" + qbflag +
                ", hornFlag=" + hornFlag +
                ", bluetoothFlag=" + bluetoothFlag +
                '}';
    }
}
