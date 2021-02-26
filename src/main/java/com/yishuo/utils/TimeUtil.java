package com.yishuo.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtil {

	public static final String MINUTE = "MINUTE";
	public static final String HOUR = "HOUR";
	public static final String DAY = "DAY";
	public static final String MONTH = "MONTH";
	public static final String YEAR = "YEAR";

	/*************************** 当前日期操作-开始 ****************************/

	/**
	 * 取得当前日期的年份，以yyyy格式返回.
	 * 
	 * @return 当年 yyyy
	 */
	public static String getCurrentYear() {
		return getCurrentTime("yyyy");
	}

	/**
	 * 取得当前日期的月份，以MM格式返回.
	 * 
	 * @return 当前月份 MM
	 */
	public static String getCurrentMonth() {
		return getCurrentTime("MM");
	}

	/**
	 * 取得当前日期的天数，以格式"dd"返回.
	 * 
	 * @return 当前月中的某天dd
	 */
	public static String getCurrentDay() {
		return getCurrentTime("dd");
	}

	/**
	 * 根据给定的格式，返回当前时间字符串。
	 * 
	 * @param format
	 *            日期格式字符串，如yyyy-MM-dd
	 * @return String 指定格式的日期字符串.
	 */
	public static String getCurrentTime(String format) {
		return date2str(new Date(), format);
	}

	/*************************** 当前日期操作-结束 ****************************/

	/**************************** 时间偏移方法-开始 ***********************************/

	/**
	 * 时间偏移：当前日期天偏移
	 * 
	 * @param amount
	 *            距今天的间隔日期长度，向前为负，向后为正
	 * @param format
	 *            输出日期的格式.
	 * @return java.lang.String 按照格式输出的间隔的日期字符串.
	 */
	public static String addDays(int amount, String format) {

		Date d = addDays(new Date(), amount);

		return date2str(d, format);
	}

	/**
	 *时间偏移： 指定日期天偏移
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的天数，向前为负，向后为正
	 * @return Date 加上一定天数以后的Date对象.
	 */
	public static Date addDays(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return cal.getTime();
	}

	/**
	 * 时间偏移：指定日期天偏移
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的天数，向前为负，向后为正
	 * @param format
	 *            输出格式.
	 * @return Date 加上一定天数以后的Date对象.
	 */
	public static String addDays(Date date, int amount, String format) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return date2str(cal.getTime(), format);
	}
	
	/**
	 * 时间偏移：指定日期天偏移
	 * 
	 * @param dateStr
	 *            给定的日期,与format指定的格式一致
	 * @param amount
	 *            需要添加的天数，向前为负，向后为正
	 * @param format
	 *            输出格式.
	 * @return Date 加上一定天数以后的Date对象.
	 */
	public static String addDays(String dateStr, int amount, String format) throws ParseException {
		Date date = str2date(dateStr, format);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return date2str(cal.getTime(), format);
	}

	/**
	 * 时间偏移：指定日期小时偏移
	 * 
	 * @param date
	 * @param amount
	 *            偏移小时，可为负数
	 * @return
	 */
	public static Date addHours(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.HOUR, amount);
		return cal.getTime();
	}
	
	
	public static Date addMinutes(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.MINUTE, amount);
		return cal.getTime();
	}

	/**
	 * 取得给定日期加上一定小时后的日期,向前加负数
	 * 
	 * @param dateStr
	 *            日期String,与format指定的格式一致
	 * @param format
	 *            日期格式，如yyyy-MM-dd
	 * @param amount
	 *            偏移小时，可为负数
	 * @return
	 * @throws ParseException 
	 */
	public static String addHours(String dateStr, int amount, String format) throws ParseException {
		Date date = str2date(dateStr, format);
		date = addHours(date, amount);
		return date2str(date, format);
	}
	
	/**
	 * 获取指定日期的下个月
	 * @param dateString 格式为 yyyy-MM
	 * @return
	 */
	public static String getNextMonth(String dateString) {
		int intYear = Integer.parseInt(dateString.substring(0, 4));
		int intMonth = Integer.parseInt(dateString.substring(dateString
				.indexOf("-") + 1));

		if (intMonth == 12) {
			intMonth = 1;
			intYear++;
		} else {
			intMonth++;
		}

		String tempMonth;
		if (intMonth < 10) {
			tempMonth = "0" + Integer.toString(intMonth);
		} else {
			tempMonth = Integer.toString(intMonth);
		}

		return intYear + "-" + tempMonth;
	}
	
	/**
	 * 获取指定日期的上个月
	 * @param dateString 格式为 yyyy-MM
	 * @return
	 */
	public static String getPreviousMonth(String dateString) {
		int intYear = Integer.parseInt(dateString.substring(0, 4));
		int intMonth = Integer.parseInt(dateString.substring(dateString
				.indexOf("-") + 1));
		String tempPM;
		// 上个月
		if (intMonth == 1) {
			intMonth = 12;
			intYear--;
		} else {
			intMonth--;
		}
		if (intMonth < 10) {
			tempPM = "0" + intMonth;
		} else {
			tempPM = intMonth + "";
		}
		return intYear + "-" + tempPM;
	}
	
	/**
	 * 将传入的日期调整月数
	 * 
	 * @param value
	 *            String yyyy-mm-dd
	 * @param num
	 *            int
	 * @return String 格式为yyyy-mm
	 */
	public static String changeMonth(String value, int num) {
		String ret = "";

		// 取年
		String strMonth = "";
		int intYear = Integer.parseInt(value.substring(0, 4));
		int intMonth = Integer.parseInt(value.substring(5, 7));
		intMonth = intMonth + num;

		if (intMonth > 12) {
			intYear = intYear + 1;
			intMonth = 1;
		}

		if (intMonth < 10) {
			strMonth = "0" + intMonth;
		} else {
			strMonth = intMonth + "";
		}

		ret = intYear + "-" + strMonth;

		return ret;
	}

	/**
	 * 求两个日期相差天数
	 * 
	 * @param sd
	 *            起始日期，格式yyyy-MM-dd
	 * @param ed
	 *            终止日期，格式yyyy-MM-dd
	 * @return 两个日期相差天数
	 */
	public static long getIntervalDays(String sd, String ed) {
		return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date
				.valueOf(sd)).getTime())
				/ (3600 * 24 * 1000);
	}
	
	/**
	* 求两个日期相差天数
	 * 
	 * @param sd
	 *            起始日期
	 * @param ed
	 *            终止日期
	 * @return 两个日期相差天数
	 */
	public static long getIntervalDays(Date sd, Date ed) {
		return ((ed).getTime() - (sd).getTime())
				/ (3600 * 24 * 1000);
	}

	/**
	 * 起始年月yyyy-MM与终止月yyyy-MM之间跨度的月数
	 * 
	 * @param beginMonth
	 *            起始日期，格式yyyy-MM
	 * @param endMonth
	 *            终止日期，格式yyyy-MM
	 * @return 两个日期相差月数
	 */
	public static int getIntervalMonth(String beginMonth, String endMonth) {
		int intBeginYear = Integer.parseInt(beginMonth.substring(0, 4));
		int intBeginMonth = Integer.parseInt(beginMonth.substring(beginMonth
				.indexOf("-") + 1));
		int intEndYear = Integer.parseInt(endMonth.substring(0, 4));
		int intEndMonth = Integer.parseInt(endMonth.substring(endMonth
				.indexOf("-") + 1));

		return ((intEndYear - intBeginYear) * 12)
				+ (intEndMonth - intBeginMonth) + 1;
	}
	/**************************** 时间偏移方法-结束 ***********************************/

	/**************************** 时间格式化-开始 ***********************************/

	/**
	 * 取得指定年月日的日期对象.
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月注意是从1到12
	 * @param day
	 *            日
	 * @return 一个java.util.Date()类型的对象
	 */
	public static Date getDateObj(int year, int month, int day) {
		Calendar c = new GregorianCalendar();
		c.set(year, month - 1, day);
		return c.getTime();
	}
	

	/**
	 * 取得指定分隔符分割的年月日的日期对象.
	 * 
	 * @param dateStr
	 *            格式为"yyyy-MM-dd"
	 * @param split
	 *            时间格式的间隔符，例如“-”，“/”
	 * @return 一个java.util.Date()类型的对象
	 */
	public static Date getDateObj(String dateStr, String split) {
		String[] temp = dateStr.split(split);
		int year = new Integer(temp[0]).intValue();
		int month = new Integer(temp[1]).intValue();
		int day = new Integer(temp[2]).intValue();
		return getDateObj(year, month, day);
	}
	
	
	/**
	 * 格式化日期显示格式
	 * 
	 * @param sdate
	 *            原始日期格式
	 * @param format
	 *            格式化后日期格式
	 * @return 格式化后的日期显示
	 */
	public static String dateChangeFormat(String sdate, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		java.sql.Date date = java.sql.Date.valueOf(sdate);
		String dateString = formatter.format(date);

		return dateString;
	}
	
	/**
	 * 将时间转换为"yyyy-MM-dd HH:mm:ss"格式的字符串
	 * 
	 * @return String
	 */
	public static String date2str(long time) {
		return date2str(new Date(time));
	}

	/**
	 * 将时间转换为"yyyy-MM-dd HH:mm:ss"格式的字符串
	 * 
	 * @return String
	 */
	public static String date2str(long time, String format) {
		return date2str(new Date(time), format);
	}

	/**
	 * 将时间转换为"yyyy-MM-dd HH:mm:ss"格式的字符串
	 * 
	 * @return String
	 */
	public static String date2str(Date date) {
		return date2str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将时间转换为"yyyy-MM-dd HH:mm:ss"格式的字符串
	 * 
	 * @return String
	 */
	public static String date2str(Date date, String format) {
		if (date == null || StringUtils.isEmpty(format)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 将当前时间转换为"yyyy-MM-dd HH:mm:ss"格式的字符串
	 * 
	 * @return String
	 */
	public static String nowTime2str() {
		return nowTime2str("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将当前时间转换为"yyyy-MM-dd HH:mm:ss"格式的字符串
	 * 
	 * @return String
	 */
	public static String nowTime2str(String format) {
		Date now = new Date();
		return date2str(now, format);
	}

	/**
	 * 使用默认格式将字符串转换为Date 默认格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timeStr
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date str2date(String timeStr) throws ParseException {
		return str2date(timeStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 使用指定格式将字符串转换为Date
	 * 
	 * @param timeStr
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date str2date(String timeStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dt = sdf.parse(timeStr);
		return dt;
	}
	
	/**************************** 时间格式化-结束 ***********************************/


	/**
	 * 获得当前的时间， 默认格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timeStr
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getNowDate() throws ParseException {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.parse(simple.format(new Date()));
	}


	/**
	 * 将时间字符串转换为TimeStamp
	 * 
	 * @param timeStr
	 *            String
	 * @param format
	 *            String
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp getTimeStamp(String timeStr, String format) throws ParseException {
		if (timeStr == null || "".equals(timeStr) || format == null || "".equals(format)) {
			return null;
		}
		return new Timestamp(str2date(timeStr, format).getTime());
	}

	/**
	 * 将时间字符串转换为TimeStamp 时间字符串格式为 "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param timeStr
	 *            String
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp getTimeStamp(String timeStr) throws ParseException {
		return getTimeStamp(timeStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 判断时间是否在指定时间范围内
	 * 
	 * @param time
	 *            String
	 * @param fromTime
	 *            String
	 * @param toTime
	 *            String
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean timeIn(String time, String fromTime, String toTime) throws ParseException {
		Date date = str2date(time);
		Date fromDate = str2date(fromTime);
		Date toDate = str2date(toTime);

		return date.after(fromDate) && date.before(toDate);
	}

	@SuppressWarnings("static-access")
	public static long getToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.HOUR_OF_DAY, 0);
		cal.set(cal.MINUTE, 0);
		cal.set(cal.SECOND, 0);
		cal.set(cal.MILLISECOND, 0);

		return cal.getTimeInMillis();
	}

	public static long getYesterday() {
		return getToday() - 1000 * 3600 * 24;
	}

	public static long getDayBefore(int interval) {
		return getToday() - 1000 * 3600 * 24 * interval;
	}

	/**
	 * 获取时间点
	 * 
	 * @param condition
	 *            -2MON3W1D2H 一天两小时前 -1MON2W2H 两小时前 1MON2W1D2H 一天两小时后
	 *            1MON4W2H两小时后
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String computeDate(String condition) {
		int day = 0;
		int hour = 0;
		int minute = 0;
		if (condition.indexOf("D") > 0) {
			String d = condition.substring(0, condition.indexOf("D"));
			day = Integer.parseInt(d);
			if (condition.indexOf("H") > 0) {
				String h = condition.substring(condition.indexOf("D") + 1, condition.indexOf("H"));
				hour = Integer.parseInt(h);
				if (condition.indexOf("-") == 0) {
					hour = -hour;
				}
			}
		} else if (condition.indexOf("H") > 0) {
			String h = condition.substring(0, condition.indexOf("H"));
			hour = Integer.parseInt(h);
		} else if (condition.indexOf("M") > 0) {
			String m = condition.substring(0, condition.indexOf("M"));
			minute = Integer.parseInt(m);
		}

		Calendar cal = Calendar.getInstance();
		cal.add(cal.DAY_OF_MONTH, day);
		cal.add(cal.HOUR_OF_DAY, hour);
		cal.set(cal.SECOND, 0);
		if (condition.indexOf("M") > 0) {
			cal.add(cal.MINUTE, minute);
		} else {
			cal.set(cal.MINUTE, 0);
		}
		int mmm = cal.get(cal.MINUTE);
		if (mmm > 0) {
			// 分钟倒推导致的时间不归整格式化为整刻
			while (mmm % 15 != 0) {
				mmm--;
			}
			cal.set(cal.MINUTE, mmm);
		}
		return date2str(cal.getTimeInMillis());
	}


	/**
	 * 跟据日期格式获取时间点
	 * 
	 * @param date
	 * @param condition
	 *            -1D2H 一天两小时前 -2H 两小时前 1D2H 一天两小时后 2H 两小时后
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String computeDate(Date date, String condition) {
		int day = 0;
		int hour = 0;
		if (condition.indexOf("D") > 0) {
			String d = condition.substring(0, condition.indexOf("D"));
			day = Integer.parseInt(d);
			if (condition.indexOf("H") > 0) {
				String h = condition.substring(condition.indexOf("D") + 1, condition.indexOf("H"));
				hour = Integer.parseInt(h);
				if (condition.indexOf("-") == 0) {
					hour = -hour;
				}
			}
		} else if (condition.indexOf("H") > 0) {
			String h = condition.substring(0, condition.indexOf("H"));
			hour = Integer.parseInt(h);
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.DAY_OF_MONTH, day);
		cal.add(cal.HOUR_OF_DAY, hour);
		cal.set(cal.MINUTE, 0);
		cal.set(cal.SECOND, 0);

		return date2str(cal.getTimeInMillis());
	}
	
	@SuppressWarnings("static-access")
	public static String computeDate(String format, String condition)
	{
		// -1D2H 一天两小时前
		// -H2 两小时前
		// 1D2H 一天两小时后
		// H2 两小时后

		int day = 0;
		int hour = 0;
		if (condition.indexOf("D") > 0)
		{
			String d = condition.substring(0, condition.indexOf("D"));
			day = Integer.parseInt(d);
			if (condition.indexOf("H") > 0)
			{
				String h = condition.substring(condition.indexOf("D") + 1, condition.indexOf("H"));
				hour = Integer.parseInt(h);
				if (condition.indexOf("-") == 0)
				{
					hour = -hour;
				}
			}
		} else if (condition.indexOf("H") > 0)
		{
			String h = condition.substring(condition.indexOf("H") + 1, condition.length());
			hour = Integer.parseInt(h);
			if (condition.indexOf("-") == 0)
			{
				hour = -hour;
			}
		}

		Calendar cal = Calendar.getInstance();
		cal.add(cal.DAY_OF_MONTH, day);
		cal.add(cal.HOUR_OF_DAY, hour);
		cal.set(cal.MINUTE, 0);
		cal.set(cal.SECOND, 0);

		return date2str(cal.getTimeInMillis(), format);
	}

	/**
	 * 通过如下格式获取时间列表
	 * 
	 * 
	 * @param timeBetween
	 *            格式为 yyyy-MM-dd HH:mm:ss-yyyy-MM-dd HH:mm:ss
	 * @param condition
	 *            3MON-2MON 起始于3月，结束于2月 -2MON4,5,12D2,16,20H30,45M
	 *            前两个月，第四,五，十二天的2，16，20点30，45分 -2MON3,4,1W2H45M
	 *            前两个月，每周三，四，一的2点45分 2MON4D2H15M 后两个月，每月四号的2点15分 2MON4W2H60M
	 *            后两个月，每周四的3点
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> computeDiscreteTime(String timeBetween, String condition) {
		List<String> timeList = new ArrayList<String>();

		String monStr = null;
		String dayStr = null;
		String weekStr = null;
		String hourStr = null;
		String minStr = null;

		@SuppressWarnings("unused")
		String startTime = null;
		@SuppressWarnings("unused")
		String startRank = null;
		@SuppressWarnings("unused")
		String endTime = null;
		@SuppressWarnings("unused")
		String endRank = null;

		if (StringUtils.isNotEmpty(timeBetween) && timeBetween.indexOf("-") > 1) {
			startTime = timeBetween.split("-")[0];
			endTime = timeBetween.split("-")[1];
		}

		boolean beforeMark = false;

		if (condition.indexOf("-") > 0) {
			beforeMark = false;// 以前的时间
		} else {
			beforeMark = true;// 以后的时间
		}

		// 月条件
		if (condition.indexOf("MON") > 0) {
			monStr = condition.substring(0, condition.indexOf("MON"));

		}
		// 天条件
		if (condition.indexOf("D") > 0) {
			dayStr = condition.substring(condition.indexOf("MON") + "MON".length(), condition.indexOf("D"));
			// 小时条件
			if (condition.indexOf("H") > 0) {
				hourStr = condition.substring(condition.indexOf("D") + 1, condition.indexOf("H"));
			}

		}

		// 周条件
		if (condition.lastIndexOf("W") > 0) {
			weekStr = condition.substring(condition.indexOf("MON") + "MON".length(), condition.lastIndexOf("W"));
			// 小时条件
			if (condition.indexOf("H") > 0) {
				hourStr = condition.substring(condition.indexOf("W") + 1, condition.indexOf("H"));
			}
		}

		// 分钟条件
		if (condition.indexOf("M") > 0) {
			minStr = condition.substring(condition.indexOf("H") + 1, condition.lastIndexOf("M"));
		}

		System.out.println("monStr=:" + monStr + "dayStr=:" + dayStr + "weekStr=:" + weekStr + "hourStr=:" + hourStr + "minStr=:" + minStr);

		if (StringUtils.isNotEmpty(monStr)) {// 月的情况
			String[] monStrArr = monStr.split(",");
			for (int k = 0; k < monStrArr.length; k++) {
				System.out.println(monStrArr[k]);
				if (StringUtils.isNotEmpty(dayStr))// 选择天的情况
				{
					String[] dayStrArr = dayStr.split(",");
					for (int m = 0; m < dayStrArr.length; m++) {
						if (StringUtils.isNotEmpty(hourStr))// 小时的情况
						{
							String hourStrArr[] = hourStr.split(",");
							for (int i = 0; i < hourStrArr.length; i++) {
								if (StringUtils.isNotEmpty(minStr)) // 这里要求分钟必须有一个默认的，也就是必须要选择一个（前台控制）
								{
									String minStrArr[] = minStr.split(",");
									for (int j = 0; j < minStrArr.length; j++) {
										Calendar cal = Calendar.getInstance();
										if (beforeMark) {
											cal.add(cal.MONTH, -Integer.parseInt(monStrArr[k]));
										} else {
											cal.add(cal.MONTH, Integer.parseInt(monStrArr[k]));
										}
										cal.add(cal.DAY_OF_MONTH, Integer.parseInt(dayStrArr[m]));
										cal.set(cal.HOUR_OF_DAY, Integer.parseInt(hourStrArr[i]));
										cal.set(cal.MINUTE, Integer.parseInt(minStrArr[j]));
										timeList.add(date2str(cal.getTimeInMillis()));
									}
								}
							}
						}
					}
				}
				if (StringUtils.isNotEmpty(weekStr))// 周的情况
				{
					String[] weekStrArr = weekStr.split(",");
					int weekDay = -1;// 周几
					for (int m = 0; m < weekStrArr.length; m++) {
						weekDay = Integer.parseInt(weekStrArr[m]);
						if (StringUtils.isNotEmpty(hourStr)) // 小时的情况
						{
							String hourStrArr[] = hourStr.split(",");
							for (int i = 0; i < hourStrArr.length; i++) {
								if (StringUtils.isNotEmpty(minStr)) // 这里要求分钟必须有一个默认的，也就是必须要选择一个（前台控制）
								{
									String minStrArr[] = minStr.split(",");
									for (int j = 0; j < minStrArr.length; j++) {
										Calendar cal = Calendar.getInstance();
										if (beforeMark) {
											cal.add(cal.MONTH, -Integer.parseInt(monStrArr[k]));
										} else {
											cal.add(cal.MONTH, Integer.parseInt(monStrArr[k]));
										}
										cal.set(cal.HOUR_OF_DAY, Integer.parseInt(hourStrArr[i]));
										cal.set(cal.MINUTE, Integer.parseInt(minStrArr[j]));

										cal.setFirstDayOfWeek(Calendar.MONDAY);

										cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY + (weekDay - 1));

										timeList.add(date2str(cal.getTimeInMillis()));
									}
								}
							}
						}
					}
				}
			}
		}
		return timeList;
	}

	/**
	 * 产生一个12位的随机数
	 * 
	 * @return
	 */
	public static String identity() {
		Random r = new Random();
		Random y = new Random();
		for (int i = 0; i < 19; i++) {
			String id = String.valueOf(Math.abs(r.nextInt())) + String.valueOf(Math.abs(y.nextInt()));
			int idlength = 12;
			if (id.length() >= idlength + 1) {
				String retid = id.substring(1, idlength + 1);
				// System.out.println("RamdomNum====" + retid);
				return retid;
			}
		}
		System.out.println("File_id generated error");
		return null;
	}

	public static String getOneDayOneIntTime(String day, String intTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			// String intTime=preTime+aftime;
			date = format.parse(format.format(date));
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + Integer.valueOf(day));
			// System.out.println(":"+format.format(c.getTime()));
			long Time = (date.getTime() / 1000) + Integer.parseInt("-1") * 24 * 60 * 60;
			date.setTime(Time);
			// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			StringBuilder time = new StringBuilder(format.format(c.getTime()));
			if (intTime != null && intTime.length() > 0) {
				time.append(" " + intTime + ":00:00");
			} else {
				time.append(" 00:00:00");
			}

			return time.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format.format(date);
	}

	/**
	 * 根据开始时间、结束时间、时间粒度计算得出该时间范围内所有的时间点 add by wuyg 2011-7-3
	 * 
	 * @param startTime
	 * @param endTime
	 * @param timeGranularity
	 *            可填YEAR，MONTH，DAY，HOUR，MINUTE
	 * @return
	 */
	public static List<String> getTimeList(Date startTime, Date endTime, String timeGranularity) {

		List<String> timeList = new ArrayList<String>();

		//开始时间和结束时间一样，则直接返回
		if (startTime.getTime() == endTime.getTime()) {
			timeList.add(date2str(startTime));
			return timeList;
		}

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startTime);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endTime);

		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		cal.add(Calendar.YEAR, -1);// 开始时间减1年避免第一个时间段被错过，因为时间粒度最粗就是年了所以减1年足够了

		if (timeGranularity.equalsIgnoreCase(MINUTE)) {
			cal.set(Calendar.SECOND, 0);
		} else if (timeGranularity.equalsIgnoreCase(HOUR)) {
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		} else if (timeGranularity.equalsIgnoreCase(DAY)) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		} else if (timeGranularity.equalsIgnoreCase(MONTH)) {
			cal.set(Calendar.DAY_OF_MONTH, 1);// 年中月从1开始
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		} else if (timeGranularity.equalsIgnoreCase(YEAR)) {
			cal.set(Calendar.DAY_OF_YEAR, 1);// 年中天从1开始
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		}

		while (true) {
			if (timeGranularity.equalsIgnoreCase(MINUTE)) {
				cal.add(Calendar.MINUTE, 15);
			} else if (timeGranularity.equalsIgnoreCase(HOUR)) {
				cal.add(Calendar.HOUR, 1);
			} else if (timeGranularity.equalsIgnoreCase(DAY)) {
				cal.add(Calendar.DAY_OF_YEAR, 1);
			} else if (timeGranularity.equalsIgnoreCase(MONTH)) {
				cal.add(Calendar.MONTH, 1);
			} else if (timeGranularity.equalsIgnoreCase(YEAR)) {
				cal.add(Calendar.YEAR, 1);
			}

			if (cal.getTimeInMillis() < startCal.getTimeInMillis()) {
				continue;
			} else if (cal.getTimeInMillis() >= endCal.getTimeInMillis()) {
				break;
			} else {
				timeList.add(date2str(cal.getTime()));
			}
		}

		return timeList;
	}
	
	/**
	 * 返回指定日期所在的周是一年中的第几个周。<br>
	 * created by wangmj at 20060324.<br>
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月，范围1-12<br>
	 * @param day
	 *            日
	 * @return int 一年中的第几个周
	 */
	public static int getWeekOfYear(String year, String month, String day) {
		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(new Integer(year).intValue(),
				new Integer(month).intValue() - 1, new Integer(day).intValue());
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 根据指定的年、月、日返回是星期几。1表示星期天、2表示星期一、7表示星期六。
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月，是从1开始的12结束
	 * @param day
	 *            日
	 * @return 星期几。1表示星期天、2表示星期一、7表示星期六。
	 */
	public static int getDayOfWeek(String year, String month, String day) {
		Calendar cal = new GregorianCalendar(new Integer(year).intValue(),
				new Integer(month).intValue() - 1, new Integer(day).intValue());
		return cal.get(Calendar.DAY_OF_WEEK);
	}


	/**
	 * 返回指定为年度为year月度为month的月份内，第weekOfMonth个星期的第dayOfWeek天。<br>
	 * 00 00 00 01 02 03 04 <br>
	 * 05 06 07 08 09 10 11<br>
	 * 12 13 14 15 16 17 18<br>
	 * 19 20 21 22 23 24 25<br>
	 * 26 27 28 29 30 31 <br>
	 * 2006年的第一个周的1到7天为：05 06 07 01 02 03 04 <br>
	 * 2006年的第二个周的1到7天为：12 13 14 08 09 10 11 <br>
	 * 2006年的第三个周的1到7天为：19 20 21 15 16 17 18 <br>
	 * 2006年的第四个周的1到7天为：26 27 28 22 23 24 25 <br>
	 * 2006年的第五个周的1到7天为：02 03 04 29 30 31 01 。本月没有就自动转到下个月了。
	 * 
	 * @param year
	 *            形式为yyyy <br>
	 * @param month
	 *            形式为MM,参数值在[1-12]。<br>
	 * @param weekOfMonth
	 *            在[1-6],因为一个月最多有6个周。<br>
	 * @param dayOfWeek
	 *            数字在1到7之间，包括1和7。1表示星期天，7表示星期六<br>
	 *            -6为星期日-1为星期五，0为星期六 <br>
	 * @return <type>int</type>
	 */
	public static int getDayofWeekInMonth(String year, String month,
			String weekOfMonth, String dayOfWeek) {
		Calendar cal = new GregorianCalendar();
		// 在具有默认语言环境的默认时区内使用当前时间构造一个默认的 GregorianCalendar。
		int y = new Integer(year).intValue();
		int m = new Integer(month).intValue();
		cal.clear();// 不保留以前的设置
		cal.set(y, m - 1, 1);// 将日期设置为本月的第一天。
		cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, new Integer(weekOfMonth)
				.intValue());
		cal.set(Calendar.DAY_OF_WEEK, new Integer(dayOfWeek).intValue());
		// System.out.print(cal.get(Calendar.MONTH)+" ");
		// System.out.print("当"+cal.get(Calendar.WEEK_OF_MONTH)+"\t");
		// WEEK_OF_MONTH表示当天在本月的第几个周。不管1号是星期几，都表示第一个周
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 根据制定的年月返回当前月份有多少天。
	 * 
	 * @param year
	 *            指定年
	 * @param month
	 *            指定月份从1到12
	 * @return
	 */
	public static int getDaysOfMonth(final String year, final String month) {
		int curyear = new Integer(year).intValue(); // 当前年份
		int curMonth = new Integer(month).intValue();// 当前月份
		int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
				31 };
		// 判断闰年的情况 ，2月份有29天；
		if ((curyear % 400 == 0)
				|| ((curyear % 100 != 0) && (curyear % 4 == 0))) {
			mArray[1] = 29;
		}
		if (curMonth == 12) {
			return mArray[0];
		}
		return mArray[curMonth - 1];
		// 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
		// 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
	}

	/**
	 * 获取指定年月的最后一天日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayInMonth(final String year, final String month) {
		int curyear = new Integer(year).intValue(); // 当前年份
		int curMonth = new Integer(month).intValue();// 当前月份
		GregorianCalendar gc = new GregorianCalendar(curyear, curMonth, 1);
		int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		daysInMonths[1] += (gc.isLeapYear(curyear) ? 1 : 0);
		int day= daysInMonths[curMonth - 1];
		return dateChangeFormat(Integer.toString(curyear) + "-"
				+ Integer.toString(curMonth) + "-"+day, "yyyy-MM-dd");
	}

	/**
	 * 获取指定年月的第一天日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayInMonth(final String year, final String month) {
		return dateChangeFormat(year + "-"
				+ month + "-01", "yyyy-MM-dd");
	}
	
	/**
	 * 得到两个日期之间的毫秒数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getMillisBetweenDates(Date startDate,Date endDate)
	{
		Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        
        return endCal.getTimeInMillis()-startCal.getTimeInMillis();
	}

}
