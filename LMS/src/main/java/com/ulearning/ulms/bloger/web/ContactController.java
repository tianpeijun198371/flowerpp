/*
 * Created on 2004-10-6
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.domain.Account;
import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * To contact the blogger.
 *
 * @author Huaxia
 */
public class ContactController implements Controller
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
                // get the parameters:
                int accountId = Integer.parseInt(request.getParameter("accountId"));
                Account account = facade.getAccount(accountId);

                // create model:
                Map map = new HashMap();
                map.put("account", account);

                return new ModelAndView(skin.getSkin(account.getSkinId()) + "contact",
                        map);
        }
}
