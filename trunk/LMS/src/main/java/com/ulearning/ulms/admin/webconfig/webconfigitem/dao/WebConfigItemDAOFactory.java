/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 13:58:45
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webconfigitem.dao;

import com.ulearning.ulms.admin.webconfig.webconfigitem.exceptions.WebConfigItemDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class WebConfigItemDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static WebConfigItemDAO getDAO() throws WebConfigItemDAOSysException
        {
                WebConfigItemDAO dao = null;

                try
                {
                        dao = new WebConfigItemDAOOracle();

                        /*
                          if (DBUtil.getWhichDatabase() == 0) {
                              dao = new WebConfigItemDAOOracle();
                          } else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                              //dao = new PlanDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new WebConfigItemDAOSysException(
                                "WebConfigItemDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
