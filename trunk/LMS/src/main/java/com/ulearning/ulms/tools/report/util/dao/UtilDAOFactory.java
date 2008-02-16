/**
 * Created by IntelliJ IDEA.
 * Report: xiejh
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.util.dao;

import com.ulearning.ulms.tools.report.util.exceptions.UtilDAOSysException;
import com.ulearning.ulms.util.DBUtil;

public class UtilDAOFactory
{
        public static UtilDAO getDAO() throws UtilDAOSysException
        {
                UtilDAO dao = null;
                try
                {
                        dao = new UtilDAOImpl();
                        /*
                        if (DBUtil.getWhichDatabase() == 0)
                        {
                                dao = new UtilDAOImpl();
                        }
                        else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                        {
                                //dao = new ReportDAOSQLServer();
                        }
                        */
                }
                catch (Exception se)
                {
                        throw new UtilDAOSysException("UtilDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }
                return dao;
        }
}
