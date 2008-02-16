/**
 * AddLoginUserAction.java. 
 * User: huangsb  Date: 2004-7-22 
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved. 
 */
package com.ulearning.ulms.user.action;

import com.ulearning.ulms.admin.webconfig.webcustom.bean.WebCustomHelper;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.form.LoginForm;
import com.ulearning.ulms.core.security.form.STicket;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class myceLoginAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                OrganDAO orgDao = OrganDAOFactory.getDAO();
                UserDAO userDao = UserDAOFactory.getDAO();
                String resultScreen = "success";
                String employeeid = request.getParameter("employeeid");
                String employee = request.getParameter("employee");
                String sex = request.getParameter("sex");
                String userName = request.getParameter("username");
                String email = request.getParameter("email");
                String comany = request.getParameter("comany");
                String department = request.getParameter("department");
                String assignment = request.getParameter("assignment");
                String character = request.getParameter("character");
                String myeduce = request.getParameter("myeduce");
                UserForm uf = new UserForm();
                int userID = UserHelper.getUserID(userName);
                if (userID > 0)
                {
                        uf.setUserID(Integer.parseInt(employeeid));
                        uf.setLoginName(userName);
                        uf.setPassword("123456");
                        uf.setName(employee);
                        uf.setAvailable("1");
                        uf.setMail(email);
                        uf.setDesc0(assignment);
                        uf.setDesc1(character);
                }
                else
                {
                        int orgID = OrganHelper.getOrgID(comany);
                        LogUtil.debug("user", "[AddLoginAction]===========get a orgID " + orgID);
                        if (orgID != -1)
                        {
                                List orgList = OrganHelper.getOrganTree(orgID);

                                for (int i = 0; i < orgList.size(); i++)
                                {
                                        OrganForm of = (OrganForm) orgList.get(i);
                                        int dupOrgID = OrganHelper.getOrgID(department); /*,of.getOrgID()*/
                                        if (dupOrgID != -1)
                                        {
                                                uf.setCatalogID(dupOrgID);
                                        }
                                        else
                                        {
                                                OrganForm organForm = new OrganForm();
                                                organForm.setParentID(orgID);
                                                organForm.setOrgName(department);
                                                organForm.setAspID(1);
                                                organForm.setOrgType(1);
                                                int catLogID = orgDao.addOrgan(organForm);
                                                LogUtil.debug("user", "[AddLoginAction]===========get a catLogID " + catLogID);

                                                uf.setCatalogID(catLogID);
                                        }
                                }
                        }
                        else
                        {
                                OrganForm organForm = new OrganForm();
                                organForm.setParentID(0);
                                organForm.setOrgName(comany);
                                organForm.setAspID(1);
                                organForm.setOrgType(1);
                                int catLogID = orgDao.addOrgan(organForm);
                                LogUtil.debug("user", "[AddLoginAction]===========get a catLogID " + catLogID);
                                uf.setCatalogID(catLogID);
                        }
                        uf.setLoginName(userName);
                        uf.setPassword("123456");
                        uf.setName(employee);
                        uf.setAvailable("1");
                        uf.setMail(email);
                        uf.setDesc0(assignment);
                        uf.setDesc1(character);
                        LogUtil.debug("user", "[AddLoginAction]===========get a orgID " + uf.getCatalogID());
                        userDao.addUser(uf);
                        LogUtil.debug("user", "[AddLoginAction]=========== get a account " + uf.getUserID());
                }

                //Login this user
                //LoginForm lf = (LoginForm) form;
                String name = "student";
                String passwd = "student";
                if (character.equalsIgnoreCase("1"))
                {
                        name = "lmsadmin";
                        passwd = "lmsadmin9088";
                }
                else if (character.equalsIgnoreCase("2"))
                {
                        name = "teacher";
                        passwd = "teacher";
                }
                userID = SecurityHelper.checkSecurity(name, passwd);
                if (userID > 0)
                {
                        resultScreen = "success";
                        STicket ticket = SecurityHelper.getTicket(userID);
                        request.getSession().setAttribute(LMSConstants.TICKET_KEY, ticket);
                        request.getSession().setAttribute(LMSConstants.USERID_KEY, new Integer(userID).toString());
                        request.getSession().setAttribute(LMSConstants.LOGINNAME_KEY, userName);
                        request.getSession().setAttribute(LMSConstants.USER_ORGID_KEY, new Integer(ticket.getOrgID()).toString());
                        request.getSession().setAttribute(LMSConstants.USER_ASPID_KEY, new Integer(ticket.getAspID()).toString());
                        HashMap properties = new HashMap();
                        properties.put("sysStartTime", String.valueOf(System.currentTimeMillis()));
                        properties.put("ip", request.getRemoteAddr());
                        request.getSession().setAttribute("Access", properties);
                        HashMap myConfig = WebCustomHelper.getMyConfig(userID, ticket.getOrgID(), ticket.getAspID());
                        request.getSession().setAttribute("myConfig", myConfig);
                }


                return mapping.findForward(resultScreen);
        }

}
