/**
 * CalendarDAOFactory.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.dao;

import com.ulearning.ulms.tools.calendar.exceptions.CalendarSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class CalendarDAOFactory
{
        public static CalendarDAO getDAO() throws CalendarSysException
        {
                CalendarDAO dao = null;

                try
                {
                        dao = new CalendarDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("course",
                                "[CalendarDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new CalendarSysException(se);
                }

                return dao;
        }
}
