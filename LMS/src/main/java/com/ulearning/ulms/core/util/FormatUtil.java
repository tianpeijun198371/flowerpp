/**
 * FormatUtil.java.
 * User: fengch  Date: 2004-4-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import java.text.*;


/**
 * General output formatting functions - mainly for helping in JSPs
 */
public class FormatUtil
{
        // ------------------- price format handlers -------------------
        static DecimalFormat priceDecimalFormat = new DecimalFormat("#,##0.00");

        // ------------------- percentage format handlers -------------------
        static DecimalFormat percentageDecimalFormat = new DecimalFormat("##0.##%");

        // ------------------- quantity format handlers -------------------
        static DecimalFormat quantityDecimalFormat = new DecimalFormat("#,##0.###");

        /**
         * Formats a Double representing a price into a string
         *
         * @param price The price Double to be formatted
         * @return A String with the formatted price
         */
        public static String formatPrice(Double price)
        {
                if (price == null)
                {
                        return "";
                }

                return formatPrice(price.doubleValue());
        }

        /**
         * Formats a double representing a price into a string
         *
         * @param price The price double to be formatted
         * @return A String with the formatted price
         */
        public static String formatPrice(double price)
        {
                return priceDecimalFormat.format(price);
        }

        /**
         * Formats a Double representing a percentage into a string
         *
         * @param percentage The percentage Double to be formatted
         * @return A String with the formatted percentage
         */
        public static String formatPercentage(Double percentage)
        {
                if (percentage == null)
                {
                        return "";
                }

                return formatPercentage(percentage.doubleValue());
        }

        /**
         * Formats a double representing a percentage into a string
         *
         * @param percentage The percentage double to be formatted
         * @return A String with the formatted percentage
         */
        public static String formatPercentage(double percentage)
        {
                return percentageDecimalFormat.format(percentage);
        }

        /**
         * Formats an Long representing a quantity into a string
         *
         * @param quantity The quantity Long to be formatted
         * @return A String with the formatted quantity
         */
        public static String formatQuantity(Long quantity)
        {
                if (quantity == null)
                {
                        return "";
                }
                else
                {
                        return formatQuantity(quantity.doubleValue());
                }
        }

        /**
         * Formats an int representing a quantity into a string
         *
         * @param quantity The quantity long to be formatted
         * @return A String with the formatted quantity
         */
        public static String formatQuantity(long quantity)
        {
                return formatQuantity((double) quantity);
        }

        /**
         * Formats an Integer representing a quantity into a string
         *
         * @param quantity The quantity Integer to be formatted
         * @return A String with the formatted quantity
         */
        public static String formatQuantity(Integer quantity)
        {
                if (quantity == null)
                {
                        return "";
                }
                else
                {
                        return formatQuantity(quantity.doubleValue());
                }
        }

        /**
         * Formats an int representing a quantity into a string
         *
         * @param quantity The quantity int to be formatted
         * @return A String with the formatted quantity
         */
        public static String formatQuantity(int quantity)
        {
                return formatQuantity((double) quantity);
        }

        /**
         * Formats a Float representing a quantity into a string
         *
         * @param quantity The quantity Float to be formatted
         * @return A String with the formatted quantity
         */
        public static String formatQuantity(Float quantity)
        {
                if (quantity == null)
                {
                        return "";
                }
                else
                {
                        return formatQuantity(quantity.doubleValue());
                }
        }

        /**
         * Formats a float representing a quantity into a string
         *
         * @param quantity The quantity float to be formatted
         * @return A String with the formatted quantity
         */
        public static String formatQuantity(float quantity)
        {
                return formatQuantity((double) quantity);
        }

        /**
         * Formats an Double representing a quantity into a string
         *
         * @param quantity The quantity Double to be formatted
         * @return A String with the formatted quantity
         */
        public static String formatQuantity(Double quantity)
        {
                if (quantity == null)
                {
                        return "";
                }
                else
                {
                        return formatQuantity(quantity.doubleValue());
                }
        }

        /**
         * Formats an double representing a quantity into a string
         *
         * @param quantity The quantity double to be formatted
         * @return A String with the formatted quantity
         */
        public static String formatQuantity(double quantity)
        {
                return quantityDecimalFormat.format(quantity);
        }

