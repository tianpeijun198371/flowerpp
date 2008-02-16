/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.form;

import org.apache.struts.action.ActionForm;
import com.ulearning.ulms.user.group.model.GroupModel;

import java.util.Date;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051124
 * Time: 155359
 */

public class GroupForm extends ActionForm
{

        private int groupid = 0;
        private String groupname = "";
        private int relationID = 0;
        private int type = 0;
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";
        private String remark4 = "";
        private String remark5 = "";


        public int getGroupid()
        {
                return groupid;
        }


        public void setGroupid(int groupid)
        {
                this.groupid = groupid;
        }


        public String getGroupname()
        {
                return groupname;
        }


        public void setGroupname(String groupname)
        {
                this.groupname = groupname;
        }


        public int getRelationid()
        {
                return relationID;
        }


        public void setRelationid(int relationid)
        {
                this.relationID = relationid;
        }


        public int getType()
        {
                return type;
        }


        public void setType(int type)
        {
                this.type = type;
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


        public String getRemark5()
        {
                return remark5;
        }


        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }


        public GroupModel getGroupModel()
        {
                GroupModel groupModel = new GroupModel();
                groupModel.setGroupId(this.groupid);
                groupModel.setGroupName(this.groupname);
                groupModel.setRelationID(this.relationID);
                groupModel.setType(this.type);
                groupModel.setRemark1(this.remark1);
                groupModel.setRemark2(this.remark2);
                groupModel.setRemark3(this.remark3);
                groupModel.setRemark4(this.remark4);
                groupModel.setRemark5(this.remark5);
                return groupModel;
        }

        public GroupForm getGroupForm(GroupModel theModel)
        {
                GroupForm groupForm = new GroupForm();
                groupForm.setGroupid(theModel.getGroupId());
                groupForm.setGroupname(theModel.getGroupName());
                groupForm.setRelationid(theModel.getRelationID());
                groupForm.setType(theModel.getType());
                groupForm.setRemark1(theModel.getRemark1());
                groupForm.setRemark2(theModel.getRemark2());
                groupForm.setRemark3(theModel.getRemark3());
                groupForm.setRemark4(theModel.getRemark4());
                groupForm.setRemark5(theModel.getRemark5());
                return groupForm;
        }


}
