/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeuser.form;

import com.ulearning.ulms.admin.gradeuser.model.GradeUserModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeUserForm extends ActionForm
{
        private int gradeUserID = 0;
        private String gradeUserName = "";
        private String gradeUserpwd = "";
        private String entergrade = "";
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";

        public int getGradeUserID()
        {
                return gradeUserID;
        }

        public void setGradeUserID(int gradeUserID)
        {
                this.gradeUserID = gradeUserID;
        }

        public String getGradeUserName()
        {
                return gradeUserName;
        }

        public void setGradeUserName(String gradeUserName)
        {
                this.gradeUserName = gradeUserName;
        }

        public String getGradeUserpwd()
        {
                return gradeUserpwd;
        }

        public void setGradeUserpwd(String gradeUserpwd)
        {
                this.gradeUserpwd = gradeUserpwd;
        }

        public String getEntergrade()
        {
                return entergrade;
        }

        public void setEntergrade(String entergrade)
        {
                this.entergrade = entergrade;
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

        public GradeUserModel getGradeUserModel()
        {
                GradeUserModel gradeUserModel = new GradeUserModel();
                gradeUserModel.setGradeUserID(this.gradeUserID);
                gradeUserModel.setGradeUserName(this.gradeUserName);
                gradeUserModel.setGradeUserpwd(this.gradeUserpwd);
                gradeUserModel.setEntergrade(this.entergrade);
                gradeUserModel.setRemark1(this.remark1);
                gradeUserModel.setRemark2(this.remark2);
                gradeUserModel.setRemark3(this.remark3);

                return gradeUserModel;
        }

        public GradeUserForm getGradeUserForm(GradeUserModel theModel)
        {
                GradeUserForm gradeUserForm = new GradeUserForm();
                gradeUserForm.setGradeUserID(theModel.getGradeUserID());
                gradeUserForm.setGradeUserName(theModel.getGradeUserName());
                gradeUserForm.setGradeUserpwd(theModel.getGradeUserpwd());
                gradeUserForm.setEntergrade(theModel.getEntergrade());
                gradeUserForm.setRemark1(theModel.getRemark1());
                gradeUserForm.setRemark2(theModel.getRemark2());
                gradeUserForm.setRemark3(theModel.getRemark3());

                return gradeUserForm;
        }
}
