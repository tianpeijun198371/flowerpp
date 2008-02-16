/**
 * ConfigModuleAction.java.
 * User: huangsb  Date: 2004-12-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConfigModuleAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                SecurityDAO dao = SecurityDAOFactory.getDAO();
                String resultScreen = "success";
                String[] proID = request.getParameterValues("proID");
                String[] isAvailable = request.getParameterValues("isAvailable");

                for (int i = 0; i < proID.length; i++)
                {
                        dao.ConfigModule(proID[i], Integer.parseInt(isAvailable[i]));
                }

                return mapping.findForward(resultScreen);
        }
}
