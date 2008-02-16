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
        private int detailType; //明细类型
        private String beginDate; //开始时间
        private String endDate; //结束时间
        private String orgId; //当前用户ID

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
