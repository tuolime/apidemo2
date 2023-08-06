package com.ss.apidemo.utils;

import com.ss.apidemo.bean.ExpertModeBean;
import com.ss.apidemo.bean.SmartModeBean;

/*
 * 智能模式 下auto 30  100  400  对应的 单脉冲能量
 * */
public final class SmartModeUtils {

    //用户性别 //1 男 2 女
    // TODO stack 不分男女
    public SmartModeBean modeType(int modeType) {
        SmartModeBean type = new SmartModeBean();

        switch (modeType) {
            case 6://auto
                type.setFluenceProposal(20);
                type.setHzProposal(4);
                break;
            case 7://30
                type.setFluenceProposal(20);
                type.setHzProposal(4);
                break;
            case 8://100
                type.setFluenceProposal(20);
                type.setHzProposal(1);
                break;
            case 9://400
                type.setFluenceProposal(20);
                type.setHzProposal(1);
                break;

        }

        return type;
    }
}
