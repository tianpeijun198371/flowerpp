package com.ulearning.ulms.tools.report.custom.conditionitemrelation.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-21
 * Time: 10:58:26
 * To change this template use File | Settings | File Templates.
 */
public class ConditionItemRelationForm extends ActionForm
{
        private int reportID;
        private String relation;

        public ConditionItemRelationForm()
        {
        }

        public int getReportID()
        {
                return reportID;
        }

        public void setReportID(int reportID)
        {
                this.reportID = reportID;
        }

        public String getRelation()
        {
                return relation;
        }

        public void setRelation(String relation)
        {
                this.relation = relation;
        }
}
