package com.ulearning.ulms.tools.report.custom.conditionitem.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-19
 * Time: 14:10:09
 * To change this template use File | Settings | File Templates.
 */
public class CustomConditionItemForm extends ActionForm
{
        private int customConditionItemID;
        private int reportID;
        private int conditionItemID;
        private String conditionValue;
        private String relationSymbol;

        public int getCustomConditionItemID()
        {
                return customConditionItemID;
        }

        public void setCustomConditionItemID(int conditinItemID)
        {
                this.customConditionItemID = conditinItemID;
        }

        public int getReportID()
        {
                return reportID;
        }

        public void setReportID(int reportID)
        {
                this.reportID = reportID;
        }

        public int getConditionItemID()
        {
                return conditionItemID;
        }

        public void setConditionItemID(int conditionItemID)
        {
                this.conditionItemID = conditionItemID;
        }

        public String getConditionValue()
        {
                return conditionValue;
        }

        public void setConditionValue(String conditionValue)
        {
                this.conditionValue = conditionValue;
        }

        public String getRelationSymbol()
        {
                return relationSymbol;
        }

        public void setRelationSymbol(String relationSymbol)
        {
                this.relationSymbol = relationSymbol;
        }

        public CustomConditionItemForm()
        {
        }
}
