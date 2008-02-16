/**
 * SeaChargeAction.java.
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
import java.util.List;

public class SeaChargeAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {

                String name = request.getParameter("searchname");
                String work = request.getParameter("searchwork");
                String sid = request.getParameter("id");
                //下面是jboss使用，webshpere注释掉
                //name=new String(name.getBytes("ISO-8859-1"),"gb2312");
                int iid = 0;
                if (null != sid && !sid.equals(""))
                {
                        iid = Integer.parseInt(sid);
                }
                RChargeHelper help = new RChargeHelper();
                // System.out.println("name===="+name);
                //System.out.println("work===="+work);
                //System.out.println("sid===="+sid);
                List list = help.getChargeList(iid, name, work);
                request.setAttribute("frm", list);
                return mapping.findForward("success");
        }

}
