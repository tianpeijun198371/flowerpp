// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packfields(3) packimports(7) deadcode fieldsfirst splitstr(64) nonlb lnc radix(10) lradix(10) 
// Source File Name:   DelCertPlanAction.java
package com.ulearning.ulms.admin.certificate.action;

import com.ulearning.ulms.admin.certificate.bean.CertPlanHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelCertPlanAction extends Action
{
        public DelCertPlanAction()
        {
        }

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                /*  29*/
                String[] cpacotid = request.getParameterValues("cpacotid");

                /*  30*/
                if (null != cpacotid)
                {
                        /*  31*/
                        System.out.println("cpacotidLen==" + cpacotid.length);
                }
                /*  33*/
                else
                {
                        /*  33*/
                        System.out.println("ID?NULL");
                }

                /*  38*/
                for (int i = 0; i < cpacotid.length; i++)
                {
                        /*  40*/
                        System.out.println("cpacotid:" + cpacotid[i]);
                        /*  41*/
                        CertPlanHelper.removeCertPlan(cpacotid[i]);
                }

                /*  43*/
                return mapping.findForward("success");
        }
}
