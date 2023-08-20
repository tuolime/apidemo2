package com.ss.apidemo.utils;

import com.ss.apidemo.bean.AutoSkinBean;
import com.ss.apidemo.bean.HrSkinBean;
import com.ss.apidemo.bean.ShrSkinBean;
import com.ss.apidemo.bean.StackSkinBean;

/*
 * 工作模式2 auto模式 肤色及身体对应的默认值
 * */
public final class AutoSkinTypeUtils {

    //用户性别 //1 男 2 女
    public AutoSkinBean modeType(int skinType, int handgear, int body) {
        AutoSkinBean type = new AutoSkinBean();
        type.setModeType(7);
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
            if (handgear == 1 || handgear == 2 || handgear == 3 || handgear == 4 || handgear == 5
                    || handgear == 6 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(16);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(22);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(22);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(22);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(20);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(20);
                    type.setHzProposal(4);
                }

            }

        }
        if (skinType == 2) {
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

            if (handgear == 1 || handgear == 2 || handgear == 4 || handgear == 5
                    || handgear == 6) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(15);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(21);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(21);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(21);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(19);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(19);
                    type.setHzProposal(4);
                }
            }
            if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(14);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(20);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(20);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(20);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(4);
                }
            }
        }
        if (skinType == 3) {

            if (handgear == 1 || handgear == 2 || handgear == 4 || handgear == 5
                    || handgear == 6) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(14);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(20);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(20);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(20);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(4);
                }
            }
            if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(12);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(16);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(16);
                    type.setHzProposal(4);
                }
            }
        }
        if (skinType == 4) {

            if (handgear == 1 || handgear == 2 || handgear == 4 || handgear == 5
                    || handgear == 6) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(13);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(19);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(19);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(19);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(17);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(17);
                    type.setHzProposal(4);
                }
            }
            if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(11);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(17);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(17);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(17);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(15);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(15);
                    type.setHzProposal(4);
                }
            }
        }
        if (skinType == 5) {

            if (handgear == 1 || handgear == 2 || handgear == 4 || handgear == 5
                    || handgear == 6) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(12);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(18);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(16);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(16);
                    type.setHzProposal(4);
                }
            }
            if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(10);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(16);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(16);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(16);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(14);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(14);
                    type.setHzProposal(4);
                }
            }
        }
        if (skinType == 6) {
            if (handgear == 1 || handgear == 2 || handgear == 4 || handgear == 5
                    || handgear == 6) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(11);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(17);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(17);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(17);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(15);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(15);
                    type.setHzProposal(4);
                }
            }
            if (handgear == 3 || handgear == 7 || handgear == 8 || handgear == 9 || handgear == 10) {
                //1：面部；2：四肢；3：腋下；4：前胸； 5：后背；6：比基尼；
                if (body == 1) {
                    type.setFluenceProposal(8);
                    type.setHzProposal(4);
                } else if (body == 2) {
                    type.setFluenceProposal(14);
                    type.setHzProposal(5);
                } else if (body == 3) {
                    type.setFluenceProposal(14);
                    type.setHzProposal(5);
                } else if (body == 4) {
                    type.setFluenceProposal(14);
                    type.setHzProposal(5);
                } else if (body == 5) {
                    type.setFluenceProposal(12);
                    type.setHzProposal(5);
                } else if (body == 6) {
                    type.setFluenceProposal(12);
                    type.setHzProposal(4);
                }
            }
        }

        return type;
    }
}
