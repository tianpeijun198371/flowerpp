/**
 * LmslogUserDescModel.java.
 * User: keyh  Date: 2004-8-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class LmslogUserDescModel implements Serializable
{
        /**
         * identifier field
         */
        private int loguserdescid;

        /**
         * persistent field
         */
        private int usertype;

        /**
         * persistent field
         */
        private String usertypedesc;

        /**
         * nullable persistent field
         */
        private String userdesc;

        /**
         * full constructor
         */
        public LmslogUserDescModel(int loguserdescid, int usertype,
                                   String usertypedesc, String userdesc)
        {
                this.loguserdescid = loguserdescid;
                this.usertype = usertype;
                this.usertypedesc = usertypedesc;
                this.userdesc = userdesc;
        }

        /**
         * default constructor
         */
        public LmslogUserDescModel()
        {
        }

        /**
         * minimal constructor
         */
        public LmslogUserDescModel(int loguserdescid, int usertype,
                                   String usertypedesc)
        {
                this.loguserdescid = loguserdescid;
                this.usertype = usertype;
                this.usertypedesc = usertypedesc;
        }

        public int getLoguserdescid()
        {
                return this.loguserdescid;
        }

        public void setLoguserdescid(int loguserdescid)
        {
                this.loguserdescid = loguserdescid;
        }

        public int getUsertype()
        {
                return this.usertype;
        }

        public void setUsertype(int usertype)
        {
                this.usertype = usertype;
        }

        public String getUsertypedesc()
        {
                return this.usertypedesc;
        }

        public void setUsertypedesc(String usertypedesc)
        {
                this.usertypedesc = usertypedesc;
        }

        public String getUserdesc()
        {
                return this.userdesc;
        }

        public void setUserdesc(String userdesc)
        {
                this.userdesc = userdesc;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("loguserdescid",
                        getLoguserdescid()).toString();
        }
}
