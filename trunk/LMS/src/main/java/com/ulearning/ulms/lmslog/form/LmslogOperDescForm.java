/**
 * LmslogOperDesc.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.form;

import com.ulearning.ulms.lmslog.model.LmslogOperDescModel;

import org.apache.struts.action.ActionForm;


public class LmslogOperDescForm extends ActionForm
{
        int operationTypeID;
        int logTypeID;
        String operationName;
        String operationDesc;

        public LmslogOperDescForm(int operationTypeID, int logTypeID,
                                  String operationName, String operationDesc)
        {
                this.operationTypeID = operationTypeID;
                this.logTypeID = logTypeID;
                this.operationName = operationName;
                this.operationDesc = operationDesc;
        }

        public LmslogOperDescForm()
        {
        }

        public LmslogOperDescModel getLmslogOperModel()
        {
                LmslogOperDescModel lodm = new LmslogOperDescModel();
                lodm.setOperationtypeid(this.operationTypeID);
                lodm.setLogtypeid(this.logTypeID);
                lodm.setOperationname(this.operationName);
                lodm.setOperationdesc(this.operationDesc);

                return lodm;
        }

        public int getOperationTypeID()
        {
                return operationTypeID;
        }

        public void setOperationTypeID(int operationTypeID)
        {
                this.operationTypeID = operationTypeID;
        }

        public int getLogTypeID()
        {
                return logTypeID;
        }

        public void setLogTypeID(int logTypeID)
        {
                this.logTypeID = logTypeID;
        }

        public String getOperationName()
        {
                return operationName;
        }

        public void setOperationName(String operationName)
        {
                this.operationName = operationName;
        }

        public String getOperationDesc()
        {
                return operationDesc;
        }

        public void setOperationDesc(String operationDesc)
        {
                this.operationDesc = operationDesc;
        }
}
