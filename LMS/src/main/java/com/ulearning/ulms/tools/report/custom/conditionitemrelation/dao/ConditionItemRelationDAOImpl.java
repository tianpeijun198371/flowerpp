package com.ulearning.ulms.tools.report.custom.conditionitemrelation.dao;

import com.ulearning.ulms.tools.report.custom.conditionitemrelation.form.ConditionItemRelationForm;
import com.ulearning.ulms.tools.report.custom.conditionitemrelation.exceptions.ConditionItemRelationDAOSysException;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-21
 * Time: 11:19:31
 * To change this template use File | Settings | File Templates.
 */
public class ConditionItemRelationDAOImpl implements ConditionItemRelationDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public void insertConditionItemRelation(ConditionItemRelationForm crf) throws ConditionItemRelationDAOSysException
        {

                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void updateConditionItemRelation(ConditionItemRelationForm crf) throws ConditionItemRelationDAOSysException
        {
                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void deleteConditionItemRelation(int reportID) throws ConditionItemRelationDAOSysException
        {
                String sqlStr = "delete from R_CONDITIONITEMRELATION_TAB where ReportID = " + reportID;
                try
                {
                        LogUtil.debug("system", "[ConditionItemRelationDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new ConditionItemRelationDAOSysException("SQLException while delete " + "ConditionItemRelation; ReprotID = " + reportID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                //To change body of implemented methods use File | Settings | File Templates.
        }

        public String getConditionItemRelation(int reportID) throws ConditionItemRelationDAOSysException
        {
                String sqlStr = "select * from R_CONDITIONITEMRELATION_TAB where ReportID = " + reportID;
                String relation = null;
                try
                {
                        LogUtil.debug("system", "[ConditionItemRelationDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        if (rs.next())
                        {
                                relation = rs.getString("Relation");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ConditionItemRelationDAOSysException("SQLException while quering " + "ConditionItemRelation; ReportID = " + reportID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ConditionItemRelationDAOSysException("SQLException while quering " + "ConditionItemRelation; ReportID = " + reportID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return relation;
        }

}
