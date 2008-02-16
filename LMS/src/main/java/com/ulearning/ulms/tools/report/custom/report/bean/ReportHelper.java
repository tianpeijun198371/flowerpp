/** * ReportHelper.java.
 * Report: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.custom.report.bean;

import com.ulearning.ulms.tools.report.custom.report.dao.ReportDAO;
import com.ulearning.ulms.tools.report.custom.report.dao.ReportDAOFactory;
import com.ulearning.ulms.tools.report.custom.report.exceptions.ReportDAOSysException;
import com.ulearning.ulms.tools.report.custom.report.form.ReportForm;

import java.util.List;

public class ReportHelper
{

        public void addReport(ReportForm formDetail)
        {
                try
                {
                        ReportDAO reportDao = ReportDAOFactory.getDAO();
                        reportDao.addReport(formDetail);
                }
                catch (ReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public void updateReport(ReportForm formDetail)
        {
                try
                {
                        ReportDAO reportDao = ReportDAOFactory.getDAO();
                        reportDao.updateReport(formDetail);
                }
                catch (ReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public void removeReport(String ReportID)
        {
                try
                {
                        ReportDAO reportDao = ReportDAOFactory.getDAO();
                        reportDao.removeReport(ReportID);
                }
                catch (ReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        /**
         * Wrapping the get report method for JSP and  the other modules
         *
         * @param reportID
         * @return the report modle according to the reportID
         */
        public ReportForm getReport(int reportID)
        {
                ReportForm bf = null;
                try
                {
                        ReportDAO reportDao = ReportDAOFactory.getDAO();
                        bf = reportDao.getReport(reportID);
                }
                catch (ReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return bf;
        }

        /**
         * Wrapping the get reportList method for JSP and  the other modules
         *
         * @param ModulID RalationID CategoryID UserID
         * @return the report list according to the catalog
         */
        public List getReportList(int ModulID, int RalationID, int CategoryID, int UserID)
        {
                List reportList = null;
                try
                {
                        ReportDAO reportDao = ReportDAOFactory.getDAO();
                        reportList = reportDao.getReportList(ModulID, RalationID, CategoryID, UserID);
                }
                catch (ReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return reportList;
        }

        /**
         * Wrapping the get reportList method for JSP and  the other modules
         *
         * @param ModulID RalationID CategoryID UserID
         * @return the report list according to the catalog
         */
        public List getReportList(int ModulID, int RalationID, int UserID)
        {
                List reportList = null;
                try
                {
                        ReportDAO reportDao = ReportDAOFactory.getDAO();
                        reportList = reportDao.getReportList(ModulID, RalationID, UserID);
                }
                catch (ReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return reportList;
        }
}