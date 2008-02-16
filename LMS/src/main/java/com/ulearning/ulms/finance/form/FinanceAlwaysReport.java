/**
 * FinanceAlwaysReport.java.
 * User: liz  Date: 2006-1-9
 * finance always  reports Form
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.form;

import org.apache.struts.action.ActionForm;


public class FinanceAlwaysReport extends ActionForm
{
        private String entityName; //业务名称
        private String entitysum; //合计金额

        public String getEntityName()
        {
                return entityName;
        }

        public void setEntityName(String entityName)
        {
                this.entityName = entityName;
        }

        public String getEntitysum()
        {
                return entitysum;
        }

        public void setEntitysum(String entitysum)
        {
                this.entitysum = entitysum;
        }
}
