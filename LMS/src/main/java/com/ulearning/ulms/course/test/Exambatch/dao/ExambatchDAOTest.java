/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.dao;

import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAO;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOFactory;
import com.ulearning.ulms.course.test.Exambatch.exceptions.ExambatchDAOSysException;
import com.ulearning.ulms.course.test.Exambatch.form.ExambatchForm;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * 考场操作类的测试
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public class ExambatchDAOTest extends TestCase
{
        private ExambatchDAO dao = null;

        public ExambatchDAOTest(String name)
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
                        ExambatchForm testForm = new ExambatchForm();
                        testForm.setPaperID(1);
                        testForm.setClassID(5);
                        testForm.setCreatuserID(1);
                        testForm.setExambatchname("1");
                        //testForm.setExambegintime(new Date());
                        //testForm.setExamendtime(new Date());
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");
                        testForm.setRemark4("1");
                        testForm.setRemark5("1");
                        testForm.setRemark6("1");

                        ExambatchDAO dao = getDAO();
                        dao.insertExambatch(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[ExambatchDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[ExambatchDAOTest] Exception End ******************");
                }
        }

        public void testDeleteExambatch()
        {
                try
                {
                        ExambatchDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteExambatch(4);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[ExambatchDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[ExambatchDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        ExambatchForm testForm = new ExambatchForm();
                        testForm.setExambatchID(6);
                        testForm.setPaperID(1);
                        testForm.setClassID(1);
                        testForm.setCreatuserID(1);
                        testForm.setExambatchname("1");
                        //testForm.setExambegintime(new Date());
                        //testForm.setExamendtime(new Date());
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");
                        testForm.setRemark4("1");
                        testForm.setRemark5("1");
                        testForm.setRemark6("1");

                        ExambatchDAO dao = getDAO();
                        dao.updateExambatch(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[ExambatchDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[ExambatchDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        ExambatchDAO dao = getDAO();
                        ExambatchForm testForm = dao.getExambatch(1);

                        //verify the result
                        //assertNotNull(delete.getExambatchID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[ExambatchDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[ExambatchDAOTest] Exception End ******************");
                }
        }

        private ExambatchDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = ExambatchDAOFactory.getDAO();
                        }
                        catch (ExambatchDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(ExambatchDAOTest.class);
        }
}
