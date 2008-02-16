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

import com.ulearning.ulms.user.group.dao.GroupDAO;
import com.ulearning.ulms.user.group.dao.GroupDAOFactory;
import com.ulearning.ulms.user.group.form.GroupForm;
import com.ulearning.ulms.user.group.exceptions.GroupDAOSysException;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051124
 * Time: 155359
 */

public class GroupDAOTest extends TestCase
{

        public GroupDAOTest(String name)
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
                        GroupForm testForm = new GroupForm();
                        testForm.setGroupname("1");
                        testForm.setRelationid(1);
                        testForm.setType(1);
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");
                        testForm.setRemark4("1");
                        testForm.setRemark5("1");

                        GroupDAO dao = getDAO();
                        dao.insertGroup(testForm);
                }
                catch (Exception e)
                {
                        System.out.println("[GroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println("[GroupDAOTest] Exception End ******************");
                }
        }

        public void testDeleteGroup()
        {
                try
                {
                        GroupDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteGroup(1);
                }
                catch (Exception e)
                {
                        System.out.println("[GroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println("[GroupDAOTest] Exception End ******************");
                }
        }


        public void testUpdate()
        {

                try
                {
                        GroupForm testForm = new GroupForm();
                        testForm.setGroupid(1);
                        testForm.setGroupname("1");
                        testForm.setRelationid(1);
                        testForm.setType(1);
                        testForm.setRemark1("1");
                        testForm.setRemark2("1");
                        testForm.setRemark3("1");
                        testForm.setRemark4("1");
                        testForm.setRemark5("1");

                        GroupDAO dao = getDAO();
                        dao.updateGroup(testForm);

                }
                catch (Exception e)
                {
                        System.out.println("[GroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println("[GroupDAOTest] Exception End ******************");
                }
        }


        public void testGet()
        {
                try
                {
                        GroupDAO dao = getDAO();
                        GroupForm testForm = dao.getGroup(1);

                        //verify the result
                        //assertNotNull(delete.getGroupID());

                }
                catch (Exception e)
                {
                        System.out.println("[GroupDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println("[GroupDAOTest] Exception End ******************");
                }
        }


        private GroupDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = GroupDAOFactory.getDAO();
                        }
                        catch (GroupDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }
                return dao;
        }

        private GroupDAO dao = null;

        public static Test suite()
        {
                return new TestSuite(GroupDAOTest.class);
        }
}

