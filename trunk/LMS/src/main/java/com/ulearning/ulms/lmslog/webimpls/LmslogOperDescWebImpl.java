/**
 * LmslogOperDescWebImpl.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.webimpls;

import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.dao.LmslogOperDescDAO;
import com.ulearning.ulms.lmslog.dao.LmslogOperDescDAOFactory;
import com.ulearning.ulms.lmslog.exceptions.LmslogOperDescSysException;
import com.ulearning.ulms.lmslog.exceptions.LmslogSysException;

import java.util.List;


public class LmslogOperDescWebImpl
{
        public List getAll() throws LmslogOperDescSysException
        {
                List lmslogOperDescFormList = null;

                try
                {
                        LmslogOperDescDAO dao = LmslogOperDescDAOFactory.getDAO();
                        lmslogOperDescFormList = dao.getAll();
                }
                catch (LmslogOperDescSysException cse)
                {
                        cse.printStackTrace();
                }

                return lmslogOperDescFormList;
        }

        public List getByLogType(int logTypeID) throws LmslogOperDescSysException
        {
                List lmslogOperDescFormList = null;

                try
                {
                        LmslogOperDescDAO dao = LmslogOperDescDAOFactory.getDAO();
                        lmslogOperDescFormList = dao.getByLogType(logTypeID);
                }
                catch (LmslogOperDescSysException cse)
                {
                        cse.printStackTrace();
                }

                return lmslogOperDescFormList;
        }
}
