/*
 * Created on 2004-10-9
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
 * List the images of the specified category.
 *
 * @author Huaxia
 */
public class ManageImageController implements Controller
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
                int categoryId = (-1);
                String s_categoryId = request.getParameter("categoryId");

                if ((s_categoryId != null) && !s_categoryId.equals(""))
                {
                        categoryId = Integer.parseInt(s_categoryId);
                }

                // get images:
                List images = null;

                if (categoryId != (-1))
                {
                        images = facade.getImages(categoryId);
                }
                else
                {
                        images = Collections.EMPTY_LIST;
                }

                // get categories:
                List categories = facade.getCategoriesOfType(accountId,
                        Category.TYPE_IMAGES);

                Map map = new HashMap();
                map.put("categories", categories);
                map.put("categoryId", new Integer(categoryId));
                map.put("images", images);
                map.put("total", new Integer(images.size()));
                map.put("account", facade.getAccount(accountId));
                map.put("position", "¹ÜÀí >> Í¼Æ¬");

                return new ModelAndView("manage/image-list", map);
        }
}
