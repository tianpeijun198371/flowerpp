/**
 * DateTimeUtil.java.
 * User: fengch  Date: 2004-4-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;


public class DateTimeUtil
{
        private static Log log = LogFactory.getLog(DateTimeUtil.class);

        public static final long SECOND = 1000;
        public static final long MINUTE = SECOND * 60;
        public static final long HOUR = MINUTE * 60;
        public static final long DAY = HOUR * 24;
        public static final long WEEK = DAY * 7;

        private static String defaultDatePattern = "yyyy-MM-dd";
        private static String defaultDateTime24PatternYMDHMS = "yyyy-MM-dd HH:mm:ss";
        private static String defaultDateTime24PatternYMDHM = "yyyy-MM-dd HH:mm";
        private static String timePattern = "HH:mm";
        private static String simpleFormat = "yyyyMMddHHmmss-";
        private static String defaultDateDayPattern = "MM-dd-yyyy hh24:mi:ss";

        /**
         * 按yyyy-MM-dd格式解析字符串，输出日期
         * <p/>
         *
         * @return Date
         * @throws
         */
        public static Date toDateYMD(String sourcedate)
        {
                DateFormat dateformat;
                if (sourcedate == null)
                {
                        return null;
                }
                Date newDate = null;
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(defaultDatePattern);
                try
                {
                        newDate = sdf.parse(sourcedate);

                }
                catch (Exception e)
                {
                        log.debug(e.toString());
                }
                return newDate;
        }

        /**
         * 按yyyy-MM-dd HHmm:ss格式解析字符串，输出日期
         * <p/>
         *
         * @return Date
         * @throws
         */
        public static Date toDateYMDHMS(String sourcedate)
        {
                DateFormat dateformat;
                if (sourcedate == null)
                {
                        return null;
                }
                Date newDate = null;
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(defaultDateTime24PatternYMDHMS);
                try
                {
                        newDate = sdf.parse(sourcedate);

                }
                catch (Exception e)
                {
                        log.debug(e.toString());
                }
                return newDate;
        }
        /**
         * 按yyyy-MM-dd HH:mm格式解析字符串，输出日期
         * <p/>
         *
         * @return Date
         * @throws
         */
        public static Date toDateYMDHM(String sourcedate)
        {
                DateFormat dateformat;
                if (sourcedate == null)
                {
                        return null;
                }
                Date newDate = null;
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(defaultDateTime24PatternYMDHM);
                try
                {
                        newDate = sdf.parse(sourcedate);

                }
                catch (Exception e)
                {
                        log.debug(e.toString());
                }
                return newDate;
        }

        /**
         * 按yyyy-MM-dd HH:mm:ss格式日期，输出字符串
         * <p/>
         *
         * @return String
         * @throws
         */
        public static String toStringYMDHMS(Date sourcedate)
        {
                DateFormat dateformat;
                if (sourcedate == null)
                {
                        return "";
                }
                dateformat = new SimpleDateFormat(defaultDateTime24PatternYMDHMS);
                //System.out.println("dd-MM-yyyy HH:mm:ss " + dateformat.format(date));
                return dateformat.format(sourcedate);
        }

        /**
         * 输按yyyy-MM-dd HH:mm格式日期，输出字符串
         * <p/>
         *
         * @return String
         * @throws
         */
        public static String toStringYMDHM(Date sourcedate)
        {
                DateFormat dateformat;
                if (sourcedate == null)
                {
                        return "";
                }
                dateformat = new SimpleDateFormat(defaultDateTime24PatternYMDHM);
                //System.out.println("dd-MM-yyyy HH:mm:ss " + dateformat.format(date));
                return dateformat.format(sourcedate);
        }

        /**
         * 按yyyy-MM-dd格式日期，输出字符串
         * <p/>
         *
         * @return string
         * @throws
         */
        public static String toStringYMD(Date sourcedate)
        {
                DateFormat dateformat;
                if (sourcedate == null)
                {
                        return "";
                }
                dateformat = new SimpleDateFormat(defaultDatePattern);
                //System.out.println("dd-MM-yyyy HH:mm:ss " + dateformat.format(date));
                return dateformat.format(sourcedate);
        }


        /*
        * 针对传入的date,最近n天的那天的日期
        * <br>
        * @param n  n=0:今天;n=n-1:最近n天.
        */
        public static Date getRecentDay(Date d, int n)
        {
                int specdate_day;
                int specdate_month;
                int specdate_year;
                Calendar cal_spec = Calendar.getInstance();
                cal_spec.setTime(d);
                cal_spec.add(Calendar.DATE, -n);

                specdate_day = cal_spec.get(Calendar.DAY_OF_MONTH);
                specdate_month = cal_spec.get(Calendar.MONTH);
                specdate_year = cal_spec.get(Calendar.YEAR);

                Calendar cal_this = new GregorianCalendar(specdate_year,
                        specdate_month, specdate_day);

                return cal_this.getTime();
        }

        /*
        * 针对传入的date,最近n(天/周/月/年)的那天的日期
        * n>0表示以前,n<0表示以后
        * @param type:d天w周m月y年
        */
        public static Date getRecentDate(Date d, int n, String type)
        {
                Date newDate = null;

                if (type.equals("d"))
                {
                        newDate = getRecentDay(d, n);
                }
                else if (type.equals("w"))
                {
                        newDate = getRecentDay(d, n * 7);
                }
                else if (type.equals("m"))
                {
                        int n1 = n / 12;
                        n = n % 12;

                        String datetime = FormatDateToWeb4(d);
                        String year = datetime.substring(0, 4);
                        year = String.valueOf(Integer.parseInt(year) - n1);

                        String month = datetime.substring(4, 6);
                        int monthInt = Integer.parseInt(month) - n;

                        if (monthInt < 1)
                        {
                                monthInt = monthInt + 12;
                                year = String.valueOf(Integer.parseInt(year) - 1);
                        }
                        else if (monthInt > 12)
                        {
                                monthInt = monthInt - 12;
                                year = String.valueOf(Integer.parseInt(year) + 1);
                        }

                        month = String.valueOf(monthInt);

                        String day = datetime.substring(6, 8);
                        String hour = datetime.substring(8, 10);
                        String minute = datetime.substring(10, 12);
                        String second = datetime.substring(12, 14);
                        newDate = toDate(month, day, year, hour, minute, second);
                }
                else if (type.equals("y"))
                {
                        String datetime = FormatDateToWeb4(d);
                        String year = String.valueOf(Integer.parseInt(datetime.substring(
                                0, 4)) - n);
                        String month = datetime.substring(4, 6);
                        String day = datetime.substring(6, 8);
                        String hour = datetime.substring(8, 10);
                        String minute = datetime.substring(10, 12);
                        String second = datetime.substring(12, 14);
                        newDate = toDate(month, day, year, hour, minute, second);
                }

                return newDate;
        }

        /**
         * to formate date using the pattern.
         * about the pattern,Please see:java api-SimpleDateFormat
         *
         * @param d
         * @param pattern
         * @return
         */
        public static String format(Date d, String pattern)
        {
                if (d == null)
                {
                        if (pattern.equals("HH") || pattern.equals("mm"))
                        {
                                return "00";
                        }
                        else
                        {
                                return "";
                        }
                }
                else
                {
                        SimpleDateFormat formatter = new SimpleDateFormat(pattern,
                                Locale.getDefault());
                        String formattedDate = formatter.format(d);

                        return formattedDate;
                }
        }

        /*
        * to formate date for inserting date to database:oracle
        */
        public static String FormatDate_Ora(Date d)
        {
                //define variables
                Date currentDate;
                SimpleDateFormat formatter;
                String formattedDate;
                //prepare values for variables
                currentDate = d;
                formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss",
                        Locale.getDefault());
                formattedDate = formatter.format(currentDate);

                return formattedDate;
        }

        public static String FormatDateToWeb1(Date d)
        {
                String formattedDate = "";

                if (d != null)
                {
                        Date currentDate = d;
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
                                Locale.getDefault());
                        formattedDate = formatter.format(currentDate);
                }

                return formattedDate;
        }

        public static String FormatDateToWeb2(Date d)
        {
                Date currentDate = d;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                        Locale.getDefault());
                String formattedDate = formatter.format(currentDate);

                return formattedDate;
        }

        public static String FormatDateToWeb3(Date d)
        {
                Date currentDate = d;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        Locale.getDefault());
                String formattedDate = formatter.format(currentDate);

                return formattedDate;
        }

        public static String FormatDateToWeb4(Date d)
        {
                Date currentDate = d;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss",
                        Locale.getDefault());
                String formattedDate = formatter.format(currentDate);

                return formattedDate;
        }

        /*
        * to formate date for inserting date to database:sqlserver
        */
        public static String FormatDate_SQL(Date d)
        {
                //define variables
                Date currentDate;
                SimpleDateFormat formatter;
                String formattedDate;

                //prepare values for variables
                currentDate = d;

                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        Locale.getDefault());
                formattedDate = formatter.format(currentDate);

                return formattedDate;
        }

        /*
        * to merge sql date and sql time to util date.
        */
        public static java.util.Date toDate(java.sql.Date d, java.sql.Time t)
        {
                if ((d == null) || (t == null))
                {
                        return null;
                }

                //define variables;
                java.sql.Date sdate;
                java.sql.Time stime;

                java.util.Calendar c;

                String dtString;

                int year;
                int month;
                int day;
                int hour;
                int minute;
                int second;

                //prepare values for variables
                sdate = d;
                stime = t;

                c = Calendar.getInstance();

                dtString = sdate.toString() + "," + stime.toString();

                year = Integer.parseInt(dtString.substring(0, 4));
                month = Integer.parseInt(dtString.substring(5, 7)) - 1;
                day = Integer.parseInt(dtString.substring(8, 10));

                hour = Integer.parseInt(dtString.substring(11, 13));
                minute = Integer.parseInt(dtString.substring(14, 16));
                second = Integer.parseInt(dtString.substring(17, 19));

                c.set(year, month, day, hour, minute, second);

                return c.getTime();
        }

        /**
         * 直接将数组转化为需要的日期类型
         *
         * @param alldate
         * @return
         */
        public static java.util.Date toDate(String[] alldate)
        {
                if (alldate == null)
                {
                        return new Date();
                }
                else
                {
                        return DateTimeUtil.toDate(alldate[1], alldate[2], alldate[0],
                                alldate[3], alldate[4], "0");
                }
        }

        /**
         * Makes a Date from separate Strings for month, day, year, hour, minute, and second.
         *
         * @param monthStr  The month String
         * @param dayStr    The day String
         * @param yearStr   The year String
         * @param hourStr   The hour String
         * @param minuteStr The minute String
         * @param secondStr The second String
         * @return A Date made from separate Strings for month, day, year, hour, minute, and second.
         */
        public static java.util.Date toDate(String monthStr, String dayStr,
                                            String yearStr, String hourStr, String minuteStr, String secondStr)
        {
                int month;
                int day;
                int year;
                int hour;
                int minute;
                int second;

                try
                {
                        month = Integer.parseInt(monthStr);
                        day = Integer.parseInt(dayStr);
                        year = Integer.parseInt(yearStr);
                        hour = Integer.parseInt(hourStr);
                        minute = Integer.parseInt(minuteStr);
                        second = Integer.parseInt(secondStr);
                }
                catch (Exception e)
                {
                        return null;
                }

                return toDate(month, day, year, hour, minute, second);
        }

        /**
         * Makes a Date from separate ints for month, day, year, hour, minute, and second.
         *
         * @param month  The month int
         * @param day    The day int
         * @param year   The year int
         * @param hour   The hour int
         * @param minute The minute int
         * @param second The second int
         * @return A Date made from separate ints for month, day, year, hour, minute, and second.
         */
        public static java.util.Date toDate(int month, int day, int year, int hour,
                                            int minute, int second)
        {
                Calendar calendar = Calendar.getInstance();

                try
                {
                        calendar.set(year, month - 1, day, hour, minute, second);
                }
                catch (Exception e)
                {
                        return null;
                }

                return new java.util.Date(calendar.getTime().getTime());
        }

        /**
         * 按年月日返回日期。<br>
         *
         * @param year
         * @param month
         * @param day
         * @return
         */
        public static java.util.Date toDate(int year, int month, int day)
        {
                Calendar calendar = Calendar.getInstance();

                try
                {
                        calendar.set(year, month - 1, day);
                }
                catch (Exception e)
                {
                        return null;
                }

                return new java.util.Date(calendar.getTime().getTime());
        }

        public static java.util.Date toDate(String date, String token)
        {
                int year = 0;
                int month = 0;
                int day = 0;
                List temps = StringUtil.split(date, token);

                if ((temps == null) || (temps.size() != 3))
                {
                        return null;
                }
                else
                {
                        if (((String) temps.get(0) != null) &&
                                !((String) temps.get(0)).equals(""))
                        {
                                year = Integer.parseInt((String) temps.get(0));
                        }

                        if (((String) temps.get(1) != null) &&
                                !((String) temps.get(1)).equals(""))
                        {
                                month = Integer.parseInt((String) temps.get(1));
                        }

                        if (((String) temps.get(2) != null) &&
                                !((String) temps.get(2)).equals(""))
                        {
                                day = Integer.parseInt((String) temps.get(2));
                        }
                }

                Calendar calendar = Calendar.getInstance();

                try
                {
                        calendar.set(year, month - 1, day, 0, 0, 0);
                }
                catch (Exception e)
                {
                        return null;
                }

                return new java.util.Date(calendar.getTime().getTime());
        }

        //datetime="2004-08-31 12:14:15"
        public static java.util.Date toDate(String datetime)
        {
                Date d = null;

                if ((datetime != null) && (datetime.length() >= 19))
                {
                        int month;
                        int day;
                        int year;
                        int hour;
                        int minute;
                        int second;

                        //Calendar calendar = Calendar.getInstance();
                        try
                        {
                                year = Integer.parseInt(datetime.substring(0, 4));
                                month = Integer.parseInt(datetime.substring(5, 7));
                                day = Integer.parseInt(datetime.substring(8, 10));
                                hour = Integer.parseInt(datetime.substring(11, 13));
                                minute = Integer.parseInt(datetime.substring(14, 16));
                                second = Integer.parseInt(datetime.substring(17, 19));
                                d = toDate(month, day, year, hour, minute, second);
                        }
                        catch (Exception e)
                        {
                                return null;
                        }
                }

                return d;
        }

        /**
         * to parse a date according to the string(yyyy-MM-dd)
         *
         * @param dateString The parseed string
         * @return A Date
         */
        public static java.util.Date parseDate(String dateString)
        {
                Date date = null;

                try
                {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                        // Parse the previous string back into a Date.
                        ParsePosition pos = new ParsePosition(0);
                        date = formatter.parse(dateString, pos);
                }
                catch (Exception ex)
                {
                }

                return date;
        }

        /**
         * to parse a date according to the string(yyyy-MM-dd hh:mm:ss)
         *
         * @param dateTimeString The parseed string
         * @return A Date
         */
        public static java.util.Date parseDateTime(String dateTimeString)
        {
                Date date = null;

                try
                {
                        SimpleDateFormat formatter = new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss");

                        // Parse the previous string back into a Date.
                        ParsePosition pos = new ParsePosition(0);
                        date = formatter.parse(dateTimeString, pos);
                }
                catch (Exception ex)
                {
                }

                return date;
        }

        /**
         * @param dateTimeString
         * @return "yyyy-MM-dd hh:mm:ss"->"yyyyMMddhhmmss"
         *         "yyyy-MM-dd hh:mm"->"yyyyMMddhhmm"
         *         "yyyy-MM-dd"->"yyyyMMdd"
         */
        public static String FormatDateTime1(String dateTimeString)
        {
                int len = dateTimeString.length();
                StringBuffer out = new StringBuffer();

                for (int i = 0; i < len; i++)
                {
                        char c = dateTimeString.charAt(i);

                        if ((c != '-') && (c != ' ') && (c != ':'))
                        {
                                out.append(c);
                        }
                }

                return out.toString();
        }

        /**
         * @param dateTimeString
         * @return "yyyyMMddhhmmss"->"yyyy-MM-dd hh:mm:ss"
         *         "yyyyMMddhhmm"->"yyyy-MM-dd hh:mm"
         *         "yyyyMMdd"->"yyyy-MM-dd"
         */
        public static String FormatDateTime2(String dateTimeString)
        {
                int len = 0;

                if (dateTimeString != null)
                {
                        len = dateTimeString.length();
                }

                String res = "";

                if (len == 8)
                {
                        res = dateTimeString.substring(0, 4) + "-" +
                                dateTimeString.substring(4, 6) + "-" +
                                dateTimeString.substring(6, 8);
                }
                else if (len == 12)
                {
                        res = dateTimeString.substring(0, 4) + "-" +
                                dateTimeString.substring(4, 6) + "-" +
                                dateTimeString.substring(6, 8) + " " +
                                dateTimeString.substring(8, 10) + ":" +
                                dateTimeString.substring(10, 12);
                }
                else if (len == 14)
                {
                        res = dateTimeString.substring(0, 4) + "-" +
                                dateTimeString.substring(4, 6) + "-" +
                                dateTimeString.substring(6, 8) + " " +
                                dateTimeString.substring(8, 10) + ":" +
                                dateTimeString.substring(10, 12) + ":" +
                                dateTimeString.substring(12, 14);
                }

                return res;
        }

        /**
         * 返回停留时间.
         * eg.5天 5小时 5分 5秒
         *
         * @param milliseconds
         * @return
         */
        public static String getDateTimeDescription(long milliseconds)
        {
                String s = "";

                int x = 0;

                if (milliseconds >= DAY)
                {
                        x = (int) (milliseconds / DAY);
                        s += (x + "天 ");
                        milliseconds = milliseconds % DAY;
                }

                if (milliseconds >= HOUR)
                {
                        x = (int) (milliseconds / HOUR);
                        s += (x + "小时 ");
                        milliseconds = milliseconds % HOUR;
                }

                if (milliseconds >= MINUTE)
                {
                        x = (int) (milliseconds / MINUTE);
                        s += (x + "分 ");
                        milliseconds = milliseconds % MINUTE;
                }

                if (milliseconds >= SECOND)
                {
                        x = (int) (milliseconds / SECOND);
                        s += (x + "秒 ");
                }

                if (milliseconds < SECOND)
                {
                        s = "0 天 0 小时 0 分 0 秒";
                }

                return s;
        }

        /**
         * 判断中间时间参数是否是在两边的参数时间点中间
         */
        public static boolean isTestTime(Date begTime, Date midTime, Date endTime)
        {
                if ((midTime.getTime() > begTime.getTime()) &&
                        (midTime.getTime() < endTime.getTime()))
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }

        /**
         * 【功能】日期的加减；
         *
         * @param date 系统时间；
         * @param type 加减的类型 D 日期 M 月份 Y 年；
         * @param into 加减的数量；
         * @return String 返回时间
         */
        public static String addDateTime(Date date, String type, int into)
        {
                //java.util.Date jj = new java.util.Date();
                //String date_temp = DateChange.getSysENDate();
                //(jj.getYear() + 1900) + "/" + (jj.getMonth() + 1) + "/" +jj.getDate();
                //System.out.println(date);
                GregorianCalendar grc = new GregorianCalendar();
                grc.setTime(date);

                if (type.equals("D"))
                {
                        grc.add(GregorianCalendar.DATE, into);
                }
                else if (type.equals("M"))
                {
                        grc.add(GregorianCalendar.MONTH, into);
                }
                else if (type.equals("Y"))
                {
                        grc.add(GregorianCalendar.YEAR, into);
                }
                else if (type.equals("HH"))
                {
                        grc.add(GregorianCalendar.HOUR, into);
                }
                else if (type.equals("MI"))
                {
                        grc.add(GregorianCalendar.MINUTE, into);
                }
                else if (type.equals("SS"))
                {
                        grc.add(GregorianCalendar.SECOND, into);
                }
                else
                {
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String Sdate = new String(formatter.format(grc.getTime()));

                //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
                return Sdate;
        }

        /**
         * 【功能】得到日期yyyy-MM-dd；
         *
         * @return String 字符串 2004-12-29
         */
        public static String getSystemDate()
        {
                java.util.Date date = new java.util.Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String mDateTime1 = formatter.format(date);

                return mDateTime1;
        }

        /**
         * 获得指定日期所在week的起始日期，提供和返回的日期格式为yyyy-MM-dd
         *
         * @param strDate 提供的日期
         * @return 指定日期所在week的起始日期
         */
        public static String getCurWeekBegin(String strDate)
        {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.parse(strDate, new ParsePosition(0));

                Calendar calendar = dateFormat.getCalendar();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                calendar.add(Calendar.DATE, 1 - day);

                return dateFormat.format(calendar.getTime());
        }

        /**
         * 获得指定星期所在开始结束时间内的占用天数
         *
         * @return 指定日期所在week的起始日期
         */
        public static String getCurWeekDay(Date BinDate, Date EndDate, String week)
        {
                //String[] tmp = StringUtil.splitString(binDate, "-");
                //Date BinDate = DateTimeUtil.toDate(tmp[1], tmp[2].substring(0, 2), tmp[0], "0", "0", "0");
                //String[] tmp2 = StringUtil.splitString(endDate, "-");
                //Date EndDate = DateTimeUtil.toDate(tmp2[1], tmp2[2].substring(0, 2), tmp2[0], "23", "59", "59");
                String[] tmp3 = StringUtil.splitString(week, "-");
                Date Week = DateTimeUtil.toDate(tmp3[1], tmp3[2].substring(0, 2),
                        tmp3[0], "12", "0", "0");
                String strreturn = "";

                String strtime = "";

                for (int i = 0; i < 7; i++)
                {
                        strtime = DateTimeUtil.addDateTime(Week, "D", i);

                        String[] tmp4 = StringUtil.splitString(strtime, "-");
                        Date Strtime1 = DateTimeUtil.toDate(tmp4[1],
                                tmp4[2].substring(0, 2), tmp4[0], "11", "0", "0");

                        Date Strtime2 = DateTimeUtil.toDate(tmp4[1],
                                tmp4[2].substring(0, 2), tmp4[0], "13", "0", "0");
                        Date Strtime3 = DateTimeUtil.toDate(tmp4[1],
                                tmp4[2].substring(0, 2), tmp4[0], "16", "0", "0");

                        //System.out.println(Strtime.getTime());
                        //System.out.println(BinDate.getTime());
                        //System.out.println(EndDate.getTime());
                        //System.out.println((Strtime.getTime()>=BinDate.getTime())&&(Strtime.getTime()<=EndDate.getTime()));
                        //System.out.println("=====================");
                        if ((Strtime1.getTime() >= BinDate.getTime()) &&
                                (Strtime1.getTime() <= EndDate.getTime()))
                        {
                                strreturn += ((i + 1) + "A,");
                        }

                        if ((Strtime2.getTime() >= BinDate.getTime()) &&
                                (Strtime2.getTime() <= EndDate.getTime()))
                        {
                                strreturn += ((i + 1) + "B,");
                        }

                        if ((Strtime3.getTime() >= BinDate.getTime()) &&
                                (Strtime3.getTime() <= EndDate.getTime()))
                        {
                                strreturn += ((i + 1) + "C,");
                        }
                }

                if (strreturn.equals(""))
                {
                }
                else
                {
                        strreturn = strreturn.substring(0, strreturn.length() - 1);
                }

                return strreturn;
        }

        public static int Datecomp()
        {
                long todate = DateTimeUtil.toDate("06", "05", "2006", "0", "0", "0")
                        .getTime() -
                        DateTimeUtil.toDate("06", "03", "2006", "0", "0", "0").getTime();

                int day = (int) (todate / 86400000);
                System.out.println();

                return day;
        }

        public static void main(String[] args) throws Exception
        {
                //System.out.println(
                String[] tmp3 = StringUtil.splitString(addDateTime(toDate("02", "03",
                        "2006", "00", "00", "0"), "D", 1).substring(0, 10), "-");
                System.out.println(DateTimeUtil.toDate(tmp3[1], tmp3[2], tmp3[0], "00",
                        "00", "0"));

                //String[] tmp = StringUtil.splitString("2005-11-11", "-");
                //String nowtime = DateTimeUtilz.getCurWeekDay("2005-01-10","2005-01-19","2005-01-07");
                //System.out.println(nowtime);
        }
}
