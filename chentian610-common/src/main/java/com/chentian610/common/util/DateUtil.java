/**
 * <p>Title:时间工具包</p>
 * <p>Description:时间工具包</p>
 * <p>Copyright:Copyright (c) 2010</p>
 * <p>Company:九天音乐公司 </p>
 * <p>Date:2010-12-27</p>
 * <p>Modify:</p>
 * <p>Bug:</p>
 * @author com.chentian610
 * @version 1.0
 */
package com.chentian610.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {
	public static final String DATE_END = " 23:59:59";

	// 每分钟秒数
	private static final int SECONDS = 3600;
	
	// 每日小时数
	private static final int HOURS = 24;

	// 每秒毫秒数
	private static final int MILLISECONDS = 1000;

    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String Y_M_D_HM = "yyyy-MM-dd HH:mm";
    public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD = "yyyyMMdd";
    public static final String YMDHM = "yyyyMMddHHmm";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String ymd = "yyyy/MM/dd";
    public static final String ymd_HM = "yyyy/MM/dd HH:mm";
    public static final String ymd_HMS = "yyyy/MM/dd HH:mm:ss";
	
	/***************************************************************************
	 * 将date延长至当天23:59:59
	 * @param date 参数日期
	 * @return返回延长后的日期
	 */
	public static Date formatDateEnd(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strd = sdf.format(date) + DATE_END;
			sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
			try {
				date = sdf.parse(strd);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
		return null;
	}
    
	 /** 将date提前至当天00:00:00
	 * 
	 * @param date 参数日期
	 * @return返回延长后的日期
	 */
	public static Date formatDateStart(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strd = sdf.format(date) + " 00:00:00";
			sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
			try {
				date = sdf.parse(strd);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
		return null;
	}
	
	/**
     * 日期转换成字符串类型
     * @param date 转换日期
     * @param formatType 日期格式yyyy-MM-dd
     * @return
     */
    public static String formatDateToString(Date date, String formatType) {
        String strDate = null;
        try {
            DateFormat format = new SimpleDateFormat(formatType);
            strDate = format.format(date);
            return strDate;
        } catch (Exception ex) {
            return strDate;
        }
    }
	
	/**
	 * 计算两日期之间相差天数
	 * 
	 * @param date1
	 *            , date2不分大小先后
	 * @return 返回两日期之间相差的天数
	 */
	public static int dayNum(Date date1, Date date2) throws ParseException {
		return dayNum(date1, date2, true);
	}
	
	/**
	 * 计算两日期之间相差天数
	 * 
	 * @param date1
	 * @param date2 不分大小先后
	 * @param flag 是否考虑到精确时间，如果考虑那么要大于24小时才算一天，不考虑就直接计算天数
	 * @return 返回两日期之间相差的天数
	 */
	public static int dayNum(Date date1, Date date2, boolean flag) throws ParseException {
		if (flag) return Math.abs((int) ((date2.getTime() - date1.getTime()) / HOURS / SECONDS / MILLISECONDS));
		else return Math.abs((int) ((date2.getTime() / HOURS / SECONDS / MILLISECONDS) - (date1.getTime() / HOURS / SECONDS / MILLISECONDS)));
	}
    
	/**
     * 日期计算方法gxk
     * @param date 计算对象
     * @param filed 计算域 Calendar.YEAR:年 Calendar.MONTH:月 Calendar.DAY_OF_MONTH:日
     * @param val 日期加减数量
     * @return
     */
    public static Date calcDate(Date date, int filed, int val) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(filed, val);
        cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), cl.get(Calendar.DAY_OF_MONTH));
        return cl.getTime();
    }
	
    
	  /**
     * 取得这个礼拜第一天:礼拜天
     * @param date
     * @return
     */
	  public static Date firstDayOfWeek(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int value = cal.getActualMinimum(1);
	        cal.set(Calendar.DAY_OF_WEEK, value);
	        Date day = cal.getTime();
			return formatDateStart(day);
	    }
	  
	 /**
     * 取得这个礼拜第最后一天：礼拜六
     * @param date
     * @return
     */
	  public static Date lastDayOfWeek(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int value = cal.getActualMaximum(Calendar.DAY_OF_WEEK);
	        cal.set(Calendar.DAY_OF_WEEK, value);
	        Date day = cal.getTime();
			return formatDateEnd(day);
	    }
    
	  /**
     * 取得当月第一天	
     * @param date
     * @return
     * @author Gengxk
     */
	  public static Date firstDayOfMonth(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int value = cal.getActualMinimum(1);
	        cal.set(Calendar.DAY_OF_MONTH, value);
	        return formatDateStart(cal.getTime());
	    }
	
    /**
     * 取得当月最后1天	
     * @param date
     * @return
     * @author Gengxk
     */
	  public static Date lastDayOfMonth(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        cal.set(Calendar.DAY_OF_MONTH, value);
	        return formatDateEnd(cal.getTime());
	    }
	  
	/**
	 * 得到延长天数后的日期
	 * 
	 * @param date 原始日期
	 * @param days 需要延长的天数
	 * @return 返回延长天数后的日期
	 */
	public static Date addDoubleDay(Date date, double days) {
		Calendar calendar = Calendar.getInstance();
		long msPerDay = 1000 * 60 * 60 * 24; // 一天的毫秒数
		long msTarget = date.getTime(); // 返回从一个特定的日期（1970。。）到现在经过的毫秒数。
		long msSum = (long) (msTarget + (msPerDay * days));
		Date result = new Date();
		result.setTime(msSum); // 根据毫秒设置日期
		calendar.setTime(result);
		return calendar.getTime();
	}

	/**
	 * 返回yyyy-MM-dd格式化后的日期
	 * 
	 * @param date
	 * @return 返回格式化后的日期
	 */
	public static Date dateFormat(Date date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = dateFormat.parse(dateFormat.format(date));
		return date;
	}
	
	/**
	 * 返回yyyy-MM-dd HH:mm:ss格式化后的日期
	 * 
	 * @param strDate
	 * @return 返回格式化后的日期
	 */
	public static Date dateTimeFormat(String strDate) {
		if(StringUtil.isEmpty(strDate)) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        Date date = null;
        try{
            date = sdf.parse(strDate.toString()) ;
        }catch(Exception e){
            e.printStackTrace() ;
        }
        return date;
	}

	 /** 返回yyyy-MM格式化后的日期
	 * 
	 * @param strDate
	 * @return 返回格式化后的日期
	 */
	public static Date timeFormat(String strDate) {
		if(StringUtil.isEmpty(strDate)) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM") ;
       Date date = null;
       try{
           date = sdf.parse(strDate) ;
       }catch(Exception e){
           e.printStackTrace() ;
       }
       return date;
	}
	 
	/**
	 * 比较两个日期大小
	 * yyyy-MM-dd比较到天
	 * @param date1
	 *            , date2
	 * @return 如果date1在前返回-1，如果相等返回0，如果date1在后返回1
	 */
	public static int compDate(Date date1, Date date2) throws ParseException {
		return dateFormat(date1).compareTo(dateFormat(date2));
	}
	
	/**
	 * 比较两个日期大小
	 * yyyy-MM-dd HH:mm:ss比较到秒
	 * @param date1
	 *            , date2
	 * @return 如果date1在前返回-1，如果相等返回0，如果date1在后返回1
	 */
	public static int compareDate(Date date1, Date date2) throws ParseException {
		return date1.compareTo(dateFormat(date2));
	}
	
	/**
	 * 返回yyyyMM格式化后的日期
	 * 
	 * @param date
	 * @return 返回格式化后的日期
	 */
	public static Long dateFormatMonth(Date date)  {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		Long l= Long.valueOf(dateFormat.format(date));
		return l;
	}
	
	public static Date dateTimeFormatByString(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss") ;
        Date date = null;
        try{
            date = sdf.parse(strDate.toString()) ;
        }catch(Exception e){
            e.printStackTrace() ;
        }
        return date;
	}
	
	/**
	 * 得到延长天数后的时间
	 * 
	 * @param date 原始时间
	 * @param day 需要延长的天数
	 * @return 返回延长天数后的时间
	 */
	public static Date addDay(Date date, int day)  {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}
	
	/**
	 * 两日期月份差值
	 */
	public static int monthMinus(Date start, Date end){
		Calendar d1 = Calendar.getInstance();
		d1.setTime(start);
		int sMonth = d1.get(Calendar.MONTH);
		int sYear = d1.get(Calendar.YEAR);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(end);
		int eYear = d2.get(Calendar.YEAR);
		int eMonth = d2.get(Calendar.MONTH);
		int monthMinus = 12*(eYear - sYear) + eMonth - sMonth;
		return monthMinus;
	}
	
	/**
     * 取得某月第一天	
     */
	  public static Date firstDayOfOneMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		int value = cal.getActualMinimum(1);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return cal.getTime();
	  }
	  
	/**
     * 取得上一个月YYYY-MM	
     */
	  public static String lastMonth(String Month) {
		  int iYear = Integer.parseInt(Month.substring(0,4));
		  String sMonth = Month.substring(5);
		  int iMonth = Integer.parseInt(Month.substring(5));
		  if ("01".equals(sMonth)) return (iYear-1)+"-12";
		  if (iMonth >10) return iYear+"-"+(iMonth-1);
		  return iYear+"-0"+(iMonth-1);
	  }
	  
	/**
     * 取得下一个月YYYY-MM	
     */
	  public static String nextMonth(String Month) {
		  int iYear = Integer.parseInt(Month.substring(0,4));
		  String sMonth = Month.substring(5);
		  int iMonth = Integer.parseInt(Month.substring(5));
		  if ("12".equals(sMonth)) return (iYear+1)+"-01";
		  if (iMonth > 9) return iYear+"-"+(iMonth+1);
		  return iYear+"-0"+(iMonth+1);
	  }
	  
	/**
	 * 得到某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 获取一定分钟后的时间
	 * @param date
	 * @param minuteCount
	 * @return
	 * @author com.chentian610
	 */
	 public static Date addMinute(Date date, int minuteCount) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.MINUTE, minuteCount);
		  return  calendar.getTime();
	 }

	/**
	 * 获取一定小时后的时间
	 * @param date
	 * @param hourCount
	 * @return
	 * @author com.chentian610
	 */
	 public static Date addHour(Date date, int hourCount) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.HOUR, hourCount);
		  return  calendar.getTime();
	 }
	 
	/**
	 * 获取一定月份后的时间
	 * @param date
	 * @param monthCount
	 * @return
	 * @author com.chentian610
	 */
	 public static Date addMonth(Date date, int monthCount) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.MONTH, monthCount);
		  return  calendar.getTime();
	 }
	 
	/**
	 * 获取一定年数后的时间
	 * @param date
	 * @param yearCount
	 * @return
	 * @author com.chentian610
	 */
	 public static Date addYear(Date date, int yearCount) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.YEAR, yearCount);
		  return  calendar.getTime();
	 }
	 
	 /**
	 * 获取今天是第几月
	 * @param date
	 * @author com.chentian610
	 */
	 public static int getMonthOfYear(Date date) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  return calendar.get(Calendar.MONTH)+1;
	 }

	 /**
	  * 根据传入的日期，获取当前日期所在周的第一天（星期一）
	  * @param date
	  * @return
	  */
	 public static String getFirstDayOfWeek(Date date) {
		  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  if (cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY)
		  {
			  cal.add(Calendar.DATE, -6);
			  return df.format(cal.getTime());  
		  } else {
			  cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
			  return df.format(cal.getTime());
		  }
	 }
	 
	 public static String getFirstDayOfWeek(Date date, String format) {
		  SimpleDateFormat df = new SimpleDateFormat(format);
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  if (cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY)
		  {
			  cal.add(Calendar.DATE, -6);
			  return df.format(cal.getTime());  
		  } else {
			  cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
			  return df.format(cal.getTime());
		  }
	 }
	 
	 /**
	  * 根据传入的日期，获取当前日期所在周的第一天（星期一）
	  * @param day
	  * @return
	  */
	 public static String getFirstDayOfWeek(String day) {
		  Date date = smartFormat(day);
		  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  if (cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY)
		  {
			  cal.add(Calendar.DATE, -6);
			  return df.format(cal.getTime());  
		  } else {
			  cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
			  return df.format(cal.getTime());
		  }
	 }
	 
	 /**
	  * 根据传入的日期，获取当前日期所在年的第一天
	  * @param day
	  * @return
	  */
	 public static String getFirstDayOfYear(String day) {
		  return day.substring(0,4)+"0101";
	 }
	 
	 public static Date getMonthFirstDay(Date date) {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.set(Calendar.DAY_OF_MONTH,1);
		 return cal.getTime();
	 }
		
	 public static Date getMonthLastDay(Date date) {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		 return cal.getTime();
	 }
		
	 public static Date getYearLastDay(Date date) {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.set(cal.get(Calendar.YEAR), 11, 31);
		 return cal.getTime();
	 }
	 
	 public static String getFirstDayOfMonth(Date date) {
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.set(Calendar.DATE, 1); //获取本月第一天的日期
		 return df.format(cal.getTime());
	 }
		
	 public static String getFirstDayOfMonth(String day) {
		 Date date = smartFormat(day);
		 SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		 Calendar cal= Calendar.getInstance();
		 cal.setTime(date);
		 cal.set(Calendar.DATE, 1);
		 return df.format(cal.getTime());
	 }
		
	 public static String getFirstDayOfMonth(Date date, String format) {
		 SimpleDateFormat df = new SimpleDateFormat(format);
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.set(Calendar.DATE, 1); //获取本月第一天的日期
		 return df.format(cal.getTime());
	 }
		
	 public static String getFirstDayOfYear(Date date) {
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.set(Calendar.DAY_OF_YEAR, 1); //获取当前年第一天的日期
		 return df.format(cal.getTime());
	 }
	 
	 public static void main(String[]args) throws ParseException {
		 Long a = Long.parseLong("600");
		 List<String> list = new ArrayList<String>();
		 list.addAll(null);
		 for (int i=0;i<501;i++) {
			 list.add(i+"");
		 }
		 while (list.size()>99) {
			 System.out.println(list.get(0)+","+list.get(99));
			 list.removeAll(list.subList(0, 100));
		 }
		 if (ListUtil.isNotEmpty(list))
			 System.out.println("HEHE:"+list.get(0));
	 }
	 
	 /**
	 * 获取本周在一年中是第几周
	 * @param date
	 * @author com.chentian610
	 */
	 public static int getWeekOfYear(Date date) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  return calendar.get(Calendar.WEEK_OF_YEAR);
	 }
	 
	 /**
	 * 获取学年以及学期标记
	 * @param date
	 * @author com.chentian610
	 */
	 public static String getYearAndTerm(Date date) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  int year = calendar.get(Calendar.YEAR);
		  int month = calendar.get(Calendar.MONTH)+1;
		  if (month>8) return year+"UP";
		  if ((month==2) && (calendar.get(Calendar.DAY_OF_MONTH)<16)) return (year-1)+"UP";
		  if (month==1)  return (year-1)+"UP";
		  return (year-1)+"DOWN";
	 }
	 
	 /**
	 * 获取当前时间的所处学年
	 * @param date
	 * @author com.chentian610
	 */
	 public static int getYearOfTerm(Date date) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  int year = calendar.get(Calendar.YEAR);
		  int month = calendar.get(Calendar.MONTH)+1;
		  if (month>8) return year;
		  else return year-1;
	 }
	 
	 /**
	 * @param date
	 * @return 星期一返回1，星期二返回2，星期天返回7
	 * @author com.chentian610
	 */
	 public static int getWeek(Date date) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  int week = calendar.get(Calendar.DAY_OF_WEEK);
		  if (week == 1) return 7;
		  else return week-1;
	 }

	 /**
	 * @param date
	 * @return 以数字形式返回日期的几号
	 * @author com.chentian610
	 */
	 public static int getDayOfMonth(Date date) {
		  Calendar calendar= Calendar.getInstance();
		  calendar.setTime(date);
		  return calendar.get(Calendar.DAY_OF_MONTH);
	 } 
	
	/**
	 * 比较两个日期的时间差，返回小时格式
	 * @param startDate
	 * @param endDate
	 * @return 
	 */
	public static double hourCompare(Date startDate, Date endDate){
		double starttime = startDate.getTime()/ SECONDS /MILLISECONDS;
		double endtime = endDate.getTime()/ SECONDS /MILLISECONDS;
		return endtime-starttime;
	}
		
	/**
	 * 比较两个日期的时间差，返回分钟格式
	 * @param startDate
	 * @param endDate
	 * @return 
	 */
	public static double miniteCompare(Date startDate, Date endDate){
		double starttime = startDate.getTime() / 60 / MILLISECONDS;
		double endtime = endDate.getTime() / 60 / MILLISECONDS;
		return endtime-starttime;
	}
	
	/**
	 * 比较两个日期的时间差，返回秒格式
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static double secondCompare(Date startDate, Date endDate){
		double starttime = startDate.getTime() / MILLISECONDS;
		double endtime = endDate.getTime() / MILLISECONDS;
		return endtime-starttime;
	}

    /**
     * 智能转换日期
     *
     * @param date
     * @return
     */
    public static String smartFormat(Date date) {
        String dateStr = null;
        if (date == null) {
            dateStr = "";
        } else {
            try {
                dateStr = formatDate(date, Y_M_D_HMS);
                //时分秒
                if (dateStr.endsWith(" 00:00:00")) {
                    dateStr = dateStr.substring(0, 10);
                }
                //时分
                else if (dateStr.endsWith("00:00")) {
                    dateStr = dateStr.substring(0, 16);
                }
                //秒
                else if (dateStr.endsWith(":00")) {
                    dateStr = dateStr.substring(0, 16);
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
            }
        }
        return dateStr;
    }

    /**
     * 智能转换日期
     *
     * @param text
     * @return
     */
    public static Date smartFormat(String text) {
        Date date = null;
        try {
            if (text == null || text.length() == 0) {
                date = null;
            } else if (text.length() == 8) {
                date = formatStringToDate(text, YMD);
            } else if (text.length() == 10) {
                date = formatStringToDate(text, Y_M_D);
            } else if (text.length() == 13) {
                date = new Date(Long.parseLong(text));
            } else if (text.length() == 16) {
                date = formatStringToDate(text, Y_M_D_HM);
            } else if (text.length() == 19) {
                date = formatStringToDate(text, Y_M_D_HMS);
            } else {
                throw new IllegalArgumentException("日期长度不符合要求!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("日期转换失败!");
        }
        return date;
    }

    /**
     * 获取当前日期
     * @param format
     * @return
     * @throws Exception
     */
    public static String getNow(String format) {
        try {
			return formatDate(new Date(), format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * 格式化日期格式
     * @param format
     * @return
     * @throws Exception
     */
    public static String getNow(String format, Date date) {
        try {
			return formatDate(date, format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * 格式化日期格式
     * @param argDate
     * @param argFormat
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date argDate, String argFormat) {
        if (argDate == null) return "";
        if (StringUtil.isEmpty(argFormat)) {
            argFormat = Y_M_D;
        }
        SimpleDateFormat sdfFrom = new SimpleDateFormat(argFormat);
        return sdfFrom.format(argDate).toString();
    }

    /**
     * 把字符串格式化成日期
     *
     * @param DateStr
     * @param Formattype
     * @return
     */
    public static Date formatStringToDate(String DateStr, String Formattype){
    	if (StringUtil.isEmpty(DateStr)) return null;
        DateFormat fmt =new SimpleDateFormat(Formattype);
        try {
			Date date = fmt.parse(DateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * 年级，班级名称更新时间（redis中的失效时间）
     * @param smonth
     * @param sdate
     * @return
     */
    public static Date teamnameUpdateTime(int smonth, int sdate){
    	Calendar calendar= Calendar.getInstance();
		Date date=new Date();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		if (month>8) year++;
		calendar.set(year, smonth-1, sdate);
		return calendar.getTime();
    }
}