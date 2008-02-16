/**
 * financeUserDetail_Form.java.
 * User: liz  Date: 2005-12-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import org.apache.struts.action.ActionForm;


/**
 * ���������û���ϸ����������Form
 *
 * @author Liz
 * @ date 2005-12-19
 */
public class financeUserDetail_Form extends ActionForm
{
        private int detailType; //��ϸ����
        private String beginDate; //��ʼʱ��
        private String endDate; //����ʱ��
        private String userID; //��ǰ�û�ID

        public String getUserID()
        {
                return userID;
        }

        public void setUserID(String userID)
        {
                this.userID = userID;
        }

        public int getDetailType()
        {
                return detailType;
        }

        public void setDetailType(int detailType)
        {
                this.detailType = detailType;
        }

        public String getBeginDate()
        {
                return beginDate;
        }

        public void setBeginDate(String beginDate)
        {
                this.beginDate = beginDate;
        }

        public String getEndDate()
        {
                return endDate;
        }

        public void setEndDate(String endDate)
        {
                this.endDate = endDate;
        }
}
