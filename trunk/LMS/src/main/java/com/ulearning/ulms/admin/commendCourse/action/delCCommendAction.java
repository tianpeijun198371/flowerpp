/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.commendCourse.action;

import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAO;
import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAOFactory;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class delCCommendAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.info("system",
                        "[AddCCommendAction]========================Begin");

                String[] ids = request.getParameterValues("ID");
                int ccourseID = 0;
                CCommendDAO DAO = CCommendDAOFactory.getDAO();

                for (int i = 0; (ids != null) && (i < ids.length); i++)
                {
                        ccourseID = Integer.parseInt(ids[i]);
                        DAO.removeCCommend(ccourseID);
                }

                LogUtil.info("system", "[AddCCommendAction]========================End");

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
