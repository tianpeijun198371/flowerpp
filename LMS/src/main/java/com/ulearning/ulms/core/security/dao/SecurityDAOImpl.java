/**
 * Created by IntelliJ IDEA.
 * Security: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.core.security.dao;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.core.crypto.MD5Encrypt;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.security.exceptions.SecurityDAOSysException;
import com.ulearning.ulms.core.security.form.*;
import com.ulearning.ulms.core.security.model.UserRoleModel;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.organ.form.OrgUserForm;
import com.ulearning.ulms.organ.model.OrganModel;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.model.UserModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.commons.lang.StringEscapeUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SecurityDAOImpl implements SecurityDAO
{
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;

        /**
         * Judge whether the user is in the given orgID
         *
         * @param userID
         * @param orgID
         * @return
         * @throws SecurityDAOSysException
         */
        public boolean isExistUser(int userID, int orgID)
                throws SecurityDAOSysException
        {
                boolean isExistUser = false;
                String sql = " from OrganUserModel oum where oum.comp_id.userid=" +
                        userID + " and oum.comp_id.orgid=" + orgID;

                try
                {
                        int duplicateID = HibernateDAO.count(sql);

                        if (duplicateID > 0)
                        {
                                isExistUser = true;
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while isExistUser(int userID, int orgID) " +
                                        "account; Serial = " + userID + " :\n" + se);
                }

                return isExistUser;
        }

        /**
         * @param userName
         * @param passwd
         * @return
         * @throws SecurityDAOSysException
         */
        public int checkSecurity(String userName, String passwd)
                throws SecurityDAOSysException
        {
                String sqlStr = " from UserModel um where  um.available = '1' " +
                        " and um.loginname = '" + userName + "'";
                String MD5Pwd = "";
                int userID = 0;

                try
                {
                        List objects = HibernateDAO.find(sqlStr);

                        if ((objects != null) && (objects.size() > 0))
                        {
                                UserModel um = ((UserModel) objects.get(0));
                                MD5Pwd = um.getPassword();
                                userID = um.getUserid();
                        }

                        if ((!passwd.equalsIgnoreCase(MD5Pwd))&&((!MD5Encrypt.compareEncrypted(MD5Pwd, passwd))))
                        {
                                userID = 0;
                        }

                        return userID;
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while checking Security " + "account; Serial = " +
                                        userName + " :\n" + se);
                }
        }

        /**
         * @param userName
         * @return
         * @throws SecurityDAOSysException
         */
        public int checkActive(String userName) throws SecurityDAOSysException
        {
                String sqlStr = " from UserModel um where  um.available = '1' " +
                        " and um.loginname = '" + userName + "'";
                String MD5Pwd = "";
                int userID = 0;

                try
                {
                        List objects = HibernateDAO.find(sqlStr);

                        if ((objects != null) && (objects.size() > 0))
                        {
                                UserModel um = ((UserModel) objects.get(0));
                                MD5Pwd = um.getPassword();
                                userID = um.getUserid();
                        }

                        //if (!MD5Encrypt.compareEncrypted(MD5Pwd, passwd)) userID = 0;
                        return userID;
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while checking Security " + "account; Serial = " +
                                        userName + " :\n" + se);
                }
        }

        public int checkActive(String userName, boolean Flag)
                throws SecurityDAOSysException
        {
                String sqlStr = " from UserModel um where um.loginname = '" + userName +
                        "'";
                String MD5Pwd = "";
                int userID = 0;

                try
                {
                        List objects = HibernateDAO.find(sqlStr);

                        if ((objects != null) && (objects.size() > 0))
                        {
                                UserModel um = ((UserModel) objects.get(0));
                                MD5Pwd = um.getPassword();
                                userID = um.getUserid();
                        }

                        //if (!MD5Encrypt.compareEncrypted(MD5Pwd, passwd)) userID = 0;
                        return userID;
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while checking Security " + "account; Serial = " +
                                        userName + " :\n" + se);
                }
        }

        public int checkCard(String card) throws SecurityDAOSysException
        {
                String sqlStr = " from UserModel um where um.card = '" + card + "'";
                String MD5Pwd = "";
                int userID = 0;

                try
                {
                        List objects = HibernateDAO.find(sqlStr);

                        if ((objects != null) && (objects.size() > 0))
                        {
                                UserModel um = ((UserModel) objects.get(0));
                                MD5Pwd = um.getPassword();
                                userID = um.getUserid();
                        }

                        //if (!MD5Encrypt.compareEncrypted(MD5Pwd, passwd)) userID = 0;
                        return userID;
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while checking Security " + "account; Serial = " +
                                        card + " :\n" + se);
                }
        }

        public void setIsAvailable(UserForm userForm)
                throws SecurityDAOSysException
        {
                try
                {
                        HibernateDAO.update(userForm.getUserModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new SecurityDAOSysException(
                                "ULMSSysException while updateUser(UserForm details) method" +
                                        e);
                }
        }

        /**
         * 判断用户在当前环境下是否有对应的权限
         *
         * @param ticket     用户Ticket
         * @param relationID relationID = orgID ; courseID; platform
         * @param type       type = 1 平台; 1 机构; 3 课程 ; 4 证书
         * @param permitID   权限ID
         * @return
         * @throws SecurityDAOSysException
         */
        public boolean isHasPermission(STicket ticket, int relationID, int type,
                                       int permitID) throws SecurityDAOSysException
        {
                boolean isPermission = false;
                List userRolePermission = ticket.getRolePermission();

                for (int k = 0; k < userRolePermission.size(); k++)
                {
                        RolePermission rsp = (RolePermission) userRolePermission.get(k);
                        UserRoleModel urm = rsp.getUrf();
                        String[][] rpm = rsp.getRolePermission();
                        String nowPermission = "";

                        if ((urm.getComp_id().getType() == type) &&
                                (urm.getComp_id().getRelationid() == relationID))
                        {
                                nowPermission = new Integer(ticket.getUserID()).toString() +
                                        new Integer(urm.getComp_id().getRoleid()).toString() +
                                        new Integer(relationID).toString() +
                                        new Integer(type).toString() +
                                        new Integer(permitID).toString();

                                for (int m = 0; m < rpm.length; m++)
                                {
                                        for (int n = 0; n < rpm[m].length; n++)
                                        {
                                                if (nowPermission.equals(rpm[m][n]))
                                                {
                                                        //System.out.println("rpm["+m+"]["+n+"]"+rpm[m][n]);
                                                        isPermission = true;

                                                        //break;
                                                }
                                        }
                                }
                        }

                        //System.out.println("nowPermission="+nowPermission);
                }

                return isPermission;
        }

        public List getModuleList(STicket ticket) throws SecurityDAOSysException
        {
                Statement stmt = null;
                String roleIDList = "(";

                if (ticket != null)
                {
                        List roleList = ticket.getRoleList();

                        for (int i = 0; i < roleList.size(); i++)
                        {
                                if (i == 0)
                                {
                                        roleIDList = roleIDList + roleList.get(i).toString();
                                }
                                else
                                {
                                        roleIDList = roleIDList + "," + roleList.get(i).toString();
                                }
                        }

                        roleIDList = roleIDList + " ,0)";
                }

                String sqlStr = "SELECT moduleID FROM Sec_Module_Tab WHERE moduleID IN " +
                        "(SELECT moduleID FROM Sec_RolePerm_Tab " + "WHERE ROLEID IN " +
                        roleIDList + " AND STATUS = 1) ORDER BY moduleID DESC ";
                ResultSet rs = null;
                List moduleList = new ArrayList();
                System.out.println("sqlStr=====================" + sqlStr);

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.debug("system", "[SecurityDAOOracle]=============the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                moduleList.add(new Integer(rs.getInt("moduleID")).toString());
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SecurityDAOSysException(se);

                        //throw new SecurityDAOSysException("SQLException while get moduleList; Serial = " + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return moduleList;
        }

        public static void main(String[] args)
        {
                SecurityDAOImpl s = new SecurityDAOImpl();
        }

        /**
         * Get the roleList according to the sysID
         *
         * @param sysID 0,means get all the roleList,1 means get system role
         *              2,means get organ role, 3 means get course role list
         * @return prepared roleList according to the sysID
         * @throws SecurityDAOSysException
         */
        public List getRoleList(int sysID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "Select * from Sec_Role_Tab where sysID = " + sysID;

                if (sysID == 0)
                {
                        sqlStr = "SELECT * FROM Sec_Role_Tab  WHERE roleID > 1 ORDER BY sysID";
                }

                List roleList = new ArrayList();
                ResultSet rs = null;
                int rsIndex = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.debug("system", "[SecurityDAOOracle]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;

                                RoleForm rf = new RoleForm();
                                rf.setSysID(rs.getInt(rsIndex++));
                                rf.setRoleID(rs.getInt(rsIndex++));
                                rf.setName(rs.getString(rsIndex++));
                                rf.setDescription(rs.getString(rsIndex++));
                                roleList.add(rf);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "account; Serial = " + sysID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return roleList;
        }

        //todo where to use this method?
        public List getUserRoleList(String userID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = " from UserRoleModel as urm where urm.comp_id.userid = " +
                        userID;
                List roleList = new ArrayList();

                try
                {
                        List objects = HibernateDAO.find(sqlStr);

                        if (objects != null)
                        {
                                for (int i = 0; i < objects.size(); i++)
                                {
                                        UserRoleModel urm = new UserRoleModel();
                                        urm = (UserRoleModel) objects.get(i);
                                        roleList.add(new UserRoleForm(urm));
                                }
                        }
                }
                catch (ULMSSysException els)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while getting role Form " + " Serial = " +
                                        userID + " :\n" + els);
                }

                return roleList;
        }

        public int addUserRole(UserRoleForm urf) throws SecurityDAOSysException
        {
                /*String preSql = "select count(*) as userCountinRole from Sec_UserRole_Tab where userID = " + urf.getUserID()
         + " and roleID = " + urf.getRoleID() + " and relationID = " + urf.getRelationID()
         + " and type = " + urf.getType();*/
                String preSql = "select count(*)  from UserRoleModel as urm where urm.comp_id.userid = " +
                        urf.getUserID() + " and urm.comp_id.roleid = " + urf.getRoleID() +
                        " and urm.comp_id.relationid = " + urf.getRelationID() +
                        " and urm.comp_id.type = " + urf.getType();

                try
                {
                        //For certificate user
                        if (urf.getType() == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                List courseList = CertHelper.getCourseListFromCert(new Integer(
                                        urf.getRelationID()).toString(),
                                        LMSConstants.LEARNING_TUTORIAL);

                                if ((courseList != null) && (courseList.size() > 0))
                                {
                                        CourseModel cm = null;
                                        int courseID = 0;

                                        for (int i = 0; i < courseList.size(); i++)
                                        {
                                                cm = (CourseModel) courseList.get(i);
                                                courseID = cm.getCourseID();

                                                try
                                                {
                                                        CourseUserHelper.addCourseUser(courseID,
                                                                SecurityConstants.USER_COURSE_RELATION,
                                                                urf.getUserID(),
                                                                CourseKeys.COURSE_USER_AVAILABLE_STATE,
                                                                urf.getRoleID());
                                                }
                                                catch (Exception ex)
                                                {
                                                        throw new ULMSSysException("Add course failed " +
                                                                ex);
                                                }
                                        }
                                }
                        }

                        int duplicateID = HibernateDAO.count(preSql);

                        if (duplicateID == 0)
                        {
                                HibernateDAO.add(urf.getUserRoleModel());
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new SecurityDAOSysException(
                                "addUserRole(UserRoleForm urf) method" + e);
                }

                return 0;
        }

        public void delUserRole(UserRoleForm urf) throws SecurityDAOSysException
        {
                String sqlStr = " from UserRoleModel as urm where urm.comp_id.userid = " +
                        urf.getUserID() + "urm.comp_id.relationid = " +
                        urf.getRelationID() + " and  urm.comp_id.type = " + urf.getType();

                try
                {
                        HibernateDAO.delete(sqlStr);
                }
                catch (ULMSSysException els)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while deleting userRole " + "account; Serial = " +
                                        urf.getUserID() + " :\n" + els);
                }
        }

        public List getUserRoleList(int userID, int relationID, int type)
                throws SecurityDAOSysException
        {
                List result = new ArrayList();
                Statement stmt = null;
                String sqlStr = "Select * from Sec_UserRole_Tab s1,Sec_Role_Tab s2 where s1.userID = " +
                        userID + " and s1.relationID = " + relationID + " and s1.type = " +
                        type + " and s1.roleID = s2.roleID";
                ResultSet rs = null;
                int rsIndex = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.info("system", "[SecurityDAOImpl]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;

                                UserRoleForm rf = new UserRoleForm();
                                rf.setUserID(rs.getInt(rsIndex++));
                                rf.setRoleID(rs.getInt(rsIndex++));
                                rf.setRelationID(rs.getInt(rsIndex++));
                                rf.setType(rs.getInt(rsIndex++));
                                rf.setRoleName(rs.getString("Name"));
                                result.add(rf);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while getting user role list " +
                                        "account; Serial = " + userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return result;
        }

        public List getUserRoleList(int relationID, int type)
                throws SecurityDAOSysException
        {
                List userRoleList = new ArrayList();
                String sqlStr = "  from UserRoleModel as urm where urm.comp_id.relationid = " +
                        relationID + " and urm.comp_id.type = " + type +
                        "  ORDER BY urm.comp_id.type";

                try
                {
                        List objects = HibernateDAO.find(sqlStr);
                        int roleID = 0;
                        int userID = 0;

                        if (objects != null)
                        {
                                for (int i = 0; i < objects.size(); i++)
                                {
                                        UserRoleModel urm = (UserRoleModel) objects.get(i);
                                        UserRoleForm urf = new UserRoleForm();
                                        userID = urm.getComp_id().getUserid();
                                        roleID = urm.getComp_id().getRoleid();
                                        urf.setUserID(userID);
                                        urf.setRoleID(roleID);
                                        urf.setUserForm(UserHelper.getUser(userID + ""));
                                        urf.setRoleForm(SecurityHelper.getRole(roleID));
                                        userRoleList.add(urf);
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while getting sec userlist " +
                                        "account; Serial = " + relationID + " :\n" + se);
                }

                return userRoleList;
        }

        //get userRoleList by orgID
        public List getUserOrgRoleList(int userID, int relationID)
                throws SecurityDAOSysException
        {
                List userRoleList = new ArrayList();
                String sqlStr = "  from UserRoleModel as urm where urm.comp_id.relationid = " +
                        relationID + " and urm.comp_id.userid = " + userID +
                        "  ORDER BY urm.comp_id.roleid";

                try
                {
                        List objects = HibernateDAO.find(sqlStr);
                        int roleID = 0;

                        if (objects != null)
                        {
                                for (int i = 0; i < objects.size(); i++)
                                {
                                        UserRoleModel urm = (UserRoleModel) objects.get(i);
                                        UserRoleForm urf = new UserRoleForm();
                                        roleID = urm.getComp_id().getRoleid();
                                        urf.setUserID(userID);
                                        urf.setRoleID(roleID);
                                        urf.setUserForm(UserHelper.getUser(userID + ""));
                                        urf.setRoleForm(SecurityHelper.getRole(roleID));
                                        userRoleList.add(urf);
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while getting sec userlist " +
                                        "account; Serial = " + relationID + " :\n" + se);
                }

                return userRoleList;
        }

        public void addRole(RoleForm roleForm) throws SecurityDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;

                String[] permissionList = roleForm.getPermissionList();
                String collectStr = "(";

                try
                {
                        String roleIDs = HibernateDAO.add(roleForm.getRoleModel()).toString();
                        int roleID = 0;
                        roleID = Integer.parseInt(roleIDs);
                        addDefaultPermission(roleForm.getSysID(), roleID);

                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        ////LogUtil.debug("security", "[SecurityDAOOracle==============the SQL String]" + sqlStr);
                        //stmt.execute(sqlStr);
                        if (permissionList != null)
                        {
                                //Get the ID of current role record
                                //Insert the permission info to databse one by one
                                for (int i = 0; i < permissionList.length; i++)
                                {
                                        if (i == 0)
                                        {
                                                collectStr = collectStr + permissionList[i];
                                        }
                                        else
                                        {
                                                collectStr = collectStr + "," + permissionList[i];
                                        }
                                }

                                collectStr = collectStr + ")";

                                String sqlStr = "UPDATE Sec_RolePerm_Tab SET Status = 1 WHERE " +
                                        " roleID = " + roleID + " AND permitID IN " + collectStr;
                                // HibernateDAO.update();
                                //LogUtil.debug("security", "[SecurityDAOOracle==============the SQL String]" + sqlStr);
                                stmt.execute(sqlStr);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "role;" + " Serial=" + roleForm.getName() + ":\n" + se);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new SecurityDAOSysException("addRole(RoleForm rf) method" +
                                e);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public void updateRole(RoleForm roleForm) throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "UPDATE Sec_Role_Tab SET Name = '" +
                        roleForm.getName() + "' , Description = '" +
                        roleForm.getDescription() + "' " + "WHERE roleID = " +
                        roleForm.getRoleID();

                String[] permissionList = roleForm.getPermissionList();
                String collectStr = "(";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.debug("security", "[SecurityDAOOracle==============the SQL String]" + sqlStr);
                        stmt.execute(sqlStr);

                        //HibernateDAO.update(roleForm.getRoleModel());
                        if (permissionList != null)
                        {
                                int roleID = roleForm.getRoleID();

                                for (int i = 0; i < permissionList.length; i++)
                                {
                                        if (i == 0)
                                        {
                                                collectStr = collectStr + permissionList[i];
                                        }
                                        else
                                        {
                                                collectStr = collectStr + "," + permissionList[i];
                                        }
                                }

                                collectStr = collectStr + ")";
                                sqlStr = "UPDATE Sec_RolePerm_Tab SET Status = 0 WHERE  roleID = " +
                                        roleID;
                                stmt.execute(sqlStr);
                                //LogUtil.debug("security", "[UpdateSecurityDAOOracle==============the SQL String]" + sqlStr);
                                sqlStr = "UPDATE Sec_RolePerm_Tab SET Status = 1 WHERE " +
                                        " roleID = " + roleID + " AND permitID IN " + collectStr;
                                stmt.execute(sqlStr);

                                //LogUtil.debug("security", "[UpdateSecurityDAOOracle==============the SQL String]" + sqlStr);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "role;" + " Serial=" + roleForm.getName() + ":\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }

                //                try
                //                {
                //                      HibernateDAO.update(roleForm.getRoleModel());
                //                }
                //                catch (ULMSSysException e)
                //                {
                //                        e.printStackTrace();
                //                        throw new SecurityDAOSysException("addRole(RoleForm rf) method"+e);
                //                }
        }

        public void ConfigModule(String proID, int isAvailable)
                throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sql = "";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        sql = "update Sec_RolePerm_Tab set Status=" + isAvailable +
                                " where permitID in (" + proID + "0," + proID + "1) ";
                        stmt.execute(sql);
                        LogUtil.debug("security",
                                "[ConfigModule==============the SQL String]" + sql);
                        sql = "update Sec_Property_Tab set IsAvailable=" + isAvailable +
                                " where ProID=" + proID;
                        stmt.execute(sql);
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while ConfigModule; Serial = " + proID + " :\n" +
                                        se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public RoleForm getRole(int roleID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                RoleForm rf = null;
                int rsIndex = 0;
                String sqlStr = "Select * from Sec_Role_Tab where roleID = " + roleID;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.info("system", "[SecurityDAOOracle]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                rsIndex = 1;
                                rf = new RoleForm();
                                rf.setSysID(rs.getInt(rsIndex++));
                                rf.setRoleID(rs.getInt(rsIndex++));
                                rf.setName(rs.getString(rsIndex++));
                                rf.setDescription(rs.getString(rsIndex++));
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while getting roleForm; Serial = " + roleID +
                                        " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return rf;
        }

        /**
         * Remove the plan from database by the roleID
         *
         * @param roleID
         * @throws SecurityDAOSysException
         */
        public void removeRole(String roleID) throws SecurityDAOSysException
        {
                String hql = " from RoleModel where roleID=" + roleID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "PlAN;" + " Serial=" + roleID + ":\n" + se);
                }
        }

        /**
         * Remove the plan from database by the userID
         *
         * @param userID
         * @throws SecurityDAOSysException
         */
        public void removeUserRole(String userID) throws SecurityDAOSysException
        {
                String sqlStr = " from  UserRoleModel  where " +
                        " urm.comp_id.userid=" + userID;

                try
                {
                        HibernateDAO.delete(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while removing user role " + "Security;" +
                                        " Serial=" + userID + ":\n" + se);
                }
        }

        /**
         * @param userID
         * @param relationID
         * @param type
         * @throws SecurityDAOSysException
         */
        public void removeUserRole(int userID, int relationID, int type)
                throws SecurityDAOSysException
        {
                String sqlStr = " from  UserRoleModel urm where " +
                        " urm.comp_id.relationid=" + relationID + "" +
                        " and urm.comp_id.type=" + type + "" + " and urm.comp_id.userid=" +
                        userID;

                try
                {
                        HibernateDAO.delete(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "Security;" + " Serial=" + userID + ":\n" + se);
                }
        }

        public void updatePWD(String userID, String passwd)
                throws SecurityDAOSysException
        {
                Statement stmt = null;
                String plainPassword=passwd;
                passwd = MD5Encrypt.encrypt(passwd);
                //并且明码也修改
                String sqlStr = "update U_User_Tab set Password='" + passwd +
                        "', PlainPassword='"+ StringEscapeUtils.escapeSql(plainPassword)+"' where userID=" + userID;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                " Serial=" + userID + ":\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public STicket getTicket(int userID) throws SecurityDAOSysException
        {
                STicket ticket = new STicket();

                try
                {
                        //Set roleList
                        List roleList = getUserRoleID(userID);
                        List per = getUserRolePermission(userID);
                        //String rolePermission[][]= getUserRoleID(userID);
                        ticket.setRoleList(roleList);
                        ticket.setRolePermission(per);
                        //Set the userID
                        ticket.setUserID(userID);

                        //Set the orgID
                        Object user = HibernateDAO.load(UserModel.class, new Integer(userID));
                        int orgID = 1;

                        if (user != null)
                        {
                                orgID = ((UserModel) user).getCatalogid();
                                ticket.setOrgID(orgID);
                                ticket.setUf(new UserForm((UserModel) user));
                        }

                        ticket.setOrgID(orgID);

                        //Set the aspID
                        Object organ = HibernateDAO.load(OrganModel.class,
                                new Integer(orgID));

                        if (organ != null)
                        {
                                int aspID = ((OrganModel) organ).getAspid();
                                ticket.setAspID(aspID);
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                " Serial=" + userID + ":\n" + se);
                }

                return ticket;
        }

        public STicket getRoleList(int userID, int relationID, int type)
                throws SecurityDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                String sqlStr = "select roleID from Sec_UserRole_Tab where " +
                        " relationID=" + relationID + "" + " and type=" + type + " " +
                        " and userID =" + userID;

                STicket ticket = new STicket();
                List roleList = new ArrayList();

                try
                {
                        //Get the rolelist
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.info("system", "[getTicket==============the SQL String]" + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                roleList.add(new Integer(rs.getInt("roleID")));
                        }

                        ticket.setRoleList(roleList);
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                " Serial=" + userID + ":\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return ticket;
        }

        /**
         * @param userID
         * @param relationID
         * @param type
         * @return
         * @throws SecurityDAOSysException
         */
        public List getRoleForm(int userID, int relationID, int type)
                throws SecurityDAOSysException
        {
                String sqlStr = "  from UserRoleModel as urm where urm.comp_id.userid = " +
                        userID + " and urm.comp_id.relationid = " + relationID +
                        " and urm.comp_id.type = " + type;

                List roleList = new ArrayList();

                try
                {
                        //Get the rolelist
                        List objects = HibernateDAO.find(sqlStr);

                        if (objects != null)
                        {
                                for (int i = 0; i < objects.size(); i++)
                                {
                                        UserRoleModel urm = (UserRoleModel) objects.get(i);
                                        int roleID = urm.getComp_id().getRoleid();
                                        RoleForm rf = getRole(roleID);
                                        roleList.add(rf);
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while getting roles " + " Serial=" + userID +
                                        ":\n" + se);
                }

                return roleList;
        }

        public List getPropertyList(String moduleID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "Select * from Sec_Property_Tab where IsAvailable =  1 " +
                        " And moduleID = " + moduleID;
                List proList = new ArrayList();
                ResultSet rs = null;
                PropertyForm pf = null;
                int rsIndex = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;
                                pf = new PropertyForm();
                                pf.setModuleID(rs.getInt(rsIndex++));
                                pf.setProID(rs.getInt(rsIndex++));
                                pf.setIsAvailable(rs.getInt(rsIndex++));
                                pf.setName(rs.getString(rsIndex++));
                                pf.setProNo(rs.getString(rsIndex++));
                                pf.setDescription(rs.getString(rsIndex++));
                                proList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "account; Serial = " + moduleID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return proList;
        }

        public List getAllPropertyList(String moduleID)
                throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "Select * from Sec_Property_Tab where moduleID = " +
                        moduleID;
                List proList = new ArrayList();
                ResultSet rs = null;
                PropertyForm pf = null;
                int rsIndex = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;
                                pf = new PropertyForm();
                                pf.setModuleID(rs.getInt(rsIndex++));
                                pf.setProID(rs.getInt(rsIndex++));
                                pf.setIsAvailable(rs.getInt(rsIndex++));
                                pf.setName(rs.getString(rsIndex++));
                                pf.setProNo(rs.getString(rsIndex++));
                                pf.setDescription(rs.getString(rsIndex++));
                                proList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "account; Serial = " + moduleID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return proList;
        }

        public List getPermissionListByRoleID(int proID, int roleID)
                throws SecurityDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                List roleList = new ArrayList();
                PermissionForm pf = null;
                int rsIndex = 0;
                String sqlStr = "select * from Sec_Permission_Tab p,SEC_ROLEPERM_TAB rp " +
                        " WHERE p.PermitID = rp.PermitID and ProID =" + proID + " " +
                        " and roleID=" + roleID +
                        " AND IsAvailable = 1 order by p.permitid desc";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.info("system", "[SecurityDAOOracle]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;
                                pf = new PermissionForm();
                                pf.setModuleID(rs.getInt(rsIndex++));
                                pf.setProID(rs.getInt(rsIndex++));
                                pf.setPermitID(rs.getInt(rsIndex++));
                                pf.setAvailable(rs.getInt(rsIndex++));
                                pf.setType(rs.getInt(rsIndex++));
                                pf.setDefautCheck(rs.getInt(rsIndex++));
                                pf.setName(rs.getString(rsIndex++));
                                pf.setPermitNO(rs.getString(rsIndex++));
                                pf.setDescription(rs.getString(rsIndex++));
                                pf.setStatus(rs.getInt("Status"));
                                roleList.add(pf);
                        }

                        //LogUtil.info("system", "[SecurityDAOOracle]====================the sql string is : " + roleList.size());
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "propertyID and roleID; Serial = " + proID + "  and  " +
                                roleID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return roleList;
        }

        public List getPermissionList(String sysID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                PermissionForm pf = null;
                String sqlStr = "SELECT * FROM Sec_Permission_Tab p,Sec_Module_Tab m WHERE " +
                        " p.moduleID = m.moduleID AND m.sysID = " + sysID +
                        " AND IsAvailable = 1";
                List proList = new ArrayList();

                int rsIndex = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.info("system", "[SecurityDAOOracle]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;
                                pf = new PermissionForm();
                                pf.setModuleID(rs.getInt(rsIndex++));
                                pf.setProID(rs.getInt(rsIndex++));
                                pf.setPermitID(rs.getInt(rsIndex++));
                                pf.setAvailable(rs.getInt(rsIndex++));
                                pf.setType(rs.getInt(rsIndex++));
                                pf.setDefautCheck(rs.getInt(rsIndex++));
                                pf.setName(rs.getString(rsIndex++));
                                pf.setPermitNO(rs.getString(rsIndex++));
                                pf.setDescription(rs.getString(rsIndex++));
                                proList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while getting permission list; Serial = " +
                                        sysID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return proList;
        }

        public List getPropertyPerm(int PropertyID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                PermissionForm pf = null;
                String sqlStr = "SELECT * FROM Sec_Permission_Tab  WHERE " +
                        " ProID = " + PropertyID +
                        " AND IsAvailable = 1 order by permitid desc";
                List proList = new ArrayList();

                int rsIndex = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.info("system", "[SecurityDAOOracle]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;
                                pf = new PermissionForm();
                                pf.setModuleID(rs.getInt(rsIndex++));
                                pf.setProID(rs.getInt(rsIndex++));
                                pf.setPermitID(rs.getInt(rsIndex++));
                                pf.setAvailable(rs.getInt(rsIndex++));
                                pf.setType(rs.getInt(rsIndex++));
                                pf.setDefautCheck(rs.getInt(rsIndex++));
                                pf.setName(rs.getString(rsIndex++));
                                pf.setPermitNO(rs.getString(rsIndex++));
                                pf.setDescription(rs.getString(rsIndex++));
                                proList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while getting permission list; PropertyID = " +
                                        PropertyID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return proList;
        }

        public List getModuleList(int sysID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                ModuleForm mf = null;
                String sqlStr = "SELECT * FROM Sec_Module_Tab WHERE " + " SysID = " +
                        sysID + " order by moduleID";
                List mfList = new ArrayList();

                int rsIndex = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.info("system", "[SecurityDAOOracle]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;
                                mf = new ModuleForm();
                                mf.setModuleID(rs.getInt(rsIndex++));
                                mf.setName(rs.getString(rsIndex++));
                                mf.setSysID(rs.getInt(rsIndex++));
                                mf.setDescription(rs.getString(rsIndex++));
                                mfList.add(mf);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while getting permission list; Serial = " +
                                        sysID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return mfList;
        }

        public List getAllPermitByRoleID(int roleID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "Select * from Sec_Permission_Tab p,Sec_RolePerm_Tab rp" +
                        " where p.PermitID = rp.PermitID and rp.roleID = " + roleID +
                        " and p.IsAvailable = 1";
                List proList = new ArrayList();
                ResultSet rs = null;
                PermissionForm pf = null;
                int rsIndex = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.debug("system", "[SecurityDAOImpl]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;
                                pf = new PermissionForm();
                                pf.setModuleID(rs.getInt(rsIndex++));
                                pf.setProID(rs.getInt(rsIndex++));
                                pf.setPermitID(rs.getInt(rsIndex++));
                                pf.setAvailable(rs.getInt(rsIndex++));
                                pf.setType(rs.getInt(rsIndex++));
                                pf.setDefautCheck(rs.getInt(rsIndex++));
                                pf.setName(rs.getString(rsIndex++));
                                pf.setPermitNO(rs.getString(rsIndex++));
                                pf.setDescription(rs.getString(rsIndex++));
                                pf.setStatus(rs.getInt("Status"));
                                proList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while getting " +
                                "roleID; Serial = " + roleID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return proList;
        }

        /**
         * 根据角色得到可用的权限
         *
         * @param roleID
         * @return
         * @throws SecurityDAOSysException
         */
        public List getIsUsePermitByRoleID(int roleID)
                throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "Select * from Sec_Permission_Tab p,Sec_RolePerm_Tab rp" +
                        " where p.PermitID = rp.PermitID and rp.roleID = " + roleID +
                        " and p.IsAvailable = 1 and rp.status = 1";
                List proList = new ArrayList();
                ResultSet rs = null;
                PermissionForm pf = null;
                int rsIndex = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        //LogUtil.debug("system", "[SecurityDAOImpl]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                rsIndex = 1;
                                pf = new PermissionForm();
                                pf.setModuleID(rs.getInt(rsIndex++));
                                pf.setProID(rs.getInt(rsIndex++));
                                pf.setPermitID(rs.getInt(rsIndex++));
                                pf.setAvailable(rs.getInt(rsIndex++));
                                pf.setType(rs.getInt(rsIndex++));
                                pf.setDefautCheck(rs.getInt(rsIndex++));
                                pf.setName(rs.getString(rsIndex++));
                                pf.setPermitNO(rs.getString(rsIndex++));
                                pf.setDescription(rs.getString(rsIndex++));
                                pf.setStatus(rs.getInt("Status"));
                                proList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while getting " +
                                "roleID; Serial = " + roleID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return proList;
        }

        public boolean isHasPermission(String userID, String relationID,
                                       String type, String permitID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "SELECT PERMITID FROM Sec_RolePerm_Tab WHERE PERMITID = " +
                        permitID + " AND PERMITID " +
                        " IN (SELECT permitID FROM Sec_RolePerm_Tab WHERE STATUS = 1 AND roleID IN " +
                        " (SELECT roleID FROM Sec_UserRole_Tab WHERE userID = " + userID +
                        " AND relationID = " + relationID + " AND TYPE = " + type + ")) ";
                ResultSet rs = null;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        ////LogUtil.info("system", "[SecurityDAOOracle]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                int perCount = rs.getInt("perCount");

                                if (perCount > 0)
                                {
                                        return true;
                                }
                                else
                                {
                                        return false;
                                }
                        }

                        //LogUtil.debug("security", "[SecurityDAOImpl==============the SQL String]" + sqlStr);
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while checking  " +
                                "security; Serial = " + userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return false;
        }

        public boolean isHasPermission(int userID, int relationID, int type,
                                       int permitID) throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "SELECT COUNT(*) AS perCount FROM Sec_RolePerm_Tab WHERE " +
                        permitID +
                        " IN (SELECT permitID FROM Sec_RolePerm_Tab WHERE STATUS = 1 AND roleID IN " +
                        "(SELECT roleID FROM Sec_UserRole_Tab WHERE userID = " + userID +
                        " AND relationID = " + relationID + " AND TYPE = " + type + ")) ";

                //LogUtil.debug("security", "[SecurityDAOImpl==============the SQL String]" + sqlStr);
                ResultSet rs = null;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        //LogUtil.info("system", "[SecurityDAOOracle]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                int perCount = rs.getInt("perCount");

                                if (perCount > 0)
                                {
                                        return true;
                                }
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while checking  " +
                                "security; Serial = " + userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return false;
        }

        /**
         * @param userID     user's ID
         * @param relationID orgID OR courseID OR defaultPlatFormID
         * @param type       Relation Type:USER_PLATFORM_RELATION   1
         *                   USER_ORGAN_RELATION      2
         *                   USER_COURSE_RELATION     3
         *                   USER_CERTIFICATE_RELATION    4
         *                   USER_PROJECT_RELATION     5
         * @return
         * @throws SecurityDAOSysException
         */
        public boolean isAdmin(int userID, int relationID, int type)
                throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "select b.permitID  from  Sec_Role_Tab a, SEC_ROLEPERM_TAB b, Sec_UserRole_Tab c " +
                        " where a.roleID=b.roleID and b.roleID=c.roleID " +
                        " and c.userID=" + userID + " and c.relationID=" + relationID +
                        "  " + " and c.type=" + type + " and status=1";

                ResultSet rs = null;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        ////LogUtil.info("system", "[SecurityDAOOracle]====================the sql string is : " + sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                String permitID = rs.getString("permitID");

                                if (Integer.parseInt(permitID.substring(3)) == 0)
                                {
                                        return true;
                                }
                        }

                        //LogUtil.debug("security", "[SecurityDAOImpl==============the SQL String]" + sqlStr);
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while checking  " +
                                "security; Serial = " + userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return false;
        }

        /**
         * Judge whether the user has the permition of the organ's parent
         *
         * @param userID
         * @param relationID
         * @param type
         * @param permitID
         */
        public boolean isParentHasPermission(int userID, int relationID, int type,
                                             int permitID) throws SecurityDAOSysException
        {
                //1,get the parentList of this organ
                List parentList = OrganHelper.getOrganParentList(relationID);
                ResultSet rs = null;

                //2,judge wether the user has the permition about the parent organ
                dbConnection = getConnection();

                Statement stmt = null;

                try
                {
                        for (int i = parentList.size() - 1; i >= 0; i--)
                        {
                                int parentID = ((Integer) parentList.get(i)).intValue();
                                String sqlStr = "SELECT COUNT(*) AS perCount FROM Sec_RolePerm_Tab WHERE " +
                                        permitID +
                                        "IN (SELECT permitID FROM Sec_RolePerm_Tab WHERE STATUS = 1 AND roleID IN " +
                                        "(SELECT roleID FROM Sec_UserRole_Tab WHERE userID = " +
                                        userID + " AND relationID = " + parentID + " AND TYPE = " +
                                        type + ")) ";

                                stmt = dbConnection.createStatement();
                                rs = stmt.executeQuery(sqlStr);

                                if (rs.next())
                                {
                                        int perCount = rs.getInt("perCount");

                                        if (perCount > 0)
                                        {
                                                return true;
                                        }
                                }

                                //LogUtil.debug("security", "[SecurityDAOImpl==============the SQL String]" + sqlStr);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while checking  " +
                                "security; Serial = " + userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return false;
        }

        public boolean addDefaultPermission(int sysID, int roleID)
                throws SecurityDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "";
                List permitList = getPermissionList(new Integer(sysID).toString());

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        if ((permitList != null) && (permitList.size() > 0))
                        {
                                for (int i = 0; i < permitList.size(); i++)
                                {
                                        PermissionForm pf = (PermissionForm) permitList.get(i);
                                        sqlStr = "INSERT INTO Sec_RolePerm_Tab VALUES(" +
                                                pf.getModuleID() + "," + roleID + "," +
                                                pf.getPermitID() + "," + i + ",0 , 1, 0 )";
                                        //LogUtil.debug("system", "[addDefaultPermission]===== sqlStr= " + sqlStr);
                                        stmt.execute(sqlStr);
                                }
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SecurityDAOSysException("SQLException while checking  " +
                                "security; Serial = " + roleID + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }

                return false;
        }

        public int getRoleIDByName(String roleName) throws SecurityDAOSysException
        {
                String sqlStr = "SELECT roleID FROM SEC_ROLE_TAB WHERE NAME='" +
                        roleName + "'";
                Statement stmt = null;
                ResultSet rs = null;
                int result = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                result = rs.getInt("roleID");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SecurityDAOSysException(
                                "SQLException while checking orgNmae  " +
                                        "security; Serial = " + roleName + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return result;
        }

        public int getRoleID(String roleName, int type)
                throws SecurityDAOSysException
        {
                // String sqlStr = "SELECT roleID FROM SEC_ROLE_TAB WHERE NAME='" + roleName + "'";
                String sqlStr = "Select distinct a.roleID from SEC_ROLE_TAB a, SEC_userROLE_TAB b where a.roleID=b.roleID and  a.Name='" +
                        roleName + "' and b.type=" + type;
                LogUtil.debug("user",
                        "[SecurityDAOImpl]getRoleID[]====================the sql string is : " +
                                sqlStr);

                Statement stmt = null;
                ResultSet rs = null;
                int result = 0;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                result = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SecurityDAOSysException(
                                "SQLException while checking orgNmae  " +
                                        "security; Serial = " + roleName + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return result;
        }

        public boolean isExisitCourseUserRole(int userID, int roleID,
                                              int relationID, int type) throws SecurityDAOSysException
        {
                boolean isExistUser = false;
                String strSql = "select count(*)  from UserRoleModel as urm where urm.comp_id.userid = " +
                        userID + " and urm.comp_id.roleid = " + roleID +
                        " and urm.comp_id.relationid = " + relationID +
                        " and urm.comp_id.type = " + type;
                LogUtil.debug("user",
                        "[SecurityDAOImpl]getRoleID[]====================the sql string is : " +
                                strSql);

                try
                {
                        int duplicateID = HibernateDAO.count(strSql);

                        if (duplicateID > 0)
                        {
                                isExistUser = true;
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while isExisitCourseUserRole(int userID, int orgID) " +
                                        "account; Serial = " + userID + " :\n" + se);
                }

                return isExistUser;
        }

        /**
         * Judge whether the roleID is used by any other users
         *
         * @param roleID
         * @return true if the roleid is not used, otherwise false
         * @throws SecurityDAOSysException
         */
        public boolean isUnusedRole(int roleID) throws SecurityDAOSysException
        {
                String sqlStr = " select count(*) from UserRoleModel as urm where urm.comp_id.roleid = " +
                        roleID;
                boolean result = true;

                try
                {
                        int count = HibernateDAO.count(sqlStr);

                        if (count > 0)
                        {
                                result = false;
                        }
                }
                catch (ULMSSysException els)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while isUnusedRole " + "user role; Serial = " +
                                        roleID + " :\n" + els);
                }

                return result;
        }

        public List getUserRoleID(int userID) throws SecurityDAOSysException
        {
                String sqlStr = " from UserRoleModel as urm where urm.comp_id.userid = " +
                        userID;
                List roleList = new ArrayList();

                try
                {
                        List objects = HibernateDAO.find(sqlStr);

                        if (objects != null)
                        {
                                for (int i = 0; i < objects.size(); i++)
                                {
                                        UserRoleModel urm = new UserRoleModel();
                                        urm = (UserRoleModel) objects.get(i);
                                        roleList.add(new Integer(urm.getComp_id().getRoleid()));
                                }
                        }
                }
                catch (ULMSSysException els)
                {
                        throw new SecurityDAOSysException("SQLException while getting " +
                                "user role; Serial = " + userID + " :\n" + els);
                }

                return roleList;
        }

        /**
         * 根据用户得到用户对应的角色和角色权限
         *
         * @param userID
         * @return
         * @throws SecurityDAOSysException
         */
        public List getUserRolePermission(int userID)
                throws SecurityDAOSysException
        {
                String sqlStr = " from UserRoleModel as urm where urm.comp_id.userid = " +
                        userID;
                List rolePerList = new ArrayList();

                String[][] rolePermission = null;

                try
                {
                        List objects = HibernateDAO.find(sqlStr);
                        //System.out.println("objects.size="+objects.size());
                        rolePermission = new String[objects.size()][];

                        if (objects != null)
                        {
                                for (int i = 0; i < objects.size(); i++)
                                {
                                        RolePermission rp = new RolePermission();
                                        UserRoleModel urm = (UserRoleModel) objects.get(i);

                                        rp.setUrf(urm);

                                        int userids = urm.getComp_id().getUserid();
                                        int roleID = urm.getComp_id().getRoleid();
                                        int relationID = urm.getComp_id().getRelationid();
                                        int type = urm.getComp_id().getType();

                                        List per = getIsUsePermitByRoleID(roleID);
                                        rolePermission[i] = new String[per.size()];

                                        for (int j = 0; j < per.size(); j++)
                                        {
                                                //System.out.println("per.size="+per.size());
                                                int permitID = ((PermissionForm) per.get(j)).getPermitID();
                                                rolePermission[i][j] = new Integer(userids).toString() +
                                                        new Integer(roleID).toString() +
                                                        new Integer(relationID).toString() +
                                                        new Integer(type).toString() +
                                                        new Integer(permitID).toString(); //new String[new Integer(roleID).toString()][new Integer(permitID).toString()];
                                                rp.setRolePermission(rolePermission);
                                        }

                                        rolePerList.add(rp);
                                }
                        }
                }
                catch (ULMSSysException els)
                {
                        throw new SecurityDAOSysException("SQLException while getting " +
                                "user role; Serial = " + userID + " :\n" + els);
                }

                return rolePerList;
        }

        /*
        * add by keyh
        */
        public void changeOrgUser(OrgUserForm oldOrgUserForm,
                                  OrgUserForm newOrgUserForm) throws SecurityDAOSysException
        {
                String sqlStr = "delete  FROM TM_OrgUser_Tab WHERE OrgID = " +
                        oldOrgUserForm.getOrgID() + " and UserID=" +
                        oldOrgUserForm.getUserID();
                Statement stmt = null;
                ResultSet rs = null;
                boolean result = true;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        stmt.executeUpdate(sqlStr);

                        try
                        {
                                OrganDAO organDao = OrganDAOFactory.getDAO();
                                organDao.addOrganUser(newOrgUserForm);
                        }
                        catch (OrganDAOSysException e)
                        {
                                e.printStackTrace();
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new SecurityDAOSysException(
                                "SQLException while changeOrgUser   " + "security; Serial = " +
                                        oldOrgUserForm.getOrgID() + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public void changeUserRole(int userID, int oldRelationID, int type,
                                   int newRelationID, String[] orgRoleID) throws SecurityDAOSysException
        {
                removeUserRole(userID, oldRelationID, type);

                if ((orgRoleID != null) && (orgRoleID.length > 0))
                {
                        for (int i = 0; i < orgRoleID.length; i++)
                        {
                                UserRoleForm urf = new UserRoleForm();
                                urf.setRoleID(Integer.parseInt(orgRoleID[i]));
                                urf.setUserID(userID);
                                urf.setRelationID(newRelationID);
                                urf.setType(type);
                                addUserRole(urf);
                        }
                }
        }

        /**
         * Get userList by roleID
         *
         * @param roleID     roleID
         * @param relationID relationID
         * @param type       type
         * @return the prepated user list ,defult is an arraylist whose size is 0
         * @throws SecurityDAOSysException
         */
        public List getUserListbyRole(int roleID, int relationID, int type)
                throws SecurityDAOSysException
        {
                Statement stmt = null;

                //todo the relationdID and type is prepared for the future requirement
                String sqlStr = "Select distinct userID from Sec_UserRole_Tab where roleID = " +
                        roleID;
                List userList = new ArrayList();
                ResultSet rs = null;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                userList.add(new Integer(rs.getInt("userID")));
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while get userList " + "roleID; Serial = " +
                                        roleID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return userList;
        }

    /**
     * Gets roleID from database from database according to userID relationID and type
     * @param userID
     * @param relationID
     * @param type
     * @return
     * @throws SecurityDAOSysException
     */
        public int getRoleID(int userID, int relationID, int type)
                throws SecurityDAOSysException
        {
                int roleID = 0;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                String sqlStr = "Select roleID from Sec_UserRole_Tab where userID = ?" +
                        " and type = ?";
                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.prepareStatement(sqlStr);
                        stmt.setInt(1,userID);
                        stmt.setInt(2,type);
                        rs = stmt.executeQuery();

                        while (rs.next())
                        {
                                roleID = rs.getInt("roleID");
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException(
                                "SQLException while get roleID " + "roleID; Serial userID = " +
                                        userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return roleID;
        }


        protected void closeConnection() throws SecurityDAOSysException
        {
                try
                {
                        if ((dbConnection != null) && !dbConnection.isClosed())
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
                }
        }

        protected void closeResultSet(ResultSet result)
                throws SecurityDAOSysException
        {
                try
                {
                        if (result != null)
                        {
                                result.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        protected void closeStatement(Statement stmt)
                throws SecurityDAOSysException
        {
                try
                {
                        if (stmt != null)
                        {
                                stmt.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        protected Connection getConnection() throws SecurityDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new SecurityDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se, se);
                }

                return dbConnection;
        }
}
