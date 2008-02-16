/**
 * PreferenceDAOFactory.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.dao;

import com.ulearning.ulms.tools.calendar.exceptions.PreferenceSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class PreferenceDAOFactory
{
        public static PreferenceDAO getDAO() throws PreferenceSysException
        {
                PreferenceDAO dao = null;

                try
                {
                        dao = new PreferenceDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("course",
                                "[PreferenceDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new PreferenceSysException(se);
                }

                return dao;
        }
}
