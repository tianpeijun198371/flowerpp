/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.ugroupship.dao;

import com.ulearning.ulms.content.ugroupship.dao.UGroupShipDAO;
import com.ulearning.ulms.content.ugroupship.dao.UGroupShipDAOFactory;
import com.ulearning.ulms.content.ugroupship.exceptions.UGroupShipDAOSysException;
import com.ulearning.ulms.content.ugroupship.form.UGroupShipForm;

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
public class UGroupShipDAOTest extends TestCase
{
        private UGroupShipDAO dao = null;

        public UGroupShipDAOTest(String name)
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
                        UGroupShipForm testForm = new UGroupShipForm();
                        testForm.setUserID(1);
                        testForm.setGroupName("1");
                        testForm.setPerdUrl("1");
                        testForm.setRemark("1");

                        UGroupShipDAO dao = getDAO();
                        dao.insertUGroupShip(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[UGroupShipDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[UGroupShipDAOTest] Exception End ******************");
                }
        }

        public void testDeleteUGroupShip()
        {
                try
                {
                        UGroupShipDAO dao = getDAO();
                        ArrayList al = new ArrayList();
                        al.add(new Integer(2));
                        dao.deleteUGroupShip(1);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[UGroupShipDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[UGroupShipDAOTest] Exception End ******************");
                }
        }

        public void testUpdate()
        {
                try
                {
                        UGroupShipForm testForm = new UGroupShipForm();
                        testForm.setG_Ship_ID(1);
                        testForm.setUserID(1);
                        testForm.setGroupName("1");
                        testForm.setPerdUrl("1");
                        testForm.setRemark("1");

                        UGroupShipDAO dao = getDAO();
                        dao.updateUGroupShip(testForm);
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[UGroupShipDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[UGroupShipDAOTest] Exception End ******************");
                }
        }

        public void testGet()
        {
                try
                {
                        UGroupShipDAO dao = getDAO();
                        UGroupShipForm testForm = dao.getUGroupShip(1);

                        //verify the result
                        //assertNotNull(delete.getUGroupShipID());
                }
                catch (Exception e)
                {
                        System.out.println(
                                "[UGroupShipDAOTest] Exception Begin ******************");
                        e.printStackTrace();
                        System.out.println(
                                "[UGroupShipDAOTest] Exception End ******************");
                }
        }

        private UGroupShipDAO getDAO()
        {
                if (dao == null)
                {
                        try
                        {
                                dao = UGroupShipDAOFactory.getDAO();
                        }
                        catch (UGroupShipDAOSysException ude)
                        {
                                ude.printStackTrace();
                        }
                }

                return dao;
        }

        public static Test suite()
        {
                return new TestSuite(UGroupShipDAOTest.class);
        }
}
