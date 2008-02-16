/**
 * AccessList.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.model;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class UserAccess extends ActionForm
{
        private int userID = 0;
        private int con = 0;
        private Date endTime = null;
        private Date startTime = null;
        private int usertimes = 0;

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getCon()
        {
                return con;
        }

        public void setCon(int con)
        {
                this.con = con;
        }

        public Date getEndTime()
        {
                return endTime;
        }

        public void setEndTime(Date endTime)
        {
                this.endTime = endTime;
        }

        public Date getStartTime()
        {
                return startTime;
        }

        public void setStartTime(Date startTime)
        {
                this.startTime = startTime;
        }

        public int getUsertimes()
        {
                return usertimes;
        }

        public void setUsertimes(int usertimes)
        {
                this.usertimes = usertimes;
        }
}
