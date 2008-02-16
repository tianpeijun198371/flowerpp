/**
 * LmslogWebImpl.java.
 * User: keyh  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.webimpls;

import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.exceptions.LmslogSysException;
import com.ulearning.ulms.lmslog.form.LmslogForm;

import java.util.List;


public class LmslogWebImpl
{
        public LmslogWebImpl()
        {
        }

        public LmslogForm get(int logID) throws LmslogSysException
        {
                LmslogDAO dao = LmslogDAOFactory.getDAO();

                return dao.get(logID);
        }

        public static List get(int logType, int operatoinType, int userID,
                               int orgID, String time, String endTime) throws LmslogSysException
        {
                List lmslogFormList = null;

                try
                {
                        LmslogDAO dao = LmslogDAOFactory.getDAO();
                        lmslogFormList = dao.get(logType, operatoinType, userID, orgID,
                                time, endTime);
                }
                catch (LmslogSysException cse)
                {
                        cse.printStackTrace();
                }

                return lmslogFormList;
        }

        public static List getAllUser(int logType, int operatoinType, int orgID,
                                      String time, String endTime) throws LmslogSysException
        {
                List lmslogFormList = null;

                try
                {
                        LmslogDAO dao = LmslogDAOFactory.getDAO();
                        lmslogFormList = dao.getAllUser(logType, operatoinType, orgID,
                                time, endTime);
                }
                catch (LmslogSysException cse)
                {
                        cse.printStackTrace();
                }

                return lmslogFormList;
        }

        public static void add(LmslogForm lf) throws LmslogSysException
        {
                LmslogDAO dao = LmslogDAOFactory.getDAO();
                dao.insert(lf);
        }
}
