/**
 * Created by IntelliJ IDEA.
 * Delete: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.delete.action;

import com.ulearning.ulms.tools.delete.dao.DeleteDAO;
import com.ulearning.ulms.tools.delete.dao.DeleteDAOFactory;
import com.ulearning.ulms.tools.delete.form.DeleteForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelDeleteAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] deleteID = request.getParameterValues("deleteID");
                DeleteDAO dao = DeleteDAOFactory.getDAO();

                for (int i = 0; i < deleteID.length; i++)
                {
                        dao.removeDelete(deleteID[i]);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
