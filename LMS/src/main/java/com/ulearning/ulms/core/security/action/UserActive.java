/**
 * UserActive.java.
 * User: ibm Date: 2005-8-12 9:21:38
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.form.STicket;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserActive extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String userID = request.getParameter("userID");
                UserForm uf = UserHelper.getUser(userID);

                uf.setAvailable("1");

                SecurityHelper.setIsAvailable(uf);

                STicket ticket = SecurityHelper.getTicket(Integer.parseInt(userID));
                request.getSession().setAttribute(LMSConstants.TICKET_KEY, ticket);
                request.getSession()
                        .setAttribute(LMSConstants.USERID_KEY,
                                new Integer(userID).toString());
                request.getSession()
                        .setAttribute(LMSConstants.LOGINNAME_KEY, uf.getLoginName());
                request.getSession()
                        .setAttribute(LMSConstants.USER_ORGID_KEY,
                                new Integer(ticket.getOrgID()).toString());
                request.getSession()
                        .setAttribute(LMSConstants.USER_ASPID_KEY,
                                new Integer(ticket.getAspID()).toString());
                //add  zhangy  start
                request.getSession()
                        .setAttribute(LMSConstants.LOGINNAME_KEY_NAME, uf.getName());

                return mapping.findForward(resultScreen);
        }
}
