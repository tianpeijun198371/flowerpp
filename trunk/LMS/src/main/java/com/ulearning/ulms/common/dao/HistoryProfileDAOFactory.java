/**
 * HistoryProfileDAOFactory.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.dao;

import com.ulearning.ulms.common.exceptions.HistoryProfileSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class HistoryProfileDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static HistoryProfileDAO getDAO() throws HistoryProfileSysException
        {
                HistoryProfileDAO dao = new HistoryProfileDAOImpl();

                return dao;
        }
}
