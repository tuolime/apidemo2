package com.ss.apidemo.utils;

import com.ss.apidemo.bean.HrModeBean;

/*
 * SHR Stack SHR HR 模式 下 选择手具后 对应的 单脉冲能量
 * */
public final class HrModeUtils {

    //用户性别 //1 男 2 女
    // TODO stack 不分男女
    public HrModeBean modeType(int gender, int handgear) {
        HrModeBean type = new HrModeBean();
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
                    type.setFluence1HzMax(120);
                    type.setFluence2HzMax(60);
                    type.setFluence3HzMax(40);
                    type.setFluence4HzMax(30);
                    type.setFluence5HzMax(24);
                    type.setFluence6HzMax(20);
                    type.setFluence7HzMax(17);
                    type.setFluence8HzMax(15);
                    type.setFluence9HzMax(13);
                    type.setFluence10HzMax(12);

                    break;
                case 2:
                    type.setHandgearType(2);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(180);
                    type.setFluence2HzMax(90);
                    type.setFluence3HzMax(60);
                    type.setFluence4HzMax(45);
                    type.setFluence5HzMax(36);
                    type.setFluence6HzMax(30);
                    type.setFluence7HzMax(25);
                    type.setFluence8HzMax(22);
                    type.setFluence9HzMax(20);
                    type.setFluence10HzMax(18);
                    break;
                case 3:
                    type.setHandgearType(3);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(100);
                    type.setFluence2HzMax(80);
                    type.setFluence3HzMax(72);
                    type.setFluence4HzMax(56);
                    type.setFluence5HzMax(45);
                    type.setFluence6HzMax(38);
                    type.setFluence7HzMax(32);
                    type.setFluence8HzMax(28);
                    type.setFluence9HzMax(25);
                    type.setFluence10HzMax(23);
                    type.setFluence20HzMax(12);
                    break;
                case 4:
                    type.setHandgearType(4);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(120);
                    type.setFluence2HzMax(60);
                    type.setFluence3HzMax(40);
                    type.setFluence4HzMax(30);
                    type.setFluence5HzMax(24);
                    type.setFluence6HzMax(20);
                    type.setFluence7HzMax(17);
                    type.setFluence8HzMax(15);
                    type.setFluence9HzMax(13);
                    type.setFluence10HzMax(12);
                    break;
                case 5:
                    type.setHandgearType(5);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(180);
                    type.setFluence2HzMax(90);
                    type.setFluence3HzMax(60);
                    type.setFluence4HzMax(45);
                    type.setFluence5HzMax(36);
                    type.setFluence6HzMax(30);
                    type.setFluence7HzMax(25);
                    type.setFluence8HzMax(22);
                    type.setFluence9HzMax(20);
                    type.setFluence10HzMax(18);
                    break;
                case 6:
                    type.setHandgearType(6);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(100);
                    type.setFluence2HzMax(75);
                    type.setFluence3HzMax(50);
                    type.setFluence4HzMax(38);
                    type.setFluence5HzMax(30);
                    type.setFluence6HzMax(25);
                    type.setFluence7HzMax(21);
                    type.setFluence8HzMax(18);
                    type.setFluence9HzMax(16);
                    type.setFluence10HzMax(15);
                    break;
                case 7:
                    type.setHandgearType(7);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(90);
                    type.setFluence2HzMax(79);
                    type.setFluence3HzMax(71);
                    type.setFluence4HzMax(56);
                    type.setFluence5HzMax(45);
                    type.setFluence6HzMax(38);
                    type.setFluence7HzMax(32);
                    type.setFluence8HzMax(28);
                    type.setFluence9HzMax(25);
                    type.setFluence10HzMax(23);
                    type.setFluence20HzMax(12);
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
                    type.setFluence1HzMax(120);
                    type.setFluence2HzMax(60);
                    type.setFluence3HzMax(40);
                    type.setFluence4HzMax(30);
                    type.setFluence5HzMax(24);
                    type.setFluence6HzMax(20);
                    type.setFluence7HzMax(17);
                    type.setFluence8HzMax(15);
                    type.setFluence9HzMax(13);
                    type.setFluence10HzMax(12);

                    break;
                case 2:
                    type.setHandgearType(2);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(180);
                    type.setFluence2HzMax(90);
                    type.setFluence3HzMax(60);
                    type.setFluence4HzMax(45);
                    type.setFluence5HzMax(36);
                    type.setFluence6HzMax(30);
                    type.setFluence7HzMax(25);
                    type.setFluence8HzMax(22);
                    type.setFluence9HzMax(20);
                    type.setFluence10HzMax(18);
                    break;
                case 3:
                    type.setHandgearType(3);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(100);
                    type.setFluence2HzMax(80);
                    type.setFluence3HzMax(72);
                    type.setFluence4HzMax(56);
                    type.setFluence5HzMax(45);
                    type.setFluence6HzMax(38);
                    type.setFluence7HzMax(32);
                    type.setFluence8HzMax(28);
                    type.setFluence9HzMax(25);
                    type.setFluence10HzMax(23);
                    type.setFluence20HzMax(12);
                    break;
                case 4:
                    type.setHandgearType(4);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(120);
                    type.setFluence2HzMax(60);
                    type.setFluence3HzMax(40);
                    type.setFluence4HzMax(30);
                    type.setFluence5HzMax(24);
                    type.setFluence6HzMax(20);
                    type.setFluence7HzMax(17);
                    type.setFluence8HzMax(15);
                    type.setFluence9HzMax(13);
                    type.setFluence10HzMax(12);
                    break;
                case 5:
                    type.setHandgearType(5);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(180);
                    type.setFluence2HzMax(90);
                    type.setFluence3HzMax(60);
                    type.setFluence4HzMax(45);
                    type.setFluence5HzMax(36);
                    type.setFluence6HzMax(30);
                    type.setFluence7HzMax(25);
                    type.setFluence8HzMax(22);
                    type.setFluence9HzMax(20);
                    type.setFluence10HzMax(18);
                    break;
                case 6:
                    type.setHandgearType(6);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(100);
                    type.setFluence2HzMax(75);
                    type.setFluence3HzMax(50);
                    type.setFluence4HzMax(38);
                    type.setFluence5HzMax(30);
                    type.setFluence6HzMax(25);
                    type.setFluence7HzMax(21);
                    type.setFluence8HzMax(18);
                    type.setFluence9HzMax(16);
                    type.setFluence10HzMax(15);
                    break;
                case 7:
                    type.setHandgearType(7);
                    type.setFluenceMin(4);
                    type.setFluence1HzMax(90);
                    type.setFluence2HzMax(79);
                    type.setFluence3HzMax(71);
                    type.setFluence4HzMax(56);
                    type.setFluence5HzMax(45);
                    type.setFluence6HzMax(38);
                    type.setFluence7HzMax(32);
                    type.setFluence8HzMax(28);
                    type.setFluence9HzMax(25);
                    type.setFluence10HzMax(23);
                    type.setFluence20HzMax(12);
                    break;
            }
        }

        return type;
    }
}
