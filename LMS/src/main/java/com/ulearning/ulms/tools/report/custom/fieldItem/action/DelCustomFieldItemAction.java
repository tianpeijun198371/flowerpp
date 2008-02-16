/**
 * Created by IntelliJ IDEA.
 * CustomFieldItem: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.fieldItem.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.tools.report.custom.fieldItem.form.CustomFieldItemForm;
import com.ulearning.ulms.tools.report.custom.fieldItem.dao.CustomFieldItemDAO;
import com.ulearning.ulms.tools.report.custom.fieldItem.dao.CustomFieldItemDAOFactory;


public class DelCustomFieldItemAction extends Action
{

        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String CustomFieldItemID = request.getParameter("CustomFieldItemID");
                String MCustomFieldItemID = request.getParameter("MCustomFieldItemID");
                CustomFieldItemDAO dao = CustomFieldItemDAOFactory.getDAO();
                dao.removeCustomFieldItem(Integer.parseInt(CustomFieldItemID), Integer.parseInt(MCustomFieldItemID));
                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
