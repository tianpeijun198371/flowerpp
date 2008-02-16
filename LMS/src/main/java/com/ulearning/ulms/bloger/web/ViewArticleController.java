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
 * To view the content of the article.
 *
 * @author Huaxia
 */
public class ViewArticleController implements Controller
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
                int articleId = Integer.parseInt(request.getParameter("articleId"));

                Article article = facade.getArticle(articleId);
                int accountId = article.getAccountId();
                Account account = facade.getAccount(accountId);
                List categories = facade.getCategories(accountId);
                List links = facade.getAllLinks(accountId);
                List feedbacks = facade.getFeedbacks(articleId);

                Map map = new HashMap();
                map.put("article", article);
                map.put("feedbacks", feedbacks);
                map.put("account", account);
                map.put("categories", categories);
                map.put("links", links);

                return new ModelAndView(skin.getSkin(account.getSkinId()) + "article",
                        map);
        }
}
