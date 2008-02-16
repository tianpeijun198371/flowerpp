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
import com.ulearning.ulms.evaluate.model.ERecordPointConversionModel;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class updatePointConversionAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                List list = EvaluateManageHelper.getPointConversion();

                for (int i = 0; i < list.size(); i++)
                {
                        String[] pointConversion = request.getParameterValues(
                                "pointConversion[" + i + "]");
                        ERecordPointConversionModel eRecordPointConversionModel = (ERecordPointConversionModel) list.get(i);

                        eRecordPointConversionModel.setPoint(StringUtil.parseInt(
                                pointConversion[0]));
                        EvaluateManageHelper.updateERecordPointConversion(eRecordPointConversionModel);
                }

                return mapping.findForward(resultScreen);
        }
}
