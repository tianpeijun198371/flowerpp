/**
 * Created by IntelliJ IDEA.
 * SubReport: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.subreport.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.report.custom.subreport.exceptions.SubReportDAOSysException;
import com.ulearning.ulms.tools.report.custom.subreport.form.SubReportForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SubReportDAOImpl implements SubReportDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public void addSubReport(SubReportForm details) throws SubReportDAOSysException
        {

        }

        public void updateSubReport(SubReportForm details) throws SubReportDAOSysException
        {

        }

        /**
         * Remove the report from database by the subReportID
         *
         * @param ReportID MReportID
         * @throws SubReportDAOSysException
         */
        public void removeSubReport(int ReportID, int MReportID) throws SubReportDAOSysException
        {
                String sqlStr = "delete R_CustomSubReport_Tab where ReportID = " + ReportID + " and MReportID=" + MReportID;
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new SubReportDAOSysException("SQLException while updating " + "report; Serial = " + ReportID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        public void removeSubReport(int ReportID) throws SubReportDAOSysException
        {
                String sqlStr = "delete R_CustomSubReport_Tab where ReportID = " + ReportID;
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new SubReportDAOSysException("SQLException while updating " + "report; Serial = " + ReportID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * Get the report info via the unique subReportID
         *
         * @param ReportID MReportID
         * @return the prepared reportForm, default is null
         * @throws SubReportDAOSysException
         */
        public SubReportForm getSubReport(int ReportID, int MReportID) throws SubReportDAOSysException
        {
                SubReportForm bf = null;
                String sqlStr = "select * from R_CustomSubReport_Tab where ReportID = " + ReportID + " and MReportID=" + MReportID;
                try
                {
                        LogUtil.debug("system", "[SubReportDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        if (rs.next())
                        {
                                bf = convertRs2Form(rs);
                        }
                }
                catch (SQLException se)
                {
                        throw new SubReportDAOSysException("SQLException while getSubReport " + "report; Serial = " + ReportID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new SubReportDAOSysException("Exception while getSubReport " + "report; Serial = " + ReportID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return bf;
        }

        /**
         * Get the report list by the catalogID
         *
         * @param ReportID
         * @return The prepared arraylist object,default size is 0
         * @throws SubReportDAOSysException
         */
        public List getSubReportList(int ReportID) throws SubReportDAOSysException
        {
                SubReportForm bf = null;
                ArrayList reportList = new ArrayList();
                String sqlStr = "select * from R_CustomSubReport_Tab where ReportID = " + ReportID;
                try
                {
                        LogUtil.debug("system", "[SubReportDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                bf = convertRs2Form(rs);
                                reportList.add(bf);
                        }

                }
                catch (SQLException se)
                {
                        throw new SubReportDAOSysException("SQLException while getSubReportList sql=" + sqlStr + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new SubReportDAOSysException("SQLException while getSubReportList sql=" + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return reportList;
        }


        /**
         * Convert the resultSet object to reportForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted reportForm
         */
        private SubReportForm convertRs2Form(ResultSet rs)
        {
                SubReportForm bf = new SubReportForm();
                int rsIndex = 1;
                try
                {
                        bf.setReportID(rs.getInt(rsIndex++));
                        bf.setMReportID(rs.getInt(rsIndex++));
                        bf.setReportName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setState(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return bf;
        }
}
