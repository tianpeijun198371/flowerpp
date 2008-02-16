package com.ulearning.ulms.tools.message.action;

import com.ulearning.ulms.tools.message.dao.MessageDAO;
import com.ulearning.ulms.tools.message.dao.MessageDAOFactory;
import com.ulearning.ulms.tools.message.dao.MessageUserDAO;
import com.ulearning.ulms.tools.message.dao.MessageUserDAOFactory;
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
 * Time: 16:56:30
 * To change this template use File | Settings | File Templates.
 */
public class DelMessageAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] messageIDList = request.getParameterValues("messageIDList");

                for (int i = 0; i < messageIDList.length; i++)
                {
                        int messageID = Integer.parseInt(messageIDList[i]);
                        MessageDAO mDao = MessageDAOFactory.getDAO();
                        mDao.deleteMessage(messageID);

                        MessageUserDAO muDao = MessageUserDAOFactory.getDAO();
                        muDao.deleteMessageUser(messageID);
                }

                LogUtil.info("admin",
                        "[UpdateMessageActoin]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
