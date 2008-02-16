/**
 * addOrderAction.java.
 * User: liz  Date: 2006-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.content.schoolbook.bean.OrderHelper;
import com.ulearning.ulms.content.schoolbook.form.OrderForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class addOrderAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                OrderForm frm = (OrderForm) form;
                OrderHelper help = new OrderHelper();
                help.insterData(frm);

                return mapping.findForward("success");
        }
}
