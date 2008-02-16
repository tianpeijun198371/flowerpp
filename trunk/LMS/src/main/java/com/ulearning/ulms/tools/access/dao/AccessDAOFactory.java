/**
 * AccessDAOFactory.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.dao;

import com.ulearning.ulms.tools.access.exceptions.*;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class AccessDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static AccessDAO getDAO() throws AccessSysException
        {
                AccessDAO dao = null;
                Connection conn = null;
                String DBProductName = null;

                try
                {
                        dao = new AccessDAOImpl();

                        /*
                          conn = DBUtil.getConnection();
                          DBProductName =conn.getMetaData().getDatabaseProductName().trim();
                          conn.close();
                          LogUtil.debug("access", "[AccessDAOFactory]---- the DBProductName:"+DBProductName);
                              if (DBProductName.startsWith("Oracle"))
                              {
                                  dao = new AccessDAOOracle();
                                  LogUtil.debug("access", "[AccessDAOFactory]----Using Product:" + DBProductName);
                              }
                              else if (DBProductName.startsWith("Microsoft")) //"Microsoft SQL Server"))
                              {
                                  LogUtil.debug("access", "[AccessDAOFactory]----Using Product:" + DBProductName);
                                  dao = new AccessDAOMsSQLServer2000();
                              }
                        */
                }
                catch (Exception se)
                {
                        LogUtil.debug("access",
                                "[AccessDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new AccessSysException(se);
                }

                return dao;
        }
}
