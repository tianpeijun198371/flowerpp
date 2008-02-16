/**
 * MatchDAOFactory.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.dao;

import com.ulearning.ulms.common.match.exceptions.MatchSysException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class MatchDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static MatchDAO getDAO() throws MatchSysException
        {
                MatchDAO dao = null;
                Connection conn = null;
                String DBProductName = null;

                try
                {
                        conn = DBUtil.getConnection();
                        DBProductName = conn.getMetaData().getDatabaseProductName().trim();
                        conn.close();
                        LogUtil.debug("common",
                                "[MatchDAOFactory]---- the DBProductName:" + DBProductName);

                        dao = new MatchDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("common",
                                "[MatchDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new MatchSysException(se);
                }

                return dao;
        }
}
