package com.ulearning.ulms.tools.report.custom.tasktime.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Hibernate CodeGenerator
 */
public class TaskTimeModel implements Serializable
{

        /**
         * identifier field
         */
        private int taskTimeID;

        private int taskID;

        /**
         * nullable persistent field
         */
        private Date taskTime;

        /**
         * nullable persistent field
         */
        private String notExecuteCondition;

        /**
         * full constructor
         */
        public TaskTimeModel(Date taskTime, String notExecuteCondition)
        {
                this.taskTime = taskTime;
                this.notExecuteCondition = notExecuteCondition;
        }

        /**
         * default constructor
         */
        public TaskTimeModel()
        {
        }

        public int getTaskTimeID()
        {
                return this.taskTimeID;
        }

        public void setTaskTimeID(int taskTimeID)
        {
                this.taskTimeID = taskTimeID;
        }

        public Date getTaskTime()
        {
                return this.taskTime;
        }

        public void setTaskTime(Date taskTime)
        {
                this.taskTime = taskTime;
        }

        public String getNotExecuteCondition()
        {
                return this.notExecuteCondition;
        }

        public void setNotExecuteCondition(String notExecuteCondition)
        {
                this.notExecuteCondition = notExecuteCondition;
        }

        public int getTaskID()
        {
                return taskID;
        }

        public void setTaskID(int taskID)
        {
                this.taskID = taskID;
        }

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("taskTimeID", getTaskTimeID())
                        .toString();
        }

}
