/**
 * CertDAOFactory.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.dao;

import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class CertDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static CertDAO getDAO() throws CertDAOSysException
        {
                CertDAO dao = null;
                int database = 0;

                try
                {
                        dao = new CertDAOImpl();

                        /*database = DBUtil.getWhichDatabase();
                        if (database  == DBUtil.ORACLE)
                        {
                            dao = new CertDAOImpl();
                            LogUtil.debug("course", "[CertDAOFactory]----Using DB Product:oracle");
                        } else if (database == DBUtil.SQLServer2000) //"Microsoft SQL Server"))
                        {
                            dao = new CertDAOImpl();
                            LogUtil.debug("course", "[CertDAOFactory]----Using DB Product:Microsoft SQL Server");
                        }else if (database == DBUtil.MySQL)
                        {
                                    //todo: implemented later.
                        }*/
                }
                catch (Exception se)
                {
                        throw new CertDAOSysException(
                                "PlanDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
