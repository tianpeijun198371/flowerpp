/**
 * Code.java.
 * User: Dengj  Date: 2004-12-01
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.model;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.announcement.model.Announcement;
import com.ulearning.ulms.tools.announcement.model.AnnouncementModel;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Set;


/**
 * @author Hibernate CodeGenerator
 */
public class Codes implements Serializable
{
        /**
         * identifier field
         */
        private int codeid;

        /**
         * nullable persistent field
         */
        private int relationid;

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
        private String comments;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * persistent field
         */
        private Set scodeitemTabs;
        private CodeModel codeModel;

        /**
         * full constructor
         */
        public Codes(int codeid, int relationid, int type, String title,
                     String comments, String description, Set scodeitemTabs)
        {
                this.codeid = codeid;
                this.relationid = relationid;
                this.type = type;
                this.title = title;
                this.comments = comments;
                this.description = description;
                this.scodeitemTabs = scodeitemTabs;
        }

        /**
         * default constructor
         */
        public Codes()
        {
        }

        /**
         * minimal constructor
         */
        public Codes(int codeid, int type, String title, Set scodeitemTabs)
        {
                this.codeid = codeid;
                this.type = type;
                this.title = title;
                this.scodeitemTabs = scodeitemTabs;
        }

        public Codes getCodee(CodeModel codeModel)
        {
                Codes codes = new Codes();
                codes.setCodeid(codeModel.getCodeID());

                return codes;
        }

        public int getCodeid()
        {
                return this.codeid;
        }

        public void setCodeid(int codeid)
        {
                this.codeid = codeid;
        }

        public int getRelationid()
        {
                return this.relationid;
        }

        public void setRelationid(int relationid)
        {
                this.relationid = relationid;
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

        public String getComments()
        {
                return this.comments;
        }

        public void setComments(String comments)
        {
                this.comments = comments;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Set getScodeitemTabs()
        {
                return this.scodeitemTabs;
        }

        public void setScodeitemTabs(Set scodeitemTabs)
        {
                this.scodeitemTabs = scodeitemTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("codeid", getCodeid()).toString();
        }
}
