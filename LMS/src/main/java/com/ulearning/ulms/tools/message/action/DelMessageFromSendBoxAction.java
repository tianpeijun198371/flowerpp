/**
 * DelMessageFromSendBoxAction.java.
 * User: keyh  Date: 2004-8-3
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelMessageFromSendBoxAction extends Action
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
                        MessageDAO mDao = MessageDAOFactory.getDAO();
                        mDao.deleteMessageFromSendBox(messageID);
                }

                for (int i = 0; i < messageIDList.length; i++)
                {
                        int messageID = Integer.parseInt(messageIDList[i]);
                        MessageDAO mDao = MessageDAOFactory.getDAO();
                        MessageUserDAO muDao = MessageUserDAOFactory.getDAO();
                        List muList = muDao.getMessageUserListByMessage(messageID);

                        if ((muList == null) || (muList.size() < 1))
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
