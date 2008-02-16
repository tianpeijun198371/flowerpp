/**
 * certChangeStat_action.java.
 * User: liz  Date: 2006-1-9
 * Stat course of certificate action
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.stat.action;

import com.ulearning.ulms.finance.helper.FinanceReporterHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class certChangeStat_action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                certChangeStat_form frm = (certChangeStat_form) form;
                FinanceReporterHelper help = new FinanceReporterHelper();
                List courseList = help.getCertCourseCharge(frm.getCerid(),
                        frm.getTypeid());
                //System.out.println("courseList:"+courseList.size());
                request.setAttribute("frm", courseList);

                return mapping.findForward("successcert");
        }
}
