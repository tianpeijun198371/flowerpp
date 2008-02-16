/**
 * addcertreportAction.java.
 * User: liz  Date: 2006-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.general.bean;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.report.general.model.RCertreportForm;

import java.util.List;


public class addcertreportAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                //System.out.println("OK====================");
                String certname = request.getParameter("remark1");
                String[] certrname = request.getParameterValues("certrname");
                String[] certrwork = request.getParameterValues("certrwork");
                String[] certrsum = request.getParameterValues("certrsum");
                String[] certrpaymark = request.getParameterValues("certrpaymark");
                String[] certrpaysum = request.getParameterValues("certrpaysum");
                String[] certrseemark = request.getParameterValues("certrseemark");
                String[] certrprepaymode = request.getParameterValues("certrprepaymode");
                String[] certrprepaysum = request.getParameterValues("certrprepaysum");
                String[] certrinvomark = request.getParameterValues("certrinvomark");
                String[] certrposcode = request.getParameterValues("certrposcode");
                String[] certrprovince = request.getParameterValues("certrprovince");
                String[] certrmemo = request.getParameterValues("certrmemo");

                String scertrname = "";
                String scertrwork = "";
                String scertrsum = "0";
                String scertrpaymark = "0";
                String scertrpaysum = "0";
                String scertrseemark = "0";
                String scertrprepaymode = "0";
                String scertrprepaysum = "0";
                String scertrinvomark = "0";
                String scertrposcode = "";
                String scertrprovince = "";
                String scertrmemo = "";

                RCertreportForm frm = new RCertreportForm();
                RCertHelper help = new RCertHelper();
                //System.out.println("certrname============="+certrname);
                for (int i = 0; null != certrname && i < certrname.length; i++)
                {


                        frm.setCertrid(0);
                        if (null != certrinvomark[i] && !(certrinvomark[i]).equals(""))
                        {
                                scertrinvomark = (String) certrinvomark[i];
                        }
                        if (null != (String) certrmemo[i] && !(certrmemo[i]).equals(""))
                        {
                                scertrmemo = (String) certrmemo[i];
                        }
                        if (null != (String) certrname[i] && !(certrname[i]).equals(""))
                        {
                                scertrname = (String) certrname[i];
                        }
                        if (null != (String) certrpaymark[i] && !(certrpaymark[i]).equals(""))
                        {
                                scertrpaymark = (String) certrpaymark[i];
                        }
                        if (null != (String) certrpaysum[i] && !(certrpaysum[i]).equals(""))
                        {
                                scertrpaysum = (String) certrpaysum[i];
                        }
                        if (null != (String) certrposcode[i] && !(certrposcode[i]).equals(""))
                        {
                                scertrposcode = (String) certrposcode[i];
                        }
                        if (null != (String) certrprepaymode[i] && !(certrprepaymode[i]).equals(""))
                        {
                                scertrprepaymode = (String) certrprepaymode[i];
                        }
                        if (null != (String) certrprepaysum[i] && !(certrprepaysum[i]).equals(""))
                        {
                                scertrprepaysum = (String) certrprepaysum[i];
                        }

                        if (null != (String) certrprovince[i] && !(certrprovince[i]).equals(""))
                        {
                                scertrprovince = (String) certrprovince[i];
                        }

                        if (null != (String) certrseemark[i] && !((String) certrseemark[i]).equals(""))
                        {
                                scertrseemark = (String) certrseemark[i];
                        }
                        if (null != (String) certrsum[i] && !((String) certrsum[i]).equals(""))
                        {
                                scertrsum = (String) certrsum[i];
                        }
                        if (null != (String) certrwork[i] && !((String) certrwork[i]).equals(""))
                        {
                                scertrwork = (String) certrwork[i];
                        }


                        frm.setRemark1(certname);
                        frm.setCertrwork(scertrwork);
                        frm.setCertrsum(Double.parseDouble(scertrsum));
                        frm.setCertrseemark(Integer.parseInt(scertrseemark));
                        frm.setCertrprovince(scertrprovince);
                        frm.setCertrprepaysum(Double.parseDouble(scertrprepaysum));
                        frm.setCertrprepaymode(Integer.parseInt(scertrprepaymode));
                        frm.setCertrposcode(scertrposcode);
                        frm.setCertrpaysum(Double.parseDouble(scertrpaysum));
                        frm.setCertrpaymark(Integer.parseInt(scertrpaymark));
                        frm.setCertrname(scertrname);
                        frm.setCertrmemo(scertrmemo);
                        frm.setCertrinvomark(Integer.parseInt(scertrinvomark));

                        //System.out.println("OK111====================");

                        //System.out.println("OK2222====================");
                        help.insterData(frm);
                }
                return mapping.findForward("success");
        }
}
