/**
 * SysConfigDAOFactory.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.dao;

import com.ulearning.ulms.admin.sysconfig.exceptions.SysConfigDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class SysConfigDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static SysConfigDAO getDAO() throws SysConfigDAOSysException
        {
                SysConfigDAO dao = null;

                // int database = 0;
                try
                {
                        dao = new SysConfigDAOImpl();

                        /*if (DBUtil.getWhichDatabase() == 0)
                        {
                                dao = new SysConfigDAOImpl();
                                //dao = new SysConfigDAOOracle();
                        }
                        else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                        {
                                //dao = new PlanDAOSQLServer();
                        }
                        else if (DBUtil.getWhichDatabase() == 2)
                        {
                                //dao=new PlanDAOMySQl();
                        }*/
                }
                catch (Exception se)
                {
                        throw new SysConfigDAOSysException(
                                "PlanDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
