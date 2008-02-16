/*
 * Created on 2004-10-14
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.*;


/**
 * Send a message to the specified account.
 *
 * @author Huaxia
 */
public class SendMessageController implements Controller
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
                int accountId = Integer.parseInt(request.getParameter("accountId"));
                Message message = new Message();
                message.setAccountId(accountId);
                message.setSender(request.getParameter("sender"));
                message.setEmail(request.getParameter("email"));
                message.setSubject(request.getParameter("subject"));
                message.setContent(request.getParameter("content"));
                facade.sendMessage(message);

                Account account = facade.getAccount(accountId);

                return new ModelAndView(skin.getSkin(account.getSkinId()) + "sent",
                        "account", account);
        }
}
