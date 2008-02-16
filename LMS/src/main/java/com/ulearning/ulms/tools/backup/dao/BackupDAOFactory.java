/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.dao;

import com.ulearning.ulms.tools.backup.exceptions.BackupDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class BackupDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static BackupDAO getDAO() throws BackupDAOSysException
        {
                BackupDAO dao = null;

                try
                {
                        dao = new BackupDAOImpl();

                        /*
                          if (DBUtil.getWhichDatabase() == 0)
                          {
                                  dao = new BackupDAOOracle();
                          }
                          else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                                  //dao = new BackupDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new BackupDAOSysException(
                                "BackupDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
