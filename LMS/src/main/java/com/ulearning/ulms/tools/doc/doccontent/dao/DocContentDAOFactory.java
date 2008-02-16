/** * DocumentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.doccontent.dao;

import com.ulearning.ulms.tools.doc.doccontent.exceptions.DocContentDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class DocContentDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static DocContentDAO getDAO() throws DocContentDAOSysException
        {
                DocContentDAO dao = null;

                try
                {
                        dao = new DocContentDAOImpl();

                        /*
                          if (DBUtil.getWhichDatabase() == 0) {
                              dao = new DocContentDAOOracle();
                          } else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                              //dao = new DocContentDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new DocContentDAOSysException(
                                "DocContentDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
