/**
 * CodeItem.java.
 * User: Dengj  Date: 2004-12-01
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class CodeItem implements Serializable
{
        /**
         * identifier field
         */
        private int codeitemid;

        /**
         * persistent field
         */
        private int codeid;

        /**
         * persistent field
         */
        private int type1;

        /**
         * persistent field
         */
        private String name1;

        /**
         * persistent field
         */
        private String value1;

        /**
         * nullable persistent field
         */
        private int orderindex;

        /**
         * nullable persistent field
         */
        private String comments;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * full constructor
         */
        public CodeItem(int codeid, int type, String name, String value,
                        int orderindex, String comments, String description)
        {
                this.codeid = codeid;
                this.type1 = type;
                this.name1 = name;
                this.value1 = value;
                this.orderindex = orderindex;
                this.comments = comments;
                this.description = description;
        }

        /**
         * default constructor
         */
        public CodeItem()
        {
        }

        /**
         * minimal constructor
         */
        public CodeItem(int codeid, int type, String name, String value)
        {
                this.codeid = codeid;
                this.type1 = type;
                this.name1 = name;
                this.value1 = value;
        }

        public int getCodeitemid()
        {
                return this.codeitemid;
        }

        public void setCodeitemid(int codeitemid)
        {
                this.codeitemid = codeitemid;
        }

        public int getCodeid()
        {
                return codeid;
        }

        public void setCodeid(int codeid)
        {
                this.codeid = codeid;
        }

        public int getType1()
        {
                return this.type1;
        }

        public void setType1(int type)
        {
                this.type1 = type;
        }

        public String getName1()
        {
                return this.name1;
        }

        public void setName1(String name)
        {
                this.name1 = name;
        }

        public String getValue1()
        {
                return this.value1;
        }

        public void setValue1(String value)
        {
                this.value1 = value;
        }

        public int getOrderindex()
        {
                return this.orderindex;
        }

        public void setOrderindex(int orderindex)
        {
                this.orderindex = orderindex;
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

        public String toString()
        {
                return new ToStringBuilder(this).append("codeitemid", getCodeitemid())
                        .toString();
        }
}
