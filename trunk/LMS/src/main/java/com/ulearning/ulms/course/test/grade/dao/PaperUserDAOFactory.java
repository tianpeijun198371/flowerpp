/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-16
 * Time: 11:16:17
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.test.grade.dao;

import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class PaperUserDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static PaperUserDAO getDAO() throws GradeDAOSysException
        {
                PaperUserDAO dao = null;
                int database = 0;

                try
                {
                        database = DBUtil.getWhichDatabase();

                        if (database == DBUtil.ORACLE)
                        {
                                dao = new PaperUserDAOImpl();
                                LogUtil.debug("course",
                                        "[ColSignDAOFactory]----Using DB Product:oracle");
                        }
                        else if (database == DBUtil.SQLServer2000) //"Microsoft SQL Server"))
                        {
                                dao = new PaperUserDAOImpl();
                                LogUtil.debug("course",
                                        "[ColSignDAOFactory]----Using DB Product:Microsoft SQL Server");
                        }
                        else if (database == DBUtil.MySQL)
                        {
                                //todo: implemented later.
                        }
                        else if (database == DBUtil.DB2)
                        {
                                dao = new PaperUserDAOImpl();
                                LogUtil.debug("course",
                                        "[ColSignDAOFactory]----Using DB Product:DB2");
                        }
                        else
                        {
                                dao = new PaperUserDAOImpl();
                                LogUtil.debug("course",
                                        "[ColSignDAOFactory]----Using DB Product:ALL");
                        }
                }
                catch (Exception se)
                {
                        throw new GradeDAOSysException(
                                "PlanDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
