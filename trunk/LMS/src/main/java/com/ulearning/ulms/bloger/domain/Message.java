/*
 * Created on 2004-10-14
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.domain;

import com.ulearning.ulms.bloger.exception.ValidateException;

import java.util.Date;


/**
 * Message object.
 *
 * @author Huaxia
 */
public final class Message implements Validator
{
        private int blogmessageId; // the primary key
        private int accountId; // to which user
        private String sender; // the sender's name
        private String subject; // message subject
        private String email; // sender's email address
        private String content; // message content
        private Date sentDate; // when to sent

        public int getAccountId()
        {
                return accountId;
        }

        public void setAccountId(int accountId)
        {
                this.accountId = accountId;
        }

        public String getContent()
        {
                return content;
        }

        public void setContent(String content)
        {
                this.content = content;
        }

        public String getEmail()
        {
                return email;
        }

        public void setEmail(String email)
        {
                this.email = email;
        }

        public int getBlogmessageId()
        {
                return blogmessageId;
        }

        public void setBlogmessageId(int blogmessageId)
        {
                this.blogmessageId = blogmessageId;
        }

        public String getSender()
        {
                return sender;
        }

        public void setSender(String sender)
        {
                this.sender = sender;
        }

        public Date getSentDate()
        {
                return sentDate;
        }

        public void setSentDate(Date sentDate)
        {
                this.sentDate = sentDate;
        }

        public String getSubject()
        {
                return subject;
        }

        public void setSubject(String subject)
        {
                this.subject = subject;
        }

        public void validate() throws ValidateException
        {
                if (accountId < 0)
                {
                        throw new ValidateException("User is not exist.");
                }

                if ((sender == null) || sender.equals(""))
                {
                        sender = "anonymous";
                }

                if ((subject == null) || subject.equals(""))
                {
                        throw new ValidateException("Subject cannot be empty.");
                }

                if ((content == null) || content.equals(""))
                {
                        throw new ValidateException("Content cannot be empty.");
                }
        }
}
