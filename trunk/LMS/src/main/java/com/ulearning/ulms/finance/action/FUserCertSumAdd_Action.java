/**
 * FUserCertSumAdd_Action.java.
 * User: liz  Date: 2006-3-24
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.finance.form.UserAccountForm;
import com.ulearning.ulms.finance.helper.UserAccountHelper;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FUserCertSumAdd_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String[] userid = request.getParameterValues("userid");

                for (int o = 0; o < userid.length; o++)
                {
                        System.out.println("userid = " + userid[o]);
                }

                String[] cuserid = request.getParameterValues("cuserid");
                String[] username = request.getParameterValues("username");
                String[] sDate = request.getParameterValues("sDate");
                String[] optName = request.getParameterValues("optName");
                String[] optID = request.getParameterValues("optID");
                String[] sum = request.getParameterValues("sum");

                //System.out.println("userid==========="+userid.length);
                //System.out.println("cuserid==========="+cuserid.length);
                HttpSession session = request.getSession();
                String orgID_str = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);
                String aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                UserAccountHelper help = new UserAccountHelper();
                boolean isadd = true;

                if ((request.getParameter("money-back") != null) &&
                        request.getParameter("money-back").equals("money-back"))
                {
                        isadd = false;
                }

                if (isadd)
                {
                        help.addCertUserFanace(userid, cuserid, username, sDate, optName,
                                optID, sum, orgID_str, aspID, 1);
                }
                else
                {
                        UserAccountForm frm = new UserAccountForm();
                        double dAmount = 0.0;

                        for (int i = 0; i < userid.length; i++)
                        {
                                for (int k = 0; k < cuserid.length; k++)
                                {
                                        if (userid[i].equals(cuserid[k]))
                                        { //System.out.println("userid[" + k + "]=========" + userid[k]);

                                                List list = help.getUMainAccountOfId(Integer.parseInt(
                                                        userid[i]));

                                                if ((null != list) && (0 != list.size()))
                                                {
                                                        frm = (UserAccountForm) list.get(0);
                                                        dAmount = frm.getuAcotTotal();
                                                        System.out.println(
                                                                "sum[k] = ================================" +
                                                                        sum[k]);

                                                        if (dAmount < StringUtil.parseFloat(sum[k]))
                                                        {
                                                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                                                        "退费金额大于" + frm.getuAcotUserName() +
                                                                                "帐户余额！");

                                                                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                                                        }
                                                }

                                                break;
                                        }
                                }
                        }

                        help.addCertUserFanace(userid, cuserid, username, sDate, optName,
                                optID, sum, orgID_str, aspID, 2);
                }

                return mapping.findForward("success");
        }
}
