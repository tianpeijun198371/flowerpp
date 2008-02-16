package com.ulearning.ulms.tools.report.custom.task.bean;

import com.ulearning.ulms.tools.report.custom.task.exceptions.TaskDAOSysException;
import com.ulearning.ulms.tools.report.custom.task.form.TaskForm;
import com.ulearning.ulms.tools.report.custom.task.dao.TaskDAO;
import com.ulearning.ulms.tools.report.custom.task.dao.TaskDAOFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 15:31:54
 * To change this template use File | Settings | File Templates.
 */
public class TaskHelper
{
        public List getTaskFormList(int reportID) throws TaskDAOSysException
        {
                List taskList = null;
                try
                {
                        TaskDAO dao = TaskDAOFactory.getDAO();
                        taskList = dao.getTaskFormList(reportID);
                }
                catch (TaskDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
                return taskList;

        }

        public TaskForm getTask(int taskID) throws TaskDAOSysException
        {
                TaskForm tf = null;
                try
                {
                        TaskDAO dao = TaskDAOFactory.getDAO();
                        tf = dao.getTask(taskID);
                }
                catch (TaskDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
                return tf;

        }
}
