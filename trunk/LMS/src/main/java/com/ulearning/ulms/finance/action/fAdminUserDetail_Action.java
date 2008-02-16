/**
 * fAdminUserDetail_Action.java.
 * User: liz  Date: 2005-12-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.finance.helper.UserAccountHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 管理员查询个人用户Action
 *
 * @author Liz
 * @ date 2005-12-19
 */
public class fAdminUserDetail_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                HttpSession session = request.getSession();

                fAdminUserDetail_Form frm = (fAdminUserDetail_Form) form;
                String sName = frm.getUserName();
                String srealid = request.getParameter("realid");

                //System.out.println("srealidsrealidsrealidsrealid---------------****" + srealid);
                /*if(null==srealid || srealid.equals("-1")){
             srealid = (String)request.getAttribute("realid");
             }   */

                // System.out.println("srealid=====================" + srealid);
                String srealtype = "4";
                UserAccountHelper help = new UserAccountHelper();
                List list = null;

                //list = help.getUMainAcotFrm(0,sName,0);
                //System.out.println("srealid*********************************" + srealid);
                if ((null != srealid) && !srealid.equals("-1"))
                {
                        list = help.getUMainAcotFrm(0, sName, 0, Integer.valueOf(srealid),
                                Integer.valueOf(srealtype));
                }
                else
                {
                        list = help.getUMainAcotFrm(0, sName, 0, null, null);
                }

                //System.out.println("action++++++list----------------------" + list);
                request.setAttribute("frm", list);
                session.setAttribute("realid", srealid);
                request.setAttribute("searchname", sName);

                ActionForward forward = mapping.findForward("success");
                StringBuffer bf = new StringBuffer(forward.getPath());
                bf.append("?sName=" + sName);

                // bf.append("&realid=" + srealid);
                return new ActionForward(bf.toString());
        }
}
