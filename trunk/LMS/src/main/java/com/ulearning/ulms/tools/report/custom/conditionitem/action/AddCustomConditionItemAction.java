package com.ulearning.ulms.tools.report.custom.conditionitem.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ulearning.ulms.tools.report.custom.conditionitem.dao.CustomConditionItemDAO;
import com.ulearning.ulms.tools.report.custom.conditionitem.dao.CustomConditionItemDAOFactory;
import com.ulearning.ulms.tools.report.custom.conditionitem.form.CustomConditionItemForm;
import com.ulearning.ulms.util.log.LogUtil;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-19
 * Time: 15:06:56
 * To change this template use File | Settings | File Templates.
 */
public class AddCustomConditionItemAction extends Action
{
        public ActionForward excute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                CustomConditionItemForm ccf = (CustomConditionItemForm) form;
                CustomConditionItemDAO dao = CustomConditionItemDAOFactory.getDAO();
                dao.insertCustomConditionItem(ccf);

                LogUtil.info("system", "[AddCustomConditionItemAction]===========resultScreen = "
                        + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);


        }
}
