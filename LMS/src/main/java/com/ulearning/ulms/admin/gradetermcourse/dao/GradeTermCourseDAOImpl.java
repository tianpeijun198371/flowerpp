/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradetermcourse.dao;

import com.ulearning.ulms.admin.gradetermcourse.exceptions.GradeTermCourseDAOSysException;
import com.ulearning.ulms.admin.gradetermcourse.form.GradeTermCourseForm;
import com.ulearning.ulms.admin.gradetermcourse.model.GradeTermCourseModel;
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
public class GradeTermCourseDAOImpl implements GradeTermCourseDAO
{
        public Serializable insertGradeTermCourse(GradeTermCourseForm details)
                throws GradeTermCourseDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getGradeTermCourseModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermCourseDAOSysException("" + e);
                }

                return s;
        }

        public void updateGradeTermCourse(GradeTermCourseForm details)
                throws GradeTermCourseDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getGradeTermCourseModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermCourseDAOSysException("" + e);
                }
        }

        /**
         * Remove the GradeTermCourse from database by the GRADECID
         *
         * @throws GradeTermCourseDAOSysException
         */
        public void deleteGradeTermCourse(int pkID)
                throws GradeTermCourseDAOSysException
        {
                String hql = " from GradeTermCourseModel where GRADECID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermCourseDAOSysException("" + e);
                }
        }

        /**
         * Get the GradeTermCourse info via the unique GRADECID
         *
         * @return the prepared GradeTermCourseForm, default is null
         * @throws GradeTermCourseDAOSysException
         */
        public GradeTermCourseForm getGradeTermCourse(int pkID)
                throws GradeTermCourseDAOSysException
        {
                GradeTermCourseForm bf = new GradeTermCourseForm();
                GradeTermCourseForm res = null;
                List tmList = null;
                String hql = " from GradeTermCourseModel where GRADECID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermCourseDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GradeTermCourseModel bm = (GradeTermCourseModel) tmList.get(0);
                        res = bf.getGradeTermCourseForm(bm);
                }

                return res;
        }

        /**
         * Get the GradeTermCourse list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws GradeTermCourseDAOSysException
         */
        public List getGradeTermCourseList(int gradetID)
                throws GradeTermCourseDAOSysException
        {
                GradeTermCourseForm bf = new GradeTermCourseForm();
                GradeTermCourseModel bm = null;
                ArrayList GradeTermCourseList = new ArrayList();
                List tmList = null;
                String hql = " from GradeTermCourseModel where  gradetID=" + gradetID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermCourseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (GradeTermCourseModel) tmList.get(i);
                        GradeTermCourseList.add(bf.getGradeTermCourseForm(bm));
                }

                return GradeTermCourseList;
        }

        /*        public  List   getGradeTermCourseListByName(String[] courseName) throws GradeTermCourseDAOSysException
        {
                GradeTermCourseForm bf = new GradeTermCourseForm();
                GradeTermCourseModel bm = null;
                ArrayList GradeTermCourseList = new ArrayList();
                List tmList = null;
                String hql = "";
                try
                {
                        for(int i =0;i<courseName.length;i++)
                        {
                               hql = " from GradeTermCourseModel where  courseName='"+courseName[i]+"'";
                               tmList=HibernateDAO.find(hql);
                               System.out.println("hql = " + hql);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermCourseDAOSysException(""+e);
                }
                for(int i=0;i<tmList.size();i++)
                {
                         bm=(GradeTermCourseModel)tmList.get(i);
                         GradeTermCourseList.add(bf.getGradeTermCourseForm(bm));
                }
                return GradeTermCourseList;
        }*/
        public GradeTermCourseForm getGradeTermCourseListByName(int gradeTID,
                                                                String courseName) throws GradeTermCourseDAOSysException
        {
                GradeTermCourseForm bf = new GradeTermCourseForm();
                GradeTermCourseForm res = null;
                List tmList = null;
                String hql = " from GradeTermCourseModel where gradeTID=" + gradeTID +
                        " and courseName = '" + courseName + "'";
                System.out.println("hql=" + hql);

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermCourseDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GradeTermCourseModel bm = (GradeTermCourseModel) tmList.get(0);
                        res = bf.getGradeTermCourseForm(bm);
                }

                return res;
        }
}
