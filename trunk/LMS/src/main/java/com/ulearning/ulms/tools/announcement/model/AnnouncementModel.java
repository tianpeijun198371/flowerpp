/** * AnnouncementModel.java.
 * User: xiejh  Date: 2004-8-25 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.announcement.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class AnnouncementModel implements Serializable
{
        /**
         * identifier field
         */
        private int announcementID;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * persistent field
         */
        private int userID;

        /**
         * persistent field
         */
        private String username;

        /**
         * persistent field
         */
        private String subject;

        /**
         * nullable persistent field
         */
        private String message;

        /**
         * nullable persistent field
         */
        private Date createdate;

        /**
         * nullable persistent field
         */
        private Date modifydate;

        /**
         * persistent field
         */
        private String ispermanent;

        /**
         * nullable persistent field
         */
        private Date displaybegindate;

        /**
         * nullable persistent field
         */
        private Date displayenddate;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * persistent field
         */
        private String texttype;

        /**
         * nullable persistent field
         */
        private String link;

        /**
         * full constructor
         */
        public AnnouncementModel(int relationID, int userID, String username,
                                 String subject, String message, Date createdate, Date modifydate,
                                 String ispermanent, Date displaybegindate, Date displayenddate,
                                 String type, String texttype, String link)
        {
                this.relationID = relationID;
                this.userID = userID;
                this.username = username;
                this.subject = subject;
                this.message = message;
                this.createdate = createdate;
                this.modifydate = modifydate;
                this.ispermanent = ispermanent;
                this.displaybegindate = displaybegindate;
                this.displayenddate = displayenddate;
                this.type = type;
                this.texttype = texttype;
                this.link = link;
        }

        /**
         * default constructor
         */
        public AnnouncementModel()
        {
        }

        /**
         * minimal constructor
         */
        public AnnouncementModel(int relationID, int userID, String username,
                                 String subject, String ispermanent, String texttype)
        {
                this.relationID = relationID;
                this.userID = userID;
                this.username = username;
                this.subject = subject;
                this.ispermanent = ispermanent;
                this.texttype = texttype;
        }

        public int getAnnouncementID()
        {
                return this.announcementID;
        }

        public void setAnnouncementID(int announcementID)
        {
                this.announcementID = announcementID;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getUserID()
        {
                return this.userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getUsername()
        {
                return this.username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public String getSubject()
        {
                return this.subject;
        }

        public void setSubject(String subject)
        {
                this.subject = subject;
        }

        public String getMessage()
        {
                return this.message;
        }

        public void setMessage(String message)
        {
                this.message = message;
        }

        public Date getCreatedate()
        {
                return this.createdate;
        }

        public void setCreatedate(Date createdate)
        {
                this.createdate = createdate;
        }

        public Date getModifydate()
        {
                return this.modifydate;
        }

        public void setModifydate(Date modifydate)
        {
                this.modifydate = modifydate;
        }

        public String getIspermanent()
        {
                return this.ispermanent;
        }

        public void setIspermanent(String ispermanent)
        {
                this.ispermanent = ispermanent;
        }

        public Date getDisplaybegindate()
        {
                return this.displaybegindate;
        }

        public void setDisplaybegindate(Date displaybegindate)
        {
                this.displaybegindate = displaybegindate;
        }

        public Date getDisplayenddate()
        {
                return this.displayenddate;
        }

        public void setDisplayenddate(Date displayenddate)
        {
                this.displayenddate = displayenddate;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getTexttype()
        {
                return this.texttype;
        }

        public void setTexttype(String texttype)
        {
                this.texttype = texttype;
        }

        public String getLink()
        {
                return this.link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("announcementID",
                        getAnnouncementID()).toString();
        }
}
