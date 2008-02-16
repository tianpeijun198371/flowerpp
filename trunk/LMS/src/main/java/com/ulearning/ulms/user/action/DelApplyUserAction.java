/**
 * DelApplyUserAction.java.
 * User: Fengch  Date: 2005-8-29 12:36:49
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.user.action;

import com.ulearning.ulms.blog.helper.BlogHelper;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.mail.SmtpModel;
import com.ulearning.ulms.core.mail.EmailServices;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

public class DelApplyUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";

                String userIDs = request.getParameter("userID");

                UserForm uf = UserHelper.getUser(userIDs);
                LogUtil.debug("system", "[DelApplyUserAction]===userIDs = " + userIDs);

                if ((userIDs != null) || (!userIDs.trim().equals("")))
                {
                        String userMail = uf.getMail();
                        String title = "拒绝通知";
                        //String url = Config.getulmsURL();//+"/UserActive.do?userID ="+userID;

                        String body = "尊敬的" + uf.getName() + "用户 :<br> " +
                                " 抱歉，现阶段只为中企动力油箱开放注册,请确认! <br>";
                        //" 用户名: " + uf.getLoginName() + "<br>" +
                        // " 密　码: " + uf.getPassword() + "<br>" +
                        // " 现在就 <a href=\"" + Config.getulmsURL() + "\" target=_blank>登陆</a>动力大学";
                        ///System.out.println("============url========================="+url);
                        //System.out.println("============body========================="+body);
                        String formUser = Config.getSmtpUser();
                        if (userMail != null && !userMail.equals(""))
                        {
                                List list = new ArrayList();
                                list.add(userMail);

                                SmtpModel sm = new SmtpModel();
                                sm.setSubject(title);

                                sm.setBody(body);

                                sm.setSendTo(list);
                                sm.setSendFrom(formUser);
                                EmailServices.sendMail(sm);
                        }

                        UserDAO userDao = UserDAOFactory.getDAO();
                        userDao.removeUser(userIDs);

                        BlogHelper.deleteAccount(userDao.getUser(userIDs).getLoginName());
                        try
                        {
                                if (Config.getIsIntegrateJieFo())
                                {
                                        userDao.removeJieFoUser(userIDs);
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }


                }
                return mapping.findForward(resultScreen);
        }
}
