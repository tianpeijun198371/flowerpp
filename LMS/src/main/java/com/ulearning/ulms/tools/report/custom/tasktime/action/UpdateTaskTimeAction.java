package com.ulearning.ulms.tools.report.custom.tasktime.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.report.custom.tasktime.model.TaskTimeForm;
import com.ulearning.ulms.tools.report.custom.tasktime.dao.TaskTimeDAOFactory;
import com.ulearning.ulms.tools.report.custom.tasktime.dao.TaskTimeDAO;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.core.util.DateTimeUtil;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-20
 * Time: 17:40:14
 * To change this template use File | Settings | File Templates.
 */
public class UpdateTaskTimeAction
{
        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                TaskTimeForm ttf = (TaskTimeForm) form;
                TaskTimeDAO dao = TaskTimeDAOFactory.getDAO();
                ttf.setTaskTime(DateTimeUtil.toDate(ttf.getTaskTimeStr()));
                dao.updateTaskTime(ttf);

                LogUtil.info("admin", "[UpdateTaskTime]===========resultScreen = "
                        + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
