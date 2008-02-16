/**
 * AddDiscussAction.java.
 * User: huangsb  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.discussion.dao.DiscussDAO;
import com.ulearning.ulms.tools.discussion.dao.DiscussDAOFactory;
import com.ulearning.ulms.tools.discussion.form.DiscussForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddDiscussAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                String resultScreen = "success";
                DiscussForm df = (DiscussForm) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadForm uploadForm = df.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[AddDiscussAction] Exeception====================" + e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                df.setFileLink(StringUtil.nullToStr(df.getFilePath()) +
                                        StringUtil.nullToStr(
                                                (String) request.getAttribute("newFileName")));
                        }
                }

                // String title=ff.getTitle();
                DiscussDAO dao = DiscussDAOFactory.getDAO();
                dao.insert(df);

                LogUtil.info("system",
                        "[AddDiscussAction]===========resultScreen = " + resultScreen);
                request.setAttribute("mp", mp);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
