/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-7
 * Time: 13:50:04
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.dao;

import com.ulearning.ulms.admin.graduation.exceptions.GraduationAppException;
import com.ulearning.ulms.admin.graduation.exceptions.GraduationDAOSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class GraduationDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static GraduationDAO getDAO() throws GraduationAppException
        {
                GraduationDAO dao = null;
                int database = 0;

                try
                {
                        dao = new GraduationDAOImpl();
                }
                catch (Exception se)
                {
                        throw new GraduationAppException(
                                "PlanDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
