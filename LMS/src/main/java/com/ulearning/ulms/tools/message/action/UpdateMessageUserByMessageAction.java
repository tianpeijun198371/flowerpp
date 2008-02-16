package com.ulearning.ulms.tools.message.action;

import com.ulearning.ulms.tools.message.dao.MessageUserDAO;
import com.ulearning.ulms.tools.message.dao.MessageUserDAOFactory;
import com.ulearning.ulms.tools.message.form.MessageUserForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-26
 * Time: 11:32:36
 * To change this template use File | Settings | File Templates.
 */
public class UpdateMessageUserByMessageAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                MessageUserForm muf = (MessageUserForm) form;
                MessageUserDAO dao = MessageUserDAOFactory.getDAO();
                dao.updateMessageUserByMessage(muf);

                LogUtil.info("admin",
                        "[UpdateMessageUserByMessageAction]===========resultScreen = " +
                                resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
