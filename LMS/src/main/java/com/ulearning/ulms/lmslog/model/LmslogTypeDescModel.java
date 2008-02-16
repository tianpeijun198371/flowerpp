/**
 * LmslogTypeDescModel.java.
 * User: keyh  Date: 2004-8-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class LmslogTypeDescModel implements Serializable
{
        /**
         * identifier field
         */
        private int logtypeid;

        /**
         * persistent field
         */
        private String logtypedesc;

        public LmslogTypeDescModel(int logtypeid, String logtypedesc)
        {
                this.logtypeid = logtypeid;
                this.logtypedesc = logtypedesc;
        }

        /**
         * full constructor
         */
        public LmslogTypeDescModel()
        {
        }

        public int getLogtypeid()
        {
                return logtypeid;
        }

        public void setLogtypeid(int logtypeid)
        {
                this.logtypeid = logtypeid;
        }

        public String getLogtypedesc()
        {
                return logtypedesc;
        }

        public void setLogtypedesc(String logtypedesc)
        {
                this.logtypedesc = logtypedesc;
        }
}
