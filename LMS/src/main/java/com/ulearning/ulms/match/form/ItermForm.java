/**
 * ItermModel.java.
 * User: zhangy Date: 2005-6-2 14:32:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.form;

import org.apache.struts.action.ActionForm;


public class ItermForm extends ActionForm
{
        /**
         * identifier field
         */
        private int itermid;

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
        public ItermForm(String matchkey, String matchkeyid,
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
        public ItermForm()
        {
        }

        public int getItermid()
        {
                return this.itermid;
        }

        public void setItermid(int itermid)
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
}
