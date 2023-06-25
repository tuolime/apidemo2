package com.ss.apidemo.utils;

import com.ss.apidemo.bean.StackSkinBean;

/*
 * 肤色 及 选中身体的部位  设置建议的脉冲最大值和最小值
 * */
public final class StackSkinTypeUtils {

    //用户性别 //1 男 2 女
    public StackSkinBean modeType(int gender, int skinType, int body) {
        StackSkinBean type = new StackSkinBean();
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
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                        break;


                }
            }
            if (skinType == 2) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(12);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(12);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(12);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(12);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                        break;
                }
            }
            if (skinType == 3) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(10);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(10);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(10);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(10);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                        break;
                }
            }
            if (skinType == 4) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                        break;
                }
            }
            if (skinType == 5) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(8);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(8);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(8);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(8);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                        break;
                }
            }
            if (skinType == 6) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(6);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(6);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(6);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(6);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
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
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                        break;
                    case 17:
                        type.setSkinType(1);
                        type.setBodyType(17);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(22);
                        break;

                }
            }
            if (skinType == 2) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(12);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(12);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(12);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(12);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                        break;
                    case 17:
                        type.setSkinType(1);
                        type.setBodyType(17);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(20);
                        break;
                }
            }
            if (skinType == 3) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                        break;
                    case 17:
                        type.setSkinType(1);
                        type.setBodyType(17);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(18);
                        break;
                }
            }
            if (skinType == 4) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(9);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                        break;
                    case 17:
                        type.setSkinType(1);
                        type.setBodyType(17);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(17);
                        break;
                }
            }
            if (skinType == 5) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(8);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(8);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(8);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(8);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                        break;
                    case 17:
                        type.setSkinType(1);
                        type.setBodyType(17);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(16);
                        break;
                }
            }
            if (skinType == 6) {
                // 男部位id
                //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
                // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
                // 13：后颈；14：后背；15：臀；16：肩；
                switch (body) {
                    case 1:
                        type.setSkinType(1);
                        type.setBodyType(1);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(6);
                    case 2:
                        type.setSkinType(1);
                        type.setBodyType(2);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(6);
                    case 3:
                        type.setSkinType(1);
                        type.setBodyType(3);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(6);
                    case 4:
                        type.setSkinType(1);
                        type.setBodyType(4);
                        type.setStack(3);
                        type.setEnergy(0);
                        type.setFluenceProposal(6);
                        break;
                    case 5:
                        type.setSkinType(1);
                        type.setBodyType(5);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 6:
                        type.setSkinType(1);
                        type.setBodyType(6);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 7:
                        type.setSkinType(1);
                        type.setBodyType(7);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 8:
                        type.setSkinType(1);
                        type.setBodyType(8);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 9:
                        type.setSkinType(1);
                        type.setBodyType(9);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 10:
                        type.setSkinType(1);
                        type.setBodyType(10);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 11:
                        type.setSkinType(1);
                        type.setBodyType(11);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 12:
                        type.setSkinType(1);
                        type.setBodyType(12);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 13:
                        type.setSkinType(1);
                        type.setBodyType(13);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 14:
                        type.setSkinType(1);
                        type.setBodyType(14);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 15:
                        type.setSkinType(1);
                        type.setBodyType(15);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                    case 16:
                        type.setSkinType(1);
                        type.setBodyType(16);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                        break;
                    case 17:
                        type.setSkinType(1);
                        type.setBodyType(17);
                        type.setStack(4);
                        type.setEnergy(0);
                        type.setFluenceProposal(14);
                        break;
                }
            }
        }

        return type;
    }
}
