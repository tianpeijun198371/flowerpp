/*
 * Created on 2004-10-10
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * Delete a link.
 *
 * @author Huaxia
 */
public class DeleteLinkController implements Controller
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
                int linkId = Integer.parseInt(request.getParameter("linkId"));
                // delete it:
                facade.deleteLink(id, linkId);

                // result:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("position", "管理 >> 结果");
                map.put("url", "manageLink.c");
                map.put("message", "链接已经成功删除!");

                return new ModelAndView("manage/result", map);
        }
}
