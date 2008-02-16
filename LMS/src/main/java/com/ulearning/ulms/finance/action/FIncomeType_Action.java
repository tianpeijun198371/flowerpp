/**
 * FIncomeType_Action.java.
 * User: liz  Date: 2005-12-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.finance.helper.IncomeTypeHelper;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;


/**
 * 取个人收入类型Action
 *
 * @author Liz
 * @ date 2005-12-21
 */
public class FIncomeType_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                HttpSession session = request.getSession();
                String aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                List certList = CertHelper.getAllCertList(Integer.parseInt(aspID), 0); //取培训班

                IncomeTypeHelper help = new IncomeTypeHelper();
                List list = help.getAllIncomeType(2);
                request.setAttribute("certList", certList);
                request.setAttribute("inType", list);

                if (request.getParameter("money-back") != null)
                {
                        request.setAttribute("money-back", "money-back");
                }

                return mapping.findForward("success");
        }
}
