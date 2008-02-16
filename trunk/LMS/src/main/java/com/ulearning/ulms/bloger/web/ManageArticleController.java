/*
 * Created on 2004-10-8
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * To manage the articles. url=/manageArticle.c?page=###
 *
 * @author Huaxia
 */
public class ManageArticleController implements Controller
{
        private static final int MAX_PER_PAGE = 20;
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                Identity id = Identity.getIdentity(request);
                int page = 1;
                String s_page = request.getParameter("page");

                if ((s_page != null) && !s_page.equals(""))
                {
                        page = Integer.parseInt(s_page);
                }

                // now get the article list:
                List articles = facade.getArticles(id.getAccountId(), MAX_PER_PAGE, page);
                Account account = facade.getAccount(id.getAccountId());
                List categories = facade.getCategoriesOfArticle(id.getAccountId());
                int count = account.getArticles();
                int pages = (count / MAX_PER_PAGE) +
                        (((count % MAX_PER_PAGE) == 0) ? 0 : 1);

                // create model:
                Map map = new HashMap();
                map.put("account", account);
                map.put("articles", articles);
                map.put("page", new Integer(page));
                map.put("pages", new Integer(pages));
                map.put("categories", categories);
                map.put("position", "¹ÜÀí >> ÎÄÕÂ");

                return new ModelAndView("manage/article-list", map);
        }
}
