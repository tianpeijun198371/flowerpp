/**
 * Created by IntelliJ IDEA.
 * Plan: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.plan.action;

import com.ulearning.ulms.admin.plan.bean.PlanHelper;
import com.ulearning.ulms.admin.plan.dao.PlanDAO;
import com.ulearning.ulms.admin.plan.dao.PlanDAOFactory;
import com.ulearning.ulms.admin.plan.form.PlanForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelPlanAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] planID = request.getParameterValues("planID");

                for (int i = 0; i < planID.length; i++)
                {
                        PlanDAO dao = PlanDAOFactory.getDAO();
                        PlanForm del = PlanHelper.getPlan(Integer.parseInt(planID[i]));
                        int submitOrg = del.getSubmitOrg();

                        if (submitOrg > 0)
                        {
                                dao.removePlan(String.valueOf(submitOrg));
                        }

                        dao.removePlan(planID[i]);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
