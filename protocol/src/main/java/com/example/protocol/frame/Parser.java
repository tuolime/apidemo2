package com.example.protocol.frame;

import android.util.Log;

import com.example.protocol.exception.ParseException;
import com.example.protocol.frame.contants.FrameType;
import com.example.protocol.frame.data.UploadWorkingInfo;
import com.example.protocol.frame.entity.DataItem;
import com.example.protocol.utils.ByteUtil;
import com.example.protocol.utils.ParserUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;


import java.util.List;

public class Parser {

    private static final String TAG = "Parser";

    public int parse(Frame frame, List<DataItem> commonDataItems) throws ParseException {
        try {
            byte[] frmData = frame.getFrameData();
            String c = Integer.toHexString(frmData[1] & 0xFF).toUpperCase();
            Log.i(TAG,"控制域:" + c);
            // 数据异常
            if (!ProtocalHandler.recognizeProtocol(frmData[1])) {
                DataItem bean = new DataItem();
                bean.setName("数据异常");
                commonDataItems.add(bean);
                return 1;
            }
            frame.setCtrlCode(c);
            int frmDataLen = frmData[2] & 0xFF;
            Log.i(TAG,"数据域长度:" + frmDataLen);
            if (!c.equals(FrameType.UPLOAD_WORKING_INFO.getKey())) {
                DataItem bean = new DataItem();
                bean.setName("数据应答报文");
                bean.setValue(Integer.toString(frmDataLen));
                commonDataItems.add(bean);
//                Log.i(TAG,FrameType.getFrameName(c) + "解析后数据值:" + bean.getValue());
                return 1;
            }
            // 上位机自动上报数据
            byte[] mark = new byte[frame.getIndex()];
            System.arraycopy(frame.getFrame(), 0, mark, 0, mark.length);
            Log.i(TAG,"报文信息：" + StrUtil.str(mark, "gbk"));
            byte[] itemsValue = protocolValue(frmData, 3, frmDataLen);
            Log.i(TAG,"解析数据项值:" + ParserUtil.toHexString(itemsValue, 0, itemsValue.length));
            UploadWorkingInfo uploadWorkingInfo = new UploadWorkingInfo();
            uploadWorkingInfo.setWorkingModel(ParserUtil.toBcdHexString(itemsValue, 0, 2));
            uploadWorkingInfo.setFluence(ParserUtil.toBcdHexString(itemsValue, 2, 2));
            uploadWorkingInfo.setFrequency(ParserUtil.toBcdHexString(itemsValue, 4, 2));
            int toalCountHi = ParserUtil.toBcdHexString(itemsValue, 6, 2);
            int toalCountLow = ParserUtil.toBcdHexString(itemsValue, 8, 2);
            uploadWorkingInfo.setToalCount((toalCountHi << 8) + toalCountLow);
            uploadWorkingInfo.setWorkingTime(ParserUtil.toBcdHexString(itemsValue, 10, 2));
            uploadWorkingInfo.setTotalEnergy(ParserUtil.toBcdHexString(itemsValue, 12, 2));
            int temperatureAndFlowVelocity =  ParserUtil.toBcdHexString(itemsValue, 14, 2);
            uploadWorkingInfo.setTemperature(temperatureAndFlowVelocity  & 0xff);
            uploadWorkingInfo.setFlowVelocity((temperatureAndFlowVelocity >> 8) & 0xff);
            int workingStatsAndWarning =  ParserUtil.toBcdHexString(itemsValue, 16, 2);
            uploadWorkingInfo.setWorkingStatus(workingStatsAndWarning & 0xff);
            String warnningFlag = ByteUtil.toBinaryString(((workingStatsAndWarning >> 8) & 0xff), 6);
            uploadWorkingInfo.setShortCircuited(Integer.parseInt(warnningFlag.substring(5, 6)));
            uploadWorkingInfo.setQbConnFail(Integer.parseInt(warnningFlag.substring(4, 5)));
            uploadWorkingInfo.setTemperatureHi(Integer.parseInt(warnningFlag.substring(3, 4)));
            uploadWorkingInfo.setTemperatureLow(Integer.parseInt(warnningFlag.substring(2, 3)));
            uploadWorkingInfo.setFlowVelocityLow(Integer.parseInt(warnningFlag.substring(1, 2)));
            uploadWorkingInfo.setNoFlowVelocity(Integer.parseInt(warnningFlag.substring(0, 1)));
            frame.setDataValue(uploadWorkingInfo);
            Console.log(uploadWorkingInfo);
            return 1;

        } catch (Exception e) {
            throw new ParseException("解析报文异常!", e);
        }
    }

    /**
     * 报文数据区
     *
     * @param b
     * @param start
     * @param len
     * @return
     */
    private byte[] protocolValue(byte[] b, int start, int len) {
        byte[] ib = new byte[len];
        System.arraycopy(b, start, ib, 0, len);
        return ib;
    }

    /**
     * 日期格式化 2018年8月4日 By zhangfeng
     *
     * @param value
     * @param bean
     * @return str
     */
    private String getFormatValue(String value, DataItem bean) {
        StringBuffer sb = new StringBuffer();
        if ("date".equals(bean.getUnit()) && value.length() >= 10) {
            sb.append(value.substring(0, 2));
            sb.append("-");
            sb.append(value.substring(2, 4));
            sb.append("-");
            sb.append(value.substring(4, 6));
            sb.append(" ");
            sb.append(value.substring(6, 8));
            sb.append(":");
            sb.append(value.substring(8, 10));
            if (value.length() == 12) {
                sb.append(":");
                sb.append(value.substring(10, 12));
            }
            return sb.toString();
        }
        return value + Convert.toStr(bean.getUnit(), "");
    }


}
