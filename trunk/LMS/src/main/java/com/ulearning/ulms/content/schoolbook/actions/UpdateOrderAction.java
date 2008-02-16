package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.content.schoolbook.bean.OrderHelper;
import com.ulearning.ulms.content.schoolbook.form.OrderForm;
import com.ulearning.ulms.tools.report.custom.task.dao.TaskDAO;
import com.ulearning.ulms.tools.report.custom.task.dao.TaskDAOFactory;
import com.ulearning.ulms.tools.report.custom.task.form.TaskForm;
import com.ulearning.ulms.util.log.LogUtil;

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
 * Time: 15:31:06
 * To change this template use File | Settings | File Templates.
 */
public class UpdateOrderAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                OrderForm tf = (OrderForm) form;
                OrderHelper oh = new OrderHelper();
                oh.updateData(tf);
                LogUtil.info("admin",
                        "[UpdateTask]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
