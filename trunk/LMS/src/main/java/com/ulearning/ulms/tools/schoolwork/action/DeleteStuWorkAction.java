/**
 * DeleteStuWorkAction.java.
 * User: yud  Date: 2005-4-15
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.action;

import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.schoolwork.bean.StuWorkHelper;
import com.ulearning.ulms.tools.schoolwork.form.StuWorkForm;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

public class DeleteStuWorkAction extends DispatchAction
{

        public DeleteStuWorkAction()
        {

        }

        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse)
                throws Exception
        {
                String resultScreen = "success";

                StuWorkForm stuWorkForm = (StuWorkForm) actionForm;
                ArrayList al = new ArrayList();

                String documentIDs[] = httpServletRequest.getParameterValues("userswId");

                if (documentIDs != null)
                {

                        for (int i = 0; i < documentIDs.length; i++)
                        {
                                al.add(new Integer(documentIDs[i]));

                        }

                }
                StuWorkHelper.deleteStuWork(al);
                MultipartParamUtils mp = new MultipartParamUtils(httpServletRequest, 0x9e7000);
                httpServletRequest.setAttribute("mp", mp);
                return actionMapping.findForward("success");
        }

}


