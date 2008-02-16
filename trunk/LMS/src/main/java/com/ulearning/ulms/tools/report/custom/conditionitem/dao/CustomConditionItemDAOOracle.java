package com.ulearning.ulms.tools.report.custom.conditionitem.dao;

import com.ulearning.ulms.tools.report.custom.conditionitem.exceptions.CustomConditionItemDAOSysException;
import com.ulearning.ulms.tools.report.custom.conditionitem.form.CustomConditionItemForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-19
 * Time: 14:28:48
 * To change this template use File | Settings | File Templates.
 */
public class CustomConditionItemDAOOracle extends CustomConditionItemDAOImpl
{
        OperateDB operateDB = new OperateDB();

        public void insertCustomConditionItem(CustomConditionItemForm ccf) throws CustomConditionItemDAOSysException
        {

                String sqlStr = "insert into R_CUSTOMCONDITIONITEM_Tab values(" +
                        "CustomConditionItemID.nextval ," +
                        ccf.getReportID() + "," +
                        ccf.getConditionItemID() + ",'" +
                        ccf.getConditionValue() + "','" +
                        ccf.getRelationSymbol() + "')";

                if (operateDB == null)
                {
                        operateDB = new OperateDB();
                }
                try
                {
                        LogUtil.debug("system", "[CustomConditionItemDAOOracle]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CustomConditionItemDAOSysException("SQLException while updating " + "CustomConditionItem; Serial = " + ccf.getReportID() + " :\n" + se);
                }
        }

        public void updateCustomConditionitem(CustomConditionItemForm ccf) throws CustomConditionItemDAOSysException
        {

                String sqlStr = "update R_CUSTOMCONDITIONITEM_Tab set " +
                        "ReportID = " + ccf.getReportID() + ", " +
                        "ConditionItemID = " + ccf.getConditionItemID() + ", " +
                        "ConditionValue = '" + ccf.getConditionValue() + "', " +
                        "RelationSymbol= '" + ccf.getRelationSymbol() + "'" +
                        " where CustomConditionItemID =  " + ccf.getCustomConditionItemID();


                if (operateDB == null)
                {
                        operateDB = new OperateDB();
                }
                try
                {
                        LogUtil.debug("system", "[CustomConditionItemDAOOracle]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CustomConditionItemDAOSysException("SQLException while updating " + "account; Serial = " + ccf.getCustomConditionItemID() + " :\n" + se);
                }
        }
}
