/*
 * Created on 2004-10-8
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * Delete an article.
 *
 * @author Huaxia
 */
public class DeleteArticleController implements Controller
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
                int articleId = Integer.parseInt(request.getParameter("articleId"));
                // delete it:
                facade.deleteArticle(id, articleId);

                // show result:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("message", "文章已经被删除!");
                map.put("url", "manageArticle.c");
                map.put("position", "管理 >> 管理");

                return new ModelAndView("manage/result", map);
        }
}
