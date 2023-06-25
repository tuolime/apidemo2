package com.example.protocol.frame;



import com.example.protocol.frame.entity.DataItem;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class Frame {

    /**
     * 报文数组
     */
    private byte[] bytes;
    /**
     * 数据域长度
     */
    private int dataLen;

    /**
     * 当前报文索引
     */
    private int index;

    /**
     * 报文编号。
     */
    private long frameNo;

    /**
     * 16进制报文内容
     */
    private byte[] frame;

    /**
     * 采集项数据
     */
    private byte[] frameData;

    /**
     * 报文长度
     */
    private int frameLen = 0;

    /**
     * 报文控制域编码
     */
    private String ctrlCode;

    /**
     * 采集时间
     */
    private Timestamp cTime;

    /**
     * 采集项FN
     */
    private List<DataItem> dataItems;

    private Object dataValue;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getDataLen() {
        return dataLen;
    }

    public void setDataLen(int dataLen) {
        this.dataLen = dataLen;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(long frameNo) {
        this.frameNo = frameNo;
    }

    public byte[] getFrame() {
        return frame;
    }

    public void setFrame(byte[] frame) {
        this.frame = frame;
    }

    public byte[] getFrameData() {
        return frameData;
    }

    public void setFrameData(byte[] frameData) {
        this.frameData = frameData;
    }

    public int getFrameLen() {
        return frameLen;
    }

    public void setFrameLen(int frameLen) {
        this.frameLen = frameLen;
    }

    public String getCtrlCode() {
        return ctrlCode;
    }

    public void setCtrlCode(String ctrlCode) {
        this.ctrlCode = ctrlCode;
    }

    public Timestamp getcTime() {
        return cTime;
    }

    public void setcTime(Timestamp cTime) {
        this.cTime = cTime;
    }

    public List<DataItem> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<DataItem> dataItems) {
        this.dataItems = dataItems;
    }

    public Object getDataValue() {
        return dataValue;
    }

    public void setDataValue(Object dataValue) {
        this.dataValue = dataValue;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "bytes=" + Arrays.toString(bytes) +
                ", dataLen=" + dataLen +
                ", index=" + index +
                ", frameNo=" + frameNo +
                ", frame=" + Arrays.toString(frame) +
                ", frameData=" + Arrays.toString(frameData) +
                ", frameLen=" + frameLen +
                ", ctrlCode='" + ctrlCode + '\'' +
                ", cTime=" + cTime +
                ", dataItems=" + dataItems +
                ", dataValue=" + dataValue +
                '}';
    }
}
