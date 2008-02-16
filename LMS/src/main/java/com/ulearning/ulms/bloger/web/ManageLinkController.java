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
 * Display all links.
 *
 * @author Huaxia
 */
public class ManageLinkController implements Controller
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
                List categories = facade.getCategoriesOfType(accountId,
                        Category.TYPE_LINKS);
                List links = facade.getAllLinks(accountId);

                // create model:
                Map map = new HashMap();
                map.put("account", facade.getAccount(accountId));
                map.put("categories", categories);
                map.put("links", links);
                map.put("position", "¹ÜÀí >> Á´½Ó");

                return new ModelAndView("manage/link-list", map);
        }
}
