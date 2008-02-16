package com.ulearning.ulms.tools.report.custom.conditionitem.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.report.custom.conditionitem.dao.CustomConditionItemDAO;
import com.ulearning.ulms.tools.report.custom.conditionitem.dao.CustomConditionItemDAOFactory;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-19
 * Time: 15:17:37
 * To change this template use File | Settings | File Templates.
 */
public class DelCustomConditionItemAction extends Action
{

        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String reportID = request.getParameter("reportID");
                CustomConditionItemDAO dao = CustomConditionItemDAOFactory.getDAO();
                dao.deleteCustomConditionItem(Integer.parseInt(reportID));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
