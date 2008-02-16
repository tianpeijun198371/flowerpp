package com.ulearning.ulms.tools.report.custom.report.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Hibernate CodeGenerator
 */
public class ReportModel implements Serializable
{

        /**
         * identifier field
         */
        private int reportID;

        /**
         * persistent field
         */
        private String reportName;

        /**
         * nullable persistent field
         */
        private String reportType;

        /**
         * nullable persistent field
         */
        private String reportFormat;

        /**
         * persistent field
         */
        private int modulID;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * persistent field
         */
        private int categoryID;

        /**
         * nullable persistent field
         */
        private String jasperReportXMLPath;

        /**
         * persistent field
         */
        private int userID;

        /**
         * nullable persistent field
         */
        private Date createDate;

        /**
         * nullable persistent field
         */
        private Date updateDate;

        /**
         * nullable persistent field
         */
        private String remark;

        /**
         * full constructor
         */
        public ReportModel(String reportName, String reportType, String reportFormat, int modulID, int relationID, int categoryID, String jasperReportXMLPath, int userID, Date createDate, Date updateDate, String remark)
        {
                this.reportName = reportName;
                this.reportType = reportType;
                this.reportFormat = reportFormat;
                this.modulID = modulID;
                this.relationID = relationID;
                this.categoryID = categoryID;
                this.jasperReportXMLPath = jasperReportXMLPath;
                this.userID = userID;
                this.createDate = createDate;
                this.updateDate = updateDate;
                this.remark = remark;
        }

        /**
         * default constructor
         */
        public ReportModel()
        {
        }

        /**
         * minimal constructor
         */
        public ReportModel(String reportName, int modulID, int relationID, int categoryID, int userID)
        {
                this.reportName = reportName;
                this.modulID = modulID;
                this.relationID = relationID;
                this.categoryID = categoryID;
                this.userID = userID;
        }

        public int getReportID()
        {
                return this.reportID;
        }

        public void setReportID(int reportID)
        {
                this.reportID = reportID;
        }

        public String getReportName()
        {
                return this.reportName;
        }

        public void setReportName(String reportName)
        {
                this.reportName = reportName;
        }

        public String getReportType()
        {
                return this.reportType;
        }

        public void setReportType(String reportType)
        {
                this.reportType = reportType;
        }

        public String getReportFormat()
        {
                return this.reportFormat;
        }

        public void setReportFormat(String reportFormat)
        {
                this.reportFormat = reportFormat;
        }

        public int getModulID()
        {
                return this.modulID;
        }

        public void setModulID(int modulID)
        {
                this.modulID = modulID;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getCategoryID()
        {
                return this.categoryID;
        }

        public void setCategoryID(int categoryID)
        {
                this.categoryID = categoryID;
        }

        public String getJasperReportXMLPath()
        {
                return this.jasperReportXMLPath;
        }

        public void setJasperReportXMLPath(String jasperReportXMLPath)
        {
                this.jasperReportXMLPath = jasperReportXMLPath;
        }

        public int getUserID()
        {
                return this.userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getCreateDate()
        {
                return this.createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getUpdateDate()
        {
                return this.updateDate;
        }

        public void setUpdateDate(Date updateDate)
        {
                this.updateDate = updateDate;
        }

        public String getRemark()
        {
                return this.remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("reportID", getReportID())
                        .toString();
        }

}
