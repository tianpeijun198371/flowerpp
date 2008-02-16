/**
 * LmslogOperDescDAOFactory.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.dao;

import com.ulearning.ulms.lmslog.exceptions.LmslogOperDescSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class LmslogOperDescDAOFactory
{
        public static LmslogOperDescDAO getDAO() throws LmslogOperDescSysException
        {
                LmslogOperDescDAO dao = null;

                try
                {
                        dao = new LmslogOperDescDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("common",
                                "[LmslogOperDescDAOFactory]======================SQLException=" +
                                        se.getMessage());
                }

                return dao;
        }
}
