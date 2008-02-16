/**
 * FinanceStatDetail_Action.java.
 * User: liz  Date: 2005-12-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.finance.form.FinanceStatResultForm;
import com.ulearning.ulms.finance.helper.FinanceStatHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 取具体业务实体详细收费信息
 *
 * @author Liz
 * @ date 2005-12-26
 */
public class FinanceStatDetail_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                //FinanceStatResultForm frm=(FinanceStatResultForm)form;
                FinanceStatHelper help = new FinanceStatHelper();

                //System.out.println("start_input");
                int entId = (Integer.parseInt((String) request.getParameter("entId")));
                int typeId = (Integer.parseInt((String) request.getParameter("typeId")));

                //由JSP传到JAVA的汉字，JAVA再传给JSP会形成乱码，用以下处理
                //String entName=new String(request.getParameter("entName").getBytes("ISO8859-1"),"GB2312");
                String entName = request.getParameter("entName");
                List list = null;
                //System.out.println("entName =========" + entName);
                //System.out.println("entName =========" +new String(request.getParameter("entName").getBytes("ISO8859-1"),"GB2312"));
                list = help.makeEntityfinance(entId, typeId, entName);

                request.setAttribute("frm", list);

                //System.out.println("OK_getData"+list.size());
                return mapping.findForward("success");
        }
}
