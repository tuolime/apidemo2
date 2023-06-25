package com.example.protocol.utils;


import com.example.protocol.exception.FormatException;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.core.util.HexUtil;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;

public class ParserUtil {
    private final static char[] HEX_DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     * 数组转字符串
     *
     * @param b
     * @return
     */
    public static String toHexString(byte[] b) {
        return toHexString(b, 0, b.length);
    }

    /**
     * 数组转字符串
     *
     * @param data
     * @param offset
     * @param length
     * @return
     */
    public static String toHexString(byte[] data, int offset, int length) {
        char[] buf = new char[length * 2];
        int bufIndex = 0;
        for (int i = offset; i < offset + length; i++) {
            byte b = data[i];
            buf[bufIndex++] = HEX_DIGITS[(b >>> 4) & 0x0F];
            buf[bufIndex++] = HEX_DIGITS[b & 0x0F];
        }
        return new String(buf);
    }

    /**
     * 字节转字符串
     *
     * @param b
     * @return
     */
    public static String toHexString(byte b) {
        int hi = (byte) (b >> 4 & 0xF);
        int low = (byte) (b & 0xF);
        return Integer.toHexString(hi) + Integer.toHexString(low);
    }

    /**
     * 数组转字符串(解析数值)
     *
     * @param b
     * @param start
     * @param lenth
     * @return
     */
    public static Integer toBcdHexString(byte[] b, int start, int lenth) {

        byte[] bytes = new byte[lenth];
        System.arraycopy(b, start, bytes, 0, lenth);
//        StringBuffer temp = new StringBuffer(bytes.length * 2);
//        for (int i = bytes.length - 1; i >= 0; i--) {

//        for(int i = 0 ; i< bytes.length; i ++){
//            temp.append((byte) ((bytes[i] & 0xF0) >>> 4));
//            temp.append((byte) (bytes[i] & 0xF));
//        }
        //return temp.toString();
        return Integer.parseInt(HexUtil.encodeHexStr(bytes), 16);
    }

    /**
     * 带有空格的报文格式
     *
     * @param b
     * @return
     */
    public static String toHexLogString(byte[] b) {
        return toHexString(b, 0, b.length).replaceAll("(.{2})", "$1 ");
    }

    /**
     * 带有空格的报文格式
     *
     * @param s
     * @return
     */
    public static String toHexLogString(String s) {
        return s.replaceAll("(.{2})", "$1 ");
    }

    static int[] listToArray(List<Integer> list) {
        int[] rt = new int[list.size()];
        int i = 0;
        for (Integer e : list) {
            rt[(i++)] = e.intValue();
        }
        return rt;
    }

    public static int toUnSignByte(byte b) {
        return b < 0 ? b + 256 : b;
    }

    public static long toUnSignInt(int b) {
        return b < 0 ? b + 4294967295L : b;
    }

    public static int toUnsignedInt(byte a, byte b) {
        return (a < 0 ? a + 256 : a) + (b < 0 ? b + 256 : b) * 256;
    }

    public static int toUnsignedInt(byte[] data, int offset) {
        return (data[offset] < 0 ? data[offset] + 256 : data[offset])
                + (data[(offset + 1)] < 0 ? data[(offset + 1)] + 256 : data[(offset + 1)]) * 256;
    }

    public static int fromBcd(byte bcdCode) {
        int value = NumberUtil.fromBcd(bcdCode);
        if (value < 0) {
            return 0;
        }

        return value;
    }

    public static int fromHi3BitsBcd(byte bcdCode) {
        int value = NumberUtil.fromHi3BitsBcd(bcdCode);
        if (value < 0) {
            throw new FormatException(
                    "Convert from hi3BitsBcd exception. BCD code is 0x" + Integer.toHexString(bcdCode));
        }
        return value;
    }

    public static int fromHi4BitsBcd(byte bcdCode) {
        int value = ProtocolUtil.fromHi4BitsBcd(bcdCode);
        if (value < 0) {
            throw new FormatException(
                    "Convert from hi3BitsBcd exception. BCD code is 0x" + Integer.toHexString(bcdCode));
        }
        return value;
    }

    public static int fromLo4BitsBcd(byte bcdCode) {
        int value = ProtocolUtil.fromLo4BitsBcd(bcdCode);
        if (value < 0) {
            throw new FormatException(
                    "Convert from lo4BitsBcd exception. BCD code is 0x" + Integer.toHexString(bcdCode));
        }
        return value;
    }

    public static long fromUnsignedBcd(byte[] frame, int offset, int length) {
        long value = ProtocolUtil.fromUnsignedBcd(frame, offset, length);
        if (value < 0L) {
            throw new FormatException(
                    "Convert from unsignedBcd exception. BCD code is " + getHexFromBytes(frame, offset, length));
        }
        return value;
    }

