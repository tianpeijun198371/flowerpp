//Source file: d:\\ulms\\source\\com\\eduedu\\ulms\\tools\\calendar\\helper\\CalendarHelper.java
package com.ulearning.ulms.tools.calendar.helper;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.calendar.dao.CalendarDAO;
import com.ulearning.ulms.tools.calendar.dao.CalendarDAOFactory;
import com.ulearning.ulms.tools.calendar.dao.PreferenceDAO;
import com.ulearning.ulms.tools.calendar.dao.PreferenceDAOFactory;
import com.ulearning.ulms.tools.calendar.exceptions.CalendarSysException;
import com.ulearning.ulms.tools.calendar.exceptions.PreferenceSysException;
import com.ulearning.ulms.tools.calendar.form.PreferenceForm;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


public class CalendarHelper
{
        private static int loginView;
        private static TimeZone timeZone;
        private static int firstDayOfWeek;
        private static boolean isViewLunarCalendar;
        private static String hourStyle;

        /**
         * 脏标志
         */
        private static boolean updateFlag = true;

        public static Calendar String2Calenar(String str)
        {
                List dates = StringUtil.split(str, "-");
                Calendar cal = Calendar.getInstance();
                int year = Integer.parseInt(dates.get(0).toString());
                int month = Integer.parseInt(dates.get(1).toString()) - 1;
                int day = Integer.parseInt(dates.get(2).toString());

                cal.set(year, month, day);

                return cal;
        }

        public static int compare(String date1, String date2)
        {
                int year1 = Integer.parseInt(date1.substring(0, 4));
                int month1 = Integer.parseInt(date1.substring(5, 7));
                int day1 = Integer.parseInt(date1.substring(8, 10));

                int year2 = Integer.parseInt(date2.substring(0, 4));
                int month2 = Integer.parseInt(date2.substring(5, 7));
                int day2 = Integer.parseInt(date2.substring(8, 10));

                Calendar cal1 = Calendar.getInstance();
                cal1.set(year1, month1, day1, 0, 0, 0);

                Calendar cal2 = Calendar.getInstance();
                cal2.set(year2, month2, day2, 0, 0, 0);

                if (cal1.before(cal2))
                {
                        int cnt = 0;

                        while (cal1.before(cal2))
                        {
                                cal1.add(Calendar.DAY_OF_MONTH, 1);
                                cnt++;
                        }

                        return cnt; //小于
                }
                else if (cal1.after(cal2))
                {
                        int cnt = 0;

                        while (cal1.after(cal2))
                        {
                                cal1.add(Calendar.DAY_OF_MONTH, -1);
                                cnt--;
                        }

                        return cnt; //大于
                }

                return 0; //等于
        }

        public static String Calendar2String(Calendar cal)
        {
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                String m = String.valueOf(month);

                if (m.length() < 2)
                {
                        m = "0" + m;
                }

                int day = cal.get(Calendar.DATE);
                String d = String.valueOf(day);

                if (d.length() < 2)
                {
                        d = "0" + d;
                }

                String str = "" + year + "-" + m + "-" + d;

                return str;
        }

        public static String convertTime2String(Calendar cal)
        {
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                String time = year + "-";

                if (month < 10)
                {
                        time += "0";
                }

                time = time + month + "-";

                if (day < 10)
                {
                        time += "0";
                }

                time += (day + " ");

                if (hour < 10)
                {
                        time += "0";
                }

                time += (hour + ":");

                if (minute < 10)
                {
                        time += "0";
                }

                time += (minute + ":");

                if (second < 10)
                {
                        time += "0";
                }

                time += second;

                return time;
        }

        public static Calendar convertString2Time(String dateTime)
        {
                Calendar cal = Calendar.getInstance();
                List all = StringUtil.split(dateTime, " ");
                String date = (String) all.get(0);
                String time = (String) all.get(1);
                List dates = StringUtil.split(date, "-");
                List times = StringUtil.split(time, ":");
                int year = Integer.parseInt((String) dates.get(0));
                int month = Integer.parseInt((String) dates.get(1));
                int day = Integer.parseInt((String) dates.get(2));
                int hour = Integer.parseInt((String) times.get(0));
                int minute = Integer.parseInt((String) times.get(1));
                int second = Integer.parseInt((String) times.get(2));
                cal.set(year, month, day, hour, minute, second);

                return cal;
        }

        public static boolean isUpdateFlag()
        {
                return updateFlag;
        }

        public static void setUpdateFlag(boolean updateFlag)
        {
                CalendarHelper.updateFlag = updateFlag;
        }

        public static void update(int userID) throws PreferenceSysException
        {
                PreferenceForm pm = null;

                try
                {
                        PreferenceDAO dao = PreferenceDAOFactory.getDAO();
                        pm = dao.get(userID);
                        loginView = pm.getLoginView();
                        timeZone = TimeZone.getTimeZone(pm.getTimeZone());
                        firstDayOfWeek = pm.getFirstDayOfWeek();

                        if (pm.getIsViewLunarCalendar().equals("1"))
                        {
                                isViewLunarCalendar = true;
                        }
                        else
                        {
                                isViewLunarCalendar = false;
                        }

                        hourStyle = pm.getHourStyle();
                }
                catch (PreferenceSysException gse)
                {
                        gse.printStackTrace();
                }
        }

        /**
         * @return int
         */
        public static int getLoginView()
        {
                return loginView;
        }

        /**
         * @return int
         */
        public static TimeZone getTimeZone()
        {
                return timeZone;
        }

        /**
         * @return int
         */
        public static int getFirstDayOfWeek()
        {
                return firstDayOfWeek;
        }

        /**
         * @return boolean
         */
        public static boolean getIsViewLunarCalendar()
        {
                return isViewLunarCalendar;
        }

        /**
         * @return int
         */
        public static String getHourStyle()
        {
                return hourStyle;
        }

        public static void delete(int type, int relationID)
                throws CalendarSysException
        {
                CalendarDAO calendardao = CalendarDAOFactory.getDAO();
                calendardao.delete(type, relationID);
        }

        public static void deleteByUserID(int userID) throws CalendarSysException
        {
                CalendarDAO calendardao = CalendarDAOFactory.getDAO();
                calendardao.deleteByUserID(userID);
        }
}
