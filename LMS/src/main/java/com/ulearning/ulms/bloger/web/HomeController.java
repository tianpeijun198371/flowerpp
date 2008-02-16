/*
 * Created on 2004-10-3
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
 * This is the home page of the blog. URL=/home.c?accountId=###
 *
 * @author Huaxia
 */
public class HomeController implements Controller
{
        private SkinManager skin = SkinManager.getSkinManager();
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                // get the parameters:
                int accountId = Integer.parseInt(request.getParameter("accountId"));
                String s_page = request.getParameter("page");
                int page = 1;

                if ((s_page != null) && !s_page.equals(""))
                {
                        page = Integer.parseInt(s_page);
                }

                // get the model:
                Account account = facade.getAccount(accountId);
                List categories = facade.getCategories(accountId);
                List articles = facade.getArticles(accountId, account.getMaxPerPage(),
                        page);
                List links = facade.getAllLinks(accountId);

                // create model:
                Map map = new HashMap();
                map.put("account", account);
                map.put("categories", categories);
                map.put("links", links);
                map.put("articles", articles);

                int all = account.getArticles();
                int per = account.getMaxPerPage();
                int pages = (all / per) + (((all % per) == 0) ? 0 : 1);
                map.put("pages", new Integer(pages));
                map.put("page", new Integer(page));

                return new ModelAndView(skin.getSkin(account.getSkinId()) + "home", map);
        }
}
