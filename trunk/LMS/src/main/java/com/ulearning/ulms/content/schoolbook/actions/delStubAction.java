/**
 * delStubAction.java.
 * User: liz  Date: 2006-5-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.content.schoolbook.bean.TeachbillHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class delStubAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String[] ID = request.getParameterValues("cpacotid");
                TeachbillHelper help = new TeachbillHelper();

                for (int i = 0; (null != ID) && (i < ID.length); i++)
                {
                        help.delMainAndItemData(ID[i]);
                }

                return mapping.findForward("success");
        }
}
