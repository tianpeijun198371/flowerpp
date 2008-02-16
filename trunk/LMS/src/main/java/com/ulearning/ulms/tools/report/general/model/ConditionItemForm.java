/** * CategoryForm.java. 
 * User: xiejh  Date: 2004-7-19 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */

package com.ulearning.ulms.tools.report.general.model;

import org.apache.struts.action.ActionForm;

public class ConditionItemForm extends ActionForm
{
        private int conditionItemID = 0;
        private String reportType = null;
        private String conditionType = null;
        private String conditionName = null;
        private String tableName = null;
        private String fieldName = null;
        private String fieldType = null;
        private String state = null;

        public int getConditionItemID()
        {
                return conditionItemID;
        }

        public void setConditionItemID(int conditionItemID)
        {
                this.conditionItemID = conditionItemID;
        }

        public String getReportType()
        {
                return reportType;
        }

        public void setReportType(String reportType)
        {
                this.reportType = reportType;
        }

        public String getConditionType()
        {
                return conditionType;
        }

        public void setConditionType(String conditionType)
        {
                this.conditionType = conditionType;
        }

        public String getConditionName()
        {
                return conditionName;
        }

        public void setConditionName(String conditionName)
        {
                this.conditionName = conditionName;
        }

        public String getTableName()
        {
                return tableName;
        }

        public void setTableName(String tableName)
        {
                this.tableName = tableName;
        }

        public String getFieldName()
        {
                return fieldName;
        }

        public void setFieldName(String fieldName)
        {
                this.fieldName = fieldName;
        }

        public String getFieldType()
        {
                return fieldType;
        }

        public void setFieldType(String fieldType)
        {
                this.fieldType = fieldType;
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