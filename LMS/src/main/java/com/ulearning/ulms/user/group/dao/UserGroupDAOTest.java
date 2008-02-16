/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.dao;

import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.util.Date;

import com.ulearning.ulms.user.group.dao.UserGroupDAO;
import com.ulearning.ulms.user.group.dao.UserGroupDAOFactory;
import com.ulearning.ulms.user.group.form.UserGroupForm;
import com.ulearning.ulms.user.group.exceptions.UserGroupDAOSysException;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051130
 * Time: 142230
 */

public class UserGroupDAOTest extends TestCase
{

        public UserGroupDAOTest(String name)
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
                        UserGroupForm testForm = new UserGroupForm();
                        testForm.setGroupid(1);
                        testForm.setUserid(1);
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");
                        testForm.setRemark4("1");

                        UserGroupDAO dao = getDAO();
                        dao.insertUserGroup(testForm);
                }
                catch (Exception e)
                {
                        System.out.println("[UserGroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println("[UserGroupDAOTest] Exception End ******************");
                }
        }

        public void testDeleteUserGroup()
        {
                try
                {
                        UserGroupDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteUserGroup(1);
                }
                catch (Exception e)
                {
                        System.out.println("[UserGroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println("[UserGroupDAOTest] Exception End ******************");
                }
        }


        public void testUpdate()
        {

                try
                {
                        UserGroupForm testForm = new UserGroupForm();
                        testForm.setUsergroupid(1);
                        testForm.setGroupid(1);
                        testForm.setUserid(1);
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");
                        testForm.setRemark4("1");

                        UserGroupDAO dao = getDAO();
                        dao.updateUserGroup(testForm);

                }
                catch (Exception e)
                {
                        System.out.println("[UserGroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println("[UserGroupDAOTest] Exception End ******************");
                }
        }


        public void testGet()
        {
                try
                {
                        UserGroupDAO dao = getDAO();
                        UserGroupForm testForm = dao.getUserGroup(1);

                        //verify the result
                        //assertNotNull(delete.getUserGroupID());

                }
                catch (Exception e)
                {
                        System.out.println("[UserGroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println("[UserGroupDAOTest] Exception End ******************");
                }
        }


        private UserGroupDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = UserGroupDAOFactory.getDAO();
                        }
                        catch (UserGroupDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }
                return dao;
        }

        private UserGroupDAO dao = null;

        public static Test suite()
        {
                return new TestSuite(UserGroupDAOTest.class);
        }
}

