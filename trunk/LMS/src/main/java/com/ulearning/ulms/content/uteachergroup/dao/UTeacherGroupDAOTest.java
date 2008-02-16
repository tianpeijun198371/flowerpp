/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.uteachergroup.dao;

import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAO;
import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAOFactory;
import com.ulearning.ulms.content.uteachergroup.exceptions.UTeacherGroupDAOSysException;
import com.ulearning.ulms.content.uteachergroup.form.UTeacherGroupForm;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UTeacherGroupDAOTest extends TestCase
{
        private UTeacherGroupDAO dao = null;

        public UTeacherGroupDAOTest(String name)
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
                        UTeacherGroupForm testForm = new UTeacherGroupForm();
                        testForm.setUserID(1);
                        testForm.setG_Ship_ID(1);
                        testForm.setRemark("1");

                        UTeacherGroupDAO dao = getDAO();
                        dao.insertUTeacherGroup(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[UTeacherGroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[UTeacherGroupDAOTest] Exception End ******************");
                }
        }

        public void testDeleteUTeacherGroup()
        {
                try
                {
                        UTeacherGroupDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteUTeacherGroup(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[UTeacherGroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[UTeacherGroupDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        UTeacherGroupForm testForm = new UTeacherGroupForm();
                        testForm.setUteachgroupID(1);
                        testForm.setUserID(1);
                        testForm.setG_Ship_ID(1);
                        testForm.setRemark("1");

                        UTeacherGroupDAO dao = getDAO();
                        dao.updateUTeacherGroup(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[UTeacherGroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[UTeacherGroupDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        UTeacherGroupDAO dao = getDAO();
                        UTeacherGroupForm testForm = dao.getUTeacherGroup(1);

                        //verify the result
                        //assertNotNull(delete.getUTeacherGroupID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[UTeacherGroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[UTeacherGroupDAOTest] Exception End ******************");
                }
        }

        private UTeacherGroupDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = UTeacherGroupDAOFactory.getDAO();
                        }
                        catch (UTeacherGroupDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(UTeacherGroupDAOTest.class);
        }
}
