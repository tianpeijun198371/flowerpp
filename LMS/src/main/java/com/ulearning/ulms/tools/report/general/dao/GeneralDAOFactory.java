/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.general.dao;

import com.ulearning.ulms.tools.report.general.exceptions.GeneralDAOSysException;
import com.ulearning.ulms.tools.report.general.dao.GeneralDAO;
import com.ulearning.ulms.util.DBUtil;

public class GeneralDAOFactory
{

        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static GeneralDAO getDAO() throws GeneralDAOSysException
        {
                GeneralDAO dao = null;
                try
                {
                        dao = new GeneralDAOImpl();
                        /*
                        if (DBUtil.getWhichDatabase() == 0)
                        {
                                dao = new GeneralDAOImpl();
                        }
                        else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                        {
                                //dao = new ReportDAOSQLServer();
                        }
                        */
                }
                catch (Exception se)
                {
                        throw new GeneralDAOSysException("GeneralDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }
                return dao;
        }
}
