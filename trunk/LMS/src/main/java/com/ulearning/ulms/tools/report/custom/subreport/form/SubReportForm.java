/**
 * Created by IntelliJ IDEA.
 * SubReport: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.subreport.form;

import org.apache.struts.action.ActionForm;

import java.sql.Date;

public class SubReportForm extends ActionForm
{
        private int reportID = 0;
        private int mReportID = 0;
        private String reportName = null;
        private String state = null;


        public int getReportID()
        {
                return reportID;
        }

        public void setReportID(int reportID)
        {
                this.reportID = reportID;
        }

        public int getMReportID()
        {
                return mReportID;
        }

        public void setMReportID(int mReportID)
        {
                this.mReportID = mReportID;
        }

        public String getReportName()
        {
                return reportName;
        }

        public void setReportName(String reportName)
        {
                this.reportName = reportName;
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
