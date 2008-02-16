/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.report.dao;

import com.ulearning.ulms.tools.report.custom.report.form.ReportForm;
import com.ulearning.ulms.tools.report.custom.report.exceptions.ReportDAOSysException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ReportDAO
{

        public int addReport(ReportForm details) throws ReportDAOSysException;

        public void updateReport(ReportForm details) throws ReportDAOSysException;

        public void removeReport(String reportID) throws ReportDAOSysException;

        public ReportForm getReport(int reportID) throws ReportDAOSysException;

        public List getReportList(int ModulID, int RalationID, int CategoryID, int UserID) throws ReportDAOSysException;

        public List getReportList(int ModulID, int RalationID, int UserID) throws ReportDAOSysException;

        public void insertReportField(HttpServletRequest request, int reportID) throws ReportDAOSysException;

        public void updateReportField(HttpServletRequest request, int reportID) throws ReportDAOSysException;

        public void updateReportTask(HttpServletRequest request, int reportID) throws ReportDAOSysException;

}
