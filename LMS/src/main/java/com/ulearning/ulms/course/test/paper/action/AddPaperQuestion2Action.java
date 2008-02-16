/**
 * AddPaperQuestion2Action.java.
 * User: huangsb  Date: 2004-6-24
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAOFactory;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;
import com.ulearning.ulms.course.test.question.base.bean.BaseHelper;
import com.ulearning.ulms.course.test.question.base.bean.baseConstants;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAO;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOFactory;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPaperQuestion2Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                PaperQuestionForm pqf = new PaperQuestionForm();
                PaperQuestionDAO dao = PaperQuestionDAOFactory.getDAO();
                BaseForm bf = (BaseForm) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadAction uploadAction = new UploadAction();
                                UploadForm uploadForm = bf.getUploadForm();
                                uploadAction.executeUpload(mapping, uploadForm, request,
                                        response);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                LogUtil.debug("system",
                                        "[UpdateAssignmentAction] Exeception====================" +
                                                e);
                        }

                        LogUtil.info("test", "[AddPaperQuestionChoiceAction]===========1");

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                bf.setLink((String) request.getAttribute("newFilePath"));
                        }
                }

                String type = request.getParameter("type");
                int paperID = Integer.parseInt(request.getParameter("paperID"));

                if ((type != null) && type.equals(baseConstants.QUESTION_FILL_TYPE))
                {
                        String correctAnswer = "";
                        String[] CorrectAnswer = request.getParameterValues("correctAnswer");

                        for (int i = 0;
                             (CorrectAnswer != null) && (i < CorrectAnswer.length);
                             i++)
                        {
                                if (!CorrectAnswer[i].equals(""))
                                {
                                        correctAnswer = correctAnswer + "%" +
                                                StringUtil.replaceString(CorrectAnswer[i], "%", "&#037;");
                                }
                        }

                        if (!correctAnswer.equals(""))
                        {
                                correctAnswer = correctAnswer.substring(1);
                        }

                        bf.setCorrectAnswer(correctAnswer);
                }

                BaseDAO dao1 = BaseDAOFactory.getDAO();
                int questionID = dao1.addBase(bf);
                int dupquestionID = dao.getPaperQuestion(paperID,
                        String.valueOf(questionID));

                if (dupquestionID != 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                "其中有一道或几道题已经加到试卷中，请重选！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }
                else
                {
                        pqf.setQuestionID(questionID);
                        pqf.setPaperID(paperID);
                        pqf.setType(Integer.parseInt(bf.getType()));
                        pqf.setScore(bf.getScore());
                        dao.addPaperQuestion(pqf);
                }

                //request.setAttribute("questionID", questionID);
                LogUtil.info("admin",
                        "[AddBaseAction]===========resultScreen = " + resultScreen);

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
