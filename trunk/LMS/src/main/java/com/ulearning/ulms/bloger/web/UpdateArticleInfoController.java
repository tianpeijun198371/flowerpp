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
public class UpdateArticleInfoController implements Controller
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

                // get article:
                int articleId = Integer.parseInt(request.getParameter("articleId"));
                Article article = facade.getArticleInfo(articleId);
                article.setTitle(request.getParameter("title"));
                article.setCategoryId(Integer.parseInt(request.getParameter(
                        "categoryId")));
                article.setType(Integer.parseInt(request.getParameter("type")));
                article.setVisible("0".equals(request.getParameter("visible")) ? false
                        : true);
                article.setUpdatedDate(new Date());

                facade.updateArticleInfo(id, article);

                // create model:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("url", "manageArticle.c");
                map.put("position", "管理 >> 结果");
                map.put("message", "文章 \"" + article.getTitle() + "\" 已经保存!");

                return new ModelAndView("manage/result", map);
        }
}
