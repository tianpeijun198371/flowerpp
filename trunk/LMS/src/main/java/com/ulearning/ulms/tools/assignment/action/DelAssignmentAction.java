/** * DelAssignmentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.assignment.bean.AssignmentHelper;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAO;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelAssignmentAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] assignmentID = request.getParameterValues("assignmentID");
                List arenaList = new ArrayList();
                String fromAdminList = String.valueOf(request.getParameter(
                        "fromAdminList"));
                String parent_sub_id = request.getParameter("parent_sub_id");
                AssignmentHelper ah = new AssignmentHelper();
                AssignmentDAO dao = AssignmentDAOFactory.getDAO();

                for (int i = 0; i < assignmentID.length; i++)
                {
                        arenaList.add(assignmentID[i]);

                        if (fromAdminList.equals("1"))
                        {
                                dao.removeAssignmentRoot(assignmentID[i]);
                        }
                        else
                        {
                                dao.removeAssignment(assignmentID[i]);

                                //delete sub node
                                List subID = ah.getSubID(parent_sub_id, assignmentID[i]);

                                for (int k = 0; (subID != null) && (k < subID.size()); k++)
                                {
                                        List t = StringUtil.split((String) subID.get(k), ",");
                                        dao.removeAssignment((String) t.get(1));
                                }
                        }
                }

                // Forward to result page
                request.setAttribute("courseID", request.getParameter("courseID"));
                request.setAttribute("arenaList", arenaList);

                return mapping.findForward(resultScreen);
        }
}
