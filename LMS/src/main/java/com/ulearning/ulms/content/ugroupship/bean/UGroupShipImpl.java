/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.ugroupship.bean;

import com.ulearning.ulms.content.ugroupship.dao.UGroupShipDAO;
import com.ulearning.ulms.content.ugroupship.dao.UGroupShipDAOFactory;
import com.ulearning.ulms.content.ugroupship.exceptions.UGroupShipDAOSysException;
import com.ulearning.ulms.content.ugroupship.form.UGroupShipForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UGroupShipImpl
{
        public List getUGroupShipList() throws UGroupShipDAOSysException
        {
                List UGroupShipList = null;

                try
                {
                        UGroupShipDAO dao = UGroupShipDAOFactory.getDAO();
                        UGroupShipList = dao.getUGroupShipList();
                }
                catch (UGroupShipDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return UGroupShipList;
        }

        public UGroupShipForm getUGroupShip(int G_Ship_ID)
                throws UGroupShipDAOSysException
        {
                UGroupShipForm tf = null;

                try
                {
                        UGroupShipDAO dao = UGroupShipDAOFactory.getDAO();
                        tf = dao.getUGroupShip(G_Ship_ID);
                }
                catch (UGroupShipDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public void deleteUGroupShip(int G_Ship_ID)
                throws UGroupShipDAOSysException
        {
                try
                {
                        UGroupShipDAO dao = UGroupShipDAOFactory.getDAO();
                        dao.deleteUGroupShip(G_Ship_ID);
                }
                catch (UGroupShipDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }

        public List findbyUserId(int userId) throws UGroupShipDAOSysException
        {
                List UGroupShipList = null;

                try
                {
                        UGroupShipDAO dao = UGroupShipDAOFactory.getDAO();
                        UGroupShipList = dao.findbyUserId(userId);
                }
                catch (UGroupShipDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return UGroupShipList;
        }
}
