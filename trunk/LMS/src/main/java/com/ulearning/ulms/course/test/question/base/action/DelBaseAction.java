/**
 * Created by IntelliJ IDEA.
 * Base: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.question.base.action;

import com.ulearning.ulms.course.test.question.base.dao.BaseDAO;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOFactory;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelBaseAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] baseID = request.getParameterValues("baseID");
                BaseDAO dao = BaseDAOFactory.getDAO();
                dao.removeBase(baseID);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
