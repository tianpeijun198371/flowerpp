package com.ulearning.ulms.admin.teacher.form;

import org.apache.struts.action.ActionForm;


/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-17
 * Time: 11:18:53
 * To change this template use File | Settings | File Templates.
 */
public class GroupShipForm extends ActionForm
{
        private int id; //����
        private int userId; //����Աid������ʦ�ʿ�Ĺ���Ա
        private String groupName; //����
        private String perdUrl; //����ʹ�õ�ַ ��*.do��
        private String remark; //��ע

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
}
