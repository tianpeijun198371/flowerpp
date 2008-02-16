package com.ulearning.ulms.tools.report.custom.tasktime.model;

import org.apache.struts.action.ActionForm;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 17:00:33
 * To change this template use File | Settings | File Templates.
 */
public class TaskTimeForm extends ActionForm
{
        private int taskTimeID;
        private int taskID;
        private Date taskTime;
        private String taskTimeStr;
        private String notExecuteCondition;
        private TaskTimeModel taskTimeModel = null;

        public TaskTimeModel getTaskTimeModel()
        {
                taskTimeModel = new TaskTimeModel();
                taskTimeModel.setTaskTimeID(this.taskTimeID);
                taskTimeModel.setTaskID(this.taskID);
                taskTimeModel.setTaskTime(this.taskTime);
                taskTimeModel.setNotExecuteCondition(this.notExecuteCondition);
                return taskTimeModel;
        }

        public TaskTimeForm getTaskTimeForm(TaskTimeModel taskTimeModel)
        {
                TaskTimeForm taskTimeForm = new TaskTimeForm();
                taskTimeForm.setTaskTimeID(taskTimeModel.getTaskTimeID());
                taskTimeForm.setTaskID(taskTimeModel.getTaskID());
                taskTimeForm.setTaskTime(taskTimeModel.getTaskTime());
                taskTimeForm.setNotExecuteCondition(taskTimeModel.getNotExecuteCondition());
                return taskTimeForm;
        }

        public int getTaskTimeID()
        {
                return taskTimeID;
        }

        public void setTaskTimeID(int taskTimeID)
        {
                this.taskTimeID = taskTimeID;
        }

        public int getTaskID()
        {
                return taskID;
        }

        public void setTaskID(int taskID)
        {
                this.taskID = taskID;
        }

        public Date getTaskTime()
        {
                return taskTime;
        }

        public void setTaskTime(Date taskTime)
        {
                this.taskTime = taskTime;
        }

        public String getTaskTimeStr()
        {
                return taskTimeStr;
        }

        public void setTaskTimeStr(String taskTimeStr)
        {
                this.taskTimeStr = taskTimeStr;
        }


        public String getNotExecuteCondition()
        {
                return notExecuteCondition;
        }

        public void setNotExecuteCondition(String notExecuteCondition)
        {
                this.notExecuteCondition = notExecuteCondition;
        }

}
