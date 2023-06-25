package com.example.protocol;


import com.example.protocol.frame.Frame;
import com.example.protocol.frame.ProtocalHandler;
import com.example.protocol.frame.data.SetDeviceConfig;
import com.example.protocol.frame.data.SetWarningConfig;
import com.example.protocol.frame.data.SetWorkingStatus;
import com.example.protocol.frame.data.UploadWorkingInfo;
import com.example.protocol.frame.entity.DataItem;
import com.example.protocol.utils.ParserUtil;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.HexUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;


public class FrameTest {


    /**
     * 工作状态下发
     */
    void testBuildSetWorkingStatusFrame() {
        //停止状态  工作状态给 0  总能量 也能 0
        ProtocalHandler protocalHandler = new ProtocalHandler();
        SetWorkingStatus setWorkingStatus = new SetWorkingStatus();
        setWorkingStatus.setWorkingModel(1); // 工作模式  1 2 3 4 5 shr 6 hr
        setWorkingStatus.setFluence(4); // 单脉冲能量
        setWorkingStatus.setFrequency(5); // 频率
        setWorkingStatus.setTotalEnergy(0);// 总能量
        setWorkingStatus.setWorkingTime(0); // 工作时间  秒
        setWorkingStatus.setQbConfig(2); // 手具选择 1-7
        setWorkingStatus.setChangeQbPortFlag(1); // 是否切换手具端口 1 left 0 right
        setWorkingStatus.setWorkingStatus(1);// 工作状态 0  stby和停止  1 reading 2 working
        Frame frame = protocalHandler.buildSetWorkingStatusFrame(setWorkingStatus);
        Console.log(ParserUtil.toHexString(frame.getFrame()));
    }

    // 下发告警
    void testBuildSetWarningConfig() {
        ProtocalHandler protocalHandler = new ProtocalHandler();
        SetWarningConfig setWarningConfig = new SetWarningConfig();
        setWarningConfig.setFlowVelocityWarningFlag(1); // 是否开启告警 0 关闭  1 开启
        setWarningConfig.setFlowVelocity(20); // 水流下限
        setWarningConfig.setTemperatureWarningFlag(1); // 是否开启告警 0 关闭  1 开启
        setWarningConfig.setTemperature(40); // 温度上限
        setWarningConfig.setResetTotalCountFlag(0); // 不重置计数器  0 不清空  1 清空
        Frame frame1 = protocalHandler.buildSetWarningConfig(setWarningConfig);
        Console.log(ParserUtil.toHexString(frame1.getFrame()));
    }

    // 下发设备配置
    void testbuildSetDeviceConfigFrame() {
        ProtocalHandler protocalHandler = new ProtocalHandler();
        SetDeviceConfig setDeviceConfig = new SetDeviceConfig();
        setDeviceConfig.setPowerType(1); // 电源类型
        setDeviceConfig.setQbflag(1); // 单手具 0 单  1 双
        setDeviceConfig.setHornFlag(0); // 设备关 上位机开  喇叭 0 下关上开 1 下开上开 2 下开上关
        Frame frame2 = protocalHandler.buildSetDeviceConfigFrame(setDeviceConfig);
        Console.log(HexUtil.encodeHexStr(frame2.getFrame()).toUpperCase());
    }

    // 接收报文
    void testReceiveFrame() {
        ProtocalHandler protocalHandler = new ProtocalHandler();
        Frame resFrame = protocalHandler.recognizeFrame(HexUtil.decodeHex("0A0DCBAE20202020CEC23A36353533332C09CBAE20202020C1F73A20302C09CAB120202020BCE43A20302C09D7DC20202020BCC63A20202020203330320A0D0A0DC4A320202020CABD3A20312C09C4DC20202020C1BF3A32302C09C2F620202020BFED3A34302C09C6B520202020C2CA3A20322C09D7DC202020202020C4DC3A20302C202020202020202020202020D7B420202020CCAC3A20300A0D0A0DCBAEBEAFBFAAB9D83A20312C09CEC2BEAFBFAAB9D83A20302C09C0AEB0C8BFAAB9D83A20302C09C7E5C1E3BFAAB9D83A20302C09B5E7D4B4C0E0D0CD3A20322C09CAD6BEDFD1A1D4F13A20310A0D0A0DCBAEC1F7CFC2CFDE3A20302C09CEC2B6C8C9CFCFDE3A33302C09CBAEC1F7BEAFB1A83A20312C09CEC2B6C8BEAFB1A83A20302C09BEAFB1A8BAC5C2EB3A32302C09CAD6BEDFB5A5CBAB3A20300A0DAA40140001001400020000012E0000000000FD2000CC33"), 0);
//        resFrame.getCtrlCode()
        // 40 是上报报文取lst, 55, 5A, 5B是应答报文
//        Console.log(resFrame.getCtrlCode());
//        List<DataItem> lst = resFrame.getDataItems();
//        Map<String, String> map = lst.stream().collect(Collectors.toMap(DataItem::getId, DataItem::getValue));
//        UploadWorkingInfo uploadWorkingInfo = JSONUtil.toBean(new JSONObject(map), UploadWorkingInfo.class);
        UploadWorkingInfo uploadWorkingInfo =(UploadWorkingInfo) resFrame.getDataValue();
        Console.log(uploadWorkingInfo);

        Console.log((10 << 8) + 1);
        Console.log((10 & 0xff) << 8 | 1 & 0xff);
    }

}
