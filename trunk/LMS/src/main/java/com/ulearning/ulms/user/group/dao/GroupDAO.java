/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.dao;

import com.ulearning.ulms.user.group.form.GroupForm;
import com.ulearning.ulms.user.group.exceptions.GroupDAOSysException;

import java.util.List;
import java.io.Serializable;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051124
 * Time: 155359
 */

public interface GroupDAO
{
        public Serializable insertGroup(GroupForm tf) throws GroupDAOSysException;

        public int insertGroup2(GroupForm tf) throws GroupDAOSysException;

        public void updateGroup(GroupForm tf) throws GroupDAOSysException;

        public void deleteGroup(int groupid) throws GroupDAOSysException;

        public List getGroupList() throws GroupDAOSysException;

        public GroupForm getGroup(int groupid) throws GroupDAOSysException;
}
