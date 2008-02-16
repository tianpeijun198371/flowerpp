/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.dao;

import com.ulearning.ulms.user.group.form.UserGroupForm;
import com.ulearning.ulms.user.group.exceptions.UserGroupDAOSysException;

import java.util.List;
import java.io.Serializable;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051130
 * Time: 142230
 */

public interface UserGroupDAO
{
        public Serializable insertUserGroup(UserGroupForm tf) throws UserGroupDAOSysException;

        public void updateUserGroup(UserGroupForm tf) throws UserGroupDAOSysException;

        public void deleteUserGroup(int usergroupid) throws UserGroupDAOSysException;

        public void deleteGroupuser(int group, int user) throws UserGroupDAOSysException;

        public List getUserGroupList(int groupID) throws UserGroupDAOSysException;

        public UserGroupForm getUserGroup(int usergroupid) throws UserGroupDAOSysException;
}
