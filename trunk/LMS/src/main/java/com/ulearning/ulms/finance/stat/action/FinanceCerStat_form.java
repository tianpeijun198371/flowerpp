/**
 * FinanceCerStat_form.java.
 * User: liz  Date: 2006-1-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.stat.action;

import org.apache.struts.action.ActionForm;


/**
 * 班级财务统计
 */
public class FinanceCerStat_form extends ActionForm
{
        private String beginDate;
        private String endDate;
        private String cername;
        private int cerid;
        private int stattype; //Action转向标记，1：班级使用，2：课程使用

        public int getStattype()
        {
                return stattype;
        }

        public void setStattype(int stattype)
        {
                this.stattype = stattype;
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

        public String getCername()
        {
                return cername;
        }

        public void setCername(String cername)
        {
                this.cername = cername;
        }

        public int getCerid()
        {
                return cerid;
        }

        public void setCerid(int cerid)
        {
                this.cerid = cerid;
        }
}
