package com.example.protocol.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
	public static Timestamp getToday() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getMonth() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getLastMonth() {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static boolean isToday(Date t) {
		Calendar today = new GregorianCalendar();
		Calendar c = timestampToCalendar(t);

		if ((today.get(Calendar.YEAR) == c.get(Calendar.YEAR)) && (today.get(Calendar.MONTH) == c.get(Calendar.MONTH))
				&& (today.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH))) {
			return true;
		}
		return false;
	}

	public static Timestamp getToday(Date t) {
		Calendar c = new GregorianCalendar();
		c.setTime(t);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getYestoday(Date t) {
		Calendar c = new GregorianCalendar();
		c.setTime(t);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getTodayTh(Date t) {
		Calendar c = new GregorianCalendar();
		c.setTime(t);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getNextday(Date t) {
		Calendar c = new GregorianCalendar();
		c.setTime(t);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getNeedTime(Date t1, Date t2) {
		Calendar c = new GregorianCalendar();
		Calendar c1 = new GregorianCalendar();
		if ((t1 == null) || (t2 == null)) {
			return null;
		}
		c.setTime(t1);
		c1.setTime(t2);
		c.set(Calendar.YEAR, c1.get(Calendar.YEAR));
		c.set(Calendar.MONTH, c1.get(Calendar.MONTH));
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getNeedTimeYear(Date t1, Date t2) {
		Calendar c = new GregorianCalendar();
		Calendar c1 = new GregorianCalendar();
		if ((t1 == null) || (t2 == null)) {
			return null;
		}
		c.setTime(t1);
		c1.setTime(t2);
		c.set(Calendar.YEAR, c1.get(Calendar.YEAR));
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getPreMonth(Date t) {
		Calendar c = new GregorianCalendar();
		c.setTime(t);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MONTH, -1);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Calendar timestampToCalendar(Date t) {
		Calendar c = new GregorianCalendar();
		if (t != null) {
			c.setTime(t);
			return c;
		}
		return null;
	}

	public static String getTimeStrOfTimeInMillis(long timeInMillis) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(timeInMillis);
		return df.format(c.getTime());
	}

	public static String getTimeStrOfTimeInMillis(Date ts) {
		if (ts == null) {
			return "";
		}
		return getTimeStrOfTimeInMillis(ts.getTime());
	}

	public static String getTimeStrOfCalender(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(c.getTime());
	}

	public static String getTimeStrOfCalender15(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(c.getTime());
	}

	public static String getTimeStrOfCalender16(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("dd HH:mm:ss");
		return df.format(c.getTime());
	}

	public static String getTimeStrOfCalender17(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("MM-dd HH:mm");
		return df.format(c.getTime());
	}

	public static String getTimeStrOfCalender18(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("dd HH:mm");
		return df.format(c.getTime());
	}

	public static String getTimeStrOfCalender19(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(c.getTime());
	}

	public static String getTimeStrOfCalender20(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(c.getTime());
	}

	public static String getTimeStrOfCalender21(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		return df.format(c.getTime());
	}

	public static String getTimeStrOfCalender24(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("dd HH");
		return df.format(c.getTime());
	}

	public static String getTimeStrOfCalenderYear(Calendar c) {
		if (c == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyy");
		return df.format(c.getTime());
	}

	public static String buildTimeStr(String s1, String s2) {
		if (!s2.equals("")) {
			return s1 + "-" + s2;
		}
		return "";
	}

	public static String buildTimeStr2(String s1, String s2) {
		if (!s2.equals("")) {
			return s1 + s2;
		}
		return "";
	}

	public static String getStrOfCalender(Calendar c) {
		DateFormat df = new SimpleDateFormat("MM-dd HH");
		return df.format(c.getTime());
	}

	public static String getCurTime() {
		return getTimeStrOfCalender(new GregorianCalendar());
	}

	public static Calendar getCurCalendarOfYMDHM() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	public static Calendar getCurCalendarOfYMD() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	public static Calendar getCalendarOfYMD(Date t) {
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(t.getTime());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	public static Calendar getCalender4GivingHourMin(Calendar c, int hour, int min) {
		Calendar cal = (Calendar) c.clone();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar getCalender4GivingDayHourMin(Calendar c, int day, int hour, int min) {
		Calendar cal = (Calendar) c.clone();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static boolean afterOrEqual(Calendar srcCal, Calendar compCal) {
		return (srcCal.after(compCal)) || (srcCal.getTimeInMillis() == compCal.getTimeInMillis());
	}

	public static Calendar add(Calendar cal, int unit, int val) {
		Calendar calAfterAdd = (Calendar) cal.clone();
		calAfterAdd.add(unit, val);
		return calAfterAdd;
	}

	public static Date strToDate(String sStr) {
		if (sStr == null) {
			return null;
		}
		SimpleDateFormat formatter;
		if (sStr.length() == 19) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			if (sStr.length() == 16) {
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			} else {
				if (sStr.length() == 10) {
					formatter = new SimpleDateFormat("yyyy-MM-dd");
				} else {
					if (sStr.length() == 8) {
						formatter = new SimpleDateFormat("yyyyMMdd");
					} else {
						if (sStr.length() == 14) {
							formatter = new SimpleDateFormat("yyyyMMddHHmmss");
						} else {
							formatter = new SimpleDateFormat("yyyyMMddHHmmss");
						}
					}
				}
			}
		}
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(sStr, pos);
	}

	public static Timestamp strToDatetime(String s) {
		if (s == null || (s.equals(""))) {
			return null;
		}
		return new Timestamp(strToDate(s).getTime());
	}

	public static String stampToStr(Date t) {
		return getTimeStrOfCalender(timestampToCalendar(t));
	}

	public static String stampToStr15(Date t) {
		return getTimeStrOfCalender15(timestampToCalendar(t));
	}

	public static String stampToStr16(Date t) {
		return getTimeStrOfCalender16(timestampToCalendar(t));
	}

	public static String stampToStr17(Date t) {
		return getTimeStrOfCalender17(timestampToCalendar(t));
	}

	public static String stampToStr18(Date t) {
		return getTimeStrOfCalender18(timestampToCalendar(t));
	}

	public static String stampToStr19(Date t) {
		return getTimeStrOfCalender19(timestampToCalendar(t));
	}

	public static String stampToStr20(Date t) {
		return getTimeStrOfCalender20(timestampToCalendar(t));
	}

	public static String stampToStr21(Date t) {
		return getTimeStrOfCalender21(timestampToCalendar(t));
	}

	public static String stampToStr24(Date t) {
		return getTimeStrOfCalender24(timestampToCalendar(t));
	}

	public static String stampToStrYear(Date t) {
		return getTimeStrOfCalenderYear(timestampToCalendar(t));
	}

	public static String datetimeToChinese(Date dtSource) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		return formatter.format(dtSource);
	}

	public static String dateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}

	public static String datetimeToStr(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static String today() {
		return dateToStr(new Date());
	}

	public static String now() {
		return datetimeToStr(new Date());
	}

	public static String getCurrentTime() {
		String s = new Time(System.currentTimeMillis()).toString();
		return s;
	}

	public static String getCurrentDate() {
		String s = new java.sql.Date(System.currentTimeMillis()).toString();
		return s;
	}

	public static String getCurrentNow() {
		String s = getCurrentDate() + " " + getCurrentTime();
		return s;
	}

	public static String dateFromStr(String date) {
		if ((date == null) || (date.length() < 10)) {
			return getCurrentDate();
		}
		return date.substring(0, 10);
	}

	public static String timeFromStr(String date) {
		if ((date == null) || (date.length() < 19)) {
			return "00:00:00";
		}
		return date.substring(11, 19);
	}

	public static Timestamp setHour2Zero(Date t) {
		Calendar c = new GregorianCalendar();
		c.setTime(t);
		c.set(Calendar.HOUR_OF_DAY, 0);

		return new Timestamp(c.getTimeInMillis());
	}

	public static int returnHour(Date t) {
		Calendar c = new GregorianCalendar();
		c.setTime(t);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static byte[] getYMDHMSByteArray(Calendar c) {
		int sec = c.get(Calendar.SECOND);
		int min = c.get(Calendar.MINUTE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		byte[] frame = new byte[6];
		frame[0] = ProtocolUtil.to1ByteBcd(sec);
		frame[1] = ProtocolUtil.to1ByteBcd(min);
		frame[2] = ProtocolUtil.to1ByteBcd(hour);
		frame[3] = ProtocolUtil.to1ByteBcd(day);
		frame[4] = ProtocolUtil.to1ByteBcd(month);
		frame[5] = ProtocolUtil.to1ByteBcd(year - 2000);
		return frame;
	}

	public static Timestamp parseYMDHMSByteArray(byte[] frame, int offset) {
		int len = 0;
		int sec = ParserUtil.fromBcd(frame[(offset + len++)]);
		int min = ParserUtil.fromBcd(frame[(offset + len++)]);
		int hour = ParserUtil.fromBcd(frame[(offset + len++)]);
		int day = ParserUtil.fromBcd(frame[(offset + len++)]);
		int month = ParserUtil.fromBcd(frame[(offset + len++)]);
		int year = 2000 + ParserUtil.fromBcd(frame[(offset + len++)]);
		return new Timestamp(new GregorianCalendar(year, month - 1, day, hour, min, sec).getTimeInMillis());
	}

	public static Timestamp calendarToTimestamp(Calendar c) {
		return new Timestamp(c.getTimeInMillis());
	}

	public static int getTwoTimeMinutes(Date t1, Date t2) {
		return (int) (t2.getTime() - t1.getTime()) / 60000;
	}

	public static long getDaysBetweenNow(Timestamp yDate) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTimeInMillis(yDate.getTime());
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);

		return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / 86400000L;
	}

	public static int getMonthBetweenNow(Timestamp yDate) {
		int bMonths = 0;
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTimeInMillis(yDate.getTime());
		int fromYear = fromCalendar.get(Calendar.YEAR);
		int fromMonth = fromCalendar.get(Calendar.MONTH);

		Calendar toCalendar = Calendar.getInstance();
		int toYear = toCalendar.get(Calendar.YEAR);
		int toMonth = toCalendar.get(Calendar.MONTH);

		if (toYear == fromYear) {
			bMonths = toMonth - fromMonth;
		} else if (toYear == fromYear + 1) {
			bMonths = toMonth + 11 - fromMonth;
		} else {
			bMonths = (toYear - fromYear - 1) * 12 + toMonth + 11 - fromMonth;
		}

		return bMonths;
	}

	public static Timestamp addMinutes(Date t, int min) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		c.add(Calendar.MINUTE, min);

		return new Timestamp(c.getTimeInMillis());
	}

	public static void main(String[] args) {
		System.out.println(getTimeStrOfTimeInMillis(1385615725742L));
	}
}
