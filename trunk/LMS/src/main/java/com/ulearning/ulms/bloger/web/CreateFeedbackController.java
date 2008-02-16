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
 * TO create a feedback. URL=/createFeedback.c?accountId=###&articleId=###
 *
 * @author Huaxia
 */
public class CreateFeedbackController implements Controller
{
        // TODO...
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                int articleId = Integer.parseInt(request.getParameter("articleId"));
                int accountId = Integer.parseInt(request.getParameter("accountId"));
                Article article = facade.getArticle(articleId);

                if (article.getAccountId() != accountId)
                {
                        throw new IllegalArgumentException();
                }

                // create a new feedback:
                Feedback feedback = new Feedback();
                feedback.setUsername(request.getParameter("username"));
                feedback.setUrl(request.getParameter("url"));
                feedback.setContent(request.getParameter("content"));
                feedback.setArticleId(articleId);
                feedback.setCreatedDate(new Date());
                // create:
                facade.createFeedback(feedback);
                // forward to the "article.c"
                response.sendRedirect("article.c?articleId=" + articleId);

                return null;
        }
}
