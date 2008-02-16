/**
 * ApplyUserAction.java.
 * User: Fengch  Date: 2005-8-29 10:21:14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.user.action;

import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.mail.SmtpModel;
import com.ulearning.ulms.core.mail.EmailServices;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.dao.OrganDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class ApplyUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                UserForm uf = (UserForm) form;
                OrganDAO orgDao = OrganDAOFactory.getDAO();
                UserDAO dao = UserDAOFactory.getDAO();

                UserForm userForm = UserHelper.getUser(new Integer(uf.getUserID()).toString());
                if(userForm.getRegisterDate()==null)
                {
                    uf.setRegisterDate(new Date(System.currentTimeMillis()));
                }else{
                    uf.setRegisterDate(userForm.getRegisterDate());
                }
                
                dao.updateUser(uf);
                int userID = uf.getUserID();
                if (userID != 0)
                {

                        OrgUserForm ouf = new OrgUserForm();
                        ouf.setOrgID(uf.getCatalogID());
                        ouf.setUserID(userID);
                        orgDao.updateOrganUser(ouf);
                        String userMail = uf.getMail();
                        String title = "注册通知";
                        //String url = Config.getulmsURL();//+"/UserActive.do?userID ="+userID;

                        String body = "尊敬的" + uf.getName() + "用户 :<br> " +
                                " 欢迎您注册成为动力大学正式成员 <br>" +
                                //" 用户名: " + uf.getLoginName() + "<br>" +
                                // " 密　码: " + uf.getPassword() + "<br>" +
                                " 现在就 <a href=\"" + Config.getulmsURL() + "\" target=_blank>登陆</a>动力大学";
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

                }

                return mapping.findForward(resultScreen);
        }
}
