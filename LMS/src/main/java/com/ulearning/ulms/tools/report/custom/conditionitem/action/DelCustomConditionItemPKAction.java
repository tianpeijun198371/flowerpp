package com.ulearning.ulms.tools.report.custom.conditionitem.action;

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
 * Date: 2004-7-20
 * Time: 13:32:01
 * To change this template use File | Settings | File Templates.
 */
public class DelCustomConditionItemPKAction
{
        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String CustomConditionItemID = request.getParameter("CustomConditionItemID");
                CustomConditionItemDAO dao = CustomConditionItemDAOFactory.getDAO();
                dao.deleteCustomConditionItemPK(Integer.parseInt(CustomConditionItemID));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
