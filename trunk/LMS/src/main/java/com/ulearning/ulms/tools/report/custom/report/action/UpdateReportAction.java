/**
 * Created by IntelliJ IDEA.
 * Report: dengj
 * Date: Apr 19, 2004
 * Time: 8:54:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.report.action;

import com.ulearning.ulms.tools.report.custom.report.dao.ReportDAO;
import com.ulearning.ulms.tools.report.custom.report.dao.ReportDAOFactory;
import com.ulearning.ulms.tools.report.custom.report.form.ReportForm;
import com.ulearning.ulms.tools.report.custom.report.bean.UserInfo;
import com.ulearning.ulms.tools.report.custom.conditionitem.bean.CustomConditionItemHelper;
import com.ulearning.ulms.tools.report.custom.conditionitem.exceptions.CustomConditionItemDAOSysException;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.core.util.DateTimeUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class UpdateReportAction extends Action
{

        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ReportForm uf = (ReportForm) form;
                ReportDAO dao = ReportDAOFactory.getDAO();
                uf.setCreateDate(DateTimeUtil.toDate(uf.getCreateDateStr()));
                uf.setUpdateDate(new Date());
                dao.updateReport(uf);

                int reportID = uf.getReportID();
                dao.updateReportField(request, reportID);
                dao.updateReportTask(request, reportID);

                //first:delete condition ,second:insert condition
                CustomConditionItemHelper customConditionItemHelper = new CustomConditionItemHelper();
                try
                {
                        customConditionItemHelper.deleteCustomConditionItem(reportID);
                }
                catch (CustomConditionItemDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                if (uf.getReportType().equals("050201"))
                {
                        UserInfo userInfo = new UserInfo();
                        userInfo.insertReportCondition(request, reportID);
                }

                LogUtil.info("admin", "[UpdateReportAction]===========resultScreen = "
                        + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
