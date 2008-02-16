/**
 * SearchUserAction.java. 
 * User: huangsb  Date: 2004-8-11 
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved. 
 */
package com.ulearning.ulms.user.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;

public class SearchUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                UserForm userForm = (UserForm) form;
                //发现此处代码没有用处 。修改者：fengcun 07-08-07
                /*UserDAO dao = UserDAOFactory.getDAO();
                dao.getSearchUserList(userForm);*/
                request.setAttribute("loginName", userForm.getLoginName());
                request.setAttribute("name", userForm.getName());
                request.setAttribute("roleID",userForm.getRemark12());
                request.setAttribute("userForm", userForm);
                return mapping.findForward(resultScreen);
        }

}
