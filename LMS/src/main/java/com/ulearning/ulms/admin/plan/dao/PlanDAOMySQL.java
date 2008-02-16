/**
 * PlanDAOMySQL.java.
 * User: huangsb  Date: 2004-7-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.plan.dao;

import com.ulearning.ulms.admin.plan.exceptions.PlanDAOSysException;
import com.ulearning.ulms.admin.plan.form.PlanForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Statement;


public class PlanDAOMySQL extends PlanDAOImpl
{
        /**
         * Insert a new plan record to database
         *
         * @param planForm the value object to be added
         * @throws com.ulearning.ulms.admin.plan.exceptions.PlanDAOSysException
         *
         */
        public int addPlan(PlanForm planForm) throws PlanDAOSysException
        {
                Statement stmt = null;
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());

                //String establishTime = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String sqlStr = "insert into TM_Plan_Tab values(" +
                        planForm.getPlanID() + ",'" + planForm.getTitle() + "'," +
                        planForm.getOrgID() + ",'" + planForm.getSubmitOrg() + "'," +
                        planForm.getIsContent() + ",'" + planForm.getLink() + "'," +
                        planForm.getIsHyperLink() + "," + planForm.getParentID() + ",'" +
                        dayToInsert + "','" + planForm.getDescription() + "')";
                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("system",
                                "[PlanDAOMysql==============the SQL String]" + sqlStr);
                        operateDB.exeupdate(sqlStr);

                        return operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new PlanDAOSysException("SQLException while updating " +
                                "plan; Serial = " + planForm.getTitle() + " :\n" + se);
                }
        }
}
