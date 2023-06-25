package com.ss.apidemo.db.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class UserValue implements Serializable {
    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField
    private String tel;//用户手机号
    @DatabaseField
    private String gender;//性别
    @DatabaseField
    private String mode;//工作模式
    @DatabaseField
    private String skinType;//皮肤类型
    @DatabaseField
    private String bodyType;//身体部位
    @DatabaseField
    private String energy;//能量
    @DatabaseField
    private String frequency;//频率 就是 Hz
    @DatabaseField
    private String workCount;//工作次数
    @DatabaseField
    private String fluence;//单脉冲
    @DatabaseField
    private String date;//时间

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getWorkCount() {
        return workCount;
    }

    public void setWorkCount(String workCount) {
        this.workCount = workCount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFluence() {
        return fluence;
    }

    public void setFluence(String fluence) {
        this.fluence = fluence;
    }
}
