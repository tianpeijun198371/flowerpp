/**
 * UserEJB.java.
 * User: dengj  Date: 2004-6-10
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.user.ejb;

import com.ulearning.ulms.core.ejb.DefaultSessionBean;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.security.exceptions.SecurityDAOSysException;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.exceptions.UserDuplicatException;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.form.JieFoClerkForm;
import com.ulearning.ulms.util.log.LogUtil;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.List;

public class UserEJB extends DefaultSessionBean
{
        public UserEJB()
        {
        }

        public int addUser(UserForm details) throws EJBException, UserDuplicatException
        {
                try
                {
                        OrganDAO orgDao = OrganDAOFactory.getDAO();
                        SecurityDAO secDao = SecurityDAOFactory.getDAO();
                        userDAO = getDAO();
                        int dupUserID = UserHelper.getUserID(details.getLoginName());
                        if (dupUserID > 0)
                        {
                                throw new UserDuplicatException("The LoginName is duplicat!");
                        }
                        String pwd = details.getPassword();
                        int userID = userDAO.addUser(details);

                        //Add the user to organ and give it the pointed role
                        int orgID = details.getCatalogID();

                        OrganForm oof = orgDao.getOrgan(orgID);
                        int user_type = details.getUser_type();
                        String[] roleID = details.getRoleID();
                        String[] orgRoleID = details.getOrgRoleID();

                        try
                        {
                                //Add the user to jiefo
                                if (Config.getIsIntegrateJieFo())
                                {
                                        int leibie = 0;
                                        if (orgRoleID != null)
                                        {
                                                for (int i = 0; i < orgRoleID.length; i++)
                                                {
                                                        if (Integer.parseInt(orgRoleID[i]) == 10)
                                                        {
                                                                leibie = 5;
                                                        }
                                                }

                                        }
                                        else
                                        {
                                                for (int i = 0; i < roleID.length; i++)
                                                {
                                                        if (Integer.parseInt(roleID[i]) == 10)
                                                        {
                                                                leibie = 5;
                                                        }
                                                }
                                        }
                                        //List organList  = orgDao.getOrganParentList(orgID);
                                        List organList = orgDao.getUserAsp(orgID);
                                        LogUtil.debug("user", "[UserDAOOracle]====================pwd is : " + pwd);
                                        LogUtil.debug("user", "[UserDAOOracle]====================uf.getPassword : " + details.getPassword());
                                        details.setPassword(pwd);
                                        LogUtil.debug("user", "[UserDAOOracle]====================uf.setPassword(pwd) : " + details.getPassword());
                                        OrganForm organForm = (OrganForm) organList.get(organList.size() - 1);

                                        if (organForm.getIsAsp() == 1)
                                        {
                                                int jiefoorgID = organForm.getOrgID();
                                                JieFoClerkForm jf = new JieFoClerkForm();

                                                jf.setUserID(userID);
                                                jf.setOrganID(jiefoorgID);
                                                jf.setClerk_name(details.getLoginName());
                                                jf.setName(details.getName());
                                                jf.setClerk_pwd(details.getPassword());
                                                if (details.getSex().equals("1"))
                                                {
                                                        jf.setClerk_sex("ÄÐ");
                                                }
                                                else
                                                {
                                                        jf.setClerk_sex("Å®");
                                                }
                                                jf.setClerk_job(StringUtil.nullToStr(details.getDesc1()));
                                                jf.setClerk_address(StringUtil.nullToStr(details.getAddress()));
                                                jf.setClerk_telephone(StringUtil.nullToStr(details.getPhone()));
                                                jf.setClerk_email(details.getMail());
                                                jf.setClerk_BM(oof.getOrgName());
                                                jf.setClerk_xl(StringUtil.nullToStr(details.getEduLevel()));
                                                jf.setClerk_WorkType(StringUtil.nullToStr(details.getDescription()));
                                                jf.setClerk_SFZNumber(details.getCard());
                                                jf.setClerk_post(StringUtil.nullToStr(details.getPostalcode()));
                                                jf.setLeibie(leibie);
                                                userDAO.addJieFoUser(jf);
                                        }
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        UserRoleForm urf = null;

                        //begin to add role to user
                        int relationdID = SecurityConstants.DEFAULT_RELATIONID_PLATFORM;
                        if (user_type == SecurityConstants.USER_ORGAN_RELATION)
                        {
                                relationdID = orgID;
                                urf = new UserRoleForm();
                                urf.setRoleID(SecurityConstants.ROLE_SYS_STAFF);
                                urf.setUserID(userID);
                                urf.setRelationID(SecurityConstants.DEFAULT_RELATIONID_PLATFORM);
                                urf.setType(SecurityConstants.USER_PLATFORM_RELATION);
                                secDao.addUserRole(urf);
                        }
//                        else
//                        {
//                                urf = new UserRoleForm();
//                                urf.setRoleID(SecurityConstants.ROLE_ORG_STAFF);
//                                urf.setUserID(userID);
//                                urf.setRelationID(orgID);
//                                urf.setType(SecurityConstants.USER_ORGAN_RELATION);
//                                secDao.addUserRole(urf);
//                        }

                        if (userID != 0)
                        {
                                OrgUserForm ouf = new OrgUserForm();
                                ouf.setOrgID(orgID);
                                ouf.setUserID(userID);
                                //orgDao.addOrganUser(orgID, userID);
                                orgDao.addOrganUser(ouf);
                                if (roleID.length > 0)
                                {
                                        for (int i = 0; i < roleID.length; i++)
                                        {
                                                urf = new UserRoleForm();
                                                urf.setRoleID(Integer.parseInt(roleID[i]));
                                                urf.setUserID(userID);
                                                urf.setRelationID(relationdID);
                                                urf.setType(user_type);
                                                secDao.addUserRole(urf);
                                        }
                                }
                                if ((orgRoleID != null) && (orgRoleID.length > 0))
                                {
                                        for (int i = 0; i < orgRoleID.length; i++)
                                        {
                                                urf = new UserRoleForm();
                                                urf.setRoleID(Integer.parseInt(orgRoleID[i]));
                                                urf.setUserID(userID);
                                                urf.setRelationID(orgID);
                                                urf.setType(SecurityConstants.USER_ORGAN_RELATION);
                                                secDao.addUserRole(urf);
                                        }
                                }
                        }


                        return userID;
                }
                catch (UserDAOSysException e)
                {
                        throw new EJBException(e);
                }
                catch (OrganDAOSysException ose)
                {
                        throw new EJBException(ose);
                }
                catch (SecurityDAOSysException se)
                {
                        throw new EJBException(se);
                }
                catch (Exception se)
                {
                        throw new EJBException(se);
                }
        }

        private UserDAO getDAO() throws UserDAOSysException
        {
                if (userDAO == null)
                {
                        userDAO = UserDAOFactory.getDAO();
                }
                return userDAO;
        }

        private transient UserDAO userDAO;
}
