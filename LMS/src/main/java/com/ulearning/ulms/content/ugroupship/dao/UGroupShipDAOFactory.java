/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.ugroupship.dao;

import com.ulearning.ulms.content.ugroupship.exceptions.UGroupShipDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UGroupShipDAOFactory
{
        public static UGroupShipDAO getDAO() throws UGroupShipDAOSysException
        {
                UGroupShipDAO dao = null;

                try
                {
                        dao = new UGroupShipDAOImpl();
                }
                catch (Exception se)
                {
                        throw new UGroupShipDAOSysException(
                                "UGroupShipDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
