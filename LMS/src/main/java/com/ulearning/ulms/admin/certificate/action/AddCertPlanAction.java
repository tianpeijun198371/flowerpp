package com.ulearning.ulms.admin.certificate.action;

import com.ulearning.ulms.admin.certificate.bean.CertPlanHelper;
import com.ulearning.ulms.admin.certificate.form.CertPlanForm;
import com.ulearning.ulms.core.util.DateTimeUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddCertPlanAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                CertPlanForm frm = (CertPlanForm) form;
                frm.setCpComeDate(DateTimeUtil.parseDate(frm.getAsscpComeDate())); //将从JSP接收的日期转化到FRM中
                frm.setCpGoDate(DateTimeUtil.parseDate(frm.getAsscpGoDate())); //将从JSP接收的日期转化到FRM中
                CertPlanHelper.insertCertPlan(frm);

                return mapping.findForward("success");
        }
}
