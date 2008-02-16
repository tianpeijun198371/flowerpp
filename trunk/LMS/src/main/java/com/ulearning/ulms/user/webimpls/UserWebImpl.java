/**
 * UserWebImpl.java.
 * User: dengj  Date: 2004-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.user.webimpls;

import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.form.UserForm;

import java.util.List;

public class UserWebImpl
{
        /**
         * Wrapping the get user method for JSP and  the other modules
         *
         * @param userID
         * @return the user modle according to the userID
         */
        public UserForm getUser(String userID)
        {
                UserForm uf = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        uf = userDao.getUser(userID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return uf;
        }

        /**
         * Wrapping the get userList method for JSP and  the other modules
         *
         * @param catlogID
         * @return the user list according to the catalog
         */
        public List getUserList(int catlogID)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getUserList(catlogID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        /**
         * Get the email according to the userID
         *
         * @param userID
         * @return the prepared email,null if the userID is not existed
         */
        public String getUserEmail(String userID)
        {
                String email = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        email = userDao.getUserEmail(userID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return email;
        }

        /**
         * Get whether the user is exist
         *
         * @param userID
         * @return true if the user is exist,otherwise flase
         */
        public boolean isExistUser(String userID)
        {
                boolean isExist = false;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        isExist = userDao.isExistUser(userID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return isExist;
        }
}
