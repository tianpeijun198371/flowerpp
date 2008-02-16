/**
 * LmslogOperDescModel.java.
 * User: keyh  Date: 2004-8-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.model;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class LmslogOperDescModel implements Serializable
{
        /**
         * persistent field
         */
        private int operationtypeid;
        private int logtypeid;

        /**
         * persistent field
         */
        private String operationname;

        /**
         * nullable persistent field
         */
        private String operationdesc;

        public LmslogOperDescModel()
        {
        }

        public LmslogOperDescModel(int operationtypeid, int logtypeid,
                                   String operationname, String operationdesc)
        {
                this.operationtypeid = operationtypeid;
                this.logtypeid = logtypeid;
                this.operationname = operationname;
                this.operationdesc = operationdesc;
        }

        public int getOperationtypeid()
        {
                return operationtypeid;
        }

        public void setOperationtypeid(int operationtypeid)
        {
                this.operationtypeid = operationtypeid;
        }

        public int getLogtypeid()
        {
                return logtypeid;
        }

        public void setLogtypeid(int logtypeid)
        {
                this.logtypeid = logtypeid;
        }

        public String getOperationname()
        {
                return operationname;
        }

        public void setOperationname(String operationname)
        {
                this.operationname = operationname;
        }

        public String getOperationdesc()
        {
                return operationdesc;
        }

        public void setOperationdesc(String operationdesc)
        {
                this.operationdesc = operationdesc;
        }
}
