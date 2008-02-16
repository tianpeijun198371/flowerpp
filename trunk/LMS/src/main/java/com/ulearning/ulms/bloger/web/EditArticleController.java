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
 * TODO Description here...
 *
 * @author Huaxia
 */
public class EditArticleController implements Controller
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
                boolean create = "true".equals(request.getParameter("create"));
                Article article = null;

                if (!create)
                {
                        // update article:
                        int articleId = Integer.parseInt(request.getParameter("articleId"));
                        article = facade.getArticle(articleId);
                }
                else
                {
                        article = new Article();
                }

                // create model:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("categories", facade.getCategoriesOfArticle(id.getAccountId()));
                map.put("article", article);
                map.put("action", create ? "createArticle.c" : "updateArticle.c");
                map.put("position", create ? "管理 >> 撰写文章" : "管理 >> 编辑文章");

                return new ModelAndView("manage/article-edit", map);
        }
}
