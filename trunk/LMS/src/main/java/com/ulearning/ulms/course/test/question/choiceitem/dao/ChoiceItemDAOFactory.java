/**
 * Created by IntelliJ IDEA.
 * ChoiceItem: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choiceitem.dao;

import com.ulearning.ulms.course.test.question.choiceitem.exceptions.ChoiceItemDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class ChoiceItemDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static ChoiceItemDAO getDAO() throws ChoiceItemDAOSysException
        {
                ChoiceItemDAO dao = null;

                try
                {
                        dao = new ChoiceItemDAOImpl();

                        /*
                          if (DBUtil.getWhichDatabase() == 0)
                          {
                                  dao = new ChoiceItemDAOOracle();
                          }
                          else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                                  //dao = new ChoiceItemDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new ChoiceItemDAOSysException(
                                "ChoiceItemDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
