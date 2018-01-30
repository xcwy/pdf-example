package io.rai.example.utils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil extends PropertyEditorSupport {

	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static final SimpleDateFormat datetimeFormat2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	private static final SimpleDateFormat datetimeFormat3 = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat datetimeFormat4 = new SimpleDateFormat(
			"MMM.dd.yyyy",Locale.ENGLISH);

	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;// 以毫秒表示的时间

	public static String formateDate(Timestamp t) {
		return datetimeFormat.format(t);
	}

	public static String formateDate2(Timestamp t) {
		return datetimeFormat2.format(t);
	}
	public static String formateDate3(Timestamp t) {
		return datetimeFormat3.format(t);
	}
	public static String formateDate4(Timestamp t) {
		return datetimeFormat4.format(t);
	}

	public static Timestamp stringToTimestamp(String date, String pattern) {
		if (date == null || "".equals(date)) {
			return null;
		}
		DateFormat format = new SimpleDateFormat(pattern);
		format.setLenient(false);
		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * 获取当前日期的时间戳
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTimeStamp() {
		String s = datetimeFormat.format(new Date());
		return Timestamp.valueOf(s);
	}

	/**
	 * 获取yyyy-MM-dd HH:mm:ss格式的日期字符串
	 * 
	 * @return
	 */
	public static String getDateString() {
		Date date = new Date();
		return datetimeFormat.format(date);
	}

	public static String getDateString2() {
		Date date = new Date();
		return datetimeFormat3.format(date);
	}

	/**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * 
	 * @param flag
	 *            计算标志，表示按照年/月/日/时/分/秒等计算
	 * @param timeSrc
	 *            减数
	 * @param timeDes
	 *            被减数
	 * @return 两个日期之间的差值
	 */
	public static int dateDiff(char flag, Timestamp timeSrc, Timestamp timeDes) {

		long millisDiff = timeSrc.getTime() - timeDes.getTime();

		if (flag == 'd') {
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		return 0;
	}

	public static Timestamp addHourFromNow(int hour) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.HOUR, hour);
		Timestamp timestamp = new Timestamp(gc.getTimeInMillis());
		return Timestamp.valueOf(datetimeFormat.format(timestamp));
	}

	public static Timestamp addSecondFromNow(int second) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.SECOND, second);
		Timestamp timestamp = new Timestamp(gc.getTimeInMillis());
		return Timestamp.valueOf(datetimeFormat.format(timestamp));
	}
}
