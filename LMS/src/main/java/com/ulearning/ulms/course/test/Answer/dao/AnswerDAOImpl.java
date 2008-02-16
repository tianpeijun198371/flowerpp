/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Answer.dao;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.Answer.exceptions.AnswerDAOSysException;
import com.ulearning.ulms.course.test.Answer.form.AnswerForm;
import com.ulearning.ulms.course.test.Answer.model.AnswerModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051121
 * Time: 135243
 */
public class AnswerDAOImpl implements AnswerDAO
{
        public Serializable insertAnswer(AnswerForm details)
                throws AnswerDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getAnswerModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnswerDAOSysException("" + e);
                }

                return s;
        }

        public void updateAnswer(AnswerForm details) throws AnswerDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getAnswerModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnswerDAOSysException("" + e);
                }
        }

        /**
         * Remove the Answer from database by the ANSWERID
         *
         * @throws AnswerDAOSysException
         */
        public void deleteAnswer(int pkID) throws AnswerDAOSysException
        {
                String hql = " from AnswerModel where ANSWERID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnswerDAOSysException("" + e);
                }
        }

        /**
         * Get the Answer info via the unique ANSWERID
         *
         * @return the prepared AnswerForm, default is null
         * @throws AnswerDAOSysException
         */
        public AnswerForm getAnswer(int pkID) throws AnswerDAOSysException
        {
                AnswerForm bf = new AnswerForm();
                AnswerForm res = null;
                List tmList = null;
                String hql = " from AnswerModel where ANSWERID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnswerDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        AnswerModel bm = (AnswerModel) tmList.get(0);
                        res = bf.getAnswerForm(bm);
                }

                return res;
        }

        /**
         * Get the Answer list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws AnswerDAOSysException
         */
        public List getAnswerList() throws AnswerDAOSysException
        {
                AnswerForm bf = new AnswerForm();
                AnswerModel bm = null;
                ArrayList AnswerList = new ArrayList();
                List tmList = null;
                String hql = " from AnswerModel where  1 = 1 ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnswerDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (AnswerModel) tmList.get(i);
                        AnswerList.add(bf.getAnswerForm(bm));
                }

                return AnswerList;
        }
}
