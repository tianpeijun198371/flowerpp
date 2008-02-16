/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.ugroupship.dao;

import com.ulearning.ulms.content.ugroupship.exceptions.UGroupShipDAOSysException;
import com.ulearning.ulms.content.ugroupship.form.UGroupShipForm;
import com.ulearning.ulms.content.ugroupship.model.UGroupShipModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.HibernateDAO;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UGroupShipDAOImpl implements UGroupShipDAO
{
        public Serializable insertUGroupShip(UGroupShipForm details)
                throws UGroupShipDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getUGroupShipModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UGroupShipDAOSysException("" + e);
                }

                return s;
        }

        public void updateUGroupShip(UGroupShipForm details)
                throws UGroupShipDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getUGroupShipModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UGroupShipDAOSysException("" + e);
                }
        }

        /**
         * Remove the UGroupShip from database by the G_SHIP_ID
         *
         * @param G_SHIP_ID
         * @throws UGroupShipDAOSysException
         */
        public void deleteUGroupShip(int pkID) throws UGroupShipDAOSysException
        {
                String hql = " from UGroupShipModel where G_SHIP_ID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UGroupShipDAOSysException("" + e);
                }
        }

        /**
         * Get the UGroupShip info via the unique G_SHIP_ID
         *
         * @param G_SHIP_ID
         * @return the prepared UGroupShipForm, default is null
         * @throws UGroupShipDAOSysException
         */
        public UGroupShipForm getUGroupShip(int pkID)
                throws UGroupShipDAOSysException
        {
                UGroupShipForm bf = new UGroupShipForm();
                UGroupShipForm res = null;
                List tmList = null;
                String hql = " from UGroupShipModel where G_SHIP_ID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UGroupShipDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        UGroupShipModel bm = (UGroupShipModel) tmList.get(0);
                        res = bf.getUGroupShipForm(bm);
                }

                return res;
        }

        /**
         * Get the UGroupShip list by the catalogID
         *
         * @param type relationID
         * @return The prepared arraylist object,default size is 0
         * @throws UGroupShipDAOSysException
         */
        public List getUGroupShipList() throws UGroupShipDAOSysException
        {
                UGroupShipForm bf = new UGroupShipForm();
                UGroupShipModel bm = null;
                ArrayList UGroupShipList = new ArrayList();
                List tmList = null;
                String hql = " from UGroupShipModel where  1 = 1 ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UGroupShipDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (UGroupShipModel) tmList.get(i);
                        UGroupShipList.add(bf.getUGroupShipForm(bm));
                }

                return UGroupShipList;
        }

        public List findbyUserId(int userid) throws UGroupShipDAOSysException
        {
                UGroupShipForm bf = new UGroupShipForm();
                UGroupShipModel bm = null;
                ArrayList UGroupShipList = new ArrayList();
                List tmList = null;
                String hql = " from UGroupShipModel where  1 = 1 " + "and userID=" +
                        userid;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UGroupShipDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (UGroupShipModel) tmList.get(i);
                        UGroupShipList.add(bf.getUGroupShipForm(bm));
                }

                return UGroupShipList;
        }

        public static void main(String[] args)
        {
                try
                {
                        UGroupShipForm ship = new UGroupShipForm();
                        ship.setG_Ship_ID(1001);
                        ship.setGroupName("first group");
                        ship.setPerdUrl("ddd.do");
                        ship.setUserID(180);
                        ship.setRemark("testsd");

                        UGroupShipDAOImpl usImp = new UGroupShipDAOImpl();
                        usImp.insertUGroupShip(ship);

                        //List list = (new UGroupShipDAOImpl()).findbyUserId(180);
                        // System.out.println(list.size());
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
}
