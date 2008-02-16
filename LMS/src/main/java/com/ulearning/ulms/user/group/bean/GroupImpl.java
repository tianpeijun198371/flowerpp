/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.bean;

import com.ulearning.ulms.user.group.exceptions.GroupDAOSysException;
import com.ulearning.ulms.user.group.form.GroupForm;
import com.ulearning.ulms.user.group.dao.GroupDAO;
import com.ulearning.ulms.user.group.dao.GroupDAOFactory;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051124
 * Time: 155359
 */

public class GroupImpl
{
        public List getGroupList() throws GroupDAOSysException
        {
                List GroupList = null;
                try
                {
                        GroupDAO dao = GroupDAOFactory.getDAO();
                        GroupList = dao.getGroupList();
                }
                catch (GroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
                return GroupList;

        }

        public GroupForm getGroup(int groupid) throws GroupDAOSysException
        {
                GroupForm tf = null;
                try
                {
                        GroupDAO dao = GroupDAOFactory.getDAO();
                        tf = dao.getGroup(groupid);
                }
                catch (GroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
                return tf;

        }

        public void deleteGroup(int groupid) throws GroupDAOSysException
        {
                try
                {
                        GroupDAO dao = GroupDAOFactory.getDAO();
                        dao.deleteGroup(groupid);
                }
                catch (GroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }

}
