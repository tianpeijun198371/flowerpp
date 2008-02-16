package com.ulearning.ulms.tools.message.action;

import com.ulearning.ulms.tools.message.dao.MessageDAO;
import com.ulearning.ulms.tools.message.dao.MessageDAOFactory;
import com.ulearning.ulms.tools.message.form.MessageForm;
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
 * Date: 2004-7-23
 * Time: 17:08:20
 * To change this template use File | Settings | File Templates.
 */
public class UpdateMessageAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                MessageForm mf = (MessageForm) form;
                MessageDAO dao = MessageDAOFactory.getDAO();
                dao.updateMessage(mf);

                LogUtil.info("admin",
                        "[UpdateMessageAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
