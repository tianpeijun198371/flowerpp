/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.content.schoolbook.actions;

import com.ulearning.ulms.admin.topTen.dao.TopTenDAO;
import com.ulearning.ulms.admin.topTen.dao.TopTenDAOFactory;
import com.ulearning.ulms.content.schoolbook.bean.OrderHelper;
import com.ulearning.ulms.tools.report.general.dao.ReportDAO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class delOrderAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] ID = request.getParameterValues("cpacotid");
                OrderHelper oh = new OrderHelper();

                for (int i = 0; i < ID.length; i++)
                {
                        oh.delData(Integer.parseInt(ID[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
