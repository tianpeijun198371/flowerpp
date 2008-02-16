package com.ulearning.ulms.tools.report.custom.task.dao;

import com.ulearning.ulms.tools.report.custom.task.form.TaskForm;
import com.ulearning.ulms.tools.report.custom.task.exceptions.TaskDAOSysException;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.exceptions.ULMSException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 14:38:19
 * To change this template use File | Settings | File Templates.
 */
public class TaskDAOOracle extends TaskDAOImpl
{
        OperateDB operateDB = new OperateDB();

        public int insertTask(TaskForm tf) throws TaskDAOSysException
        {
                String sqlStr = "insert into R_TASK_TAB values(" +
                        "TaskID.nextval," +
                        tf.getReportID() + ",'" +
                        tf.getTaskType() + "','" +
                        tf.getEmail() + "','" +
                        tf.getState() + "')";
                if (operateDB == null)
                {
                        operateDB = new OperateDB();
                }

                try
                {
                        LogUtil.debug("system", "[TasktDAOOracel]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new TaskDAOSysException("SQLException while insert a task " + "TaskID = " + tf.getTaskID() + " :\n" + se);
                }

                int taskID = 0;
                sqlStr = "select max(taskID) from R_TASK_TAB where reportID=" + tf.getReportID();
                if (operateDB == null)
                {
                        operateDB = new OperateDB();
                }
                try
                {
                        LogUtil.debug("system", "[TaskDAOImpl]===================the sql string is :" + sqlStr);
                        ResultSet rs = operateDB.exequery(sqlStr);
                        if (rs.next())
                        {
                                taskID = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new TaskDAOSysException("SQLException while insertTask sql = " + sqlStr + " :\n" + se);

                }
                catch (ULMSException se)
                {
                        se.printStackTrace();
                        throw new TaskDAOSysException("SQLException while insertTask sql = " + sqlStr + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                operateDB.closeDB();
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                        }
                }
                return taskID;
        }

        public void updateTask(TaskForm tf) throws TaskDAOSysException
        {
                String sqlStr = "update R_TASK_TAB set " +
                        "ReportID = " + tf.getReportID() + "," +
                        "TaskType ='" + tf.getTaskType() + "'," +
                        "EMail = '" + tf.getEmail() + "'," +
                        "State = '" + tf.getState() + "'" +
                        " where TaskID = " + tf.getTaskID();

                if (operateDB == null)
                {
                        operateDB = new OperateDB();
                }

                try
                {
                        LogUtil.debug("system", "[TasktDAOOracel]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new TaskDAOSysException("SQLException while updating " + "TaskID = " + tf.getTaskID() + " :\n" + se);
                }
        }

}
