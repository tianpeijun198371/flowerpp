/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.general.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.report.general.exceptions.GeneralDAOSysException;
import com.ulearning.ulms.tools.report.general.model.*;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GeneralDAOImpl implements GeneralDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public List getCategoryFormList(int ModulID) throws GeneralDAOSysException
        {
                CategoryForm bf = null;
                ArrayList formList = new ArrayList();
                String sqlStr = "select * from R_Category_Tab where ModulID = " + ModulID;
                try
                {
                        LogUtil.debug("system", "[GeneralDAOImpl]============the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                bf = convertRs2CategoryForm(rs);
                                formList.add(bf);
                        }

                }
                catch (SQLException se)
                {
                        throw new GeneralDAOSysException("SQLException while getCategoryFormList sql=" + sqlStr + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new GeneralDAOSysException("SQLException while getCategoryFormList sql=" + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return formList;
        }

        public List getReportTypeFormList(int CategoryID) throws GeneralDAOSysException
        {
                ReportTypeForm bf = null;
                ArrayList formList = new ArrayList();
                String sqlStr = "select * from R_ReportType_Tab where CategoryID = " + CategoryID;
                try
                {
                        LogUtil.debug("system", "[GeneralDAOImpl]============the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                bf = convertRs2ReportTypeForm(rs);
                                formList.add(bf);
                        }

                }
                catch (SQLException se)
                {
                        throw new GeneralDAOSysException("SQLException while getReportTypeFormList sql=" + sqlStr + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new GeneralDAOSysException("SQLException while getReportTypeFormList sql=" + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return formList;
        }

        public List getMReportFormList(String ReportType) throws GeneralDAOSysException
        {
                MReportForm bf = null;
                ArrayList formList = new ArrayList();
                String sqlStr = "select * from R_MReport_Tab where ReportType = " + ReportType;
                try
                {
                        LogUtil.debug("system", "[GeneralDAOImpl]============the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                bf = convertRs2MReportForm(rs);
                                formList.add(bf);
                        }

                }
                catch (SQLException se)
                {
                        throw new GeneralDAOSysException("SQLException while getMReportFormList sql=" + sqlStr + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new GeneralDAOSysException("SQLException while getMReportFormList sql=" + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return formList;
        }


        /**
         * Convert the resultSet object to CategoryForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted reportForm
         */
        private CategoryForm convertRs2CategoryForm(ResultSet rs)
        {
                CategoryForm bf = new CategoryForm();
                int rsIndex = 1;
                try
                {
                        bf.setCategoryID(rs.getInt(rsIndex++));
                        bf.setModulID(rs.getInt(rsIndex++));
                        bf.setCategoryName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setState(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return bf;
        }

        public List getFieldItemFormList(int MReportID) throws GeneralDAOSysException
        {
                FieldItemForm bf = null;
                ArrayList formList = new ArrayList();
                String sqlStr = "select * from R_FieldItem_Tab where MReportID = " + MReportID;
                try
                {
                        LogUtil.debug("system", "[GeneralDAOImpl]============the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                bf = convertRs2FieldItemForm(rs);
                                formList.add(bf);
                        }

                }
                catch (SQLException se)
                {
                        throw new GeneralDAOSysException("SQLException while getFieldItemFormList sql=" + sqlStr + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new GeneralDAOSysException("SQLException while getFieldItemFormList sql=" + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return formList;
        }

        public List getConditionItemFormList(String ReportType) throws GeneralDAOSysException
        {
                ConditionItemForm bf = null;
                ArrayList formList = new ArrayList();
                String sqlStr = "select * from R_ConditionItem_Tab where ReportType = " + ReportType;
                try
                {
                        LogUtil.debug("system", "[GeneralDAOImpl]============the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                bf = convertRs2ConditionItemForm(rs);
                                formList.add(bf);
                        }

                }
                catch (SQLException se)
                {
                        throw new GeneralDAOSysException("SQLException while getConditionItemFormList sql=" + sqlStr + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new GeneralDAOSysException("SQLException while getConditionItemFormList sql=" + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return formList;
        }

        /**
         * Convert the resultSet object to ReportTypeForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted reportForm
         */
        private ReportTypeForm convertRs2ReportTypeForm(ResultSet rs)
        {
                ReportTypeForm bf = new ReportTypeForm();
                int rsIndex = 1;
                try
                {
                        bf.setReportType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setReportTypeName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setCategoryID(rs.getInt(rsIndex++));
                        bf.setReportTypeDetail(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setJasperReportXMLPath(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setState(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return bf;
        }

        /**
         * Convert the resultSet object to MReportForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted reportForm
         */
        private MReportForm convertRs2MReportForm(ResultSet rs)
        {
                MReportForm bf = new MReportForm();
                int rsIndex = 1;
                try
                {
                        bf.setMReportID(rs.getInt(rsIndex++));
                        bf.setParentID(rs.getInt(rsIndex++));
                        bf.setReportType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setMReportName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setState(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return bf;
        }


        /**
         * Convert the resultSet object to FieldItemForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted reportForm
         */
        private FieldItemForm convertRs2FieldItemForm(ResultSet rs)
        {
                FieldItemForm bf = new FieldItemForm();
                int rsIndex = 1;
                try
                {
                        bf.setFieldItemID(rs.getInt(rsIndex++));
                        bf.setMReportID(rs.getInt(rsIndex++));
                        bf.setItemName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setTableName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setFieldName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setState(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return bf;
        }

        /**
         * Convert the resultSet object to ConditionItemForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted reportForm
         */
        private ConditionItemForm convertRs2ConditionItemForm(ResultSet rs)
        {
                ConditionItemForm bf = new ConditionItemForm();
                int rsIndex = 1;
                try
                {
                        bf.setConditionItemID(rs.getInt(rsIndex++));
                        bf.setReportType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setConditionType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setConditionName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setTableName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setFieldName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setFieldType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setState(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return bf;
        }
}
