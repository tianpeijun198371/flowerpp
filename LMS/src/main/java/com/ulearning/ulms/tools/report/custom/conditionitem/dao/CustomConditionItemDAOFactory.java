package com.ulearning.ulms.tools.report.custom.conditionitem.dao;

import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.tools.report.custom.conditionitem.exceptions.CustomConditionItemDAOSysException;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-19
 * Time: 14:44:48
 * To change this template use File | Settings | File Templates.
 */
public class CustomConditionItemDAOFactory
{

        public static CustomConditionItemDAO getDAO() throws CustomConditionItemDAOSysException
        {
                CustomConditionItemDAO dao = null;
                try
                {
                        dao = new CustomConditionItemDAOOracle();
                        /*
                        if (DBUtil.getWhichDatabase() == 0)
                        {
                                dao = new CustomConditionItemDAOOracle();
                        }
                        else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                        {

                        }
                        */
                }
                catch (Exception se)
                {
                        throw new CustomConditionItemDAOSysException("CustomConditionItemDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;
        }
}
