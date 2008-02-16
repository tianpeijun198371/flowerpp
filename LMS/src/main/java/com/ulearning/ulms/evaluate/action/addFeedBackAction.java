/**
 * UpdateRankPoint.java.
 * User: yud  Date: 2005-06-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.action;

import com.ulearning.ulms.evaluate.form.EFeedBackForm;
import com.ulearning.ulms.evaluate.helper.FeedBackHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class addFeedBackAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                EFeedBackForm eFeedBackForm = (EFeedBackForm) form;
                FeedBackHelper.addFeedBack(eFeedBackForm);

                return mapping.findForward(resultScreen);
        }
}
