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
 * �շ�ҵ��ʵ���ѯ
 */
public class FinanceStatSearch_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                //�ڵ������������ȡ�����б����ݣ��Ա�ҳ��������ʾ��
                IncomeTypeHelper helpType = new IncomeTypeHelper();
                List listType = helpType.getAllIncomeType(2);

                //ȡҵ��ʵ��
                FinanceStatSearch_Form frm = (FinanceStatSearch_Form) form;
                int iType = frm.getTypeID();
                String sName = frm.getSname();
                int aspId = frm.getAspid();
                FinanceStatHelper help = new FinanceStatHelper();
                List list = null;
                list = help.statEntity(iType, sName, aspId);
                request.setAttribute("inType", listType); //����
                request.setAttribute("frm", list); //�������

                return mapping.findForward("success");
        }
}
