/**
 * TopTenDAOImplTest.java.
 * User: zengwj Date: 2005-6-2 14:00:23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.topTen.dao.test;

import com.ulearning.ulms.admin.topTen.dao.TopTenDAO;
import com.ulearning.ulms.admin.topTen.dao.TopTenDAOFactory;
import com.ulearning.ulms.admin.topTen.dao.TopTenDAOImpl;
import com.ulearning.ulms.admin.topTen.form.TopTenForm;
import com.ulearning.ulms.admin.topTen.model.TopTenModel;

import junit.framework.*;

import java.util.Date;
import java.util.List;


/**
 * @see com.ulearning.ulms.admin.topTen.dao.TopTenDAOImpl
 */
public class TopTenDAOImplTest extends TestCase
{
        TopTenDAO DAO;

        /**
         * Sets up the fixture, for example, open a network connection.
         * This method is called before a test is executed.
         */
        protected void setUp() throws Exception
        {
                //super.setUp();
                //com.ulearning.ulms.log.LogUtilTest.initLog();
                //todo please create a object for TopTenDAOImpl here
        }

        /**
         * Tears down the fixture, for example, close a network connection.
         * This method is called after a test is executed.
         */
        protected void tearDown() throws Exception
        {
                //super.tearDown();
        }

        /**
         * @throws Exception
         * @see TopTenDAOImpl#getTopTen_T_List(String,String)
         */
        public void testGetTopTen_T_List() throws Exception
        {
                String teacher_type = "1";
                String is_display = "1";
                DAO = TopTenDAOFactory.getDAO();

                List result = DAO.getTopTen_T_List(teacher_type, is_display);

                if (result != null)
                {
                        System.out.println("size =====================" + result.size());
                }
        }

        public void testAddTopTen() throws Exception
        {
                DAO = TopTenDAOFactory.getDAO();

                TopTenForm ttm = new TopTenForm();
                ttm.setCreattime(new Date());
                ttm.setDuty("公共课1");
                ttm.setName("商务英语1");
                ttm.setType("1");
                ttm.setC_t("林星43");
                ttm.setGrade(4);
                ttm.setIs_display("1");
                DAO.addTopTen(ttm);
        }
}
