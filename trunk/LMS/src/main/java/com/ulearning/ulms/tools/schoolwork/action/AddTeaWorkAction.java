/**
 * AddTeaWorkAction.java.
 * User: Administrator  Date: 2005-4-15
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
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddTeaWorkAction extends Action
{

        public AddTeaWorkAction()
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

                TeaWorkHelper.insertTeaWork(teaWorkForm);
                MultipartParamUtils mp = new MultipartParamUtils(httpServletRequest, 0x9e7000);

                httpServletRequest.setAttribute("mp", mp);
                ActionForward inforward = actionMapping.findForward("success");
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?userswId=" + teaWorkForm.getUserswId());
                return new ActionForward(bf.toString(), true);
        }
}


