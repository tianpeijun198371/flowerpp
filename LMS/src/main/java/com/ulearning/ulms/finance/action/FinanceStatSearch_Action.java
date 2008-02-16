/**
 * FinanceStatSearch_Action.java.
 * User: liz  Date: 2005-12-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.finance.helper.FinanceStatHelper;
import com.ulearning.ulms.finance.helper.IncomeTypeHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 收费业务实体查询
 */
public class FinanceStatSearch_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                //在点击搜索后重新取类型列表数据，以便页面正常显示　
                IncomeTypeHelper helpType = new IncomeTypeHelper();
                List listType = helpType.getAllIncomeType(2);

                //取业务实体
                FinanceStatSearch_Form frm = (FinanceStatSearch_Form) form;
                int iType = frm.getTypeID();
                String sName = frm.getSname();
                int aspId = frm.getAspid();
                FinanceStatHelper help = new FinanceStatHelper();
                List list = null;
                list = help.statEntity(iType, sName, aspId);
                request.setAttribute("inType", listType); //类型
                request.setAttribute("frm", list); //搜索结果

                return mapping.findForward("success");
        }
}
