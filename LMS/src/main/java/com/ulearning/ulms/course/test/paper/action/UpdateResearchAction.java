/**
 * UpdatePaperAction.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.action;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.paper.dao.PaperDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperDAOFactory;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateResearchAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                //yangds add: start
                String type = request.getParameter("submitType");

                if ((type != null) && type.equals("1"))
                {
                        resultScreen = "nextPage";
                }

                //yangds add: end for portal/survey/updateresearch.jsp file
                // PaperForm pf=(PaperForm)request.getAttribute("paperForm");
                int isA = Integer.parseInt(request.getParameter("isAvailable"));

                PaperForm pf = (PaperForm) form;
                LogUtil.info("yangds",
                        "[updPaperAction]================beginTime=" + pf.getBeginTime());
                LogUtil.info("yangds",
                        "[updPaperAction]================lastTime=" + pf.getLastTime());

                if (!pf.getBeginTime().equals(""))
                {
                        //String[] tmp=StringUtil.splitString(pf.getBeginTime(),"-");
                        //pf.setStartTime(DateTimeUtil.toDate(tmp[1],tmp[2],tmp[0],"0","0","0"));
                        pf.setStartTime(DateTimeUtil.parseDateTime(pf.getBeginTime()));
                }

                if (!pf.getLastTime().equals(""))
                {
                        // String[] tmp=StringUtil.splitString(pf.getLastTime(),"-");
                        //pf.setEndTime(DateTimeUtil.toDate(tmp[1],tmp[2],tmp[0],"0","0","0"));
                        pf.setEndTime(DateTimeUtil.parseDateTime(pf.getLastTime()));
                }

                LogUtil.info("yangds",
                        "[updPaperAction]===========startTime = " +
                                DateTimeUtil.format(pf.getStartTime(), "yyyy-MM-dd hh:mm"));
                LogUtil.info("yangds",
                        "[updPaperAction]===========endTime = " +
                                DateTimeUtil.format(pf.getEndTime(), "yyyy-MM-dd hh:mm"));
                pf.setIsAvailable(isA);

                PaperDAO dao = PaperDAOFactory.getDAO();
                dao.updatePaper(pf);

                LogUtil.info("system",
                        "[updPaperAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
