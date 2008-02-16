/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:28:23
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.form;

import com.ulearning.ulms.admin.colsignup.colsign.model.ColSignModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class ColSignForm extends ActionForm
{
        private int colSignID = 0;
        private String name = "";
        private int orgID = 0;
        private String orgName = "";
        private String description = "";
        private Date createTime = null;
        private String createTimeStr = "";
        private int creator = 0;
        private String isSubmit = "";
        private String approved = "";
        private String desc1 = "";

        public ColSignForm()
        {
        }

        public ColSignForm(ColSignModel csm)
        {
                if (csm != null)
                {
                        this.colSignID = csm.getColSignID();
                        this.name = csm.getName();
                        this.orgID = csm.getOrgID();
                        this.description = csm.getDescription();
                        this.createTime = csm.getCreateTime();
                        this.creator = csm.getCreator();
                        this.isSubmit = csm.getIsSubmit();
                        this.approved = csm.getApproved();
                        this.desc1 = csm.getDesc1();
                }
        }

        public int getColSignID()
        {
                return colSignID;
        }

        public void setColSignID(int colSignID)
        {
                this.colSignID = colSignID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getOrgName()
        {
                return orgName;
        }

        public void setOrgName(String orgName)
        {
                this.orgName = orgName;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Date getCreateTime()
        {
                return createTime;
        }

        public void setCreateTime(Date createTime)
        {
                this.createTime = createTime;
        }

        public String getCreateTimeStr()
        {
                return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr)
        {
                this.createTimeStr = createTimeStr;
        }

        public int getCreator()
        {
                return creator;
        }

        public void setCreator(int creator)
        {
                this.creator = creator;
        }

        public String getIsSubmit()
        {
                return isSubmit;
        }

        public void setIsSubmit(String isSubmit)
        {
                this.isSubmit = isSubmit;
        }

        public String getApproved()
        {
                return approved;
        }

        public void setApproved(String approved)
        {
                this.approved = approved;
        }

        public String getDesc1()
        {
                return desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public ColSignModel getColSignModel()
        {
                ColSignModel csm = new ColSignModel();
                csm.setColSignID(this.colSignID);
                csm.setName(this.name);
                csm.setOrgID(this.orgID);
                csm.setDescription(this.description);
                csm.setCreateTime(this.createTime);
                csm.setCreator(this.creator);
                csm.setIsSubmit(this.isSubmit);
                csm.setApproved(this.approved);
                csm.setDesc1(this.desc1);

                return csm;
        }
}
