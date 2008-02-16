/** * SubReportHelper.java.
 * SubReport: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.custom.subreport.bean;

import com.ulearning.ulms.tools.report.custom.subreport.dao.SubReportDAO;
import com.ulearning.ulms.tools.report.custom.subreport.dao.SubReportDAOFactory;
import com.ulearning.ulms.tools.report.custom.subreport.exceptions.SubReportDAOSysException;
import com.ulearning.ulms.tools.report.custom.subreport.form.SubReportForm;

import java.util.List;

public class SubReportHelper
{

        public void addSubReport(SubReportForm srf)
        {
                try
                {
                        SubReportDAO reportDao = SubReportDAOFactory.getDAO();
                        reportDao.addSubReport(srf);
                }
                catch (SubReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public void updateSubReport(SubReportForm srf)
        {
                try
                {
                        SubReportDAO reportDao = SubReportDAOFactory.getDAO();
                        reportDao.updateSubReport(srf);
                }
                catch (SubReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public void removeSubReport(int ReportID, int MReportID)
        {
                try
                {
                        SubReportDAO reportDao = SubReportDAOFactory.getDAO();
                        reportDao.removeSubReport(ReportID, MReportID);
                }
                catch (SubReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public void removeSubReport(int ReportID)
        {
                try
                {
                        SubReportDAO reportDao = SubReportDAOFactory.getDAO();
                        reportDao.removeSubReport(ReportID);
                }
                catch (SubReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        /**
         * Wrapping the get report method for JSP and  the other modules
         *
         * @param ReportID MReportID
         * @return the report modle according to the subReportID
         */
        public SubReportForm getSubReport(int ReportID, int MReportID)
        {
                SubReportForm bf = null;
                try
                {
                        SubReportDAO reportDao = SubReportDAOFactory.getDAO();
                        bf = reportDao.getSubReport(ReportID, MReportID);
                }
                catch (SubReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return bf;
        }

        /**
         * Wrapping the get reportList method for JSP and  the other modules
         *
         * @param ReportID RalationID CategoryID UserID
         * @return the report list according to the catalog
         */
        public List getSubReportList(int ReportID)
        {
                List reportList = null;
                try
                {
                        SubReportDAO reportDao = SubReportDAOFactory.getDAO();
                        reportList = reportDao.getSubReportList(ReportID);
                }
                catch (SubReportDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return reportList;
        }


}