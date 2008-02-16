/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:08:29
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.dao;

import com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions.ColSignDetailDAOSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class ColSignDetailDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static ColSignDetailDAO getDAO() throws ColSignDetailDAOSysException
        {
                ColSignDetailDAO dao = null;
                int database = 0;

                try
                {
                        dao = new ColSignDetailDAOImpl();

                        /* database = DBUtil.getWhichDatabase();
                        if (database  == DBUtil.ORACLE)
                        {
                            dao = new ColSignDetailDAOImpl();
                            LogUtil.debug("course", "[ColSignDetailDAOFactory]----Using DB Product:oracle");
                        } else if (database == DBUtil.SQLServer2000) //"Microsoft SQL Server"))
                        {
                            dao = new ColSignDetailDAOImpl();
                            LogUtil.debug("course", "[ColSignDAOFactory]----Using DB Product:Microsoft SQL Server");
                        }else if (database == DBUtil.MySQL)
                        {
                                    //todo: implemented later.
                        }*/
                }
                catch (Exception se)
                {
                        throw new ColSignDetailDAOSysException(
                                "PlanDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