        // ------------------- date handlers -------------------
        /**
         * Formats a String timestamp into a nice string
         *
         * @param timestamp String timestamp to be formatted
         * @return A String with the formatted date/time
         */
        public static String formatDate(java.sql.Timestamp timestamp)
        {
                if (timestamp == null)
                {
                        return "";
                }

                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
                        DateFormat.FULL);
                java.util.Date date = (java.util.Date) timestamp;

                return df.format(date);
        }

        // ------------------- null string handlers -------------------
        /**
         * Checks to see if the passed Object is null, if it is returns an empty but non-null string, otherwise calls toString() on the object
         *
         * @param obj1 The passed Object
         * @return The toString() of the passed Object if not null, otherwise an empty non-null String
         */
        public static String makeString(Object obj1)
        {
                if (obj1 != null)
                {
                        return obj1.toString();
                }
                else
                {
                        return "";
                }
        }

        /**
         * Checks to see if the passed string is null, if it is returns an empty but non-null string.
         *
         * @param string1 The passed String
         * @return The passed String if not null, otherwise an empty non-null String
         */
        public static String checkNull(String string1)
        {
                if (string1 != null)
                {
                        return string1;
                }
                else
                {
                        return "";
                }
        }

        /**
         * Returns the first passed String if not null, otherwise the second if not null, otherwise an empty but non-null String.
         *
         * @param string1 The first passed String
         * @param string2 The second passed String
         * @return The first passed String if not null, otherwise the second if not null, otherwise an empty but non-null String
         */
        public static String checkNull(String string1, String string2)
        {
                if (string1 != null)
                {
                        return string1;
                }
                else if (string2 != null)
                {
                        return string2;
                }
                else
                {
                        return "";
                }
        }

        /**
         * Returns the first passed String if not null, otherwise the second if not null, otherwise the third if not null, otherwise an empty but non-null String.
         *
         * @param string1 The first passed String
         * @param string2 The second passed String
         * @param string3 The third passed String
         * @return The first passed String if not null, otherwise the second if not null, otherwise the third if not null, otherwise an empty but non-null String
         */
        public static String checkNull(String string1, String string2,
                                       String string3)
        {
                if (string1 != null)
                {
                        return string1;
                }
                else if (string2 != null)
                {
                        return string2;
                }
                else if (string3 != null)
                {
                        return string3;
                }
                else
                {
                        return "";
                }
        }

        /**
         * Returns the first passed String if not null, otherwise the second if not null, otherwise the third if not null, otherwise the fourth if not null, otherwise an empty but non-null String.
         *
         * @param string1 The first passed String
         * @param string2 The second passed String
         * @param string3 The third passed String
         * @param string4 The fourth passed String
         * @return The first passed String if not null, otherwise the second if not null, otherwise the third if not null, otherwise the fourth if not null, otherwise an empty but non-null String
         */
        public static String checkNull(String string1, String string2,
                                       String string3, String string4)
        {
                if (string1 != null)
                {
                        return string1;
                }
                else if (string2 != null)
                {
                        return string2;
                }
                else if (string3 != null)
                {
                        return string3;
                }
                else if (string4 != null)
                {
                        return string4;
                }
                else
                {
                        return "";
                }
        }

        /**
         * Returns <code>pre + base + post</code> if base String is not null or empty, otherwise an empty but non-null String.
         *
         * @param base The base String
         * @param pre  The pre String
         * @param post The post String
         * @return <code>pre + base + post</code> if base String is not null or empty, otherwise an empty but non-null String.
         */
        public static String ifNotEmpty(String base, String pre, String post)
        {
                if ((base != null) && (base.length() > 0))
                {
                        return pre + base + post;
                }
                else
                {
                        return "";
                }
        }

        /**
         * Returns the first passed String if not empty, otherwise the second if not empty, otherwise an empty but non-null String.
         *
         * @param string1 The first passed String
         * @param string2 The second passed String
         * @return The first passed String if not empty, otherwise the second if not empty, otherwise an empty but non-null String
         */
        public static String checkEmpty(String string1, String string2)
        {
                if ((string1 != null) && (string1.length() > 0))
                {
                        return string1;
                }
                else if ((string2 != null) && (string2.length() > 0))
                {
                        return string2;
                }
                else
                {
                        return "";
                }
        }

        /**
         * Returns the first passed String if not empty, otherwise the second if not empty, otherwise the third if not empty, otherwise an empty but non-null String.
         *
         * @param string1 The first passed String
         * @param string2 The second passed String
         * @param string3 The third passed String
         * @return The first passed String if not empty, otherwise the second if not empty, otherwise the third if not empty, otherwise an empty but non-null String
         */
        public static String checkEmpty(String string1, String string2,
                                        String string3)
        {
                if ((string1 != null) && (string1.length() > 0))
                {
                        return string1;
                }
                else if ((string2 != null) && (string2.length() > 0))
                {
                        return string2;
                }
                else if ((string3 != null) && (string3.length() > 0))
                {
                        return string3;
                }
                else
                {
                        return "";
                }
        }

        // ------------------- web encode handlers -------------------
        /**
         * Encodes an HTTP URL query String, replacing characters used for other things in HTTP URL query strings, but not touching the separator characters '?', '=', and '&'
         *
         * @param query The plain query String
         * @return The encoded String
         */
        public static String encodeQuery(String query)
        {
                String retString;

                retString = replaceString(query, "%", "%25");
                retString = replaceString(retString, " ", "%20");

                return retString;
        }

        /**
         * Encodes a single HTTP URL query value, replacing characters used for other things in HTTP URL query strings
         *
         * @param query The plain query value String
         * @return The encoded String
         */
        public static String encodeQueryValue(String query)
        {
                String retString;

                retString = replaceString(query, "%", "%25");
                retString = replaceString(retString, " ", "%20");
                retString = replaceString(retString, "&", "%26");
                retString = replaceString(retString, "?", "%3F");
                retString = replaceString(retString, "=", "%3D");

                return retString;
        }

        /**
         * Replaces all occurances of oldString in mainString with newString
         *
         * @param mainString The original string
         * @param oldString  The string to replace
         * @param newString  The string to insert in place of the old
         * @return mainString with all occurances of oldString replaced by newString
         */
        public static String replaceString(String mainString, String oldString,
                                           String newString)
        {
                return StringUtil.replaceString(mainString, oldString, newString);
        }

        /**
         * Decodes a single query value from an HTTP URL parameter, replacing %ASCII values with characters
         *
         * @param query The encoded query value String
         * @return The plain, decoded String
         */
        public static String decodeQueryValue(String query)
        {
                String retString;

                retString = replaceString(query, "%25", "%");
                retString = replaceString(retString, "%20", " ");
                retString = replaceString(retString, "%26", "&");
                retString = replaceString(retString, "%3F", "?");
                retString = replaceString(retString, "%3D", "=");

                return retString;
        }

        // ------------------- web encode handlers -------------------
        /**
         * Encodes an XML string replacing the characters '<', '>', '"', ''', '&'
         *
         * @param inString The plain value String
         * @return The encoded String
         */
        public static String encodeXmlValue(String inString)
        {
                String retString = inString;

                retString = StringUtil.replaceString(retString, "&", "&amp;");
                retString = StringUtil.replaceString(retString, "<", "&lt;");
                retString = StringUtil.replaceString(retString, ">", "&gt;");
                retString = StringUtil.replaceString(retString, "\"", "&quot;");
                retString = StringUtil.replaceString(retString, "'", "&apos;");

                return retString;
        }

        /**
         * 返回可以显示大小的值,<br>形如4234,返回"4,2k";
         *
         * @param b
         * @return
         */
        public static String formatSize(long b)
        {
                long SIZE_K = 1024;
                long SIZE_M = SIZE_K * SIZE_K;
                long SIZE_G = SIZE_K * SIZE_K * SIZE_K;
                DecimalFormat nf = new DecimalFormat("##########.#");
                String result = null;

                if (b >= SIZE_G)
                {
                        result = nf.format((double) b / SIZE_G) + " G";
                }
                else if (b >= SIZE_M)
                {
                        result = nf.format((double) b / SIZE_M) + " M";
                }
                else if (b >= SIZE_K)
                {
                        result = nf.format((double) b / SIZE_K) + " K";
                }
                else
                {
                        result = String.valueOf(b);
                }

                return result;
        }
}
