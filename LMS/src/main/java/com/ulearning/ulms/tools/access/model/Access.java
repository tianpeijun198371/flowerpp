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


public class Access extends ActionForm
{
        private int accessID = 0;
        private int userID = 0;
        private Date createDateTime = null;
        private String strCreateDateTime = "";
        private int moduleID = 0;
        private int courseID = 0;
        private int certificateID = 0;
        private int projectID = 0;
        private int orgID = 0;
        private String ip = "";
        private double userTime = 0;
        private AccessModel accessModel;

        public AccessModel getAccessModel()
        {
                accessModel = new AccessModel();
                accessModel.setAccessID(this.accessID);
                accessModel.setUserID(this.userID);
                accessModel.setCreateDateTime(this.createDateTime);
                accessModel.setModuleID(this.moduleID);
                accessModel.setCourseID(this.courseID);
                accessModel.setCertificateID(this.certificateID);
                accessModel.setProjectID(this.projectID);
                accessModel.setOrgID(this.orgID);
                accessModel.setIp(this.ip);
                accessModel.setUserTime(this.userTime);

                return accessModel;
        }

        public Access getAccessForm(AccessModel accessModel)
        {
                Access access = new Access();
                access.setAccessID(accessModel.getAccessID());
                access.setUserID(accessModel.getUserID());
                access.setCreateDateTime(accessModel.getCreateDateTime());
                access.setModuleID(accessModel.getModuleID());
                access.setCourseID(accessModel.getCourseID());
                access.setCertificateID(accessModel.getCertificateID());
                access.setProjectID(accessModel.getProjectID());
                access.setOrgID(accessModel.getOrgID());
                access.setIp(accessModel.getIp());
                access.setUserTime(accessModel.getUserTime());

                return access;
        }

        public int getAccessID()
        {
                return accessID;
        }

        public void setAccessID(int accessID)
        {
                this.accessID = accessID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getCreateDateTime()
        {
                return createDateTime;
        }

        public void setCreateDateTime(Date createDateTime)
        {
                this.createDateTime = createDateTime;
        }

        public String getStrCreateDateTime()
        {
                return strCreateDateTime;
        }

        public void setStrCreateDateTime(String strCreateDateTime)
        {
                this.strCreateDateTime = strCreateDateTime;
        }

        public int getModuleID()
        {
                return moduleID;
        }

        public void setModuleID(int moduleID)
        {
                this.moduleID = moduleID;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getCertificateID()
        {
                return certificateID;
        }

        public void setCertificateID(int certificateID)
        {
                this.certificateID = certificateID;
        }

        public int getProjectID()
        {
                return projectID;
        }

        public void setProjectID(int projectID)
        {
                this.projectID = projectID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getIp()
        {
                return ip;
        }

        public void setIp(String ip)
        {
                this.ip = ip;
        }

        public double getUserTime()
        {
                return userTime;
        }

        public void setUserTime(double userTime)
        {
                this.userTime = userTime;
        }
}
