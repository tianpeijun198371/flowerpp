/**
 * MatchDaoFactory.java.
 * User: zhangy Date: 2005-6-3 9:16:44
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.dao;

import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.util.log.LogUtil;


public class MatchDaoFactory
{
        public static MatchDao getDAO() throws MatchDaoSysException
        {
                MatchDao dao = null;

                try
                {
                        dao = new MatchDaoImpl();
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
