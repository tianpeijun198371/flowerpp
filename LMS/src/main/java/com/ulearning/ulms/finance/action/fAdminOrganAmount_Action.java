/**
 * fAdminOrganAmount_Action.java.
 * User: liz  Date: 2005-12-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.finance.helper.OrganAccountHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class fAdminOrganAmount_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                fAdminOrganAmount_Form frm = (fAdminOrganAmount_Form) form;
                String sName = frm.getOrganName();
                OrganAccountHelper help = new OrganAccountHelper();
                List list = null;
                list = help.getOMainAcotFrm(0, sName, 0);
                request.setAttribute("frm", list);

                return mapping.findForward("success");
        }
}
