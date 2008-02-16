/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.Exambatch.exceptions.ExambatchDAOSysException;
import com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm;
import com.ulearning.ulms.course.test.Exambatch.model.ExambatchModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * 实现的考场操作类
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public class ExambatchDAOImpl implements ExambatchDAO
{
        public Serializable insertExambatch(ExambatchForm details)
                throws ExambatchDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getExambatchModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ExambatchDAOSysException("" + e);
                }

                return s;
        }

        public void updateExambatch(ExambatchForm details)
                throws ExambatchDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getExambatchModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ExambatchDAOSysException("" + e);
                }
        }

        /**
         * Remove the Exambatch from database by the EXAMBATCHID
         *
         * @throws ExambatchDAOSysException
         */
        public void deleteExambatch(int pkID) throws ExambatchDAOSysException
        {
                String hql = " from ExambatchModel where EXAMBATCHID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ExambatchDAOSysException("" + e);
                }
        }

        /**
         * Get the Exambatch info via the unique EXAMBATCHID
         *
         * @return the prepared ExambatchForm, default is null
         * @throws ExambatchDAOSysException
         */
        public ExambatchForm getExambatch(int pkID) throws ExambatchDAOSysException
        {
                ExambatchForm bf = new ExambatchForm();
                ExambatchForm res = null;
                List tmList = null;
                String hql = " from ExambatchModel where EXAMBATCHID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ExambatchDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        ExambatchModel bm = (ExambatchModel) tmList.get(0);
                        res = bf.getExambatchForm(bm);
                }

                return res;
        }

        /**
         * Get the Exambatch info via the unique EXAMBATCHID
         *
         * @return the prepared ExambatchForm, default is null
         * @throws ExambatchDAOSysException
         */
        public List getPaperList(int paperID) throws ExambatchDAOSysException
        {
                ExambatchForm bf = new ExambatchForm();
                ExambatchModel bm = null;
                ArrayList ExambatchList = new ArrayList();
                List tmList = null;
                String hql = " from ExambatchModel where paperID= " + paperID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ExambatchDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (ExambatchModel) tmList.get(i);
                        ExambatchList.add(bf.getExambatchForm(bm));
                }

                return ExambatchList;
        }

        /**
         * Get the Exambatch list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws ExambatchDAOSysException
         */
        public List getExambatchList() throws ExambatchDAOSysException
        {
                ExambatchForm bf = new ExambatchForm();
                ExambatchModel bm = null;
                ArrayList ExambatchList = new ArrayList();
                List tmList = null;
                String hql = " from ExambatchModel where  1 = 1 ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ExambatchDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (ExambatchModel) tmList.get(i);
                        ExambatchList.add(bf.getExambatchForm(bm));
                }

                return ExambatchList;
        }

        /**
         * Get the Exambatch list by the catalogID
         *
         * @param type relationID
         * @return The prepared arraylist object,default size is 0
         * @throws ExambatchDAOSysException ture 是开始时间 false 是结束时间
         */
        public Date getdatePaperTime(int PaperID, boolean type)
                throws ExambatchDAOSysException
        {
                ExambatchForm bf = new ExambatchForm();
                ExambatchModel bm = null;
                ArrayList ExambatchList = new ArrayList();
                List tmList = null;
                String ordstr = "";

                if (!type)
                {
                        ordstr = " ORDER BY examendtime DESC";
                }
                else
                {
                        ordstr = " ORDER BY exambegintime ASC";
                }

                String hql = " from ExambatchModel where paperID= " + PaperID + ordstr;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ExambatchDAOSysException("" + e);
                }

                bm = (ExambatchModel) tmList.get(0);

                Date teturnstr = null;

                if (!type)
                {
                        teturnstr = bm.getExamendtime();
                }
                else
                {
                        teturnstr = bm.getExambegintime();
                }

                return teturnstr;
        }
}
