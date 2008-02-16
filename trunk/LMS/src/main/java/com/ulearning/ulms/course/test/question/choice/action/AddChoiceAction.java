/**
 * Created by IntelliJ IDEA.
 * Choice: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.choice.action;

import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.test.question.choice.dao.ChoiceDAO;
import com.ulearning.ulms.course.test.question.choice.dao.ChoiceDAOFactory;
import com.ulearning.ulms.course.test.question.choice.form.ChoiceForm;
import com.ulearning.ulms.course.test.question.choiceitem.form.ChoiceItemForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddChoiceAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
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

                ArrayList list = new ArrayList();
                ChoiceItemForm cif = null;

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

                ChoiceDAO dao = ChoiceDAOFactory.getDAO();
                dao.addChoice(choiceForm);

                LogUtil.info("admin",
                        "[AddChoiceAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
