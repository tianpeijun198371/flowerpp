/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.uteachergroup.form;

import com.ulearning.ulms.content.uteachergroup.model.UTeacherGroupModel;

import org.apache.struts.action.ActionForm;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UTeacherGroupForm extends ActionForm
{
        private int uteachgroupID = 0;
        private int userID = 0;
        private int g_Ship_ID = 0;
        private String remark = "";

        public int getUteachgroupID()
        {
                return uteachgroupID;
        }

        public void setUteachgroupID(int uteachgroupID)
        {
                this.uteachgroupID = uteachgroupID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getG_Ship_ID()
        {
                return g_Ship_ID;
        }

        public void setG_Ship_ID(int g_Ship_ID)
        {
                this.g_Ship_ID = g_Ship_ID;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public UTeacherGroupModel getUTeacherGroupModel()
        {
                UTeacherGroupModel uTeacherGroupModel = new UTeacherGroupModel();
                uTeacherGroupModel.setUteachgroupID(this.uteachgroupID);
                uTeacherGroupModel.setUserID(this.userID);
                uTeacherGroupModel.setG_Ship_ID(this.g_Ship_ID);
                uTeacherGroupModel.setRemark(this.remark);

                return uTeacherGroupModel;
        }

        public UTeacherGroupForm getUTeacherGroupForm(UTeacherGroupModel theModel)
        {
                UTeacherGroupForm uTeacherGroupForm = new UTeacherGroupForm();
                uTeacherGroupForm.setUteachgroupID(theModel.getUteachgroupID());
                uTeacherGroupForm.setUserID(theModel.getUserID());
                uTeacherGroupForm.setG_Ship_ID(theModel.getG_Ship_ID());
                uTeacherGroupForm.setRemark(theModel.getRemark());

                return uTeacherGroupForm;
        }
}
