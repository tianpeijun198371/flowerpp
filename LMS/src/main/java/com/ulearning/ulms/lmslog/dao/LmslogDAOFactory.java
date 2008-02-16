/**
 * LmslogDAOFactory.java.
 * User: keyh  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.dao;

import com.ulearning.ulms.lmslog.exceptions.LmslogSysException;
import com.ulearning.ulms.tools.message.dao.MessageDAO;
import com.ulearning.ulms.tools.message.dao.MessageDAOImpl;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class LmslogDAOFactory
{
        public static LmslogDAO getDAO() throws LmslogSysException
        {
                LmslogDAO dao = null;

                try
                {
                        dao = new LmslogDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("common",
                                "[LmslogDAOFactory]======================SQLException=" +
                                        se.getMessage());
                }

                return dao;
        }
}
