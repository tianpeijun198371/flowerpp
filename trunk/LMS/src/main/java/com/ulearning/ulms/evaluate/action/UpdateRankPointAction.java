/**
 * UpdateRankPoint.java.
 * User: yud  Date: 2005-06-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.evaluate.helper.EvaluateManageHelper;
import com.ulearning.ulms.evaluate.model.ERecordRankStandardModel;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateRankPointAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                List list = EvaluateManageHelper.getRankPoint();

                for (int i = 0; i < list.size(); i++)
                {
                        String[] rankPoint = request.getParameterValues("rankPoint" + i);
                        ERecordRankStandardModel eRecordRankStandardModel = (ERecordRankStandardModel) list.get(i);

                        eRecordRankStandardModel.setPoint(StringUtil.parseInt(rankPoint[0]));
                        EvaluateManageHelper.updateERecordRankStandard(eRecordRankStandardModel);
                }

                return mapping.findForward(resultScreen);
        }
}
