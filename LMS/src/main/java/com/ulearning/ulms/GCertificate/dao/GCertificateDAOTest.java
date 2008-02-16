/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.GCertificate.dao;

import com.ulearning.ulms.GCertificate.dao.GCertificateDAO;
import com.ulearning.ulms.GCertificate.dao.GCertificateDAOFactory;
import com.ulearning.ulms.GCertificate.exceptions.GCertificateDAOSysException;
import com.ulearning.ulms.GCertificate.form.GCertificateForm;

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
 * Date: 20060111
 * Time: 110302
 */
public class GCertificateDAOTest extends TestCase
{
        private GCertificateDAO dao = null;

        public GCertificateDAOTest(String name)
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
                        GCertificateForm testForm = new GCertificateForm();
                        testForm.setGUserID(1);
                        testForm.setGUserName("1");
                        testForm.setGCourseID(1);
                        testForm.setGCourseName("1");
                        testForm.setGCGrade("1");
                        testForm.setGCTime("1");
                        testForm.setGBak1("1");
                        testForm.setGBak2("1");
                        testForm.setGBak3("1");

                        GCertificateDAO dao = getDAO();
                        dao.insertGCertificate(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GCertificateDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GCertificateDAOTest] Exception End ******************");
                }
        }

        public void testDeleteGCertificate()
        {
                try
                {
                        GCertificateDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteGCertificate(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GCertificateDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GCertificateDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        GCertificateForm testForm = new GCertificateForm();
                        testForm.setGID(1);
                        testForm.setGUserID(1);
                        testForm.setGUserName("1");
                        testForm.setGCourseID(1);
                        testForm.setGCourseName("1");
                        testForm.setGCGrade("1");
                        testForm.setGCTime("1");
                        testForm.setGBak1("1");
                        testForm.setGBak2("1");
                        testForm.setGBak3("1");

                        GCertificateDAO dao = getDAO();
                        dao.updateGCertificate(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GCertificateDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GCertificateDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        GCertificateDAO dao = getDAO();
                        GCertificateForm testForm = dao.getGCertificate(1);

                        //verify the result
                        //assertNotNull(delete.getGCertificateID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GCertificateDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GCertificateDAOTest] Exception End ******************");
                }
        }

        private GCertificateDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = GCertificateDAOFactory.getDAO();
                        }
                        catch (GCertificateDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(GCertificateDAOTest.class);
        }
}
