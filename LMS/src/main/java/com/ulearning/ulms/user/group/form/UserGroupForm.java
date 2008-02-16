/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.form;

import org.apache.struts.action.ActionForm;
import com.ulearning.ulms.user.group.model.UserGroupModel;

import java.util.Date;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051130
 * Time: 142230
 */

public class UserGroupForm extends ActionForm
{

        private int usergroupid = 0;
        private int groupid = 0;
        private int userid = 0;
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";
        private String remark4 = "";


        public int getUsergroupid()
        {
                return usergroupid;
        }


        public void setUsergroupid(int usergroupid)
        {
                this.usergroupid = usergroupid;
        }


        public int getGroupid()
        {
                return groupid;
        }


        public void setGroupid(int groupid)
        {
                this.groupid = groupid;
        }


        public int getUserid()
        {
                return userid;
        }


        public void setUserid(int userid)
        {
                this.userid = userid;
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


        public String getRemark4()
        {
                return remark4;
        }


        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }


        public UserGroupModel getUserGroupModel()
        {
                UserGroupModel userGroupModel = new UserGroupModel();
                userGroupModel.setUsergroupid(this.usergroupid);
                userGroupModel.setGroupid(this.groupid);
                userGroupModel.setUserid(this.userid);
                userGroupModel.setRemark1(this.remark1);
                userGroupModel.setRemark2(this.remark2);
                userGroupModel.setRemark3(this.remark3);
                userGroupModel.setRemark4(this.remark4);
                return userGroupModel;
        }

        public UserGroupForm getUserGroupForm(UserGroupModel theModel)
        {
                UserGroupForm userGroupForm = new UserGroupForm();
                userGroupForm.setUsergroupid(theModel.getUsergroupid());
                userGroupForm.setGroupid(theModel.getGroupid());
                userGroupForm.setUserid(theModel.getUserid());
                userGroupForm.setRemark1(theModel.getRemark1());
                userGroupForm.setRemark2(theModel.getRemark2());
                userGroupForm.setRemark3(theModel.getRemark3());
                userGroupForm.setRemark4(theModel.getRemark4());
                return userGroupForm;
        }


}
