/**
 * MessageModel.java.
 * User: keyh  Date: 2004-8-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.message.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;


/**
 * @author Hibernate CodeGenerator
 */
public class MessageModel implements Serializable
{
        /**
         * identifier field
         */
        private int messageid;
        private int senduserid;
        private String reciever;

        /**
         * persistent field
         */
        private String title;

        /**
         * persistent field
         */
        private String content;

        /**
         * nullable persistent field
         */
        private int ispopupmessage;

        /**
         * nullable persistent field
         */
        private int issave;

        /**
         * nullable persistent field
         */
        private int type;

        /**
         * nullable persistent field
         */
        private Date sendtime;
        private String description;

        public MessageModel()
        {
        }

        public MessageModel(int messageid, int senduserid, String reciever,
                            String title, String content, int ispopupmessage, int issave, int type,
                            Date sendtime, String description)
        {
                this.messageid = messageid;
                this.senduserid = senduserid;
                this.reciever = reciever;
                this.title = title;
                this.content = content;
                this.ispopupmessage = ispopupmessage;
                this.issave = issave;
                this.type = type;
                this.sendtime = sendtime;
                this.description = description;
        }

        public int getMessageid()
        {
                return messageid;
        }

        public void setMessageid(int messageid)
        {
                this.messageid = messageid;
        }

        public int getSenduserid()
        {
                return senduserid;
        }

        public void setSenduserid(int senduserid)
        {
                this.senduserid = senduserid;
        }

        public String getReciever()
        {
                return reciever;
        }

        public void setReciever(String reciever)
        {
                this.reciever = reciever;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getContent()
        {
                return content;
        }

        public void setContent(String content)
        {
                this.content = content;
        }

        public int getIspopupmessage()
        {
                return ispopupmessage;
        }

        public void setIspopupmessage(int ispopupmessage)
        {
                this.ispopupmessage = ispopupmessage;
        }

        public int getIssave()
        {
                return issave;
        }

        public void setIssave(int issave)
        {
                this.issave = issave;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public Date getSendtime()
        {
                return sendtime;
        }

        public void setSendtime(Date sendtime)
        {
                this.sendtime = sendtime;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }
}
