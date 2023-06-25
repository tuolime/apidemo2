package com.example.protocol.frame;


import android.util.Log;

import com.example.protocol.exception.ParseException;
import com.example.protocol.frame.data.SetDeviceConfig;
import com.example.protocol.frame.data.SetWarningConfig;
import com.example.protocol.frame.data.SetWorkingStatus;
import com.example.protocol.frame.entity.DataItem;
import com.example.protocol.utils.ByteUtil;
import com.example.protocol.utils.ParserUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hutool.core.util.HexUtil;

public class ProtocalHandler {
    public  byte[] frameData;

    public  int index;
    private Parser dataObj;

    public static final int FRAME_HEAD = 0xAA; // 帧开始符
    public static final int FRAME_END_CC = 0xCC; // 帧结束符2
    public static final int FRAME_END_33 = 0x33; // 帧结束符1

    public static int FRAME_HEAD_LEN = 6; // 报文控制码长度（帧开始符，帧控制符，数据长度，帧结束符）
    public static int FRAME_START_HEAD_LEN = 3; // 帧开始符长度 + 控制位

    public static final String[] CTRL = {"40", "55", "5A", "5B"};
    private static ProtocalHandler protocalHandler;
    public static  ProtocalHandler getInstance(){
        if (protocalHandler == null){
            protocalHandler = new ProtocalHandler();
        }
        return protocalHandler;
    }

    public Frame buildSetWorkingStatusFrame(SetWorkingStatus setWorkingStatus) {
        Frame frame = new Frame();
        byte[] frameBytes = new byte[21];
        frameBytes[0] = (byte) FRAME_HEAD;
        frameBytes[1] = (byte) 0x55; // c
        frameBytes[2] = 18; // 长度
        frameBytes[19] = (byte) FRAME_END_CC;
        frameBytes[20] = (byte) FRAME_END_33;
        // 工作模式
        System.arraycopy(ByteUtil.intToByteArray2(setWorkingStatus.getWorkingModel()), 0, frameBytes, 3, 2);

        // 能量
        System.arraycopy(ByteUtil.intToByteArray2(setWorkingStatus.getFluence()), 0, frameBytes, 5, 2);

        // 脉宽值
        frameBytes[7] = 0;
        frameBytes[8] = 0;

        // 频率
        System.arraycopy(ByteUtil.intToByteArray2(setWorkingStatus.getFrequency()), 0, frameBytes, 9, 2);

        // 总能量值
        System.arraycopy(ByteUtil.intToByteArray2(setWorkingStatus.getTotalEnergy()), 0, frameBytes, 11, 2);

        // 本次工作总时间值 SHR 模式下 Time 时间
        System.arraycopy(ByteUtil.intToByteArray2(setWorkingStatus.getWorkingTime()), 0, frameBytes, 13, 2);

        //手具选择设置（低8位） + 手具切换设置（高8位）
        System.arraycopy(ByteUtil.intToByteArray2((setWorkingStatus.getChangeQbPortFlag() << 8) + setWorkingStatus.getQbConfig()), 0, frameBytes, 15, 2);

        // 工作状态
        System.arraycopy(ByteUtil.intToByteArray2(setWorkingStatus.getWorkingStatus()), 0, frameBytes, 17, 2);
        frame.setCtrlCode( Integer.toHexString(frameBytes[1] & 0xFF).toUpperCase());
        frame.setFrame(frameBytes);
        return frame;
    }

    public Frame buildSetDeviceConfigFrame(SetDeviceConfig setDeviceConfig) {
        Frame frame = new Frame();
        byte[] frameBytes = new byte[13];
        frameBytes[0] = (byte) FRAME_HEAD;
        frameBytes[1] = (byte) 0x5A; // c
        frameBytes[2] = 10; // 长度
        frameBytes[11] = (byte) FRAME_END_CC;
        frameBytes[12] = (byte) FRAME_END_33;

        // 电源设置
        System.arraycopy(ByteUtil.intToByteArray2(setDeviceConfig.getPowerType()), 0, frameBytes, 3, 2);

        // 手具设置
        System.arraycopy(ByteUtil.intToByteArray2(setDeviceConfig.getQbflag()), 0, frameBytes, 5, 2);

        //  喇叭设置
        System.arraycopy(ByteUtil.intToByteArray2(setDeviceConfig.getHornFlag()), 0, frameBytes, 7, 2);

        // 保留
        frameBytes[9] = 0;
        frameBytes[10] = 0;
        frame.setCtrlCode( Integer.toHexString(frameBytes[1] & 0xFF).toUpperCase());
        frame.setFrame(frameBytes);
        return frame;
    }

    public Frame buildSetWarningConfig(SetWarningConfig setWarningConfig) {
        Frame frame = new Frame();
        byte[] frameBytes = new byte[13];
        frameBytes[0] = (byte) FRAME_HEAD;
        frameBytes[1] = (byte) 0x5B; // c
        frameBytes[2] = 10; // 长度
        frameBytes[11] = (byte) FRAME_END_CC;
        frameBytes[12] = (byte) FRAME_END_33;

        // 1( 低 8位)开启+ 上限报警值（高 8位)
        System.arraycopy(ByteUtil.intToByteArray2LE((setWarningConfig.getFlowVelocityWarningFlag() << 8) + setWarningConfig.getFlowVelocity()), 0, frameBytes, 3, 2);

        // 1( 低位 )开启+ 水流下限报警值（高位）
        System.arraycopy(ByteUtil.intToByteArray2LE((setWarningConfig.getTemperatureWarningFlag() << 8) + setWarningConfig.getTemperature()), 0, frameBytes, 5, 2);

        // 重置计数器
        System.arraycopy(ByteUtil.intToByteArray2(setWarningConfig.getResetTotalCountFlag()), 0, frameBytes, 7, 2);

        // 保留
        frameBytes[9] = 0;
        frameBytes[10] = 0;
        frame.setCtrlCode( Integer.toHexString(frameBytes[1] & 0xFF).toUpperCase());
        frame.setFrame(frameBytes);
        return frame;
    }

