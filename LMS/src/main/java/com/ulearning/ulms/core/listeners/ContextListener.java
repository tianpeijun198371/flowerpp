/**
 * ContextListener.java.
 * User: fengch Date: 2005-6-20 18:20:31
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.listeners;

import com.ulearning.ulms.core.util.Timer;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.util.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextListener implements ServletContextListener
{
        protected static Log logger = LogFactory.getLog(ContextListener.class);
        //用户session的计时器
        private Timer timer = null;
        public void contextInitialized(ServletContextEvent event)
        {
                logger.info("contextInitialized---contextInitialized!");

                //参数用于表示DateConvert类负责处理java.util.Date类型的转化
                //ConvertUtils.register(new DateConvert(),java.util.Date.class);

                //init the hibernate xml
                try
                {
                        HibernateUtil.closeSession();
                }
                catch (Exception ex)
                {
                        logger.info("Error:",ex);
                }

                timer = new Timer();
        }

        public void contextDestroyed(ServletContextEvent event)
        {
                logger.info("contextDestroyed---contextDestroyed!");
                if (timer == null)
                {
                        return;
                }

                long totalTime = timer.getTotal();
                logger.info("contextDestroyed---totalTime=" +
                        totalTime);
                logger.info("contextDestroyed---totalTimeDescription=" +
                                DateTimeUtil.getDateTimeDescription(totalTime));
        }

        public long getServerRunTime()
        {
                return timer.getTotal();
        }
}
