/*
 * Created on 2004-10-11
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.logic.Facade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.*;

import javax.servlet.http.*;


/**
 * Change the user's password.
 *
 * @author Huaxia
 */
public class ChangePasswordController implements Controller
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
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                String newPassword2 = request.getParameter("newPassword2");
                // change password:
                facade.changePassword(id, oldPassword, newPassword, newPassword2);

                // result:
                Map map = new HashMap();
                map.put("account", facade.getAccount(id.getAccountId()));
                map.put("message", "已经成功更新选项!");
                map.put("position", "管理 >> 结果");
                map.put("url", "manageOption.c");

                return new ModelAndView("manage/result", map);
        }
}