    public static long fromUnsignedBcd4BE(byte[] frame, int offset, int length) {
        long value = ProtocolUtil.fromUnsignedBcd4BE(frame, offset, length);
        if (value < 0L) {
            throw new FormatException(
                    "Convert from unsignedBcd exception. BCD code is " + getHexFromBytes(frame, offset, length));
        }
        return value;
    }

    public static double getSignedValue(byte signedByte, int signedPos, double value) {
        if ((signedByte >> signedPos & 0x1) == 0) {
            return value;
        }
        return -value;
    }

    public static int getSignedValue(byte signedByte, int signedPos, int value) {
        if ((signedByte >> signedPos & 0x1) == 0) {
            return value;
        }
        return -value;
    }

    public static long getSignedValue(byte signedByte, int signedPos, long value) {
        if ((signedByte >> signedPos & 0x1) == 0) {
            return value;
        }
        return -value;
    }

    public static byte intTo1Bcd(int bcdCode) {
        byte b = 0;
        int hi = bcdCode / 10;
        int low = bcdCode % 10;
        b = (byte) hi;
        b = (byte) ((b << 4) + low);

        return b;
    }

    public static Date[] parseTd7Bytes(byte[] frame, int offset) {
        Date t = parseMHDMY(frame, offset);
        Calendar c = timestampToCalendar(t);
        int m = frame[(offset + 5)];
        m = m < 0 ? m + 256 : m;
        int min = 0;
        if (m == 0) {
            return new Timestamp[0];
        }
        if (m == 1) {
            min = 15;
        } else if (m == 2) {
            min = 30;
        } else if (m == 3) {
            min = 60;
        } else if (m == 254) {
            min = 5;
        } else if (m == 255) {
            min = 1;
        } else {
            throw new FormatException("expect m [1,2,3], bus m is " + m);
        }

        int n = frame[(offset + 6)];
        n = n < 0 ? n + 256 : n;
        Calendar[] cs = new GregorianCalendar[n];
        Timestamp[] ts = new Timestamp[n];
        for (int i = 0; i < n; i++) {
            cs[i] = c;
            ts[i] = new Timestamp(c.getTimeInMillis());
            c.add(12, min);
        }

        return ts;
    }

    public static int parseByteToBcd(byte bcd) {
        return fromBcd(bcd);
    }

    @SuppressWarnings("unused")
    public static String parseAsc(byte[] frame, int offset, int length) {
        try {
            for (int i = 0; i < length; i++) {
                if (frame[(offset + i)] == 0) {
                    break;
                }
                // XXX:暂时修改方案
                return new String(frame, offset, i, "US-ASCII");
            }
            // return new String(frame, offset, i, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
        }
        throw new FormatException();
    }

    @SuppressWarnings("unused")
    public static String parseAsc2(byte[] frame, int offset, int length) {
        try {
            for (int i = 0; i < length; i++) {
                if (frame[(offset + i)] != 0) {
                    // XXX:暂时修改方案
                    break;
                }
                return new String(frame, offset, i, "US-ASCII");
            }

            // return new String(frame, offset, i, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
        }
        throw new FormatException();
    }

