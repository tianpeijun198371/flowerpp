/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.ugroupship.form;

import com.ulearning.ulms.content.ugroupship.model.UGroupShipModel;

import org.apache.struts.action.ActionForm;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UGroupShipForm extends ActionForm
{
        private int g_Ship_ID = 0;
        private int userID = 0;
        private String groupName = "";
        private String perdUrl = "";
        private String remark = "";

        public int getG_Ship_ID()
        {
                return g_Ship_ID;
        }

        public void setG_Ship_ID(int g_Ship_ID)
        {
                this.g_Ship_ID = g_Ship_ID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getGroupName()
        {
                return groupName;
        }

        public void setGroupName(String groupName)
        {
                this.groupName = groupName;
        }

        public String getPerdUrl()
        {
                return perdUrl;
        }

        public void setPerdUrl(String perdUrl)
        {
                this.perdUrl = perdUrl;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public UGroupShipModel getUGroupShipModel()
        {
                UGroupShipModel uGroupShipModel = new UGroupShipModel();
                uGroupShipModel.setG_Ship_ID(this.g_Ship_ID);
                uGroupShipModel.setUserID(this.userID);
                uGroupShipModel.setGroupName(this.groupName);
                uGroupShipModel.setPerdUrl(this.perdUrl);
                uGroupShipModel.setRemark(this.remark);

                return uGroupShipModel;
        }

        public UGroupShipForm getUGroupShipForm(UGroupShipModel theModel)
        {
                UGroupShipForm uGroupShipForm = new UGroupShipForm();
                uGroupShipForm.setG_Ship_ID(theModel.getG_Ship_ID());
                uGroupShipForm.setUserID(theModel.getUserID());
                uGroupShipForm.setGroupName(theModel.getGroupName());
                uGroupShipForm.setPerdUrl(theModel.getPerdUrl());
                uGroupShipForm.setRemark(theModel.getRemark());

                return uGroupShipForm;
        }
}
