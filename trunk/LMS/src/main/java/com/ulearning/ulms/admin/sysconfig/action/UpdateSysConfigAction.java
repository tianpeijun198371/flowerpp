/**
 * UpdateSysConfigAction.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.action;

import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAO;
import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAOFactory;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
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


public class UpdateSysConfigAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                String resultScreen = "success";
                SysConfigForm sf = (SysConfigForm) form;

                //String platLogo =request.getParameter("platLogo");
                //String Platlogo=sf.getPlatLogo();
                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadForm uploadForm = sf.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[UpdateSysConfigAction] Exeception====================" +
                                                e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                sf.setPlatLogo(sf.getFilePath() +
                                        request.getAttribute("newFileName"));
                        }
                }

                SysConfigDAO dao = SysConfigDAOFactory.getDAO();
                dao.updateSysConfig(sf);

                LogUtil.info("system",
                        "[UpdateSysConfigAction]===========resultScreen = " + resultScreen);

                request.setAttribute("mp", mp);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
