/**
 * Created by IntelliJ IDEA.
 * Plan: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.plan.dao;

import com.ulearning.ulms.admin.plan.exceptions.PlanDAOSysException;
import com.ulearning.ulms.admin.plan.form.PlanForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.SQLException;
import java.sql.Statement;


public class PlanDAOOracle extends PlanDAOImpl
{
        /**
         * Insert a new plan record to database
         *
         * @param planForm the value object to be added
         * @throws PlanDAOSysException
         */
        public int addPlan(PlanForm planForm) throws PlanDAOSysException
        {
                Statement stmt = null;
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String establishTime = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String sqlStr = "insert into TM_Plan_Tab values(planID.nextval,'" +
                        planForm.getTitle() + "','" + planForm.getOrgID() + "','" +
                        planForm.getSubmitOrg() + "','" + planForm.getIsContent() + "','" +
                        planForm.getLink() + "','" + planForm.getIsHyperLink() + "','" +
                        planForm.getParentID() + "'," + establishTime + ",'" +
                        planForm.getDescription() + "')";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[PlanDAOOracle==============the SQL String]" + sqlStr);
                        stmt.execute(sqlStr);

                        return Integer.parseInt(stmt.toString());
                }
                catch (SQLException se)
                {
                        throw new PlanDAOSysException("SQLException while updating " +
                                "plan;" + " Serial=" + planForm.getTitle() + ":\n" + se);
                }
                finally
                {
                        try
                        {
                                if (stmt != null)
                                {
                                        stmt.close();
                                        closeConnection();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }

        /**
         * Update planInfo by the new Form
         *
         * @param planForm value object for changed
         * @throws PlanDAOSysException
         */
        public void updatePlan(PlanForm planForm) throws PlanDAOSysException
        {
                Statement stmt = null;
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String establishTime = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String sqlStr = "update TM_Plan_Tab set " + "Title='" +
                        planForm.getTitle() + "'," + "OrgID = '" + planForm.getOrgID() +
                        "', " + "SubmitOrg = '" + planForm.getSubmitOrg() + "', " +
                        "IsContent = '" + planForm.getIsContent() + "', " + "Link = '" +
                        planForm.getLink() + "', " + "IsHyperLink = '" +
                        planForm.getIsHyperLink() + "', " + "ParentID = '" +
                        planForm.getParentID() + "', " + "EstablishTime = " +
                        establishTime + ", " + "Description = '" +
                        planForm.getDescription() + "'  where PlanID = " +
                        planForm.getPlanID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[PlanDAOOracle==============the SQL String]" + sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new PlanDAOSysException("SQLException while updating " +
                                "account;" + " Serial=" + planForm.getTitle() + ":\n" + se);
                }
                finally
                {
                        try
                        {
                                if (stmt != null)
                                {
                                        stmt.close();
                                        closeConnection();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }
}
