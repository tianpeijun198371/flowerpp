/**
 * AddUserToOrganAction.java. 
 * User: huangsb  Date: 2004-8-16 
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved. 
 */
package com.ulearning.ulms.user.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.form.JieFoClerkForm;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;

import java.util.List;

public class AddUserToOrganAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                OrganDAO orgDao = OrganDAOFactory.getDAO();
                UserDAO daoUser = UserDAOFactory.getDAO();
                OrgUserForm ouf = new OrgUserForm();
                UserForm uf = new UserForm();
                String roleID[] = request.getParameterValues("roleID");
                int orgID = Integer.parseInt(request.getParameter("orgID"));

                OrganForm oof = orgDao.getOrgan(orgID);
                String loginName = request.getParameter("loginName");
                int userID = UserHelper.getUserID(loginName);
                List organList = orgDao.getUserAsp(orgID);
                //LogUtil.debug("user", "[UserDAOOracle]====================the orgID is : " + orgID);
                //LogUtil.debug("user", "[UserDAOOracle]====================the details.getCatalogID() is : " + details.getCatalogID());
                OrganForm organForm = (OrganForm) organList.get(organList.size() - 1);

                int leibie = 0;
                if (userID == 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "<bean:message key=\"button.loginname\"/>不存在！");
                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }
                else if (OrganHelper.isExsistOrganUser(orgID, userID))
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "此<bean:message key=\"button.loginname\"/>已存在，请另外选择！");
                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }
                else
                {
                        ouf.setOrgID(orgID);
                        ouf.setUserID(userID);
                        orgDao.addOrganUser(ouf);

                        //update jiefo user
                        uf = UserHelper.getUser(new Integer(userID).toString());

                        try
                        {
                                for (int j = 0; j < roleID.length; j++)
                                {
                                        if (Integer.parseInt(roleID[j]) == 10)
                                        {
                                                leibie = 5;

                                                int jiefoorgID = organForm.getOrgID();
                                                JieFoClerkForm jf = new JieFoClerkForm();

                                                jf.setUserID(userID);
                                                jf.setOrganID(jiefoorgID);
                                                jf.setClerk_name(uf.getLoginName());
                                                jf.setName(uf.getName());
                                                //jf.setClerk_pwd(uf.getPassword());
                                                if (uf.getSex().equals("1"))
                                                {
                                                        jf.setClerk_sex("男");
                                                }
                                                else
                                                {
                                                        jf.setClerk_sex("女");
                                                }
                                                jf.setClerk_job(uf.getDesc1());
                                                jf.setClerk_address(uf.getAddress());
                                                jf.setClerk_telephone(uf.getPhone());
                                                jf.setClerk_email(uf.getMail());
                                                jf.setClerk_xl(uf.getEduLevel());
                                                jf.setClerk_BM(oof.getOrgName());
                                                jf.setClerk_WorkType(uf.getDesc1());
                                                jf.setClerk_SFZNumber(uf.getCard());
                                                jf.setClerk_post(uf.getPostalcode());
                                                jf.setLeibie(leibie);
                                                daoUser.updateJieFoUser(jf);
                                        }
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }

                }

                SecurityDAO dao = SecurityDAOFactory.getDAO();
                for (int i = 0; i < roleID.length; i++)
                {
                        UserRoleForm urf = new UserRoleForm();
                        urf.setUserID(userID);
                        urf.setRoleID(Integer.parseInt(roleID[i]));
                        urf.setRelationID(orgID);
                        urf.setType(SecurityConstants.USER_ORGAN_RELATION);
                        dao.addUserRole(urf);
                }

                return mapping.findForward(resultScreen);
        }

}
