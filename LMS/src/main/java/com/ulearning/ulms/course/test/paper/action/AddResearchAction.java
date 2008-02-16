/**
 * AddPaperAction.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.action;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.paper.bean.PaperHelper;
import com.ulearning.ulms.course.test.paper.dao.PaperDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperDAOFactory;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.course.test.testbase.PaperBaseConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddResearchAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                //String nextPage = "nextPage";
                int submitType = Integer.parseInt(request.getParameter("submitType"));
                PaperForm pf = (PaperForm) form;
                //pf.setCreateTime(new Date());
                LogUtil.info("yangds",
                        "[AddPaperAction]================beginTime=" + pf.getBeginTime());

                if (!pf.getBeginTime().equals(""))
                {
                        String[] tmp = StringUtil.splitString(pf.getBeginTime(), "-");

                        if (tmp.length > 3)
                        {
                                pf.setStartTime(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                        tmp[3], tmp[4], "0"));
                        }
                        else
                        {
                                pf.setStartTime(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                        "0", "0", "0"));
                        }
                }

                if (!pf.getLastTime().equals(""))
                {
                        String[] tmp = StringUtil.splitString(pf.getLastTime(), "-");

                        if (tmp.length > 3)
                        {
                                pf.setEndTime(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                        tmp[3], tmp[4], "0"));
                        }
                        else
                        {
                                pf.setEndTime(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0",
                                        "0", "0"));
                        }
                }

                LogUtil.info("yangds",
                        "[AddPaperAction]================startTime=" +
                                DateTimeUtil.format(pf.getStartTime(), "yyyy-MM-dd hh:mm"));
                LogUtil.info("system",
                        "IsFeedbackGrade=" + pf.getIsFeedbackGrade() +
                                "*IsFeedbackAnswer=" + pf.getIsFeedbackAnswer() +
                                "*IsFeedbackReply=" + pf.getIsFeedbackReply());

                PaperDAO dao = PaperDAOFactory.getDAO();
                int paperID = dao.addPaper(pf);
                request.setAttribute("paperID", paperID + "");

                // Forward to result page
                if (submitType == 0)
                {
                        resultScreen = "success";
                        LogUtil.info("system",
                                "[AddPaperAction]===========resultScreen = " + resultScreen);
                }
                else if (submitType == 1)
                {
                        resultScreen = "nextPage";
                        LogUtil.info("system",
                                "[AddPaperAction]===========resultScreen = " + resultScreen);
                }

                return mapping.findForward(resultScreen);
        }
}
