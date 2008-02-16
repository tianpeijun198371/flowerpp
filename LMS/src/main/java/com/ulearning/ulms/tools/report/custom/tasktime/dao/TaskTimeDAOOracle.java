package com.ulearning.ulms.tools.report.custom.tasktime.dao;

import com.ulearning.ulms.tools.report.custom.tasktime.exceptions.TaskTimeDAOSysException;
import com.ulearning.ulms.tools.report.custom.tasktime.model.TaskTimeForm;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.core.exceptions.ULMSSysException;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 17:05:45
 * To change this template use File | Settings | File Templates.
 */
public class TaskTimeDAOOracle extends TaskTimeDAOImpl
{
        /*
        public void  insertTaskTime (TaskTimeForm ttf) throws TaskTimeDAOSysException
        {
                String sqlStr="insert into R_TASKTIME_TAB values("+
                        "TaskTimeID.nextval,"+
                        ttf.getTaskID()+","+
                        "to_date('" + ttf.getTaskTimeStr()+ "','yyyy-mm-dd hh24:mi:ss'),'"+
                        ttf.getNotExecuteCondition()+"')";
                {
                }

                try
                {
                        LogUtil.debug("system","[TaskTimeDAOOracel]====================the sql string is : " + sqlStr)
                }
                catch (ULMSSysException se)
                {
                        throw new TaskTimeDAOSysException("SQLException while insert a tasktime " + "TaskID = "
                                + ttf.getTaskID() + " :\n" + se);
                }
        }

        public void  updateTaskTime (TaskTimeForm ttf) throws TaskTimeDAOSysException
        {
                String sqlStr="update R_TASKTIME_TAB set "+
                        "TaskID = "+ ttf.getTaskID()+","+
                        "TaskTime ="+ "to_date('" +ttf.getTaskTimeStr() + "','yyyy-mm-dd hh24:mi:ss'),"+
                        "NotExecuteCondition = '"+
                        ttf.getNotExecuteCondition()+"' "+
                        " where TaskTimeID = "+ttf.getTaskTimeID();
                {
                }

                try
                {
                        LogUtil.debug("system","[TaskTimeDAOOracel]====================the sql string is : " + sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new TaskTimeDAOSysException("SQLException while updating " + "TaskID = "
                                + ttf.getTaskTimeID() + " :\n" + se);
                }
        }
        */

}
