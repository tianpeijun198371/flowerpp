/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.report.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;

import com.ulearning.ulms.tools.report.custom.report.model.ReportModel;

public class ReportForm extends ActionForm
{
        private int reportID = 0;
        private String reportName = null;
        private String reportType = null;
        private String reportFormat = null;
        private int modulID = 0;
        private int relationID = 0;
        private int categoryID = 0;
        private String jasperReportXMLPath = null;
        private int userID = 0;
        private Date createDate = null;
        private Date updateDate = null;
        private String createDateStr = null;
        private String updateDateStr = null;
        private String remark = null;

        private ReportModel reportModel = null;

        public ReportModel getReportModel()
        {
                reportModel = new ReportModel();
                reportModel.setReportID(this.reportID);
                reportModel.setReportName(this.reportName);
                reportModel.setReportType(this.reportType);
                reportModel.setReportFormat(this.reportFormat);
                reportModel.setModulID(this.modulID);
                reportModel.setRelationID(this.relationID);
                reportModel.setCategoryID(this.categoryID);
                reportModel.setJasperReportXMLPath(this.jasperReportXMLPath);
                reportModel.setUserID(this.userID);
                reportModel.setCreateDate(this.createDate);
                reportModel.setUpdateDate(this.updateDate);
                reportModel.setRemark(this.remark);
                return reportModel;
        }

        public ReportForm getReportForm(ReportModel reportModel)
        {
                ReportForm reportForm = new ReportForm();
                reportForm.setReportID(reportModel.getReportID());
                reportForm.setReportName(reportModel.getReportName());
                reportForm.setReportType(reportModel.getReportType());
                reportForm.setReportFormat(reportModel.getReportFormat());
                reportForm.setModulID(reportModel.getModulID());
                reportForm.setRelationID(reportModel.getRelationID());
                reportForm.setCategoryID(reportModel.getCategoryID());
                reportForm.setJasperReportXMLPath(reportModel.getJasperReportXMLPath());
                reportForm.setUserID(reportModel.getUserID());
                reportForm.setCreateDate(reportModel.getCreateDate());
                reportForm.setUpdateDate(reportModel.getUpdateDate());
                reportForm.setRemark(reportModel.getRemark());
                return reportForm;
        }

        public int getReportID()
        {
                return reportID;
        }

        public void setReportID(int reportID)
        {
                this.reportID = reportID;
        }

        public String getReportName()
        {
                return reportName;
        }

        public void setReportName(String reportName)
        {
                this.reportName = reportName;
        }

        public String getReportType()
        {
                return reportType;
        }

        public void setReportType(String reportType)
        {
                this.reportType = reportType;
        }

        public String getReportFormat()
        {
                return reportFormat;
        }

        public void setReportFormat(String reportFormat)
        {
                this.reportFormat = reportFormat;
        }

        public int getModulID()
        {
                return modulID;
        }

        public void setModulID(int modulID)
        {
                this.modulID = modulID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getCategoryID()
        {
                return categoryID;
        }

        public void setCategoryID(int categoryID)
        {
                this.categoryID = categoryID;
        }

        public String getJasperReportXMLPath()
        {
                return jasperReportXMLPath;
        }

        public void setJasperReportXMLPath(String jasperReportXMLPath)
        {
                this.jasperReportXMLPath = jasperReportXMLPath;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getUpdateDate()
        {
                return updateDate;
        }

        public void setUpdateDate(Date updateDate)
        {
                this.updateDate = updateDate;
        }

        public String getCreateDateStr()
        {
                return createDateStr;
        }

        public void setCreateDateStr(String createDateStr)
        {
                this.createDateStr = createDateStr;
        }

        public String getUpdateDateStr()
        {
                return updateDateStr;
        }

        public void setUpdateDateStr(String updateDateStr)
        {
                this.updateDateStr = updateDateStr;
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
