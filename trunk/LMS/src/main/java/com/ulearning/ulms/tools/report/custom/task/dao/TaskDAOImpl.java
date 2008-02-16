package com.ulearning.ulms.tools.report.custom.task.dao;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.report.custom.task.exceptions.TaskDAOSysException;
import com.ulearning.ulms.tools.report.custom.task.form.TaskForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 14:43:00
 * To change this template use File | Settings | File Templates.
 */
public class TaskDAOImpl implements TaskDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public int insertTask(TaskForm tf) throws TaskDAOSysException
        {
                //To change body of implemented methods use File | Settings | File Templates.
                return 0;
        }

        public void updateTask(TaskForm tf) throws TaskDAOSysException
        {
                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void deleteTask(int taskID) throws TaskDAOSysException
        {
                String sqlStr = "delete R_TASK_TAB where TaskID = " + taskID;
                try
                {
                        LogUtil.debug("system", "[TasktDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new TaskDAOSysException("SQLException while updating " + "TaskID = " + taskID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

        }

        public void deleteTaskReport(int reportID) throws TaskDAOSysException
        {
                String sqlStr = "delete R_TASK_TAB where ReportID = " + reportID;
                try
                {
                        LogUtil.debug("system", "[TasktDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new TaskDAOSysException("SQLException while deleteTaskReport reportID = "
                                + reportID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

        }

        public List getTaskFormList(int reportID) throws TaskDAOSysException
        {
                TaskForm tf = null;
                ArrayList taskList = new ArrayList();
                String sqlStr = "select * from R_TASK_TAB where ReportID = " + reportID;
                try
                {
                        LogUtil.debug("system", "[TaskDAOImpl]===================the sql string is :" + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        if (rs.next())
                        {
                                tf = convertRs2Form(rs);
                                taskList.add(tf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new TaskDAOSysException("SQLException while updating " + "taskID = " + reportID + " :\n" + se);

                }
                catch (ULMSException se)
                {
                        se.printStackTrace();
                        throw new TaskDAOSysException("SQLException while updating " + "taskID = " + reportID + " :\n" + se);

                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return taskList;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public TaskForm getTask(int taskID) throws TaskDAOSysException
        {
                TaskForm tf = null;
                String sqlStr = "select * from R_TASK_TAB where TaskID =" + taskID;
                try
                {
                        LogUtil.debug("system", "[TaskDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        if (rs.next())
                        {
                                tf = convertRs2Form(rs);

                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new TaskDAOSysException("SQLException while updating " + "taskID = " + taskID + " :\n" + se);
                }
                catch (ULMSException se)
                {
                        se.printStackTrace();
                        throw new TaskDAOSysException("SQLException while updating " + "taskID = " + taskID + " :\n" + se);

                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return tf;  //To change body of implemented methods use File | Settings | File Templates.
        }

        private TaskForm convertRs2Form(ResultSet rs)
        {
                TaskForm tf = new TaskForm();
                int rsIndex = 1;
                try
                {
                        tf.setTaskID(rs.getInt(rsIndex++));
                        tf.setReportID(rs.getInt(rsIndex++));
                        tf.setTaskType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        tf.setEmail(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        tf.setState(StringUtil.nullToStr(rs.getString(rsIndex++)));

                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                return tf;
        }

}
