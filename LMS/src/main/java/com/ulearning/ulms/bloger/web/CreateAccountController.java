/*
 * Created on 2004-10-3
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.domain.Account;
import com.ulearning.ulms.bloger.logic.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * To register a new account.
 *
 * @author Huaxia
 */
public class CreateAccountController implements Controller
{
        private String baseDir = null;
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                Account account = new Account();
                account.setUsername(request.getParameter("username"));
                account.setPassword(request.getParameter("password"));
                account.setEmail(request.getParameter("email"));
                account.setFirstName(request.getParameter("firstName"));
                account.setLastName(request.getParameter("lastName"));
                account.setGender("0".equals(request.getParameter("gender")) ? false
                        : true);
                account.setTitle(request.getParameter("title"));
                account.setSubtitle(request.getParameter("subtitle"));

                // default values:
                account.setMaxPerPage(10);
                account.setSkinId(0);
                account.setCreatedDate(new Date());

                // check:
                boolean succeed = false;
                String message = null;
                String password2 = request.getParameter("password2");
                account.debug();

                if ((password2 == null) || !password2.equals(account.getPassword()))
                {
                        message = "ÃÜÂë²»Ò»ÖÂ.";
                }
                else
                {
                        try
                        {
                                facade.createAccount(account);
                                succeed = true;
                                System.out.println("Account created successfully!");
                        }
                        catch (Exception e)
                        {
                                message = e.getMessage();
                        }
                }

                Map map = new HashMap();
                map.put("account", account);

                if (succeed)
                {
                        // succeeded:
                        if (baseDir == null)
                        {
                                synchronized (this)
                                {
                                        baseDir = request.getSession().getServletContext()
                                                .getRealPath("/upload/");

                                        if (!baseDir.endsWith("/") && !baseDir.endsWith("\\"))
                                        {
                                                baseDir = baseDir + "/";
                                        }

                                        System.out.println("[INFO] set account upload dir = " +
                                                baseDir);
                                }
                        }

                        // create a dir for this account:
                        new java.io.File(baseDir + account.getAccountId() + "/").mkdir();

                        return new ModelAndView("register-succeed", map);
                }

                // failed, so return to the registration form:
                map.put("message", message);

                return new ModelAndView("register-form", map);
        }
}
