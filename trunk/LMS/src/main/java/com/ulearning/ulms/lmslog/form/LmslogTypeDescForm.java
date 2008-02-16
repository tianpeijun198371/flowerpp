/**
 * LmslogTypeDescForm.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.form;

import com.ulearning.ulms.lmslog.model.LmslogTypeDescModel;

import org.apache.struts.action.ActionForm;


public class LmslogTypeDescForm extends ActionForm
{
        int logTypeID;
        String logTypeDesc;

        public LmslogTypeDescForm(int logTypeID, String logTypeDesc)
        {
                this.logTypeID = logTypeID;
                this.logTypeDesc = logTypeDesc;
        }

        public LmslogTypeDescForm()
        {
        }

        public LmslogTypeDescModel getLmslogTypeDescModel()
        {
                LmslogTypeDescModel ltdm = new LmslogTypeDescModel();
                ltdm.setLogtypeid(this.logTypeID);
                ltdm.setLogtypedesc(this.logTypeDesc);

                return ltdm;
        }

        public int getLogTypeID()
        {
                return logTypeID;
        }

        public void setLogTypeID(int logTypeID)
        {
                this.logTypeID = logTypeID;
        }

        public String getLogTypeDesc()
        {
                return logTypeDesc;
        }

        public void setLogTypeDesc(String logTypeDesc)
        {
                this.logTypeDesc = logTypeDesc;
        }
}
