/** * UpdateAssignProcessAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.action;

import com.ulearning.ulms.tools.assignment.assignprocess.dao.AssignProcessDAO;
import com.ulearning.ulms.tools.assignment.assignprocess.dao.AssignProcessDAOFactory;
import com.ulearning.ulms.tools.assignment.assignprocess.form.AssignProcessForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateAssignProcessAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                String resultScreen = "success";
                AssignProcessForm uf = (AssignProcessForm) form;

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadForm uploadForm = uf.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[UpdateAssignProcessAction] Exeception====================" +
                                                e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                if (request.getParameter("attachType").equals("student"))
                                {
                                        uf.setAttach_path_stu(uf.getFilePath() +
                                                request.getAttribute("newFileName"));
                                }
                                else if (request.getParameter("attachType").equals("teacher"))
                                {
                                        uf.setAttach_path_tea(uf.getFilePath() +
                                                request.getAttribute("newFileName"));
                                }
                        }
                }

                AssignProcessDAO dao = AssignProcessDAOFactory.getDAO();
                dao.updateAssignProcess(uf);

                LogUtil.info("admin",
                        "[UpdateAssignProcessAction]===========resultScreen = " +
                                resultScreen);

                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
