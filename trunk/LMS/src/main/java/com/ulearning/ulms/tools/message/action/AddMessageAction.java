package com.ulearning.ulms.tools.message.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.message.dao.MessageDAO;
import com.ulearning.ulms.tools.message.dao.MessageDAOFactory;
import com.ulearning.ulms.tools.message.dao.MessageUserDAO;
import com.ulearning.ulms.tools.message.dao.MessageUserDAOFactory;
import com.ulearning.ulms.tools.message.form.MessageForm;
import com.ulearning.ulms.tools.message.form.MessageUserForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-23
 * Time: 16:50:10
 * To change this template use File | Settings | File Templates.
 */
public class AddMessageAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                MessageForm mf = (MessageForm) form;

                MessageUserForm muf = new MessageUserForm();
                MessageUserDAO muDAO = MessageUserDAOFactory.getDAO();
                String tagType = request.getParameter("tagType");
                String nameList = request.getParameter("nameList");
                List userNameList = StringUtil.split(nameList, ";");
                List userIDList = new ArrayList();
                String res = "";

                for (int i = 0; i < userNameList.size(); i++)
                {
                        int userID = UserHelper.getUserID((String) userNameList.get(i));

                        if (userID < 1)
                        {
                                res += (" 用户 '" + (String) userNameList.get(i) + " '不存在,请检查");
                        }
                        else
                        {
                                userIDList.add(new Integer(userID));
                        }
                }

                if (res.length() > 0)
                {
                        request.setAttribute("CheckInfo", res);
                        resultScreen = "batch.Input.check.error";
                }
                else
                {
                        MessageDAO dao = MessageDAOFactory.getDAO();
                        int messageID = dao.insertMessage(mf);

                        for (int i = 0; i < userIDList.size(); i++)
                        {
                                muf.setUserID(((Integer) userIDList.get(i)).intValue());
                                muf.setMessageID(messageID);
                                muDAO.insertMessageUser(muf);
                        }

                        if ((tagType != null) && !tagType.equals(""))
                        {
                                //转到课程用户发送消息的页面
                                resultScreen = "tagType";
                        }
                }

                System.out.println("request ggggggggg= " +
                        request.getParameter("fromOutline"));

                if ((request.getParameter("fromOutline") != null) &&
                        request.getParameter("fromOutline").equals("1"))
                {
                        resultScreen = "outline";
                }

                LogUtil.info("system",
                        "[AddMessageAction]===========resultScreen = " + resultScreen);

                return mapping.findForward(resultScreen);
        }
}
