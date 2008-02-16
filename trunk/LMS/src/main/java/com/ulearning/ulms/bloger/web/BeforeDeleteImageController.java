/*
 * Created on 2004-10-13
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * Prompt before delete an image.
 *
 * @author Huaxia
 */
public class BeforeDeleteImageController implements Controller
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
                int imageId = Integer.parseInt(request.getParameter("imageId"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                Map map = new HashMap();
                map.put("account", facade.getAccount(accountId));
                map.put("position", "管理 >> 提示");
                map.put("yesUrl",
                        "deleteImage.c?imageId=" + imageId + "&categoryId=" + categoryId);
                map.put("noUrl", "manageImage.c");
                map.put("message", "你确认要删除这张图片吗? 这个操作将不能恢复.");

                return new ModelAndView("manage/prompt", map);
        }
}
