/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

/**
 *  Class for the parameter input table like.
 *
 */

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class TableParameter implements IParameter
{
        protected static Log logger = LogFactory.getLog(GetParam.class);

        //properties
        private String[] lineData = null;

        public TableParameter(String inStr)
        {
                readInArray(inStr);
        }

        public String getParameter(String pName)
        {
                if (lineData == null)
                {
                        return null;
                }

                if ((pName == null) || pName.trim().equals(""))
                {
                        return null;
                }

                boolean found = false;
                int i;
                String s = pName.toLowerCase(); //just for aicc, for in aicc, the parameter name is not case sensitivity

                for (i = 0; i < (lineData.length / 2); i++)
                {
                        if (lineData[i].toLowerCase().equals(s))
                        {
                                found = true;

                                break;
                        }
                }

                //logger.info("parameter number:"+i+ "  find:"+found);
                if (!found)
                {
                        return null;
                }
                else
                {
                        return lineData[i + (lineData.length / 2)];
                }
        }

        public void print()
        {
                logger.info("Table parameter:");

                for (int i = 0; i < (lineData.length / 2); i++)
                {
                        logger.info(lineData[i] + "  values:  " +
                                lineData[i + (lineData.length / 2)]);
                }
        }

        public String getPairs()
        {
                if (lineData == null)
                {
                        return null;
                }

                String s = "";

                for (int i = 0; i < (lineData.length / 2); i++)
                {
                        s += (lineData[i] + "=" + lineData[i + (lineData.length / 2)] +
                                "\r\n");
                }

                return s;
        }

        public int getCount()
        {
                return lineData.length / 2;
        }

        public String getName(int i)
        {
                if ((i >= 0) && (i < (lineData.length / 2)))
                {
                        return lineData[i];
                }
                else
                {
                        return null;
                }
        }

        private void readInArray(String str)
        {
                int columns = 0;
                int start = 1;
                int stop;
                int i;
                int j;
                String st;

                // cat the column define line with data line
                String s = str.replaceAll("\r\n", ",");

                while (start > 0)
                {
                        columns++;
                        start = s.indexOf(",", start) + 1;
                }

                lineData = new String[columns];

                start = 0;
                stop = 0;

                for (i = 0; i < columns; i++)
                {
                        stop = s.indexOf(",", start);

                        //logger.info("stop:"+stop);
                        if (stop < 0)
                        {
                                st = s.substring(start, s.length());
                        }
                        else
                        {
                                st = s.substring(start, stop);
                        }

                        if (i < (columns / 2))
                        {
                                st = st.trim();

                                if ((st != null) || !st.equals(""))
                                {
                                        st = st.trim();
                                }
                        }

                        //logger.info("start:"+start+ "   stop:"+stop+ "   str:"+st + "   remove:"+remove(st));
                        start = stop + 1;
                        lineData[i] = remove(st);
                }
        }

        private String remove(String str)
        {
                str = StringUtils.trimToEmpty(str);
                logger.info("str = >" + str + "<");

                if (!str.equals(""))
                {
                        logger.info("s.charAt(0) = >" + str.charAt(0) + "<");

                        String s = str;

                        if (s.charAt(0) == '"')
                        {
                                s = s.substring(1, s.length());
                        }

                        logger.info("str1 = >" + str + "<");
                        logger.info("s.charAt(s.length() - 1) = >" +
                                s.charAt(s.length() - 1) + "<");

                        if (s.charAt(s.length() - 1) == '"')
                        {
                                s = s.substring(0, s.length() - 1);
                        }

                        return s;
                }
                else
                {
                        return "";
                }
        }
}
