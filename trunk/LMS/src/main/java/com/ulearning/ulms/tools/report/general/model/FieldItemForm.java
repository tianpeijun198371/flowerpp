/** * CategoryForm.java. 
 * User: xiejh  Date: 2004-7-19 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */

package com.ulearning.ulms.tools.report.general.model;

import org.apache.struts.action.ActionForm;

public class FieldItemForm extends ActionForm
{
        private int fieldItemID = 0;
        private int mReportID = 0;
        private String itemName = null;
        private String tableName = null;
        private String fieldName = null;
        private String state = null;

        public int getFieldItemID()
        {
                return fieldItemID;
        }

        public void setFieldItemID(int fieldItemID)
        {
                this.fieldItemID = fieldItemID;
        }

        public int getMReportID()
        {
                return mReportID;
        }

        public void setMReportID(int mReportID)
        {
                this.mReportID = mReportID;
        }

        public String getItemName()
        {
                return itemName;
        }

        public void setItemName(String itemName)
        {
                this.itemName = itemName;
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

        public String getState()
        {
                return state;
        }

        public void setState(String state)
        {
                this.state = state;
        }

}