package com.ulearning.ulms.tools.report.custom.conditionitem.dao;

import com.ulearning.ulms.tools.report.custom.conditionitem.exceptions.CustomConditionItemDAOSysException;
import com.ulearning.ulms.tools.report.custom.conditionitem.form.CustomConditionItemForm;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-19
 * Time: 14:40:01
 * To change this template use File | Settings | File Templates.
 */
public interface CustomConditionItemDAO
{
        public void insertCustomConditionItem(CustomConditionItemForm ccf) throws CustomConditionItemDAOSysException;

        public void updateCustomConditionitem(CustomConditionItemForm ccf) throws CustomConditionItemDAOSysException;

        public void deleteCustomConditionItem(int ReportID) throws CustomConditionItemDAOSysException;

        public void deleteCustomConditionItemPK(int CustomConditionItemID) throws CustomConditionItemDAOSysException;

        public List getCustomConditionItemFormList(int ReportID) throws CustomConditionItemDAOSysException;

}
