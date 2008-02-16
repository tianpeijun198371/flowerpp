/** * AddAssignProcessAction.java.
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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddAssignProcessAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                AssignProcessForm pf = (AssignProcessForm) form;

                AssignProcessDAO dao = AssignProcessDAOFactory.getDAO();
                dao.addAssignProcess(pf);
                LogUtil.info("admin",
                        "[AddAssignProcessAction]===========resultScreen = " +
                                resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
