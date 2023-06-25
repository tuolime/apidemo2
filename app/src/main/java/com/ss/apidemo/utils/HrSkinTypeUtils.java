package com.ss.apidemo.utils;

import com.ss.apidemo.bean.HrSkinBean;

/*
 * 肤色 及 选中身体的部位  设置建议的脉冲最大值和最小值
 * */
public final class HrSkinTypeUtils {

    //用户性别 //1 男 2 女
    public HrSkinBean modeType(int gender, int skinType,int handgear, int body) {
        HrSkinBean type = new HrSkinBean();
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
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(14);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {

                        type.setFluenceProposal(22);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(20);
                        type.setFreQuencyProposal(5);
                    }

                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(14);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {
                        type.setFluenceProposal(22);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(20);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 2) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(13);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {

                        type.setFluenceProposal(21);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(19);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(12);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {
                        type.setFluenceProposal(20);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(18);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 3) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(12);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {

                        type.setFluenceProposal(20);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(18);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(10);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {
                        type.setFluenceProposal(18);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(16);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 4) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(11);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {

                        type.setFluenceProposal(19);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(17);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(9);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {
                        type.setFluenceProposal(17);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(15);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 5) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(10);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {

                        type.setFluenceProposal(18);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(16);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(8);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {
                        type.setFluenceProposal(16);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(14);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 6) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(9);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {

                        type.setFluenceProposal(17);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(15);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(6);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16) {
                        type.setFluenceProposal(14);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(12);
                        type.setFreQuencyProposal(5);
                    }
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
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(14);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {

                        type.setFluenceProposal(22);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(20);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(14);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {
                        type.setFluenceProposal(22);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(20);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 2) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(13);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {

                        type.setFluenceProposal(21);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(19);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(12);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {
                        type.setFluenceProposal(20);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(18);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 3) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(12);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {

                        type.setFluenceProposal(20);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(18);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(10);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {
                        type.setFluenceProposal(18);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(16);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 4) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(11);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {

                        type.setFluenceProposal(19);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(17);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(9);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {
                        type.setFluenceProposal(17);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(15);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 5) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(10);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {

                        type.setFluenceProposal(18);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(16);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(8);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {
                        type.setFluenceProposal(16);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(14);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
            if (skinType == 6) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；

                if (handgear == 1|| handgear == 2|| handgear == 4|| handgear == 5
                        || handgear == 6) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(9);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {

                        type.setFluenceProposal(17);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(15);
                        type.setFreQuencyProposal(5);
                    }
                }
                if (handgear == 3|| handgear == 7) {
                    // 男部位id
                    //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                    // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                    // 13：后颈；14：后背；15：臀；16：肩；
                    if (body == 1 || body == 2 || body == 3 || body == 4) {
                        type.setFluenceProposal(6);
                        type.setFreQuencyProposal(4);
                    }
                    if (body == 5|| body == 6|| body == 8|| body == 9
                            || body == 10|| body == 11|| body == 12|| body == 13
                            || body == 14|| body == 15|| body == 16| body == 17) {
                        type.setFluenceProposal(14);
                        type.setFreQuencyProposal(5);
                    }
                    if (body == 7) {
                        type.setFluenceProposal(12);
                        type.setFreQuencyProposal(5);
                    }
                }
            }
        }

        return type;
    }
}
