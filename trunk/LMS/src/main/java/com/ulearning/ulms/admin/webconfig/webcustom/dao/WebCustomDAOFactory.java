/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 10:26:35
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustom.dao;

import com.ulearning.ulms.admin.webconfig.webcustom.exceptions.WebCustomDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class WebCustomDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static WebCustomDAO getDAO() throws WebCustomDAOSysException
        {
                WebCustomDAO dao = null;

                try
                {
                        dao = new WebCustomDAOImpl();

                        /*
                          if (DBUtil.getWhichDatabase() == 0) {
                              dao = new WebCustomDAOOracle();
                          } else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                              //dao = new PlanDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new WebCustomDAOSysException(
                                "WebCustomDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
