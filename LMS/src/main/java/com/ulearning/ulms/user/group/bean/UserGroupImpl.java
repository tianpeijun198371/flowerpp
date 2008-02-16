/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.bean;

import com.ulearning.ulms.user.group.exceptions.UserGroupDAOSysException;
import com.ulearning.ulms.user.group.form.UserGroupForm;
import com.ulearning.ulms.user.group.dao.UserGroupDAO;
import com.ulearning.ulms.user.group.dao.UserGroupDAOFactory;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051130
 * Time: 142230
 */

public class UserGroupImpl
{
        public List getUserGroupList(int groupID) throws UserGroupDAOSysException
        {
                List UserGroupList = null;
                try
                {
                        UserGroupDAO dao = UserGroupDAOFactory.getDAO();
                        UserGroupList = dao.getUserGroupList(groupID);
                }
                catch (UserGroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
                return UserGroupList;

        }

        public UserGroupForm getUserGroup(int usergroupid) throws UserGroupDAOSysException
        {
                UserGroupForm tf = null;
                try
                {
                        UserGroupDAO dao = UserGroupDAOFactory.getDAO();
                        tf = dao.getUserGroup(usergroupid);
                }
                catch (UserGroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
                return tf;

        }

        public void deleteUserGroup(int usergroupid) throws UserGroupDAOSysException
        {
                try
                {
                        UserGroupDAO dao = UserGroupDAOFactory.getDAO();
                        dao.deleteUserGroup(usergroupid);
                }
                catch (UserGroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
