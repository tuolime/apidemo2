package com.ss.apidemo.utils;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳与字符串转换
 * 
 */
public class DateUtils {
//	private final static Logger log = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * 默认日期格式
	 */
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/**
	 * 默认构造函数
	 */
	private DateUtils() {
	}

	/**
	 * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            日期格式
	 * @return 日期
	 * @throws ParseException
	 */
	public static Date str2Date(String str, String format) {
		if (null == str || "".equals(str)) {
			return null;
		}
		// 如果没有指定字符串转换的格式，则用默认格式进行转换
		if (null == format || "".equals(format)) {
			format = DEFAULT_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
//			log.error("日期转换异常", e);
		}
		return null;
	}

	/**
	 * 日期转换为字符串
	 *
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, String format) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 日期转换为字符串
	 * @see #DATE_FORMAT_YYYY_MM_DD
	 * @param date
	 *            日期
	 * @return 字符串
	 */
	public static String date2Str(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return sdf.format(date);
	}

	/**
	 * 日期转换为字符串
	 * @see #DEFAULT_FORMAT
	 * @param date
	 *            日期
	 * @return 字符串
	 */
	public static String dateTime2Str(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 时间戳转换为字符串
	 *
	 * @param time
	 * @return
	 */
	public static String timestamp2Str(Timestamp time) {
		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return date2Str(date, DEFAULT_FORMAT);
	}

	/**
	 * 时间戳转换为字符串
	 *
	 * @param time
	 * @return
	 */
	public static String timestamp2DateStr(Timestamp time) {
		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return date2Str(date, DATE_FORMAT_YYYY_MM_DD);
	}

	/**
	 * 字符串转换时间戳 字符串格式yyyy-MM-dd HH:mm:ss
	 *
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, DEFAULT_FORMAT);
		return new Timestamp(date.getTime());
	}

	/**
	 * 字符串转换时间戳 字符串格式yyyy-MM-dd
	 *
	 * @param str
	 * @return
	 */
	public static String str2str(String str) {
		Date date = str2Date(str, DATE_FORMAT_YYYY_MM_DD);
		long a = date.getTime() / 1000;
		String b = a + "";
		return b;
	}

	/**
	 * 字符串转换时间戳 字符串格式yyyy-MM-dd HH:mm:ss
	 *
	 * @param str
	 * @return
	 */
	public static String str2straa(String str) {
		Date date = str2Date(str, DEFAULT_FORMAT);
		long a = date.getTime() / 1000;
		String b = a + "";
		return b;
	}

	/**
	 * 取得系统时间
	 *
	 * @return yyyy-mm-dd hh:mm:ss
	 */
	public static String getDateTime() {
		Date date = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		return df.format(date);
	}

	/**
	 * 取得系统时间
	 *
	 * @return yyyy-mm-dd hh:mm:ss
	 */
	public static String getDatet() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		return sdf.format(new Date());
	}

	/**
	 * 取得系统时间
	 *
	 * @return yyyy-mm-dd hh:mm:ss
	 */
	public static String getDatetStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS);
		return sdf.format(new Date());
	}

	public static long getCurrentLong() {
		return System.currentTimeMillis() / 1000;
	}

	// 取得当前时间
	public static String getCurrentTime() {
		return long2String(getCurrentLong());
	}

	/**
	 * long转换String日期
	 *
	 * @param date
	 *            为空，返回当前时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String long2String(long date) {
		if (date > 0)
			return new SimpleDateFormat(DEFAULT_FORMAT).format(new Date(date * 1000));
		else
			return getDateTime();
	}

	public static String getCurrentDate() {
		Date date = new Date();
		DateFormat df = DateFormat.getDateInstance();
		return df.format(date);
	}

	/**
	 * 把时间转换为long
	 */
	public static long getDate2Long(String date) {
		Date date2 = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
			date2 = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2.getTime() / 1000;
	}

	/**
	 * 取得系统时间
	 * 
	 * @return DATE_FORMAT_YYYYMMDDHHMMSS
	 */
	public static String getDateTimeYYYYMMDDHHMMSS() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS);
		return sdf.format(new Date());
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getDate2Long("2013-4-10 9:18:29"));
	}

}
