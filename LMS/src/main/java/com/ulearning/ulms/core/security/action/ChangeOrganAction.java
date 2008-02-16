/*
 * Copyright (c) 2004 HuaXia. All Rights Reserved.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.form.UserForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangeOrganAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                int userID = Integer.parseInt(request.getParameter("userID"));
                int newOrgID = Integer.parseInt(request.getParameter("catalogID"));
                String[] orgRoleID = request.getParameterValues("roleID");
                OrganDAO orgDao = OrganDAOFactory.getDAO();
                UserForm uf = UserHelper.getUser(Integer.toString(userID));
                uf.setPassword(null);

                int oldOrgID = uf.getCatalogID();
                uf.setCatalogID(newOrgID);

                UserDAO userDAO = UserDAOFactory.getDAO();
                userDAO.updateUser(uf);

                OrgUserForm ouf = new OrgUserForm();
                ouf.setOrgID(newOrgID);
                ouf.setUserID(userID);
                orgDao.updateOrganUser(ouf);

                SecurityDAO securityDAO = SecurityDAOFactory.getDAO();
                securityDAO.changeOrgUser(new OrgUserForm(oldOrgID, userID, 0),
                        new OrgUserForm(newOrgID, userID, 0));
                securityDAO.changeUserRole(userID, oldOrgID,
                        SecurityConstants.USER_ORGAN_RELATION, newOrgID, orgRoleID);

                return mapping.findForward(resultScreen);
        }
}
