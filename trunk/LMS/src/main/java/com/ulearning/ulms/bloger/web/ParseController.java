/*
 * Created on 2004-10-9
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.domain.Account;
import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.*;


/**
 * To redirect "/!username.c" to "/home.c?accountId=###"
 *
 * @author Huaxia
 */
public class ParseController implements Controller
{
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                String uri = request.getRequestURI();
                int n = uri.lastIndexOf("~");
                int m = uri.lastIndexOf(".c");
                String username = uri.substring(n + 1, m);
                System.out.println("username = " + username);

                Account account = facade.getAccount(username);
                response.sendRedirect("home.c?accountId=" + account.getAccountId());

                return null;
        }
}
