package com.ulearning.ulms.tools.assignment.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.assignment.bean.AssignmentHelper;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAO;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2004-5-1
 * Time: 15:59:37
 * To change this template use File | Settings | File Templates.
 */
public class UpdateStateAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] assignmentID = request.getParameterValues("assignmentID");
                String fromAdminList = String.valueOf(request.getParameter(
                        "fromAdminList"));
                String viewable = request.getParameter("viewable");
                String available = request.getParameter("available");
                AssignmentDAO dao = AssignmentDAOFactory.getDAO();

                for (int i = 0; i < assignmentID.length; i++)
                {
                        if (viewable != null)
                        {
                                dao.updateAssignmentViewable(assignmentID[i], viewable);
                        }

                        if (available != null)
                        {
                                dao.updateAssignmentAvailable(assignmentID[i], available);
                        }
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
