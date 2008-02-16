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
 * TO preview the images in the gallery. URL=/viewGallery.c?accountId=###&galleryId=###
 *
 * @author Huaxia
 */
public class ViewGalleryController implements Controller
{
        private static final int IMAGES_PER_PAGE = 20;
        private SkinManager skin = SkinManager.getSkinManager();
        private Facade facade;

        public void setFacade(Facade facade)
        {
                this.facade = facade;
        }

        public ModelAndView handleRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
        {
                // get the category:
                int categoryId = Integer.parseInt(request.getParameter("galleryId"));
                Category category = facade.getCategory(categoryId);

                // and the account:
                Account account = facade.getAccount(category.getAccountId());

                // and the page:
                int page = 1;
                String s_page = request.getParameter("page");

                if ((s_page != null) && !s_page.equals(""))
                {
                        page = Integer.parseInt(s_page);
                }

                // get model:
                List categories = facade.getCategories(account.getAccountId());
                List links = facade.getAllLinks(account.getAccountId());
                List images = facade.getImages(categoryId, IMAGES_PER_PAGE, page);
                int total = facade.getImagesCount(categoryId);

                // create model:
                Map map = new HashMap();
                map.put("account", account);
                map.put("categories", categories);
                map.put("category", category);
                map.put("links", links);
                map.put("images", images);
                map.put("imageCount", new Integer(images.size()));
                map.put("total", new Integer(total));
                map.put("page", new Integer(page));

                int pages = (total / IMAGES_PER_PAGE) +
                        (((total % IMAGES_PER_PAGE) == 0) ? 0 : 1);
                map.put("pages", new Integer(pages));

                return new ModelAndView(skin.getSkin(account.getSkinId()) + "gallery",
                        map);
        }
}
