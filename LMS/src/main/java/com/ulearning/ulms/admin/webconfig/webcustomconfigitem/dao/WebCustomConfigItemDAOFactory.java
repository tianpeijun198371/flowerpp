/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 10:45:35
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustomconfigitem.dao;

import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.exceptions.CustomConfigItemDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class WebCustomConfigItemDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static WebCustomConfigItemDAO getDAO()
                throws CustomConfigItemDAOSysException
        {
                WebCustomConfigItemDAO dao = null;

                try
                {
                        dao = new WebCustomConfigItemDAOOracle();

                        /*
                          if (DBUtil.getWhichDatabase() == 0) {
                              dao = new WebCustomConfigItemDAOOracle();
                          } else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                              //dao = new PlanDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new CustomConfigItemDAOSysException(
                                "WebCustomConfigItemDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
