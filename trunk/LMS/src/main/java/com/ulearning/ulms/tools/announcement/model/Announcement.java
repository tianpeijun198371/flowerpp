/**
 * AnnouncementList.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.announcement.model;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class Announcement extends UploadForm
{
        private int announcementID;
        private int relationID;
        private int type;
        private int textType;
        private String isPermanent;
        private String relationName;
        private int userID;
        private String userName;
        private String subject;
        private String message;
        private Date createDate;
        private Date modifyDate;
        private Date displayBeginDate;
        private Date displayEndDate;
        private String beginDate;
        private String endDate;
        private String link;
        private AnnouncementModel announcementModel;

        public Announcement()
        {
        }

        public AnnouncementModel getAnnouncementModel()
        {
                announcementModel = new AnnouncementModel();
                announcementModel.setAnnouncementID(this.announcementID);
                announcementModel.setRelationID(this.relationID);
                announcementModel.setUserID(this.userID);
                announcementModel.setUsername(this.userName);
                announcementModel.setSubject(this.subject);
                announcementModel.setMessage(this.message);
                announcementModel.setCreatedate(this.createDate);
                announcementModel.setModifydate(this.modifyDate);
                announcementModel.setIspermanent(this.isPermanent);
                announcementModel.setDisplaybegindate(this.displayBeginDate);
                announcementModel.setDisplayenddate(this.displayEndDate);
                announcementModel.setType(String.valueOf(this.type));
                announcementModel.setTexttype(String.valueOf(this.textType));
                announcementModel.setLink(this.link);

                return announcementModel;
        }

        public Announcement getAnnouncement(AnnouncementModel announcementModel)
        {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementID(announcementModel.getAnnouncementID());
                announcement.setRelationID(announcementModel.getRelationID());
                announcement.setUserID(announcementModel.getUserID());
                announcement.setUserName(announcementModel.getUsername());
                announcement.setSubject(StringUtil.nullToStr(
                        announcementModel.getSubject()));
                announcement.setMessage(StringUtil.nullToStr(
                        announcementModel.getMessage()));
                announcement.setCreateDate(announcementModel.getCreatedate());
                announcement.setModifyDate(announcementModel.getModifydate());
                announcement.setIsPermanent(announcementModel.getIspermanent());
                announcement.setDisplayBeginDate(announcementModel.getDisplaybegindate());
                announcement.setDisplayEndDate(announcementModel.getDisplayenddate());
                announcement.setType(Integer.parseInt(announcementModel.getType()));
                announcement.setTextType(Integer.parseInt(
                        announcementModel.getTexttype()));
                announcement.setLink(StringUtil.nullToStr(announcementModel.getLink()));

                return announcement;
        }

        public String isPermanent()
        {
                return isPermanent;
        }

        public void setIsPermanent(String permanent)
        {
                isPermanent = permanent;
        }

        public String getBeginDate()
        {
                return beginDate;
        }

        public void setBeginDate(String beginDate)
        {
                this.beginDate = beginDate;
        }

        public String getEndDate()
        {
                return endDate;
        }

        public void setEndDate(String endDate)
        {
                this.endDate = endDate;
        }

        public String getMessage()
        {
                return message;
        }

        public void setMessage(String message)
        {
                this.message = message;
        }

        public int getAnnouncementID()
        {
                return announcementID;
        }

        public void setAnnouncementID(int announcementID)
        {
                this.announcementID = announcementID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getRelationName()
        {
                return relationName;
        }

        public void setRelationName(String relationName)
        {
                this.relationName = relationName;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public String getSubject()
        {
                return subject;
        }

        public void setSubject(String subject)
        {
                this.subject = subject;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public Date getDisplayBeginDate()
        {
                return displayBeginDate;
        }

        public void setDisplayBeginDate(Date displayBeginDate)
        {
                this.displayBeginDate = displayBeginDate;
        }

        public Date getDisplayEndDate()
        {
                return displayEndDate;
        }

        public void setDisplayEndDate(Date displayEndDate)
        {
                this.displayEndDate = displayEndDate;
        }

        public String getLink()
        {
                return link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getTextType()
        {
                return textType;
        }

        public void setTextType(int textType)
        {
                this.textType = textType;
        }

        public String getIsPermanent()
        {
                return isPermanent;
        }

        /**
         * Validate the properties that have been set from this HTTP request,
         * and return an <code>ActionErrors</code> object that encapsulates any
         * validation errors that have been found.  If no errors are found, return
         * <code>null</code> or an <code>ActionErrors</code> object with no
         * recorded error messages.
         *
         * @param mapping The mapping used to select this instance
         * @param request The servlet request we are processing
         */
        public ActionErrors validate(ActionMapping mapping,
                                     HttpServletRequest request)
        {
                ActionErrors errors = new ActionErrors();

                if ((subject == null) || (subject.length() < 1))
                {
                        errors.add("subject",
                                new ActionError("error.announcement.subject.required"));
                }

                return errors;
        }
}
