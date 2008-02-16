package com.ulearning.ulms.tools.report.custom.conditionitem.dao;

import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.tools.report.custom.conditionitem.exceptions.CustomConditionItemDAOSysException;
import com.ulearning.ulms.tools.report.custom.conditionitem.form.CustomConditionItemForm;
import com.ulearning.ulms.core.exceptions.*;
import com.ulearning.ulms.core.util.StringUtil;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-19
 * Time: 14:29:21
 * To change this template use File | Settings | File Templates.
 */
public class CustomConditionItemDAOImpl implements CustomConditionItemDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public void insertCustomConditionItem(CustomConditionItemForm ccf) throws CustomConditionItemDAOSysException
        {

        }

        public void updateCustomConditionitem(CustomConditionItemForm ccf) throws CustomConditionItemDAOSysException
        {
        }

        public void deleteCustomConditionItem(int reportID) throws CustomConditionItemDAOSysException
        {
                String sqlStr = "delete R_CustomCONDITIONITEM_TAB where  ReportId = " + reportID;
                try
                {
                        LogUtil.debug("system", "[CustomConditionItemDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new CustomConditionItemDAOSysException("SQLException while updating " + "reportID = " + reportID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        public void deleteCustomConditionItemPK(int customConditionItemID) throws CustomConditionItemDAOSysException
        {
                String sqlStr = "delete R_CUSTOMCONDITIONITEM_TAB where CustomConditionItemID = " + customConditionItemID;
                try
                {
                        LogUtil.debug("system", "[CustomConditionItemDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new CustomConditionItemDAOSysException("SQLException while updating " + "CustomConditionItemID = " + customConditionItemID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }


        }

        public List getCustomConditionItemFormList(int reportID) throws CustomConditionItemDAOSysException
        {
                CustomConditionItemForm ccf = null;
                ArrayList customConditonItemFormList = new ArrayList();
                String sqlStr = "select * from R_CUSTOMCONDITIONITEM_TAB where reportID = " + reportID;
                try
                {
                        LogUtil.debug("system", "[CustomConditionItemDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                ccf = convertRs2Form(rs);
                                customConditonItemFormList.add(ccf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CustomConditionItemDAOSysException("SQLException while get " + "CustomConditonItemForm; Serial reportID= " + reportID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CustomConditionItemDAOSysException("SQLException while updating " + "assignment; reportID = "
                                + reportID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return customConditonItemFormList;
        }

        private CustomConditionItemForm convertRs2Form(ResultSet rs)
        {
                CustomConditionItemForm ccf = new CustomConditionItemForm();
                int rsIndex = 1;
                try
                {

                        ccf.setCustomConditionItemID(rs.getInt(rsIndex++));
                        ccf.setReportID(rs.getInt(rsIndex++));
                        ccf.setConditionItemID(rs.getInt(rsIndex++));
                        ccf.setConditionValue(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        ccf.setRelationSymbol(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return ccf;
        }
}
