package com.example.protocol.utils;

//import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProtocolUtil {

	/**
	 * 字符串转数组
	 *
	 * @param data
	 * @return
	 */
	public static byte[] toBytes(String data) {
		if (data == null || "".equals(data))
			return null;
		data = data.toUpperCase();
		int length = data.length() / 2;
		char[] dataChars = data.toCharArray();
		byte[] byteData = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			byteData[i] = (byte) (charToByte(dataChars[pos]) << 4 | charToByte(dataChars[pos + 1]));
		}
		return byteData;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * bcd字符串转数组,低位在前,高位在后
	 *
	 * @param value
	 * @return
	 */
	public static byte[] toBcdBytes(String value) {
		byte[] bytArry = new byte[getByteArryLength(value)];
		int posOfBytArry = 0;

		int endPos = value.length();
		for (int i = 0; i < value.length(); i += 2) {
			int startPos;
			if (endPos - 2 < 0) {
				startPos = 0;
			} else {
				startPos = endPos - 2;
			}
			String valOfOneByte = value.substring(startPos, endPos);
			bytArry[(posOfBytArry++)] = to1ByteBcdWithHexContent(valOfOneByte);

			endPos -= 2;
		}
		return bytArry;
	}

	private static int getByteArryLength(String val) {
		int len = val.length();
		if (len % 2 == 0) {
			return val.length() / 2;
		}
		return val.length() / 2 + 1;
	}

	private static byte to1ByteBcdWithHexContent(String value) {
		String highNum = "0";
		String lowNum;
		if (value.length() == 1) {
			lowNum = value;
		} else {
			lowNum = value.substring(1, 2);
			highNum = value.substring(0, 1);
		}

		return (byte) ((Integer.parseInt(highNum, 16) << 4) + Integer.parseInt(lowNum, 16));
	}

	/**
	 * 时间戳转5位bcd数组 (18-09-01 17:33 3317010918)
	 *
	 * @param time
	 * @return
	 */
	public static byte[] timestampTo5Byte(Date time) {
		Calendar c = TimeUtil.timestampToCalendar(time);
		byte[] data = new byte[5];
		data[0] = to1BcdByte(c.get(Calendar.MINUTE));
		data[1] = to1BcdByte(c.get(Calendar.HOUR_OF_DAY));
		data[2] = to1BcdByte(c.get(Calendar.DAY_OF_MONTH));
		data[3] = to1BcdByte(c.get(Calendar.MONTH) + 1);
		data[4] = to1BcdByte(c.get(Calendar.YEAR));
		return data;
	}

	/**
	 * 时间戳转6位bcd数组 (18-09-01 17:33:57 573317010918)
	 *
	 * @param time
	 * @return
	 */
	public static byte[] timestampTo6Byte(Date time) {
		Calendar c = TimeUtil.timestampToCalendar(time);
		byte[] data = new byte[6];
		data[0] = to1BcdByte(c.get(Calendar.SECOND));
		data[1] = to1BcdByte(c.get(Calendar.MINUTE));
		data[2] = to1BcdByte(c.get(Calendar.HOUR_OF_DAY));
		data[3] = to1BcdByte(c.get(Calendar.DAY_OF_MONTH));
		data[4] = to1BcdByte(c.get(Calendar.MONTH) + 1);
		data[5] = to1BcdByte(c.get(Calendar.YEAR));
		return data;
	}

	/**
	 * int转bcd数组 (value:2 len:2 02 00)
	 *
	 * @param value
	 * @param len
	 * @return
	 */
	public static byte[] toBcdBytes(int value, int len) {
		byte[] rt = new byte[len];

		for (int i = 0; i < len; i++) {
			rt[i] = to1BcdByte((int) (value % 100L));
			value /= 100L;
		}
		return rt;
	}

	/**
	 * int转bcd
	 *
	 * @param value
	 * @return
	 */
	public static byte to1BcdByte(int value) {
		value %= 100;
		return (byte) (value / 10 << 4 | value % 10);
	}

	public static String addZeroForNum(String v, int maxLen) {
		int len = v.length();
		if (len < maxLen) {
			while (len < maxLen) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(v);// 左补0
				v = sb.toString();
				len = v.length();
			}
		}
		return v;
	}

	public static String fromBcdWithHexContent(byte bcdCode) {
		int hi = (byte) (bcdCode >> 4 & 0xF);
		int low = (byte) (bcdCode & 0xF);
		return Integer.toHexString(hi) + Integer.toHexString(low);
	}

	/**
	 * 字节转字符串
	 *
	 * @param bcdCode
	 * @return
	 */
	public static String byteToHexStr(byte bcdCode) {
		int hi = (byte) (bcdCode >> 4 & 0xF);
		int low = (byte) (bcdCode & 0xF);
		return Integer.toHexString(hi) + Integer.toHexString(low);
	}

	/**
	 * 字节高位数
	 *
	 * @param bcdCode
	 * @return
	 */
	public static int fromHi4BitsBcd(byte bcdCode) {
		if ((bcdCode >> 4 & 0xF) > 9) {
			return -1;
		}
		return bcdCode >> 4 & 0xF;
	}

	/**
	 * 字节低位数
	 *
	 * @param bcdCode
	 * @return
	 */
	public static int fromLo4BitsBcd(byte bcdCode) {
		if ((bcdCode & 0xF) > 9) {
			return -1;
		}
		return bcdCode & 0xF;
	}
	
	public static Timestamp getCallDataTime(int frequencyUnit, int frequencyValue) {
		Calendar c = Calendar.getInstance();
		if (frequencyUnit == 1) {
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - frequencyValue);
			c.set(Calendar.MINUTE, 0);
		} else if (frequencyUnit == 2) {
			c.set(Calendar.DATE, c.get(Calendar.DATE) - frequencyValue);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
		} else if (frequencyUnit == 3) {
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) - frequencyValue);
			c.set(Calendar.DATE, c.get(Calendar.DATE) - frequencyValue);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
		}

		return new Timestamp(c.getTimeInMillis());
	}

	public static long fromSignedBcd(byte[] b, int offset, int length) {
		long s = 0L;
		try {
			for (int i = 0; i < length - 1; i++) {
				int bcd = NumberUtil.fromBcd(b[(offset + i)]);
				if ((bcd == -2) || (bcd == -1)) {
					s = 0L;
					break;
				}
				s += bcd * Math.pow(10.0D, i * 2);
			}

			s += NumberUtil.from4BitsBcd(b[(length - 1)]) * Math.pow(10.0D, (length - 1) * 2);

			if (getSign(b[(length - 1)]) == 0) {
				return s;
			}
			return -s;
		} catch (Exception e) {
//			log.error("toLongBCD", e);
		}
		return 0L;
	}

	public static long fromUnsignedBcd(byte[] b, int offset, int length) {
		long s = 0L;
		try {
			for (int i = 0; i < length; i++) {
				int value = NumberUtil.fromBcd(b[(offset + i)]);
				if ((value == -2) || (value == -1)) {
					s = -1L;
					break;
				}
				s += value * Math.pow(10.0D, i * 2);
			}

			return s;
		} catch (Exception e) {
//			log.error("toLongBCD", e);
		}
		return -1L;
	}

	public static long fromUnsignedBcd4BE(byte[] b, int offset, int length) {
		long s = 0L;
		try {
			for (int i = 0; i < length; i++) {
				int value = NumberUtil.fromBcd(b[(offset + i)]);
				if ((value == -2) || (value == -1)) {
					s = -1L;
					break;
				}
				s += value * Math.pow(10.0D, (length - i - 1) * 2);
			}

			return s;
		} catch (Exception e) {
//			log.error("toLongBCD", e);
		}
		return -1L;
	}

	public static short getSign(byte value) {
		if ((byte) (value >> 4 & 0xF) == 0) {
			return 1;
		}
		return -1;
	}

	public static byte to1ByteBcd(int value) {
		value %= 100;
		return (byte) (value / 10 << 4 | value % 10);
	}

	public static byte[] to2BytesBcd(int value) {
		return toMultiBytesBcd(value, 2);
	}

	public static byte[] to2BytesBcdFromFloat(float value, int multiple) {
		return to2BytesBcd((int) value * multiple);
	}

	public static byte[] to3BytesBcd(int value) {
		return toMultiBytesBcd(value, 3);
	}

	public static byte[] to4BytesBcd(int value) {
		return toMultiBytesBcd(value, 4);
	}

	public static byte[] to6BytesBcd(long value) {
		return toMultiBytesBcd(value, 6);
	}

	public static long toLong(byte[] b, int offset, int length) {
		long s = 0L;
		try {
			for (int i = 0; i < length; i++) {
				s |= (b[(offset + i)] < 0 ? b[(offset + i)] + 256 : b[(offset + i)]) << i * 8;
			}
			return s;
		} catch (Exception e) {
//			log.error("toLong", e);
		}
		return 0L;
	}

	public static int toInt(byte[] b, int offset, int len) {
		try {
			return ParserUtil.toUnSignByte(b[(offset + 1)]) * 256 + ParserUtil.toUnSignByte(b[offset]);
		} catch (Exception e) {
//			log.error("toInt", e);
		}
		return 0;
	}

	public static int toIntFrom2Byts(byte[] b, int offset) {
		return toInt(b, offset, 2);
	}

	public static byte[] toMultiBytesBcd(long value, int bcdBytes) {
		byte[] rt = new byte[bcdBytes];

		for (int i = 0; i < bcdBytes; i++) {
			rt[i] = to1ByteBcd((int) (value % 100L));
			value /= 100L;
		}
		return rt;
	}

	public static byte[] toMultiBytes(long value, int bytes) {
		byte[] rt = new byte[bytes];

		for (int i = 0; i < bytes; i++) {
			rt[i] = ((byte) (int) (value >> i * 8 & 0xFF));
		}
		return rt;
	}

	public static byte[] to2Bytes(int val) {
		if (val > 65535) {
			try {
				throw new Exception("数值的范围应在0-0xffff，目前是" + val);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return toMultiBytes(val, 2);
	}

	public static int pnfnToDID(byte[] da, byte[] dt) {
		int rt = 0;

		if ((da.length >= 2) && (dt.length >= 2)) {
			rt = (((dt[1] & 0xFF) << 8 ^ dt[0] & 0xFF) << 8 ^ da[1] & 0xFF) << 8 ^ da[0] & 0xFF;
		}
		return rt;
	}

	public static int toUnSignByte(byte b) {
		return b < 0 ? b + 256 : b;
	}

	public static int toUnsignedInt(byte a, byte b) {
		return (a < 0 ? a + 256 : a) + (b < 0 ? b + 256 : b) * 256;
	}

	public static int toUnsignedInt(byte[] data, int offset) {
		return (data[offset] < 0 ? data[offset] + 256 : data[offset])
				+ (data[(offset + 1)] < 0 ? data[(offset + 1)] + 256 : data[(offset + 1)]) * 256;
	}

	public static long BCDetoInt(byte b) {
		long s = 0L;
		s += (b & 0xF);
		s += (b >> 4 & 0xF) * 10;
		return s;
	}

	public static long BCDetoLong(byte[] b, int start, int lenth) {
		long s = 0L;
		int fnum = 0;
		for (int i = 0; i < lenth; i++) {
			s += BCDetoInt(b[(i + start)]) * Math.pow(10.0D, i * 2);
			if (b[(start + i)] == -1) {
				fnum++;
			}
		}
		if (fnum == lenth) {
			return 0L;
		}
		return s;
	}

	public static long BCDetoLongSign(byte[] b, int start, int lenth) {
		long s = 0L;
		int fnum = 0;
		int sign = 1;
		for (int i = 0; i < lenth; i++) {
			if (i == lenth - 1) {
				if ((b[(i + start)] >> 7 & 0x1) == 1) {
					sign = -1;
				}
				s += BCDetoInt((byte) (b[(i + start)] & 0x7F)) * Math.pow(10.0D, i * 2);
			} else {
				s += BCDetoInt(b[(i + start)]) * Math.pow(10.0D, i * 2);
			}
			if (b[(start + i)] == -1) {
				fnum++;
			}
		}
		if (fnum == lenth) {
			return 0L;
		}
		return s * sign;
	}

	public static String bcd2Str(byte[] b, int start, int lenth) {
		byte[] bytes = new byte[lenth];
		System.arraycopy(b, start, bytes, 0, lenth);
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = bytes.length - 1; i >= 0; i--) {
			temp.append((byte) ((bytes[i] & 0xF0) >>> 4));
			temp.append((byte) (bytes[i] & 0xF));
		}
		return temp.toString();
	}

	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte[] abt = new byte[len];
		if (len >= 2) {
			len /= 2;
		}
		byte[] bbt = new byte[len];
		abt = asc.getBytes();

		for (int p = 0; p < asc.length() / 2; p++) {
			int j;
			if ((abt[(2 * p)] >= 48) && (abt[(2 * p)] <= 57)) {
				j = abt[(2 * p)] - 48;
			} else {
				if ((abt[(2 * p)] >= 97) && (abt[(2 * p)] <= 122)) {
					j = abt[(2 * p)] - 97 + 10;
				} else {
					j = abt[(2 * p)] - 65 + 10;
				}
			}
			int k;
			if ((abt[(2 * p + 1)] >= 48) && (abt[(2 * p + 1)] <= 57)) {
				k = abt[(2 * p + 1)] - 48;
			} else {
				if ((abt[(2 * p + 1)] >= 97) && (abt[(2 * p + 1)] <= 122)) {
					k = abt[(2 * p + 1)] - 97 + 10;
				} else {
					k = abt[(2 * p + 1)] - 65 + 10;
				}
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	public static byte[] toBuyEData(int zValue, long value) {
		byte[] data = new byte[4];
		int g = 0;
		int s = value > 0L ? 0 : 1;
		value = Math.abs(value);

		if (value > 9999999L) {
			g = 1;
			value = (int) (value / 1000L);
		}
		System.arraycopy(toMultiBytesBcd(value, 3), 0, data, 0, 3);
		data[3] = toMultiBytesBcd(value / 1000000L, 1)[0];
		if (s == 1) {
			data[3] = setBit(data[3], 4);
		}
		if (g == 1) {
			data[3] = setBit(data[3], 6);
		}
		if (zValue == 1) {
			data[3] = setBit(data[3], 7);
		}

		return data;
	}

	public static Calendar timestampToCalendar(Date t) {
		Calendar c = new GregorianCalendar();
		c.setTime(t);
		return c;
	}

	public static byte[] buildDayTime(Date t) {
		Calendar c = timestampToCalendar(t);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int weekday = c.get(Calendar.DAY_OF_WEEK) - 1;
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR) - 2000;

		byte[] frame = new byte[3];
		frame[0] = to1ByteBcd(day);
		frame[1] = ((byte) (weekday > 0 ? weekday : 7));
		frame[1] = ((byte) (frame[1] << 5));
		byte b = to1ByteBcd(month);
		frame[1] = ((byte) (frame[1] | b));
		frame[2] = to1ByteBcd(year);

		return frame;
	}

	public static byte[] buildMonthTime(Date t) {
		Calendar c = timestampToCalendar(t);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR) - 2000;

		byte[] frame = new byte[2];
		frame[0] = to1ByteBcd(month);
		frame[1] = to1ByteBcd(year);

		return frame;
	}

	public static Timestamp beforeOneDay(Date t) {
		Calendar c = timestampToCalendar(t);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return new Timestamp(c.getTimeInMillis());
	}

	public static byte buildBS(int[] bs) {
		return buildBS(bs, 1)[0];
	}

	public static byte[] buildBS(int[] bs, int bytes) {
		byte[] frame = new byte[bytes];
		for (int i = 0; i < bs.length; i++) {
			if (bs[i] > 8 * bytes - 1) {
				throw new IllegalArgumentException("expect [0," + (8 * bytes - 1) + "], but bs[" + i + "] is " + bs[i]);
			}
			int count = bs[i] / 8;
			int pos = bs[i] % 8;
			frame[count] = setBit(frame[count], pos);
		}
		return frame;
	}

	public static byte setBit(byte value, int bitPos) {
		return (byte) (value | 1 << bitPos);
	}

	public static long toLongF(byte[] b, int offset, int length) {
		long s = 0L;
		try {
			for (int i = length - 1; i >= 0; i--) {
				s |= (b[(offset + i)] < 0 ? b[(offset + i)] + 256 : b[(offset + i)]) << (length - i - 1) * 8;
			}
			return s;
		} catch (Exception e) {
//			log.error("toLongF", e);
		}
		return 0L;
	}

	public static int getCRCCheck(byte[] data, int offset, int v_len, int v_psw) {
		int crcCode = 0;
		for (int i = 0; i < v_len; i++) {
			crcCode ^= ParserUtil.toUnSignByte(data[(offset + i)]);
			for (int j = 0; j < 8; j++) {
				int w_reg = crcCode & 0x1;
				crcCode >>= 1;
				if (w_reg != 0) {
					crcCode ^= v_psw;
				}
			}
		}
		return crcCode;
	}

	public static byte cs(byte[] buff, int start, int end) {
		int cs = 0;
		for (int i = start; i < end; i++) {
			cs += buff[i];
		}
		return (byte) cs;
	}

	public static byte[] fromTimestampTo6ByteArray(Date time) {
		Calendar c = TimeUtil.timestampToCalendar(time);
		byte[] data = new byte[6];
		System.arraycopy(to2BytesBcd(c.get(Calendar.YEAR)), 0, data, 0, 2);
		byte b = data[0];
		data[0] = data[1];
		data[1] = b;
		data[2] = to1ByteBcd(c.get(Calendar.MONTH) + 1);
		data[3] = to1ByteBcd(c.get(Calendar.DAY_OF_MONTH));
		data[4] = to1ByteBcd(c.get(Calendar.HOUR_OF_DAY));
		data[5] = to1ByteBcd(c.get(Calendar.MINUTE));
		return data;
	}

	public static byte[] fromTimestampTo5ByteArray(Date time) {
		Calendar c = TimeUtil.timestampToCalendar(time);
		byte[] data = new byte[5];
		data[0] = to1ByteBcd(c.get(Calendar.MINUTE));
		data[1] = to1ByteBcd(c.get(Calendar.HOUR_OF_DAY));
		data[2] = to1ByteBcd(c.get(Calendar.DAY_OF_MONTH));
		data[3] = to1ByteBcd(c.get(Calendar.MONTH) + 1);
		data[4] = to1ByteBcd(c.get(Calendar.YEAR));
		return data;
	}

	public static byte[] formStringToByteArray(String ip) {
		byte[] data = new byte[4];
		String[] str = ip.split("\\.");
		for (int i = 0; i < 4; i++) {
			data[i] = ((byte) Integer.parseInt(str[i]));
		}
		return data;
	}

	public static Timestamp processToWholeMinute(Date t) {
		Calendar c = timestampToCalendar(t);
		int mi = c.get(Calendar.MINUTE) / 5 * 5;
		c.set(Calendar.MINUTE, mi);

		return new Timestamp(c.getTimeInMillis());
	}
}