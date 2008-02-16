/*
 * Created on 2004-10-4
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.*;


/**
 * For user logout. Parameter: none. Forward to "home.jsp".
 *
 * @author Huaxia
 */
public class LogoutController implements Controller
{
        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                int accountId = Identity.getIdentity(request).getAccountId();
                request.getSession().invalidate();
                response.sendRedirect("home.c?accountId=" + accountId);

                return null;
        }
}
