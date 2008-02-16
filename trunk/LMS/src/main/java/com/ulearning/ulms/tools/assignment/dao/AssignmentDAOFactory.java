/** * AssignmentDAOFactory.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.dao;

import com.ulearning.ulms.tools.assignment.exceptions.AssignmentDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class AssignmentDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static AssignmentDAO getDAO() throws AssignmentDAOSysException
        {
                AssignmentDAO dao = null;

                try
                {
                        dao = new AssignmentDAOImpl();

                        /*
                          if (DBUtil.getWhichDatabase() == 0) {
                              dao = new AssignmentDAOOracle();
                          } else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                              //dao = new AssignmentDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new AssignmentDAOSysException(
                                "AssignmentDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
