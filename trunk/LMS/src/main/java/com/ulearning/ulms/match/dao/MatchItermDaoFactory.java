/**
 * MatchItermDaoFactory.java.
 * User: zhangy Date: 2005-6-6 14:08:55
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.dao;

import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.util.log.LogUtil;


public class MatchItermDaoFactory
{
        public static MatchItermDao getDAO() throws MatchDaoSysException
        {
                MatchItermDao dao = null;

                try
                {
                        dao = new MatchItermDaoImpl();
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
