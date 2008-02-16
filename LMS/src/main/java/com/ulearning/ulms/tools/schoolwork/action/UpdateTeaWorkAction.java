/**
 * UpdateTeaWorkAction.java.
 * User: yud  Date: 2005-4-18
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.action;

import com.ulearning.ulms.core.util.ValidateUtil;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.tools.schoolwork.form.TeaWorkForm;
import com.ulearning.ulms.tools.schoolwork.bean.TeaWorkHelper;
import com.ulearning.ulms.tools.schoolwork.model.TeaWorkModel;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateTeaWorkAction extends Action
{

        public UpdateTeaWorkAction()
        {
        }

        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse)
                throws Exception
        {

                TeaWorkForm teaWorkForm = (TeaWorkForm) actionForm;

                if (httpServletRequest.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadAction uploadAction = new UploadAction();
                                UploadForm uploadForm = teaWorkForm.getUploadForm();
                                uploadAction.executeUpload(actionMapping, uploadForm,
                                        httpServletRequest, httpServletResponse);

                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();

                        }
                        LogUtil.info("course", "[TeaWorkAction]===========1");

                        if (!String.valueOf(httpServletRequest.getAttribute("size")).equals("0"))
                        {
                                teaWorkForm.setLink((String) httpServletRequest.getAttribute("newFilePath"));
                        }
                        else if (ValidateUtil.isEmpty((String) (httpServletRequest.getAttribute("fileName"))))
                        {
                                teaWorkForm.setLink("");
                        }
                        else
                        {
                                teaWorkForm.setLink("");
                        }
                }

                TeaWorkModel teaWorkModel = (TeaWorkModel) teaWorkForm.getTeaWorkModel();
                TeaWorkHelper.updateTeaWork(teaWorkModel);

                MultipartParamUtils mp = new MultipartParamUtils(httpServletRequest, 0x9e7000);
                httpServletRequest.setAttribute("mp", mp);
                return actionMapping.findForward("success");
        }

}


