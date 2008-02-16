/**
 * ScormDAOFactory.java.
 * User: fengch  Date: 2004-7-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class ScormDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static ScormDAO getDAO() throws ScormSysException
        {
                ScormDAO dao = null;

                int database = DBUtil.getWhichDatabase();

                if (database == DBUtil.ORACLE)
                {
                        dao = new ScormDAOOracle();
                        LogUtil.debug("scorm",
                                "[ScormDAOFactory]----Using DB Product:oracle");
                }
                else if (database == DBUtil.SQLServer2000) //"Microsoft SQL Server"))
                {
                        dao = new ScormDAOMsSQLServer2000();
                        LogUtil.debug("scorm",
                                "[ScormDAOFactory]----Using DB Product:Microsoft SQL Server");
                }
                else if (database == DBUtil.DB2)
                {
                        dao = new ScormDAODB2();
                }

                return dao;
        }
}
