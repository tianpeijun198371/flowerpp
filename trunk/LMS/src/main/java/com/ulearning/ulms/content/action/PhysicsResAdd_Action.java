/**
 * PhysicsResAdd_Action.java.
 * User: liz  Date: 2006-2-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.action;

import com.ulearning.ulms.content.form.PhysicsResForm;
import com.ulearning.ulms.content.helper.PhysicsResHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PhysicsResAdd_Action extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                PhysicsResForm frm = (PhysicsResForm) form;

                PhysicsResHelper.insertRes(frm);

                return mapping.findForward("success");
        }
}
