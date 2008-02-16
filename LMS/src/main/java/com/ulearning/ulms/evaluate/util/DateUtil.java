package com.ulearning.ulms.evaluate.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;


/**
 * Created by IntelliJ IDEA.
 * User: gongsi
 * Date: 2007-2-3
 * Time: 12:33:19
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil
{
        private static Log log = LogFactory.getLog(DateUtil.class);
        private static String defaultDatePattern = "yyyy-MM-dd";
        private static String defaultDateTime24Pattern = "MM-dd-yyyy HH:mm:ss";
        private static String timePattern = "HH:mm";
        private static String simpleFormat = "yyyyMMddHHmmss-";
        private static String defaultDateDayPattern = "MM-dd-yyyy hh24:mi:ss";

        public static String getDefaultDateDayPattern()
        {
                return defaultDateDayPattern;
        }

        public static void setDefaultDateDayPattern(String defaultDateDayPattern)
        {
                DateUtil.defaultDateDayPattern = defaultDateDayPattern;
        }

        /**
         * Return default datePattern (MM/dd/yyyy)
         *
         * @return a string representing the date pattern on the UI
         */
        public static String getDatePattern()
        {
                return defaultDatePattern;
        }

        /**
         * This method attempts to convert an Oracle-formatted date
         * in the form dd-MMM-yyyy to mm/dd/yyyy.
         *
         * @param aDate date from database as a string
         * @return formatted string for the ui
         */
        public static final String getDate(Date aDate)
        {
                SimpleDateFormat df = null;
                String returnValue = "";

                if (aDate != null)
                {
                        df = new SimpleDateFormat(getDatePattern());
                        returnValue = df.format(aDate);
                }

                return (returnValue);
        }

        /**
         * This method generates a string representation of a date/time
         * in the format you specify on input
         *
         * @param aMask   the date pattern the string is in
         * @param strDate a string representation of a date
         * @return a converted Date object
         * @throws ParseException
         * @see java.text.SimpleDateFormat
         */
        public static final Date convertStringToDate(String aMask, String strDate)
                throws ParseException
        {
                SimpleDateFormat df = null;
                Date date = null;
                df = new SimpleDateFormat(aMask);

                if (log.isDebugEnabled())
                {
                        log.debug("converting '" + strDate + "' to date with mask '" +
                                aMask + "'");
                }

                try
                {
                        date = df.parse(strDate);
                }
                catch (ParseException pe)
                {
                        throw new ParseException(pe.getMessage(), pe.getErrorOffset());
                }

                return (date);
        }

        /**
         * This method returns the current date time in the format:
         * MM/dd/yyyy HH:MM a
         *
         * @param theTime the current time
         * @return the current date/time
         */
        public static String getTimeNow(Date theTime)
        {
                return getDateTime(timePattern, theTime);
        }

        /**
         * This method returns the current date in the format: MM/dd/yyyy
         *
         * @return the current date
         * @throws ParseException
         */
        public static Calendar getToday() throws ParseException
        {
                Date today = new Date();
                SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

                // This seems like quite a hack (date -> string -> date),
                // but it works ;-)
                String todayAsString = df.format(today);
                Calendar cal = new GregorianCalendar();
                cal.setTime(convertStringToDate(todayAsString));

                return cal;
        }

        /**
         * This method generates a string representation of a date's date/time
         * in the format you specify on input
         *
         * @param aMask the date pattern the string is in
         * @param aDate a date object
         * @return a formatted string representation of the date
         * @see java.text.SimpleDateFormat
         */
        public static final String getDateTime(String aMask, Date aDate)
        {
                SimpleDateFormat df = null;
                String returnValue = "";

                if (aDate != null)
                {
                        df = new SimpleDateFormat(aMask);
                        returnValue = df.format(aDate);
                }

                return (returnValue);
        }

        /**
         * This method generates a string representation of a date based
         * on the System Property 'dateFormat'
         * in the format you specify on input
         *
         * @param aDate A date to convert
         * @return a string representation of the date
         */
        public static final String convertDateToString(Date aDate)
        {
                return getDateTime(getDatePattern(), aDate);
        }

        /**
         * This method generates a string representation of a date based
         * on the System Property 'dateFormat'
         * in the format you specify on input
         *
         * @param aDate A date to convert
         * @return a string representation of the date
         */
        public static final String convertDateTimeToString(Date aDate)
        {
                return getDateTime(getDefaultDateTime24Pattern(), aDate);
        }

        /**
         * This method converts a String to a date using the datePattern
         *
         * @param strDate the date to convert (in format MM/dd/yyyy)
         * @return a date object
         * @throws ParseException
         */
        public static Date convertStringToDate(String strDate)
                throws ParseException
        {
                Date aDate = null;

                try
                {
                        if (log.isDebugEnabled())
                        {
                                log.debug("converting date with pattern: " + getDatePattern());
                        }

                        aDate = convertStringToDate(getDatePattern(), strDate);
                }
                catch (ParseException pe)
                {
                        log.error("Could not convert '" + strDate +
                                "' to a date, throwing exception");
                        pe.printStackTrace();
                        throw new ParseException(pe.getMessage(), pe.getErrorOffset());
                }

                return aDate;
        }

        /**
         * This method converts a String to a date using the datePattern
         *
         * @param strDate the date to convert (in format MM/dd/yyyy HH:mm:ss)
         * @return a date object
         * @throws ParseException
         */
        public static Date convertStringToDateTime(String strDate)
                throws ParseException
        {
                Date aDate = null;

                try
                {
                        if (log.isDebugEnabled())
                        {
                                log.debug("converting date with pattern: " +
                                        getDefaultDateTime24Pattern());
                        }

                        aDate = convertStringToDate(getDefaultDateTime24Pattern(), strDate);
                }
                catch (ParseException pe)
                {
                        log.error("Could not convert '" + strDate +
                                "' to a date, throwing exception");
                        pe.printStackTrace();
                        throw new ParseException(pe.getMessage(), pe.getErrorOffset());
                }

                return aDate;
        }

        /**
         * �����������֮����������,secondDate-firstdate
         * <p/>
         *
         * @return String
         * @throws
         */
        public static int getA_BDaysFromDate(Date firstdate, Date secondDate)
        {
                Calendar cal = Calendar.getInstance();
                cal.set(2000, Calendar.AUGUST, 24);

                Date d1 = cal.getTime();
                cal.set(2000, Calendar.AUGUST, 28);

                Date d2 = cal.getTime();
                long daterange = secondDate.getTime() - firstdate.getTime();
                long time = 1000 * 3600 * 24; //A day in milliseconds һ��ĺ�����
                //System.out.println(daterange/time);

                int rettime = (new Long(daterange / time)).intValue();

                return rettime;
        }

        /**
         * ��String���͵�yyyy-MM-dd��ʽ���Date���͵�yyyy-MM-dd ��ʽ��ʱ��
         * <p/>
         *
         * @throws
         */
        public static Date getDateFormatYMDString(String sourcedate)
        {
                DateFormat dateformat;

                if (sourcedate == null)
                {
                        return null;
                }

                Date newDate = new Date();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(defaultDatePattern);

                try
                {
                        newDate = sdf.parse(sourcedate);
                }
                catch (Exception e)
                {
                        log.debug(e.toString());

                        return null;
                }

                return newDate;
        }

        /**
         * ��String���͵�yyyy-MM-dd HH:mm:ss��ʽ���Date���͵�yyyy-MM-dd ��ʽ��ʱ��
         * <p/>
         */
        public static Date getDateFormatYMDHMSString(String sourcedate)
        {
                DateFormat dateformat;

                if (sourcedate == null)
                {
                        return null;
                }

                Date newDate = new Date();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(defaultDateTime24Pattern);

                try
                {
                        newDate = sdf.parse(sourcedate);
                }
                catch (Exception e)
                {
                        log.debug(e.toString());

                        return null;
                }

                return newDate;
        }

        /**
         * ���String���͵�dd-MM-yyyy HH:mm:ss��ʽ��ʱ��
         */
        public static String getStringFormatDMYHMSDate(Date sourcedate)
        {
                DateFormat dateformat;

                if (sourcedate == null)
                {
                        return null;
                }

                dateformat = new SimpleDateFormat(defaultDateTime24Pattern);

                //System.out.println("dd-MM-yyyy HH:mm:ss " + dateformat.format(date));
                return dateformat.format(sourcedate);
        }

        /**
         * ��String���͵�yyyy-MM-dd���String���͵�MM-dd-yyyy��ʽ��ʱ��
         * <p/>
         *
         * @throws
         */
        public static String getStringFormatYMDString(String sourcedate)
        {
                DateFormat dateformat;
                Date date = new Date();

                if (sourcedate == null)
                {
                        return null;
                }

                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(defaultDatePattern);

                try
                {
                        date = sdf.parse(sourcedate);
                }
                catch (Exception e)
                {
                        log.debug(e.toString());

                        return null;
                }

                return getStringFormatDMYHMSDate(date);
        }

        /**
         * ��String���͵�yyyy-MM-dd���String���͵�MM-dd-yyyy HH:mm:ss��ʽ��ʱ��
         * <p/>
         *
         * @throws
         */
        public static String getStringFormatYMDHMSString(String sourcedate)
        {
                DateFormat dateformat;
                Date date = new Date();

                if (sourcedate == null)
                {
                        return null;
                }

                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(defaultDateTime24Pattern);

                try
                {
                        date = sdf.parse(sourcedate);
                }
                catch (Exception e)
                {
                        log.debug(e.toString());

                        return null;
                }

                return getStringFormatDMYHMSDate(date);
        }

        public static String getSimpleFormatDate(Date date)
        {
                return (new SimpleDateFormat(simpleFormat).format(date)) +
                        new Random().nextInt(1000);
        }

        public static void main(String[] args)
        {
                System.out.println(new Date());
                System.out.println(DateUtil.convertDateToString(new Date()));
                System.out.println(DateUtil.getA_BDaysFromDate(new Date(), new Date()));
                System.out.println(DateUtil.getSimpleFormatDate(new Date()));
        }

        public static String getDefaultDateTime24Pattern()
        {
                return defaultDateTime24Pattern;
        }

        public static void setDefaultDateTime24Pattern(
                String defaultDateTime24Pattern)
        {
                DateUtil.defaultDateTime24Pattern = defaultDateTime24Pattern;
        }
}
