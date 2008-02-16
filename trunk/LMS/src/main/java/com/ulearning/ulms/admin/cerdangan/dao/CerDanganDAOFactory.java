/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.cerdangan.dao;

import com.ulearning.ulms.admin.cerdangan.dao.CerDanganDAOImpl;
import com.ulearning.ulms.admin.cerdangan.exceptions.CerDanganDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class CerDanganDAOFactory
{
        public static com.ulearning.ulms.admin.cerdangan.dao.CerDanganDAO getDAO()
                throws CerDanganDAOSysException
        {
                com.ulearning.ulms.admin.cerdangan.dao.CerDanganDAO dao = null;

                try
                {
                        dao = new CerDanganDAOImpl();
                }
                catch (Exception se)
                {
                        throw new CerDanganDAOSysException(
                                "CerDanganDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
