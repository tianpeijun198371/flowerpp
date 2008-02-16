/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.dao;

import com.ulearning.ulms.user.group.exceptions.UserGroupDAOSysException;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051130
 * Time: 142230
 */

public class UserGroupDAOFactory
{
        public static UserGroupDAO getDAO() throws UserGroupDAOSysException
        {
                UserGroupDAO dao = null;
                try
                {
                        dao = new UserGroupDAOImpl();
                }
                catch (Exception se)
                {
                        throw new UserGroupDAOSysException("UserGroupDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;

        }

}
