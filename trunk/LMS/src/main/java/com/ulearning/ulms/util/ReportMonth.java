/***********************************************************************
 * Module:  ReportMonth.java
 * Author:  liz
 * Modified: 2005年12月31日 13:15:35
 * Purpose: Defines the Class ReportMonth
 ***********************************************************************/

package com.ulearning.ulms.util;

import java.util.Map;

/**
 * 产生报表单月对应的日期段。
 */
public class ReportMonth
{
        private intfcmonth state;

        /**
         * @param state
         */
        public void setState(intfcmonth state)
        {
                // TODO: implement
                this.state = state;
        }

        /**
         * @param monthmark 要统计的月份（1、2、3...12）
         *                  <p/>
         *                  Map 包含一个月的开始和结束日期key=beginDate 和　endDate
         */
        public Map makeDateRange(int monthmark)
        {
                // TODO: implement
                return state.makeDateRange(monthmark);
        }

}