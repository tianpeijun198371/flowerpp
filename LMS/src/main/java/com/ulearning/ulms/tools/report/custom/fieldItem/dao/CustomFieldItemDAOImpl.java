/**
 * Created by IntelliJ IDEA.
 * CustomFieldItem: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.fieldItem.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.report.custom.fieldItem.exceptions.CustomFieldItemDAOSysException;
import com.ulearning.ulms.tools.report.custom.fieldItem.form.CustomFieldItemForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomFieldItemDAOImpl implements CustomFieldItemDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public void addCustomFieldItem(CustomFieldItemForm details) throws CustomFieldItemDAOSysException
        {

        }

        public void updateCustomFieldItem(CustomFieldItemForm details) throws CustomFieldItemDAOSysException
        {

        }

        /**
         * Remove the fieldItem from database by the subCustomFieldItemID
         *
         * @param ReportID
         * @throws CustomFieldItemDAOSysException
         */
        public void removeCustomFieldItem(int ReportID, int MReportID, int FieldItemID) throws CustomFieldItemDAOSysException
        {
                String sqlStr = "delete R_CustomFieldItem_Tab where"
                        + " ReportID = " + ReportID
                        + " and MReportID=" + MReportID
                        + " and FieldItemID=" + FieldItemID;
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new CustomFieldItemDAOSysException("SQLException while removeCustomFieldItem  sql = " + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }


        /**
         * Remove the fieldItem from database by the subCustomFieldItemID
         *
         * @param ReportID
         * @throws CustomFieldItemDAOSysException
         */
        public void removeCustomFieldItem(int ReportID, int MReport) throws CustomFieldItemDAOSysException
        {
                String sqlStr = "delete R_CustomFieldItem_Tab where ReportID = " + ReportID
                        + " and MReportID=" + MReport;
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new CustomFieldItemDAOSysException("SQLException while removeCustomFieldItem  sql = " + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        public void removeCustomFieldItem(int ReportID) throws CustomFieldItemDAOSysException
        {
                String sqlStr = "delete R_CustomFieldItem_Tab where ReportID = " + ReportID;
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new CustomFieldItemDAOSysException("SQLException while removeCustomFieldItem  sql = " + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * Get the fieldItem list by the catalogID
         *
         * @param ReportID MReportID
         * @return The prepared arraylist object,default size is 0
         * @throws CustomFieldItemDAOSysException
         */
        public List getCustomFieldItemList(int ReportID, int MReportID) throws CustomFieldItemDAOSysException
        {
                CustomFieldItemForm bf = null;
                ArrayList fieldItemList = new ArrayList();
                String sqlStr = "select * from R_CustomFieldItem_Tab where ReportID = "
                        + ReportID + " and MReportID=" + MReportID;
                try
                {
                        LogUtil.debug("system", "[CustomFieldItemDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                bf = convertRs2Form(rs);
                                fieldItemList.add(bf);
                        }

                }
                catch (SQLException se)
                {
                        throw new CustomFieldItemDAOSysException("SQLException while getCustomFieldItemList sql=" + sqlStr + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new CustomFieldItemDAOSysException("SQLException while getCustomFieldItemList sql=" + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return fieldItemList;
        }

        /**
         * Get the fieldItem list by the catalogID
         *
         * @param ReportID MReportID
         * @return The prepared arraylist object,default size is 0
         * @throws CustomFieldItemDAOSysException
         */
        public List getCustomFieldItemList(int ReportID) throws CustomFieldItemDAOSysException
        {
                CustomFieldItemForm bf = null;
                ArrayList fieldItemList = new ArrayList();
                String sqlStr = "select * from R_CustomFieldItem_Tab where ReportID = " + ReportID;
                try
                {
                        LogUtil.debug("system", "[CustomFieldItemDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                bf = convertRs2Form(rs);
                                fieldItemList.add(bf);
                        }

                }
                catch (SQLException se)
                {
                        throw new CustomFieldItemDAOSysException("SQLException while getCustomFieldItemList sql=" + sqlStr + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new CustomFieldItemDAOSysException("SQLException while getCustomFieldItemList sql=" + sqlStr + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return fieldItemList;
        }


        /**
         * Convert the resultSet object to fieldItemForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted fieldItemForm
         */
        private CustomFieldItemForm convertRs2Form(ResultSet rs)
        {
                CustomFieldItemForm bf = new CustomFieldItemForm();
                int rsIndex = 1;
                try
                {
                        bf.setCustomFieldItemID(rs.getInt(rsIndex++));
                        bf.setReportID(rs.getInt(rsIndex++));
                        bf.setMReportID(rs.getInt(rsIndex++));
                        bf.setFieldItemID(rs.getInt(rsIndex++));
                        bf.setNewItemName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        bf.setSequence(rs.getInt(rsIndex++));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return bf;
        }
}
