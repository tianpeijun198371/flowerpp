/**
 * UpdatulmslogAction.java.
 * User: keyh  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.action;

import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.form.LmslogForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateLmslogAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LmslogForm lmslogForm = (LmslogForm) form;
                LmslogDAO dao = LmslogDAOFactory.getDAO();
                dao.update(lmslogForm);

                LogUtil.info("system",
                        "[UpdatulmslogAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
