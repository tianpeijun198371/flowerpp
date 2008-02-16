/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.log4j.*;

/**
 * Class for the pair parameters.
 *
 */
import java.io.*;

import java.lang.*;

import java.util.*;
import java.util.Properties;


public class PairParameter implements IParameter
{
        //properties
        protected static Log logger = LogFactory.getLog(GetParam.class);
        private int count = 0;
        private String[] lineData = null;

        public PairParameter(String inStr)
        {
                try
                {
                        readInPairs(inStr);
                }
                catch (Exception e)
                {
                        logger.info(e);
                        e.printStackTrace();
                }
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

                for (i = 0; i < count; i++)
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
                        return lineData[i + count];
                }
        }

        public String getPairs()
        {
                if (lineData == null)
                {
                        return null;
                }

                String s = "";

                for (int i = 0; i < count; i++)
                {
                        s += (lineData[i] + "=" + lineData[i + count] + "\r\n");
                }

                return s;
        }

        public int getCount()
        {
                return count;
        }

        public void print()
        {
                logger.info("PairParameter:");

                for (int i = 0; i < count; i++)
                {
                        logger.info(lineData[i] + "  values:  " + lineData[i + count]);
                }
        }

        public String getName(int i)
        {
                if ((i >= 0) && (i < count))
                {
                        return lineData[i];
                }
                else
                {
                        return null;
                }
        }

        private void readInPairs(String instr) throws IOException
        {
                ByteArrayInputStream bin = null;
                Properties props = new Properties();

                // first delete the param caps
                String ts = removeCap(instr);

                bin = new ByteArrayInputStream(ts.getBytes());
                props.load(bin);

                Enumeration paramNames = props.propertyNames();
                count = 0;

                while (paramNames.hasMoreElements())
                {
                        count++;
                        paramNames.nextElement();
                }

                logger.info("total count:" + count);

                int i = 0;
                String s;
                lineData = new String[count * 2];
                paramNames = props.propertyNames();

                while (paramNames.hasMoreElements())
                {
                        s = (String) paramNames.nextElement();
                        s = s.trim();
                        lineData[i] = s;
                        lineData[i + count] = props.getProperty(s).trim();
                        i++;
                }
        }

        private String removeCap(String s)
        {
                if ((s == null) || (s.trim().equals("")))
                {
                        return null;
                }

                String st = s.trim();
                String t;
                String result = "";

                int b = 0;
                int e;
                e = st.indexOf("\r\n", b);

                while (e > 0)
                {
                        t = st.substring(b, e).trim();
                        logger.info(t);

                        if (!t.equals(""))
                        {
                                if ((t.charAt(0) == '[') && (t.charAt(t.length() - 1) == ']'))
                                {
                                        ;
                                }
                                else
                                {
                                        result += (t + "\r\n");
                                }
                        }

                        if ((e + 2) < st.length())
                        {
                                b = e + 2;
                                e = st.indexOf("\r\n", b);
                        }
                        else
                        {
                                break;
                        }
                }

                result = result.substring(0, result.length() - 2);
                logger.info("final:\r\n" + result);

                return result;
        }
}
