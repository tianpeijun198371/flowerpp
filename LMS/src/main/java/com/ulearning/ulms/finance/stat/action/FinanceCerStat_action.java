/**
 * FinanceCerStat_action.java.
 * User: liz  Date: 2006-1-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.stat.action;

import com.ulearning.ulms.finance.form.FinanceCerReportForm;
import com.ulearning.ulms.finance.helper.FinanceReporterHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 班级财务统计
 */
public class FinanceCerStat_action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                FinanceCerStat_form frm = (FinanceCerStat_form) form;
                List list = null;
                String entityname = "";
                String forward = "";
                String scatalogID = request.getParameter("scatalogID");

                if (1 == frm.getStattype()) //班级使用
                {
                        FinanceReporterHelper help = new FinanceReporterHelper();
                        list = help.getCerreportOrg(frm.getBeginDate(), frm.getEndDate(),
                                frm.getCerid(), scatalogID);
                        entityname = ((FinanceCerReportForm) list.get(0)).getCername();
                        forward = "successcer";
                }
                else if (2 == frm.getStattype()) //课程调用
                {
                        FinanceReporterHelper help = new FinanceReporterHelper();
                        list = help.getCoursereport(frm.getBeginDate(), frm.getEndDate(),
                                frm.getCerid());
                        entityname = ((FinanceCerReportForm) list.get(0)).getCername();
                        forward = "successCourse";
                }

                request.setAttribute("entityname", entityname); //名称
                request.setAttribute("frm", list);

                return mapping.findForward(forward);
        }
}
