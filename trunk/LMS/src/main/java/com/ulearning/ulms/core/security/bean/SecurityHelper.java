/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 13, 2004
 * Time: 3:18:40 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.core.security.bean;

import com.ulearning.ulms.core.security.dao.SecurityDAO;
import com.ulearning.ulms.core.security.dao.SecurityDAOFactory;
import com.ulearning.ulms.core.security.exceptions.SecurityDAOSysException;
import com.ulearning.ulms.core.security.form.PermissionForm;
import com.ulearning.ulms.core.security.form.RoleForm;
import com.ulearning.ulms.core.security.form.STicket;
import com.ulearning.ulms.user.form.UserForm;

import java.util.List;


public class SecurityHelper
{
        /**
         * Judge whether the user is existed in the given organ ID
         *
         * @param userID
         * @param orgID
         * @return true if the user exist,false otherwise
         */
        public static boolean isExistUser(int userID, int orgID)
        {
                boolean isExistUser = false;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        isExistUser = dao.isExistUser(userID, orgID);
                }
                catch (SecurityDAOSysException sdse)
                {
                        sdse.printStackTrace();
                }

                return isExistUser;
        }

        public static void setIsAvailable(UserForm userForm)
                throws SecurityDAOSysException
        {
                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        dao.setIsAvailable(userForm);
                }
                catch (SecurityDAOSysException sdse)
                {
                        sdse.printStackTrace();
                }
        }

        //是否具有角色操作权限
        public static boolean getIsAvailable(int RoleID)
                throws SecurityDAOSysException
        {
                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        List al = dao.getAllPermitByRoleID(RoleID);

                        for (int i = 0; i < al.size(); i++)
                        {
                                PermissionForm pf = (PermissionForm) al.get(i);

                                if ((pf.getPermitID() == SecurityConstants.SYSTEM_ROLE_MANAGE) ||
                                        (pf.getPermitID() == SecurityConstants.SYSTEM_USER_MANAGE))
                                {
                                        if (pf.getStatus() == 1)
                                        {
                                                return true;
                                        }
                                }
                        }

                        //dao.setIsAvailable(userForm);
                        return false;
                }
                catch (SecurityDAOSysException sdse)
                {
                        sdse.printStackTrace();
                }

                return false;
        }

        /**
         * Judge whether the user role is existed in system
         *
         * @param userID     userID
         * @param roleID     roleID
         * @param relationID the ralation id to the user,e.g. courseID, orgID
         * @param type       1,means the platform ID, 2 means the ralationID is orgID, 3 means the ralationID is courseID
         * @return true if the role exist,false otherwise
         */
        public static boolean isExisitCourseUserRole(int userID, int roleID,
                                                     int relationID, int type)
        {
                boolean isExistUser = false;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        isExistUser = dao.isExisitCourseUserRole(userID, roleID,
                                relationID, type);
                }
                catch (SecurityDAOSysException sdse)
                {
                        sdse.printStackTrace();
                }

                return isExistUser;
        }

        /**
         * Verify whether the password is correct according to the userName
         *
         * @param userName the login name
         * @param passwd   the password need to verify
         * @return userID if the password is true,0 otherwise
         */
        public static int checkSecurity(String userName, String passwd)
        {
                int isOK = 0;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        isOK = dao.checkSecurity(userName, passwd);
                }
                catch (SecurityDAOSysException sdse)
                {
                        sdse.printStackTrace();
                }

                return isOK;
        }

        public static int checkActive(String userName)
        {
                int isOK = 0;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        isOK = dao.checkActive(userName);
                }
                catch (SecurityDAOSysException sdse)
                {
                        sdse.printStackTrace();
                }

                return isOK;
        }

        /**
         * Wrapping the get roleList method for JSP and  the other modules
         *
         * @param sysID
         * @return the role list according to the sysId
         */
        public static List getRoleList(String sysID)
        {
                List roleList = null;

                if ((sysID == null) || (sysID.trim().length() == 0))
                {
                        return null;
                }

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        roleList = dao.getRoleList(Integer.parseInt(sysID));
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return roleList;
        }

        /**
         * Wrapping the get roleList method for JSP and  the other modules
         *
         * @param sysID
         * @return the role list according to the sysId
         */
        public static List getRoleList(int sysID)
        {
                List roleList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        roleList = dao.getRoleList(sysID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return roleList;
        }

        /**
         * Get the role ticket from database by userID
         *
         * @param userID
         * @return prepared user security ticket
         */
        public static STicket getTicket(int userID)
        {
                STicket ticket = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        ticket = dao.getTicket(userID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return ticket;
        }

        /**
         * Getting all the  available property List according by moduleID
         *
         * @param moduleID
         * @return the role list according to the moduleID
         */
        public static List getPropertyList(String moduleID)
        {
                List roleList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        roleList = dao.getPropertyList(moduleID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return roleList;
        }

        /**
         * Getting all the  Property List according by moduleID
         *
         * @param moduleID
         * @return the role list according to the moduleID
         */
        public static List getAllPropertyList(String moduleID)
        {
                List roleList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        roleList = dao.getAllPropertyList(moduleID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return roleList;
        }

        /**
         * Getting all the permitions by PropertyID
         *
         * @param PropertyID
         * @return the role list according to the moduleID
         */
        public static List getPropertyPerm(int PropertyID)
        {
                List perList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        perList = dao.getPropertyPerm(PropertyID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return perList;
        }

        /**
         * Getting all the permitions by roleID and property ID
         *
         * @param ProID
         * @param roleID
         * @return permission list , default is null
         */
        public static List getPermissionListByRoleID(int ProID, int roleID)
        {
                List perList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        perList = dao.getPermissionListByRoleID(ProID, roleID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return perList;
        }

        /**
         * Wrapping the get module permission List method for JSP and  the other modules
         *
         * @param moduleID
         * @return the permission list according to the moduleID
         */
        public static List getPermissionList(String moduleID)
        {
                List permissionList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        permissionList = dao.getPermissionList(moduleID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return permissionList;
        }

        /**
         * Wrapping the get view moduleList method for JSP and  the other modules
         *
         * @param ticket
         * @return the moduleList which be viewed by user
         */
        public static List getModuleList(STicket ticket)
        {
                List moduleList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        moduleList = dao.getModuleList(ticket);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return moduleList;
        }

        /**
         * Wrapping the get view moduleList method for JSP and  the other modules
         *
         * @param sysID
         * @return the moduleList which be viewed by user
         */
        public static List getModuleList(int sysID)
        {
                List moduleList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        moduleList = dao.getModuleList(sysID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return moduleList;
        }

        /**
         * Judge whether user has the permission of viewing the module
         *
         * @param ticket   user security ticket
         * @param moduleID system module
         * @return true if has ,false otherwise
         */
        public static boolean isHasModulePermission(STicket ticket, int moduleID)
        {
                List moduleList = null;
                boolean isOK = false;

                if (ticket == null)
                {
                        return false;
                }

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        moduleList = dao.getModuleList(ticket);

                        for (int i = 0; i < moduleList.size(); i++)
                        {
                                String security = moduleList.get(i).toString();

                                if (moduleID == Integer.parseInt(security))
                                {
                                        return true;
                                }
                        }
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return isOK;
        }

        /**
         * Check whether the user has the permission according to the given parameters
         *
         * @param userID     the user need to check
         * @param relationID the ralation id to the user,e.g. courseID, orgID
         * @param type       1,means the platform ID, 2 means the ralationID is orgID, 3 means the ralationID is courseID
         * @param permitID   the permissionID of the module,e.g. 3010 means Course announcement management
         * @return
         */
        public static boolean isHasPermission(String userID, String relationID,
                                              String type, String permitID)
        {
                boolean isOK = false;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        isOK = dao.isHasPermission(userID, relationID, type, permitID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return isOK;
        }

        /**
         * Check whether the user has the permission according to the permissionID and relationID
         *
         * @param userID     the user need to check
         * @param relationID the ralation id to the user,e.g. courseID, orgID
         * @param type       1,means the platform ID, 2 means the ralationID is orgID, 3 means the ralationID is courseID,
         *                   please see Relation Type in SecurityConstants for detail
         * @param permitID   the permissionID of the module,e.g. 3010 means Course announcement management
         *                   please see Permission ID in SecurityConstants for detail
         * @return true if the authentication is success,false otherwise
         */
        public static boolean isHasPermission(int userID, int relationID, int type,
                                              int permitID)
        {
                boolean isOK = false;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        isOK = dao.isHasPermission(userID, relationID, type, permitID);

                        if ((!isOK) && (type == SecurityConstants.USER_ORGAN_RELATION))
                        {
                                return dao.isParentHasPermission(userID, relationID, type,
                                        permitID);
                        }
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return isOK;
        }

        public static boolean isHasPermission(STicket st, int relationID, int type,
                                              int permitID)
        {
                boolean isPermission = false;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        isPermission = dao.isHasPermission(st, relationID, type, permitID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return isPermission;
        }

        /**
         * Check whether the user is administrator with  the userID , relationID
         * and type
         *
         * @param userID     the user need to check
         * @param relationID the ralation id to the user,e.g. courseID, orgID
         * @param type       1,means the platform ID, 2 means the ralationID is orgID, 3 means the ralationID is courseID,
         *                   please see Relation Type in SecurityConstants for detail
         * @return true if the authentication is success,false otherwise
         */
        public static boolean isAdmin(int userID, int relationID, int type)
        {
                boolean isOK = false;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        isOK = dao.isAdmin(userID, relationID, type);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return isOK;
        }

        /**
         * Get user role list according to userID, relationID and type
         *
         * @param userID     user id
         * @param relationID courseID, orgID
         * @param type       see Relation Type in SecurityConstants for detail
         * @return prepared user role list, default empty arraylist
         */
        public static List getUserRoleList(int userID, int relationID, int type)
        {
                List userRoleList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        userRoleList = dao.getUserRoleList(userID, relationID, type);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return userRoleList;
        }

        /**
         * Get the user roleForm list according to the relationID and type
         *
         * @param relationID courseID or orgID or 0 means platform
         * @param type       please see com.ulearning.ulms.core.security.bean.SecurityConstants
         * @return Prepared user role form list,default null
         */
        public static List getUserRoleList(int relationID, int type)
        {
                List userRoleList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        userRoleList = dao.getUserRoleList(relationID, type);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return userRoleList;
        }

        /**
         * Get the user roleForm list according to the relationID and userID
         *
         * @param relationID courseID or orgID or 0 means platform
         * @param userID     userID
         * @return Prepared user role form list,default null
         */
        public static List getUserOrgRoleList(int userID, int relationID)
        {
                List userRoleList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        userRoleList = dao.getUserOrgRoleList(userID, relationID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return userRoleList;
        }

        /**
         * Get permission list according to the roleID
         *
         * @param roleID the ID of role
         * @return all the availbale permission of role, null default
         */
        public static List getAllPermitByRoleID(int roleID)
        {
                List permitList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        permitList = dao.getAllPermitByRoleID(roleID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return permitList;
        }

        /**
         * 根据角色得到可用的权限
         *
         * @param roleID
         * @return
         */
        public static List getIsUsePermitByRoleID(int roleID)
        {
                List permitList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        permitList = dao.getIsUsePermitByRoleID(roleID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return permitList;
        }

        /**
         * Get roleForm according to the roleID
         *
         * @param roleID
         * @return prepared role form object ,default is null
         */
        public static RoleForm getRole(int roleID)
        {
                RoleForm rf = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        rf = dao.getRole(roleID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return rf;
        }

        /**
         * Get roleForm according to the roleID
         *
         * @param userID
         * @param relationID
         * @param type
         * @return prepared role form object ,default is an empty arraylist
         */
        public static List getRoleForms(int userID, int relationID, int type)
        {
                List roleList = null;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        roleList = dao.getRoleForm(userID, relationID, type);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return roleList;
        }

        /**
         * Get roleName according to the userID relationID and relation type
         *
         * @param userID
         * @param relationID
         * @param type
         * @return prepared name, default is ""
         */
        public static String getRoleName(int userID, int relationID, int type)
        {
                String roleName = "";

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        List roleList = dao.getRoleForm(userID, relationID, type);

                        if (roleList != null)
                        {
                                for (int i = 0; i < roleList.size(); i++)
                                {
                                        roleName = roleName +
                                                ((RoleForm) roleList.get(i)).getName() + " ";
                                }
                        }
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return roleName;
        }

        /**
         * Get all the roleName together from the role list
         *
         * @param roleList
         * @return the prepared string
         */
        public String getRoleName(List roleList)
        {
                String roleName = "";

                try
                {
                        if (roleList != null)
                        {
                                for (int i = 0; i < roleList.size(); i++)
                                {
                                        roleName = roleName +
                                                ((RoleForm) roleList.get(i)).getName() + " ";
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return roleName;
        }

        /**
         * Gets roleID from database according to userID,relationID and type
         * @param userID
         * @param relationID
         * @param type
         * @return  prepared roleID,default is 0
         */
        public static int getUserRoleForPortal(int userID, int relationID, int type)
        {
                int roleID = 0;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        roleID = dao.getRoleID(userID, relationID, type);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return roleID;
        }

        /**
         * Get the roleID from database according to the org Name
         *
         * @param roleName
         * @return the roleID of orgName,default is 0
         */
        public static int getRoleIDByName(String roleName)
        {
                int roleID = 0;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        roleID = dao.getRoleIDByName(roleName);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return roleID;
        }

        /**
         * Get the roleID from database according to the org Name
         *
         * @param roleName
         * @return the roleID of orgName,default is 0
         */
        public static int getRoleID(String roleName, int type)
        {
                int roleID = 0;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        roleID = dao.getRoleID(roleName, type);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return roleID;
        }

        /**
         * Judge whether the roleID is used by any other users
         *
         * @param roleID
         * @return true if the roleid is not used, otherwise false
         */
        public boolean isUnusedRole(int roleID)
        {
                boolean result = true;

                try
                {
                        SecurityDAO dao = SecurityDAOFactory.getDAO();
                        result = dao.isUnusedRole(roleID);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return result;
        }

        public static void updatePWD(String userID, String passwd)
                throws SecurityDAOSysException
        {
                try
                {
                        SecurityDAOFactory.getDAO().updatePWD(userID, passwd);
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public static void removeUserRole(int userID)
                throws SecurityDAOSysException
        {
                try
                {
                        SecurityDAOFactory.getDAO().removeUserRole(String.valueOf(userID));
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        //根据用户ID得到roleID
        public static List getUserRoleID(int userID) throws SecurityDAOSysException
        {
                List list = null;

                try
                {
                        list = SecurityDAOFactory.getDAO().getUserRoleID(userID);

                        // String aa="xxxx";
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }

        //根据roleID得到用户ID
        public static List getUserbyRoleID(int roleID, int relationID, int type)
                throws SecurityDAOSysException
        {
                List list = null;

                try
                {
                        list = SecurityDAOFactory.getDAO()
                                .getUserListbyRole(roleID, relationID, type);

                        // String aa="xxxx";
                }
                catch (SecurityDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }

        public static void main(String[] args) throws Exception
        {
                //System.out.println(SecurityHelper.getIsAvailable(6));
                List userList = getUserbyRoleID(SecurityConstants.ROLE_RESOURCE_PROVIDER,
                        0, 0);
                System.out.println("userList.size() = " + userList.size());
        }
}
