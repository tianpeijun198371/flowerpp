/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.util;

/**
 * Created by IntelliJ IDEA.
 * LunarCalendar.java .
 * <p/>
 * User: keyh * Date: 2004-9-14
 * Time: 17:41:46
 * <p/>
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

public class LunarCalendar
{
        private int year;
        private int month;
        private int date;
        private int week;
        private boolean isLeap;
        //日期字符串
        private String lunarDateString;

        private String lunarFulDateString;
        //农历节日
        private String lunarFestival;
        //公历节日
        private String solarFestival;
        //节气
        private String solarTerms;

        public LunarCalendar()
        {
        }

        public LunarCalendar(int year, int month, int date, int week, boolean leap, String lunarDateString, String lunarFulDateString, String lunarFestival, String solarFestival, String solarTerms)
        {
                this.year = year;
                this.month = month;
                this.date = date;
                this.week = week;
                isLeap = leap;
                this.lunarDateString = lunarDateString;
                this.lunarFulDateString = lunarFulDateString;
                this.lunarFestival = lunarFestival;
                this.solarFestival = solarFestival;
                this.solarTerms = solarTerms;
        }

        public int getYear()
        {
                return year;
        }

        public void setYear(int year)
        {
                this.year = year;
        }

        public int getMonth()
        {
                return month;
        }

        public void setMonth(int month)
        {
                this.month = month;
        }

        public int getDate()
        {
                return date;
        }

        public void setDate(int date)
        {
                this.date = date;
        }

        public int getWeek()
        {
                return week;
        }

        public void setWeek(int week)
        {
                this.week = week;
        }

        public boolean isLeap()
        {
                return isLeap;
        }

        public void setLeap(boolean leap)
        {
                isLeap = leap;
        }

        public String getLunarDateString()
        {
                return lunarDateString;
        }

        public void setLunarDateString(String lunarDateString)
        {
                this.lunarDateString = lunarDateString;
        }

        public String getLunarFulDateString()
        {
                return lunarFulDateString;
        }

        public void setLunarFulDateString(String lunarFulDateString)
        {
                this.lunarFulDateString = lunarFulDateString;
        }

        public String getLunarFestival()
        {
                return lunarFestival;
        }

        public void setLunarFestival(String lunarFestival)
        {
                this.lunarFestival = lunarFestival;
        }

        public String getSolarFestival()
        {
                return solarFestival;
        }

        public void setSolarFestival(String solarFestival)
        {
                this.solarFestival = solarFestival;
        }

        public String getSolarTerms()
        {
                return solarTerms;
        }

        public void setSolarTerms(String solarTerms)
        {
                this.solarTerms = solarTerms;
        }

}
