/**
 * Created by IntelliJ IDEA.
 * Delete: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.delete.action;

import com.ulearning.ulms.tools.delete.dao.DeleteDAO;
import com.ulearning.ulms.tools.delete.dao.DeleteDAOFactory;
import com.ulearning.ulms.tools.delete.form.DeleteForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddDeleteAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                DeleteForm pf = (DeleteForm) form;
                DeleteDAO dao = DeleteDAOFactory.getDAO();
                pf.setUpdateDate(new Date());
                dao.addDelete(pf);

                LogUtil.info("admin",
                        "[AddDeleteAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
