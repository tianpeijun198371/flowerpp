/***********************************************************************
 * Module:  intfcmonth.java
 * Author:  liz
 * Modified: 2005年12月31日 11:09:01
 * Purpose: Defines the Interface intfcmonth
 ***********************************************************************/

package com.ulearning.ulms.util;

import java.util.*;

/**
 * 产生单月时间段，用来作按月统计时使用，当用户选了某个月，根据用户的选择产生对应的日期段，供查询统计来使用。
 *
 * @author Liz
 * @ date 2005.12.31
 */
public interface intfcmonth
{
        /**
         * @param monthmark 要统计的月份（1、2、3...12）
         *                  <p/>
         *                  Map 包含一个月的开始和结束日期key=beginDate 和　endDate
         */
        java.util.Map makeDateRange(int monthmark);

}