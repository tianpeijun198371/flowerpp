/**
 * HistoryProfileWebImpl.java.
 * User: fengch  Date: 2004-5-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.webimpls;

import com.ulearning.ulms.common.dao.HistoryProfileDAO;
import com.ulearning.ulms.common.dao.HistoryProfileDAOFactory;
import com.ulearning.ulms.common.exceptions.HistoryProfileSysException;
import com.ulearning.ulms.common.model.HistoryProfileListModel;
import com.ulearning.ulms.common.model.HistoryProfileModel;

import java.io.Serializable;


public class HistoryProfileWebImpl implements Serializable
{
        private HistoryProfileDAO historyProfileDAO;

        public HistoryProfileWebImpl()
        {
                try
                {
                        historyProfileDAO = HistoryProfileDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * get  the user-relation profiles
         * type
         * ==0:课程记录
         * ==1:证书记录
         * ==2:项目记录
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public HistoryProfileListModel get(int userID, int type, int pageNo,
                                           int pageSize) throws HistoryProfileSysException
        {
                return historyProfileDAO.get(userID, type, pageNo, pageSize);
        }

        /**
         * get  the user-relationed profile
         * type
         * ==0:课程记录
         * ==1:证书记录
         * ==2:项目记录
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public HistoryProfileModel get(int userID, int relationID, int type)
                throws HistoryProfileSysException
        {
                return historyProfileDAO.get(userID, relationID, type);
        }
}
