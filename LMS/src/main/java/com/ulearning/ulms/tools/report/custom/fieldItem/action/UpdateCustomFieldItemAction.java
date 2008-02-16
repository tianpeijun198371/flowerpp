/**
 * Created by IntelliJ IDEA.
 * CustomFieldItem: dengj
 * Date: Apr 19, 2004
 * Time: 8:54:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.report.custom.fieldItem.action;

import com.ulearning.ulms.tools.report.custom.fieldItem.dao.CustomFieldItemDAO;
import com.ulearning.ulms.tools.report.custom.fieldItem.dao.CustomFieldItemDAOFactory;
import com.ulearning.ulms.tools.report.custom.fieldItem.form.CustomFieldItemForm;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCustomFieldItemAction extends Action
{

        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                CustomFieldItemForm uf = (CustomFieldItemForm) form;
                CustomFieldItemDAO dao = CustomFieldItemDAOFactory.getDAO();
                dao.updateCustomFieldItem(uf);

                LogUtil.info("admin", "[UpdateCustomFieldItemAction]===========resultScreen = "
                        + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
