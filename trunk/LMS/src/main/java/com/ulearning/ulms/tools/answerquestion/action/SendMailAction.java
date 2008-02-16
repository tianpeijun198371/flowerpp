/** * AddMailAction.java.
 * User: xiejh  Date: 2004-8-9 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.action;

import com.ulearning.ulms.core.mail.EmailServices;
import com.ulearning.ulms.core.mail.SmtpModel;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.answerquestion.bean.AnswerQuestionHelper;
import com.ulearning.ulms.tools.answerquestion.model.AnswerQuestionModel;
import com.ulearning.ulms.tools.answerquestion.webimpls.AnswerQuestionWeblmpl;
import com.ulearning.ulms.tools.upload.action.UploadAction;

//import com.ulearning.ulms.tools.upload.action.MultipartParamUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SendMailAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.info("system", "[AddAnnouncementAction]===========Begin");

                SmtpModel smtpModel = (SmtpModel) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                com.ulearning.ulms.tools.upload.model.UploadForm uploadForm = smtpModel.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[AddAnnouncementAction] Exeception====================" +
                                                e);
                        }

                        smtpModel.setSendFrom(Config.getSmtpUser()); //验证时要于 SmtpUser一致

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                String attach = Config.getUploadPhysicalPath() +
                                        request.getParameter("filePath") +
                                        request.getAttribute("newFileName");
                                smtpModel.setAttachment(attach);
                        }
                        else
                        {
                                smtpModel.setAttachment(null);
                        }
                }

                //List mailTo= StringUtil.split(request.getParameter("mailTo"),";");
                String mailToStr = StringUtil.replaceString(request.getParameter(
                        "mailTo"), ";", ",");
                List mailTo = StringUtil.split(mailToStr, "%$#*");
                smtpModel.setSendTo(mailTo);

                List mailCc = StringUtil.split(request.getParameter("mailCc"), ";");
                smtpModel.setSendCc(mailCc);

                List mailBcc = StringUtil.split(request.getParameter("mailBcc"), ";");
                smtpModel.setSendBcc(mailBcc);

                LogUtil.info("system",
                        "[AddMailAction]===========resultScreen = " + resultScreen);

                EmailServices.sendMail(smtpModel);

                //MultipartParamUtil mp = new MultipartParamUtil(request, 0x9e7000);
                //request.setAttribute("mp", mp);
                String relationIDs = request.getParameter("relationID");
                String relationName = request.getParameter("relationName");
                String qtype = request.getParameter("qtype");
                int aqID = 0;
                String aqIDs = request.getParameter("aqIDs");

                if (aqIDs != null)
                {
                        aqID = Integer.parseInt(aqIDs);

                        AnswerQuestionWeblmpl aqw = new AnswerQuestionWeblmpl();
                        AnswerQuestionModel aqm = aqw.getQuestion(aqID);
                        //把问题状态置为已转交
                        aqm.setIsReply("2");
                        AnswerQuestionHelper.updateQuestiont(aqm.getAnswerQuestionForm());
                }

                request.setAttribute("relationID", relationIDs);
                request.setAttribute("relationName", relationName);
                LogUtil.debug("tools",
                        "[AnswerQuestionAction]-----addCatalog start:relationName=" +
                                relationName);

                ActionForward inforward = mapping.findForward(resultScreen);
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?qtype=" + qtype + "&relationID=" + relationIDs +
                        "&relationName=" + relationName);
                LogUtil.debug("tools",
                        "[AnswerQuestionAction]-----addCatalog start:url=" + bf.toString());

                return new ActionForward(bf.toString(), true);
        }
}
