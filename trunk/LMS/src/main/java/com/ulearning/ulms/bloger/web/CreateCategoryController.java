/*
 * Created on 2004-10-5
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
 * Create a new category, if succeed, go to manageCategory.c
 *
 * @author Huaxia
 */
public class CreateCategoryController implements Controller
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

                // set category info:
                Category category = new Category();
                category.setTitle(request.getParameter("title"));
                category.setVisible("0".equals(request.getParameter("visible")) ? false
                        : true);

                String description = request.getParameter("description");
                category.setDescription((description == null) ? "" : description);
                category.setType(Integer.parseInt(request.getParameter("type")));
                // create it:
                facade.createCategory(id, category);

                // show result:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("message", "\"" + category.getTitle() + "\" 已经建立!");
                map.put("url", "manageCategory.c?type=" + category.getType());
                map.put("position", "管理 >> 结果");

                return new ModelAndView("manage/result", map);
        }
}
