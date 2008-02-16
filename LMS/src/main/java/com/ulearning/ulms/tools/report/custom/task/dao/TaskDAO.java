package com.ulearning.ulms.tools.report.custom.task.dao;

import com.ulearning.ulms.tools.report.custom.task.form.TaskForm;
import com.ulearning.ulms.tools.report.custom.task.exceptions.TaskDAOSysException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 14:29:22
 * To change this template use File | Settings | File Templates.
 */
public interface TaskDAO
{
        public int insertTask(TaskForm tf) throws TaskDAOSysException;

        public void updateTask(TaskForm tf) throws TaskDAOSysException;

        public void deleteTask(int taskID) throws TaskDAOSysException;

        public void deleteTaskReport(int reportID) throws TaskDAOSysException;

        public List getTaskFormList(int reportID) throws TaskDAOSysException;

        public TaskForm getTask(int taskID) throws TaskDAOSysException;

}
