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
 * To view an image. URL=/viewImage.c?accountId=###&imageId=###
 *
 * @author Huaxia
 */
public class ViewImageController implements Controller
{
        private SkinManager skin = SkinManager.getSkinManager();
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                int imageId = Integer.parseInt(request.getParameter("imageId"));

                // get model:
                Image image = facade.getImage(imageId);
                Category category = facade.getCategory(image.getCategoryId());
                Account account = facade.getAccount(category.getAccountId());
                List categories = facade.getCategories(account.getAccountId());
                List links = facade.getAllLinks(account.getAccountId());

                // create model:
                Map map = new HashMap();
                map.put("account", account);
                map.put("categories", categories);
                map.put("links", links);
                map.put("image", image);

                return new ModelAndView(skin.getSkin(account.getSkinId()) + "image", map);
        }
}
