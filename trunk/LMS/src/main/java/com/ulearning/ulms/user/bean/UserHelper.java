/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 13, 2004
 * Time: 3:18:40 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.user.bean;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.core.security.exceptions.SecurityDAOSysException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.lmslog.helper.LmslogHelper;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.tools.calendar.helper.CalendarHelper;
import com.ulearning.ulms.tools.calendar.helper.PreferenceHelper;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.exceptions.UserDuplicatException;
import com.ulearning.ulms.user.form.JieFoClerkForm;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.model.UserModel;
import com.ulearning.ulms.blog.helper.BlogHelper;
import com.ulearning.ulms.blog.exceptions.BlogSysException;
import com.ulearning.ulms.blog.form.AccountForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;

import javax.ejb.EJBException;

public class UserHelper
{

        /**
         * Wrapping the get user method for JSP and  the other modules
         *
         * @param userID
         * @return the user modle according to the userID
         */
        public static UserForm getUser(String userID)
        {
                UserForm uf = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        uf = userDao.getUser(userID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return uf;
        }


        public static UserForm getUserByLoginName(String loginName)
        {
                UserForm uf = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        uf = userDao.getUserByLoginName(loginName);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return uf;
        }


        public static UserForm getUserByJiaZhang(String loginName)
        {
                UserForm uf = null;
                String stuLoginName = "";
                try
                {
                        if ((loginName == null) || (loginName.trim().length() == 0))
                        {
                                return null;
                        } else
                        {
                                if ((loginName.indexOf("jz_")) != -1)
                                {
                                        stuLoginName = loginName.substring(3, loginName.trim().length());
                                }
                        }
                        UserDAO userDao = UserDAOFactory.getDAO();
                        uf = userDao.getUserByLoginName(stuLoginName);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return uf;
        }

        /**
         * Gets BLOG accountForm from database according to userID
         *
         * @param userID
         * @return AccountForm,default is null
         */
        public static AccountForm getBlogAccount(int userID)
        {
                AccountForm accountForm = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        UserForm uf = userDao.getUser(String.valueOf(userID));

                        if (uf != null)
                        {
                                String loginName = uf.getLoginName();
                                accountForm = BlogHelper.get(uf.getLoginName());
                                if (accountForm == null)
                                {
                                        BlogHelper.addAccount(uf);
                                        accountForm = BlogHelper.get(loginName);
                                }
                        }
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return accountForm;
        }


        public static void delUser(String userID) throws ULMSException
        {
                try
                {
                        //UserDAO userDao = UserDAOFactory.getDAO();
                        // userDao.removeUser(userID);
                        deletedRelationData(Integer.parseInt(userID));
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public static int checkOrgUser(int userID) throws UserDAOSysException
        {
                int useID = 0;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        useID = userDao.checkOrgUser(userID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return useID;
        }

        public static void updateStates(int userID, int available)
        {
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userDao.updateStates(userID, available);
                }
                catch (Exception e)
                {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
        }

        public static void addJieFoUser(JieFoClerkForm cf)
        {
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userDao.addJieFoUser(cf);
                }
                catch (Exception e)
                {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
        }

        public static void updateJieFoUser(JieFoClerkForm cf)
        {
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userDao.updateJieFoUser(cf);
                }
                catch (Exception e)
                {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
        }

        public static void updateLeibie(int userID, int leibie)
        {
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userDao.updateLeiBie(userID, leibie);
                        ;
                }
                catch (Exception e)
                {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
        }

        public static void removeJieFoUser(String userID)
        {
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userDao.removeJieFoUser(userID);
                }
                catch (Exception e)
                {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
        }

        /**
         * Wrapping the get userList method for JSP and  the other modules
         *
         * @param catlogID
         * @return the user list according to the catalog
         */
        public static List getUserList(int catlogID)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getUserList(catlogID + "");
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        /**
         * 得到所有需要审批的用户列表
         *
         * @return ;
         */
        public static List getApplyUserList()
        {
                List userLists = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userLists = userDao.getApplyUserList();
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userLists;
        }

        /**
         * 得到所有需要审批的用户列表
         *
         * @return ;
         */
        public static List getApplyUserList(int aspID)
        {
                List userLists = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userLists = userDao.getApplyUserList(aspID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userLists;
        }

        /**
         * Wrapping the get userList method for JSP and  the other modules
         *
         * @param search
         * @return the user list according to the catalog
         */
        public static List getSearchUserList(UserForm search)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getSearchUserList(search);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        public static List getSearchUserListByRole(UserForm search)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getSearchUserListByRole(search);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }


        public static List getAllUserList(int orgID, int userType)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getUserList(orgID, userType);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        /**
         * Wrapping the get userList method for JSP and  the other modules
         *
         * @param ogID
         * @return the user list according to the catalog
         */
        public static List getAllUserList(int ogID)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getUserList(ogID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        public static int getCountAllUserList(int orgID)
        {
                int num = 0;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        num = userDao.getCountUserList(orgID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return num;
        }


        public static List getAllUserList(int ogID, int firse, int max)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getUserList(ogID, firse, max);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                return userList;
        }


        /**
         * Wrapping the get userList method for JSP and  the other modules
         *
         * @param aspID
         * @return the user list according to the catalog
         */
        public static int getAspUserCount(int aspID) throws UserDAOSysException
        {
                int userCount = 0;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userCount = userDao.getAspUserCount(aspID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userCount;
        }

        /**
         * Wrapping the get userList method for JSP and  the other modules
         *
         * @param orgID
         * @return the user list according to the catalog
         */
        public static int getUserCount(int orgID) throws UserDAOSysException
        {
                int userCount = 0;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userCount = userDao.getUserCount(orgID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userCount;
        }

        /**
         * Judge whether the userID is exist
         *
         * @param userID
         * @return true if exist, false otherwise
         */
        public static boolean isExistUser(String userID)
        {
                boolean isExist = false;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        isExist = userDao.isExistUser(userID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return isExist;
        }

        /**
         * Judge whether the userID is exist
         *
         * @param loginName
         * @param aspID
         * @return true if exist, false otherwise
         */
        public static boolean isExistUser(String loginName, int aspID)
        {
                boolean isExist = false;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        isExist = userDao.isExistUser(loginName, aspID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return isExist;
        }

        /**
         * Get the user list by the orgID
         *
         * @param orgID
         * @param orgType org : 0  , asp: 1
         * @param desc1
         * @return The prepared arraylist UserForm,default size is 0
         */
        public static List getUserList(int orgID, int orgType, String desc1)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getUserList(orgID, orgType, desc1);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        /**
         * Wrapping the get userList method for JSP and  the other modules
         *
         * @param orgID
         * @return the user list according to the orgID
         */
        public static List getUserList(String orgID)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getUserList(orgID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        /**
         * Wrapping the get userList method for JSP and  the other modules
         *
         * @param aspID
         * @return the user list according to the orgID
         */
        public static List getAspUserList(int aspID)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getAspUserList(aspID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        /**
         * Get all the user under the organ and which in the sub organ from it.
         *
         * @param orgID
         * @return Prepared user list, otherwise return en empty arraylist
         */
        public static List getAllOrganUser(int orgID)
        {
                List userList = new ArrayList();
                try
                {
                        UserDAO userDAO = UserDAOFactory.getDAO();
                        List organList = OrganHelper.getOrganTree(orgID);
                        List orgUsers = userDAO.getCatalogUserList(orgID);
                        for (int i = 0; i < orgUsers.size(); i++)
                        {
                                UserForm uf = (UserForm) orgUsers.get(i);
                                userList.add(uf);
                        }
                        for (int i = 0; i < organList.size(); i++)
                        {
                                OrganForm of = (OrganForm) organList.get(i);
                                orgUsers = userDAO.getCatalogUserList(of.getOrgID());
                                for (int j = 0; j < orgUsers.size(); j++)
                                {
                                        UserForm uf = (UserForm) orgUsers.get(j);
                                        userList.add(uf);
                                }
                        }
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        /**
         * Judge whether the userID is exist
         *
         * @param userID
         * @return true if exist, false otherwise
         */
        public static boolean isExistUser(int userID)
        {
                boolean isExist = false;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        isExist = userDao.isExistUser(new Integer(userID).toString());
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return isExist;
        }

        /**
         * Get userID by loginName
         *
         * @param loginName
         * @return int value if exist, 0 otherwise
         */
        public static int getUserID(String loginName)
        {
                int userID = 0;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userID = userDao.getUserID(loginName);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userID;
        }

        /**
         * Get orgID according to userID
         *
         * @param userID
         * @return the prepared userID, 0 if not found the userID
         */
        public static int getOrgID(int userID)
        {
                int orgID = 0;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        orgID = userDao.getOrgID(userID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return orgID;
        }

        /**
         * Get OrganForm from userID
         *
         * @param userID
         * @return the prepared OrganForm, default value is null
         */
        public static OrganForm getOrganForm(int userID)
        {
                OrganForm of = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        int orgID = userDao.getOrgID(userID);
                        of = OrganHelper.getOrgan(orgID);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return of;
        }

        public static void updateUser(UserForm details) throws UserDAOSysException, BlogSysException
        {
                UserDAO userDao = UserDAOFactory.getDAO();
                userDao.updateUser(details);
                BlogHelper.updateAccount(details);

        }

        public static int isExistIdentity(String Identity) throws UserDAOSysException, BlogSysException
        {
                UserDAO userDao = UserDAOFactory.getDAO();
                return userDao.isExistIdentity(Identity);

        }

        public static void deletedRelationData(int userID) throws ULMSException
        {
                //keyh
                CalendarHelper.deleteByUserID(userID);
                LmslogHelper.deleteByUserID(userID);
                PreferenceHelper.delete(userID);
                //fengch
                CourseUserHelper.deleteByUserID(userID);

                //huangsb
                SecurityHelper.removeUserRole(userID);

                if (Config.getIsIntegrateJieFo())
                {
                        removeJieFoUser(String.valueOf(userID));
                }
                BlogHelper.deleteAccount(UserHelper.getUser(String.valueOf(userID)).getLoginName());
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userDao.removeUser(new Integer(userID).toString());
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        /**
         * Add a user to system
         *
         * @param details
         * @return userID, default is 0
         * @throws EJBException
         * @throws UserDuplicatException
         */
        public static int addUser(UserForm details) throws EJBException, UserDuplicatException
        {
                int userID = 0;
                try
                {
                        OrganDAO orgDao = OrganDAOFactory.getDAO();
                        SecurityDAO secDao = SecurityDAOFactory.getDAO();
                        UserDAO userDAO = UserDAOFactory.getDAO();
                        int dupUserID = UserHelper.getUserID(details.getLoginName());
                        if (dupUserID > 0)
                        {
                                throw new UserDuplicatException("The LoginName is duplicat!");
                        }
                        String pwd = details.getPassword();
                        userID = userDAO.addUser(details);
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

                                        } else
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
                                                        jf.setClerk_sex("男");
                                                } else
                                                {
                                                        jf.setClerk_sex("女");
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
                        //修改权限后
                        // int relationdID = SecurityConstants.DEFAULT_RELATIONID_PLATFORM;
//                        if (user_type == SecurityConstants.USER_ORGAN_RELATION) {
//                                relationdID = orgID;
//                                urf = new UserRoleForm();
//                                urf.setRoleID(SecurityConstants.ROLE_SYS_STAFF);
//                                urf.setUserID(userID);
//                                urf.setRelationID(SecurityConstants.DEFAULT_RELATIONID_PLATFORM);
//                                urf.setType(SecurityConstants.USER_PLATFORM_RELATION);
//                                secDao.addUserRole(urf);
//                        }
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
                                //修改权限后08-19
//                                if (roleID.length > 0) {
//                                        for (int i = 0; i < roleID.length; i++) {
//                                                urf = new UserRoleForm();
//                                                urf.setRoleID(Integer.parseInt(roleID[i]));
//                                                urf.setUserID(userID);
//                                                urf.setRelationID(relationdID);
//                                                urf.setType(user_type);
//                                                secDao.addUserRole(urf);
//                                        }
//                                }
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
                                //添加家长帐号
                                String projectName = com.ulearning.ulms.core.util.Config.getProjectName();

                                if (projectName.equals("XLN"))  //假如项目是农广校
                                {
                                        if (Integer.parseInt(orgRoleID[0]) == SecurityConstants.ROLE_COURSR_STUDENT)
                                        {
                                                String jz_name = "jz_" + details.getLoginName();
                                                details.setLoginName(jz_name);
                                                details.setAvailable("1");
                                                details.setCatalogID(orgID);
                                                details.setPassword(pwd);
                                                details.setName(details.getRemark8());
                                                userID = userDAO.addUser(details);
                                                if (userID != 0)
                                                {
                                                        ouf.setOrgID(details.getCatalogID());
                                                        ouf.setUserID(userID);
                                                        orgDao.addOrganUser(ouf);
                                                        //UserRoleForm urf = new UserRoleForm();
                                                        urf.setUserID(userID);
                                                        urf.setRoleID(SecurityConstants.ROLE_STUPARENT);
                                                        urf.setRelationID(orgID);
                                                        urf.setType(SecurityConstants.USER_ORGAN_RELATION);
                                                        //urf.setRelationID(SecurityConstants.DEFAULT_RELATIONID_PLATFORM);
                                                        //urf.setType(SecurityConstants.USER_CERTIFICATE_RELATION);
                                                        secDao.addUserRole(urf);
                                                }
                                        }
                                }
                        }
                }
                catch (UserDAOSysException ue)
                {
                        ue.printStackTrace();
                }
                catch (OrganDAOSysException ose)
                {
                        ose.printStackTrace();
                }
                catch (SecurityDAOSysException se)
                {
                        se.printStackTrace();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                return userID;
        }

        /**
         * get user teacher  or student
         *
         * @param relationID aspID
         * @param roleID     roleID
         * @param type
         * @return
         */
        public static List getUserListByRole(int relationID, int roleID, int type)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getUserListByRole(relationID, roleID, type);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }

        /**
         * get user teacher  or student
         *
         * @param relationID aspID
         * @param roleID     roleID
         * @param type
         * @return
         */
        public static List getMaster(int relationID, int roleID, int type)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getMaster(relationID, roleID, type);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }


        /**
         * get user by registerDate
         *
         * @param relationID aspID
         * @param roleID     roleID
         * @param type
         * @return
         */
        public static List getUserByRegisterDate(int relationID, int roleID, int type)
        {
                List userList = null;
                try
                {
                        UserDAO userDao = UserDAOFactory.getDAO();
                        userList = userDao.getUserByRegisterDate(relationID, roleID, type);
                }
                catch (UserDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return userList;
        }
        /**
         * 根据externalSystemUserID返回对应User
         *
         * @param externalSystemUserID
         * @return
         * @throws UserDAOSysException
         */
        public static UserModel getUserByExternalSystemUserID(int externalSystemUserID) throws UserDAOSysException
        {
                UserDAO userDao = UserDAOFactory.getDAO();
                return userDao.getUserByExternalSystemUserID(externalSystemUserID);
        }

        public static void main(String[] args)
        {
                List as = UserHelper.getUserListByRole(1, 9, 1);
                System.out.println("as = " + as.size());
                for (int i = 0; i < as.size(); i++)
                {
                        UserForm uf = (UserForm) as.get(i);
                        System.out.println("uf.getName() = " + uf.getName());
                        System.out.println("uf.getLoginName() = " + uf.getLoginName());
                }
        }
}
