/**
 * financeOrganSearch_Form.java.
 * User: liz  Date: 2005-12-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import org.apache.struts.action.ActionForm;


public class financeOrganSearch_Form extends ActionForm
{
        private int detailType; //��ϸ����
        private String beginDate; //��ʼʱ��
        private String endDate; //����ʱ��
        private String orgId; //��ǰ�û�ID

        public String getOrgId()
        {
                return orgId;
        }

        public void setOrgId(String orgId)
        {
                this.orgId = orgId;
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
