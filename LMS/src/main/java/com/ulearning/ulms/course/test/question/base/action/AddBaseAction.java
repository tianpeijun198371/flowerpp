/**
 * Created by IntelliJ IDEA.
 * Base: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.base.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.question.base.bean.baseConstants;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAO;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOFactory;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddBaseAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                BaseForm pf = (BaseForm) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadAction uploadAction = new UploadAction();
                                UploadForm uploadForm = pf.getUploadForm();
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
                                pf.setLink((String) request.getAttribute("newFilePath"));
                        }
                }

                String type = request.getParameter("type");

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

                        pf.setCorrectAnswer(correctAnswer);
                }

                Date d = new Date();
                pf.setCreateTime(d);
                pf.setUpdateTime(d);

                BaseDAO dao = BaseDAOFactory.getDAO();
                String questionID = String.valueOf(dao.addBase(pf));
                request.setAttribute("questionID", questionID);
                LogUtil.info("admin",
                        "[AddBaseAction]===========resultScreen = " + resultScreen);

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
