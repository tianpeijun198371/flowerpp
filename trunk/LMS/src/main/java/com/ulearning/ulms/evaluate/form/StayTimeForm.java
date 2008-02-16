/**
 * stayTimeModel.java.
 * User: YuD Date: 2005-6-13 09:02:52
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.form;

import com.ulearning.ulms.evaluate.model.StayTimeModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class StayTimeForm extends ActionForm
{
        private int stayTimeID;
        private long stayTime;
        private int userID;
        private int moduleID;
        private String type;
        private int parentID;
        private Date createDate;
        private Date lastUpdateDate;
        private String remark1;
        private String remark2;
        private String remark3;

        public StayTimeForm()
        {
        }

        public StayTimeModel getstayTimeModel()
        {
                StayTimeModel stayTimeModel = new StayTimeModel();
                stayTimeModel.setStayTimeID(this.stayTimeID);
                stayTimeModel.setStayTime(this.stayTime);
                stayTimeModel.setUserID(this.userID);
                stayTimeModel.setModuleID(this.moduleID);
                stayTimeModel.setType(this.type);
                stayTimeModel.setParentID(this.parentID);
                stayTimeModel.setCreateDate(this.createDate);
                stayTimeModel.setLastUpdateDate(this.lastUpdateDate);
                stayTimeModel.setRemark1(this.remark1);
                stayTimeModel.setRemark2(this.remark2);
                stayTimeModel.setRemark3(this.remark3);

                return stayTimeModel;
        }

        public StayTimeForm getstayTimeForm(StayTimeModel stayTimeModel)
        {
                StayTimeForm stayTimeForm = new StayTimeForm();

                stayTimeForm.setStayTimeID(stayTimeModel.getStayTimeID());
                stayTimeForm.setStayTimeID(stayTimeModel.getStayTimeID());
                stayTimeForm.setStayTime(stayTimeModel.getStayTime());
                stayTimeForm.setUserID(stayTimeModel.getUserID());
                stayTimeForm.setModuleID(stayTimeModel.getModuleID());
                stayTimeForm.setType(stayTimeModel.getType());
                stayTimeForm.setParentID(stayTimeModel.getParentID());
                stayTimeForm.setCreateDate(stayTimeModel.getCreateDate());
                stayTimeForm.setLastUpdateDate(stayTimeModel.getLastUpdateDate());
                stayTimeForm.setRemark1(stayTimeModel.getRemark1());
                stayTimeForm.setRemark2(stayTimeModel.getRemark2());
                stayTimeForm.setRemark3(stayTimeModel.getRemark3());

                return stayTimeForm;
        }

        public int getStayTimeID()
        {
                return stayTimeID;
        }

        public void setStayTimeID(int stayTimeID)
        {
                this.stayTimeID = stayTimeID;
        }

        public long getStayTime()
        {
                return stayTime;
        }

        public void setStayTime(long stayTime)
        {
                this.stayTime = stayTime;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getModuleID()
        {
                return moduleID;
        }

        public void setModuleID(int moduleID)
        {
                this.moduleID = moduleID;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getLastUpdateDate()
        {
                return lastUpdateDate;
        }

        public void setLastUpdateDate(Date lastUpdateDate)
        {
                this.lastUpdateDate = lastUpdateDate;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }
}
