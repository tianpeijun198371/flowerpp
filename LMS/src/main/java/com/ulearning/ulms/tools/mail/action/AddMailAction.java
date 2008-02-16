/** * AddMailAction.java.
 * User: xiejh  Date: 2004-8-9 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.mail.action;

import com.ulearning.ulms.core.mail.EmailServices;
import com.ulearning.ulms.core.mail.SmtpModel;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.upload.action.UploadAction;

//import com.ulearning.ulms.tools.upload.action.MultipartParamUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddMailAction extends UploadAction
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
                String relationID = request.getParameter("relationID");
                String tagType = request.getParameter("tagType");

                if ((relationID != null) && (tagType != null))
                {
                        ActionForward forward = mapping.findForward(resultScreen);
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?relationID=" + relationID + "&tagType=" + "star");

                        return new ActionForward(bf.toString());
                }
                else
                {
                        return mapping.findForward(resultScreen);
                }
        }
}
