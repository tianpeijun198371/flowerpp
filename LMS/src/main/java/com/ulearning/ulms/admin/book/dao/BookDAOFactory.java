/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.book.dao;

import com.ulearning.ulms.admin.book.exceptions.BookDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class BookDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static BookDAO getDAO() throws BookDAOSysException
        {
                BookDAO dao = null;

                try
                {
                        dao = new BookDAOImpl();

                        /*
                          if (DBUtil.getWhichDatabase() == 0)
                          {
                                  //dao = new BookDAOOracle();
                          }
                          else if (DBUtil.getWhichDatabase() == 1) //"Microsoft SQL Server"))
                          {
                                  //dao = new BookDAOSQLServer();
                          }
                        */
                }
                catch (Exception se)
                {
                        throw new BookDAOSysException(
                                "BookDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
