/**
 * Created by IntelliJ IDEA.
 * Security: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.core.security.dao;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.exceptions.SecurityDAOSysException;
import com.ulearning.ulms.core.security.form.RoleForm;
import com.ulearning.ulms.core.security.form.UserRoleForm;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;


public class SecurityDAOOracle extends SecurityDAOImpl
{
        /**
         * Assign the user with the appointed role
         *
         * @param urf
         * @return
         * @throws SecurityDAOSysException
         */
        public int addUserRole(UserRoleForm urf) throws SecurityDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;

                //ResultSet rs1 = null;
                int userCountinRole = 0;
                String preSql = "select count(*) as userCountinRole from Sec_UserRole_Tab where userID = " +
                        urf.getUserID() + " and roleID = " + urf.getRoleID() +
                        " and relationID = " + urf.getRelationID() + " and type = " +
                        urf.getType();
                String sqlStr = "insert into Sec_UserRole_Tab values(" +
                        urf.getUserID() + "," + urf.getRoleID() + "," +
                        urf.getRelationID() + "," + urf.getType() + ")";

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
                                                }
                                        }
                                }
                        }

                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(preSql);

                        if (rs.next())
                        {
                                userCountinRole = rs.getInt("userCountinRole");
                                LogUtil.debug("system",
                                        "[SecurityDAOOracle]====================the userCountinRole is : " +
                                                userCountinRole);

                                if (userCountinRole == 0)
                                {
                                        stmt.executeQuery(sqlStr);
                                        LogUtil.debug("system",
                                                "[SecurityDAOOracle]====================the sql string is : " +
                                                        sqlStr);
                                }
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "account; Serial = " + urf.getUserID() + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return 0;
        }

        /**
         * Assign the user with the appointed role
         *
         * @param roleForm
         * @throws SecurityDAOSysException
         */
        public void addRole(RoleForm roleForm) throws SecurityDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                String sqlStr = "insert into Sec_Role_Tab values(" +
                        roleForm.getSysID() + "," + "RoleID.nextval,'" +
                        roleForm.getName() + "','" + roleForm.getDescription() + "')";

                String[] permissionList = roleForm.getPermissionList();
                String collectStr = "(";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("security",
                                "[SecurityDAOOracle==============the SQL String]" + sqlStr);
                        stmt.execute(sqlStr);

                        if (permissionList != null)
                        {
                                //Get the ID of current role record
                                String isql = "SELECT RoleID.currval  as RoleID FROM DUAL";
                                int roleID = 0;
                                stmt.execute(isql);
                                rs = stmt.getResultSet();

                                if (rs.next())
                                {
                                        roleID = rs.getInt("RoleID");
                                }

                                //Insert the permission info to databse one by one
                                addDefaultPermission(roleForm.getSysID(), roleID);

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
                                sqlStr = "UPDATE Sec_RolePerm_Tab SET Status = 1 WHERE " +
                                        " roleID = " + roleID + " AND permitID IN " + collectStr;
                                LogUtil.debug("security",
                                        "[SecurityDAOOracle==============the SQL String]" + sqlStr);
                                stmt.execute(sqlStr);
                        }
                }
                catch (SQLException se)
                {
                        throw new SecurityDAOSysException("SQLException while updating " +
                                "role;" + " Serial=" + roleForm.getName() + ":\n" + se);
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
                        LogUtil.debug("security",
                                "[SecurityDAOOracle==============the SQL String]" + sqlStr);
                        stmt.execute(sqlStr);

                        if (permissionList != null)
                        {
                                //Get the ID of current role record
                                //String isql = "SELECT RoleID.currval  as RoleID FROM DUAL";
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
                                LogUtil.debug("security",
                                        "[UpdateSecurityDAOOracle==============the SQL String]" +
                                                sqlStr);

                                sqlStr = "UPDATE Sec_RolePerm_Tab SET Status = 1 WHERE " +
                                        " roleID = " + roleID + " AND permitID IN " + collectStr;
                                stmt.execute(sqlStr);
                                LogUtil.debug("security",
                                        "[UpdateSecurityDAOOracle==============the SQL String]" +
                                                sqlStr);
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
        }
}
