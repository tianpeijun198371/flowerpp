/**
 * DiscussForm.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.discussion.model.DiscussModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class DiscussForm extends UploadForm
{
        private int articleID = 0;
        private int forumID = 0;
        private int userID = 0;
        private int parentID = 0;
        private String title = null;
        private String content = null;
        private Date dateTime = new Date(); //new java.sql.Date(System.currentTimeMillis());//;
        private String fileLink = null;
        private int linkType = 0;
        private int isLock = 0;
        private int isRead = 0;
        private int layer = 0;
        private String description = null;
        private int best = 0;
        private int recommend = 0;

        public DiscussForm()
        {
        }

        public DiscussForm(DiscussModel dm)
        {
                if (dm != null)
                {
                        this.articleID = dm.getArticleid();
                        this.forumID = dm.getForumid();
                        this.userID = dm.getUserid();
                        this.parentID = dm.getParentid();
                        this.title = dm.getTitle();
                        this.content = dm.getContent();
                        this.dateTime = dm.getDatetime();
                        this.fileLink = StringUtil.nullToStr(dm.getFilelink());
                        this.linkType = Integer.parseInt(dm.getLinktype());
                        this.isLock = Integer.parseInt(dm.getIslock());
                        this.isRead = Integer.parseInt(dm.getIsread());
                        this.layer = dm.getLayer();
                        this.description = dm.getDescription();
                        this.best = dm.getBest();
                        this.recommend = dm.getRecommend();
                }
        }

        public int getArticleID()
        {
                return articleID;
        }

        public void setArticleID(int articleID)
        {
                this.articleID = articleID;
        }

        public int getForumID()
        {
                return forumID;
        }

        public void setForumID(int forumID)
        {
                this.forumID = forumID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
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

        public Date getDateTime()
        {
                return dateTime;
        }

        public void setDateTime(Date dateTime)
        {
                this.dateTime = dateTime;
        }

        public String getFileLink()
        {
                return fileLink;
        }

        public void setFileLink(String fileLink)
        {
                this.fileLink = fileLink;
        }

        public int getLinkType()
        {
                return linkType;
        }

        public void setLinkType(int linkType)
        {
                this.linkType = linkType;
        }

        public int getLayer()
        {
                return layer;
        }

        public void setLayer(int layer)
        {
                this.layer = layer;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public int getIsLock()
        {
                return isLock;
        }

        public void setIsLock(int lock)
        {
                isLock = lock;
        }

        public int getIsRead()
        {
                return isRead;
        }

        public void setIsRead(int isRead)
        {
                this.isRead = isRead;
        }

        public int getBest()
        {
                return best;
        }

        public void setBest(int best)
        {
                this.best = best;
        }

        public int getRecommend()
        {
                return recommend;
        }

        public void setRecommend(int recommend)
        {
                this.recommend = recommend;
        }

        public DiscussModel getDiscussModel()
        {
                DiscussModel dm = new DiscussModel();
                dm.setArticleid(this.articleID);
                dm.setForumid(this.forumID);
                dm.setUserid(this.userID);
                dm.setParentid(this.parentID);
                dm.setTitle(this.title);
                dm.setContent(this.content);
                dm.setDatetime(this.dateTime);
                dm.setFilelink(this.fileLink);
                dm.setLinktype(new Integer(this.linkType).toString());
                dm.setIslock(new Integer(this.isLock).toString());
                dm.setIsread(new Integer(this.isRead).toString());
                dm.setLayer(this.layer);
                dm.setDescription(this.description);
                dm.setBest(this.best);
                dm.setRecommend(this.recommend);

                return dm;
        }
}
