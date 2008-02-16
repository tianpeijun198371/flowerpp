/**
 * AddPaperQuestionChoiceAction.java.
 * User: huangsb  Date: 2004-6-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.ValidateUtil;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAOFactory;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.test.question.choice.dao.ChoiceDAO;
import com.ulearning.ulms.course.test.question.choice.dao.ChoiceDAOFactory;
import com.ulearning.ulms.course.test.question.choice.form.ChoiceForm;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPaperQuestionChoiceAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                int paperID = Integer.parseInt(request.getParameter("paperID"));
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

                //处理多选的答案
                String[] CorrectAnswer = request.getParameterValues("correctAnswer");
                String corAnswer = "";

                for (int i = 0; (CorrectAnswer != null) && (i < CorrectAnswer.length);
                     i++)
                {
                        if (!CorrectAnswer[i].trim().equals(""))
                        {
                                corAnswer = corAnswer + CorrectAnswer[i].trim();
                        }
                }

                bf.setCorrectAnswer(corAnswer);

                Date d = new Date();
                bf.setCreateTime(d);
                bf.setUpdateTime(d);

                PaperQuestionForm pqf = new PaperQuestionForm();
                PaperQuestionDAO dao = PaperQuestionDAOFactory.getDAO();
                ArrayList list = new ArrayList();
                ChoiceItemForm cif = null;
                String[] Title = request.getParameterValues("itemTitle");
                String[] Link = request.getParameterValues("link");

                for (int i = 0; (Title != null) && (i < Title.length); i++)
                {
                        if (!Title[i].trim().equals(""))
                        {
                                cif = new ChoiceItemForm();
                                cif.setItemTitle(Title[i]);
                                cif.setLink(Link[i]);
                                list.add(cif);
                        }
                }

                ChoiceForm choiceForm = new ChoiceForm();
                choiceForm.setBaseForm(bf);
                choiceForm.setList(list);

                ChoiceDAO dao1 = ChoiceDAOFactory.getDAO();
                int questionID = dao1.addChoice(choiceForm);

                LogUtil.info("admin",
                        "[AddChoiceAction]===========resultScreen = " + resultScreen);

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

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);

                //request.setAttribute("questionID",questionID);
                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
