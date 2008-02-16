/**
 * GradeSearchAction.java.
 * User: huangsb Date: 2006-3-24 15:57:14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.action;

import com.ulearning.ulms.admin.gradeuser.bean.GradeUserImpl;
import com.ulearning.ulms.admin.gradeuser.form.GradeUserForm;
import com.ulearning.ulms.admin.gradeusercourse.bean.GradeUserCourseImpl;
import com.ulearning.ulms.core.security.form.LoginForm;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GradeSearchAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LoginForm lf = (LoginForm) form;
                String name = lf.getName();
                String passwd = lf.getPasswd();

                GradeUserForm guf = GradeUserImpl.getGradeUser(name, passwd);

                if (guf != null)
                {
                        int userID = guf.getGradeUserID();

                        if (userID > 0)
                        {
                                resultScreen = "success";

                                List gradList = GradeUserCourseImpl.getGradeUserCourseList(userID);
                                request.setAttribute("gradList", gradList);
                        }
                        else
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO, " 姓名和密码错误！");

                                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                        }
                }
                else
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, " 系统中不存在该用户！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                return mapping.findForward(resultScreen);
        }
}
