/**
 * Created by IntelliJ IDEA.
 * CustomFieldItem: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.fieldItem.form;

import org.apache.struts.action.ActionForm;


public class CustomFieldItemForm extends ActionForm
{
        private int customFieldItemID = 0;
        private int MReportID = 0;
        private int reportID = 0;
        private int fieldItemID = 0;
        private String newItemName = null;
        private int sequence = 0;

        public int getCustomFieldItemID()
        {
                return customFieldItemID;
        }

        public void setCustomFieldItemID(int customFieldItemID)
        {
                this.customFieldItemID = customFieldItemID;
        }

        public int getMReportID()
        {
                return MReportID;
        }

        public void setMReportID(int MReportID)
        {
                this.MReportID = MReportID;
        }

        public int getReportID()
        {
                return reportID;
        }

        public void setReportID(int reportID)
        {
                this.reportID = reportID;
        }

        public int getFieldItemID()
        {
                return fieldItemID;
        }

        public void setFieldItemID(int fieldItemID)
        {
                this.fieldItemID = fieldItemID;
        }

        public String getNewItemName()
        {
                return newItemName;
        }

        public void setNewItemName(String newItemName)
        {
                this.newItemName = newItemName;
        }

        public int getSequence()
        {
                return sequence;
        }

        public void setSequence(int sequence)
        {
                this.sequence = sequence;
        }

}