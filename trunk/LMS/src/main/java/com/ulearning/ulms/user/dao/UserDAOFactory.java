/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.user.dao;

import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.util.DBUtil;

public class UserDAOFactory
{

        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static UserDAO getDAO() throws UserDAOSysException
        {
                UserDAO dao = null;
                try
                {
                        dao = new UserDAOImpl();
                }
                catch (Exception se)
                {
                        throw new UserDAOSysException("UserDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;
        }


}
