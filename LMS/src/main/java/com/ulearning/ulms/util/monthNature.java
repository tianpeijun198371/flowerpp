/***********************************************************************
 * Module:  monthNature.java
 * Author:  liz
 * Modified: 2005年12月31日 11:10:55
 * Purpose: Defines the Class monthNature
 ***********************************************************************/

package com.ulearning.ulms.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 产生自然月的日期段。
 */
public class monthNature implements intfcmonth
{
        /**
         * @param monthmark 要统计的月份（1、2、3...12）
         * @return Map 包含一个月的开始和结束日期key=beginDate 和　endDate
         */
        public java.util.Map makeDateRange(int monthmark)
        {
                // TODO: implement
                Map map = null;
                switch (monthmark)
                {
                        case 1:
                                map = new HashMap();
                                map = make("01-01", "01-31");
                                return map;
                        case 2:
                                map = new HashMap();
                                map = make("02-01", "02-28");
                                return map;
                        case 3:
                                map = new HashMap();
                                map = make("03-01", "03-31");
                                return map;
                        case 4:
                                map = new HashMap();
                                map = make("04-01", "04-30");
                                return map;
                        case 5:
                                map = new HashMap();
                                map = make("05-01", "05-31");
                                return map;
                        case 6:
                                map = new HashMap();
                                map = make("06-01", "06-30");
                                return map;
                        case 7:
                                map = new HashMap();
                                map = make("07-01", "07-31");
                                return map;
                        case 8:
                                map = new HashMap();
                                map = make("08-01", "08-31");
                                return map;
                        case 9:
                                map = new HashMap();
                                map = make("09-01", "09-30");
                                return map;
                        case 10:
                                map = new HashMap();
                                map = make("10-01", "10-31");
                                return map;
                        case 11:
                                map = new HashMap();
                                map = make("11-01", "11-30");
                                return map;
                        case 12:
                                map = new HashMap();
                                map = make("12-01", "12-31");
                                return map;
                }
                return map;
        }

        /**
         * 取月份的日期段
         *
         * @return Map 包含一个月的开始和结束日期key=beginDate 和　endDate
         */
        private Map make(String bStr, String eStr)
        {
                Map map = new HashMap();
                java.util.Date date = new java.util.Date();
                String str = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
                StringBuffer beginStr = new StringBuffer();
                StringBuffer endStr = new StringBuffer();
                beginStr.append(str.substring(0, 4));
                beginStr.append("-");
                beginStr.append(bStr);
                map.put("beginDate", beginStr.toString());//开始日期

                endStr.append(str.substring(0, 4));
                endStr.append("-");
                endStr.append(eStr);
                map.put("endDate", endStr.toString());//结束日期
                return map;
        }

}