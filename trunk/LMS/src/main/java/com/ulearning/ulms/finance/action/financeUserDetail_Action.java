/**
 * financeUserDetail_Action.java.
 * User: liz  Date: 2005-12-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.finance.form.UserAccountDetailForm;
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


/**
 * 个人搜索用户明细Action
 *
 * @author Liz
 * @ date 2005-12-19
 */
public class financeUserDetail_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                //HttpSession session = request.getSession();
                financeUserDetail_Form frmQuery = (financeUserDetail_Form) form;
                int detailType = frmQuery.getDetailType();
                String bDate = frmQuery.getBeginDate();
                String eDate = frmQuery.getEndDate();
                int userID = Integer.parseInt(frmQuery.getUserID());
                List list = null;

                //UserAccountDetailForm frmResult = null;
                UserAccountHelper help = new UserAccountHelper();
                list = help.getUADetailENameForUID(userID, bDate, eDate, detailType);
                request.setAttribute("frm", list);

                return mapping.findForward("success");
        }
}
