/**
 * AnnouncementDAOFactory.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.announcement.dao;

import com.ulearning.ulms.tools.announcement.exceptions.*;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class AnnouncementDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static AnnouncementDAO getDAO() throws AnnouncementSysException
        {
                AnnouncementDAO dao = null;
                Connection conn = null;
                String DBProductName = null;

                try
                {
                        dao = new AnnouncementDAOImpl();

                        /*
                          conn = DBUtil.getConnection();
                          DBProductName =conn.getMetaData().getDatabaseProductName().trim();
                          conn.close();
                          LogUtil.debug("announcement", "[AnnouncementDAOFactory]---- the DBProductName:"+DBProductName);
                              if (DBProductName.startsWith("Oracle"))
                              {
                                  //dao = new AnnouncementDAOOracle();
                                      dao = new AnnouncementDAOImpl();
                                  LogUtil.debug("announcement", "[AnnouncementDAOFactory]----Using Product:" + DBProductName);
                              }
                              else if (DBProductName.startsWith("Microsoft")) //"Microsoft SQL Server"))
                              {
                                  LogUtil.debug("announcement", "[AnnouncementDAOFactory]----Using Product:" + DBProductName);
                                  dao = new AnnouncementDAOMsSQLServer2000();
                              }
                        */
                }
                catch (Exception se)
                {
                        LogUtil.debug("announcement",
                                "[AnnouncementDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new AnnouncementSysException(se);
                }

                return dao;
        }
}
