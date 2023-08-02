package com.ss.apidemo.utils;

import com.ss.apidemo.bean.ExpertModeBean;
import com.ss.apidemo.bean.ShrModeBean;
import com.ss.apidemo.bean.ThirtyModeBean;

/*
 * z专家 模式 下 选择手具后 对应的 单脉冲能量
 * */
public final class ExpertModeUtils {

    //用户性别 //1 男 2 女
    // TODO stack 不分男女
    public ExpertModeBean modeType(int gender, int handgear) {
        ExpertModeBean type = new ExpertModeBean();
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
             * 8 QB-*-30
             * 9 QB-6-200
             * 10 QB-6-40
             *
             * */
            switch (handgear) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 19:
                    type.setHandgearType(1);
                    type.setFluenceProposal(20);
                    type.setHzProposal(4);
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
             * 8 QB-*-30
             * 9 QB-6-200
             * 10 QB-6-40
             *
             * */
            switch (handgear) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 19:
                    type.setHandgearType(1);
                    type.setFluenceProposal(20);
                    type.setHzProposal(4);
                    break;
            }
        }

        return type;
    }
}
