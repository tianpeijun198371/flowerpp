/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.user.dao;

import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.form.JieFoClerkForm;
import com.ulearning.ulms.user.model.UserModel;

import java.util.List;
import net.sf.hibernate.HibernateException;


public interface UserDAO
{

        public int addUser(UserForm details) throws UserDAOSysException;

        public void addJieFoUser(JieFoClerkForm form) throws UserDAOSysException;

        public void updateJieFoUser(JieFoClerkForm form) throws UserDAOSysException;

        public void updateLeiBie(int userID, int leibie) throws UserDAOSysException;

        public void updateUser(UserForm details) throws UserDAOSysException;

        public void updateStates(int userID, int available) throws UserDAOSysException;

        public void removeUser(String userID) throws UserDAOSysException;

        public void removeJieFoUser(String userID) throws UserDAOSysException;

        public UserForm getUser(String userID) throws UserDAOSysException;
        
	public UserForm getUserByLoginName(String loginname) throws UserDAOSysException;

        public UserModel getUserByExternalSystemUserID(int externalSystemUserID) throws UserDAOSysException;

        public int checkOrgUser(int userid) throws UserDAOSysException;

        /**
         * 得到所有需要申请用户
         *
         * @return
         * @throws UserDAOSysException
         */
        public List getApplyUserList() throws UserDAOSysException;

        public List getApplyUserList(int aspID) throws UserDAOSysException;

        public List getSearchUserListByRole(UserForm search) throws UserDAOSysException;

        public List getUserList(int catalogID) throws UserDAOSysException;

        public int getCountUserList(int catalogID) throws UserDAOSysException;

        public List getUserList(int catalogID, int firse, int max) throws UserDAOSysException, HibernateException;

        public List getUserList(String orgID) throws UserDAOSysException;

        public int getCountUserList(String orgID) throws UserDAOSysException;

        public List getUserList(String orgID, int first, int max) throws UserDAOSysException, HibernateException;

        public List getCatalogUserList(int catalogID) throws UserDAOSysException;

        public int getAspUserCount(int aspID) throws UserDAOSysException;

        public int getUserCount(int orgID) throws UserDAOSysException;

        public String getUserEmail(String userID) throws UserDAOSysException;

        public boolean isExistUser(String userID) throws UserDAOSysException;

        public boolean isExistUser(String loginName, int aspID) throws UserDAOSysException;

        public int getUserID(String loginName) throws UserDAOSysException;


        public int getIsExistEmail(String email) throws UserDAOSysException;


        public int getOrgID(int userID) throws UserDAOSysException;

        /**
         * Get the user list by the orgID
         *
         * @param orgID
         * @param isAsp org : 0  , asp: 1
         * @param desc1
         * @return The prepared arraylist UserForm,default size is 0
         * @throws UserDAOSysException
         */
        public List getUserList(int orgID, int isAsp, String desc1) throws UserDAOSysException;

        public List getSearchUserList(UserForm search) throws UserDAOSysException;

        public List getAspUserList(int aspID) throws UserDAOSysException;

        public void updatePwdQA(int userID, String pwdQuestion, String pwdAnswer)
                throws UserDAOSysException;

        List getUserList(int orgID, int userType)
                throws UserDAOSysException;

        public int isExistIdentity(String identity) throws UserDAOSysException;

        public List getUserListByRole(int relationID,int roleID,int type) throws UserDAOSysException;

        public List getMaster(int relationID,int roleID,int type) throws UserDAOSysException;

        public List getUserByRegisterDate(int relationID,int roleID,int type) throws UserDAOSysException;


}
