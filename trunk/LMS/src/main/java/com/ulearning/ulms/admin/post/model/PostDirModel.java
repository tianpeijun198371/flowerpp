package com.ulearning.ulms.admin.post.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PostDirModel implements Serializable
{
        /**
         * identifier field
         */
        private int dirID;

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
        private int type;

        /**
         * persistent field
         */
        private String title;

        /**
         * nullable persistent field
         */
        private String describe;

        /**
         * full constructor
         */
        public PostDirModel(int aspID, int parentID, int type, String title,
                            String describe)
        {
                this.aspID = aspID;
                this.parentID = parentID;
                this.type = type;
                this.title = title;
                this.describe = describe;
        }

        /**
         * default constructor
         */
        public PostDirModel()
        {
        }

        /**
         * minimal constructor
         */
        public PostDirModel(int aspID, int parentID, int type, String title)
        {
                this.aspID = aspID;
                this.parentID = parentID;
                this.type = type;
                this.title = title;
        }

        public int getDirID()
        {
                return this.dirID;
        }

        public void setDirID(int dirID)
        {
                this.dirID = dirID;
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

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getDescribe()
        {
                return this.describe;
        }

        public void setDescribe(String describe)
        {
                this.describe = describe;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("dirID", getDirID()).toString();
        }
}
