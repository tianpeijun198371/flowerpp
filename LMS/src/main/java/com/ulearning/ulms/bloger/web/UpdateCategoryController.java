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
public class UpdateCategoryController implements Controller
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
                Category category = facade.getCategory(Integer.parseInt(
                        request.getParameter("categoryId")));
                category.setTitle(request.getParameter("title"));
                category.setVisible("0".equals(request.getParameter("visible")) ? false
                        : true);
                category.setDescription(request.getParameter("description"));
                facade.updateCategory(id, category);

                // model:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("message", "\"" + category.getTitle() + "\" 已经更新!");
                map.put("url", "manageCategory.c?type=" + category.getType());
                map.put("position", "管理 >> 结果");

                return new ModelAndView("manage/result", map);
        }
}
