/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.util;

public class MSEL2HHMMDD
{
        int day;
        int hour;
        int minute;
        int second;
        int milliSecond;
        final int DAY_MSEL = 24 * 60 * 60 * 1000;
        final int HOUR_MSEL = 60 * 60 * 1000;
        final int MINUTE_MSEL = 60 * 1000;
        final int SECOND_MSED = 1000;

        public MSEL2HHMMDD()
        {
        }

        public MSEL2HHMMDD(long MSEL)
        {
                convert(MSEL);
        }

        private MSEL2HHMMDD(int day, int hour, int minute, int second,
                            int milliSecond)
        {
                this.day = day;
                this.hour = hour;
                this.minute = minute;
                this.second = second;
                this.milliSecond = milliSecond;
        }

        public MSEL2HHMMDD convert(long MSEL)
        {
                this.day = (int) (MSEL / this.DAY_MSEL);

                long m = (int) (MSEL % this.DAY_MSEL);
                this.hour = (int) (m / this.HOUR_MSEL);

                m = (int) (m % this.HOUR_MSEL);
                this.minute = (int) (m / this.MINUTE_MSEL);

                m = (int) (m % this.MINUTE_MSEL);
                this.second = (int) (m / this.SECOND_MSED);

                this.milliSecond = (int) (m % this.SECOND_MSED);

                return new MSEL2HHMMDD(this.day, this.hour, this.minute, this.second,
                        this.milliSecond);
        }

        public String convert2LString(long MSEL)
        {
                convert(MSEL);

                StringBuffer stringBuffer = new StringBuffer("");

                if (day > 0)
                {
                        stringBuffer.append(Integer.toString(day) + "D ");
                }

                String tmp = Integer.toString(hour);

                if (tmp.length() < 2)
                {
                        tmp = "0" + tmp;
                }

                stringBuffer.append(tmp + ":");

                tmp = Integer.toString(minute);

                if (tmp.length() < 2)
                {
                        tmp = "0" + tmp;
                }

                stringBuffer.append(tmp + ":");

                tmp = Integer.toString(second);

                if (tmp.length() < 2)
                {
                        tmp = "0" + tmp;
                }

                stringBuffer.append(tmp + ".");

                tmp = Integer.toString(milliSecond);

                if (tmp.length() < 2)
                {
                        tmp = "0" + tmp;
                }

                stringBuffer.append(tmp);

                return stringBuffer.toString();
        }

        public String convert2SString(long MSEL)
        {
                convert(MSEL);

                String tmp = Integer.toString(hour);

                if (tmp.length() < 2)
                {
                        tmp = "0" + tmp;
                }

                StringBuffer stringBuffer = new StringBuffer(tmp + ":");

                tmp = Integer.toString(minute);

                if (tmp.length() < 2)
                {
                        tmp = "0" + tmp;
                }

                stringBuffer.append(tmp + ":");

                tmp = Integer.toString(second);

                if (tmp.length() < 2)
                {
                        tmp = "0" + tmp;
                }

                stringBuffer.append(tmp);

                return stringBuffer.toString();
        }

        public int getDay()
        {
                return day;
        }

        public void setDay(int day)
        {
                this.day = day;
        }

        public int getHour()
        {
                return hour;
        }

        public void setHour(int hour)
        {
                this.hour = hour;
        }

        public int getMinute()
        {
                return minute;
        }

        public void setMinute(int minute)
        {
                this.minute = minute;
        }

        public int getSecond()
        {
                return second;
        }

        public void setSecond(int second)
        {
                this.second = second;
        }

        public int getMilliSecond()
        {
                return milliSecond;
        }

        public void setMilliSecond(int milliSecond)
        {
                this.milliSecond = milliSecond;
        }
}
