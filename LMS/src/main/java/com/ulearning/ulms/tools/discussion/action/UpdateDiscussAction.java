/**
 * UpdateDiscussAction.java.
 * User: huangsb  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.action;

import com.ulearning.ulms.tools.discussion.dao.DiscussDAO;
import com.ulearning.ulms.tools.discussion.dao.DiscussDAOFactory;
import com.ulearning.ulms.tools.discussion.dao.ForumDAO;
import com.ulearning.ulms.tools.discussion.dao.ForumDAOFactory;
import com.ulearning.ulms.tools.discussion.form.DiscussForm;
import com.ulearning.ulms.tools.discussion.form.ForumForm;
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


public class UpdateDiscussAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                String updateLink = mp.getParameter("updateLink");
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
                                df.setFileLink(df.getFilePath() +
                                        request.getAttribute("newFileName"));
                        }
                        else
                        {
                                df.setFileLink(updateLink);
                        }
                }

                DiscussDAO dao = DiscussDAOFactory.getDAO();
                dao.update(df);

                LogUtil.info("admin",
                        "[UpdateDiscussAction]===========resultScreen = " + resultScreen);
                request.setAttribute("mp", mp);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
