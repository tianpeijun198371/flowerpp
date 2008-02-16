/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.dao;

import com.ulearning.ulms.cert.Certificate.dao.CertificateDAO;
import com.ulearning.ulms.cert.Certificate.dao.CertificateDAOFactory;
import com.ulearning.ulms.cert.Certificate.exceptions.CertificateDAOSysException;
import com.ulearning.ulms.cert.Certificate.form.CertificateForm;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060109
 * Time: 101040
 */
public class CertificateDAOTest extends TestCase
{
        private CertificateDAO dao = null;

        public CertificateDAOTest(String name)
        {
                super(name);
        }

        protected void setUp()
        {
                //com.ulearning.ulms.log.LogUtilTest.initLog();
        }

        public void testInsert()
        {
                try
                {
                        CertificateForm testForm = new CertificateForm();
                        testForm.setCourseID(1);
                        testForm.setCZuyeci("1");
                        testForm.setCZuoyeGrade("1");
                        testForm.setCZuoyeAllGrade("1");
                        testForm.setCZuoyeMoren("1");
                        testForm.setCKaoshiID("1");
                        testForm.setCKaoshiTitle("1");
                        testForm.setCKaoshiBili("1");
                        testForm.setCKaoshiMoren("1");
                        testForm.setCbak1("1");
                        testForm.setCbak2("1");
                        testForm.setCbak3("1");

                        CertificateDAO dao = getDAO();
                        dao.insertCertificate(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[CertificateDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[CertificateDAOTest] Exception End ******************");
                }
        }

        public void testDeleteCertificate()
        {
                try
                {
                        CertificateDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteCertificate(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[CertificateDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[CertificateDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        CertificateForm testForm = new CertificateForm();
                        testForm.setCtifiID(1);
                        testForm.setCourseID(1);
                        testForm.setCZuyeci("1");
                        testForm.setCZuoyeGrade("1");
                        testForm.setCZuoyeAllGrade("1");
                        testForm.setCZuoyeMoren("1");
                        testForm.setCKaoshiID("1");
                        testForm.setCKaoshiTitle("1");
                        testForm.setCKaoshiBili("1");
                        testForm.setCKaoshiMoren("1");
                        testForm.setCbak1("1");
                        testForm.setCbak2("1");
                        testForm.setCbak3("1");

                        CertificateDAO dao = getDAO();
                        dao.updateCertificate(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[CertificateDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[CertificateDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        CertificateDAO dao = getDAO();
                        CertificateForm testForm = dao.getCertificate(1);

                        //verify the result
                        //assertNotNull(delete.getCertificateID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[CertificateDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[CertificateDAOTest] Exception End ******************");
                }
        }

        private CertificateDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = CertificateDAOFactory.getDAO();
                        }
                        catch (CertificateDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(CertificateDAOTest.class);
        }
}
