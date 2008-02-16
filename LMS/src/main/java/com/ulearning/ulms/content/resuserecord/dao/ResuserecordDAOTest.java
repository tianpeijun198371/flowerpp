/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.resuserecord.dao;

import com.ulearning.ulms.content.resuserecord.dao.ResuserecordDAO;
import com.ulearning.ulms.content.resuserecord.dao.ResuserecordDAOFactory;
import com.ulearning.ulms.content.resuserecord.exceptions.ResuserecordDAOSysException;
import com.ulearning.ulms.content.resuserecord.form.ResuserecordForm;

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
 * Date: 20060317
 * Time: 103906
 */
public class ResuserecordDAOTest extends TestCase
{
        private ResuserecordDAO dao = null;

        public ResuserecordDAOTest(String name)
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
                        ResuserecordForm testForm = new ResuserecordForm();
                        testForm.setResID(1);
                        testForm.setResName("1");
                        testForm.setAspID(1);
                        testForm.setOrgID(1);
                        testForm.setAspName("1");
                        testForm.setOrgName("1");
                        testForm.setUserID(1);
                        testForm.setUserName("1");
                        testForm.setUserbegindate(new Date());
                        testForm.setUserenddate(new Date());
                        testForm.setRespurpost("1");
                        testForm.setUserdel(1);
                        testForm.setAudittag(1);
                        testForm.setAudituserID(1);
                        testForm.setAudituserName("1");
                        testForm.setUresremark1("1");
                        testForm.setUresremark2("1");

                        ResuserecordDAO dao = getDAO();
                        dao.insertResuserecord(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[ResuserecordDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[ResuserecordDAOTest] Exception End ******************");
                }
        }

        public void testDeleteResuserecord()
        {
                try
                {
                        ResuserecordDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteResuserecord(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[ResuserecordDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[ResuserecordDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        ResuserecordForm testForm = new ResuserecordForm();
                        testForm.setResuseID(1);
                        testForm.setResID(1);
                        testForm.setResName("1");
                        testForm.setAspID(1);
                        testForm.setOrgID(1);
                        testForm.setAspName("1");
                        testForm.setOrgName("1");
                        testForm.setUserID(1);
                        testForm.setUserName("1");
                        testForm.setUserbegindate(new Date());
                        testForm.setUserenddate(new Date());
                        testForm.setRespurpost("1");
                        testForm.setUserdel(1);
                        testForm.setAudittag(1);
                        testForm.setAudituserID(1);
                        testForm.setAudituserName("1");
                        testForm.setUresremark1("1");
                        testForm.setUresremark2("1");

                        ResuserecordDAO dao = getDAO();
                        dao.updateResuserecord(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[ResuserecordDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[ResuserecordDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        ResuserecordDAO dao = getDAO();
                        ResuserecordForm testForm = dao.getResuserecord(1);

                        //verify the result
                        //assertNotNull(delete.getResuserecordID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[ResuserecordDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[ResuserecordDAOTest] Exception End ******************");
                }
        }

        private ResuserecordDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = ResuserecordDAOFactory.getDAO();
                        }
                        catch (ResuserecordDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(ResuserecordDAOTest.class);
        }
}
