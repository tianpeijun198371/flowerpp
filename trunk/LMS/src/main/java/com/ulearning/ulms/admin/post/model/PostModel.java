package com.ulearning.ulms.admin.post.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PostModel implements Serializable
{
        /**
         * identifier field
         */
        private int postID;

        /**
         * persistent field
         */
        private int aspID;

        /**
         * persistent field
         */
        private int parentID;

        /**
         * persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String postType;

        /**
         * nullable persistent field
         */
        private String postLevel;

        /**
         * nullable persistent field
         */
        private String require;

        /**
         * nullable persistent field
         */
        private String describe;

        /**
         * nullable persistent field
         */
        private String skill;

        /**
         * nullable persistent field
         */
        private String remark1;

        /**
         * nullable persistent field
         */
        private String remark2;

        /**
         * nullable persistent field
         */
        private String remark3;

        /**
         * nullable persistent field
         */
        private String remark4;

        /**
         * nullable persistent field
         */
        private String remark5;

        /**
         * full constructor
         */
        public PostModel(int aspID, int parentID, String name, String postType,
                         String postLevel, String require, String describe, String skill,
                         String remark1, String remark2, String remark3, String remark4,
                         String remark5)
        {
                this.aspID = aspID;
                this.parentID = parentID;
                this.name = name;
                this.postType = postType;
                this.postLevel = postLevel;
                this.require = require;
                this.describe = describe;
                this.skill = skill;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
        }

        /**
         * default constructor
         */
        public PostModel()
        {
        }

        /**
         * minimal constructor
         */
        public PostModel(int aspID, int parentID, String name)
        {
                this.aspID = aspID;
                this.parentID = parentID;
                this.name = name;
        }

        public int getPostID()
        {
                return this.postID;
        }

        public void setPostID(int postID)
        {
                this.postID = postID;
        }

        public int getAspID()
        {
                return this.aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public int getParentID()
        {
                return this.parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getPostType()
        {
                return this.postType;
        }

        public void setPostType(String postType)
        {
                this.postType = postType;
        }

        public String getPostLevel()
        {
                return this.postLevel;
        }

        public void setPostLevel(String postLevel)
        {
                this.postLevel = postLevel;
        }

        public String getRequire()
        {
                return this.require;
        }

        public void setRequire(String require)
        {
                this.require = require;
        }

        public String getDescribe()
        {
                return this.describe;
        }

        public void setDescribe(String describe)
        {
                this.describe = describe;
        }

        public String getSkill()
        {
                return this.skill;
        }

        public void setSkill(String skill)
        {
                this.skill = skill;
        }

        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return this.remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return this.remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return this.remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return this.remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("postID", getPostID()).toString();
        }
}
