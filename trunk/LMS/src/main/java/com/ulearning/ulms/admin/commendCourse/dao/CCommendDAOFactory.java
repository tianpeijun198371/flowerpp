/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.commendCourse.dao;

import com.ulearning.ulms.admin.commendCourse.exceptions.CCommendDAOSysException;


public class CCommendDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static CCommendDAO getDAO() throws CCommendDAOSysException
        {
                CCommendDAO dao = null;

                try
                {
                        dao = new CCommendDAOImpl();
                }
                catch (Exception e)
                {
                        throw new CCommendDAOSysException(
                                "TopTenDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        e.getMessage());
                }

                return dao;
        }
}
