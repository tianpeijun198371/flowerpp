/**
 * eRecordPointConversionModel.java.
 * User: YuD Date: 2005-6-13 11:20:52
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.form;

import com.ulearning.ulms.evaluate.model.ERecordPointConversionModel;

import org.apache.struts.action.ActionForm;


public class ERecordPointConversionForm extends ActionForm
{
        private int actionID;
        private int point;

        public ERecordPointConversionForm()
        {
        }

        public ERecordPointConversionModel geteRecordPointConversionModel()
        {
                ERecordPointConversionModel eRecordPointConversionModel = new ERecordPointConversionModel();
                eRecordPointConversionModel.setActionID(this.actionID);
                eRecordPointConversionModel.setPoint(this.point);

                return eRecordPointConversionModel;
        }

        public ERecordPointConversionForm geteRecordPointConversionForm(
                ERecordPointConversionModel eRecordPointConversionModel)
        {
                ERecordPointConversionForm eRecordPointConversionForm = new ERecordPointConversionForm();
                eRecordPointConversionForm.setActionID(eRecordPointConversionModel.getActionID());
                eRecordPointConversionForm.setPoint(eRecordPointConversionModel.getPoint());

                return eRecordPointConversionForm;
        }

        public int getActionID()
        {
                return actionID;
        }

        public void setActionID(int actionID)
        {
                this.actionID = actionID;
        }

        public int getPoint()
        {
                return point;
        }

        public void setPoint(int point)
        {
                this.point = point;
        }
}
