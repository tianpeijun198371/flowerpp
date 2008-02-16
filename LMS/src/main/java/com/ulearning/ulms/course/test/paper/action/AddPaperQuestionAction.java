/**
 * AddPaperQuestionAction.java.
 * User: huangsb  Date: 2004-6-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.action;

import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAOFactory;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;
import com.ulearning.ulms.course.test.question.base.bean.BaseHelper;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPaperQuestionAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                PaperQuestionForm pqf = new PaperQuestionForm();
                PaperQuestionDAO dao = PaperQuestionDAOFactory.getDAO();
                int type = Integer.parseInt(request.getParameter("type"));
                String[] questionID = request.getParameterValues("questionID");
                int paperID = Integer.parseInt(request.getParameter("paperID"));

                for (int j = 0; j < questionID.length; j++)
                {
                        int dupquestionID = dao.getPaperQuestion(paperID, questionID[j]);

                        if (dupquestionID != 0)
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                        "其中有一道或几道题已经加到试卷中，请重选！");

                                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                        }
                        else
                        {
                                BaseHelper bh = new BaseHelper();
                                BaseForm bf = bh.getBase(Integer.parseInt(questionID[j]));
                                pqf.setPaperID(paperID);
                                pqf.setQuestionID(Integer.parseInt(questionID[j]));
                                pqf.setScore(bf.getScore());
                                pqf.setType(type);
                                dao.addPaperQuestion(pqf);
                        }
                }

                // BaseForm bf=
                return mapping.findForward(resultScreen);
        }
}
