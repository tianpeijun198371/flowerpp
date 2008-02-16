/** * CategoryForm.java. 
 * User: xiejh  Date: 2004-7-19 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */

package com.ulearning.ulms.tools.report.general.model;

import org.apache.struts.action.ActionForm;

public class ReportTypeForm extends ActionForm
{
        private String reportType = null;
        private String reportTypeName = null;
        private int categoryID = 0;
        private String reportTypeDetail = null;
        private String jasperReportXMLPath = null;
        private String state = null;

        public String getReportType()
        {
                return reportType;
        }

        public void setReportType(String reportType)
        {
                this.reportType = reportType;
        }

        public String getReportTypeName()
        {
                return reportTypeName;
        }

        public void setReportTypeName(String reportTypeName)
        {
                this.reportTypeName = reportTypeName;
        }

        public int getCategoryID()
        {
                return categoryID;
        }

        public void setCategoryID(int categoryID)
        {
                this.categoryID = categoryID;
        }

        public String getReportTypeDetail()
        {
                return reportTypeDetail;
        }

        public void setReportTypeDetail(String reportTypeDetail)
        {
                this.reportTypeDetail = reportTypeDetail;
        }

        public String getJasperReportXMLPath()
        {
                return jasperReportXMLPath;
        }

        public void setJasperReportXMLPath(String jasperReportXMLPath)
        {
                this.jasperReportXMLPath = jasperReportXMLPath;
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