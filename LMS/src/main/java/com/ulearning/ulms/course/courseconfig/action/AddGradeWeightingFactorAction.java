/**
 * AddGradeWeightingFactorAction.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.course.courseconfig.dao.CreditCourseWiseDAO;
import com.ulearning.ulms.course.courseconfig.dao.CreditCourseWiseDAOFactory;
import com.ulearning.ulms.course.courseconfig.dao.GradeWeightingFactorDAO;
import com.ulearning.ulms.course.courseconfig.dao.GradeWeightingFactorDAOFactory;
import com.ulearning.ulms.course.courseconfig.form.GradeWeightingFactorForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddGradeWeightingFactorAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "coursetree";
                GradeWeightingFactorForm gwff = (GradeWeightingFactorForm) form;
                String[] idList = (String[]) request.getSession()
                        .getAttribute("leafIDList");

                for (int i = 0; i < idList.length; i++)
                {
                        GradeWeightingFactorDAO dao = GradeWeightingFactorDAOFactory.getDAO();
                        int relationID = Integer.parseInt(idList[i]);
                        gwff.setRelationID(relationID);
                        dao.add(gwff);
                }

                int typeID = gwff.getTypeID();

                if (typeID == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        resultScreen = "certtree";
                }

                request.setAttribute("process", "gwf");

                LogUtil.info("Course",
                        "[AddGradeWeightingFactorAction]===========resultScreen = " +
                                resultScreen);

                return mapping.findForward(resultScreen);
        }
}
