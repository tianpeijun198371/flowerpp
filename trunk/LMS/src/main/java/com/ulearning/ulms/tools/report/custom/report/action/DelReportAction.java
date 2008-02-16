/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.report.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.report.custom.report.form.ReportForm;
import com.ulearning.ulms.tools.report.custom.report.dao.ReportDAO;
import com.ulearning.ulms.tools.report.custom.report.dao.ReportDAOFactory;


public class DelReportAction extends Action
{

        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String[] reportID = request.getParameterValues("reportID");
                ReportDAO dao = ReportDAOFactory.getDAO();
                for (int i = 0; i < reportID.length; i++)
                {
                        if (!reportID[i].trim().equals(""))
                        {
                                dao.removeReport(reportID[i]);
                        }
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
