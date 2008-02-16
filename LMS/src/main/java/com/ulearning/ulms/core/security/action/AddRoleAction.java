/**
 * AddRoleAction.java.
 * User: huangsb  Date: 2004-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.security.form.RoleForm;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddRoleAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                RoleForm rf = (RoleForm) form;

                //Check whether the org name is duplicate
                int roleID = SecurityHelper.getRoleIDByName(rf.getName());

                if (roleID > 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "角色名称不能重复！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                SecurityDAO dao = SecurityDAOFactory.getDAO();

                //process the default action of adding a role
                String[] permitList = request.getParameterValues("permitID");
                rf.setPermissionList(permitList);
                dao.addRole(rf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
