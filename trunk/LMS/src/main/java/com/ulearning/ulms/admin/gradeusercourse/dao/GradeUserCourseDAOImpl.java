/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeusercourse.dao;

import com.ulearning.ulms.admin.gradeusercourse.exceptions.GradeUserCourseDAOSysException;
import com.ulearning.ulms.admin.gradeusercourse.form.GradeUserCourseForm;
import com.ulearning.ulms.admin.gradeusercourse.model.GradeUserCourseModel;
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
public class GradeUserCourseDAOImpl implements GradeUserCourseDAO
{
        public int insertGradeUserCourse(GradeUserCourseForm details)
                throws GradeUserCourseDAOSysException
        {
                int userID = 0;

                try
                {
                        String s = HibernateDAO.add(details.getGradeUserCourseModel())
                                .toString();
                        userID = Integer.parseInt(s);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserCourseDAOSysException("" + e);
                }

                return userID;
        }

        public GradeUserCourseForm checkuser(int gradetID, int gradeuserID,
                                             int gradecID) throws GradeUserCourseDAOSysException
        {
                GradeUserCourseForm bf = new GradeUserCourseForm();
                GradeUserCourseForm res = null;
                List tmList = null;
                String hql = " from GradeUserCourseModel where gradetID = " + gradetID +
                        "" + " and  gradecID =" + gradecID + " and gradeuserID=" +
                        gradeuserID + "";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserCourseDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GradeUserCourseModel bm = (GradeUserCourseModel) tmList.get(0);
                        res = bf.getGradeUserCourseForm(bm);
                }

                return res;
        }

        public void updateGradeUserCourse(GradeUserCourseForm details)
                throws GradeUserCourseDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getGradeUserCourseModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserCourseDAOSysException("" + e);
                }
        }

        /**
         * Remove the GradeUserCourse from database by the GRADEUCID
         *
         * @throws GradeUserCourseDAOSysException
         */
        public void deleteGradeUserCourse(int pkID)
                throws GradeUserCourseDAOSysException
        {
                String hql = " from GradeUserCourseModel where GRADEUCID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserCourseDAOSysException("" + e);
                }
        }

        /**
         * Get the GradeUserCourse info via the unique GRADEUCID
         *
         * @return the prepared GradeUserCourseForm, default is null
         * @throws GradeUserCourseDAOSysException
         */
        public GradeUserCourseForm getGradeUserCourse(int pkID)
                throws GradeUserCourseDAOSysException
        {
                GradeUserCourseForm bf = new GradeUserCourseForm();
                GradeUserCourseForm res = null;
                List tmList = null;
                String hql = " from GradeUserCourseModel where GRADEUCID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserCourseDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GradeUserCourseModel bm = (GradeUserCourseModel) tmList.get(0);
                        res = bf.getGradeUserCourseForm(bm);
                }

                return res;
        }

        /**
         * Get the GradeUserCourse list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws GradeUserCourseDAOSysException
         */
        public List getGradeUserCourseList() throws GradeUserCourseDAOSysException
        {
                GradeUserCourseForm bf = new GradeUserCourseForm();
                GradeUserCourseModel bm = null;
                ArrayList GradeUserCourseList = new ArrayList();
                List tmList = null;
                String hql = " from GradeUserCourseModel where  1 = 1 ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserCourseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (GradeUserCourseModel) tmList.get(i);
                        GradeUserCourseList.add(bf.getGradeUserCourseForm(bm));
                }

                return GradeUserCourseList;
        }

        public List getGradeUserCourseList(int guID)
                throws GradeUserCourseDAOSysException
        {
                GradeUserCourseForm bf = new GradeUserCourseForm();
                GradeUserCourseModel bm = null;
                ArrayList GradeUserCourseList = new ArrayList();
                List tmList = null;
                String hql = " from GradeUserCourseModel where  GradeUserID =  " +
                        guID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserCourseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (GradeUserCourseModel) tmList.get(i);
                        GradeUserCourseList.add(bf.getGradeUserCourseForm(bm));
                }

                return GradeUserCourseList;
        }

        public List getGradeUserCourseList(int gradeTID, int courseID)
                throws GradeUserCourseDAOSysException
        {
                GradeUserCourseForm bf = new GradeUserCourseForm();
                GradeUserCourseModel bm = null;
                ArrayList GradeUserCourseList = new ArrayList();
                List tmList = null;
                String hql = " from GradeUserCourseModel where  GradeTID =  " +
                        gradeTID + " and GradeCID =" + courseID;
                LogUtil.debug("course", "[GradeUserCourseDAOImpl] ===============" +
                        hql);

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeUserCourseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (GradeUserCourseModel) tmList.get(i);
                        GradeUserCourseList.add(bf.getGradeUserCourseForm(bm));
                }

                return GradeUserCourseList;
        }
}
