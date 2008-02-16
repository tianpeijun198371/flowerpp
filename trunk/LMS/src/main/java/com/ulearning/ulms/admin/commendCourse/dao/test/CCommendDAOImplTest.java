/**
 * CCommendDAOImplTest.java.
 * User: zengwj Date: 2005-5-18 12:47:18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.commendCourse.dao.test;

import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAO;
import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAOFactory;
import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAOImpl;
import com.ulearning.ulms.admin.commendCourse.form.CCommendForm;

import junit.framework.*;

import java.util.Date;
import java.util.List;


/**
 * @see com.ulearning.ulms.admin.commendCourse.dao.CCommendDAOImpl
 */
public class CCommendDAOImplTest extends TestCase
{
        CCommendDAOImpl cCommendDAOImpl;

        /**
         * Sets up the fixture, for example, open a network connection.
         * This method is called before a test is executed.
         */
        protected void setUp() throws Exception
        {
                //super.setUp();
                // com.ulearning.ulms.log.LogUtilTest.initLog();
                //todo please create a object for CCommendDAOImpl here
        }

        /**
         * Tears down the fixture, for example, close a network connection.
         * This method is called after a test is executed.
         */
        protected void tearDown() throws Exception
        {
                super.tearDown();
        }

        /**
         * @throws Exception
         * @see CCommendDAOImpl#addCCommend(CCommendForm)
         */
        public void testAddCCommend() throws Exception
        {
                CCommendForm details = new CCommendForm();
                details.setName("testCommend");
                details.setBeginTime(new Date());
                details.setAimStu("student");
                details.setPeriod("12");
                details.setCourseType("1");
                details.setPublishTime(new Date());
                details.setDisplayed(0);

                CCommendDAO DAO = CCommendDAOFactory.getDAO();
                DAO.addCCommend(details);
        }

        /**
         * @throws Exception
         * @see CCommendDAOImpl#updateCCommend(CCommendForm)
         */
        public void testUpdateCCommend() throws Exception
        {
                CCommendForm details = new CCommendForm();
                details.setCcourseID(1);
                details.setName("testName");
                details.setBeginTime(new Date());
                details.setAimStu("student");
                details.setPeriod("12");
                details.setCourseType("1");
                details.setPublishTime(new Date());
                details.setDisplayed(0);

                CCommendDAO DAO = CCommendDAOFactory.getDAO();
                DAO.updateCCommend(details);
        }

        /**
         * @throws Exception
         * @see CCommendDAOImpl#getCCommend(int)
         */
        public void testGetCCommend() throws Exception
        {
                int courseID = 1;
                CCommendDAO DAO = CCommendDAOFactory.getDAO();
                CCommendForm result = DAO.getCCommend(courseID);

                if (result != null)
                {
                        System.out.println("name ==========================" +
                                result.getName());
                }
        }

        /**
         * @throws Exception
         * @see CCommendDAOImpl#getCCommend_month(String,int)
         */
        public void testGetCCommend_month() throws Exception
        {
                String displayed = "-1";
                int month = -1;
                CCommendDAO DAO = CCommendDAOFactory.getDAO();
                List result = DAO.getCCommend_month(displayed, month);

                for (int i = 0; (result != null) && (i < result.size()); i++)
                {
                        CCommendForm form = (CCommendForm) result.get(i);
                        System.out.println("name ===========================" +
                                form.getName());
                }
        }

        /**
         * @throws Exception
         * @see CCommendDAOImpl#removeCCommend(int)
         */
        public void testRemoveCCommend() throws Exception
        {
                int ccourseID = 1;
                CCommendDAO DAO = CCommendDAOFactory.getDAO();
                DAO.removeCCommend(ccourseID);
        }
}
