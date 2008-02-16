/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.dao;

import com.ulearning.ulms.user.group.exceptions.GroupDAOSysException;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051124
 * Time: 155359
 */

public class GroupDAOFactory
{
        public static GroupDAO getDAO() throws GroupDAOSysException
        {
                GroupDAO dao = null;
                try
                {
                        dao = new GroupDAOImpl();
                }
                catch (Exception se)
                {
                        throw new GroupDAOSysException("GroupDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;

        }

}
