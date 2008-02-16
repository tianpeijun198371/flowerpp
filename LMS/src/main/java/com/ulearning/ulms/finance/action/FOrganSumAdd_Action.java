/**
 * FOrganSumAdd_Action.java.
 * User: liz  Date: 2005-12-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.finance.form.OrganAccountDetailForm;
import com.ulearning.ulms.finance.helper.OrganAccountHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FOrganSumAdd_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                OrganAccountDetailForm saveFrm = (OrganAccountDetailForm) form;
                saveFrm.setOaDetailDate(DateTimeUtil.parseDate(saveFrm.getAssDate())); //将从表单收到的字符形式的日期转换后使用

                OrganAccountHelper help = new OrganAccountHelper();
                saveFrm.setOaDetailInOutType(1);
                help.insert(saveFrm);
                System.out.println("添加成功！");

                return mapping.findForward("success");
        }
}
