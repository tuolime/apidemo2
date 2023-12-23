package com.ss.apidemo.utils;

import com.ss.apidemo.bean.StackModeBean;

/*
 * SHR Stack SHR HR 模式 下 选择手具后 对应的 单脉冲能量
 * */
public final class StackModeUtils {

    //用户性别 //1 男 2 女
    // TODO stack 不分男女
    public StackModeBean modeType(int gender, int handgear) {
        StackModeBean type = new StackModeBean();
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
                    type.setFluenceMax(32);
                    break;
                case 2:
                    type.setHandgearType(2);
                    type.setFluenceMin(4);
                    type.setFluenceMax(36);
                    break;
                case 3:
                    type.setHandgearType(3);
                    type.setFluenceMin(4);
                    type.setFluenceMax(45);
                    break;
                case 4:
                    type.setHandgearType(4);
                    type.setFluenceMin(4);
                    type.setFluenceMax(32);
                    break;
                case 5:
                    type.setHandgearType(5);
                    type.setFluenceMin(4);
                    type.setFluenceMax(36);
                    break;
                case 6:
                    type.setHandgearType(6);
                    type.setFluenceMin(4);
                    type.setFluenceMax(30);
                    break;
                case 7:
                    type.setHandgearType(7);
                    type.setFluenceMin(4);
                    type.setFluenceMax(45);
                    break;
                case 8:
                    type.setHandgearType(8);
                    type.setFluenceMin(4);
                    type.setFluenceMax(60);
                    break;
                case 9:
                    type.setHandgearType(9);
                    type.setFluenceMin(4);
                    type.setFluenceMax(50);
                    break;
                case 10:
                    type.setHandgearType(10);
                    type.setFluenceMin(4);
                    type.setFluenceMax(32);
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
                    type.setFluenceMax(24);
                    break;
                case 2:
                    type.setHandgearType(2);
                    type.setFluenceMin(4);
                    type.setFluenceMax(36);
                    break;
                case 3:
                    type.setHandgearType(3);
                    type.setFluenceMin(4);
                    type.setFluenceMax(45);
                    break;
                case 4:
                    type.setHandgearType(4);
                    type.setFluenceMin(4);
                    type.setFluenceMax(24);
                    break;
                case 5:
                    type.setHandgearType(5);
                    type.setFluenceMin(4);
                    type.setFluenceMax(36);
                    break;
                case 6:
                    type.setHandgearType(6);
                    type.setFluenceMin(4);
                    type.setFluenceMax(30);
                    break;
                case 7:
                    type.setHandgearType(7);
                    type.setFluenceMin(4);
                    type.setFluenceMax(45);
                    break;
                case 8:
                    type.setHandgearType(8);
                    type.setFluenceMin(4);
                    type.setFluenceMax(60);
                    break;
                case 9:
                    type.setHandgearType(9);
                    type.setFluenceMin(4);
                    type.setFluenceMax(50);
                    break;
                case 10:
                    type.setHandgearType(10);
                    type.setFluenceMin(4);
                    type.setFluenceMax(60);
                    break;
            }
        }

        return type;
    }
}
