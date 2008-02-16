/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:08:29
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.dao;

import com.ulearning.ulms.admin.colsignup.colstudent.exceptions.ColStudentDAOSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class ColStudentDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static ColStudentDAO getDAO() throws ColStudentDAOSysException
        {
                ColStudentDAO dao = null;
                int database = 0;

                try
                {
                        dao = new ColStudentDAOImpl();

                        /*database = DBUtil.getWhichDatabase();
                        if (database  == DBUtil.ORACLE)
                        {
                            dao = new ColStudentDAOImpl();
                            LogUtil.debug("course", "[ColSignDetailDAOFactory]----Using DB Product:oracle");
                        } else if (database == DBUtil.SQLServer2000) //"Microsoft SQL Server"))
                        {
                            dao = new ColStudentDAOImpl();
                            LogUtil.debug("course", "[ColSignDAOFactory]----Using DB Product:Microsoft SQL Server");
                        }else if (database == DBUtil.MySQL)
                        {
                                    //todo: implemented later.
                        }*/
                }
                catch (Exception se)
                {
                        throw new ColStudentDAOSysException(
                                "PlanDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
