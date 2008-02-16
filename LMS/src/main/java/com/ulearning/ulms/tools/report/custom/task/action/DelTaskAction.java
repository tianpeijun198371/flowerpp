package com.ulearning.ulms.tools.report.custom.task.action;

import com.ulearning.ulms.tools.report.custom.task.dao.TaskDAO;
import com.ulearning.ulms.tools.report.custom.task.dao.TaskDAOFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 15:31:21
 * To change this template use File | Settings | File Templates.
 */
public class DelTaskAction extends Action
{
        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String taskID = request.getParameter("taskID");
                TaskDAO dao = TaskDAOFactory.getDAO();
                dao.deleteTask(Integer.parseInt(taskID));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
