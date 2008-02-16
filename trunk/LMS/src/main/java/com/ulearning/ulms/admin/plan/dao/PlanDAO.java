/**
 * Created by IntelliJ IDEA.
 * Plan: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.plan.dao;

import com.ulearning.ulms.admin.plan.exceptions.PlanDAOSysException;
import com.ulearning.ulms.admin.plan.form.PlanForm;

import java.util.List;


public interface PlanDAO
{
        public int addPlan(PlanForm details) throws PlanDAOSysException;

        public void updatePlan(PlanForm details) throws PlanDAOSysException;

        public void removePlan(String planID) throws PlanDAOSysException;

        public PlanForm getPlan(int planID) throws PlanDAOSysException;

        public int getPlanID(String title, int isContent, int parentID)
                throws PlanDAOSysException;

        public List getPlanList(int orgID) throws PlanDAOSysException;

        public List getPlanList(String parentID) throws PlanDAOSysException;

        public List getPlanList(int orgID, int parentID) throws PlanDAOSysException;

        public List getPlanPath(int planID) throws PlanDAOSysException;
}
