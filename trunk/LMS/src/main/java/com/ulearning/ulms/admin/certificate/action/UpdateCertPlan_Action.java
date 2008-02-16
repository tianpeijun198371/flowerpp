/**
 * UpdateCertPlan_Action.java.
 * User: liz  Date: 2006-3-14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.action;

import com.ulearning.ulms.admin.certificate.bean.CertPlanHelper;
import com.ulearning.ulms.admin.certificate.form.CertPlanForm;
import com.ulearning.ulms.content.helper.PhysicsResHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateCertPlan_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String cpacotid = request.getParameter("cpacotid");

                //List resList=PhysicsResHelper.getResAllUsed("0","1");//可使用的教室
                List sertList = CertPlanHelper.getAllCertPlanByid(cpacotid);
                //request.setAttribute("resList",resList);
                request.setAttribute("sertList", sertList);

                return mapping.findForward("success");
        }
}
