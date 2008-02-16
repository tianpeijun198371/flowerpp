/**
 * Created by IntelliJ IDEA.
 * Base: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.base.dao;

import com.ulearning.ulms.course.test.question.base.exceptions.BaseDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class BaseDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static BaseDAO getDAO() throws BaseDAOSysException
        {
                BaseDAO dao = null;

                try
                {
                        dao = new BaseDAOImpl();

                        /*
                          if (DBUtil.getWhichDatabase() == 0)
                          {
                                  dao = new BaseDAOOracle();
                          }
                          else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                                  //dao = new BaseDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new BaseDAOSysException(
                                "BaseDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
