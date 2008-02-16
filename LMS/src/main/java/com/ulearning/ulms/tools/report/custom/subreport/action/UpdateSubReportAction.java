/**
 * Created by IntelliJ IDEA.
 * SubReport: dengj
 * Date: Apr 19, 2004
 * Time: 8:54:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.subreport.action;

import com.ulearning.ulms.tools.report.custom.subreport.dao.SubReportDAO;
import com.ulearning.ulms.tools.report.custom.subreport.dao.SubReportDAOFactory;
import com.ulearning.ulms.tools.report.custom.subreport.form.SubReportForm;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateSubReportAction extends Action
{

        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                SubReportForm uf = (SubReportForm) form;
                SubReportDAO dao = SubReportDAOFactory.getDAO();
                dao.updateSubReport(uf);

                LogUtil.info("admin", "[UpdateSubReportAction]===========resultScreen = "
                        + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
