/**
 * I18Util.java.
 * User: fengch  Date: 2004-4-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import org.apache.struts.Globals;
import org.apache.struts.util.RequestUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.jsp.PageContext;


public class I18Util
{
        /*
        * to formate date according specfic locale
        */
        public static String FormatDate(Date d, PageContext pageContext)
        {
                if (d == null)
                {
                        return "";
                }

                Locale userLocale = getLocale(pageContext);
                String formattedDate = FormatDate(d, userLocale);

                return formattedDate;
        }

        /*
        * to formate date time according specfic locale
        */
        public static String FormatDateTime(Date d, PageContext pageContext)
        {
                if (d == null)
                {
                        return "";
                }

                Locale userLocale = getLocale(pageContext);
                String formattedDate = FormatDateTime(d, userLocale);

                return formattedDate;
        }

        /*
        * to formate date according specfic locale and TimeZONE
        * style:
        * ==DateFormat.SHORT   :04-8-7
        * ==DateFormat.MEDIUM  :2004-8-7
        * ==DateFormat.LONG    :2004年8月7日
        * ==DateFormat.FULL    :2004年8月7日
        */
        public static String FormatDate(Date d, int style, TimeZone zone,
                                        PageContext pageContext)
        {
                if (d == null)
                {
                        return "";
                }

                Locale userLocale = getLocale(pageContext);
                String formattedDate = FormatDate(d, style, zone, userLocale);

                return formattedDate;
        }

        /*
        * to formate date time according specfic locale  and TimeZONE
        * style:
        * ==DateFormat.SHORT   :04-8-7 上午1:40
        * ==DateFormat.MEDIUM  :2004-8-7 1:40:04
        * ==DateFormat.LONG    :2004年8月7日 上午01时40分04秒
        * ==DateFormat.FULL    :2004年8月7日 01时40分04秒 GMT+22:00
        */
        public static String FormatDateTime(Date d, int style, TimeZone zone,
                                            PageContext pageContext)
        {
                if (d == null)
                {
                        return "";
                }

                Locale userLocale = getLocale(pageContext);
                String formattedDate = FormatDateTime(d, style, zone, userLocale);

                return formattedDate;
        }

        /*
        * to formate date time according specfic locale
        */
        public static String FormatDateTime(Date d, Locale lo)
        {
                String formattedDate;
                int dataStyle = DateFormat.MEDIUM;
                DateFormat df = DateFormat.getDateTimeInstance(dataStyle, dataStyle, lo);
                formattedDate = df.format(d);

                return formattedDate;
        }

        /*
        * to formate date according specfic locale
        */
        public static String FormatDate(Date d, Locale lo)
        {
                String formattedDate;
                int dataStyle = DateFormat.LONG;
                DateFormat df = DateFormat.getDateInstance(dataStyle, lo);
                formattedDate = df.format(d);

                return formattedDate;
        }

        /*
          style:
          ==DateFormat.SHORT   :04-8-7 上午1:40
          ==DateFormat.MEDIUM  :2004-8-7 1:40:04
          ==DateFormat.LONG    :2004年8月7日 上午01时40分04秒
          ==DateFormat.FULL    :2004年8月7日 01时40分04秒 GMT+22:00
        */
        public static String FormatDateTime(Date d, int style, TimeZone zone,
                                            Locale lo)
        {
                TimeZone curr = TimeZone.getDefault();
                TimeZone.setDefault(zone);

                String formattedDate;

                DateFormat df = DateFormat.getDateTimeInstance(style, style, lo);
                formattedDate = df.format(d);
                TimeZone.setDefault(curr);

                return formattedDate;
        }

        /*
          style:
          ==DateFormat.SHORT   :04-8-7
          ==DateFormat.MEDIUM  :2004-8-7
          ==DateFormat.LONG    :2004年8月7日
          ==DateFormat.FULL    :2004年8月7日
        */
        public static String FormatDate(Date d, int style, TimeZone zone, Locale lo)
        {
                TimeZone curr = TimeZone.getDefault();
                TimeZone.setDefault(zone);

                String formattedDate;

                DateFormat df = DateFormat.getDateInstance(style, lo);
                formattedDate = df.format(d);
                TimeZone.setDefault(curr);

                return formattedDate;
        }

        /*
        * to formate percentage according specfic locale
        */
        public static String FormatPercentage(double number, PageContext pageContext)
        {
                Locale userLocale = getLocale(pageContext);
                String formattedDate = FormatPercentage(number, userLocale);

                return formattedDate;
        }

        /*
        * to formate percentage according specfic locale
        */
        public static String FormatPercentage(double number, Locale lo)
        {
                String formattedDate;

                NumberFormat nf = NumberFormat.getPercentInstance(lo);

                DecimalFormat df = (DecimalFormat) nf;

                df.applyPattern("#,##0.00%");

                formattedDate = df.format(number);

                return formattedDate;
        }

        /*
        * to formate number according specfic locale
        */
        public static String FormatNumber(int number, PageContext pageContext)
        {
                Locale userLocale = getLocale(pageContext);
                String formattedDate = FormatNumber(number, userLocale);

                return formattedDate;
        }

        /*
        * to formate number according specfic locale
        */
        public static String FormatNumber(int number, Locale lo)
        {
                String formattedDate;

                NumberFormat nf = NumberFormat.getNumberInstance(lo);

                formattedDate = nf.format(number);

                return formattedDate;
        }

        /*
        * to formate number according specfic locale
        */
        public static String FormatNumber(long number, PageContext pageContext)
        {
                Locale userLocale = getLocale(pageContext);
                String formattedDate = FormatNumber(number, userLocale);

                return formattedDate;
        }

        /*
        * to formate number according specfic locale
        */
        public static String FormatNumber(long number, Locale lo)
        {
                String formattedDate;

                NumberFormat nf = NumberFormat.getNumberInstance(lo);

                formattedDate = nf.format(number);

                return formattedDate;
        }

        /*
        * to formate number according specfic locale
        */
        public static String FormatNumber(double number, PageContext pageContext)
        {
                Locale userLocale = getLocale(pageContext);
                String formattedDate = FormatNumber(number, userLocale);

                return formattedDate;
        }

        /*
        * to formate number according specfic locale
        */
        public static String FormatNumber(double number, Locale lo)
        {
                String formattedDate;

                NumberFormat nf = NumberFormat.getNumberInstance(lo);

                formattedDate = nf.format(number);

                return formattedDate;
        }

        /*
        * to formate currency according specfic locale
        */
        public static String FormatCurrency(double number, PageContext pageContext)
        {
                Locale userLocale = getLocale(pageContext);
                String formattedDate = FormatCurrency(number, userLocale);

                return formattedDate;
        }

        /*
        * to formate currency according specfic locale
        */
        public static String FormatCurrency(double number, Locale lo)
        {
                String formattedDate;
                Double currency = new Double(number);
                NumberFormat nf = NumberFormat.getCurrencyInstance(lo);

                formattedDate = nf.format(number);

                return formattedDate;
        }

        private static Locale getLocale(PageContext pageContext)
        {
                return RequestUtils.retrieveUserLocale(pageContext, Globals.LOCALE_KEY);
        }
}
