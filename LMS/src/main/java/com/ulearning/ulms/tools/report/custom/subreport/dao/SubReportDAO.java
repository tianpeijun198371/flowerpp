/**
 * Created by IntelliJ IDEA.
 * SubReport: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.subreport.dao;

import com.ulearning.ulms.tools.report.custom.subreport.form.SubReportForm;
import com.ulearning.ulms.tools.report.custom.subreport.exceptions.SubReportDAOSysException;

import java.util.List;


public interface SubReportDAO
{

        public void addSubReport(SubReportForm details) throws SubReportDAOSysException;

        public void updateSubReport(SubReportForm details) throws SubReportDAOSysException;

        public void removeSubReport(int ReportID, int MReport) throws SubReportDAOSysException;

        public void removeSubReport(int ReportID) throws SubReportDAOSysException;

        public SubReportForm getSubReport(int ReportID, int MReport) throws SubReportDAOSysException;

        public List getSubReportList(int ReportID) throws SubReportDAOSysException;

}
