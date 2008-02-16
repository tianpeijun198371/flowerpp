/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.dao;

import com.ulearning.ulms.admin.gradeterm.exceptions.GradeTermDAOSysException;
import com.ulearning.ulms.admin.gradeterm.form.GradeTermForm;
import com.ulearning.ulms.admin.gradeterm.model.GradeTermModel;
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
public class GradeTermDAOImpl implements GradeTermDAO
{
        public Serializable insertGradeTerm(GradeTermForm details)
                throws GradeTermDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getGradeTermModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermDAOSysException("" + e);
                }

                return s;
        }

        public void updateGradeTerm(GradeTermForm details)
                throws GradeTermDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getGradeTermModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermDAOSysException("" + e);
                }
        }

        /**
         * Remove the GradeTerm from database by the GRADETID
         *
         * @throws GradeTermDAOSysException
         */
        public void deleteGradeTerm(int pkID) throws GradeTermDAOSysException
        {
                String hql = " from GradeTermModel where GRADETID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermDAOSysException("" + e);
                }
        }

        /**
         * Get the GradeTerm info via the unique GRADETID
         *
         * @return the prepared GradeTermForm, default is null
         * @throws GradeTermDAOSysException
         */
        public GradeTermForm getGradeTerm(int pkID) throws GradeTermDAOSysException
        {
                GradeTermForm bf = new GradeTermForm();
                GradeTermForm res = null;
                List tmList = null;
                String hql = " from GradeTermModel where GRADETID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GradeTermModel bm = (GradeTermModel) tmList.get(0);
                        res = bf.getGradeTermForm(bm);
                }

                return res;
        }

        public GradeTermForm getGradeTermSpe(String grade, String term, String speci)
                throws GradeTermDAOSysException
        {
                GradeTermForm bf = new GradeTermForm();
                GradeTermForm res = null;
                List tmList = null;
                String hql = " from GradeTermModel where" + "  grade = '" + grade +
                        "' and term ='" + term + "'" + "  and speciality ='" + speci + "'";
                System.out.println("hql = " + hql);

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GradeTermModel bm = (GradeTermModel) tmList.get(0);
                        res = bf.getGradeTermForm(bm);
                }

                return res;
        }

        /**
         * Get the GradeTerm list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws GradeTermDAOSysException
         */
        public List getGradeTermList() throws GradeTermDAOSysException
        {
                GradeTermForm bf = new GradeTermForm();
                GradeTermModel bm = null;
                ArrayList GradeTermList = new ArrayList();
                List tmList = null;
                String hql = " from GradeTermModel where  1 = 1 ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeTermDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (GradeTermModel) tmList.get(i);
                        GradeTermList.add(bf.getGradeTermForm(bm));
                }

                return GradeTermList;
        }
}
