/*
 * Created on 2004-8-22
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.domain;

import com.ulearning.ulms.bloger.exception.ValidateException;

import java.util.Date;


/**
 * Article.
 *
 * @author Huaxia
 */
public class Article implements Validator
{
        private int categoryId; // foreign key
        private int accountId; // foreign key
        private int type;
        private int articleId; // primary key
        private String title;
        private String summary = "";
        private String content = "";
        private String inclosure = "";
        private String inclosurePath = "";
        private boolean visible = true;
        private Date createdDate;
        private Date updatedDate;

        // feedbacks count:
        private int feedbacks;

        // visits count:
        private int webVisits;
        private int rssVisits;

        public int getAccountId()
        {
                return accountId;
        }

        public void setAccountId(int accountId)
        {
                this.accountId = accountId;
        }

        public int getArticleId()
        {
                return articleId;
        }

        public void setArticleId(int articleId)
        {
                this.articleId = articleId;
        }

        public int getCategoryId()
        {
                return categoryId;
        }

        public void setCategoryId(int categoryId)
        {
                this.categoryId = categoryId;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public boolean getVisible()
        {
                return visible;
        }

        public void setVisible(boolean visible)
        {
                this.visible = visible;
        }

        public String getContent()
        {
                return content;
        }

        public void setContent(String content)
        {
                this.content = content;
        }

        public String getInclosure()
        {
                return inclosure;
        }

        public void setInclosure(String inclosure)
        {
                this.inclosure = inclosure;
        }

        public String getInclosurePath()
        {
                return inclosurePath;
        }

        public void setInclosurePath(String inclosurePath)
        {
                this.inclosurePath = inclosurePath;
        }

        public Date getCreatedDate()
        {
                return createdDate;
        }

        public void setCreatedDate(Date createdDate)
        {
                this.createdDate = createdDate;
                this.updatedDate = createdDate;
        }

        public int getFeedbacks()
        {
                return feedbacks;
        }

        public void setFeedbacks(int feedbacks)
        {
                this.feedbacks = feedbacks;
        }

        public int getRssVisits()
        {
                return rssVisits;
        }

        public void setRssVisits(int rssVisits)
        {
                this.rssVisits = rssVisits;
        }

        public String getSummary()
        {
                return summary;
        }

        public void setSummary(String summary)
        {
                this.summary = summary;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public Date getUpdatedDate()
        {
                return updatedDate;
        }

        public void setUpdatedDate(Date updatedDate)
        {
                this.updatedDate = updatedDate;
        }

        public int getWebVisits()
        {
                return webVisits;
        }

        public void setWebVisits(int webVisits)
        {
                this.webVisits = webVisits;
        }

        public void validate() throws ValidateException
        {
                if ((title == null) || title.equals(""))
                {
                        throw new ValidateException(INVALID_ARTICLE_TITLE);
                }

                if ((content == null) && (content.length() < 1))
                {
                        content = "暂时没有内容";
                }
        }
}
