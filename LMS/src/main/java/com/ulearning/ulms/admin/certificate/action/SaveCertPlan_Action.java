/**
 * SaveCertPlan_Action.java.
 * User: liz  Date: 2006-3-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
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


public class SaveCertPlan_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                CertPlanForm frm = (CertPlanForm) form;
                //System.out.println("����======================="+frm.getAsscpComeDate());
                frm.setCpComeDate(DateTimeUtil.parseDate(frm.getAsscpComeDate())); //����JSP���յ�����ת����FRM��
                frm.setCpGoDate(DateTimeUtil.parseDate(frm.getAsscpGoDate())); //����JSP���յ�����ת����FRM��
                //System.out.println("����2================="+frm.getAsscpGoDate());

                CertPlanHelper.updateCertPlan(frm);

                //System.out.println("���=============================");
                return mapping.findForward("success");
        }
}
