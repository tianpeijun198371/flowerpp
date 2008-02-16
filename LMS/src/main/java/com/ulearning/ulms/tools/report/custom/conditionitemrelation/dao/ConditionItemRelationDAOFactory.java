package com.ulearning.ulms.tools.report.custom.conditionitemrelation.dao;

import com.ulearning.ulms.tools.report.custom.conditionitemrelation.exceptions.ConditionItemRelationDAOSysException;
import com.ulearning.ulms.util.DBUtil;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-21
 * Time: 11:14:35
 * To change this template use File | Settings | File Templates.
 */
public class ConditionItemRelationDAOFactory
{
        public static ConditionItemRelationDAO getDAO() throws ConditionItemRelationDAOSysException
        {
                ConditionItemRelationDAO dao = null;
                try
                {
                        dao = new ConditionItemRelationDAOOracle();
                        /*
                        if (DBUtil.getWhichDatabase() == 0) {
                            dao = new ConditionItemRelationDAOOracle();
                        } else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                        {
                            //dao = new DocumentDAOSQLServer();
                        }
                        */
                }
                catch (Exception se)
                {
                        throw new ConditionItemRelationDAOSysException("ConditionItemRelationDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;
        }
}
