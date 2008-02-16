/**
 * SessionContainer.java.
 * User: dengj  Date: 2004-11-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.context;

import com.ulearning.ulms.admin.sysconfig.bean.SysConfigHelper;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.core.util.Timer;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.model.OnlineUser;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


public class SessionContainer implements HttpSessionBindingListener
{
        private static Vector users = new Vector();

        /**
         * Get the online user number
         *
         * @return sum of user number ,default is 0
         */
        public static int getCount()
        {
                users.trimToSize();

                return users.capacity();
        }

        public static int getCount(int orgID)
        {
                //users.trimToSize();
                //return users.capacity();
                int j = 0;

                for (int i = 0; i < users.size(); i++)
                {
                        OnlineUser uf = (OnlineUser) users.get(i);

                        if (uf.getOrgID() == orgID)
                        {
                                j++;
                        }
                }

                return j;
        }

        /**
         * Get online users sum by aspID and type
         *
         * @param aspID
         * @param type
         * @return sum of user number ,default is 0
         */
        public static int getCount(int aspID, int type)
        {
                users.trimToSize();

                return users.capacity();
        }

        /**
         * Judge whether the user is existed on system
         *
         * @param loginName
         * @return
         */
        public static boolean existUser(String loginName)
        {
                LogUtil.debug("system",
                        "[SessionContainer]existUser------loginName=" + loginName);
                LogUtil.debug("system",
                        "[SessionContainer]existUser------users=" + users);
                users.trimToSize();

                boolean existUser = false;

                //todo add the configration value
                for (int i = 0; i < users.capacity(); i++)
                {
                        LogUtil.debug("system",
                                "[SessionContainer]existUser------users.get(i)).getLoginName()=" +
                                        (((OnlineUser) users.get(i)).getLoginName()));

                        if ((loginName != null) &&
                                (loginName.trim()
                                        .equalsIgnoreCase(((OnlineUser) users.get(i)).getLoginName())))
                        {
                                existUser = true;

                                break;
                        }
                }

                return existUser;
        }

        /**
         * Delete user from list according to the unique loginName
         *
         * @param loginName
         * @return
         */
        public static boolean deleteUser(String loginName)
        {
                users.trimToSize();

                if (existUser(loginName))
                {
                        int currUserIndex = -1;

                        for (int i = 0; i < users.capacity(); i++)
                        {
                                if (loginName.equals(((OnlineUser) users.get(i)).getLoginName()))
                                {
                                        currUserIndex = i;

                                        break;
                                }
                        }

                        if (currUserIndex != -1)
                        {
                                users.remove(currUserIndex);
                                users.trimToSize();

                                return true;
                        }
                }

                return false;
        }

        /**
         * Get all the online user list
         *
         * @return prepared Vector,defaut is an empty vector
         */
        public static Vector getOnLineUser()
        {
                return users;
        }

        /**
         * Get online users by aspID and type
         *
         * @param aspID aspID of user
         * @param type  the user type ,such as student, teacher
         * @return prepared Vector,defaut is an empty vector
         */
        public static Vector getOnLineUser(int aspID, int type)
        {
                return users;
        }

        /**
         * When a new session created by system, add this user to userList
         *
         * @param e
         */
        public void valueBound(HttpSessionBindingEvent e)
        {
                LogUtil.debug("system",
                        "[SessionContainer]vlaueBound------users=" + users);
                users.trimToSize();
                LogUtil.debug("system", "[SessionContainer]vlaueBound------e=" + e);

                if ((e.getName() != null) && (!existUser(e.getName())))
                {
                        OnlineUser uonline = new OnlineUser();

                        uonline.setLoginName(e.getName().trim());
                        uonline.setTimer(new Timer());

                        String userID = (String) e.getSession()
                                .getAttribute(LMSConstants.USERID_KEY);
                        LogUtil.debug("system",
                                "[SessionContainer]vlaueBound------userID=" + userID);

                        UserForm uf = UserHelper.getUser(userID);

                        uonline.setUserID(Integer.parseInt(userID));
                        uonline.setUserName(uf.getName());
                        uonline.setUserForm(uf);
                        users.add(uonline);
                        LogUtil.debug("system", e.getName() + "\t 登入到系统\t" + (new Date()));
                        LogUtil.debug("system", " 在线用户数为：" + getCount());
                }
                else
                {
                        LogUtil.debug("system", e.getName() + "已经存在");
                }
        }

        /**
         * Delete the use from userList when user logout or the session is invalid
         *
         * @param e
         */
        public void valueUnbound(HttpSessionBindingEvent e)
        {
                users.trimToSize();

                String loginName = e.getName();
                deleteUser(loginName);
                LogUtil.debug("system", loginName + "\t 退出系统\t" + (new Date()));
                LogUtil.debug("system", " 在线用户数为：" + getCount());
        }
}
