/**
 * DeleteSchoolWorkAction.java.
 * User: yud  Date: 2005-4-15
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.action;

import com.ulearning.ulms.core.util.ValidateUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.tools.schoolwork.form.SchoolWorkForm;
import com.ulearning.ulms.tools.schoolwork.bean.SchoolWorkHelper;
import com.ulearning.ulms.tools.schoolwork.model.SchoolWorkModel;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

public class DeleteSchoolWorkAction extends DispatchAction
{

        public DeleteSchoolWorkAction()
        {
        }

        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse)
                throws Exception
        {

                String resultScreen = "success";
                ArrayList al = new ArrayList();
                SchoolWorkForm schoolWorkForm = (SchoolWorkForm) actionForm;
                String documentIDs[] = httpServletRequest.getParameterValues("documentIDs");

                if (documentIDs != null)
                {

                        for (int i = 0; i < documentIDs.length; i++)
                        {
                                al.add(new Integer(documentIDs[i]));

                        }

                }
                SchoolWorkHelper.deleteSchoolWork(al);
                MultipartParamUtils mp = new MultipartParamUtils(httpServletRequest, 0x9e7000);
                httpServletRequest.setAttribute("mp", mp);
                return actionMapping.findForward("success");
        }
}


