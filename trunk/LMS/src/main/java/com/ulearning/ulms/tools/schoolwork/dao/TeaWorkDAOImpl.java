/**
 * TeaWorkDAOImpl.java
 * created by yud
 * Date 2005.04.18
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.dao;


import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.schoolwork.exceptions.TeaWorkSysException;
import com.ulearning.ulms.tools.schoolwork.model.TeaWorkModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.DebugUtil;

import java.util.List;

public class TeaWorkDAOImpl implements TeaWorkDAO
{
        /**
         * @param postilSWId
         * @return
         * @throws TeaWorkSysException
         */
        public TeaWorkModel getTeaModel(int postilSWId) throws TeaWorkSysException
        {
                TeaWorkModel TeaWorkModel = null;
                List clList = null;
                String sql_str = "from TeaWorkModel where postilSWId = " + postilSWId;

                try
                {
                        clList = HibernateDAO.find(sql_str);

                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TeaWorkSysException(e);
                }
                for (int i = 0; i < clList.size(); i++)
                {
                        TeaWorkModel = (TeaWorkModel) clList.get(i);
                }
                return TeaWorkModel;
        }

        /**
         * @param userID
         * @return
         * @throws TeaWorkSysException
         */
        public List getTeaList(int userID)
                throws TeaWorkSysException
        {
                List tmList = null;
                String sql_str = "from TeaWorkModel where userId=" + userID;

                try
                {
                        tmList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TeaWorkSysException(e);
                }
                return tmList;
        }

        /**
         * 根据用户名和学生作业取得批改作业列表
         *
         * @param userswID
         * @return
         * @throws TeaWorkSysException
         */
        public List getTeaListall(int userswID)
                throws TeaWorkSysException
        {
                List tmList = null;
                String sql_str = "from TeaWorkModel where userswId= " + userswID;

                try
                {
                        tmList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TeaWorkSysException(e);
                }
                return tmList;
        }

        public List getCheck(int userswID) throws TeaWorkSysException
        {
                List tmList = null;
                String sql_str = "from TeaWorkModel where userswId= " + userswID;

                try
                {
                        tmList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TeaWorkSysException(e);
                }

                return tmList;
        }

        /**
         * created by yud
         *
         * @param addTeaWorkModel
         * @throws TeaWorkSysException
         */
        public void insertTeaWork(TeaWorkModel addTeaWorkModel) throws TeaWorkSysException
        {
                try
                {
                        HibernateDAO.add(addTeaWorkModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TeaWorkSysException(e);
                }
        }

        /**
         * created by yud
         *
         * @param updTeaWorkModel
         * @throws TeaWorkSysException
         */
        public void updateTeaWork(TeaWorkModel updTeaWorkModel) throws TeaWorkSysException
        {
                try
                {
                        HibernateDAO.update(updTeaWorkModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TeaWorkSysException(e);
                }
        }

        /**
         * created by yud
         *
         * @param l
         * @throws TeaWorkSysException
         */
        public void deleteTeaWork(List l) throws TeaWorkSysException
        {
                String sql_str = "";
                if (l.size() > 0)
                {
                        sql_str = " from TeaWorkModel where postilSWId = " + (Integer) l.get(0);
                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += " or postilSWId = " + (Integer) l.get(i) + "";
                        }
                }

                try
                {
                        DebugUtil.print("[Debug ]  = " + sql_str);
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TeaWorkSysException(e);
                }
        }

}
