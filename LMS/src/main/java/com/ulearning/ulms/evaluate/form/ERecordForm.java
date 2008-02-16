/**
 * eRecordModel.java.
 * User: YuD Date: 2005-6-13 10:40:52
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.form;

import com.ulearning.ulms.evaluate.model.ERecordModel;

import org.apache.struts.action.ActionForm;


public class ERecordForm extends ActionForm
{
        private int eRecordID;
        private int userID;
        private int point;

        public ERecordForm()
        {
        }

        public ERecordModel geteRecordModel()
        {
                ERecordModel eRecordModel = new ERecordModel();
                eRecordModel.seteRecordID(this.eRecordID);
                eRecordModel.setUserID(this.userID);
                eRecordModel.setPoint(this.point);

                return eRecordModel;
        }

        public ERecordForm geteRecordForm(ERecordModel eRecordModel)
        {
                ERecordForm eRecordForm = new ERecordForm();
                eRecordForm.seteRecordID(eRecordModel.geteRecordID());
                eRecordForm.setUserID(eRecordModel.getUserID());
                eRecordForm.setPoint(eRecordModel.getPoint());

                return eRecordForm;
        }

        public int geteRecordID()
        {
                return eRecordID;
        }

        public void seteRecordID(int eRecordID)
        {
                this.eRecordID = eRecordID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
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
