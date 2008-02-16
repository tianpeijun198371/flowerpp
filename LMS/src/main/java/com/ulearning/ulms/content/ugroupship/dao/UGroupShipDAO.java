/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.ugroupship.dao;

import com.ulearning.ulms.content.ugroupship.exceptions.UGroupShipDAOSysException;
import com.ulearning.ulms.content.ugroupship.form.UGroupShipForm;

import java.io.Serializable;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public interface UGroupShipDAO
{
        public Serializable insertUGroupShip(UGroupShipForm tf)
                throws UGroupShipDAOSysException;

        public void updateUGroupShip(UGroupShipForm tf)
                throws UGroupShipDAOSysException;

        public void deleteUGroupShip(int G_Ship_ID)
                throws UGroupShipDAOSysException;

        public List getUGroupShipList() throws UGroupShipDAOSysException;

        public List findbyUserId(int userId) throws UGroupShipDAOSysException;

        public UGroupShipForm getUGroupShip(int G_Ship_ID)
                throws UGroupShipDAOSysException;
}
