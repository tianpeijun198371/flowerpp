package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.CourseRoleListModel;
import com.ulearning.ulms.course.model.CourseUserListModel;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: ff
 * Date: 2004-4-29
 * Time: 0:44:50
 * To change this template use File | Settings | File Templates.
 */
public class CourseUserDAOOracle extends CourseUserDAOImpl
{
        public CourseUserDAOOracle()
        {
        }

        public CourseUserListModel getCourseAllUsers(int relationID, int type,
                                                     int pageNo, int pageSize) throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                Date joinTime;
                Date applyTime;
                Date finishedTime;
                int userID;
                String trueName;
                String loginName;
                String sql_str = "";
                int courseUser_Status;
                ArrayList al_CourseUsers = new ArrayList();
                int userIDcount = 0;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        sql_str = "select DISTINCT  UU.UserID,UU.Name UserName,UU.LoginName,CU.ApplyTime CourseUser_ApplyTime," +
                                " CU.FinishedTime CourseUser_FinishedTime,CU.JoinTime CourseUser_JoinTime,CU.State CourseUser_Status " +
                                " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU " +
                                "  WHERE CU.userID = UU.userID and  CU.TYPE = " + type +
                                "  and CU.relationID = " + relationID +
                                " and CU.userID = SU.userID " + "  and CU.type = SU.type" +
                                /*                                " and  UU.UserID not in(select DISTINCT UU.UserID" +
                          " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU " +
                          "  WHERE CU.userID = UU.userID and  CU.TYPE = " + type +
                          "  and CU.relationID = " + relationID +
                          " and CU.userID = SU.userID " +
                          "  and CU.type = SU.type "+
                          " and rownum < "+pageSize * pageNo+")" +
                          " and  rownum < "+pageSize +*/
                                " order by  UU.UserID";

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseAllUsers1======================##sqlstr:" +
                                        sql_str);
                        rs = stmt.executeQuery(sql_str);

                        ArrayList al_Roles = null;
                        List tempUserList = new ArrayList();

                        while (rs.next())
                        {
                                userIDcount++;
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl] Circle===============1*****************************");

                                //ArrayList必须在这里声明
                                al_Roles = new ArrayList();
                                userID = rs.getInt(1);

                                //Jusdge whether the userID is duplicate
                                if (!OrganHelper.isDuplicate(tempUserList, userID))
                                {
                                        tempUserList.add(new Integer(userID));

                                        ///Find userName	......
                                        trueName = rs.getString("UserName");
                                        loginName = rs.getString("LoginName");
                                        applyTime = DateTimeUtil.toDate(rs.getDate(
                                                "CourseUser_ApplyTime"),
                                                rs.getTime("CourseUser_ApplyTime"));
                                        joinTime = DateTimeUtil.toDate(rs.getDate(
                                                "CourseUser_joinTime"),
                                                rs.getTime("CourseUser_joinTime"));
                                        finishedTime = DateTimeUtil.toDate(rs.getDate(
                                                "CourseUser_finishedTime"),
                                                rs.getTime("CourseUser_finishedTime"));

                                        courseUser_Status = rs.getInt("courseUser_Status");

                                        // form value object - CourseRoleListModel
                                        CourseRoleListModel crlm = getUserRoles(userID, relationID,
                                                type, conn);

                                        // form value object - CourseUserModel
                                        CourseUserModel cudm = new CourseUserModel(relationID,
                                                type, userID, courseUser_Status, trueName,
                                                loginName, "", crlm, joinTime, applyTime,
                                                finishedTime);
                                        DebugUtil.print(
                                                "[CourseUserDAOImpl] userIDcount=============== add cudm");
                                        al_CourseUsers.add(cudm);
                                }
                        }

                        DebugUtil.print("course" +
                                "[CourseUserDAOImpl] userIDcount===============" + userIDcount);

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                pageNo, pageSize, 0);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseAllUsers======================Sucess in Dao: CourseUserDAO - getCourseUsers");

                        return culm;
                }
                catch (Exception se)
                {
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseAllUsers======================Exception:" +
                                        se.getMessage());
                        System.out.println(
                                "[CourseUserDAOImpl] getCourseAllUsers======================Exception:" +
                                        se.getMessage());
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }
}
