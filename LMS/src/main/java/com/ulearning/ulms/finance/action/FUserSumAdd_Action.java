/**
 * FUserSumAdd_Action.java.
 * User: liz  Date: 2005-12-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.finance.form.UserAccountDetailForm;
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


/**
 * 个人帐户增加Action
 *
 * @author Liz
 * @ date 2005-12-20
 */
public class FUserSumAdd_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                UserAccountDetailForm saveFrm = (UserAccountDetailForm) form;
                saveFrm.setUaDetailDate(DateTimeUtil.parseDateTime(saveFrm.getAssDate()));
                saveFrm.setUaDetailInOutType(1);

                UserAccountHelper help = new UserAccountHelper();

                //uaDetailUserName
                String userNames = saveFrm.getUaDetailUserName();

                //System.out.println(request.getParameter("userID"));
                String useridstr = request.getParameter("userID");
                System.out.println("money======== " + saveFrm.getUaDetailAmount());

                //学员退费
                boolean isadd = true;

                if ((request.getParameter("money-back") != null) &&
                        request.getParameter("money-back").equals("money-back"))
                {
                        isadd = false;
                }

                UserAccountForm frm = new UserAccountForm();
                List list = null;
                double dAmount = 0.0;

                try
                {
                        String[] userids = StringUtil.splitString(useridstr, "|");
                        String[] users = StringUtil.splitString(userNames, "|");

                        for (int i = 1; i < (users.length - 1); i++)
                        {
                                //学员退费
                                if (!isadd)
                                {
                                        list = help.getUMainAccountOfId(Integer.parseInt(userids[i]));

                                        if ((null != list) && (0 != list.size()))
                                        {
                                                frm = (UserAccountForm) list.get(0);
                                                dAmount = frm.getuAcotTotal();

                                                if (dAmount < saveFrm.getUaDetailAmount())
                                                {
                                                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                                                "退费金额大于帐户余额！");

                                                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                                                }
                                                else
                                                {
                                                        saveFrm.setUaDetailInOutType(2);
                                                }
                                        }
                                }

                                //
                                UserAccountDetailForm temp = new UserAccountDetailForm();
                                org.apache.commons.beanutils.BeanUtils.copyProperties(temp,
                                        saveFrm);
                                temp.setUserID(Integer.parseInt(userids[i]));
                                temp.setUaDetailUserName(users[i]);
                                help.insert(temp);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }

                //将从表单收到的字符形式的日期转换后使用
                return mapping.findForward("success");
        }

        /*
public static void main(String [] args) {
String temp = "|sdfsd|";
String[] users = StringUtil.splitString(temp, "|");
for(int i=1; i<users.length-1; i++) {
System.out.println(users[i]);
}
System.out.println(users.length);
}                                       */
}
