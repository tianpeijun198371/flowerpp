/***********************************************************************
 * Module:  monthfinance.java
 * Author:  liz
 * Modified: 2005年12月31日 11:11:06
 * Purpose: Defines the Class monthfinance
 ***********************************************************************/

package com.ulearning.ulms.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 产生财务的月日期段
 *
 * @author Liz
 * @ date 2005.12.31
 */
public class monthfinance implements intfcmonth
{
        /**
         * @param monthmark 要统计的月份（1、2、3...12）
         *                  <p/>
         *                  Map 包含一个月的开始和结束日期key=beginDate 和　endDate
         */
        public Map makeDateRange(int monthmark)
        {
                // TODO: implement
                Map map = null;
                switch (monthmark)
                {
                        case 1:
                                map = new HashMap();
                                return map;
                        case 2:
                                map = new HashMap();
                                return map;
                        case 3:
                                map = new HashMap();
                                return map;
                        case 4:
                                map = new HashMap();
                                return map;
                        case 5:
                                map = new HashMap();
                                return map;
                        case 6:
                                map = new HashMap();
                                return map;
                        case 7:
                                map = new HashMap();
                                return map;
                        case 8:
                                map = new HashMap();
                                return map;
                        case 9:
                                map = new HashMap();
                                return map;
                        case 10:
                                map = new HashMap();
                                return map;
                        case 11:
                                map = new HashMap();
                                return map;
                        case 12:
                                map = new HashMap();
                                return map;
                }
                return map;
        }

}