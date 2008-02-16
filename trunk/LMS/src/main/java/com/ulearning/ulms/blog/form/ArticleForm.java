/**
 * ArticleForm.java.
 * User: Fengch  Date: 2005-4-19 18:47:02
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.blog.form;

import java.util.Date;


public class ArticleForm
{
        private int articleId;
        private int categoryId;
        private int type;
        private int accountId;
        private String title;
        private String summary;
        private int visiable;
        private Date createdDate;
        private Date updatedDate;
        private int webVisit;
        private int rssVisit;
        private int feedBacks;
        private String inclosure;
        private String inclosurepath;
        private String content;

        public ArticleForm()
        {
        }

        public ArticleForm(int articleId, int categoryId, int type, int accountId,
                           String title, String summary, int visiable, Date createdDate,
                           Date updatedDate, int webVisit, int rssVisit, int feedBacks,
                           String content)
        {
                this.articleId = articleId;
                this.categoryId = categoryId;
                this.type = type;
                this.accountId = accountId;
                this.title = title;
                this.summary = summary;
                this.visiable = visiable;
                this.createdDate = createdDate;
                this.updatedDate = updatedDate;
                this.webVisit = webVisit;
                this.rssVisit = rssVisit;
                this.feedBacks = feedBacks;
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

        public String getInclosurepath()
        {
                return inclosurepath;
        }

        public void setInclosurepath(String inclosurepath)
        {
                this.inclosurepath = inclosurepath;
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

        public int getAccountId()
        {
                return accountId;
        }

        public void setAccountId(int accountId)
        {
                this.accountId = accountId;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getSummary()
        {
                return summary;
        }

        public void setSummary(String summary)
        {
                this.summary = summary;
        }

        public int getVisiable()
        {
                return visiable;
        }

        public void setVisiable(int visiable)
        {
                this.visiable = visiable;
        }

        public Date getCreatedDate()
        {
                return createdDate;
        }

        public void setCreatedDate(Date createdDate)
        {
                this.createdDate = createdDate;
        }

        public Date getUpdatedDate()
        {
                return updatedDate;
        }

        public void setUpdatedDate(Date updatedDate)
        {
                this.updatedDate = updatedDate;
        }

        public int getWebVisit()
        {
                return webVisit;
        }

        public void setWebVisit(int webVisit)
        {
                this.webVisit = webVisit;
        }

        public int getRssVisit()
        {
                return rssVisit;
        }

        public void setRssVisit(int rssVisit)
        {
                this.rssVisit = rssVisit;
        }

        public int getFeedBacks()
        {
                return feedBacks;
        }

        public void setFeedBacks(int feedBacks)
        {
                this.feedBacks = feedBacks;
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
