package com.ulearning.ulms.evaluate.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jfree.data.time.*;

import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: ff
 * Date: 2007-2-4
 * Time: 23:55:37
 * To change this template use File | Settings | File Templates.
 */
public class PerformanceUtil
{
        protected final static Log logger = LogFactory.getLog(PerformanceUtil.class);
        private static final long PERIOD_HOUR = 60 * 60 * 1000;
        private static final long PERIOD_DAY = 24 * PERIOD_HOUR;
        private static final long PERIOD_WEEK = 7 * PERIOD_DAY;
        private static final long PERIOD_MONTH = 4 * PERIOD_WEEK;

        /**
         * 根据开始和结束时间，返回TimePeriod's class。
         * 若间隔 <1 Hour,返回 Minute.class
         * 若间隔 <1 Day,返回 Hour.class
         * 若间隔 <1 Week,返回 Day.class
         * 若间隔 <1 Month,返回 Week.class
         * 否则 返回TimePeriod=Month
         *
         * @param beginTime
         * @param endTime
         * @return
         */
        public static int getTimePeriod(Date beginTime, Date endTime)
        {
                long period = endTime.getTime() - beginTime.getTime();
                logger.info("getTimePeriod----period=" + period);

                if (period <= (3 * PERIOD_HOUR))
                {
                        logger.info("getTimePeriod----Minute.class!");

                        return PerformanceKeys.TIMEPERIOD_MINUTE;
                }
                else if (period < (5 * PERIOD_DAY))
                {
                        logger.info("getTimePeriod----Hour.class!");

                        return PerformanceKeys.TIMEPERIOD_HOURH;
                }
                else if (period < (14 * PERIOD_WEEK))
                {
                        logger.info("getTimePeriod----Day.class!");

                        return PerformanceKeys.TIMEPERIOD_DAY;
                }
                else if (period < (30 * PERIOD_MONTH))
                {
                        logger.info("getTimePeriod----Week.class!");

                        return PerformanceKeys.TIMEPERIOD_WEEK;
                }
                else
                {
                        logger.info("getTimePeriod----Month.class!");

                        return PerformanceKeys.TIMEPERIOD_MONTH;
                }
        }
}
