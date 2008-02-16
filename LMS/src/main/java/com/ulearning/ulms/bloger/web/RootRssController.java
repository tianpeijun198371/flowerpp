/*
 * Created on 2004-10-17
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.rsslibj.elements.Channel;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.io.PrintWriter;

import java.util.*;

import javax.servlet.http.*;


/**
 * Rss feed. path=http://server:port/.../rss.c?accountId=###.
 *
 * @author Huaxia
 */
public class RootRssController implements Controller
{
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                // get accountId:
                int accountId = Integer.parseInt(request.getParameter("accountId"));
                Account account = facade.getAccount(accountId);

                // create rss:
                Channel channel = new Channel();
                channel.setDescription(account.getDescription());

                String baseUrl = request.getRequestURL().toString();
                int n = baseUrl.lastIndexOf("/");
                baseUrl = baseUrl.substring(0, n);
                channel.setLink(baseUrl + "/home.c?accountId=" + accountId);
                channel.setTitle(account.getTitle());

                List articles = facade.getArticles(accountId, account.getMaxPerPage(), 1);
                Iterator it = articles.iterator();

                while (it.hasNext())
                {
                        Article article = (Article) it.next();
                        channel.addItem(baseUrl + "/article.c?articleId=" +
                                article.getArticleId(), article.getSummary(), article.getTitle());
                }

                // output xml:
                response.setContentType("text/xml");

                PrintWriter pw = response.getWriter();
                pw.print(channel.getFeed("rss"));
                pw.close();

                return null;
        }
}
