/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Dec 23, 2004
 * Time: 1:17:52 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.user.model;

import com.ulearning.ulms.core.util.Timer;
import com.ulearning.ulms.user.form.UserForm;

public class OnlineUser
{
        private int userID;
        private String loginName;
        private String userName;
        private String settleTime;
        private String startTime;
        private String endTime;
        //Record the start and end time for online user
        private Timer timer;
        private UserForm userForm;

        private int orgID;

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getLoginName()
        {
                return loginName;
        }

        public void setLoginName(String loginName)
        {
                this.loginName = loginName;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public String getSettleTime()
        {
                return settleTime;
        }

        public void setSettleTime(String settleTime)
        {
                this.settleTime = settleTime;
        }

        public String getStartTime()
        {
                return startTime;
        }

        public void setStartTime(String startTime)
        {
                this.startTime = startTime;
        }

        public String getEndTime()
        {
                return endTime;
        }

        public void setEndTime(String endTime)
        {
                this.endTime = endTime;
        }

        public Timer getTimer()
        {
                return timer;
        }

        public void setTimer(Timer timer)
        {
                this.timer = timer;
        }

        public UserForm getUserForm()
        {
                return userForm;
        }

        public void setUserForm(UserForm userForm)
        {
                this.userForm = userForm;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getOrgID()
        {
                return orgID;
        }
}
