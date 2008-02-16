/** * DelAssignProcessAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.assignment.assignprocess.bean.AssignProcessHelper;
import com.ulearning.ulms.tools.assignment.assignprocess.dao.AssignProcessDAO;
import com.ulearning.ulms.tools.assignment.assignprocess.dao.AssignProcessDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelAssignProcessAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] assignProcessID = request.getParameterValues("assignProcessID");
                AssignProcessDAO dao = AssignProcessDAOFactory.getDAO();

                for (int i = 0; i < assignProcessID.length; i++)
                {
                        dao.removeAssignProcess(assignProcessID[i]);
                }

                return mapping.findForward(resultScreen);
        }
}
