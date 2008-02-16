/**
 * CSVParser.java.
 * User: Fengch  Date: 2005-9-4 23:34:46
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;

import java.io.*;

import java.util.ArrayList;
import java.util.List;


public class CSVParser
{
        private static final Logger logger = Logger.getLogger(CSVParser.class);
        private char separator = ',';
        private String charset = null;
        private String fileName;

        //是否解析时,是否包含标题栏
        private boolean suppressHeaders = false;
        private BufferedReader input = null;
        private String[] columnNames = null;

        public CSVParser()
        {
        }

        public CSVParser(String fileName) throws java.lang.Exception
        {
                this(fileName, ',', false, null);
        }

        public CSVParser(String fileName, char separator, boolean suppressHeaders,
                         String charset) throws java.lang.Exception
        {
                this.separator = separator;
                this.suppressHeaders = suppressHeaders;
                this.fileName = fileName;
                this.charset = charset;

                if (charset != null)
                {
                        input = new BufferedReader(new InputStreamReader(
                                new FileInputStream(fileName), charset));
                }
                else
                {
                        input = new BufferedReader(new InputStreamReader(
                                new FileInputStream(fileName)));
                }
        }

        public CSVParser(File file, char separator, boolean suppressHeaders,
                         String charset) throws java.lang.Exception
        {
                this.separator = separator;
                this.suppressHeaders = suppressHeaders;
                this.fileName = file.getName();
                this.charset = charset;

                if (charset != null)
                {
                        input = new BufferedReader(new InputStreamReader(
                                new FileInputStream(file), charset));
                }
                else
                {
                        input = new BufferedReader(new InputStreamReader(
                                new FileInputStream(file)));
                }
        }

        public List parse() throws IOException
        {
                List list = new ArrayList();
                String[] data = null;

                // input = new BufferedReader(new FileReader(fileName));
                String line;
                int lines = 0;

                while ((line = input.readLine()) != null)
                {
                        logger.debug("parse--- line = " + line);
                        line = StringUtils.trimToEmpty(line);

                        if (line.equals(""))
                        {
                                continue; //越过此行
                        }

                        if (lines == 0)
                        {
                                if (this.suppressHeaders)
                                {
                                        // No column names available. Read first data line and determine number of colums.
                                        data = parseLine(line);
                                        list.add(data);
                                        columnNames = new String[data.length];

                                        for (int i = 0; i < data.length; i++)
                                        {
                                                columnNames[i] = "COLUMN" + String.valueOf(i + 1);
                                        }

                                        data = null;

                                        // throw away.
                                }
                                else
                                {
                                        columnNames = parseLine(line);
                                }
                        }
                        else
                        {
                                data = parseLine(line);
                                list.add(data);
                        }

                        lines++;
                }

                if (suppressHeaders)
                {
                        lines--;
                }

                logger.debug("parse--- parse lines = " + lines);

                return list;
        }

        public String[] parseLine(String buf)
        {
                logger.debug("\n\nparseLine--- start  strs = " + buf);
                //buf=StringUtils.replace(buf,"\"","");
                logger.debug("\n\nparseLine--- start  strs = " + buf);

                String[] strs = StringUtils.split(buf, String.valueOf(separator));
                logger.info("strs.length = " + strs.length);

                for (int i = 0; i < strs.length; i++)
                {
                        strs[i] = trimQuotationMarks(strs[i]);
                        logger.debug("parseLine--- parsed strs " + i + "  = " + strs[i]);
                }

                return strs;
        }

        /**
         * 去掉字符串两端的 "
         *
         * @param str
         * @return
         */
        private String trimQuotationMarks(String str)
        {
                if (str == null)
                {
                        return "";
                }

                str = str.trim();

                if ((str.charAt(0) == '\"') && (str.charAt(str.length() - 1) == '\"'))
                {
                        return str.substring(1, str.length() - 1);
                }
                else if ((str.charAt(0) == '\'') &&
                        (str.charAt(str.length() - 1) == '\''))
                {
                        return str.substring(1, str.length() - 1);
                }

                str = str.trim();

                return str;
        }
}
