/**
 * UpdateOrgUserAction.java.
 * User: huangsb  Date: 2004-7-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.form.LmslogForm;
import com.ulearning.ulms.lmslog.util.LmslogConstants;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateOrgUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                SecurityDAO dao = SecurityDAOFactory.getDAO();
                int userID = Integer.parseInt(request.getParameter("userID"));
                int orgID = Integer.parseInt(request.getParameter("orgID"));
                String[] roleID = request.getParameterValues("roleID");

                if (userID != 0)
                {
                        dao.removeUserRole(userID, orgID,
                                SecurityConstants.USER_ORGAN_RELATION);

                        if (roleID.length > 0)
                        {
                                for (int i = 0; i < roleID.length; i++)
                                {
                                        UserRoleForm urf = new UserRoleForm();
                                        urf.setRoleID(Integer.parseInt(roleID[i]));
                                        urf.setUserID(userID);
                                        urf.setRelationID(orgID);
                                        urf.setType(SecurityConstants.USER_ORGAN_RELATION);
                                        dao.addUserRole(urf);
                                }
                        }

                        /*
                          int lmsUserID = Integer.parseInt((String)request.getSession().getAttribute(LMSConstants.USERID_KEY));
                          int lmsOrgID =  Integer.parseInt((String)request.getSession().getAttribute(LMSConstants.USER_ORGID_KEY));
                          LmslogForm lmslogForm = new LmslogForm();
                          lmslogForm.setLogType(LmslogConstants.LOGTYPE_USER);
                          lmslogForm.setUserID(lmsUserID);
                          lmslogForm.setOrgID(lmsOrgID);
                          lmslogForm.setUserIP(request.getRemoteAddr());
                          lmslogForm.setOperationType(LmslogConstants.OPERATION_SECURETY_CHANGPWD);
                          lmslogForm.setOperationTable("LMSDB");
                          lmslogForm.setOperationObjectID(lmsUserID);
                          LmslogDAO logdao = LmslogDAOFactory.getDAO();
                          logdao.insert(lmslogForm);
                        */
                }

                return mapping.findForward(resultScreen);
        }
}
