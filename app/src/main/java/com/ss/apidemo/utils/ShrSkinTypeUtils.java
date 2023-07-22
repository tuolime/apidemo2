package com.ss.apidemo.utils;

import com.ss.apidemo.bean.ShrSkinBean;

/*
 * 肤色 及 选中身体的部位  设置建议的脉冲最大值和最小值
 * */
public final class ShrSkinTypeUtils {

    //用户性别 //1 男 2 女
    // TODO stack 不分男女
    public ShrSkinBean modeType(int gender, int skinType, int size, int handgear, int body) {
        ShrSkinBean type = new ShrSkinBean();
        //用户性别 //1 男 2 女
        if (gender == 1) {
            /*
             * 肤色
             * 1  I
             * 2  II
             * 3  III
             * 4  IV
             * 5  V
             * 6  VI
             * */
            if (skinType == 1) {
                /*
                 * 尺码
                 * 1  s 25cm
                 * 2  m 50cm
                 * 3  l 100cm
                 * 4  xl 300cm
                 * */

                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(14);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6 || body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(22);
                                type.setEnergy(2);
                            }
                            if (body == 7) {
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(2);
                            }

                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(3);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(14);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(22);
                                type.setEnergy(3);
                            }
                            if (body == 7) {
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(2);
                            }

                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(6);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(14);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(22);
                                type.setEnergy(6);
                            }
                            if (body == 7) {
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(2);
                            }

                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(18);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(14);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(22);
                                type.setEnergy(18);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(18);
                            }

                        }
                        break;
                }
            }
            if (skinType == 2) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(11);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(12);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(2);
                            }
                            if (body == 7) {
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(2);
                            }

                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(11);
                                type.setEnergy(3);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(12);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(3);
                            }
                            if (body == 7) {
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(2);
                            }

                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(11);
                                type.setEnergy(6);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(12);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(6);
                            }
                            if (body == 7) {
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(2);
                            }

                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(11);
                                type.setEnergy(18);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(12);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(18);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(18);
                            }

                        }
                        break;
                }
            }
            if (skinType == 3) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(2);
                            }
                            if (body == 7) {
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(2);
                            }

                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(3);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(3);
                            }
                            if (body == 7) {
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(2);
                            }

                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(6);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(6);
                            }
                            if (body == 7) {
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(2);
                            }

                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(18);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(18);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(18);
                            }

                        }
                        break;
                }
            }
            if (skinType == 4) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(17);
                                type.setEnergy(2);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(15);
                                type.setEnergy(18);
                            }
                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(3);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(17);
                                type.setEnergy(3);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(15);
                                type.setEnergy(18);
                            }

                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(6);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(17);
                                type.setEnergy(6);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(15);
                                type.setEnergy(18);
                            }

                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(18);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(17);
                                type.setEnergy(18);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(15);
                                type.setEnergy(18);
                            }

                        }
                        break;
                }
            }
            if (skinType == 5) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(7);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(9);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(2);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(18);
                            }

                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(7);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(9);
                                type.setEnergy(3);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(3);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(18);
                            }
                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(7);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(9);
                                type.setEnergy(6);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(6);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(18);
                            }

                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(7);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(9);
                                type.setEnergy(18);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(18);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(18);
                            }

                        }
                        break;
                }
            }
            if (skinType == 6) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(8);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(2);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(18);
                            }
                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(8);
                                type.setEnergy(3);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(3);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(3);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(18);
                            }

                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(8);
                                type.setEnergy(6);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(6);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(6);
                            }

                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(18);
                            }

                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(8);
                                type.setEnergy(18);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(18);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(18);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(18);
                            }

                        }
                        break;
                }
            }
        } else if (gender == 2) {
            /*
             * 肤色
             * 1  I
             * 2  II
             * 3  III
             * 4  IV
             * 5  V
             * 6  VI
             * */
            if (skinType == 1) {
                /*
                 * 尺码
                 * 1  s 25cm
                 * 2  m 50cm
                 * 3  l 100cm
                 * 4  xl 300cm
                 * */

                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(1);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(14);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(22);
                                type.setEnergy(1);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(17);
                            }

                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(14);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(22);
                                type.setEnergy(2);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(17);
                            }

                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(5);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(14);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(22);
                                type.setEnergy(5);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(17);
                            }

                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(17);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(14);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(22);
                                type.setEnergy(17);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(17);
                            }

                        }
                        break;
                }
            }
            if (skinType == 2) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(11);
                                type.setEnergy(1);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(12);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(1);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(17);
                            }
                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(11);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(12);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(2);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(17);
                            }
                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(11);
                                type.setEnergy(5);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(12);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(5);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(17);
                            }
                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(11);
                                type.setEnergy(17);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(12);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(20);
                                type.setEnergy(17);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(17);
                            }

                        }
                        break;
                }
            }
            if (skinType == 3) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(1);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(1);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(17);
                            }
                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(2);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(17);
                            }
                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(5);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(5);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(17);
                            }
                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(17);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(10);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(18);
                                type.setEnergy(17);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(17);
                            }

                        }
                        break;
                }
            }
            if (skinType == 4) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(1);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(17);
                                type.setEnergy(1);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(15);
                                type.setEnergy(17);
                            }
                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(17);
                                type.setEnergy(2);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(15);
                                type.setEnergy(17);
                            }

                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(5);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(17);
                                type.setEnergy(5);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(15);
                                type.setEnergy(17);
                            }

                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(10);
                                type.setEnergy(17);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(9);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(17);
                                type.setEnergy(17);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(15);
                                type.setEnergy(17);
                            }

                        }
                        break;
                }
            }
            if (skinType == 5) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(7);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(9);
                                type.setEnergy(1);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(1);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(17);
                            }
                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(7);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(9);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(2);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(17);
                            }
                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(7);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(9);
                                type.setEnergy(5);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(5);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(17);
                            }
                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(7);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(9);
                                type.setEnergy(17);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(8);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(16);
                                type.setEnergy(17);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(17);
                            }

                        }
                        break;
                }
            }
            if (skinType == 6) {
                switch (size) {
                    case 1:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(8);
                                type.setEnergy(1);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(1);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(1);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(17);
                            }
                        }

                        break;
                    case 2:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(8);
                                type.setEnergy(2);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(2);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(2);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(17);
                            }
                        }
                        break;
                    case 3:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(8);
                                type.setEnergy(5);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(5);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(5);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(17);
                            }
                        }
                        break;
                    case 4:
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
                        if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                                || handgear == 6) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 7|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(8);
                                type.setEnergy(17);
                            }

                        }
                        if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                            // 男部位id
                            //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                            // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                            // 13：后颈；14：后背；15：臀；16：肩；
                            if (body == 1 || body == 2 || body == 3 || body == 4) {
                                type.setBodyType_HZ(5);
                                type.setFluenceProposal(6);
                                type.setEnergy(17);
                            }
                            if (body == 5|| body == 6|| body == 8|| body == 9
                                    || body == 10|| body == 11|| body == 12|| body == 13
                                    || body == 14|| body == 15|| body == 16|| body == 17) {

                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(14);
                                type.setEnergy(17);
                            }
                            if (body == 7){
                                type.setBodyType_HZ(10);
                                type.setFluenceProposal(12);
                                type.setEnergy(17);
                            }

                        }
                        break;
                }
            }

        }

        return type;
    }
}
