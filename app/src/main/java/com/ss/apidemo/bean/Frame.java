package com.ss.apidemo.bean;

import java.io.Serializable;

public class Frame implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 0 终端登录主站，1主站应答（下发使用限制参数）， 2 主站下发锁定命令， 3 终端上传发数
     * 心跳 app发送 type 0 和 设备定位地址terminalAddress  设备id
     * 心跳 服务器应答 type 1 和 锁定状态lockStatus 、 使用限制标识useLimitedFlag
     * 服务器下发  tyoe 2的时候我直接给你一个type和useLimitedFlag
     * app发送   type 3 上传次数reportUseNum 设备id
     * <p>
     * 传输 对象json串
     */
    private int type;

    /**
     * 设备编号
     */
    private String terminalCode;

    /**
     * 设备定位地址
     */
    private String terminalAddress;

    /**
     * 锁定状态，lock unlock
     */
    private String lockStatus;

    /**
     * 使用限制类型,天数小时和天
     */
    private String useLimitedType;

    /**
     * 设备总使用次数
     */
    private Integer totalUseNum = 0;

    /**
     * 设备总使用天数
     */
    private Integer totalUseDays = 0;

    /**
     * 使用天，时和次数
     */
    private Integer useNum = 0;
    /**
     * 剩余天，时和次数
     */
    private Integer remainNum = 0;

    /**
     * 使用限制标识，0否1是
     */
    private String useLimitedFlag;
    /**
     * 上报次数
     */
    private Integer reportUseNum = 0;



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getTerminalAddress() {
        return terminalAddress;
    }

    public void setTerminalAddress(String terminalAddress) {
        this.terminalAddress = terminalAddress;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getUseLimitedType() {
        return useLimitedType;
    }

    public void setUseLimitedType(String useLimitedType) {
        this.useLimitedType = useLimitedType;
    }

    public Integer getTotalUseNum() {
        return totalUseNum;
    }

    public void setTotalUseNum(Integer totalUseNum) {
        this.totalUseNum = totalUseNum;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public String getUseLimitedFlag() {
        return useLimitedFlag;
    }

    public void setUseLimitedFlag(String useLimitedFlag) {
        this.useLimitedFlag = useLimitedFlag;
    }

    public Integer getReportUseNum() {
        return reportUseNum;
    }

    public void setReportUseNum(Integer reportUseNum) {
        this.reportUseNum = reportUseNum;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public Integer getTotalUseDays() {
        return totalUseDays;
    }

    public void setTotalUseDays(Integer totalUseDays) {
        this.totalUseDays = totalUseDays;
    }
}
