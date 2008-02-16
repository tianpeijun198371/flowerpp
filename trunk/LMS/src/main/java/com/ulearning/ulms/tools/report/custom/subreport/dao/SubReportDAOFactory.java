/**
 * Created by IntelliJ IDEA.
 * SubReport: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.subreport.dao;

import com.ulearning.ulms.tools.report.custom.subreport.exceptions.SubReportDAOSysException;
import com.ulearning.ulms.util.DBUtil;

public class SubReportDAOFactory
{

        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static SubReportDAO getDAO() throws SubReportDAOSysException
        {
                SubReportDAO dao = null;
                try
                {
                        dao = new SubReportDAOOracle();
                        /*
                        if (DBUtil.getWhichDatabase() == 0)
                        {
                                dao = new SubReportDAOOracle();
                        }
                        else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                        {
                                //dao = new SubReportDAOSQLServer();
                        }
                        */
                }
                catch (Exception se)
                {
                        throw new SubReportDAOSysException("SubReportDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }
                return dao;
        }
}
