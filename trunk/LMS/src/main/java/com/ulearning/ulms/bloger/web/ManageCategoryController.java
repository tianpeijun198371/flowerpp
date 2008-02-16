/*
 * Created on 2004-10-7
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
public class ManageCategoryController implements Controller
{
        private Facade facade;
        private String[] names = {"目录", "图片类型", "常用"};

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                // check if login:
                int accountId = Identity.getIdentity(request).getAccountId();
                Account account = facade.getAccount(accountId);
                String s_type = request.getParameter("type");
                int type = 0;

                if ((s_type != null) && !s_type.equals(""))
                {
                        type = Integer.parseInt(s_type);
                }

                if ((type < 0) || (type > 2))
                {
                        throw new IllegalArgumentException("Parameter \"type\" is invalid.");
                }

                List categories = facade.getCategoriesOfType(accountId, type);

                // model:
                Map map = new HashMap();
                map.put("account", account);
                map.put("categories", categories);
                map.put("position", "管理 >> " + names[type]);
                map.put("type", new Integer(type));

                return new ModelAndView("manage/category-list", map);
        }
}
