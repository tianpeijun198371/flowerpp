package com.ulearning.ulms.tools.report.custom.conditionitemrelation.dao;

import com.ulearning.ulms.tools.report.custom.conditionitemrelation.exceptions.ConditionItemRelationDAOSysException;
import com.ulearning.ulms.tools.report.custom.conditionitemrelation.form.ConditionItemRelationForm;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-21
 * Time: 11:14:57
 * To change this template use File | Settings | File Templates.
 */
public class ConditionItemRelationDAOOracle extends ConditionItemRelationDAOImpl
{
        OperateDB operateDB = new OperateDB();

        public void insertConditionItemRelation(ConditionItemRelationForm crf) throws ConditionItemRelationDAOSysException
        {
                String sqlStr = "insert into R_CONDITIONITEMRELATION_TAB values (" +
                        crf.getReportID() + ",'" +
                        crf.getRelation() + "')";
                if (operateDB == null)
                {
                        operateDB = new OperateDB();
                }
                try
                {
                        LogUtil.debug("system", "[ConditionItemRelationDAOOracle]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ConditionItemRelationDAOSysException("SQLException while inserting " + "ConditionItemRelation; ReportID = " + crf.getReportID() + " :\n" + se);
                }

        }

        public void updateConditionItemRelation(ConditionItemRelationForm crf) throws ConditionItemRelationDAOSysException
        {
                String sqlStr = "update R_CONDITIONITEMRELATION_TAB set Relation = '" +
                        crf.getRelation() + "'" +
                        " where ReportID = " + crf.getReportID();
                if (operateDB == null)
                {
                        operateDB = new OperateDB();
                }
                try
                {
                        LogUtil.debug("system", "[ConditionItemRelationDAOOracle]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ConditionItemRelationDAOSysException("SQLException while updating " + "ConditionItemRelation; ReportID = " + crf.getReportID() + " :\n" + se);
                }
        }

}
