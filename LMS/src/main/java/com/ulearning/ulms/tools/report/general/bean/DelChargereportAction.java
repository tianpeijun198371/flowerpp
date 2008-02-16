/**
 * DelChargereportAction.java.
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

public class DelChargereportAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {

                String[] chargeid = request.getParameterValues("chargeid");

                for (int i = 0; null != chargeid && i < chargeid.length; i++)
                {

                        RChargeHelper help = new RChargeHelper();
                        //System.out.println("OK2222====================");
                        help.delData(Integer.parseInt((String) chargeid[i]));
                }
                return mapping.findForward("success");
        }


}
