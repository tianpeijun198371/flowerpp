/**
 * ItermModel.java.
 * User: zhangy Date: 2005-6-2 14:32:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class ItermModel implements Serializable
{
        /**
         * identifier field
         */
        private Integer itermid;

        /**
         * nullable persistent field
         */
        private String matchkey;

        /**
         * nullable persistent field
         */
        private String matchkeyid;

        /**
         * nullable persistent field
         */
        private String defaultmatchvalue;

        /**
         * nullable persistent field
         */
        private String defaultoperator;

        /**
         * full constructor
         */
        public ItermModel(String matchkey, String matchkeyid,
                          String defaultmatchvalue, String defaultoperator)
        {
                this.matchkey = matchkey;
                this.matchkeyid = matchkeyid;
                this.defaultmatchvalue = defaultmatchvalue;
                this.defaultoperator = defaultoperator;
        }

        /**
         * default constructor
         */
        public ItermModel()
        {
        }

        public Integer getItermid()
        {
                return this.itermid;
        }

        public void setItermid(Integer itermid)
        {
                this.itermid = itermid;
        }

        public String getMatchkey()
        {
                return this.matchkey;
        }

        public void setMatchkey(String matchkey)
        {
                this.matchkey = matchkey;
        }

        public String getMatchkeyid()
        {
                return this.matchkeyid;
        }

        public void setMatchkeyid(String matchkeyid)
        {
                this.matchkeyid = matchkeyid;
        }

        public String getDefaultmatchvalue()
        {
                return this.defaultmatchvalue;
        }

        public void setDefaultmatchvalue(String defaultmatchvalue)
        {
                this.defaultmatchvalue = defaultmatchvalue;
        }

        public String getDefaultoperator()
        {
                return this.defaultoperator;
        }

        public void setDefaultoperator(String defaultoperator)
        {
                this.defaultoperator = defaultoperator;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("itermid", getItermid())
                        .toString();
        }
}
