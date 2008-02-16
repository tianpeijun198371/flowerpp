/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.dao;

import com.ulearning.ulms.cert.Certificate.exceptions.CertificateDAOSysException;
import com.ulearning.ulms.cert.Certificate.form.CertificateForm;
import com.ulearning.ulms.cert.Certificate.model.CertificateModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.HibernateDAO;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060109
 * Time: 101040
 */
public class CertificateDAOImpl implements CertificateDAO
{
        public Serializable insertCertificate(CertificateForm details)
                throws CertificateDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getCertificateModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertificateDAOSysException("" + e);
                }

                return s;
        }

        public void updateCertificate(CertificateForm details)
                throws CertificateDAOSysException
        {
                try
                {
                        //CertificateForm bb = this.getCertificate(details.getCertificateModel().getCtifiID());
                        /*if (bb == null)
                        {
                                HibernateDAO.add(details.getCertificateModel());
                        }
                        else
                        {*/
                        HibernateDAO.update(details.getCertificateModel());

                        //}
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertificateDAOSysException("" + e);
                }
        }

        /**
         * Remove the Certificate from database by the C_TIFI_ID
         *
         * @param pkID
         * @throws CertificateDAOSysException
         */
        public void deleteCertificate(int pkID) throws CertificateDAOSysException
        {
                String hql = " from CertificateModel where C_TIFI_ID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertificateDAOSysException("" + e);
                }
        }

        /**
         * Get the Certificate info via the unique C_TIFI_ID
         *
         * @return the prepared CertificateForm, default is null
         * @throws CertificateDAOSysException
         */
        public CertificateForm getCertificate(int pkID)
                throws CertificateDAOSysException
        {
                CertificateForm bf = new CertificateForm();
                CertificateForm res = null;
                List tmList = null;
                String hql = " from CertificateModel where C_TIFI_ID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertificateDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        CertificateModel bm = (CertificateModel) tmList.get(0);
                        res = bf.getCertificateForm(bm);
                }

                return res;
        }

        public CertificateForm getCercourseID(int courseID)
                throws CertificateDAOSysException
        {
                CertificateForm bf = new CertificateForm();
                CertificateForm res = null;
                List tmList = null;
                String hql = " from CertificateModel where C_COURSE_ID = " + courseID;

                //String hql_bak = " from CertificateModel where C_COURSE_ID = 0";
                try
                {
                        tmList = HibernateDAO.find(hql);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                        }
                        else
                        {
                                CertificateForm res2 = new CertificateForm();
                                res2.setCourseID(courseID);
                                res2.setCZuyeci("3");
                                res2.setCZuoyeMoren("7");
                                res2.setCZuoyeAllGrade("100");
                                res2.setCZuoyeGrade("0");
                                res2.setCKaoshiMoren("2");
                                insertCertificate(res2);
                                tmList = HibernateDAO.find(hql);
                        }

                        CertificateModel bm = (CertificateModel) tmList.get(0);
                        res = bf.getCertificateForm(bm);
                        res.setCourseID(courseID);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertificateDAOSysException("" + e);
                }

                return res;
        }

        /**
         * Get the Certificate list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws CertificateDAOSysException
         */
        public List getCertificateList() throws CertificateDAOSysException
        {
                CertificateForm bf = new CertificateForm();
                CertificateModel bm = null;
                ArrayList CertificateList = new ArrayList();
                List tmList = null;
                String hql = " from CertificateModel where  1 = 1 ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertificateDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (CertificateModel) tmList.get(i);
                        CertificateList.add(bf.getCertificateForm(bm));
                }

                return CertificateList;
        }

        public static void main(String[] args) throws Exception
        {
                CertificateDAOImpl ab = new CertificateDAOImpl();
                System.out.println(ab.getCercourseID(12).getCourseID());
        }
}
