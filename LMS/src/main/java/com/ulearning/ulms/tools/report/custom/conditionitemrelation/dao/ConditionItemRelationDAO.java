package com.ulearning.ulms.tools.report.custom.conditionitemrelation.dao;

import com.ulearning.ulms.tools.report.custom.conditionitemrelation.form.ConditionItemRelationForm;
import com.ulearning.ulms.tools.report.custom.conditionitemrelation.exceptions.ConditionItemRelationDAOSysException;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-21
 * Time: 11:14:20
 * To change this template use File | Settings | File Templates.
 */
public interface ConditionItemRelationDAO
{
        public void insertConditionItemRelation(ConditionItemRelationForm crf) throws ConditionItemRelationDAOSysException;

        public void updateConditionItemRelation(ConditionItemRelationForm crf) throws ConditionItemRelationDAOSysException;

        public void deleteConditionItemRelation(int reportID) throws ConditionItemRelationDAOSysException;

        public String getConditionItemRelation(int reportID) throws ConditionItemRelationDAOSysException;

}
