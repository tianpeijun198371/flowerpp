/**
 * AddSchoolWorkAction.java.
 * User: Administrator  Date: 2005-4-15
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.action;

import com.ulearning.ulms.core.util.UploadUtil;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.schoolwork.form.SchoolWorkForm;
import com.ulearning.ulms.tools.schoolwork.bean.SchoolWorkHelper;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSchoolWorkAction extends UploadAction
{

        public AddSchoolWorkAction()
        {
        }

        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse)
                throws Exception
        {

                SchoolWorkForm schoolWorkForm = (SchoolWorkForm) actionForm;
                String remark1 = httpServletRequest.getParameter("DiplayPutinBeginDate");
                String remark2 = httpServletRequest.getParameter("DiplayPutinEndDate");
                String remark3 = httpServletRequest.getParameter("DiplayAnswerBeginDate");
                String remark4 = httpServletRequest.getParameter("DiplayAnswerEndDate");

                if (httpServletRequest.getContentType().startsWith("multipart/form-data"))
                {

                        UploadUtil.executeUpload(schoolWorkForm, httpServletRequest, httpServletResponse);

                        if (!String.valueOf(httpServletRequest.getAttribute("size")).equals("0"))
                        {
                                schoolWorkForm.setSwLink((String) httpServletRequest.getAttribute("newFilePath"));
                        }
                        else
                        {
                                schoolWorkForm.setSwLink("-1");
                        }

                        if (!String.valueOf(httpServletRequest.getAttribute("size1")).equals("0"))
                        {
                                schoolWorkForm.setAnswerLink((String) httpServletRequest.getAttribute("newFilePath1"));
                        }
                        else
                        {
                                schoolWorkForm.setAnswerLink("-1");
                        }
                }

                schoolWorkForm.setRemark1(remark1);
                schoolWorkForm.setRemark2(remark2);
                schoolWorkForm.setRemark3(remark3);
                schoolWorkForm.setRemark4(remark4);
                SchoolWorkHelper.insertSchoolWork(schoolWorkForm);
                MultipartParamUtils mp = new MultipartParamUtils(httpServletRequest, 0x9e7000);
                httpServletRequest.setAttribute("mp", mp);
                //return actionMapping.findForward("success");
                ActionForward inforward = actionMapping.findForward("success");
                StringBuffer bf = new StringBuffer(inforward.getPath());
                bf.append("?courseID=" + schoolWorkForm.getRelationID());
                return new ActionForward(bf.toString(), true);


        }
}