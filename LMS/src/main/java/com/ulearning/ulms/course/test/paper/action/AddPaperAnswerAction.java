/**
 * AddPaperAnswerAction.java.
 * User: huangsb  Date: 2004-6-24
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.grade.dao.PaperUserDAO;
import com.ulearning.ulms.course.test.grade.dao.PaperUserDAOFactory;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAOFactory;
import com.ulearning.ulms.course.test.paper.form.PaperAnswerForm;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.course.test.question.base.bean.baseConstants;
import com.ulearning.ulms.course.test.testbase.PaperBaseConstants;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.commons.validator.Form;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPaperAnswerAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                PaperAnswerDAO dao = PaperAnswerDAOFactory.getDAO();
                PaperAnswerForm paf = (PaperAnswerForm) form;
                String userID = request.getParameter("userID");
                String paperID = request.getParameter("paperID");
                String[] type = request.getParameterValues("questionType");
                String[] questionID = request.getParameterValues("questionID");
                String papertype = request.getParameter("papertype");
                int dupID = dao.getPaperAnswer(Integer.parseInt(userID),
                        Integer.parseInt(paperID));

                if (dupID > 0)
                {
                        if ((papertype != null) &&
                                papertype.equals(PaperBaseConstants.PAPER_EXAMINATION_TYPE))
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                        "您参加此次考试的次数已超过允许的最大考试次数，请选择其他考试！");
                                request.setAttribute("examed",
                                        PaperBaseConstants.PAPER_EXAMINATION_TYPE);

                                return mapping.findForward(resultScreen);
                        }
                        else
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                        "您已经参加过此次考试了，请选择其他考试！");

                                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                        }
                }
                else
                {
                        for (int i = 0; i < questionID.length; i++)
                        {
                                String answerStr = "";
                                String[] answer = request.getParameterValues("answer_" +
                                        questionID[i]);

                                for (int j = 0; (answer != null) && (j < answer.length); j++)
                                {
                                        if ((type[i] != null) &&
                                                type[i].equals(baseConstants.QUESTION_FILL_TYPE))
                                        {
                                                if (!answer[j].equals(""))
                                                {
                                                        answerStr = answerStr + "%" +
                                                                StringUtil.replaceString(answer[j], "%",
                                                                        "&#037;");
                                                }

                                                if (!answerStr.equals(""))
                                                {
                                                        answerStr = answerStr.substring(1);
                                                }
                                        }
                                        else
                                        {
                                                answerStr += answer[j];
                                        }

                                        LogUtil.debug("system",
                                                "[Answer AnswerDAOAction]====================the sql string is : " +
                                                        answer[j]);
                                }

                                paf.setUserID(Integer.parseInt(userID));
                                paf.setPaperID(Integer.parseInt(paperID));
                                paf.setQuestionID(Integer.parseInt(questionID[i]));
                                paf.setAnswer(answerStr);
                                paf.setType(Integer.parseInt(type[i]));
                                dao.addPaperAnswer(paf);
                        }

                        PaperUserDAO dao1 = PaperUserDAOFactory.getDAO();
                        PaperUserForm puf = new PaperUserForm();
                        puf.setPaperID(Integer.parseInt(paperID));
                        puf.setUserID(Integer.parseInt(userID));
                        puf.setType(3);
                        dao1.addPaperUser(puf);
                }

                return mapping.findForward(resultScreen);
        }
}
