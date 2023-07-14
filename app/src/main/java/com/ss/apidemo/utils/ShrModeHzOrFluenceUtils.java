package com.ss.apidemo.utils;

import com.ss.apidemo.bean.ShrModeBean;
import com.ss.apidemo.bean.ShrModeHzOrFluenceBean;

/*
 * SHR Stack SHR HR 模式 下 选择手具后 对应的 频率Hz和单脉冲能量fluence
 * * hz对应的单脉冲 且单脉冲就是进度条的最大值

 * */
public final class ShrModeHzOrFluenceUtils {

    // TODO stack 不分男女
    public ShrModeHzOrFluenceBean modeType(int handgear,int hz) {
        ShrModeHzOrFluenceBean type = new ShrModeHzOrFluenceBean();
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
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(10);
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(120);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(60);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(40);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(30);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(24);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(20);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(17);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(15);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(13);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(12);
                        break;
                }

                break;
            case 2:
                type.setHandgearType(2);
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(10);
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(180);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(90);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(60);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(45);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(36);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(30);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(25);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(22);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(20);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(18);
                        break;
                }

                break;
            case 3:
                type.setHandgearType(3);
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(11);//对应关系是 11 对应的20Hz
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(100);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(80);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(72);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(56);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(45);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(38);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(32);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(28);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(25);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(23);
                        break;
                    case 11://
                        type.setHz(11);
                        type.setFluenceMax(12);
                        break;
                }

                break;

            case 4:
                type.setHandgearType(4);
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(10);
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(120);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(60);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(40);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(30);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(24);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(20);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(17);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(15);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(13);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(12);
                        break;
                }

                break;
            case 5:
                type.setHandgearType(5);
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(10);
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(180);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(90);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(60);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(45);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(36);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(30);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(25);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(22);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(20);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(18);
                        break;
                }

                break;
            case 6:
                type.setHandgearType(6);
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(10);
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(100);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(75);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(50);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(38);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(30);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(25);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(21);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(18);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(16);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(15);
                        break;
                }

                break;
            case 7:
                type.setHandgearType(7);
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(11);//对应关系是 11 对应的20Hz
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(90);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(79);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(71);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(56);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(45);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(38);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(32);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(28);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(25);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(23);
                        break;
                    case 11:
                        type.setHz(11);
                        type.setFluenceMax(12);
                        break;
                }

                break;
            case 8:
                type.setHandgearType(8);
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(11);//对应关系是 11 对应的20Hz
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(120);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(100);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(90);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(75);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(60);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(50);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(45);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(37);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(33);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(30);
                        break;
                    case 11:
                        type.setHz(11);
                        type.setFluenceMax(15);
                        break;
                }

                break;
            case 9:
                type.setHandgearType(9);
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(11);//对应关系是 11 对应的20Hz
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(120);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(80);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(72);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(56);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(50);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(40);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(35);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(28);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(25);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(25);
                        break;
                    case 11:
                        type.setHz(11);
                        type.setFluenceMax(12);
                        break;
                }

                break;
            case 10:
                type.setHandgearType(10);
                type.setHandgearHzMin(1);
                type.setHandgearHzMax(10);//
                type.setFluenceMin(4);
                switch (hz){
                    case 1:
                        type.setHz(1);
                        type.setFluenceMax(120);
                        break;
                    case 2:
                        type.setHz(2);
                        type.setFluenceMax(70);
                        break;
                    case 3:
                        type.setHz(3);
                        type.setFluenceMax(54);
                        break;
                    case 4:
                        type.setHz(4);
                        type.setFluenceMax(40);
                        break;
                    case 5:
                        type.setHz(5);
                        type.setFluenceMax(32);
                        break;
                    case 6:
                        type.setHz(6);
                        type.setFluenceMax(28);
                        break;
                    case 7:
                        type.setHz(7);
                        type.setFluenceMax(24);
                        break;
                    case 8:
                        type.setHz(8);
                        type.setFluenceMax(22);
                        break;
                    case 9:
                        type.setHz(9);
                        type.setFluenceMax(20);
                        break;
                    case 10:
                        type.setHz(10);
                        type.setFluenceMax(18);
                        break;
                }

                break;
        }

        return type;
    }
}
