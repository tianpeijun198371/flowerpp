package com.ulearning.ulms.admin.teacher.form;

import org.apache.struts.action.ActionForm;


/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-17
 * Time: 11:23:33
 * To change this template use File | Settings | File Templates.
 */
public class TeacherGroupForm extends ActionForm
{
        private int id; //PK
        private int userId; //FK 师资用户ID
        private int groupId; // 师资关系ID
        private String remark;

        public int getId()
        {
                return id;
        }

        public void setId(int id)
        {
                this.id = id;
        }

        public int getUserId()
        {
                return userId;
        }

        public void setUserId(int userId)
        {
                this.userId = userId;
        }

        public int getGroupId()
        {
                return groupId;
        }

        public void setGroupId(int groupId)
        {
                this.groupId = groupId;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }
}
