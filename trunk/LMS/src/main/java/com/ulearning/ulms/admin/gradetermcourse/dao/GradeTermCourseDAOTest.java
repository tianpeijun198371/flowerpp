/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradetermcourse.dao;

import com.ulearning.ulms.admin.gradetermcourse.dao.GradeTermCourseDAO;
import com.ulearning.ulms.admin.gradetermcourse.dao.GradeTermCourseDAOFactory;
import com.ulearning.ulms.admin.gradetermcourse.exceptions.GradeTermCourseDAOSysException;
import com.ulearning.ulms.admin.gradetermcourse.form.GradeTermCourseForm;

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
public class GradeTermCourseDAOTest extends TestCase
{
        private GradeTermCourseDAO dao = null;

        public GradeTermCourseDAOTest(String name)
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
                        GradeTermCourseForm testForm = new GradeTermCourseForm();
                        testForm.setGradeTID(1);
                        testForm.setCourseName("1");
                        testForm.setDescription("1");
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");

                        GradeTermCourseDAO dao = getDAO();
                        dao.insertGradeTermCourse(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeTermCourseDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeTermCourseDAOTest] Exception End ******************");
                }
        }

        public void testDeleteGradeTermCourse()
        {
                try
                {
                        GradeTermCourseDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteGradeTermCourse(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeTermCourseDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeTermCourseDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        GradeTermCourseForm testForm = new GradeTermCourseForm();
                        testForm.setGradeCID(1);
                        testForm.setGradeTID(1);
                        testForm.setCourseName("1");
                        testForm.setDescription("1");
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");

                        GradeTermCourseDAO dao = getDAO();
                        dao.updateGradeTermCourse(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeTermCourseDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeTermCourseDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        GradeTermCourseDAO dao = getDAO();
                        GradeTermCourseForm testForm = dao.getGradeTermCourse(1);

                        //verify the result
                        //assertNotNull(delete.getGradeTermCourseID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeTermCourseDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeTermCourseDAOTest] Exception End ******************");
                }
        }

        private GradeTermCourseDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = GradeTermCourseDAOFactory.getDAO();
                        }
                        catch (GradeTermCourseDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(GradeTermCourseDAOTest.class);
        }
}
