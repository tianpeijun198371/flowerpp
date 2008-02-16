/**
 * financeOrganSearch_Action.java.
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


public class financeOrganSearch_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                //HttpSession session = request.getSession();
                financeOrganSearch_Form frmQuery = (financeOrganSearch_Form) form;
                int detailType = frmQuery.getDetailType();
                String bDate = frmQuery.getBeginDate();
                String eDate = frmQuery.getEndDate();
                int organID = Integer.parseInt(frmQuery.getOrgId());
                List list = null;

                //UserAccountDetailForm frmResult = null;
                OrganAccountHelper help = new OrganAccountHelper();
                list = help.getUADetailENameForUID(organID, bDate, eDate, detailType);
                request.setAttribute("frm", list);

                return mapping.findForward("success");
        }
}
