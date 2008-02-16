package com.ulearning.ulms.tools.report.custom.tasktime.bean;

import com.ulearning.ulms.tools.report.custom.tasktime.exceptions.TaskTimeDAOSysException;
import com.ulearning.ulms.tools.report.custom.tasktime.dao.TaskTimeDAOFactory;
import com.ulearning.ulms.tools.report.custom.tasktime.dao.TaskTimeDAO;
import com.ulearning.ulms.tools.report.custom.tasktime.model.TaskTimeForm;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 17:39:41
 * To change this template use File | Settings | File Templates.
 */
public class TaskTimeHelper
{
        public List getTaskTimeFormList(int reportID) throws TaskTimeDAOSysException
        {
                List taskTimeList = null;
                try
                {
                        TaskTimeDAO dao = TaskTimeDAOFactory.getDAO();
                        taskTimeList = dao.getTaskTimeFormList(reportID);
                }
                catch (TaskTimeDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
                return taskTimeList;

        }

        public TaskTimeForm getTaskTime(int taskTimeID) throws TaskTimeDAOSysException
        {
                TaskTimeForm ttf = null;
                try
                {
                        TaskTimeDAO dao = TaskTimeDAOFactory.getDAO();
                        ttf = dao.getTaskTime(taskTimeID);
                }
                catch (TaskTimeDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
                return ttf;

        }
}
