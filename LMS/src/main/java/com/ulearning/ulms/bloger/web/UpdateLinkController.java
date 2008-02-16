/*
 * Created on 2004-10-10
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
 * Update the link.
 *
 * @author Huaxia
 */
public class UpdateLinkController implements Controller
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

                // get link:
                Link link = facade.getLink(Integer.parseInt(request.getParameter(
                        "linkId")));
                link.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
                link.setTitle(request.getParameter("title"));
                link.setUrl(request.getParameter("url"));
                link.setRss(request.getParameter("rss"));
                link.setNewWindow("0".equals(request.getParameter("newWindow")) ? false
                        : true);
                facade.updateLink(id, link);

                // create model:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("position", "管理 >> 结果");
                map.put("message", "链接 \"" + link.getTitle() + "\" 已经更新!");
                map.put("url", "manageLink.c");

                return new ModelAndView("manage/result", map);
        }
}
