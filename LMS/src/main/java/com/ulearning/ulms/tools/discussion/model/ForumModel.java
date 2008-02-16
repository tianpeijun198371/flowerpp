package com.ulearning.ulms.tools.discussion.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Set;


/**
 * @author Hibernate CodeGenerator
 */
public class ForumModel implements Serializable
{
        /**
         * identifier field
         */
        private int forumid;

        /**
         * persistent field
         */
        private int courseid;

        /**
         * nullable persistent field
         */
        private int orderindex;

        /**
         * persistent field
         */
        private String title;

        /**
         * nullable persistent field
         */
        private String isallowann;

        /**
         * nullable persistent field
         */
        private String isupdateaft;

        /**
         * nullable persistent field
         */
        private String isdeleteaft;

        /**
         * nullable persistent field
         */
        private String isaccessory;

        /**
         * nullable persistent field
         */
        private String isnewitem;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * persistent field
         */
        private Set cdiscussTabs;

        /**
         * full constructor
         */
        public ForumModel(int courseid, int orderindex, String title,
                          String isallowann, String isupdateaft, String isdeleteaft,
                          String isaccessory, String isnewitem, String description,
                          Set cdiscussTabs)
        {
                this.courseid = courseid;
                this.orderindex = orderindex;
                this.title = title;
                this.isallowann = isallowann;
                this.isupdateaft = isupdateaft;
                this.isdeleteaft = isdeleteaft;
                this.isaccessory = isaccessory;
                this.isnewitem = isnewitem;
                this.description = description;
                this.cdiscussTabs = cdiscussTabs;
        }

        /**
         * default constructor
         */
        public ForumModel()
        {
        }

        /**
         * minimal constructor
         */
        public ForumModel(int courseid, String title, Set cdiscussTabs)
        {
                this.courseid = courseid;
                this.title = title;
                this.cdiscussTabs = cdiscussTabs;
        }

        public int getForumid()
        {
                return this.forumid;
        }

        public void setForumid(int forumid)
        {
                this.forumid = forumid;
        }

        public int getCourseid()
        {
                return this.courseid;
        }

        public void setCourseid(int courseid)
        {
                this.courseid = courseid;
        }

        public int getOrderindex()
        {
                return this.orderindex;
        }

        public void setOrderindex(int orderindex)
        {
                this.orderindex = orderindex;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getIsallowann()
        {
                return this.isallowann;
        }

        public void setIsallowann(String isallowann)
        {
                this.isallowann = isallowann;
        }

        public String getIsupdateaft()
        {
                return this.isupdateaft;
        }

        public void setIsupdateaft(String isupdateaft)
        {
                this.isupdateaft = isupdateaft;
        }

        public String getIsdeleteaft()
        {
                return this.isdeleteaft;
        }

        public void setIsdeleteaft(String isdeleteaft)
        {
                this.isdeleteaft = isdeleteaft;
        }

        public String getIsaccessory()
        {
                return this.isaccessory;
        }

        public void setIsaccessory(String isaccessory)
        {
                this.isaccessory = isaccessory;
        }

        public String getIsnewitem()
        {
                return this.isnewitem;
        }

        public void setIsnewitem(String isnewitem)
        {
                this.isnewitem = isnewitem;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Set getCdiscussTabs()
        {
                return this.cdiscussTabs;
        }

        public void setCdiscussTabs(Set cdiscussTabs)
        {
                this.cdiscussTabs = cdiscussTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("forumid", getForumid())
                        .toString();
        }
}
