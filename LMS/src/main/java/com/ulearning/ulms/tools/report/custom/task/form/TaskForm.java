package com.ulearning.ulms.tools.report.custom.task.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 14:25:53
 * To change this template use File | Settings | File Templates.
 */
public class TaskForm extends ActionForm
{
        private int taskID;
        private int reportID;
        private String taskType;
        private String email;
        private String state;

        public TaskForm()
        {
        }

        public int getTaskID()
        {
                return taskID;
        }

        public void setTaskID(int taskID)
        {
                this.taskID = taskID;
        }

        public int getReportID()
        {
                return reportID;
        }

        public void setReportID(int reportID)
        {
                this.reportID = reportID;
        }

        public String getTaskType()
        {
                return taskType;
        }

        public void setTaskType(String taskType)
        {
                this.taskType = taskType;
        }

        public String getEmail()
        {
                return email;
        }

        public void setEmail(String email)
        {
                this.email = email;
        }

        public String getState()
        {
                return state;
        }

        public void setState(String state)
        {
                this.state = state;
        }

}
