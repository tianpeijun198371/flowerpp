/**
 * AddUserToOrgAction.java.
 * User: dengj  Date: 2004-5-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddUserToOrgAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] roleID = request.getParameterValues("roleID");
                OrganDAO orgDao = OrganDAOFactory.getDAO();
                int orgID = Integer.parseInt(request.getParameter("orgID"));
                String loginName = request.getParameter("loginName");
                int userID = UserHelper.getUserID(loginName);

                if (userID == 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                "<bean:message key=\"button.loginname\"/>²»´æÔÚ£¡");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }
                else
                {
                        orgDao.addOrganUser(orgID, userID);
                }

                SecurityDAO dao = SecurityDAOFactory.getDAO();

                for (int i = 0; i < roleID.length; i++)
                {
                        UserRoleForm urf = new UserRoleForm();
                        urf.setUserID(userID);
                        urf.setRoleID(Integer.parseInt(roleID[i]));
                        urf.setRelationID(orgID);
                        urf.setType(SecurityConstants.USER_ORGAN_RELATION);
                        dao.addUserRole(urf);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
