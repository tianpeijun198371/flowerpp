/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeusercourse.dao;

import com.ulearning.ulms.admin.gradeusercourse.dao.GradeUserCourseDAO;
import com.ulearning.ulms.admin.gradeusercourse.dao.GradeUserCourseDAOFactory;
import com.ulearning.ulms.admin.gradeusercourse.exceptions.GradeUserCourseDAOSysException;
import com.ulearning.ulms.admin.gradeusercourse.form.GradeUserCourseForm;

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
public class GradeUserCourseDAOTest extends TestCase
{
        private GradeUserCourseDAO dao = null;

        public GradeUserCourseDAOTest(String name)
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
                        GradeUserCourseForm testForm = new GradeUserCourseForm();
                        testForm.setGradetID(1);
                        testForm.setGradecID(1);
                        testForm.setGradeuserID(1);
                        testForm.setCoursegrade(1);

                        GradeUserCourseDAO dao = getDAO();
                        dao.insertGradeUserCourse(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeUserCourseDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeUserCourseDAOTest] Exception End ******************");
                }
        }

        public void testDeleteGradeUserCourse()
        {
                try
                {
                        GradeUserCourseDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteGradeUserCourse(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeUserCourseDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeUserCourseDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        GradeUserCourseForm testForm = new GradeUserCourseForm();
                        testForm.setGradeUCID(1);
                        testForm.setGradetID(1);
                        testForm.setGradecID(1);
                        testForm.setGradeuserID(1);
                        testForm.setCoursegrade(1);

                        GradeUserCourseDAO dao = getDAO();
                        dao.updateGradeUserCourse(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeUserCourseDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeUserCourseDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        GradeUserCourseDAO dao = getDAO();
                        GradeUserCourseForm testForm = dao.getGradeUserCourse(1);

                        //verify the result
                        //assertNotNull(delete.getGradeUserCourseID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeUserCourseDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeUserCourseDAOTest] Exception End ******************");
                }
        }

        private GradeUserCourseDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = GradeUserCourseDAOFactory.getDAO();
                        }
                        catch (GradeUserCourseDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(GradeUserCourseDAOTest.class);
        }
}
