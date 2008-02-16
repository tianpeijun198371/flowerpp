/**
 * Update.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.action;

import com.ulearning.ulms.course.courseconfig.dao.CreditCourseWiseDAO;
import com.ulearning.ulms.course.courseconfig.dao.CreditCourseWiseDAOFactory;
import com.ulearning.ulms.course.courseconfig.form.CreditCourseWiseForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateCreditCourseWiseAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                CreditCourseWiseForm ccwf = (CreditCourseWiseForm) form;
                CreditCourseWiseDAO dao = CreditCourseWiseDAOFactory.getDAO();
                dao.update(ccwf);
                LogUtil.info("Course",
                        "[UpdateCreditCourseWiseAction]===========resultScreen = " +
                                resultScreen);

                return mapping.findForward(resultScreen);
        }
}
