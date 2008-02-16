/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.portal.fuctionconfig.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.portal.fuctionconfig.exceptions.FunctionConfigSysException;
import com.ulearning.ulms.portal.fuctionconfig.model.FunctionConfigModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.Date;
import java.util.List;


public class FunctionConfigDaolmpl implements FunctionConfigDAO
{
        public FunctionConfigModel get(int functionID)
                throws FunctionConfigSysException
        {
                List tmList = null;
                FunctionConfigModel am = null;
                String hql = " from FunctionConfigModel where fuctionID = " +
                        functionID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new FunctionConfigSysException(e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (FunctionConfigModel) tmList.get(i);
                }

                return am;
        }

        public List getAll(int type) throws FunctionConfigSysException
        {
                List tmList = null;

                String hql = "from FunctionConfigModel where type ='" + type +
                        "' order by fuctionID";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new FunctionConfigSysException(e);
                }

                return tmList;
        }

        public List getCourseList(int relationID, int type)
                throws FunctionConfigSysException
        {
                List tmList = null;
                String hql = "from FunctionConfigModel where type = '" + type +
                        "' and relationID = " + relationID + " order by fuctionID";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new FunctionConfigSysException(e);
                }

                return tmList;
        }

        public void update(List l) throws FunctionConfigSysException
        {
                for (int i = 0; i < l.size(); i++)
                {
                        try
                        {
                                FunctionConfigModel ndm = (FunctionConfigModel) l.get(i);
                                HibernateDAO.update(ndm);
                        }
                        catch (ULMSSysException e)
                        {
                                e.printStackTrace();
                                throw new FunctionConfigSysException(e);
                        }
                }
        }

        public void insertCourse(FunctionConfigModel addFunctionConfigModel)
                throws FunctionConfigSysException
        {
                try
                {
                        HibernateDAO.add(addFunctionConfigModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new FunctionConfigSysException(e);
                }
        }
}
