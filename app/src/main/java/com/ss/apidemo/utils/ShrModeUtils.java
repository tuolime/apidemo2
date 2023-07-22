package com.ss.apidemo.utils;

import com.ss.apidemo.bean.ShrModeBean;

/*
 * SHR Stack SHR HR 模式 下 选择手具后 对应的 单脉冲能量
 * */
public final class ShrModeUtils {

    //用户性别 //1 男 2 女
    // TODO stack 不分男女
    public ShrModeBean modeType(int gender, int handgear) {
        ShrModeBean type = new ShrModeBean();
        //用户性别 //1 男 2 女
        if (gender == 1) {

            /*
             * 1 QB-6-10
             * 2 QB-6-16
             * 3 QB-6-20
             * 4 QB-2-10
             * 5 QB-2-16
             * 6 QB-2-24
             * 7 QB-4-24
             *
             * */
            switch (handgear) {
                case 1:
                    type.setHandgearType(1);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(24);
                    type.setFluence10HzMax(12);
                    break;
                case 2:
                    type.setHandgearType(2);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(36);
                    type.setFluence10HzMax(18);
                    break;
                case 3:
                    type.setHandgearType(3);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
                case 4:
                    type.setHandgearType(4);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(24);
                    type.setFluence10HzMax(12);
                    break;
                case 5:
                    type.setHandgearType(5);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(36);
                    type.setFluence10HzMax(18);
                    break;
                case 6:
                    type.setHandgearType(6);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(30);
                    type.setFluence10HzMax(15);
                    break;
                case 7:
                    type.setHandgearType(7);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
                case 8://8 9 10 的手具值  和  3 7 的一样
                    type.setHandgearType(8);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
                case 9:
                    type.setHandgearType(9);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
                case 10:
                    type.setHandgearType(10);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
            }

        } else if (gender == 2) {
            /*
             * 1 QB-6-10
             * 2 QB-6-16
             * 3 QB-6-20
             * 4 QB-2-10
             * 5 QB-2-16
             * 6 QB-2-24
             * 7 QB-4-24
             *
             * */
            switch (handgear) {
                case 1:
                    type.setHandgearType(1);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(24);
                    type.setFluence10HzMax(12);
                    break;
                case 2:
                    type.setHandgearType(2);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(36);
                    type.setFluence10HzMax(18);
                    break;
                case 3:
                    type.setHandgearType(3);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
                case 4:
                    type.setHandgearType(4);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(24);
                    type.setFluence10HzMax(12);
                    break;
                case 5:
                    type.setHandgearType(5);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(36);
                    type.setFluence10HzMax(18);
                    break;
                case 6:
                    type.setHandgearType(6);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(30);
                    type.setFluence10HzMax(15);
                    break;
                case 7:
                    type.setHandgearType(7);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
                case 8://8 9 10 的手具值  和  3 7 的一样
                    type.setHandgearType(8);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
                case 9:
                    type.setHandgearType(9);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
                case 10:
                    type.setHandgearType(10);
                    type.setFluenceMin(4);
                    type.setFluence5HzMax(45);
                    type.setFluence10HzMax(23);
                    break;
            }
        }

        return type;
    }
}
