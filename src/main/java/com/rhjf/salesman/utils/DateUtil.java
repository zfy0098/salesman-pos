package com.rhjf.salesman.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	public static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

	public static String yyyyMMdd = "yyyyMMdd";
	
	public static String HHmmss = "HHmmss";
	
	
	public static String HHmm = "HH-mm";

	public static String yyyy_MM_dd = "yyyy-MM-dd";

	public static String yyyy_MM_ddHH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	

	/**
	 *   获取当前时间时间
	 * @param format
	 * @return
	 */
	public static String getNowTime(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
		
	}
	public static String getDateTimeTemp() {
		SimpleDateFormat d = new SimpleDateFormat();
		d.applyPattern(yyyyMMddHHmmss);
		Date nowdate = new Date();
		String str_date = d.format(nowdate);
		return str_date;
	}


	/**
	 *    获取指定当天日之前的几天的日期
	 * @param day
	 * @return
	 */
	public static String getDateAgo(String dateStr , int day) throws Exception{
		Calendar calendar1 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat(yyyy_MM_dd);
		Date date = sdf1.parse(dateStr);//初始日期
		calendar1.setTime(date);
		calendar1.add(Calendar.DATE, -day);
		String daysAgo = sdf1.format(calendar1.getTime());
		return  daysAgo;
	}


	/**
	 *   获取指定天数之后的日期
	 * @param day
	 * @return
	 */
//	public static String getDateAfter(int day){
//		Calendar calendar1 = Calendar.getInstance();
//		SimpleDateFormat sdf1 = new SimpleDateFormat(yyyyMMdd);
//		calendar1.add(Calendar.DATE, day);
//		String daysAlter = sdf1.format(calendar1.getTime());
//		return  daysAlter;
//	}
}
