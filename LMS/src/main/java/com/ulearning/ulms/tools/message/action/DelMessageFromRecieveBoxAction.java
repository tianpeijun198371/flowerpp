package com.ulearning.ulms.tools.message.action;

import com.ulearning.ulms.tools.message.dao.MessageDAO;
import com.ulearning.ulms.tools.message.dao.MessageDAOFactory;
import com.ulearning.ulms.tools.message.dao.MessageUserDAO;
import com.ulearning.ulms.tools.message.dao.MessageUserDAOFactory;
import com.ulearning.ulms.tools.message.form.MessageForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-30
 * Time: 11:01:37
 * To change this template use File | Settings | File Templates.
 */
public class DelMessageFromRecieveBoxAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] messageIDList = request.getParameterValues("messageIDList");
                int userID = Integer.parseInt(request.getParameter("userID"));

                for (int i = 0; i < messageIDList.length; i++)
                {
                        int messageID = Integer.parseInt(messageIDList[i]);
                        MessageUserDAO muDao = MessageUserDAOFactory.getDAO();
                        muDao.deleteMessageUser(userID, messageID);
                }

                LogUtil.info("admin",
                        "[UpdateMessageActoin]===========resultScreen = " + resultScreen);

                for (int i = 0; i < messageIDList.length; i++)
                {
                        int messageID = Integer.parseInt(messageIDList[i]);
                        MessageDAO mDao = MessageDAOFactory.getDAO();
                        MessageUserDAO muDao = MessageUserDAOFactory.getDAO();
                        List list = muDao.getMessageUserListByMessage(messageID);

                        if (((list == null) || (list.size() < 1)) &&
                                (mDao.getMessage(messageID).getIsSave() == 0))
                        {
                                mDao.deleteMessage(messageID);
                        }
                }

                LogUtil.info("admin",
                        "[UpdateMessageActoin]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
