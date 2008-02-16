/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.report.dao;

import com.ulearning.ulms.tools.report.custom.report.exceptions.ReportDAOSysException;
import com.ulearning.ulms.util.DBUtil;

public class ReportDAOFactory
{

        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static ReportDAO getDAO() throws ReportDAOSysException
        {
                ReportDAO dao = null;
                try
                {
                        dao = new ReportDAOImpl();
                        /*
                        if (DBUtil.getWhichDatabase() == 0)
                        {
                                dao = new ReportDAOOracle();
                        }
                        else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                        {
                                //dao = new ReportDAOSQLServer();
                        }
                        */
                }
                catch (Exception se)
                {
                        throw new ReportDAOSysException("ReportDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }
                return dao;
        }
}
