/*
 * Created on 2004-10-4
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
 * To view the specified category.
 *
 * @author Huaxia
 */
public class ViewCategoryController implements Controller
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
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                String s_page = request.getParameter("page");
                int page = 1;

                if ((s_page != null) && !s_page.equals(""))
                {
                        page = Integer.parseInt(s_page);
                }

                Account account = facade.getAccountByCategory(categoryId);
                int accountId = account.getAccountId();
                List categories = facade.getCategories(accountId);
                List articles = facade.getArticlesByCategory(categoryId,
                        account.getMaxPerPage(), page);
                int total = facade.getArticlesCount(categoryId);
                List links = facade.getAllLinks(accountId);

                Map map = new HashMap();
                map.put("account", account);
                map.put("categories", categories);

                Iterator it = categories.iterator();

                while (it.hasNext())
                {
                        Category c = (Category) it.next();

                        if (c.getCategoryId() == categoryId)
                        {
                                map.put("category", c);

                                break;
                        }
                }

                map.put("articles", articles);
                map.put("links", links);
                map.put("total", new Integer(total));

                int per = account.getMaxPerPage();
                int pages = (total / per) + (((total % per) == 0) ? 0 : 1);
                map.put("page", new Integer(page));
                map.put("pages", new Integer(pages));

                return new ModelAndView(skin.getSkin(account.getSkinId()) + "category",
                        map);
        }
}
