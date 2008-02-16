/**
 * StringUtil.java.
 * User: fengch  Date: 2004-4-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import com.ulearning.ulms.util.log.LogUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


public class StringUtil
{
        /**
         * Escapes any html characters in the input string.
         *
         * @param in
         * @return
         */
        public static String escape(String in)
        {
                StringBuffer out = new StringBuffer();

                for (int i = 0; i < in.length(); i++)
                {
                        char c = in.charAt(i);

                        switch (c)
                        {
                                case '<':
                                        out.append("&lt;");

                                        break;

                                case '>':
                                        out.append("&gt;");

                                        break;

                                case '&':
                                        out.append("&amp;");

                                        break;

                                case '"':
                                        out.append("&quot;");

                                        break;

                                case '\n':
                                        out.append("<br>");

                                        break;

                                default:
                                        out.append(c);

                                        break;
                        }
                }

                return out.toString();
        }

        /**
         * Create By: xie jianhua
         * Filter javascript and java characters in the input string
         * fit input for type=text and type=hidden
         *
         * @param strInput
         * @return
         */
        public static String checkInputText(String strInput)
        {
                String res = "";

                if (strInput != null)
                {
                        res = checkInput(strInput.trim());
                }

                return res;
        }

        /**
         * Create By: xie jianhua
         * Filter javascript and java characters in the input string
         * fit TEXTAREA
         *
         * @param strInput
         * @return
         */
        public static String checkInputTextArea(String strInput)
        {
                return checkInput(strInput);
        }

        /**
         * Create By: xie jianhua
         * Filter javascript and java characters in the input string.
         *
         * @param strInput
         * @return
         */
        public static String checkInput(String strInput)
        {
                String res = "";

                if (strInput != null)
                {
                        StringBuffer out = new StringBuffer();
                        String in = strInput;

                        for (int i = 0; i < in.length(); i++)
                        {
                                char c = in.charAt(i);

                                switch (c)
                                {
                                        case '<':
                                                out.append("&#060;");

                                                break;

                                        case '>':
                                                out.append("&#062;");

                                                break;

                                        case '&':
                                                out.append("&#038;");

                                                break;

                                        case '"':
                                                out.append("&#034;");

                                                break;

                                        case '\'':
                                                out.append("&#039;");

                                                break;

                                        case '\\':
                                                out.append("&#034;");

                                                break;

                                        case '%':
                                                out.append("&#037;");

                                                break;

                                        default:
                                                out.append(c);

                                                break;
                                }
                        }

                        res = out.toString();
                }

                return res;
        }

        /**
         * Create By: xie jianhua
         * Fliter the input content from client.
         * example:result=htmlFilter("   test test.")
         * the value of [result] is "&nbsp; &nbsp;test test."
         */
        public static String htmlFilter(String strContent)
        {
                String strHtmlFilter = ((strContent == null) ? "" : strContent);

                String VBCRLF = "\n";
                String VBCRLF1 = "\n ";
                String ASPACE = " ";
                String ASPACE1 = "  ";
                String BR = "<br>";
                String BR1 = "<br>&nbsp;";
                String SPACETAG = "&nbsp;";
                int P = -1;

                if (strHtmlFilter.length() != 0)
                {
                        strHtmlFilter = replaceString(strHtmlFilter, VBCRLF1, BR1);
                        strHtmlFilter = replaceString(strHtmlFilter, VBCRLF, BR);

                        if (strHtmlFilter.substring(0, 1).equals(ASPACE))
                        {
                                strHtmlFilter = SPACETAG + strHtmlFilter.substring(1);
                        }

                        P = strHtmlFilter.indexOf(ASPACE1);

                        while (P > 0)
                        {
                                strHtmlFilter = strHtmlFilter.substring(0, P) + SPACETAG +
                                        ASPACE + strHtmlFilter.substring(P + 2);
                                P = strHtmlFilter.indexOf(ASPACE + ASPACE);
                        }
                }

                return strHtmlFilter;
        }

        public static String htmlFilter2(String strContent)
        {
                String strHtmlFilter = ((strContent == null) ? "" : strContent);

                String VBCRLF = "\n";
                String VBCRLF1 = "\n ";
                String ASPACE = " ";
                String ASPACE1 = "  ";
                String BR = "<br>";
                String BR1 = "<br>&nbsp;";
                String SPACETAG = "&nbsp;";
                int P = -1;

                if (strHtmlFilter.length() != 0)
                {
                        //strHtmlFilter = replaceString(strHtmlFilter, VBCRLF1, BR1);
                        //strHtmlFilter = replaceString(strHtmlFilter, VBCRLF, BR);
                        if (strHtmlFilter.substring(0, 1).equals(ASPACE))
                        {
                                strHtmlFilter = SPACETAG + strHtmlFilter.substring(1);
                        }

                        P = strHtmlFilter.indexOf(ASPACE1);

                        while (P > 0)
                        {
                                strHtmlFilter = strHtmlFilter.substring(0, P) + SPACETAG +
                                        ASPACE + strHtmlFilter.substring(P + 2);
                                P = strHtmlFilter.indexOf(ASPACE + ASPACE);
                        }
                }

                return strHtmlFilter;
        }

        public static String htmlFilter(String strContent, int lineMaxLength)
        {
                return htmlFilter(dealLongStr(strContent, lineMaxLength));
        }

        //将字符串str按space分成数组
        public static String[] splitString(String str, String space)
        {
                String str1 = str.trim();
                int i;
                int s;
                int k;
                k = space.length();

                for (i = 0; str1.indexOf(space) != -1; i++)
                {
                        str1 = str1.substring(str1.indexOf(space) + k, str1.length());
                }

                int bound = i + 1;
                str1 = str;

                String[] array1 = new String[bound];
                array1[0] = "";

                for (i = 0; str1 != ""; i++)
                {
                        s = str1.indexOf(space);

                        if (s != -1)
                        {
                                array1[i] = str1.substring(0, s);
                                str1 = str1.substring(s + k, str1.length());
                        }
                        else
                        {
                                array1[i] = str1;
                                str1 = "";
                        }
                }

                return array1;
        }

        /**
         * Create By: xie jianhua
         * add Enter after long string  while long string exceed  lineMaxLength
         * user in html page
         *
         * @param longStr lineMaxLength
         * @return
         */
        public static String dealLongStr(String longStr, int lineMaxLength)
        {
                String segment = longStr;

                if ((segment != null) && !segment.trim().equals("") &&
                        !segment.trim().equals("null") && (lineMaxLength > 0))
                {
                        int length = 0;
                        int flag = 0;

                        for (int n = 0; n < segment.length(); n++)
                        {
                                if ((segment.charAt(n) <= 32) || (segment.charAt(n) > 126))
                                {
                                        length = 0;
                                }
                                else
                                {
                                        length++;

                                        if ((segment.charAt(n) == 38) &&
                                                (segment.charAt(n + 1) == 35) &&
                                                (segment.charAt(n + 5) == 59))
                                        {
                                                boolean bn2 = (segment.charAt(n + 2) >= 48) &&
                                                        (segment.charAt(n + 2) <= 57);
                                                boolean bn3 = (segment.charAt(n + 3) >= 48) &&
                                                        (segment.charAt(n + 3) <= 57);
                                                boolean bn4 = (segment.charAt(n + 4) >= 48) &&
                                                        (segment.charAt(n + 4) <= 57);

                                                if (bn2 && bn3 && bn4)
                                                {
                                                        n = n + 5;
                                                        flag = -5;
                                                }
                                        }
                                        else
                                        {
                                                flag = 0;
                                        }
                                }

                                if ((length > 1) && ((length % lineMaxLength) == 1))
                                {
                                        segment = segment.substring(0, n + flag) + "\r\n" +
                                                segment.substring(n + flag);
                                        flag = 0;
                                        n = n + 2;
                                }
                        }
                }

                return segment;
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
                if (mainString == null)
                {
                        return null;
                }

                if ((oldString == null) || (oldString.length() == 0))
                {
                        return mainString;
                }

                if (newString == null)
                {
                        newString = "";
                }

                int i = mainString.lastIndexOf(oldString);

                if (i < 0)
                {
                        return mainString;
                }

                StringBuffer mainSb = new StringBuffer(mainString);

                while (i >= 0)
                {
                        mainSb.replace(i, i + oldString.length(), newString);
                        i = mainString.lastIndexOf(oldString, i - 1);
                }

                return mainSb.toString();
        }

        /**
         * Creates a single string from a List of strings seperated by a delimiter.
         *
         * @param list  a list of strings to join
         * @param delim the delimiter character(s) to use. (null value will join with no delimiter)
         * @return a String of all values in the list seperated by the delimiter
         */
        public static String join(List list, String delim)
        {
                if ((list == null) || (list.size() < 1))
                {
                        return null;
                }

                StringBuffer buf = new StringBuffer();
                Iterator i = list.iterator();

                while (i.hasNext())
                {
                        buf.append((String) i.next());

                        if (i.hasNext())
                        {
                                buf.append(delim);
                        }
                }

                return buf.toString();
        }

        /**
         * Splits a String on a delimiter into a List of Strings.
         *
         * @param str   the String to split
         * @param delim the delimiter character(s) to join on (null will split on whitespace)
         * @return a list of Strings
         */
        public static List split(String str, String delim)
        {
                List splitList = null;
                StringTokenizer st = null;

                if (str == null)
                {
                        return splitList;
                }

                if (delim != null)
                {
                        st = new StringTokenizer(str, delim);
                }
                else
                {
                        st = new StringTokenizer(str);
                }

                if ((st != null) && st.hasMoreTokens())
                {
                        splitList = new ArrayList();

                        while (st.hasMoreTokens())
                        {
                                splitList.add(st.nextToken());
                        }
                }

                return splitList;
        }

        /**
         * Encloses each of a List of Strings in quotes.
         *
         * @param list List of String(s) to quote.
         */
        public static List quoteStrList(List list)
        {
                List tmpList = list;

                list = new ArrayList();

                Iterator i = tmpList.iterator();

                while (i.hasNext())
                {
                        String str = (String) i.next();

                        str = "'" + str + "''";
                        list.add(str);
                }

                return list;
        }

        /**
         * Removes all spaces from a string
         */
        public static String removeSpaces(String str)
        {
                StringBuffer newString = new StringBuffer();

                for (int i = 0; i < str.length(); i++)
                {
                        if (str.charAt(i) != ' ')
                        {
                                newString.append(str.charAt(i));
                        }
                }

                return newString.toString();
        }

        /**
         * null -> "" and trim(str)
         * athor:xiejh
         */
        public static String nullToStr(String str)
        {
                String tmp = str;

                if (tmp == null)
                {
                        tmp = "";
                }
                else
                {
                        tmp = tmp.trim();

                        if (tmp.equals("null"))
                        {
                                tmp = "";
                        }
                }

                return tmp;
        }

        /**
         * 得到随机字符
         * athor:xiejh
         */
        public static String randomStr(int n)
        {
                String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
                String str2 = "";
                int len = str1.length() - 1;
                double r;

                for (int i = 0; i < n; i++)
                {
                        r = (Math.random()) * len;
                        str2 = str2 + str1.charAt((int) r);
                }

                return str2;
        }

        public static String checkEmpty(String string)
        {
                if ((string != null) && (string.length() > 0))
                {
                        return string;
                }
                else
                {
                        return "";
                }
        }

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

        public static boolean checkBoolean(String str)
        {
                return checkBoolean(str, false);
        }

        public static boolean checkBoolean(String str, boolean defaultValue)
        {
                if (defaultValue)
                {
                        //default to true, ie anything but false is true
                        return !"false".equals(str);
                }
                else
                {
                        //default to false, ie anything but true is false
                        return "true".equals(str);
                }
        }

        /**
         * ISO_8559_1---------->GBK
         *
         * @param strIn
         * @return GBK
         */
        public static String IsoToGBK(String strIn)
        {
                String strOut = null;

                if (strIn == null)
                {
                        return "";
                }

                try
                {
                        byte[] b = strIn.getBytes("ISO8859_1");
                        strOut = new String(b, "GBK");
                }
                catch (UnsupportedEncodingException e)
                {
                }

                return strOut;
        }

        /**
         * GBK------------>ISO_8559_1
         *
         * @param strIn
         * @return ISO_8559_1
         */
        public static String GBKToIso(String strIn)
        {
                byte[] b;
                String strOut = null;

                if (strIn == null)
                {
                        return "";
                }

                try
                {
                        b = strIn.getBytes("GBK");
                        strOut = new String(b, "ISO8859_1");
                }
                catch (UnsupportedEncodingException e)
                {
                }

                return strOut;
        }

        /**
         * parse a string to a integer
         */
        public static int parseInt(String str)
        {
                int i = 0;

                try
                {
                        if (str != null)
                        {
                                i = Integer.parseInt(str);
                        }
                }
                catch (Exception ex)
                {
                        LogUtil.debug("core",
                                "[StringUtil]parseInt***********************parse '" + str +
                                        "' error!");
                }

                return i;
        }

        /**
         * parse a string to a Float
         */
        public static float parseFloat(String str)
        {
                float i = 0;

                try
                {
                        if (str != null)
                        {
                                i = Float.parseFloat(str);
                        }
                }
                catch (Exception ex)
                {
                        LogUtil.debug("core",
                                "[StringUtil]parseFloat***********************parse '" + str +
                                        "' error!");
                }

                return i;
        }

        /**
         * parse a string to a long value.
         */
        public static long parseLong(String str)
        {
                long i = 0;

                try
                {
                        if (str != null)
                        {
                                i = Long.parseLong(str);
                        }
                }
                catch (Exception ex)
                {
                        LogUtil.debug("core",
                                "[StringUtil]parseLong***********************parse '" + str +
                                        "' error!");
                }

                return i;
        }

        /**
         * return  the string truncated,<br>
         * if size>str.length,then  truncat the str.
         *
         * @param str
         * @param size
         * @return
         */
        public static String truncate(String str, int size)
        {
                if (str == null)
                {
                        return "";
                }

                if ((size < 0) || (size >= str.length()))
                {
                        return str;
                }
                else
                {
                        return str.substring(0, size) + "...";
                }
        }

        //start: add for answerquestion

        /**
         * 过滤掉所有HTML标签。
         * 如过滤字符串为"<p class=dd>ffffj</p>",,则得到:"ffffj"
         *
         * @param in
         * @return
         */
        public static String filterByHTMLTag(String in)
        {
                if (in == null)
                {
                        return "";
                }

                replaceString(in, "<br>", "\n");
                replaceString(in, "<BR>", "\n");
                replaceString(in, "<br/>", "\n");
                replaceString(in, "<BR/>", "\n");
                replaceString(in, "<p>", "\n");
                replaceString(in, "<P>", "\n");

                StringBuffer out = new StringBuffer();
                boolean isOK = true;

                for (int i = 0; i < in.length(); i++)
                {
                        char c = in.charAt(i);

                        switch (c)
                        {
                                case '<':
                                        isOK = false;

                                        break;

                                case '>':
                                        isOK = true;

                                        break;

                                default:

                                        if (isOK)
                                        {
                                                out.append(c);
                                        }
                        }
                }

                return out.toString();
        }

        /**
                 * Encode a string using Base64 encoding. Used when storing passwords as
                 * cookies.
                 *
                 * This is weak encoding in that anyone can use the decodeString routine to
                 * reverse the encoding.
                 *
                 * @param str
                 * @return String
                 */
                public static String encodeByBase64(String str) {
                        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
                        return encoder.encodeBuffer(str.getBytes()).trim();
                }

                /**
                 * Decode a string using Base64 encoding.
                 * @param str
                 * @return String
                 */
                public static String decodeByBase64(String str) {
                        sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
                        try {
                                return new String(dec.decodeBuffer(str));
                        } catch (IOException io) {
                                throw new RuntimeException(io.getMessage(), io.getCause());
                        }
                }

}
