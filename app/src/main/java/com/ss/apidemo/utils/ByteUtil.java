package com.ss.apidemo.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 进制工具类
 * 
 * @Class Name ByteUtil
 * @author zhangfeng
 * @Create In 2018年6月12日
 */
public class ByteUtil {

	public static final byte BIT_OF_BYTE = 8;

	// 流水号，从0到255
	public static int SerialNumber = 0;
	// 二进制数组
	private final static String[] binaryArray = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
			"1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111" };
	// 16进制数组
	private final static Map<String, String> hexArray = new HashMap<String, String>();
	static {
		for (int i = 0; i < binaryArray.length; i++) {
			hexArray.put(binaryArray[i], Integer.toHexString(i));
		}
	}

	/**
	 * byte数组转十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteArrayToHexString(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toLowerCase();
	}

	/**
	 * byte数组转数字
	 * 
	 * @param a
	 * @return
	 */
	/*public static int byteArrayToInt(byte a[]) {

		int result = 0;
		int j = a.length;

		for (int i = 0; i != a.length; ++i)
			result += (a[i] & 0xFF) << (8 * --j);

		return result;
	}*/

	/*public static int byte2int(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或
				| ((res[2] << 24) >>> 8) | (res[3] << 24);
		return targets;
	}*/

	/**
	 * byte转2进制数字
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToBinaryString(byte b) {
		return Integer.toBinaryString(b & 0xff);
	}

	/**
	 * byte转数字
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToIntString(byte b) {
		String hs = String.valueOf(b);
		if (hs.length() == 1) {
			hs = "0" + hs;
		}
		return hs;
	}

	/**
	 * 将字节转为无符号数字
	 * 
	 * @param b
	 * @return 0~255
	 */
	public static int byteToUnsignedInt(byte b) {
		return b & 0xff;
	}

	/**
	 * 十六进制字符串转换成无符号数字
	 * 
	 * @param src
	 *            2位十六进制数字
	 * @return 介于 0~255 的数字
	 */
	public static int hexStringToUnsignedInt(String src) {
		if (p_Hex.matcher(src).find() || src.length() % 2 == 0) {
			return Integer.parseInt(src, 16);
		}
		return -1;
	}

	public static byte[] cntouni(String msg) {
		int len = msg.length();
		byte[] s = new byte[len];
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char c = msg.charAt(i);
			s[i] = (byte) c;
			ret.append(s[i]);
		}
		return s;
	}

	/**
	 * 截取name1到name2之间的字符串
	 * 
	 * @param allxx
	 * @param name1
	 * @param name2
	 * @return
	 */
	public static String getxx(String allxx, String name1, String name2) {
		String infomation = "";
		int begin = 0, end = 0;
		try {
			begin = allxx.indexOf(name1);
			end = allxx.indexOf(name2);
			if (begin != -1 && end != -1) {
				infomation = allxx.substring((begin + name1.length()), end).trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infomation;
	}

	/**
	 * 十六进制字符串转换成byte数组
	 */
	public static byte[] hexStringToBytes(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
		}
		return ret;
	}

	/**
	 * 十六进制字符串转换成普通字符串
	 */
	public static String hexStringToString(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
		}
		return new String(ret);
	}

	/**
	 * 十六进制字符串转换成二进制
	 */
	public static String hexStringToBinary(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			byte b = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
			int u = byteToUnsignedInt(b);

			sb.append(new String(new char[] { DIGITS[(u >>> 7) & 0x1], DIGITS[(u >>> 6) & 0x1], DIGITS[(u >>> 5) & 0x1],
					DIGITS[(u >>> 4) & 0x1], DIGITS[(u >>> 3) & 0x1], DIGITS[(u >>> 2) & 0x1], DIGITS[(u >>> 1) & 0x1],
					DIGITS[u & 0x1] }));
			// sb.append(byteToBinaryString(b));
			// System.out.println(byteToBinaryString(b));
		}
		return sb.toString();
	}

	/**
	 * 十六进制字符串转换成无符号数字
	 * 
	 * @param src
	 *            2位十六进制数字
	 * @return 介于 0~255 的数字
	 */
	public static int hexStringToInt1(String src) {
		if (p_Hex.matcher(src).find() || src.length() != 2) {
			return -1;
		}
		return Integer.parseInt(src, 16);
	}

	/**
	 * 十六进制字符串转换成无符号数字
	 * 
	 * @param src
	 *            4位十六进制数字
	 * @return 介于 0~255 的数字
	 */
	public static int hexStringToInt2(String src) {
		if (p_Hex.matcher(src).find() || src.length() != 4) {
			return -1;
		}
		return Integer.parseInt(src, 16);
	}

	/**
	 * 十六进制字符串转换成无符号数字
	 *
	 * @param src
	 *            6位十六进制数字
	 * @return 介于 0~255 的数字
	 */
	public static int hexStringToInt3(String src) {
		if (p_Hex.matcher(src).find() || src.length() != 6) {
			return -1;
		}
		return Integer.parseInt(src, 16);
	}

	/**
	 * 十六进制字符串转换成无符号数字
	 * 
	 * @param src
	 *            8位十六进制数字
	 */
	public static int hexStringToInt4(String src) {
		if (p_Hex.matcher(src).find() || src.length() != 8) {
			return -1;
		}
		return Integer.parseInt(src, 16);
	}

	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };
	private final static Pattern p_Hex = Pattern.compile("[^0-9A-Fa-f]+");

	/**
	 * 将int转为长度为2的byte数据组，只取2个低字节，安照Big-Endian方式
	 *
	 * @param i
	 *            取值范围：0~255
	 * @return
	 */
	public static byte intToByte(int i) {
		return (byte) i;
	}
	
	/**
	 * 将int转为长度为len的byte数据组，并安照Little-Endian做字节反转
	 * @param bin 整数
	 * @param len 长度，1~4
	 * @return
	 */
	public static byte[] intToByteArrayLE(int bin, int len) {
		byte[] result = new byte[len];
		for (int j = 0; j < len; j++) {
		    if (j < 4) {
			    result[j] = (byte) ((bin >>> j * 8) & 0xFF);
            } else {
		        result[j] = 0;
            }
		}
		return result;
	}
	
	/**
	 * 将long转为长度为len的byte数据组，并安照Little-Endian做字节反转
	 * @param bin 整数
	 * @param len 长度，1~8
	 * @return
	 */
	public static byte[] longToByteArrayLE(int bin, int len) {
		byte[] result = new byte[len];
		for (int j = 0; j < len; j++) {
			if (j < 8) {
				result[j] = (byte) ((bin >>> j * 8) & 0xFF);
			} else {
				result[j] = 0;
			}
		}
		return result;
	}
	
	
	public static byte[] intToByteArray2(int i) {
		byte[] result = new byte[2];
		result[0] = (byte) ((i >> 8) & 0xFF);
		result[1] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * 将int转为长度为2的byte数据组，并安照Little-Endian做字节反转
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArray2LE(int i) {
		byte[] result = new byte[2];
		result[0] = (byte) (i & 0xFF);
		result[1] = (byte) ((i >> 8) & 0xFF);
		return result;
	}

	/**
	 * 将int转为长度为4的byte数据组，安照Big-Endian方式
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArray4(int i) {
		byte[] result = new byte[4];
		result[0] = (byte) ((i >>> 24) & 0xFF);
		result[1] = (byte) ((i >>> 16) & 0xFF);
		result[2] = (byte) ((i >>> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * 将int转为长度为4的byte数据组，安照Little-Endian做字节反转
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArray4LE(int i) {
		byte[] result = new byte[4];
		result[0] = (byte) (i & 0xFF);
		result[1] = (byte) ((i >>> 1 * 8) & 0xFF);
		result[2] = (byte) ((i >>> 2 * 8) & 0xFF);
		result[3] = (byte) ((i >>> 3 * 8) & 0xFF);
		return result;
	}

	public static String intToByteString(int b) {
		String hs = String.valueOf(b);
		if (hs.length() == 1) {
			hs = "0" + hs;
		}
		return hs;
	}

	/**
	 * 将0~255数字，转化为16进制字符串。占一个字节
	 * 
	 * @param b
	 * @return
	 */
	public static String int1ToHexString(int b) {
		String hs = Integer.toHexString(b);
		if (hs.length() == 1) {
			hs = "0" + hs;
		} else if (hs.length() >= 3) {
			// throw new DataOutOfBoundsException("");
		}
		return hs;
	}

	/**
	 * 将0~65535数字，转化为16进制字符串。占二个字节
	 * 
	 * @param b
	 * @return
	 */
	public static String int2ToHexString(int b) {
		String hs = Integer.toHexString(b);
		for (int i = 4 - hs.length(); i > 0; i--) {
			hs = "0" + hs;
		}
		return hs;
	}

	/**
	 * 将数字，转化为16进制字符串。占四个字节
	 * 
	 * @param b
	 * @return
	 */
	public static String int4ToHexString(int b) {
		String hs = Integer.toHexString(b);
		for (int i = 8 - hs.length(); i > 0; i--) {
			hs = "0" + hs;
		}
		return hs;
	}

	/**
	 * 是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(Object s) {
		if (s == null || s.toString().length() == 0 || "null".equals(s.toString().trim().toLowerCase())) {
			return true;
		} else {
			
			
			
			return false;
		}
	}

	/**
	 * 反转字符串 123456 -> 563412
	 * 
	 * @param str
	 * @return
	 */
	public static String reverseString(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < str.length(); i = i + 2) {
				sb.append(str.substring(str.length() - i - 2, str.length() - i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private static byte uniteBytes(String src0, String src1) {
		byte b0 = Byte.decode("0x" + src0).byteValue();
		b0 = (byte) (b0 << 4);
		byte b1 = Byte.decode("0x" + src1).byteValue();
		byte ret = (byte) (b0 | b1);
		return ret;
	}

	/**
	 * 按照4为byte输入转换为int，Big-Endian方式，不足位数后面补0
	 * @param b
	 * @return
	 */
	public static int byte4ToInt(byte[] b) {
		int mask = 0xff;
		int temp = 0;
		int n = 0;
		int length = b.length;
		for (int i = 0; i < 4; i++) {
			n <<= 8;
			if (i <= length) {
				temp = b[i] & mask;
			} else {
				temp = 0x00;
			}
			n |= temp;
		}
		return n;
	}

	/**
	 * 按照4位byte输入转换为int，Little-Endian方式，不足位数前面补0
	 * @param b
	 * @return
	 */
	public static int byte4ToIntLE(byte[] b) {
		int mask = 0xff;
		int temp = 0;
		int n = 0;
		int length = b.length;
		for (int i = 0; i < 4 ; i++) {
			if (i < length) {
				temp = b[i] & mask;
			} else {
				temp = 0x00;
			}
			n |= (temp << (8 * i));
		}
		return n;
	}

	/**
	 * 字节数组，按照BCD码，转换为10进制，Little-Endian
	 * 最多去长度9的字节数组
	 * @param b
				* @return
	 */
	public static long byteToBcdLE(byte[] b) {
		long bcd = 0;
		int factor = 10;
		long tens = 1;
		int len = b.length > 10? 10:b.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j< 2; j++) {
				tens = Math.round(Math.pow(factor, i * 2+ j));
				int single = (b[i] >> (j * 4) & 0xf);
				if (single > 9) {
					// 每一位不能大于9
					single = 9;
				}
				bcd += (single * tens);
			}
		}
		return bcd;
	}

	/**
	 * 字节数组，按照BCD码，转换为10进制，Little-Endian
	 * 最多去长度9的字节数组
	 * @param b
	 * @return
	 */
	public static int byteToBcdLE(byte b) {
		int single = b & 0xf;
		int tens = (b >>> 4) & 0xf;
		return (tens * 10) + single;
	}

	/**
	 * 十进制数，按照BCD码转换为二进制
	 * @param bcd
	 * @return
	 */
	public static byte[] bcdToByteLE(int bcd) {
		int bitCount = Integer.toString(bcd).length();
		double ceil = Math.ceil(bitCount / 2.0);
		int len = (int) ceil;
		byte[] bytes = new byte[len];
		int leave = bcd;
		for (int i = 0; i < len; i++) {
			int digit = leave % 100;
			int single = digit % 10;
			int tens = digit / 10;
			byte a = (byte) (tens << 4 | single);
			bytes[i] = a;
			leave /= 100;
		}
		return bytes;
	}

	/**
	 * 十进制数，按照BCD码转换为二进制数组，不足位数填充0
	 * @param bcd
	 * @return
	 */
	public static void bcdToByteLE(long bcd, byte[] bytes) {
		int bitCount = Long.toString(bcd).length();
		double ceil = Math.ceil(bitCount / 2.0);
		int len = (int) ceil;
		int length = bytes.length;
		long leave = bcd;
		for (int i = 0; i < length; i++) {
			if (i < len) {
				long digit = leave % 100;
				long single = digit % 10;
				long tens = digit / 10;
				byte a = (byte) (tens << 4 | single);
				bytes[i] = a;
				leave /= 100;
			} else {
				bytes[i] = 0;
			}
		}
	}

	/**
	 * 十进制数，按照BCD码转换为二进制
	 * @param bcd
	 * @return
	 */
	public static byte[] bcdToByte2LE(int bcd) {
		byte[] bytes = bcdToByteLE(bcd);
		if (bytes == null || bytes.length < 1) {
			return new byte[2];
		}
		if (bytes.length == 1) {
			return new byte[]{bytes[0], 0};
		}
		if (bytes.length > 2) {
			return subBytes(bytes, 0, 2);
		}
		return bytes;
	}

	public static byte[] calculateCheckCode(byte[] str) {
		byte[] checkCode = new byte[4];

		for (int index = 0, i = 0; index != str.length; ++index) {
			int shift = index % 8;

			if (shift == 0)
				checkCode[i] = 0x00;

			checkCode[i] |= (byte) ((str[index] & 0x80) >> shift);

			if (shift == 7)
				i++;
		}
		return checkCode;
	}

	public static byte[] long2bytes(long num) {
		byte[] b = new byte[8];
		for (int i = 0; i < 8; i++) {
			b[i] = (byte) (num >>> (56 - (i * 8)));
		}
		return b;
	}

	/**
	 * 将字符串前面补0,凑够足够的位数
	 * 
	 * @param source
	 *            原始字符串
	 * @param length
	 *            期待字符串的长度
	 * @return
	 */
	public static String doAddString(String source, int length) {
		if (source.length() >= length)
			return source;
		StringBuffer sb = new StringBuffer();
		for (int i = length - source.length(); i > 0; i--) {
			sb.append("0");
		}
		sb.append(source);
		return sb.toString();
	}

	/**
	 * 生成流水号
	 * 
	 * @return
	 */
	public static String generateRequestId() {
		StringBuffer sb = new StringBuffer();
		Calendar c = Calendar.getInstance();

		// 年 12位
		sb.append(toBinaryString(c.get(Calendar.YEAR) - 2000, 6));
		// 月 4位
		sb.append(toBinaryString(c.get(Calendar.MONTH) + 1, 4));
		// 日 5位
		sb.append(toBinaryString(c.get(Calendar.DAY_OF_MONTH), 5));
		// 时 5位
		sb.append(toBinaryString(c.get(Calendar.HOUR_OF_DAY), 5));
		// 分 6位
		sb.append(toBinaryString(c.get(Calendar.MINUTE), 6));
		// 秒 6位
		sb.append(toBinaryString(c.get(Calendar.SECOND), 6));
		// 流水号 6位
		sb.append(toBinaryString(generateSerialNumber(), 8));
		return BinaryToHexString(sb.toString());
	}

	// 获取序列号
	public synchronized static int generateSerialNumber() {
		if (SerialNumber >= 256) {
			SerialNumber = 0;
		}
		return SerialNumber++;
	}

	// 获取当前时间
	public static String generateCurrentTime() {
		StringBuffer sb = new StringBuffer();
		Calendar c = Calendar.getInstance();

		// 年 6位
		sb.append(toBinaryString(c.get(Calendar.YEAR) - 200, 6));
		// 月 4位
		sb.append(toBinaryString(c.get(Calendar.MONTH) + 1, 4));
		// 日 5位
		sb.append(toBinaryString(c.get(Calendar.DAY_OF_MONTH), 5));
		// 时 5位
		sb.append(toBinaryString(c.get(Calendar.HOUR_OF_DAY), 5));
		// 分 6位
		sb.append(toBinaryString(c.get(Calendar.MINUTE), 6));
		// 秒 6位
		sb.append(toBinaryString(c.get(Calendar.SECOND), 6));
		return BinaryToHexString(sb.toString());
	}

	/**
	 * 将字符串前面补0,凑够足够的位数
	 * 
	 * @param source
	 *            原始字符串
	 * @param length
	 *            期待字符串的长度
	 * @return
	 */
	public static String toBinaryString(int source, int length) {
		String sourceA = Integer.toBinaryString(source);
		return doAddString(sourceA, length);
	}

	/**
	 *
	 * @param binary
	 * @return 将二进制字符串转换为十六进制字符输出
	 */
	public static String BinaryToHexString(String binary) {

		StringBuffer sb = new StringBuffer();
		if (binary.length() % 8 == 0) {
			int b = binary.length() / 8;
			for (int i = 0; i < b; i++) {
				int c = i * 8;
				// 字节高4位
				sb.append(hexArray.get(binary.substring(c, c + 4)));
				// 字节低4位
				sb.append(hexArray.get(binary.substring(c + 4, c + 8)));
			}
		}
		return sb.toString();
	}

	/**
	 * 复制bytes为双份
	 * @param bytes
	 * @return
	 */
	public static byte[] duplicate(byte[] bytes) {
		int length = bytes.length;
		int dupLen = length * 2;
		byte[] dupBytes = new byte[dupLen];
		System.arraycopy(bytes, 0, dupBytes, 0, length);
		System.arraycopy(bytes, 0, dupBytes, length, length);
		return dupBytes;
	}

	/**
	 * 取字节数组的子集，通过起始索引和长度
	 * @param startPos 起始索引
	 * @param length 长度
	 * @return
	 */
	public static byte[] subBytes(byte[] bytes, int startPos, int length) {
		byte[] newBytes = new byte[length];
		int srcLen = bytes.length;
		startPos %= srcLen;
		startPos = startPos < 0? srcLen - startPos: startPos;
		System.arraycopy(bytes, startPos, newBytes, 0, length);
		return newBytes;
 	}

	/**
	 * 取字节数组的自己，通过起始索引和结束索引，包含头不包含尾
	 * @param bytes
	 * @param startPos 起始索引
	 * @param endPos 结束索引
	 * @return
	 */
 	public static byte[] subBytes2(byte[] bytes, int startPos, int endPos) {
		int srcLen = bytes.length;
		// TODO: 2018/8/20 优化
		endPos = endPos <= srcLen? endPos: srcLen;
		startPos %= srcLen;
		startPos = startPos < 0? srcLen - startPos: startPos;
		int len = endPos - startPos;
		byte[] newBytes = new byte[len];
		System.arraycopy(bytes, startPos, newBytes, 0, len);
		return newBytes;
	}

	/**
	 * 从1个字节中取对应位的二进制，D0是最低位，D7是最高位
	 * @param binary 二进制
	 * @param pos 位索引，取值7~0
	 * @return 0或1
	 */
	public static byte getOneBit(byte binary, byte pos) {
		return (byte) (binary >> pos & 1);
	}


	/**
	 * 从1个字节中，从指定高位向低位取指定长度二进制D7是高位，D0是低位
	 * @param binary 二进制
	 * @param length 高位索引，取值7~0
	 * @param length 走向低位长度
	 * @return
	 */
	public static byte getMoreBit(byte binary, byte highPos, byte length) {
		return (byte) (binary >> (highPos + 1 - length) & maxValue(length));
	}

	/**
	 * 从1个字节中，从指定高位到最低位取二进制，D7是最高位，D0是最低位
	 * @param binary
	 * @param highPos
	 * @return
	 */
	public static byte getBits(byte binary, byte highPos) {
		return (byte) (binary & maxValue(1 + highPos));
	}

	/**
	 * 从二进制中取对应位区间的的值，包含头，包含尾，D7是最高位左，D0是最低位右
	 * @param binary 二进制
	 * @param highPos 高位索引，取值7~0
	 * @param lowPos 低位索引，取值7~0
	 * @return
	 */
	public static byte getBits(byte binary, byte highPos, byte lowPos) {
		return (byte) (binary >> lowPos & (1 + highPos - lowPos));
	}

	/**
	 *二进制位替换对应1位
	 * @param binary
	 * @param pos
	 * @param val
	 * @return
	 */
	public static byte replaceOneBit(byte binary, byte pos, byte val) {
		pos = pos <= 8? pos: (byte) ((pos % 8) & 0xff);
		byte bitVal = (byte) (val & 1);

		return (byte) ( (binary & (~(1 << pos))) + (bitVal << pos) );
	}

	/**
	 * 二进制替换对应多位
	 * @param binary
	 * @param leftPos 高位索引
	 * @param rightPos 低位索引
	 * @param val
	 * @return
	 */
	public static byte replaceBit(byte binary, byte leftPos, byte rightPos,byte val) {
		// 要替换的位置标记1
		long maxVal = (byte)maxValue(leftPos - rightPos + 1);
		// 要替换的位置用0与掉
		long mask = ~(maxVal << rightPos);
		// 值移到对应位
		long newVal = (val & maxVal) << rightPos;
		// 要替换为先置为0再 加 上新值
		long l = (binary & mask) + newVal;
		return (byte) (l & 0xffL);
	}

	/**
	 * 所有字节的和
	 * @param bytes
	 * @return
	 */
	public static long checksum(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return 0;
		}
		long cs = 0;
		for (byte aByte : bytes) {
			cs += (aByte & 0xffL);
		}
		return cs;
	}

	/**
	 * 指定长度bit的二进制的最大值
	 * @param bit
	 * @return
	 */
	public static long maxValue(int bit) {
		double pow = Math.pow(2, bit);
		return Math.round(pow) - 1;
	}

	public static short getUnsignShort(byte b) {
		return  (short)(b & 0xff);
	}

	public static int getUnsignInt(byte b) {
		return b & 0xff;
	}

	/**
	 * 合并byte数组
	 * @return
	 */
	public static byte[] addBytes(byte[] arg1, byte[] arg2) {

		byte[] result = Arrays.copyOf(arg1, arg1.length + arg2.length);
		System.arraycopy(arg2, 0, result, arg1.length, arg2.length);

		return result;
	}

	private static volatile int SN = 0;

	/**
	 * 获得序列号
	 * 
	 * @return
	 */
	public static synchronized int getSeqNumber() {
		if (SN == Integer.MAX_VALUE)
			SN = 0;
		return ++SN;
	}

	public static String getSeqNum() {
		return DateUtils.getDatetStr() + getSeqNumber();
	}

	public static void main(String[] args) {
		System.out.println(ByteUtil.hexStringToString("5500"));
		System.out.println(ByteUtil.hexStringToBinary("5500"));
	}
}
