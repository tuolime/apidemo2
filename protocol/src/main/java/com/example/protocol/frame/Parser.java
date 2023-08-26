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

    public static void parse(Frame frame, List<DataItem> commonDataItems) throws ParseException {
        try {
            byte[] frmData = frame.getFrameData();
            String c = Integer.toHexString(frmData[1] & 0xFF).toUpperCase();
//            log.info("控制域:" + c);
            // 数据异常
            if (!ProtocalHandler.recognizeProtocol(frmData[1])) {
                DataItem bean = new DataItem();
                bean.setName("数据异常");
                commonDataItems.add(bean);
                return;
            }
            frame.setCtrlCode(c);
            int frmDataLen = frmData[2] & 0xFF;
//            log.info("数据域长度:" + frmDataLen);
            // 是否上报报文
            if (!c.equals(FrameType.UPLOAD_WORKING_INFO.getKey())) {
                DataItem bean = new DataItem();
                bean.setName("数据应答报文");
                bean.setValue(Integer.toString(frmDataLen));
                commonDataItems.add(bean);
//                log.info(FrameType.getFrameName(c) + "解析后数据值:" + bean.getValue());
                return;
            }
            // 上位机自动上报数据
            byte[] mark = new byte[frame.getIndex()];
            System.arraycopy(frame.getFrame(), 0, mark, 0, mark.length);
//            log.info("报文信息：" + StrUtil.str(mark, "gbk"));
            byte[] itemsValue = protocolValue(frmData, 3, frmDataLen);
//            log.info("解析数据项值:" + ParserUtil.toHexString(itemsValue, 0, itemsValue.length));

            UploadWorkingInfo uploadWorkingInfo = new UploadWorkingInfo();
            int workingModelAndCoolLevel = ParserUtil.toBcdHexString(itemsValue, 0, 2);
            uploadWorkingInfo.setWorkingModel(workingModelAndCoolLevel);

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

            String warnningFlag = ByteUtil.toBinaryString(((workingStatsAndWarning >> 8) & 0xff), 7);
            uploadWorkingInfo.setShortCircuited(Integer.parseInt(warnningFlag.substring(6, 7)));
            uploadWorkingInfo.setQbConnFail(Integer.parseInt(warnningFlag.substring(5, 6)));
            uploadWorkingInfo.setTemperatureHi(Integer.parseInt(warnningFlag.substring(4, 5)));
            uploadWorkingInfo.setTemperatureLow(Integer.parseInt(warnningFlag.substring(3, 4)));
            uploadWorkingInfo.setFlowVelocityLow(Integer.parseInt(warnningFlag.substring(2, 3)));
            uploadWorkingInfo.setNoFlowVelocity(Integer.parseInt(warnningFlag.substring(1, 2)));
            uploadWorkingInfo.setCheckFilter(Integer.parseInt(warnningFlag.substring(0, 1)));

            frame.setDataValue(uploadWorkingInfo);
            Console.log(uploadWorkingInfo);
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
    private static byte[] protocolValue(byte[] b, int start, int len) {
        byte[] ib = new byte[len];
        System.arraycopy(b, start, ib, 0, len);
        return ib;
    }

}
