package com.lpp.kiven.util;

import org.apache.commons.lang3.StringUtils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final String YYYYMMDDHHMMSS_PATTERN = "yyyyMMddHHmmss";
    
	/**
     * 日期转星期
     * 
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取两位年
     * @return
     */
    public static String getYear2(){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String year = sf.format(new Date()).substring(2, 4);
		return year;
    }
    
    /**
     * 把日期对象转换成字符串
     * @param aDteValue
     * @param aFmtDate
     */
    public static String dateToStr(Date aDteValue, String aFmtDate) {
        String strRtn = null;
        if (aFmtDate.length() == 0) {
            aFmtDate = "yyyy/MM/dd";
        }
        Format fmtDate = new SimpleDateFormat(aFmtDate);
        try {
            strRtn = fmtDate.format(aDteValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strRtn;
    }
    
    public static String date2Str(Date date, String format) {
    	if (date == null)
    	    return "";
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	return sdf.format(date);
        }

    public static int differentDays(String startTimeStr,String endTimeStr)
    {
        Date startTime = null;
        Date endTime = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            startTime = format.parse(startTimeStr);
            endTime = format.parse(endTimeStr);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(startTime);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(endTime);
            int day1= cal1.get(Calendar.DAY_OF_YEAR);
            int day2 = cal2.get(Calendar.DAY_OF_YEAR);

            int year1 = cal1.get(Calendar.YEAR);
            int year2 = cal2.get(Calendar.YEAR);
            if(year1 != year2)   //同一年
            {
                int timeDistance = 0 ;
                for(int i = year1 ; i < year2 ; i ++)
                {
                    if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                    {
                        timeDistance += 366;
                    }
                    else    //不是闰年
                    {
                        timeDistance += 365;
                    }
                }
                return timeDistance + (day2-day1) ;
            }
            else    //不同年
            {
                System.out.println("判断day2 - day1 : " + (day2-day1));
                return day2-day1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //2019-23-03 23:34:34
    public static String getMonthDay(String date){
        if(StringUtils.isBlank(date)){
            return "";
        }
        return date.substring(5,10);
    }

     /**
       * 把 Thu Dec 14 00:00:00 CST 2017 转换成自己想要的格式
       * @param date
       * @param pattern 为自己定义的类型如“yyyy-MM-dd”
       * @return
      */
     public static String dateStringFormat(String date, String pattern) {
         if(date == null || pattern == null) {
             return null;
         }
         try {
             Date formDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(date);
             return new SimpleDateFormat(pattern, Locale.CHINA).format(formDate);
         }
         catch (ParseException e) {
             e.printStackTrace();
         }
         return null;
 }
}
