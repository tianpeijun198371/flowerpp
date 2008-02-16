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
 * TODO Description here...
 *
 * @author Huaxia
 */
public class BeforeDeleteArticleController implements Controller
{
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                int accountId = Identity.getIdentity(request).getAccountId();
                int articleId = Integer.parseInt(request.getParameter("articleId"));
                String title = request.getParameter("title");
                Map map = new HashMap();
                map.put("account", facade.getAccount(accountId));
                map.put("position", "管理 >> 提示");
                map.put("yesUrl", "deleteArticle.c?articleId=" + articleId);
                map.put("noUrl", "manageArticle.c");
                map.put("message", "你确定要删除这篇文章吗 \"" + title + "\"? 这个操作将不能恢复.");

                return new ModelAndView("manage/prompt", map);
        }
}
