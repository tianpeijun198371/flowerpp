/**
 * Created by IntelliJ IDEA.
 * SubReport: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.subreport.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.report.custom.subreport.form.SubReportForm;
import com.ulearning.ulms.tools.report.custom.subreport.dao.SubReportDAO;
import com.ulearning.ulms.tools.report.custom.subreport.dao.SubReportDAOFactory;


public class DelSubReportAction extends Action
{

        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String ReportID = request.getParameter("ReportID");
                String MReportID = request.getParameter("MReportID");
                SubReportDAO dao = SubReportDAOFactory.getDAO();
                dao.removeSubReport(Integer.parseInt(ReportID), Integer.parseInt(MReportID));
                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
