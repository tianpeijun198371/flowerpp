/**
 * ScoreManageDAOFactory.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.dao;

import com.ulearning.ulms.common.exceptions.ScoreSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class ScoreManageDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static ScoreManageDAO getDAO() throws ScoreSysException
        {
                ScoreManageDAO dao = null;

                try
                {
                        dao = new ScoreManageDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("common",
                                "[ScoreManageDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new ScoreSysException(se);
                }

                return dao;
        }
}
