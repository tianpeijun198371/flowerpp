/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.GCertificate.dao;

import com.ulearning.ulms.GCertificate.exceptions.GCertificateDAOSysException;
import com.ulearning.ulms.GCertificate.form.GCertificateForm;
import com.ulearning.ulms.GCertificate.model.GCertificateModel;
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
 * Date: 20060111
 * Time: 110302
 */
public class GCertificateDAOImpl implements GCertificateDAO
{
        public Serializable insertGCertificate(GCertificateForm details)
                throws GCertificateDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getGCertificateModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GCertificateDAOSysException("" + e);
                }

                return s;
        }

        public void updateGCertificate(GCertificateForm details)
                throws GCertificateDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getGCertificateModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GCertificateDAOSysException("" + e);
                }
        }

        /**
         * Remove the GCertificate from database by the G_ID
         *
         * @throws GCertificateDAOSysException
         */
        public void deleteGCertificate(int pkID) throws GCertificateDAOSysException
        {
                String hql = " from GCertificateModel where G_ID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GCertificateDAOSysException("" + e);
                }
        }

        /**
         * Get the GCertificate info via the unique G_ID
         *
         * @return the prepared GCertificateForm, default is null
         * @throws GCertificateDAOSysException
         */
        public GCertificateForm getGCertificate(int pkID)
                throws GCertificateDAOSysException
        {
                GCertificateForm bf = new GCertificateForm();
                GCertificateForm res = null;
                List tmList = null;
                String hql = " from GCertificateModel where G_ID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GCertificateDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GCertificateModel bm = (GCertificateModel) tmList.get(0);
                        res = bf.getGCertificateForm(bm);
                }

                return res;
        }

        public GCertificateForm getGCertificate(int gUserID, int gCourseID)
                throws GCertificateDAOSysException
        {
                GCertificateForm bf = new GCertificateForm();
                GCertificateForm res = null;
                List tmList = null;
                String hql = " from GCertificateModel where G_UserID = " + gUserID +
                        " and G_CourseID=" + gCourseID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GCertificateDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        GCertificateModel bm = (GCertificateModel) tmList.get(0);
                        res = bf.getGCertificateForm(bm);
                }

                return res;
        }

        public List getGCUserID(int gUserID) throws GCertificateDAOSysException
        {
                GCertificateForm bf = new GCertificateForm();
                GCertificateForm res = null;
                List tmList = null;
                String hql = " from GCertificateModel where G_UserID = " + gUserID +
                        "  order by G_CourseID";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GCertificateDAOSysException("" + e);
                }

                /*if ((tmList != null) && (tmList.size() > 0))
              {
                      GCertificateModel bm = (GCertificateModel) tmList.get(0);
                      res = bf.getGCertificateForm(bm);
              }  */
                return tmList;
        }

        public List getGCCourseID(int gCourseID) throws GCertificateDAOSysException
        {
                GCertificateForm bf = new GCertificateForm();
                GCertificateForm res = null;
                List tmList = null;
                String hql = " from GCertificateModel where G_CourseID=" + gCourseID +
                        " order by G_UserID";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GCertificateDAOSysException("" + e);
                }

                /*if ((tmList != null) && (tmList.size() > 0))
                {
                        GCertificateModel bm = (GCertificateModel) tmList.get(0);
                        res = bf.getGCertificateForm(bm);
                }*/
                return tmList;
        }

        /**
         * Get the GCertificate list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws GCertificateDAOSysException
         */
        public List getGCertificateList() throws GCertificateDAOSysException
        {
                GCertificateForm bf = new GCertificateForm();
                GCertificateModel bm = null;
                ArrayList GCertificateList = new ArrayList();
                List tmList = null;
                String hql = " from GCertificateModel ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GCertificateDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (GCertificateModel) tmList.get(i);
                        GCertificateList.add(bf.getGCertificateForm(bm));
                }

                return GCertificateList;
        }

        public List getGCertificateList(int Userid)
                throws GCertificateDAOSysException
        {
                GCertificateForm bf = new GCertificateForm();
                GCertificateModel bm = null;
                ArrayList GCertificateList = new ArrayList();
                List tmList = null;
                String hql = " from GCertificateModel where G_UserID = " + Userid;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GCertificateDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (GCertificateModel) tmList.get(i);
                        GCertificateList.add(bf.getGCertificateForm(bm));
                }

                return GCertificateList;
        }
}
