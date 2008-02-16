/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Answer.dao;

import com.ulearning.ulms.course.test.Answer.dao.AnswerDAO;
import com.ulearning.ulms.course.test.Answer.dao.AnswerDAOFactory;
import com.ulearning.ulms.course.test.Answer.exceptions.AnswerDAOSysException;
import com.ulearning.ulms.course.test.Answer.form.AnswerForm;

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
 * Date: 20051121
 * Time: 135243
 */
public class AnswerDAOTest extends TestCase
{
        private AnswerDAO dao = null;

        public AnswerDAOTest(String name)
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
                        AnswerForm testForm = new AnswerForm();
                        testForm.setUserID(1);
                        testForm.setPaperID(1);
                        testForm.setQuestionID(1);
                        testForm.setType(1);
                        testForm.setAnswer("1");
                        testForm.setGrade(1);
                        testForm.setExamtimes(1);
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");
                        testForm.setRemark4("1");
                        testForm.setRemark5("1");

                        AnswerDAO dao = getDAO();
                        dao.insertAnswer(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[AnswerDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[AnswerDAOTest] Exception End ******************");
                }
        }

        public void testDeleteAnswer()
        {
                try
                {
                        AnswerDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteAnswer(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[AnswerDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[AnswerDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        AnswerForm testForm = new AnswerForm();
                        testForm.setAnswerID(1);
                        testForm.setUserID(1);
                        testForm.setPaperID(1);
                        testForm.setQuestionID(1);
                        testForm.setType(1);
                        testForm.setAnswer("1");
                        testForm.setGrade(1);
                        testForm.setExamtimes(1);
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");
                        testForm.setRemark4("1");
                        testForm.setRemark5("1");

                        AnswerDAO dao = getDAO();
                        dao.updateAnswer(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[AnswerDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[AnswerDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        AnswerDAO dao = getDAO();
                        AnswerForm testForm = dao.getAnswer(1);

                        //verify the result
                        //assertNotNull(delete.getAnswerID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[AnswerDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[AnswerDAOTest] Exception End ******************");
                }
        }

        private AnswerDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = AnswerDAOFactory.getDAO();
                        }
                        catch (AnswerDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(AnswerDAOTest.class);
        }
}
