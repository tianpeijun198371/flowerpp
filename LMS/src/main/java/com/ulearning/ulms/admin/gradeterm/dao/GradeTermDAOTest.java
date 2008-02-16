/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.dao;

import com.ulearning.ulms.admin.gradeterm.dao.GradeTermDAO;
import com.ulearning.ulms.admin.gradeterm.dao.GradeTermDAOFactory;
import com.ulearning.ulms.admin.gradeterm.exceptions.GradeTermDAOSysException;
import com.ulearning.ulms.admin.gradeterm.form.GradeTermForm;

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
public class GradeTermDAOTest extends TestCase
{
        private GradeTermDAO dao = null;

        public GradeTermDAOTest(String name)
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
                        GradeTermForm testForm = new GradeTermForm();
                        testForm.setGrade("1");
                        testForm.setTerm("1");
                        testForm.setSpeciality("1");
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");

                        GradeTermDAO dao = getDAO();
                        dao.insertGradeTerm(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeTermDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeTermDAOTest] Exception End ******************");
                }
        }

        public void testDeleteGradeTerm()
        {
                try
                {
                        GradeTermDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteGradeTerm(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeTermDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeTermDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        GradeTermForm testForm = new GradeTermForm();
                        testForm.setGradetID(1);
                        testForm.setGrade("1");
                        testForm.setTerm("1");
                        testForm.setSpeciality("1");
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");

                        GradeTermDAO dao = getDAO();
                        dao.updateGradeTerm(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeTermDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeTermDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        GradeTermDAO dao = getDAO();
                        GradeTermForm testForm = dao.getGradeTerm(1);

                        //verify the result
                        //assertNotNull(delete.getGradeTermID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[GradeTermDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[GradeTermDAOTest] Exception End ******************");
                }
        }

        private GradeTermDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = GradeTermDAOFactory.getDAO();
                        }
                        catch (GradeTermDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(GradeTermDAOTest.class);
        }
}
