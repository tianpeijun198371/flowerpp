package com.ulearning.ulms.tools.report.custom.tasktime.dao;

import com.ulearning.ulms.tools.report.custom.tasktime.exceptions.TaskTimeDAOSysException;
import com.ulearning.ulms.tools.report.custom.tasktime.model.TaskTimeForm;

import java.util.List;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 17:06:07
 * To change this template use File | Settings | File Templates.
 */
public interface TaskTimeDAO
{
        public Serializable insertTaskTime(TaskTimeForm ttf) throws TaskTimeDAOSysException;

        public void updateTaskTime(TaskTimeForm ttf) throws TaskTimeDAOSysException;

        public void deleteTaskTime(int taskTimeID) throws TaskTimeDAOSysException;

        public List getTaskTimeFormList(int taskID) throws TaskTimeDAOSysException;

        public TaskTimeForm getTaskTime(int taskTimeID) throws TaskTimeDAOSysException;

}
