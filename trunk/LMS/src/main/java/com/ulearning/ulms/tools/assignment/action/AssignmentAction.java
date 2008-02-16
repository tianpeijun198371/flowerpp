/** * AssignmentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.action;

import com.ulearning.ulms.tools.assignment.dao.AssignmentDAO;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AssignmentAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String assignmentID = request.getParameter("assignmentID");

                AssignmentDAO dao = AssignmentDAOFactory.getDAO();
                dao.removeAssignment(assignmentID);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
