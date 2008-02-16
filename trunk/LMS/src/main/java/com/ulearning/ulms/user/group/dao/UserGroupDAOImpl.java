/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.dao;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.user.group.exceptions.UserGroupDAOSysException;
import com.ulearning.ulms.user.group.form.UserGroupForm;
import com.ulearning.ulms.user.group.model.UserGroupModel;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.HibernateDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class UserGroupDAOImpl implements UserGroupDAO
{
        public Serializable insertUserGroup(UserGroupForm details) throws UserGroupDAOSysException
        {
                Serializable s = null;
                try
                {
                        s = HibernateDAO.add(details.getUserGroupModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserGroupDAOSysException("" + e);
                }
                return s;
        }

        public void updateUserGroup(UserGroupForm details) throws UserGroupDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getUserGroupModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserGroupDAOSysException("" + e);
                }
        }

        /**
         * Remove the UserGroup from database by the USERGROUPID
         *
         * @param pkID
         * @throws UserGroupDAOSysException
         */
        public void deleteUserGroup(int pkID) throws UserGroupDAOSysException
        {
                String hql = " from UserGroupModel where USERGROUPID = " + pkID;
                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserGroupDAOSysException("" + e);
                }
        }

        public void deleteGroupuser(int group, int user) throws UserGroupDAOSysException
        {
                String hql = " from UserGroupModel where groupid = " + group + " and userid=" + user;
                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserGroupDAOSysException("" + e);
                }
        }

        /**
         * Get the UserGroup info via the unique USERGROUPID
         *
         * @param pkID
         * @return the prepared UserGroupForm, default is null
         * @throws UserGroupDAOSysException
         */
        public UserGroupForm getUserGroup(int pkID) throws UserGroupDAOSysException
        {
                UserGroupForm bf = new UserGroupForm();
                UserGroupForm res = null;
                List tmList = null;
                String hql = " from UserGroupModel where USERGROUPID = " + pkID;
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserGroupDAOSysException("" + e);
                }
                if ((tmList != null) && (tmList.size() > 0))
                {
                        UserGroupModel bm = (UserGroupModel) tmList.get(0);
                        res = bf.getUserGroupForm(bm);
                }
                return res;
        }

        /**
         * Get the UserGroup list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws UserGroupDAOSysException
         */
        public List getUserGroupList(int groupID) throws UserGroupDAOSysException
        {
                UserGroupForm bf = new UserGroupForm();
                UserGroupModel bm = null;
                ArrayList UserGroupList = new ArrayList();
                List tmList = null;
                String hql = " from UserGroupModel where  GroupId =" + groupID;
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UserGroupDAOSysException("" + e);
                }
                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (UserGroupModel) tmList.get(i);
                        UserGroupList.add(bf.getUserGroupForm(bm));
                }
                return UserGroupList;
        }

}
