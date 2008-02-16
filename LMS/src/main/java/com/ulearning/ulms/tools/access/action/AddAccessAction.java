/**
 * Created by IntelliJ IDEA.
 * Access: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.access.action;

import com.ulearning.ulms.tools.access.dao.AccessDAO;
import com.ulearning.ulms.tools.access.dao.AccessDAOFactory;
import com.ulearning.ulms.tools.access.model.Access;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddAccessAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.info("system", "[AddAccessAction]===========Begin");

                Access acf = (Access) form;
                AccessDAO dao = AccessDAOFactory.getDAO();
                dao.insert(acf);

                LogUtil.info("system",
                        "[AddAccessAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
