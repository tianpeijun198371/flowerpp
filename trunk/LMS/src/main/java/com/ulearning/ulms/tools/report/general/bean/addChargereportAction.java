/**
 * addChargereportAction.java.
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

public class addChargereportAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                //System.out.println("OK====================");
                String certname = request.getParameter("remark1");
                String[] chargename = request.getParameterValues("chargename");
                String[] chargework = request.getParameterValues("chargework");
                String[] chargesendmode = request.getParameterValues("chargesendmode");
                String[] chargesum = request.getParameterValues("chargesum");
                String[] chargecircs = request.getParameterValues("chargecircs");
                String[] chargeinvomark = request.getParameterValues("chargeinvomark");
                String[] chargeposmark = request.getParameterValues("chargeposmark");
                String[] chargephone = request.getParameterValues("chargephone");
                String[] chargeposcode = request.getParameterValues("chargeposcode");
                String[] chargeprovince = request.getParameterValues("chargeprovince");
                String[] chargememo = request.getParameterValues("chargememo");


                String schargename = "";
                String schargework = "";
                String schargesendmode = "0";
                String schargesum = "0";
                String schargecircs = "0";
                String schargeinvomark = "0";
                String schargeposmark = "0";
                String schargephone = "";
                String schargeposcode = "";
                String schargeprovince = "";
                String schargememo = "";


                RChargeitemForm frm = new RChargeitemForm();
                RChargeHelper help = new RChargeHelper();
                //System.out.println("certrname============="+certrname);
                for (int i = 0; null != chargename && i < chargename.length; i++)
                {


                        frm.setChargeid(0);
                        if (null != chargename[i] && !(chargename[i]).equals(""))
                        {
                                schargename = (String) chargename[i];
                        }
                        if (null != (String) chargework[i] && !(chargework[i]).equals(""))
                        {
                                schargework = (String) chargework[i];
                        }
                        if (null != (String) chargesendmode[i] && !(chargesendmode[i]).equals(""))
                        {
                                schargesendmode = (String) chargesendmode[i];
                        }
                        if (null != (String) chargesum[i] && !(chargesum[i]).equals(""))
                        {
                                schargesum = (String) chargesum[i];
                        }
                        if (null != (String) chargecircs[i] && !(chargecircs[i]).equals(""))
                        {
                                schargecircs = (String) chargecircs[i];
                        }
                        if (null != (String) chargeinvomark[i] && !(chargeinvomark[i]).equals(""))
                        {
                                schargeinvomark = (String) chargeinvomark[i];
                        }
                        if (null != (String) chargeposmark[i] && !(chargeposmark[i]).equals(""))
                        {
                                schargeposmark = (String) chargeposmark[i];
                        }
                        if (null != (String) chargephone[i] && !(chargephone[i]).equals(""))
                        {
                                schargephone = (String) chargephone[i];
                        }

                        if (null != (String) chargeposcode[i] && !(chargeposcode[i]).equals(""))
                        {
                                schargeposcode = (String) chargeposcode[i];
                        }

                        if (null != (String) chargeprovince[i] && !((String) chargeprovince[i]).equals(""))
                        {
                                schargeprovince = (String) chargeprovince[i];
                        }
                        if (null != (String) chargememo[i] && !((String) chargememo[i]).equals(""))
                        {
                                schargememo = (String) chargememo[i];
                        }


                        frm.setRemark1(certname);
                        frm.setChargecircs(Integer.parseInt(schargecircs));
                        frm.setChargeinvomark(Integer.parseInt(schargeinvomark));
                        frm.setChargememo(schargememo);
                        frm.setChargename(schargename);
                        frm.setChargephone(schargephone);
                        frm.setChargeposcode(schargeposcode);
                        frm.setChargeposmark(Integer.parseInt(schargeposmark));
                        frm.setChargeprovince(schargeprovince);
                        frm.setChargesendmode(Integer.parseInt(schargesendmode));
                        frm.setChargesum(Double.parseDouble(schargesum));
                        frm.setChargework(schargework);

                        //System.out.println("OK111====================");

                        //System.out.println("OK2222====================");
                        help.insterData(frm);
                }
                return mapping.findForward("success");
        }

}
