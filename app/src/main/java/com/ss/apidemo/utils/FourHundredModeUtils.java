package com.ss.apidemo.utils;

import com.ss.apidemo.bean.FourHundredModeBean;
import com.ss.apidemo.bean.HundredModeBean;

/*
 * 400 模式 下 选择手具后 对应的 单脉冲能量
 * */
public final class FourHundredModeUtils {

    // TODO stack 不分男女
    public FourHundredModeBean modeType(int handgear) {
        FourHundredModeBean type = new FourHundredModeBean();

        /*
         * 1 QB-6-10
         * 2 QB-6-16
         * 3 QB-6-20
         * 4 QB-2-10
         * 5 QB-2-16
         * 6 QB-2-24
         * 7 QB-4-24
         * 8 QB-*-30
         * 9 QB-6-200
         * 10 QB-6-40
         *
         * */
        switch (handgear) {
            case 1:
                type.setHandgearType(1);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(120);
                break;
            case 2:
                type.setHandgearType(2);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(180);
                break;
            case 3:
                type.setHandgearType(3);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(100);
                break;
            case 4:
                type.setHandgearType(4);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(120);
                break;
            case 5:
                type.setHandgearType(5);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(180);
                break;
            case 6:
                type.setHandgearType(6);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(80);
                break;
            case 7:
                type.setHandgearType(7);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(100);
                break;
            case 8:
                type.setHandgearType(8);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(120);
                break;
            case 9:
                type.setHandgearType(9);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(100);
                break;
            case 10:
                type.setHandgearType(10);
                type.setModeType(9);
                type.setHzMin(1);
                type.setHzMax(1);
                type.setFluenceMin(4);
                type.setFluenceMax(100);
                break;
        }

        return type;
    }
}
