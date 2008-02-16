/**
 * FeedbackForm.java.
 * User: keyh  Date: 2005-5-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.blog.form;

import java.io.Serializable;

import java.util.Date;


public class FeedbackForm implements Serializable
{
        private int feedbackId;
        private int articleId;
        private String username;
        private String url;
        private Date createdDate;
        private String content;

        public FeedbackForm()
        {
        }

        public FeedbackForm(int feedbackId, int articleId, String username,
                            String url, Date createdDate, String content)
        {
                this.feedbackId = feedbackId;
                this.articleId = articleId;
                this.username = username;
                this.url = url;
                this.createdDate = createdDate;
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

        public int getArticleId()
        {
                return articleId;
        }

        public void setArticleId(int articleId)
        {
                this.articleId = articleId;
        }

        public String getUsername()
        {
                return username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public String getUrl()
        {
                return url;
        }

        public void setUrl(String url)
        {
                this.url = url;
        }

        public Date getCreatedDate()
        {
                return createdDate;
        }

        public void setCreatedDate(Date createdDate)
        {
                this.createdDate = createdDate;
        }

        public String getContent()
        {
                return content;
        }

        public void setContent(String content)
        {
                this.content = content;
        }
}
