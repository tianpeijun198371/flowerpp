/**
 * Calendar.java.
 * User: fengch  Date: 2004-4-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public class Calendar
{
        java.util.Calendar ccalendar;

        public Calendar()
        {
                ccalendar = new GregorianCalendar();
        }

        public Calendar(Locale lo)
        {
                ccalendar = new GregorianCalendar(lo);
        }

        public Calendar(Date dd)
        {
                ccalendar = new GregorianCalendar();

                if (dd != null)
                {
                        ccalendar.setTime(dd);
                }
        }

        public Calendar(int year, int month, int day)
        {
                try
                {
                        ccalendar = new GregorianCalendar(year, month, day);
                }
                catch (Exception se)
                {
                }
        }

        /**
         * Update the current date.
         */
        public void setDate(Date dd)
        {
                ccalendar.setTime(dd);
        }

        public Date getDate()
        {
                return ccalendar.getTime();
        }

        public java.util.Calendar getCalendar()
        {
                return ccalendar;
        }

        /**
         * Get the current month number.
         */
        public int getYear()
        {
                return ccalendar.get(java.util.Calendar.YEAR);
        }

        /**
         * Get the month number within the current year.
         */
        public int getMonth()
        {
                return ccalendar.get(java.util.Calendar.MONTH);
        }

        /**
         * Get the day number within the current month.
         */
        public int getDayOfMonth()
        {
                return ccalendar.get(java.util.Calendar.DAY_OF_MONTH);
        }

        /**
         * Get the week number within the current year..
         */
        public int getWeekOfYear()
        {
                return ccalendar.get(java.util.Calendar.WEEK_OF_YEAR);
        }

        /**
         * Get current weekday.
         */
        public int getDayOfWeek()
        {
                return ccalendar.get(java.util.Calendar.DAY_OF_WEEK);
        }

        /**
         * Get current hour.
         */
        public int getHour()
        {
                return ccalendar.get(java.util.Calendar.HOUR_OF_DAY);
        }

        /**
         * Get current minute.
         */
        public int getMinute()
        {
                return ccalendar.get(java.util.Calendar.MINUTE);
        }

        /**
         * Get current second.
         */
        public int getSecond()
        {
                return ccalendar.get(java.util.Calendar.SECOND);
        }

        /**
         * Get next day.
         * <p/>
         * eg: 3月31日 的下一天，返回 3月1日
         */
        public int[] getNextDay()
        {
                int[] dd = new int[3];
                ccalendar.roll(java.util.Calendar.DAY_OF_MONTH, true);
                dd[0] = getDayOfMonth();
                dd[1] = getMonth();
                dd[2] = getYear();
                ccalendar.roll(java.util.Calendar.DAY_OF_MONTH, false);

                return dd;
        }

        /**
         * Get previous day.
         * <p/>
         * eg: 4月1日 的上一天，返回 4月30日
         */
        public int[] getPrevDay()
        {
                int[] dd = new int[3];
                ccalendar.roll(java.util.Calendar.DAY_OF_MONTH, false);
                dd[0] = getDayOfMonth();
                dd[1] = getMonth();
                dd[2] = getYear();
                ccalendar.roll(java.util.Calendar.DAY_OF_MONTH, true);

                return dd;
        }

        /**
         * Get next month exactly.
         * 功能同getNextMonth()
         * eg: 1999年12月 的下一月，返回 2000年1月
         */
        public int[] getNextMonth()
        {
                int[] dd = new int[2];
                java.util.Calendar ddCalendar = new GregorianCalendar(getYear(),
                        getMonth() + 1, 1);

                dd[0] = ddCalendar.get(java.util.Calendar.MONTH);
                dd[1] = ddCalendar.get(java.util.Calendar.YEAR);

                return dd;
        }

        /**
         * Get previous month exactly.
         * 功能同getPrevMonthProPro()
         * eg: 1999年1月 的上一月，返回 1998年12月
         */
        public int[] getPrevMonth()
        {
                int[] dd = new int[2];
                int T_month = getMonth();
                int T_year = getYear();
                dd[0] = T_month - 1;
                dd[1] = T_year;

                if (dd[0] < 0)
                {
                        dd[0] = ((dd[0]) % 12) + 12;
                        dd[1] = T_year - 1;
                }
                else if (dd[0] > 11)
                {
                        dd[0] = (dd[0]) % 12;
                        dd[1] = T_year + 1;
                }

                return dd;
        }

        /**
         * Get previous month.
         * <p/>
         * eg: 1999年1月 的上一月，返回 1999年12月
         */
        public int[] getPrevMonthPro()
        {
                int[] dd = new int[2];
                ccalendar.roll(java.util.Calendar.MONTH, false);
                dd[0] = getMonth();
                dd[1] = getYear();
                ccalendar.roll(java.util.Calendar.MONTH, true);

                return dd;
        }

        /**
         * Get previous month exactly.
         * 功能同getPrevMonth()
         * eg: 1999年1月 的上一月，返回 1998年12月
         */
        public int[] getPrevMonthProPro()
        {
                int[] dd = new int[2];
                java.util.Calendar ddCalendar = new GregorianCalendar(getYear(),
                        getMonth() - 1, 1);

                dd[0] = ddCalendar.get(java.util.Calendar.MONTH);
                dd[1] = ddCalendar.get(java.util.Calendar.YEAR);

                return dd;
        }

        /**
         * return total day numbers of current month.
         */
        public int totalDay()
        {
                return ccalendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        }

        /**
         * return current year.
         */
        public int getCurrentY()
        {
                java.util.Calendar dd = new GregorianCalendar();

                return dd.get(java.util.Calendar.YEAR);
        }

        /**
         * return current month.
         */
        public int getCurrentM()
        {
                java.util.Calendar dd = new GregorianCalendar();

                return dd.get(java.util.Calendar.MONTH);
        }

        /**
         * return current day.
         */
        public int getCurrentD()
        {
                java.util.Calendar dd = new GregorianCalendar();

                return dd.get(java.util.Calendar.DAY_OF_MONTH);
        }

        /**
         * return current weekday.
         * eg: "星期日",return 0; "星期一",return 1.
         */
        public int getWeekDay()
        {
                return (ccalendar.get(java.util.Calendar.DAY_OF_WEEK) - 1) % 7;
        }

        /**
         * 返回此年此月第一天星期几.
         */
        public int getFirstInMonthWeekDay()
        {
                java.util.Calendar dd = new GregorianCalendar(getYear(), getMonth(), 1);

                return (dd.get(java.util.Calendar.DAY_OF_WEEK) - 1) % 7;
        }

        public int getEra()
        {
                return ccalendar.get(java.util.Calendar.ERA);
        }

        public int getAMPM()
        {
                return ccalendar.get(java.util.Calendar.AM_PM);
        }

        /**
         * Replaces the string: hh:mm.
         *
         * @param h integer(>0;<24)
         * @param m integer(>0;<60)
         * @return String.
         */
        public String get24H(int h, int m)
        {
                String str = "";
                String mm;

                h %= 24;
                m %= 60;

                if (h < 12)
                {
                        mm = "am";
                }
                else
                {
                        mm = "pm";
                }

                if ((h == 0) || (h == 12))
                {
                        h = 12;
                }
                else
                {
                        h %= 12;
                }

                if (h < 10)
                {
                        str = "0";
                }

                str += (h + ":");

                if (m < 10)
                {
                        str = str + "0";
                }

                str = str + m + " " + mm;

                return str;
        }

        //return days between two date
        public int betweenPerDay(Date startDate, Date endDate)
        {
                java.util.Calendar ccalendar = new GregorianCalendar(2001, 11, 20);
                int dayTime = 86400000;

                int dd = (int) (((endDate.getTime() - ccalendar.getTime().getTime()) / dayTime) -
                        ((startDate.getTime() - ccalendar.getTime().getTime()) / dayTime));

                if (dd < 0)
                {
                        dd = -dd;
                }

                return dd + 1;
        }

        //返回在time0 i天后的时间
        /**
         * author: zengwj
         * date: 04/06/02
         */
        public static String addDate(String preDateStr, String toke, int dayNum)
        {
                String returnDateStr = "";
                String s2 = "";
                SimpleDateFormat objFormat = new SimpleDateFormat("yyyy-mm-dd");

                try
                {
                        objFormat.parse(preDateStr);
                }
                catch (ParseException e)
                {
                        String s = returnDateStr;

                        return s;
                }

                List preDate = StringUtil.split(preDateStr, toke);

                if ((preDate == null) && (preDate.size() != 3))
                {
                        return returnDateStr;
                }

                int[] preDateInt = new int[preDate.size()];

                for (int i = 0; i < preDate.size(); i++)
                {
                        if ((preDate.get(i) == null) ||
                                ((String) preDate.get(i)).equals(""))
                        {
                                preDateInt[i] = 0;
                        }
                        else
                        {
                                preDateInt[i] = Integer.parseInt((String) preDate.get(i));
                        }
                }

                GregorianCalendar cal = new GregorianCalendar(preDateInt[0],
                        preDateInt[1] - 1, preDateInt[2]);
                cal.add(5, dayNum);
                returnDateStr = String.valueOf(String.valueOf(
                        (new StringBuffer(String.valueOf(String.valueOf(cal.get(1))))).append(
                                "-").append(cal.get(2) + 1).append("-")
                                .append(cal.get(5))));
                System.out.println("returnDateStr ================================" +
                        returnDateStr);

                try
                {
                        Date objDate = objFormat.parse(returnDateStr);
                        String s1 = objFormat.format(objDate);

                        return s1;
                }
                catch (ParseException e)
                {
                        s2 = "";
                }

                return s2;
        }

        //return yyyy-MM-dd for oracle
        public static String getDateString(Date thisDate)
        {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                if (thisDate != null)
                {
                        return formatter.format(thisDate);
                }
                else
                {
                        return null;
                }
        }

        /**
         * 返回下一天的日期
         *
         * @return
         */
        public Date nextDay()
        {
                ccalendar.add(java.util.Calendar.DAY_OF_MONTH, 1);

                Date dd = ccalendar.getTime();
                ccalendar.add(java.util.Calendar.DAY_OF_MONTH, -1);

                return dd;
        }

        /**
         * 返回下一天的日期
         *
         * @return
         */
        public Date prevtDay()
        {
                ccalendar.add(java.util.Calendar.DAY_OF_MONTH, -1);

                Date dd = ccalendar.getTime();
                ccalendar.add(java.util.Calendar.DAY_OF_MONTH, 1);

                return dd;
        }
}
