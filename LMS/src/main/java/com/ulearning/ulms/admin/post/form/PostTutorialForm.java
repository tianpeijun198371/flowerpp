package com.ulearning.ulms.admin.post.form;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PostTutorialForm implements Serializable
{
        /**
         * identifier field
         */
        private int postTutorialID;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * persistent field
         */
        private int tutorialID;

        /**
         * persistent field
         */
        private int type;

        /**
         * persistent field
         */
        private int sort;

        /**
         * full constructor
         */
        public PostTutorialForm(int relationID, int tutorialID, int type, int sort)
        {
                this.relationID = relationID;
                this.tutorialID = tutorialID;
                this.type = type;
                this.sort = sort;
        }

        /**
         * default constructor
         */
        public PostTutorialForm()
        {
        }

        public int getPostTutorialID()
        {
                return this.postTutorialID;
        }

        public void setPostTutorialID(int postTutorialID)
        {
                this.postTutorialID = postTutorialID;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getTutorialID()
        {
                return this.tutorialID;
        }

        public void setTutorialID(int tutorialID)
        {
                this.tutorialID = tutorialID;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getSort()
        {
                return this.sort;
        }

        public void setSort(int sort)
        {
                this.sort = sort;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("postTutorialID",
                        getPostTutorialID()).toString();
        }
}
