/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeuser.dao;

import com.ulearning.ulms.admin.gradeuser.exceptions.GradeUserDAOSysException;
import com.ulearning.ulms.admin.gradeuser.form.GradeUserForm;
import com.ulearning.ulms.admin.gradeuser.model.GradeUserModel;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
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
 * Date: 20060321
 * Time: 182730
 */
public class GradeUserDAOImpl implements GradeUserDAO
{
        public int insertGradeUser(GradeUserForm details)
                throws GradeUserDAOSysException
        {
                int users = 0;

                try
                {
                        String s = HibernateDAO.add(details.getGradeUserModel()).toString();
                        users = Integer.parseInt(s);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserDAOSysException("" + e);
                }

                return users;
        }

        public void updateGradeUser(GradeUserForm details)
                throws GradeUserDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getGradeUserModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserDAOSysException("" + e);
                }
        }

        /**
         * Remove the GradeUser from database by the GRADEUSERID
         *
         * @throws GradeUserDAOSysException
         */
        public void deleteGradeUser(int pkID) throws GradeUserDAOSysException
        {
                String hql = " from GradeUserModel where GRADEUSERID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserDAOSysException("" + e);
                }
        }

        /**
         * Get the GradeUser info via the unique GRADEUSERID
         *
         * @return the prepared GradeUserForm, default is null
         * @throws GradeUserDAOSysException
         */
        public GradeUserForm getGradeUser(int pkID) throws GradeUserDAOSysException
        {
                GradeUserForm bf = new GradeUserForm();
                GradeUserForm res = null;
                List tmList = null;
                String hql = " from GradeUserModel where GRADEUSERID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GradeUserModel bm = (GradeUserModel) tmList.get(0);
                        res = bf.getGradeUserForm(bm);
                }

                return res;
        }

        public GradeUserForm getGradeUser(String name, String pwd)
                throws GradeUserDAOSysException
        {
                GradeUserForm bf = new GradeUserForm();
                GradeUserForm res = null;
                List tmList = null;
                String hql = " from GradeUserModel where " + " gradeUserName = '" +
                        name + "' and gradeUserpwd ='" + pwd + "'";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GradeUserModel bm = (GradeUserModel) tmList.get(0);
                        res = bf.getGradeUserForm(bm);
                }

                return res;
        }

        /**
         * Get the GradeUser list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws GradeUserDAOSysException
         */
        public List getGradeUserList() throws GradeUserDAOSysException
        {
                GradeUserForm bf = new GradeUserForm();
                GradeUserModel bm = null;
                ArrayList GradeUserList = new ArrayList();
                List tmList = null;
                String hql = " from GradeUserModel where  1 = 1 ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (GradeUserModel) tmList.get(i);
                        GradeUserList.add(bf.getGradeUserForm(bm));
                }

                return GradeUserList;
        }
}
