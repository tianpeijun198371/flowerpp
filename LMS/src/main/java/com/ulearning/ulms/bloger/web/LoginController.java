/*
 * Created on 2004-10-4
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.*;


/**
 * For user login. Parameter: String username; String password.
 * If succeed, forward to "manage.jsp".
 *
 * @author Huaxia
 */
public class LoginController implements Controller
{
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Identity identity = facade.login(username, password);
                // if login successfully, mark in the session:
                request.getSession().setAttribute(Identity.IDENTITY, identity);
                response.sendRedirect("manageCategory.c");

                return null;
        }
}
