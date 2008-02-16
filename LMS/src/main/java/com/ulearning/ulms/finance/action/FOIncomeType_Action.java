/**
 * FOIncomeType_Action.java.
 * User: liz  Date: 2005-12-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.finance.helper.IncomeTypeHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 取机构收入类型Action
 *
 * @author Liz
 * @ date 2005-12-21
 */
public class FOIncomeType_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                IncomeTypeHelper help = new IncomeTypeHelper();
                List list = help.getAllIncomeType(2);
                request.setAttribute("inType", list);

                return mapping.findForward("success");
        }
}
