/*
 * Created on 2004-10-13
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.logic.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * The site page.
 *
 * @author Huaxia
 */
public class SiteController implements Controller
{
        private Facade facade;
        private Map map;
        private int count = 0;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                count--;

                // refresh every 20 requests:
                if (count <= 0)
                {
                        count = 20;

                        List accounts = facade.getRecentAccounts(25);
                        List articles = facade.getRecentArticlesInfo(25);
                        map = new HashMap();
                        map.put("accounts", accounts);
                        map.put("articles", articles);
                }

                return new ModelAndView("site", map);
        }
}
