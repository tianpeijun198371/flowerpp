/**
 * Created by IntelliJ IDEA.
 * Plan: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.plan.action;

import com.ulearning.ulms.admin.plan.bean.PlanHelper;
import com.ulearning.ulms.admin.plan.dao.PlanDAO;
import com.ulearning.ulms.admin.plan.dao.PlanDAOFactory;
import com.ulearning.ulms.admin.plan.exceptions.PlanDAOSysException;
import com.ulearning.ulms.admin.plan.form.PlanForm;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CarryoutAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.info("system", "[CarryoutPlanAction]===========Begin");

                PlanForm pf = (PlanForm) form;
                int isLink = pf.getIsHyperLink();

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                com.ulearning.ulms.tools.upload.model.UploadForm uploadForm = pf.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[CarryoutPlanAction] Exeception====================" + e);
                        }

                        if (pf.getLink().equals(""))
                        {
                                if (!String.valueOf(request.getAttribute("size")).equals("0"))
                                {
                                        pf.setLink(pf.getFilePath() +
                                                request.getAttribute("newFileName"));
                                }
                                else
                                {
                                        pf.setLink("");
                                }
                        }
                }

                String title = pf.getTitle();
                int planID = PlanHelper.getPlanID(title, pf.getIsContent(),
                        pf.getParentID());
                String startDateTimeStr = pf.getStartDateTimeValue();
                Date startDateTime = new Date();

                if ((startDateTimeStr != null) && !startDateTimeStr.equals(""))
                {
                        startDateTime = DateTimeUtil.parseDate(startDateTimeStr);
                        pf.setEstablishTime(startDateTime);
                }

                LogUtil.info("admin",
                        "[CarryoutPlanAction]===========planID = " + planID);
                LogUtil.info("admin", "[CarryoutPlanAction]===========title = " +
                        title);

                if ((planID > 0) && (planID != pf.getPlanID()))
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "计划名称不能重复！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                try
                {
                        //Add a new carry out according to plan to database
                        PlanDAO dao = PlanDAOFactory.getDAO();
                        int newPlanID = dao.addPlan(pf);

                        //Update the plan object
                        int oldPlanID = pf.getPlanID();
                        pf = PlanHelper.getPlan(oldPlanID);
                        pf.setSubmitOrg(newPlanID);
                        pf.setIsHyperLink(isLink); //实施计划
                        dao.updatePlan(pf);
                }
                catch (PlanDAOSysException e)
                {
                        e.printStackTrace();
                }

                LogUtil.info("admin",
                        "[CarryoutPlanAction]===========resultScreen = " + resultScreen);
                request.setAttribute("secOrgID", request.getParameter("secOrgID"));
                request.setAttribute("parentID", request.getParameter("parentID"));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
