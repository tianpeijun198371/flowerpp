/** * CategoryForm.java. 
 * User: xiejh  Date: 2004-7-19 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */

package com.ulearning.ulms.tools.report.general.model;

import org.apache.struts.action.ActionForm;

public class MReportForm extends ActionForm
{
        private int mReportID = 0;
        private int parentID = 0;
        private String reportType = null;
        private String mReportName = null;
        private String state = null;

        public int getMReportID()
        {
                return mReportID;
        }

        public void setMReportID(int mReportID)
        {
                this.mReportID = mReportID;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public String getReportType()
        {
                return reportType;
        }

        public void setReportType(String reportType)
        {
                this.reportType = reportType;
        }

        public String getMReportName()
        {
                return mReportName;
        }

        public void setMReportName(String mReportName)
        {
                this.mReportName = mReportName;
        }

        public String getState()
        {
                return state;
        }

        public void setState(String state)
        {
                this.state = state;
        }


}