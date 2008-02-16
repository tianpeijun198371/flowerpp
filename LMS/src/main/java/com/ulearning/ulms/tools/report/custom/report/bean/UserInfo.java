/** * UserInfo.java. 
 * User: xiejh  Date: 2004-7-21 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */
package com.ulearning.ulms.tools.report.custom.report.bean;

import com.ulearning.ulms.tools.report.general.bean.GeneralHelper;
import com.ulearning.ulms.tools.report.general.model.ConditionItemForm;
import com.ulearning.ulms.tools.report.custom.conditionitem.bean.CustomConditionItemHelper;
import com.ulearning.ulms.tools.report.custom.conditionitem.form.CustomConditionItemForm;
import com.ulearning.ulms.tools.report.custom.conditionitem.exceptions.CustomConditionItemDAOSysException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Iterator;

public class UserInfo
{
        public void insertReportCondition(HttpServletRequest request, int reportID)
        {
                String reportType = request.getParameter("reportType");
                GeneralHelper generalHelper = new GeneralHelper();
                List ConditionItemFormList = generalHelper.getConditionItemFormList(reportType);
                ConditionItemForm conditionItemForm = null;
                int ConditionItemID = 0;
                String tmpValue = null;
                String ConditionValue = null;
                CustomConditionItemForm customConditionItemForm = null;
                CustomConditionItemHelper customConditionItemHelper = new CustomConditionItemHelper();

                for (Iterator it = ConditionItemFormList.iterator(); it.hasNext();)
                {
                        conditionItemForm = (ConditionItemForm) it.next();
                        ConditionItemID = conditionItemForm.getConditionItemID();
                        tmpValue = request.getParameter("ConItemID" + ConditionItemID);
                        if (tmpValue == null)
                        {
                                continue;
                        }
                        ConditionValue = request.getParameter("ConditionItemID" + ConditionItemID);
                        if (ConditionValue.trim().equals(""))
                        {
                                continue;
                        }
                        customConditionItemForm = new CustomConditionItemForm();
                        customConditionItemForm.setReportID(reportID);
                        customConditionItemForm.setConditionItemID(ConditionItemID);
                        customConditionItemForm.setConditionValue(ConditionValue);
                        if (conditionItemForm.getConditionType().equals("=LoginName"))
                        {
                                customConditionItemForm.setRelationSymbol("=");
                                String[] loginNames = request.getParameterValues("ConditionItemID" + ConditionItemID);
                                for (int i = 0; i < loginNames.length; i++)
                                {
                                        if (!loginNames[i].equals(""))
                                        {
                                                customConditionItemForm.setConditionValue(loginNames[i]);
                                                try
                                                {
                                                        customConditionItemHelper.insertCustomConditionItem(customConditionItemForm);
                                                }
                                                catch (CustomConditionItemDAOSysException udse)
                                                {
                                                        udse.printStackTrace();
                                                }
                                        }
                                }
                        }
                        else if (conditionItemForm.getConditionType().equals("roleID"))
                        {
                                customConditionItemForm.setRelationSymbol("=");
                                String[] roles = request.getParameterValues("ConditionItemID" + ConditionItemID);
                                for (int i = 0; i < roles.length; i++)
                                {
                                        if (!roles[i].equals(""))
                                        {
                                                customConditionItemForm.setConditionValue(roles[i]);
                                                try
                                                {
                                                        customConditionItemHelper.insertCustomConditionItem(customConditionItemForm);
                                                }
                                                catch (CustomConditionItemDAOSysException udse)
                                                {
                                                        udse.printStackTrace();
                                                }
                                        }
                                }
                        }
                        else
                        {
                                if (conditionItemForm.getConditionType().equals("like")
                                        || conditionItemForm.getConditionType().equals("="))
                                {
                                        customConditionItemForm.setRelationSymbol(conditionItemForm.getConditionType());
                                }
                                else
                                {
                                        customConditionItemForm.setRelationSymbol("");
                                }
                                try
                                {
                                        customConditionItemHelper.insertCustomConditionItem(customConditionItemForm);
                                }
                                catch (CustomConditionItemDAOSysException udse)
                                {
                                        udse.printStackTrace();
                                }
                        }//end if
                }//end for
        }//end method
}