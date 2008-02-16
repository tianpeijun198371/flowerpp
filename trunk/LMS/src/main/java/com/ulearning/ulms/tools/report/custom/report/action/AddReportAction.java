/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.report.action;

import com.ulearning.ulms.tools.report.custom.report.dao.ReportDAO;
import com.ulearning.ulms.tools.report.custom.report.dao.ReportDAOFactory;
import com.ulearning.ulms.tools.report.custom.report.form.ReportForm;
import com.ulearning.ulms.tools.report.custom.report.bean.UserInfo;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AddReportAction extends Action
{

        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                ReportForm rf = (ReportForm) form;
                ReportDAO dao = ReportDAOFactory.getDAO();
                rf.setCreateDate(new Date());
                int reportID = dao.addReport(rf);
                if (reportID != -1)
                {
                        System.out.println("okb");
                        dao.insertReportField(request, reportID);
                        if (rf.getReportType().equals("050201"))
                        {
                                UserInfo userInfo = new UserInfo();
                                userInfo.insertReportCondition(request, reportID);
                        }
                }

                LogUtil.info("admin", "[AddReportAction]===========resultScreen = "
                        + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
