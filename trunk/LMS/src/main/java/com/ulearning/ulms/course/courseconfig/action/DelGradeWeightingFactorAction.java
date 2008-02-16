/**
 * DelGradeWeightingFactorAction.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.action;

import com.ulearning.ulms.course.courseconfig.dao.GradeWeightingFactorDAO;
import com.ulearning.ulms.course.courseconfig.dao.GradeWeightingFactorDAOFactory;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelGradeWeightingFactorAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                int typeID = Integer.parseInt(request.getParameter("TypeID"));
                int relationID = Integer.parseInt(request.getParameter("relationID"));
                GradeWeightingFactorDAO dao = GradeWeightingFactorDAOFactory.getDAO();
                dao.delete(typeID, relationID);
                LogUtil.info("Course",
                        "[DelGradeWeightingFactorAction]===========resultScreen = " +
                                resultScreen);

                return mapping.findForward(resultScreen);
        }
}