    public byte[] recognize(byte[] buf) {
        if (this.frameData != null) {
            // 拼接后续报文
            byte[] b = new byte[buf.length + this.index];
            System.arraycopy(this.frameData, 0, b, 0, this.index);
            System.arraycopy(buf, 0, b, this.index, buf.length);
            // 判断结尾是否是33
            if (ParserUtil.toUnSignByte(b[(b.length - 1)]) == FRAME_END_33) {
                //完整报文
                this.index = 0;
                this.frameData = null;
                return b;
            } else {
                // 继续保存AA开头前 + 后续报文
                this.index = b.length;
                this.frameData = b;
            }
        } else {
            for (int i = 0; i < buf.length; i++) {
                // 判断开关是否AA
                if (ParserUtil.toUnSignByte(buf[i]) != FRAME_HEAD) {
                    continue;
                }
                // 判断结尾是否是33
                if (ParserUtil.toUnSignByte(buf[(buf.length - 1)]) == FRAME_END_33) {
                    // 完整报文
                    return buf;
                }
                // 暂时保存AA开头报文
                this.index = buf.length - i;
                this.frameData = new byte[this.index];
                System.arraycopy(buf, i, this.frameData, 0, this.index);
                break;
            }
        }
        return null;
    }

    public Frame recognizeFrame(byte[] buf, int position) {
        Frame frm = new Frame();
        for (int i = position; i < buf.length; i++) {
            if (isValidLongRtuFrameHead(buf, i)) {
                if (recognizeProtocol(buf[i + 1])) {
                    int dataLen = getDataLenOfLongRtuFrame(buf, i);
                    byte[] rtuFrm = getRtuFrameOfLongRtuFrame(buf, dataLen, i);

//                    Log.i("识别出帧:" + HexUtil.encodeHexStr(rtuFrm).toUpperCase());
                    frm.setIndex(i);
                    frm.setFrame(buf);
                    frm.setDataLen(dataLen);
                    frm.setFrameLen(dataLen + FRAME_HEAD_LEN);
                    frm.setFrameData(rtuFrm);

                    try {
                        this.resolveFrame(frm);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return frm;
    }

    /**
     * 判断是否是正常帧 2018年8月2日 By zhangfeng
     *
     * @param buf
     * @param pos
     * @return boolean
     */
    private boolean isValidLongRtuFrameHead(byte[] buf, int pos) {
        if (ParserUtil.toUnSignByte(buf[pos]) != FRAME_HEAD) {
            return false;
        }
        int lenth = buf[pos + 2];
        if (lenth <= 0) {
            return false;
        }
        return ((ParserUtil.toUnSignByte(buf[(pos + 2 + lenth)]) == FRAME_END_33) && (ParserUtil.toUnSignByte(buf[(pos + 2 + lenth - 1)]) == FRAME_END_CC));
    }

    public static boolean recognizeProtocol(byte b) {
        return Arrays.asList(CTRL).contains(Integer.toHexString(b & 0xFF).toUpperCase());
    }

    private int getDataLenOfLongRtuFrame(byte[] buf, int pos) {
        return buf[(pos + 2)] & 0xFF;
    }

    private byte[] getRtuFrameOfLongRtuFrame(byte[] buf, int dataLen, int pos) {
        byte[] rtuFrm = new byte[FRAME_START_HEAD_LEN + dataLen];
        System.arraycopy(buf, pos, rtuFrm, 0, rtuFrm.length);
        return rtuFrm;
    }

    private String getParserName() {
        return "com.example.protocol.frame.Parser";
    }

    public List<DataItem> resolveFrame(Frame frame) throws ParseException {
        List<DataItem> dataItems = new ArrayList<DataItem>();
        try {
            if (this.dataObj == null) {
                String strObjName = getParserName();
                this.dataObj = ((Parser) Class.forName(strObjName).newInstance());
            }
            this.dataObj.parse(frame, dataItems);
            frame.setDataItems(dataItems);
            return dataItems;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("规约处理解析帧失败（数组下标越界）", e);
        } catch (Exception e) {
            throw new ParseException("规约处理解析帧失败（其他错误）", e);
        }
    }
    public Frame buildReplyUploadWorkingInfoFrame() {
        Frame frame = new Frame();
        byte[] frameBytes = new byte[5];
        frameBytes[0] = (byte) FRAME_HEAD;
        frameBytes[1] = (byte) 0x40; // c
        frameBytes[2] = 2; // 长度
        frameBytes[3] = (byte) FRAME_END_CC;
        frameBytes[4] = (byte) FRAME_END_33;
        frame.setCtrlCode( Integer.toHexString(frameBytes[1] & 0xFF).toUpperCase());
        frame.setFrame(frameBytes);
        return frame;
    }
}
