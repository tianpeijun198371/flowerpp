/**
 * Created by IntelliJ IDEA.
 * SubReport: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.subreport.dao;

import com.ulearning.ulms.tools.report.custom.subreport.exceptions.SubReportDAOSysException;
import com.ulearning.ulms.tools.report.custom.subreport.form.SubReportForm;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import java.sql.Statement;


public class SubReportDAOOracle extends SubReportDAOImpl
{

        /**
         * Insert a new report record to database
         *
         * @param reportForm the value object to be added
         * @throws SubReportDAOSysException
         */
        public void addSubReport(SubReportForm reportForm) throws SubReportDAOSysException
        {
                String sqlStr = "insert into R_CustomSubReport_Tab values(" +
                        reportForm.getReportID() + "," +
                        reportForm.getMReportID() + ",'" +
                        reportForm.getReportName() + "','" +
                        reportForm.getState() + "')";

                OperateDB operateDB = new OperateDB();
                try
                {
                        LogUtil.debug("system", "[SubReportDAOOracle]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);

                }
                catch (ULMSSysException se)
                {
                        throw new SubReportDAOSysException("SQLException while addSubReport: sql = " + sqlStr + " :\n" + se);
                }

        }

        /**
         * Update reportInfo by the new Form
         *
         * @param reportForm value object for changed
         * @throws SubReportDAOSysException
         */
        public void updateSubReport(SubReportForm reportForm) throws SubReportDAOSysException
        {
                String sqlStr = "update R_CustomSubReport_Tab set " +
                        "ReportName = '" + reportForm.getReportName() + "'," +
                        "State = '" + reportForm.getState() + "'" +
                        "where ReportID = " + reportForm.getReportID() +
                        " and MReportID = " + reportForm.getMReportID();
                OperateDB operateDB = new OperateDB();
                try
                {
                        LogUtil.debug("system", "[SubReportDAOOracle]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);

                }
                catch (ULMSSysException se)
                {
                        throw new SubReportDAOSysException("SQLException while updateSubReport sql = " + sqlStr + " :\n" + se);
                }
        }


}
