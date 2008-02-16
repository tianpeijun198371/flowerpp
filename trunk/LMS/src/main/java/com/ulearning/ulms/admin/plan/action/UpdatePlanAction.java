/**
 * Created by IntelliJ IDEA.
 * Plan: dengj
 * Date: Apr 19, 2004
 * Time: 8:54:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.plan.action;

import com.ulearning.ulms.admin.plan.bean.PlanHelper;
import com.ulearning.ulms.admin.plan.dao.PlanDAO;
import com.ulearning.ulms.admin.plan.dao.PlanDAOFactory;
import com.ulearning.ulms.admin.plan.form.PlanForm;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdatePlanAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                PlanForm plan = (PlanForm) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                com.ulearning.ulms.tools.upload.model.UploadForm uploadForm = plan.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[UpdatePlanAction] Exeception====================" + e);
                        }

                        if (plan.getLink().equals(""))
                        {
                                if (!String.valueOf(request.getAttribute("size")).equals("0"))
                                {
                                        plan.setLink(plan.getFilePath() +
                                                request.getAttribute("newFileName"));
                                }
                                else
                                {
                                        plan.setLink("");
                                }
                        }
                }

                String title = plan.getTitle();
                int planID = PlanHelper.getPlanID(title, plan.getIsContent(),
                        plan.getParentID());
                String startDateTimeStr = plan.getStartDateTimeValue();
                Date startDateTime = new Date();

                if ((startDateTimeStr != null) && !startDateTimeStr.equals(""))
                {
                        startDateTime = DateTimeUtil.parseDate(startDateTimeStr);
                        plan.setEstablishTime(startDateTime);
                }

                if ((planID > 0) && (planID != plan.getPlanID()))
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "计划名称不能重复！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                PlanDAO dao = PlanDAOFactory.getDAO();
                dao.updatePlan(plan);
                LogUtil.info("admin",
                        "[UpdatePlanAction]===========resultScreen = " + resultScreen);
                request.setAttribute("secOrgID", request.getParameter("secOrgID"));
                request.setAttribute("parentID", request.getParameter("parentID"));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
