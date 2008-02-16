package com.ulearning.ulms.tools.report.custom.tasktime.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.report.custom.tasktime.dao.TaskTimeDAOFactory;
import com.ulearning.ulms.tools.report.custom.tasktime.dao.TaskTimeDAO;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 17:40:30
 * To change this template use File | Settings | File Templates.
 */
public class DelTaskTimeAction
{
        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String taskTimeID = request.getParameter("taskTimeID");
                TaskTimeDAO dao = TaskTimeDAOFactory.getDAO();
                dao.deleteTaskTime(Integer.parseInt(taskTimeID));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
