/**
 * UpdateChargereportAction.java.
 * User: liz  Date: 2006-5-5
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.general.bean;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.report.general.model.RCertreportForm;
import com.ulearning.ulms.tools.report.general.model.RChargeitemForm;

public class UpdateChargereportAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                RChargeitemForm frm = (RChargeitemForm) form;
                RChargeHelper help = new RChargeHelper();
                //System.out.println("OK2222====================");
                help.updateData(frm);
                return mapping.findForward("success");
        }

}
