/** * AssignProcessDAOFactory.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.dao;

import com.ulearning.ulms.tools.assignment.assignprocess.exceptions.AssignProcessDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class AssignProcessDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static AssignProcessDAO getDAO() throws AssignProcessDAOSysException
        {
                AssignProcessDAO dao = null;

                try
                {
                        dao = new AssignProcessDAOImpl();

                        /*
                          if (DBUtil.getWhichDatabase() == 0)
                          {
                              dao = new AssignProcessDAOOracle();
                          } else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                              //dao = new AssignProcessDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new AssignProcessDAOSysException(
                                "AssignProcessDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
