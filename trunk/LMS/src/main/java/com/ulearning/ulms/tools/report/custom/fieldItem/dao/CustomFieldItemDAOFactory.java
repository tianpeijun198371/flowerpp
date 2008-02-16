/**
 * Created by IntelliJ IDEA.
 * CustomFieldItem: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.fieldItem.dao;

import com.ulearning.ulms.tools.report.custom.fieldItem.exceptions.CustomFieldItemDAOSysException;
import com.ulearning.ulms.util.DBUtil;

public class CustomFieldItemDAOFactory
{

        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static CustomFieldItemDAO getDAO() throws CustomFieldItemDAOSysException
        {
                CustomFieldItemDAO dao = null;
                try
                {
                        dao = new CustomFieldItemDAOOracle();
                        /*
                        if (DBUtil.getWhichDatabase() == 0)
                        {
                                dao = new CustomFieldItemDAOOracle();
                        }
                        else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                        {
                                //dao = new CustomFieldItemDAOSQLServer();
                        }
                        */
                }
                catch (Exception se)
                {
                        throw new CustomFieldItemDAOSysException("CustomFieldItemDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }
                return dao;
        }
}
