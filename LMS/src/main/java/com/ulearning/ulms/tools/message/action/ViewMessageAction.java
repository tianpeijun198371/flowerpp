/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.tools.message.action;

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
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2004-10-9
 */
public class ViewMessageAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                int messageID = 0;
                String messageIDstr = (String) request.getParameter("messageID");

                if (messageIDstr != null)
                {
                        messageID = Integer.parseInt(messageIDstr);
                }

                ;

                int userID = 0;
                String userIDstr = (String) request.getParameter("userID");

                if (userIDstr != null)
                {
                        userID = Integer.parseInt(userIDstr);
                }

                if (userID > 0)
                {
                        MessageUserDAO dao = MessageUserDAOFactory.getDAO();
                        dao.viewMessage(messageID, userID);
                }

                LogUtil.info("admin",
                        "[UpdateMessageUserByMessageAction]===========resultScreen = " +
                                resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
