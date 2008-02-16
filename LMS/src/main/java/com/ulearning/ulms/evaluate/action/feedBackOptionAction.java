/**
 * UpdateRankPoint.java.
 * User: yud  Date: 2005-06-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.action;

import com.ulearning.ulms.evaluate.form.FeedBackOptionForm;
import com.ulearning.ulms.evaluate.helper.FeedBackHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class feedBackOptionAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                FeedBackOptionForm feedBackOptionForm = new FeedBackOptionForm();
                String[] relationID = request.getParameterValues("relationID");

                for (int i = 0; i < relationID.length; i++)
                {
                        feedBackOptionForm.setRelationID(Integer.parseInt(relationID[i]));
                        feedBackOptionForm.setType("0");

                        String[] status = request.getParameterValues("status[" + i + "]");
                        String[] isViewResult = request.getParameterValues("isViewResult[" +
                                i + "]");

                        if (status == null)
                        {
                                //status[0]="0";
                                feedBackOptionForm.setStatus("0");
                        }
                        else
                        {
                                feedBackOptionForm.setStatus(status[0]);
                        }

                        if (isViewResult == null)
                        {
                                feedBackOptionForm.setisViewResult("0");
                        }
                        else
                        {
                                feedBackOptionForm.setisViewResult(isViewResult[0]);
                        }

                        String[] feedBackOptionID = request.getParameterValues(
                                "feedBackOptionID[" + i + "]");

                        if (Integer.parseInt(feedBackOptionID[0]) > 0)
                        {
                                feedBackOptionForm.setFeedbackOptionID(Integer.parseInt(
                                        feedBackOptionID[0]));
                                feedBackOptionForm.setCreateDate(new Date());
                                FeedBackHelper.updateFeedBackOption(feedBackOptionForm);
                        }
                        else
                        {
                                FeedBackHelper.addFeedBackOption(feedBackOptionForm);
                        }
                }

                return mapping.findForward(resultScreen);
        }
}