    private static String getHexFromBytes(byte[] frame, int offset, int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append("0x").append(Integer.toHexString(frame[(offset + i)])).append(' ');
        }
        return sb.toString();
    }

    public static int[] parseBS(byte bs) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 8; i++) {
            if ((bs >> i & 0x1) != 0) {
                list.add(new Integer(i));
            }
        }

        return listToArray(list);
    }

    public static int[] parseBS(byte[] bs, int offset, int length) {
        List<Integer> list = new ArrayList<Integer>();
        for (int j = 0; j < length; j++) {
            for (int i = 0; i < 8; i++) {
                if ((bs[(offset + j)] >> i & 0x1) != 0) {
                    list.add(new Integer(i + j * 8));
                }
            }
        }

        return listToArray(list);
    }

    public static Date getCurTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static byte[] getDWMYByteArray(Date t) {
        byte[] dwmyByte = new byte[3];
        Calendar c = timestampToCalendar(t);

        dwmyByte[0] = ProtocolUtil.to1ByteBcd(c.get(5));
        dwmyByte[1] = ((byte) (ProtocolUtil.to1ByteBcd(c.get(2) + 1) | ProtocolUtil.to1ByteBcd(c.get(7) - 1) << 5));
        dwmyByte[2] = ProtocolUtil.to1ByteBcd(c.get(1));

        return dwmyByte;
    }

    public static boolean isToday(Date t) {
        Calendar today = new GregorianCalendar();
        Calendar c = timestampToCalendar(t);

        if ((today.get(1) == c.get(1)) && (today.get(2) == c.get(2)) && (today.get(5) == c.get(5))) {
            return true;
        }
        return false;
    }

    public static Calendar timestampToCalendar(Date t) {
        Calendar c = new GregorianCalendar();
        c.setTime(t);

        return c;
    }

    public static byte[] tranHGTimePeriodToGB(byte[] frame) {
        byte[] data = new byte[49];
        byte[] temp = new byte[48];

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {
                data[(i * 4 + j + 1)] = ((byte) (toUnSignByte(frame[i]) & 0x3));
                frame[i] = ((byte) (frame[i] >>> 2));
            }

        }

        data[0] = 1;
        for (int i = 1; i < 48; i++) {
            if (data[i] == data[(i + 1)]) {
                data[i] = data[(i - 1)];
            } else if (data[(i - 1)] == 1) {
                data[i] = ((byte) (data[(i - 1)] + 1 & 0x3));
            } else {
                data[i] = ((byte) (data[(i - 1)] + 3 & 0x3));
            }

        }

        temp[0] = 1;
        System.arraycopy(data, 0, temp, 0, 48);

        frame = new byte[12];
        for (int i = 0; i < 12; i++) {
            for (int m = 0; m < 4; m++) {
                frame[(11 - i)] = ((byte) (frame[(11 - i)] << 2));
                frame[(11 - i)] = ((byte) (toUnSignByte(frame[(11 - i)]) | temp[(47 - (i * 4 + m))]));
            }

        }

        return frame;
    }

    public static Date parseHGTime(byte[] frame, int offset, int iType) {
        Date ts;
        int min;
        int hour;
        int day;
        int month;
        int year;
        Calendar c;
        int sec;
        switch (iType) {
            case 1:
                min = fromBcd(frame[(offset++)]);
                hour = fromBcd(frame[(offset++)]);
                day = fromBcd(frame[(offset++)]);
                month = fromBcd(frame[(offset++)]);
                year = 2000 + fromBcd(frame[(offset++)]);
                ts = new Timestamp(new GregorianCalendar(year, month - 1, day, hour, min).getTimeInMillis());
                break;
            case 2:
                c = timestampToCalendar(getCurTime());
                min = fromBcd(frame[(offset++)]);
                hour = fromBcd(frame[(offset++)]);
                day = fromBcd(frame[(offset++)]);
                month = fromBcd(frame[(offset++)]);
                ts = new Timestamp(new GregorianCalendar(c.get(1), month - 1, day, hour, min).getTimeInMillis());
                break;
            case 3:
                day = fromBcd(frame[(offset++)]);
                month = fromBcd(frame[(offset++)]);
                year = 2000 + fromBcd(frame[(offset++)]);
                ts = new Timestamp(new GregorianCalendar(year, month - 1, day).getTimeInMillis());
                break;
            case 4:
                c = timestampToCalendar(getCurTime());
                sec = fromBcd(frame[(offset++)]);
                min = fromBcd(frame[(offset++)]);
                hour = fromBcd(frame[(offset++)]);
                ts = new Timestamp(new GregorianCalendar(c.get(1), c.get(2), c.get(5), hour, min, sec).getTimeInMillis());
                break;
            case 5:
                c = timestampToCalendar(getCurTime());
                min = fromBcd(frame[(offset++)]);
                hour = fromBcd(frame[(offset++)]);
                ts = new Timestamp(new GregorianCalendar(c.get(1), c.get(2), c.get(5), hour, min).getTimeInMillis());
                break;
            case 6:
                c = timestampToCalendar(getCurTime());
                sec = fromBcd(frame[(offset++)]);
                min = fromBcd(frame[(offset++)]);
                hour = fromBcd(frame[(offset++)]);
                day = fromBcd(frame[(offset++)]);
                ts = new Timestamp(new GregorianCalendar(c.get(1), c.get(2), day, hour, min, sec).getTimeInMillis());
                break;
            case 7:
                c = timestampToCalendar(getCurTime());
                hour = fromBcd(frame[(offset++)]);
                min = fromBcd(frame[(offset++)]);
                ts = new Timestamp(new GregorianCalendar(c.get(1), c.get(2), c.get(5), hour, min).getTimeInMillis());
                break;
            case 8:
                c = timestampToCalendar(getCurTime());
                day = fromBcd(frame[(offset++)]);
                month = fromBcd(frame[(offset++)]);
                year = 2000 + fromBcd(frame[(offset++)]);
                min = fromBcd(frame[(offset++)]);
                hour = fromBcd(frame[(offset++)]);
                ts = new Timestamp(new GregorianCalendar(year, month - 1, day, hour, min).getTimeInMillis());
                break;
            default:
                ts = getCurTime();
        }

        return ts;
    }

    public static Date[] parseHGMutilTime(byte[] frame, int offset, int iPointsNum, int type) {
        Timestamp[] ts = new Timestamp[iPointsNum];
        int min = 1440 / iPointsNum;
        Date t = parseHGTime(frame, offset, 3);
        Calendar c = timestampToCalendar(t);

        if (type == 2) {
            c.add(12, 15);
        }

        for (int i = 1; i <= iPointsNum; i++) {
            ts[(i - 1)] = new Timestamp(c.getTimeInMillis());
            c.add(12, min);
        }

        return ts;
    }

    public static double parse06_dist_1(byte[] frame, int offset) {
        double value = fromBcd(frame[offset]) / 100.0D;
        value += fromLo4BitsBcd(frame[(offset + 1)]);
        value += fromHi4BitsBcd(frame[(offset + 1)]) * 10;
        return value;
    }

    public static double parseBCD1DF0(byte[] frame, int offset) {
        return fromBcd(frame[offset]);
    }

    public static double parseBCD1DF1(byte bcd) {
        return fromBcd(bcd) / 10.0D;
    }

    public static double parseBCD1S0(byte[] frame, int offset) {
        int value = fromLo4BitsBcd(frame[offset]);
        value += fromHi3BitsBcd(frame[offset]) * 10;
        if ((frame[offset] & 0x80) == 0) {
            return 1.0D + value / 100.0D;
        }
        return 1.0D - value / 100.0D;
    }

    public static double parseBCD2(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 2);
    }

    public static double parseBCD2DF0(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 2);
    }

    public static double parseBCD2DF1(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 2) / 10.0D;
    }

    public static double parseBCD2DF2(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 2) / 100.0D;
    }

    public static double parseBCD2DF3(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 2) / 1000.0D;
    }

    public static double parseBCD2DF3BE(byte[] frame, int offset) {
        return fromUnsignedBcd4BE(frame, offset, 2) / 1000.0D;
    }

    public static double parseBCD2SDF1(byte[] frame, int offset) {
        double value = fromBcd(frame[offset]) / 10.0D;
        value += fromLo4BitsBcd(frame[(offset + 1)]) * 10;
        value += fromHi3BitsBcd(frame[(offset + 1)]) * 100;

        return getSignedValue(frame[(offset + 1)], 7, value);
    }

    public static double parseBCD2SDF2(byte[] frame, int offset) {
        double value = fromBcd(frame[offset]) / 100.0D;
        value += fromLo4BitsBcd(frame[(offset + 1)]);
        value += fromHi3BitsBcd(frame[(offset + 1)]) * 10;

        return getSignedValue(frame[(offset + 1)], 7, value);
    }

    public static double parseBCD2D7DF2(byte[] frame, int offset) {
        double value = fromBcd(frame[offset]) / 100.0D;
        value += fromLo4BitsBcd(frame[(offset + 1)]);
        value += fromHi3BitsBcd(frame[(offset + 1)]) * 10;

        return value;
    }

    public static int parseBCD3DF0(byte[] frame, int offset) {
        return (int) fromUnsignedBcd(frame, offset, 3);
    }

    public static double parseBCD3DF2(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 3) / 100.0D;
    }

    public static double parseBCD3DF3BE(byte[] frame, int offset) {
        return fromUnsignedBcd4BE(frame, offset, 3) / 1000.0D;
    }

    public static double parseBCD3DF4(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 3) / 10000.0D;
    }

    public static double parseBCD3SDF4(byte[] frame, int offset) {
        double value = fromUnsignedBcd(frame, offset, 2) / 10000.0D;
        value += fromLo4BitsBcd(frame[(offset + 2)]);
        value += fromHi3BitsBcd(frame[(offset + 2)]) * 10;

        value = NumberUtil.formatNDigitNumber(value, 4);

        return getSignedValue(frame[(offset + 2)], 7, value);
    }

    public static double parseBCD3SDF3(byte[] frame, int offset) {
        double value = fromUnsignedBcd(frame, offset, 2) / 1000.0D;
        value += fromLo4BitsBcd(frame[(offset + 2)]) * 10;
        value += fromHi3BitsBcd(frame[(offset + 2)]) * 100;

        return getSignedValue(frame[(offset + 2)], 7, value);
    }

    public static double parseBCD4SDF3(byte[] frame, int offset) {
        double value = fromLo4BitsBcd(frame[offset]) / 100.0D;
        value += fromHi3BitsBcd(frame[offset]) / 10.0D;
        value += fromLo4BitsBcd(frame[(offset + 1)]);
        value += fromHi3BitsBcd(frame[(offset + 1)]) * 10;
        value += fromLo4BitsBcd(frame[(offset + 2)]) * 100;
        value += fromHi3BitsBcd(frame[(offset + 2)]) * 1000;
        value += fromLo4BitsBcd(frame[(offset + 3)]) * 10000;
        value += fromHi3BitsBcd(frame[(offset + 3)]) * 100000;
        return getSignedValue(frame[(offset + 3)], 7, value);
    }

    public static double parseBCD4SDF31(byte[] frame, int offset) {
        double value = fromLo4BitsBcd(frame[offset]);
        value += fromHi3BitsBcd(frame[offset]) * 10;
        value += fromLo4BitsBcd(frame[(offset + 1)]) * 100;
        value += fromHi3BitsBcd(frame[(offset + 1)]) * 1000;
        value += fromLo4BitsBcd(frame[(offset + 2)]) * 10000;
        value += fromHi3BitsBcd(frame[(offset + 2)]) * 100000;
        return getSignedValue(frame[(offset + 2)], 7, value);
    }

    public static double parseBCD3D7DF4(byte[] frame, int offset) {
        return parseBCD3SDF4(frame, offset);
    }

    public static double parseBCD4DF0(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 4);
    }

    public static double parseBCD4DF2(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 4) / 100.0D;
    }

    public static double parseBCD4DF3(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 4) / 1000.0D;
    }

    public static double parseBCD4DF4(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 4) / 10000.0D;
    }

    public static double parseBCD5DF4(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 5) / 10000.0D;
    }

    public static long parseBCD6DF0(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 6);
    }

    public static String parseBCDnDF0WithHexContent(byte[] frame, int offset) {
        String val = "";
        for (int i = 0; i < 6; i++) {
            val = ProtocolUtil.fromBcdWithHexContent(frame[(offset + i)]) + val;
        }
        return val;
    }

    public static double parseBCD6DF2(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 6) / 100.0D;
    }

    public static double parseBCD7DF8(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 7) / Math.pow(10.0D, 8.0D);
    }

    public static double parseBCD7DF8BE(byte[] frame, int offset) {
        return fromUnsignedBcd4BE(frame, offset, 7) / Math.pow(10.0D, 8.0D);
    }

    public static long parseBCD8DF0(byte[] frame, int offset) {
        return fromUnsignedBcd(frame, offset, 8);
    }

    public static double parseG3G2G1(byte[] frame, int offset) {
        if (16 == (frame[(offset + 1)] & 0x10)) {
            throw new FormatException("G3G2G1格式解析错误");
        }
        return parseG3G2G1S(frame, offset);
    }

    public static double parseG3G2G11(byte[] frame, int offset) {
        byte[] data = new byte[2];

        data[0] = frame[offset];
        data[1] = ((byte) (frame[(offset + 1)] & 0xEF));
        return parseG3G2G1S(data, 0);
    }

    public static double parseG3G2G1S(byte[] frame, int offset) {
        int value = (int) fromUnsignedBcd(frame, offset, 1);
        value += fromLo4BitsBcd(frame[(offset + 1)]) * 100;
        value = getSignedValue(frame[(offset + 1)], 4, value);

        int pow = frame[(offset + 1)] >> 5 & 0x7;

        double dValue = value * Math.pow(10.0D, 4 - pow);

        String strValue = String.valueOf(dValue);
        if (strValue.contains(".")) {
            int dwDotNum = strValue.length() - strValue.indexOf(".");
            if (dwDotNum > 5) {
                strValue = strValue.substring(0, strValue.indexOf(".") + 5);
            }
        }
        return Double.valueOf(strValue).doubleValue();
    }

    public static double parseG(byte[] frame, int offset) {
        return parseGS(frame, offset);
    }

    public static long parseGS(byte[] frame, int offset) {
        long value = fromUnsignedBcd(frame, offset, 3);
        value += fromLo4BitsBcd(frame[(offset + 3)]) * 1000000;

        value = getSignedValue(frame[(offset + 3)], 4, value);

        if ((frame[(offset + 3)] & 0x40) != 0) {
            value *= 1000L;
        }

        return value;
    }

    public static double parseZG(byte[] frame, int offset) {
        byte[] data = new byte[4];
        data[0] = frame[offset];
        data[1] = frame[(offset + 1)];
        data[2] = frame[(offset + 2)];
        data[3] = ((byte) (0x7F & frame[(offset + 3)]));

        return parseG(data, 0);
    }

    public static double parseZGS3(byte[] frame, int offset) {
        byte[] data = new byte[4];
        data[0] = frame[offset];
        data[1] = frame[(offset + 1)];
        data[2] = frame[(offset + 2)];
        data[3] = ((byte) (0x7F & frame[(offset + 3)]));

        return parseGS(data, 0);
    }

    public static double parseS3(byte[] frame, int offset) {
        byte[] data = new byte[4];
        data[0] = frame[offset];
        data[1] = frame[(offset + 1)];
        data[2] = frame[(offset + 2)];
        data[3] = ((byte) (0x1F & frame[(offset + 3)]));

        return parseGS(data, 0);
    }

    public static Date parseSMH(byte[] frame, int offset) {
        int sec = fromBcd(frame[(offset++)]);
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);

        Calendar c = timestampToCalendar(getCurTime());
        return new Timestamp(new GregorianCalendar(c.get(1), c.get(2), c.get(5), hour, min, sec).getTimeInMillis());
    }

    public static Date parseHMS_BIN(byte[] frame, int offset) {
        int hour = frame[(offset++)];
        int min = frame[(offset++)];
        int sec = frame[(offset++)];

        Calendar c = timestampToCalendar(getCurTime());
        return new Timestamp(new GregorianCalendar(c.get(1), c.get(2), c.get(5), hour, min, sec).getTimeInMillis());
    }

    public static Date parseSMHD(byte[] frame, int offset) {
        int sec = fromBcd(frame[(offset++)]);
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        int day = fromBcd(frame[(offset++)]);
        if ((day <= 0) || (day > 31)) {
            return null;
        }
        Calendar c = timestampToCalendar(getCurTime());
        return new Timestamp(new GregorianCalendar(c.get(1), c.get(2), day, hour, min, sec).getTimeInMillis());
    }

    public static Date parseSMHDWMY(byte[] frame, int offset) {
        int sec = fromBcd(frame[(offset++)]);
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        int day = fromBcd(frame[(offset++)]);
        int month = (frame[offset] >> 4 & 0x1) * 10 + fromLo4BitsBcd(frame[offset]) - 1;
        offset++;
        if ((day < 1) || (day > 31)) {
            return null;
        }

        if ((month < 0) || (month > 12)) {
            return null;
        }
        int year = 2000 + fromBcd(frame[(offset++)]);

        return new Timestamp(new GregorianCalendar(year, month, day, hour, min, sec).getTimeInMillis());
    }

    public static Date parseMH(byte[] frame, int offset) {
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);

        Calendar c = timestampToCalendar(getCurTime());
        return new Timestamp(new GregorianCalendar(c.get(1), c.get(2), c.get(5), hour, min).getTimeInMillis());
    }

    public static Date parseMHD4Today(byte[] frame, int offset) {
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        int day = fromBcd(frame[(offset++)]);
        if ((day <= 0) || (day > 31)) {
            return null;
        }
        Calendar c = timestampToCalendar(getCurTime());
        int month = c.get(2);
        if ((day == 31)
                && ((c.get(2) == 1) || (c.get(2) == 3) || (c.get(2) == 5) || (c.get(2) == 8) || (c.get(2) == 10))) {
            month = c.get(2) - 1;
        }
        return new Timestamp(new GregorianCalendar(c.get(1), month, day, hour, min).getTimeInMillis());
    }

    public static Date parseMH4Someday(byte[] frame, int offset, Timestamp someday) {
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);

        Calendar c = timestampToCalendar(someday);
        return new Timestamp(new GregorianCalendar(c.get(1), c.get(2), c.get(5), hour, min).getTimeInMillis());
    }

    public static Date parseMHD4Someday(byte[] frame, int offset, Timestamp someday) {
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        int day = fromBcd(frame[(offset++)]);

        Calendar c = timestampToCalendar(someday);
        return new Timestamp(new GregorianCalendar(c.get(1), c.get(2), day, hour, min).getTimeInMillis());
    }

    public static Date parseMHDM(byte[] frame, int offset) {
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        int day = fromBcd(frame[(offset++)]);
        int month = fromBcd(frame[(offset++)]);

        month--;
        if ((day <= 0) || (day > 31)) {
            return null;
        }
        if ((month < 0) || (month > 12)) {
            return null;
        }
        Calendar c = timestampToCalendar(getCurTime());
        Date t = new Timestamp(new GregorianCalendar(c.get(1), month, day, hour, min).getTimeInMillis());

        return t;
    }

    public static Date parseMHDM4LastMonData(byte[] frame, int offset) {
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        int day = fromBcd(frame[(offset++)]);
        int month = fromBcd(frame[(offset++)]);

        if (((min == 0) && (hour == 0) && (day == 0) && (month == 0))
                || ((min == 238) && (hour == 238) && (day == 238) && (month == 238))) {
            return new Timestamp(0L);
        }

        month--;

        Calendar c = timestampToCalendar(getCurTime());
        c.add(2, -1);

        return new Timestamp(new GregorianCalendar(c.get(1), month, day, hour, min).getTimeInMillis());
    }

    public static Date parseMHDM_BIN(byte[] frame, int offset) {
        int min = frame[(offset++)];
        int hour = frame[(offset++)];
        int day = frame[(offset++)];
        int month = frame[(offset++)];

        if ((((min & 0xFF) == 0) && ((hour & 0xFF) == 0) && ((day & 0xFF) == 0) && ((month & 0xFF) == 0))
                || (((min & 0xFF) == 238) && ((hour & 0xFF) == 238) && ((day & 0xFF) == 238) && ((month & 0xFF) == 238))) {
            return new Timestamp(0L);
        }

        month--;

        Calendar c = timestampToCalendar(getCurTime());
        Date t = new Timestamp(new GregorianCalendar(c.get(1), month, day, hour, min).getTimeInMillis());

        return t;
    }

    public static Date parseMHDMY(byte[] frame, int offset) {
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        int day = fromBcd(frame[(offset++)]);
        int month = fromBcd(frame[(offset++)]) - 1;
        int year = 2000 + fromBcd(frame[(offset++)]);
        if ((day <= 0) || (day > 31)) {
            return null;
        }
        if ((month < 0) || (month > 12)) {
            return null;
        }
        return new Timestamp(new GregorianCalendar(year, month, day, hour, min).getTimeInMillis());
    }

    public static Date parseSMHDMYY(byte[] frame, int offset) {
        int sec = fromBcd(frame[(offset++)]);
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        int day = fromBcd(frame[(offset++)]);
        int month = fromBcd(frame[(offset++)]) - 1;
        int year = fromBcd(frame[(offset++)]);
        year += 100 * fromBcd(frame[(offset++)]);
        if ((day <= 0) || (day > 31)) {
            return null;
        }
        if ((month < 0) || (month > 12)) {
            return null;
        }
        return new Timestamp(new GregorianCalendar(year, month, day, hour, min, sec).getTimeInMillis());
    }

    public static Date parseDMY(byte[] frame, int offset) {
        int day = fromBcd(frame[(offset++)]);
        int month = fromBcd(frame[(offset++)]) - 1;
        if ((day <= 0) || (day > 31)) {
            return null;
        }
        if ((month < 0) || (month > 12)) {
            return null;
        }
        int year = 2000 + fromBcd(frame[(offset++)]);
        return new Timestamp(new GregorianCalendar(year, month, day).getTimeInMillis());
    }

    public static Date parseYMD_BIN(byte[] frame, int offset) {
        int year = 2000 + frame[(offset++)];
        int month = frame[(offset++)] - 1;
        int day = frame[(offset++)];
        if ((day <= 0) || (day > 31)) {
            return null;
        }
        if ((month < 0) || (month > 12)) {
            return null;
        }
        return new Timestamp(new GregorianCalendar(year, month, day).getTimeInMillis());
    }

    public static Date parseDMYMH(byte[] frame, int offset) {
        int day = fromBcd(frame[(offset++)]);
        int month = fromBcd(frame[(offset++)]);
        int year = 2000 + fromBcd(frame[(offset++)]);
        int min = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        if ((day <= 0) || (day > 31)) {
            return null;
        }
        if ((month < 0) || (month > 12)) {
            return null;
        }
        return new Timestamp(new GregorianCalendar(year, month - 1, day, hour, min).getTimeInMillis());
    }

    public static Date parseMY(byte[] frame, int offset) {
        int month = fromBcd(frame[(offset++)]) - 1;
        int year = fromBcd(frame[(offset++)]) + 2000;
        if ((month < 0) || (month > 12)) {
            return null;
        }
        return new Timestamp(new GregorianCalendar(year, month, 1).getTimeInMillis());
    }

    public static Date parseYM(byte[] frame, int offset) {
        int year = fromBcd(frame[(offset++)]) + 2000;
        int month = fromBcd(frame[(offset++)]) - 1;
        if ((month < 0) || (month > 12)) {
            return null;
        }
        return new Timestamp(new GregorianCalendar(year, month, 1).getTimeInMillis());
    }

    public static Date parseYMDHM(byte[] frame, int offset) {
        int year = 2000 + fromBcd(frame[(offset++)]);
        int month = fromBcd(frame[(offset++)]);
        int day = fromBcd(frame[(offset++)]);
        int hour = fromBcd(frame[(offset++)]);
        int min = fromBcd(frame[(offset++)]);
        if ((day <= 0) || (day > 31)) {
            return null;
        }
        if ((month < 0) || (month > 12)) {
            return null;
        }
        return new Timestamp(new GregorianCalendar(year, month - 1, day, hour, min).getTimeInMillis());
    }

    public static double parseBIN1DF2(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 1) / 100.0D;
    }

    public static double parseBIN1DF0(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 1);
    }

    public static double parseBIN1SDF2(byte[] frame, int offset) {
        double val = 0.0D;
        int sign = 0;

        sign = frame[offset] >> 7 & 0x1;
        if (sign == 0) {
            val = toUnSignByte(frame[offset]) & 0x7F;
        } else {
            val = (toUnSignByte(frame[offset]) & 0x7F) * -1;
        }

        return val / 100.0D;
    }

    public static double parseBIN2DF04HGACS(byte[] frame, int offset) {
        int bin2 = toUnsignedInt(frame, offset);
        if (61440 == (bin2 & 0xF000)) {
            throw new FormatException("特殊的BIN格式数据BIN2DF04HGACS转换错误，传入信息为" + getHexFromBytes(frame, offset, 2));
        }

        double value = (bin2 & 0xFFF) * Math.pow(10.0D, (bin2 & 0x7000) >>> 12);
        if (32768 == (bin2 & 0x8000)) {
            value *= -1.0D;
        }

        return value;
    }

    public static double parseBIN2DF0(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 2);
    }

    public static double parseBIN2DF1F(byte[] frame, int offset) {
        return (short) (int) ProtocolUtil.toLongF(frame, offset, 2) / 10.0D;
    }

    public static double parseBIN2DF2(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 2) / 100.0D;
    }

    public static double parseBIN2DF3(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 2) / 1000.0D;
    }

    public static double parseBIN2DF4(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 2) / 10000.0D;
    }

    public static double parseBIN3DF3(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 3) / 1000.0D;
    }

    public static double parseBIN4DF0(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 4);
    }

    public static double parseBIN4DF2(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 4) / 100.0D;
    }

    public static double parseBIN4DF3(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 4) / 1000.0D;
    }

    public static double parseBIN6DF0(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 6);
    }

    public static double parseBINX2P(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 2) / 16384.0D;
    }

    public static double parseBINX2U(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 2) * 125L / 16384.0D;
    }

    public static double parseBINX2I(byte[] frame, int offset) {
        return ProtocolUtil.toLong(frame, offset, 2) * 6L / 16384.0D;
    }

    public static double parseBIN4DF0F(byte[] frame, int offset) {
        return ProtocolUtil.toLongF(frame, offset, 4);
    }

    public static float parseFLOAT4DF0(byte[] frame, int offset) {
        int[] bVal = new int[4];

        for (int i = 0; i < 4; i++) {
            bVal[i] = (frame[(offset + i)] < 0 ? frame[(offset + i)] + 256 : frame[(offset + i)]);
        }

        int iVal = bVal[3] | bVal[2] << 8 | bVal[1] << 16 | bVal[0] << 24;

        return Float.intBitsToFloat(iVal);
    }

    public static float parseFLOAT4DF3(byte[] frame, int offset) {
        return (float) (parseFLOAT4DF0(frame, offset) / 1000.0D);
    }

    public static double parseBINCOMPLE3DF4(byte[] frame, int offset) {
        double val = 0.0D;

        long temp = ProtocolUtil.toLong(frame, offset, 3);
        if (temp < 8388608L) {
            val = temp / 10000.0D;
        } else {
            val = -((16777216L - temp) / 10000.0D);
        }

        return val;
    }

    public static String bit2String(byte bit) {
        String s = "";
        int t = 128;

        for (int i = 0; i < 8; i++) {
            int x = t >> i;
            if ((bit & x) == x) {
                s = s + "1";
            } else {
                s = s + "0";
            }
        }
        return s;
    }

    public static String bit2NString(byte bit) {
        String s = "";
        int t = 1;

        for (int i = 0; i < 8; i++) {
            int x = t << i;
            if ((bit & x) == x) {
                if (s.length() > 0) {
                    s = s + ",";
                }
                s = s + (i + 1);
            }

        }

        return s;
    }

    public static String bit2NString(byte[] bit) {
        String s = "";
        int t = 1;

        for (int j = 0; j < bit.length; j++) {
            for (int i = 0; i < 8; i++) {
                int x = t << i;
                if ((bit[j] & x) == x) {
                    if (s.length() > 0) {
                        s = s + ",";
                    }
                    s = s + (j * 8 + i + 1);
                }
            }
        }

        return s;
    }

    public static String bit2NString(byte[] bit, int length) {
        return bit2NString(bit, 0, length);
    }

    public static String bit2NString(byte[] bit, int pos, int length) {
        String s = "";
        int t = 1;

        for (int j = 0; j < length; j++) {
            for (int i = 0; i < 8; i++) {
                int x = t << i;
                if ((bit[(pos + j)] & x) == x) {
                    if (s.length() > 0) {
                        s = s + ",";
                    }
                    s = s + (j * 8 + i + 1);
                }
            }
        }

        return s;
    }

    public static int getBit1(byte bit) {
        int j = 0;
        for (int i = 0; i < 8; i++) {
            if ((bit >> i & 0x1) == 1) {
                j++;
            }
        }
        return j;
    }

    public static String timestamp2String(Date ts) {
        String t = "";
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(ts.getTime());
        t = c.get(1) + "年" + (c.get(2) + 1) + "月" + c.get(5) + "日" + c.get(11) + "时" + c.get(12) + "分";
        return t;
    }

    public static String timestamp2SString(Date ts) {
        String t = "";
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(ts.getTime());
        t = c.get(5) + "日" + c.get(11) + "时" + c.get(12) + "分";
        return t;
    }

    public static Date String2TimeStamp(String tsStr) {
        return Timestamp.valueOf(tsStr);
    }

    public static Date parseFepRtuStatusTime(byte[] frame, int offset) {
        if ((frame[(offset + 1)] == 0) && (frame[(offset + 2)] == 0)) {
            return null;
        }

        int year = 2000 + toUnSignByte(frame[(offset++)]);
        int month = toUnSignByte(frame[(offset++)]);
        int day = toUnSignByte(frame[(offset++)]);
        int hour = toUnSignByte(frame[(offset++)]);
        int min = toUnSignByte(frame[(offset++)]);

        Date ts = new Timestamp(new GregorianCalendar(year, month - 1, day, hour, min).getTimeInMillis());

        return ts;
    }
}
