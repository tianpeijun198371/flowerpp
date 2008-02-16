/**
 * Created by IntelliJ IDEA.
 * Security: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.core.security.dao;

import com.ulearning.ulms.core.security.exceptions.SecurityDAOSysException;
import com.ulearning.ulms.core.security.form.RoleForm;
import com.ulearning.ulms.core.security.form.STicket;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.user.form.UserForm;

import java.util.List;


public interface SecurityDAO
{
        public int checkSecurity(String userName, String passwd)
                throws SecurityDAOSysException;

        /**
         * 激活帐户
         *
         * @param userName
         * @return
         * @throws SecurityDAOSysException
         */
        public int checkActive(String userName) throws SecurityDAOSysException;

        public void setIsAvailable(UserForm userForm)
                throws SecurityDAOSysException;

        public boolean isHasPermission(STicket ticket, int relationID, int type,
                                       int permitID) throws SecurityDAOSysException;

        public List getModuleList(STicket ticket) throws SecurityDAOSysException;

        public List getRoleList(int sysID) throws SecurityDAOSysException;

        public boolean isExistUser(int userID, int orgID)
                throws SecurityDAOSysException;

        public int addUserRole(UserRoleForm urf) throws SecurityDAOSysException;

        public void delUserRole(UserRoleForm urf) throws SecurityDAOSysException;

        public List getUserRoleList(int userID, int relationID, int type)
                throws SecurityDAOSysException;

        public List getUserRoleList(int relationID, int type)
                throws SecurityDAOSysException;

        public List getUserOrgRoleList(int userID, int relationID)
                throws SecurityDAOSysException;

        public void addRole(RoleForm rf) throws SecurityDAOSysException;

        public void updateRole(RoleForm rf) throws SecurityDAOSysException;

        public void ConfigModule(String proID, int isAvailable)
                throws SecurityDAOSysException;

        public RoleForm getRole(int roleID) throws SecurityDAOSysException;

        public void removeRole(String roleID) throws SecurityDAOSysException;

        public void removeUserRole(String userID) throws SecurityDAOSysException;

        public void removeUserRole(int userID, int relationID, int type)
                throws SecurityDAOSysException;

        public void updatePWD(String userID, String passwd)
                throws SecurityDAOSysException;

        public STicket getTicket(int userID) throws SecurityDAOSysException;

        public STicket getRoleList(int userID, int relationID, int type)
                throws SecurityDAOSysException;

        public List getRoleForm(int userID, int relationID, int type)
                throws SecurityDAOSysException;

        public List getPropertyList(String moduleID) throws SecurityDAOSysException;

        public List getAllPropertyList(String moduleID)
                throws SecurityDAOSysException;

        public List getPermissionList(String moduleID)
                throws SecurityDAOSysException;

        public List getPropertyPerm(int PropertyID) throws SecurityDAOSysException;

        public List getModuleList(int sysID) throws SecurityDAOSysException;

        public List getAllPermitByRoleID(int roleID) throws SecurityDAOSysException;

        public List getIsUsePermitByRoleID(int roleID)
                throws SecurityDAOSysException;

        public boolean isAdmin(int userID, int relationID, int type)
                throws SecurityDAOSysException;

        public boolean isHasPermission(String userID, String relationID,
                                       String type, String moduleID) throws SecurityDAOSysException;

        public boolean isHasPermission(int userID, int relationID, int type,
                                       int moduleID) throws SecurityDAOSysException;

        public boolean isParentHasPermission(int userID, int relationID, int type,
                                             int permitID) throws SecurityDAOSysException;

        public int getRoleIDByName(String roleName) throws SecurityDAOSysException;

        public int getRoleID(String roleName, int type)
                throws SecurityDAOSysException;

        public boolean isExisitCourseUserRole(int userID, int roleID,
                                              int relationID, int type) throws SecurityDAOSysException;

        public List getPermissionListByRoleID(int proID, int roleID)
                throws SecurityDAOSysException;

        public boolean isUnusedRole(int roleID) throws SecurityDAOSysException;

        public List getUserRoleID(int userID) throws SecurityDAOSysException;

        /**
         * 根据用户得到用户对应的角色和角色权限
         *
         * @param userID
         * @return
         * @throws SecurityDAOSysException
         */
        public List getUserRolePermission(int userID)
                throws SecurityDAOSysException;

        /*
        * add by keyh
        */
        public void changeOrgUser(OrgUserForm oldOrgUserForm,
                                  OrgUserForm newOrgUserForm) throws SecurityDAOSysException;

        public void changeUserRole(int userID, int oldRelationID, int oldtype,
                                   int newRelationID, String[] orgRoleID) throws SecurityDAOSysException;

        public List getUserListbyRole(int roleID, int relationID, int type)
                throws SecurityDAOSysException;

        public int getRoleID(int userID, int relationID, int type)
                throws SecurityDAOSysException;

}
