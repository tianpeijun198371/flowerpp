/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeuser.dao;

import com.ulearning.ulms.admin.gradeuser.dao.GradeUserDAO;
import com.ulearning.ulms.admin.gradeuser.dao.GradeUserDAOFactory;
import com.ulearning.ulms.admin.gradeuser.exceptions.GradeUserDAOSysException;
import com.ulearning.ulms.admin.gradeuser.form.GradeUserForm;

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
 * Date: 20060321
 * Time: 182730
 */
public class GradeUserDAOTest extends TestCase
{
        private GradeUserDAO dao = null;

        public GradeUserDAOTest(String name)
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
                        GradeUserForm testForm = new GradeUserForm();
                        testForm.setGradeUserName("1");
                        testForm.setGradeUserpwd("1");
                        testForm.setEntergrade("1");
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");

                        GradeUserDAO dao = getDAO();
                        dao.insertGradeUser(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeUserDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeUserDAOTest] Exception End ******************");
                }
        }

        public void testDeleteGradeUser()
        {
                try
                {
                        GradeUserDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteGradeUser(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeUserDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeUserDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        GradeUserForm testForm = new GradeUserForm();
                        testForm.setGradeUserID(1);
                        testForm.setGradeUserName("1");
                        testForm.setGradeUserpwd("1");
                        testForm.setEntergrade("1");
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");

                        GradeUserDAO dao = getDAO();
                        dao.updateGradeUser(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeUserDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeUserDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        GradeUserDAO dao = getDAO();
                        GradeUserForm testForm = dao.getGradeUser(1);

                        //verify the result
                        //assertNotNull(delete.getGradeUserID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeUserDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeUserDAOTest] Exception End ******************");
                }
        }

        private GradeUserDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = GradeUserDAOFactory.getDAO();
                        }
                        catch (GradeUserDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(GradeUserDAOTest.class);
        }
}
