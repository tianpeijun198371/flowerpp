/**
 * DelRoleAction.java.
 * User: huangsb  Date: 2004-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelRoleAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String roleID = request.getParameter("roleID");

                SecurityDAO dao = SecurityDAOFactory.getDAO();

                if ((roleID != null) && (dao.isUnusedRole(Integer.parseInt(roleID))))
                {
                        dao.removeRole(roleID);
                }
                else
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "系统不能删除正在使用的角色！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
