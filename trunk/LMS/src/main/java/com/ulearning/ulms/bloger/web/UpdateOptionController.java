/*
 * Created on 2004-10-11
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.domain.Account;
import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * Update the account.
 *
 * @author Huaxia
 */
public class UpdateOptionController implements Controller
{
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                Identity id = Identity.getIdentity(request);
                Account account = facade.getAccount(id.getAccountId());
                account.setTitle(request.getParameter("title"));
                account.setSubtitle(request.getParameter("subtitle"));
                account.setDescription(request.getParameter("description"));
                account.setEmail(request.getParameter("email"));

                account.setFirstName(request.getParameter("firstName"));
                account.setLastName(request.getParameter("lastName"));
                account.setGender("1".equals(request.getParameter("gender")) ? true
                        : false);

                account.setSkinId(Integer.parseInt(request.getParameter("skinId")));
                account.setMaxPerPage(Integer.parseInt(request.getParameter(
                        "maxPerPage")));
                account.setNews(request.getParameter("news"));
                account.setCss(request.getParameter("css"));
                // update:
                facade.updateAccount(id, account);

                // result:
                Map map = new HashMap();
                map.put("account", account);
                map.put("position", "管理 >> 结果");
                map.put("message", "帐号已经更新!");
                map.put("url", "manageOption.c");

                return new ModelAndView("manage/result", map);
        }
}
