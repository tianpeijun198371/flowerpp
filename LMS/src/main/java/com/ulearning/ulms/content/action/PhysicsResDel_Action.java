/**
 * PhysicsResDel_Action.java.
 * User: liz  Date: 2006-2-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.action;

import com.ulearning.ulms.content.helper.PhysicsResHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PhysicsResDel_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String[] resId = request.getParameterValues("resid");

                if (null != resId)
                {
                        System.out.println("resLen==" + resId.length);
                }
                else
                {
                        System.out.println("IDΪNULL");
                }

                for (int i = 0; i < resId.length; i++)
                {
                        System.out.println("resId:" + resId[i]);
                        PhysicsResHelper.delRes(resId[i]);
                }

                return mapping.findForward("success");
        }
}
