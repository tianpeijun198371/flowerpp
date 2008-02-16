/*
 * Created on 2004-8-22
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.domain;

import com.ulearning.ulms.bloger.exception.ValidateException;

import java.util.Date;


/**
 * TODO Description here...
 *
 * @author Huaxia
 */
public class Feedback implements Validator
{
        private int articleId; // foreign key
        private int feedbackId; // primary key
        private String username = "Anonymous";
        private String url = "";
        private Date createdDate;
        private String content;

        public int getArticleId()
        {
                return articleId;
        }

        public void setArticleId(int articleId)
        {
                this.articleId = articleId;
        }

        public String getContent()
        {
                return content;
        }

        public void setContent(String content)
        {
                this.content = content;
        }

        public int getFeedbackId()
        {
                return feedbackId;
        }

        public void setFeedbackId(int feedbackId)
        {
                this.feedbackId = feedbackId;
        }

        public String getUrl()
        {
                return url;
        }

        public void setUrl(String url)
        {
                this.url = url;
        }

        public String getUsername()
        {
                return username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public Date getCreatedDate()
        {
                return createdDate;
        }

        public void setCreatedDate(Date createdDate)
        {
                this.createdDate = createdDate;
        }

        public void validate() throws ValidateException
        {
                if ((username == null) || username.equals(""))
                {
                        throw new ValidateException(INVALID_FEEDBACK_USERNAME);
                }

                if (url == null)
                {
                        url = "";
                }

                if (!url.equals(""))
                {
                        if (!url.matches("http://[^<>]*"))
                        {
                                throw new ValidateException(INVALID_FEEDBACK_URL);
                        }
                }

                if ((content == null) || content.equals(""))
                {
                        throw new ValidateException(INVALID_FEEDBACK_CONTENT);
                }
        }
}
