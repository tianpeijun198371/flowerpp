/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.topTen.dao;

import com.ulearning.ulms.admin.topTen.exceptions.TopTenDAOSysException;


public class TopTenDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static TopTenDAO getDAO() throws TopTenDAOSysException
        {
                TopTenDAO dao = null;

                try
                {
                        dao = new TopTenDAOImpl();
                }
                catch (Exception e)
                {
                        throw new TopTenDAOSysException(
                                "TopTenDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        e.getMessage());
                }

                return dao;
        }
}
