package com.ulearning.ulms.tools.discussion.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class DiscussModel implements Serializable
{
        /**
         * identifier field
         */
        private int articleid;

        /**
         * persistent field
         */
        private int userid;

        /**
         * persistent field
         */
        private int parentid;

        /**
         * persistent field
         */
        private String title;

        /**
         * persistent field
         */
        private String content;

        /**
         * persistent field
         */
        private Date datetime;

        /**
         * nullable persistent field
         */
        private String filelink;

        /**
         * nullable persistent field
         */
        private String linktype;

        /**
         * nullable persistent field
         */
        private String islock;

        /**
         * nullable persistent field
         */
        private String isread;

        /**
         * nullable persistent field
         */
        private int layer;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * persistent field
         */
        private int forumid;
        private int best;
        private int recommend;

        /**
         * full constructor
         */
        public DiscussModel(int userid, int parentid, String title, String content,
                            Date datetime, String filelink, String linktype, String islock,
                            String isread, int layer, String description, int forumid, int best,
                            int recommend)
        {
                this.userid = userid;
                this.parentid = parentid;
                this.title = title;
                this.content = content;
                this.datetime = datetime;
                this.filelink = filelink;
                this.linktype = linktype;
                this.islock = islock;
                this.isread = isread;
                this.layer = layer;
                this.description = description;
                this.forumid = forumid;
                this.best = best;
                this.recommend = recommend;
        }

        /**
         * default constructor
         */
        public DiscussModel()
        {
        }

        /**
         * minimal constructor
         */
        public DiscussModel(int userid, int parentid, String title, String content,
                            Date datetime, int forumid)
        {
                this.userid = userid;
                this.parentid = parentid;
                this.title = title;
                this.content = content;
                this.datetime = datetime;
                this.forumid = forumid;
        }

        public int getArticleid()
        {
                return this.articleid;
        }

        public void setArticleid(int articleid)
        {
                this.articleid = articleid;
        }

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public int getParentid()
        {
                return this.parentid;
        }

        public void setParentid(int parentid)
        {
                this.parentid = parentid;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getContent()
        {
                return this.content;
        }

        public void setContent(String content)
        {
                this.content = content;
        }

        public Date getDatetime()
        {
                return this.datetime;
        }

        public void setDatetime(Date datetime)
        {
                this.datetime = datetime;
        }

        public String getFilelink()
        {
                return this.filelink;
        }

        public void setFilelink(String filelink)
        {
                this.filelink = filelink;
        }

        public String getLinktype()
        {
                return this.linktype;
        }

        public void setLinktype(String linktype)
        {
                this.linktype = linktype;
        }

        public String getIslock()
        {
                return this.islock;
        }

        public void setIslock(String islock)
        {
                this.islock = islock;
        }

        public String getIsread()
        {
                return this.isread;
        }

        public void setIsread(String isread)
        {
                this.isread = isread;
        }

        public int getLayer()
        {
                return this.layer;
        }

        public void setLayer(int layer)
        {
                this.layer = layer;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public int getForumid()
        {
                return this.forumid;
        }

        public void setForumid(int forumid)
        {
                this.forumid = forumid;
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

        public String toString()
        {
                return new ToStringBuilder(this).append("articleid", getArticleid())
                        .toString();
        }
}
