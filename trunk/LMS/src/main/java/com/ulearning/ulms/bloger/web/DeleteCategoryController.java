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
 * Delete the category.
 *
 * @author Huaxia
 */
public class DeleteCategoryController implements Controller
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
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                Category category = facade.getCategory(categoryId);
                // delete it:
                facade.deleteCategory(id, categoryId);

                // show result:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("message", "目录 \"" + category.getName() + "\" 已经被删除!");
                map.put("url", "manageCategory.c?type=" + category.getType());
                map.put("position", "管理 >> 结果");

                return new ModelAndView("manage/result", map);
        }
}
