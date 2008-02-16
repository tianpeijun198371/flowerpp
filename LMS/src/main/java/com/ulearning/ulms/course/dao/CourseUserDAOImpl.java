/**
 * CourseUserDAOImpl.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.exceptions.UserExistedInCourseException;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.evaluate.helper.EvaluateManageHelper;
import com.ulearning.ulms.evaluate.model.ERecordPointConversionModel;
import com.ulearning.ulms.finance.helper.UserAccountHelper;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.tools.access.model.UserAccess;
import com.ulearning.ulms.tools.access.webimpls.AccessWebImpl;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.model.UserModel;
import com.ulearning.ulms.util.*;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;


public class CourseUserDAOImpl implements CourseUserDAO
{
        /**
         * add JieFoTrainClerk
         *
         * @param form
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void addJieFoTrainClerk(JieFoTrainClerkForm form)
                throws CourseSysException, CourseAppException
        {
                Connection conn = null;
                Statement stmt = null;
                String sqlStr = "insert into train_clerk(TrainID,clerkID,trainClerkType) values(" +
                        form.getTrainID() + "," + form.getClerkID() + "," +
                        form.getTrainClerkType() + ")";
                LogUtil.debug("course", "[CourseDAOImpl] " + sqlStr);

                try
                {
                        conn = getJieFoConnection();
                        stmt = conn.createStatement();
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoTrainClerk(JieFoTrainClerkForm form) " +
                                        "JieFoTrainClerk; Serial = " + form.getTrainID() + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoTrainClerk(JieFoTrainClerkForm form) " +
                                        "JieFoTrainClerk; Serial = " + form.getTrainID() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }

        /**
         * update JieFoTrainClerk
         *
         * @param form
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void updateJieFoTrainClerk(JieFoTrainClerkForm form)
                throws CourseSysException, CourseAppException
        {
                Connection conn = null;
                Statement stmt = null;
                String sqlStr = "update train_clerk set" + " trainClerkType =" +
                        form.getTrainClerkType() + "" + " where TrainID  =" +
                        form.getTrainID() + "" + " and clerkID =" + form.getClerkID();
                LogUtil.debug("course", "[CourseDAOImpl] " + sqlStr);

                try
                {
                        conn = getJieFoConnection();
                        stmt = conn.createStatement();
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoTrainClerk(JieFoTrainClerkForm form) " +
                                        "JieFoTrainClerk; Serial = " + form.getTrainID() + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoTrainClerk(JieFoTrainClerkForm form) " +
                                        "JieFoTrainClerk; Serial = " + form.getTrainID() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }

        /**
         * delete JieforTrainClerk
         *
         * @param courseID
         * @param userIDs
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void deleteJieFoTrainClerk(int courseID, String[] userIDs)
                throws CourseSysException, CourseAppException
        {
                Connection conn = null;
                Statement stmt = null;
                String sqlStr;

                try
                {
                        conn = getJieFoConnection();
                        stmt = conn.createStatement();

                        for (int i = 0; i < userIDs.length; i++)
                        {
                                //trainID = Integer.parseInt((String) courseIDs.get(i));
                                sqlStr = "delete from train_clerk where TrainID=" + courseID +
                                        "" + " and clerkID=" + userIDs[i];
                                LogUtil.debug("course", "[CourseUserDAOImpl] " + sqlStr);
                                stmt.execute(sqlStr);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoTrainClerk(JieFoTrainClerkForm form) " +
                                        "JieFoTrainClerk; Serial = " + userIDs + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoTrainClerk(JieFoTrainClerkForm form) " +
                                        "JieFoTrainClerk; Serial = " + userIDs + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }

        /**
         * delete JieforTrainClerk
         *
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void deleteJieFoTrainByClerk(String userID)
                throws CourseSysException, CourseAppException
        {
                Connection conn = null;
                Statement stmt = null;
                String sqlStr;

                try
                {
                        if (Config.getIsIntegrateJieFo())
                        {
                                conn = getJieFoConnection();
                                stmt = conn.createStatement();

                                //trainID = Integer.parseInt((String) courseIDs.get(i));
                                sqlStr = "delete from train_clerk where clerkID=" + userID;
                                LogUtil.debug("course", "[CourseUserDAOImpl] " + sqlStr);
                                stmt.execute(sqlStr);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }

        /**
         * @param cum
         * @throws CourseSysException
         * @throws CourseAppException
         * @ modifier: zengej
         * @ date: 04/08/02
         * @ description: modified struture of table of c_user_tab ,so modify this operation;
         */
        public void addCourseUser(CourseUserModel cum)
                throws CourseSysException, CourseAppException
        {
                String hql;
                CourseRoleModel crm = null;
                int userID = cum.getUserID();
                int relationID = cum.getRelationID();
                int type = cum.getType();
                CourseRoleListModel crlm = cum.getCourseRoles();
                List roles = crlm.getRoles();
                int roleID;

                try
                {
                        CourseUserPK cupk = new CourseUserPK(userID, relationID, type);

                        hql = " from CourseUser where userID = " + userID +
                                " and relationID = " + relationID + " and type = " + type;

                        List tmList = HibernateDAO.find(hql);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]addCourseUser======================hql=" +
                                        hql);

                        if ((tmList == null) || (tmList.size() <= 0))
                        {
                                HibernateDAO.add(cum.getCourseUser());

                                hql = " from SecUserRoleModel where userID=" + userID +
                                        " and relationID = " + relationID + " and type = " + type;

                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]addCourseUser======================hql=" +
                                                hql);
                                HibernateDAO.delete(hql);

                                SecUserRolePK surpk = new SecUserRolePK(userID, 0, relationID,
                                        type);
                                SecUserRoleModel surm = new SecUserRoleModel(surpk);

                                //add roles
                                for (int i = 0; i < roles.size(); i++)
                                {
                                        crm = (CourseRoleModel) roles.get(i);
                                        roleID = crm.getRoleID();
                                        surpk.setRoleID(roleID);
                                        HibernateDAO.add(surm);
                                }

                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]addCourseUser======================Sucess in Dao: CourseUserDAO - addCourseUserr");
                        }
                        else
                        {
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]addCourseUser ====================== 用户存在此课程中!continue!");

                                //throw new UserExistedInCourseException("用户已存在此课程中!");
                                //add roles
                                SecUserRolePK surpk = new SecUserRolePK(userID, 0, relationID,
                                        type);
                                SecUserRoleModel surm = new SecUserRoleModel(surpk);

                                for (int i = 0; i < roles.size(); i++)
                                {
                                        crm = (CourseRoleModel) roles.get(i);
                                        roleID = crm.getRoleID();
                                        surpk.setRoleID(roleID);
                                        HibernateDAO.add(surm);

                                        try
                                        {
                                                //insert into Sec_UserRole_Tab
                                                //HibernateDAO.add(surm);
                                        }
                                        catch (Exception ex)
                                        {
                                                LogUtil.debug("course",
                                                        "[CourseUserDAOImpl]addCourseUser ====================== 用户角色已存在!continue！");
                                        }
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new CourseSysException(
                                "SQLException while create courseuser=====" + se);
                }
        }

        public void addCertUser(CourseUserModel cum)
        {
                CourseUser cu = cum.getCourseUser();
                HibernateDAO.add(cu);
        }

        public boolean isaddCertUser(int userID, int relationID, int type)
                throws CourseSysException, CourseAppException
        {
                boolean isboolean = false;
                StringBuffer sql = new StringBuffer();
                sql.append("select * from C_User_Tab where userid=");
                sql.append(userID);
                sql.append(" and Relationid=");
                sql.append(relationID);
                sql.append(" and type=");
                sql.append(type);

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql.toString());

                        while (rs.next())
                        {
                                isboolean = true;
                        }
                }
                catch (SQLException e)
                {
                        throw new CourseSysException("SQLException while insert certeuser" +
                                e);
                }
                finally
                {
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return isboolean;
        }

        public void addCourseUser(int relationID, int type, int userID, int state,
                                  int roleID) throws CourseSysException, CourseAppException
        {
                String hql;
                CourseUserPK cupk = new CourseUserPK(userID, relationID, type);
                CourseUser cu = new CourseUser(cupk);
                cu.setJoinTime(new Date());
                cu.setState(String.valueOf(state));
                cu.setApplyTime(new Date());

                try
                {
                        //判断用户是否存在于课程表
                        System.out.println("query ==========start ");
                        hql = " from  CourseUser where userID=" + userID +
                                " and relationID = " + relationID + " and type =  " + type;
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]addCourseUser======================hql=" +
                                        hql);

                        List tmList = HibernateDAO.find(hql);

                        System.out.println("query ==========end ");

                        System.out.println("========================= ");
                        System.out.println("insert ==========start ");

                        if ((tmList == null) || tmList.isEmpty() || (tmList.size() == 0))
                        {
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]c_user_tab ===================insert c_user_tab start");
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]c_user_tab ===================insert c_user_tab userID =!" +
                                                userID);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]c_user_tab ===================insert c_user_tab relationID!" +
                                                relationID);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]c_user_tab ===================insert c_user_tab type!" +
                                                type);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]c_user_tab ===================insert c_user_tab start!" +
                                                state);

                                HibernateDAO.add(cu);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]c_user_tab ===================insert c_user_tab end");
                        }

                        System.out.println("insert ==========end ");

                        /*else
                        {
                                        LogUtil.debug("course", "[CourseUserDAOImpl]addCourseUser ====================== 用户存在此课程中!continue!");
                                        throw new UserExistedInCourseException("用户存在此课程中!");
                        }*/
                        try
                        {
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]Sec_UserRole_Tab ===================insert Sec_UserRole_Tab start!");
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]userID ===================insert userID !" +
                                                userID);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]roleID ===================insert userID !" +
                                                roleID);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]relationID ===================insert userID !" +
                                                relationID);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]type ===================insert userID !" +
                                                type);

                                //insert into Sec_UserRole_Tab
                                SecUserRolePK surpk = new SecUserRolePK(userID, roleID,
                                        relationID, type);
                                SecUserRoleModel surm = new SecUserRoleModel(surpk);

                                HibernateDAO.add(surm);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]Sec_UserRole_Tab ===================insert Sec_UserRole_Tab end!");
                        }
                        catch (Exception ex)
                        {
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]addCourseUser ====================== 用户角色已存在!continue！");

                                //ex.printStackTrace();
                        }

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]addCourseUser======================Sucess in Dao: 添加用户成功！");
                }
                catch (ULMSSysException se)
                {
                        throw new CourseSysException("SQLException while create courseuser" +
                                se);
                }
        }

        /**
         * @param relationID
         * @param type
         * @param userID
         * @param roleID
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void updateCourseUser(int relationID, int type, int userID,
                                     int roleID) throws CourseSysException, CourseAppException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                String hql;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //判断用户是否为创建者
                        /* if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                        rs = stmt.executeQuery("select creator from c_course_tab where courseID = " +
                                                        relationID);
                                        if (rs.next())
                                        {
                                                        if (rs.getInt("creator") == userID)
                                                        {
                                                                        throw new InvalidateToCourseCreatorException("课程创建者的角色不能被修改!");
                                                        }
                                        }
                        }*/

                        //判断用户是否存在于课程表
                        hql = " from CourseUser where userID=" + userID +
                                " and relationID = " + relationID + " and type = " + type;

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]updateCourseUser======================hql = " +
                                        hql);

                        List tmList = HibernateDAO.find(hql);

                        if ((tmList.size() <= 0) || (tmList == null))
                        {
                                // insert into C_User_Tab
                                CourseUserPK cupk = new CourseUserPK(userID, relationID, type);
                                CourseUser cu = new CourseUser(cupk);
                                cu.setJoinTime(new Date());
                                cu.setState(String.valueOf(
                                        CourseKeys.COURSE_USER_AVAILABLE_STATE));
                                cu.setApplyTime(new Date());
                                HibernateDAO.add(cu);
                        }
                        else
                        {
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]updateCourseUser ====================== 用户存在此课程中!continue!");

                                // throw new UserExistedInCourseException("error:can' user has Existed in this course!");
                        }

                        //delete from Sec_UserRole_Tab
                        hql = " from SecUserRoleModel where userID=" + userID +
                                " and relationID = " + relationID + " and type = " + type;
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]updateCourseUser======================hql=" +
                                        hql);
                        HibernateDAO.delete(hql);

                        try
                        {
                                SecUserRolePK surpk = new SecUserRolePK(userID, roleID,
                                        relationID, type);
                                SecUserRoleModel surm = new SecUserRoleModel(surpk);
                                HibernateDAO.add(surm);
                        }
                        catch (Exception ex)
                        {
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]updateCourseUser ====================== 用户角色已存在!continue！");
                                ex.printStackTrace();
                        }

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]updateCourseUser======================Sucess in Dao: CourseUserDAO - updateCourseUser");
                }
                catch (ULMSSysException se)
                {
                        throw new CourseSysException("SQLException while create courseuser" +
                                se);
                }
                catch (SQLException se)
                {
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

        /**
         * @param cum
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void updateCourseUser(CourseUserModel cum)
                throws CourseSysException, CourseAppException
        {
                String hql;

                CourseRoleModel crm = null;

                int state = cum.getState();
                int userID = cum.getUserID();
                int relationID = cum.getRelationID();
                int type = cum.getType();

                CourseRoleListModel crlm = cum.getCourseRoles();
                List roles = crlm.getRoles();
                int roleID;

                try
                {
                        //判断用户是否为创建者
                        /*if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                        rs = stmt.executeQuery("select creator from c_course_tab where courseID=" +
                                                        relationID);
                                        if ((rs != null) && rs.next())
                                        {
                                                        if (rs.getInt("creator") == userID)
                                                        {
                                                                        throw new InvalidateToCourseCreatorException("课程创建者的角色不能被修改!");
                                                        }
                                        }
                        }*/

                        //判断用户是否存在于课程表
                        hql = " from CourseUser where userID=" + userID +
                                " and relationID = " + relationID + " and type = " + type;

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]updateCourseUser======================hql=" +
                                        hql);

                        List tmList = HibernateDAO.find(hql);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                CourseUser cu = cum.getCourseUser();
                                HibernateDAO.update(cu);
                        }
                        else
                        {
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]updateCourseUser ====================== 用户存在此课程中!continue!");
                                throw new UserExistedInCourseException("用户不存在此课程中!");
                        }

                        //delete from Sec_UserRole_Tab
                        hql = " from SecUserRoleModel where userID=" + userID +
                                " and relationID = " + relationID + " and type=" + type;

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]updateCourseUser======================hql=" +
                                        hql);
                        HibernateDAO.delete(hql);

                        SecUserRolePK surpk = new SecUserRolePK(userID, 0, relationID, type);
                        SecUserRoleModel surm = new SecUserRoleModel(surpk);

                        //add roles
                        for (int i = 0; i < roles.size(); i++)
                        {
                                crm = (CourseRoleModel) roles.get(i);
                                roleID = crm.getRoleID();
                                surpk.setRoleID(roleID);
                                HibernateDAO.add(surm);
                        }

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]updateCourseUser======================Sucess in Dao: CourseUserDAO - updateCourseUserr");
                }
                catch (ULMSSysException se)
                {
                        throw new CourseSysException("SQLException while create courseuser" +
                                se);
                }
        }

        /**
         * @param userID
         * @param relationID
         * @param type
         * @param finishedTime
         * @param state
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void updateCourseUser(int userID, int relationID, int type,
                                     Date finishedTime, int state)
                throws CourseSysException, CourseAppException
        {
                CourseUserModel cum = getCourseUser(relationID, type, userID);

                if ((finishedTime != null) && (cum != null))
                {
                        cum.setFinishedTime(finishedTime);
                }

                if ((state != -1) && (cum != null))
                {
                        cum.setState(state);
                }

                CourseUser cu = cum.getCourseUser();

                try
                {
                        HibernateDAO.update(cu);
                }
                catch (ULMSSysException se)
                {
                        throw new CourseSysException("SQLException while create courseuser" +
                                se);
                }
        }

        /**
         * @param userID
         * @param relationID
         * @param type
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void deleteCourseUser(int userID, int relationID, int type)
                throws CourseSysException, CourseAppException
        {
                //int creator;
                String hql;

                try
                {
                        /* conn = DBUtil.getConnection();
                   stmt = conn.createStatement();
                   //判断用户是否为创建者
                        if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                        rs = stmt.executeQuery("select creator from c_course_tab where courseID =" +
                                                        relationID);
                                        if ((rs != null) && rs.next())
                                        {
                                                        if (rs.getInt("creator") == userID)
                                                        {
                                                                        throw new InvalidateToCourseCreatorException("课程创建者的角色不能被删除!");
                                                        }
                                        }
                        }*/

                        //delete from Sec_UserRole_Tab
                        hql = " from SecUserRoleModel where relationID = " + relationID;

                        if (userID != 0)
                        {
                                hql += ("and userID=" + userID);
                        }

                        if (type != -1)
                        {
                                hql += ("and type=" + type);
                        }

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]deleteCourseUsers======================hql=" +
                                        hql);
                        HibernateDAO.delete(hql);

                        hql = " from CourseUser where relationID = " + relationID;

                        if (userID != 0)
                        {
                                hql += ("and userID=" + userID);
                        }

                        if (type != -1)
                        {
                                hql += ("and type=" + type);
                        }

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]deleteCourseUser======================hql=" +
                                        hql);
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException se)
                {
                        throw new CourseSysException(se);
                }
        }

        public void deleteByUserID(int userID)
                throws CourseSysException, CourseAppException
        {
                //int creator;
                String hql;

                try
                {
                        //delete from Sec_UserRole_Tab
                        hql = " from SecUserRoleModel where userID=" + userID +
                                "  and (type =" + SecurityConstants.USER_COURSE_RELATION +
                                " or " + "type =" +
                                SecurityConstants.USER_CERTIFICATE_RELATION + ")";

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]deleteByUserID======================hql=" +
                                        hql);
                        HibernateDAO.delete(hql);

                        hql = " from CourseUser where userID=" + userID + "  and (type =" +
                                SecurityConstants.USER_COURSE_RELATION + " or " + "type =" +
                                SecurityConstants.USER_CERTIFICATE_RELATION + ")";

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]deleteByUserID======================hql=" +
                                        hql);

                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException se)
                {
                        throw new CourseSysException(se);
                }
        }

        /**
         * @param userID
         * @param relationID
         * @param type
         * @param roleID
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void deleteCourseUserRole(int userID, int relationID, int type,
                                         int roleID) throws CourseSysException, CourseAppException
        {
                //int creator;
                String hql;

                try
                {
                        //delete from Sec_UserRole_Tab
                        hql = " from SecUserRoleModel where userID=" + userID +
                                " and relationID = " + relationID + " and roleID = " + roleID +
                                " and type = " + type;

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]deleteCourseUsers======================hql=" +
                                        hql);
                        HibernateDAO.delete(hql);

                        hql = " from  SecUserRoleModel where userID=" + userID +
                                " and relationID = " + relationID + " and type = " + type;

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]deleteCourseUsers======================hql=" +
                                        hql);

                        List tmList = HibernateDAO.find(hql);

                        if ((tmList == null) || (tmList.size() <= 0)) //用户在此课程已无角色，则删除此用户
                        {
                                hql = " from CourseUser where userID=" + userID +
                                        " and relationID =" + relationID + " and type = " + type;
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]deleteCourseUser======================hql=" +
                                                hql);
                                HibernateDAO.delete(hql);
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new CourseSysException("SQLException while create courseuser" +
                                se);
                }
        }

        /**
         * @param userIDs
         * @param relationID
         * @param type
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void deleteCourseUsers(ArrayList userIDs, int relationID, int type)
                throws CourseSysException, CourseAppException
        {
                int userID;

                if (!userIDs.isEmpty())
                {
                        int size = userIDs.size();

                        for (int i = 0; i < size; i++)
                        {
                                userID = ((Integer) (userIDs.get(i))).intValue();
                                deleteCourseUser(userID, relationID, type);
                        }
                }
        }

        public CourseRoleListModel getUserRoles(int userID, int relationID,
                                                int type, Connection conn) throws CourseSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                String sqlStr = null;

                int courseRoleID;
                String courseRoleName;
                ArrayList al = new ArrayList();
                CourseRoleListModel crlm = new CourseRoleListModel();

                // form value object - CourseRoleListModel
                crlm.setUserID(userID);
                crlm.setRoles(al);

                boolean needClose = false;

                try
                {
                        if (conn == null)
                        {
                                conn = DBUtil.getConnection();
                                needClose = true;
                        }

                        stmt = conn.createStatement();

                        sqlStr = "select Sec_UserRole_Tab.RoleID,Name from Sec_UserRole_Tab,Sec_Role_Tab where " +
                                "Sec_UserRole_Tab.RoleID=Sec_Role_Tab.RoleID and " + "userID=" +
                                userID + " and relationID = " + relationID + " and type = " +
                                type;

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]getUserRoles======================sqlStr=" +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                courseRoleID = rs.getInt("RoleID");

                                courseRoleName = rs.getString("Name");
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl]getUserRoles======================" +
                                                "courseRoleID:" + courseRoleID + " && Name:" +
                                                courseRoleName);

                                CourseRoleModel crm = new CourseRoleModel(courseRoleID,
                                        courseRoleName);

                                al.add(crm);
                        }

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]getUserRoles======================Sucess in Dao: CourseUserDAO - getUserRoles");

                        return crlm;
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);

                        if (needClose)
                        {
                                DBUtil.closeConnection(conn);
                        }
                }
        }

        public void confirmApply(List userIDs, List states, int relationID, int type)
                throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                int userID;
                int state;

                String sql = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        if (!userIDs.isEmpty())
                        {
                                int size = userIDs.size();

                                for (int i = 0; i < size; i++)
                                {
                                        userID = ((Integer) (userIDs.get(i))).intValue();
                                        state = ((Integer) (states.get(i))).intValue();
                                        sql = "update C_User_Tab set state='" + state + "'" +
                                                "where userID=" + userID + " and relationID=" +
                                                relationID + " and type=" + type;
                                        LogUtil.info("course",
                                                "[CourseUserImpl]confirmApply---sql=" + sql);
                                        stmt.executeUpdate(sql);
                                }
                        }
                }
                catch (SQLException se)
                {
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

        public int countAllCourseUsers(int relationID, int type)
                throws CourseSysException
        {
                Session session = null;
                String hql = "select  count(*) from CourseUser CU,UserModel UU,SecUserRoleModel SU " +
                        "  WHERE CU.comp_id.userID = UU.userid and  CU.comp_id.type = " +
                        type + "  and SU.comp_id.relationID=CU.comp_id.relationID" +
                        "  and CU.comp_id.relationID = " + relationID +
                        "  and CU.comp_id.userID = SU.comp_id.userID " +
                        "  and CU.comp_id.type = SU.comp_id.type";

                int num = 0;

                try
                {
                        session = HibernateUtil.getSession();

                        num = ((Integer) session.iterate(hql).next()).intValue();
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return num;
        }

        public int countCourseUsers(int relationID, int type, int roleID, int state)
                throws CourseSysException
        {
                Session session = null;
                String hql = "select  count(*) from CourseUser CU,UserModel UU,SecUserRoleModel SU " +
                        "  WHERE CU.comp_id.userID = UU.userid and UU.available=1  and  CU.comp_id.type = " +
                        type + "  and SU.comp_id.relationID=CU.comp_id.relationID" +
                        "  and CU.comp_id.relationID = " + relationID +
                        "  and CU.comp_id.userID = SU.comp_id.userID " +
                        "  and CU.comp_id.type = SU.comp_id.type";

                if (state >= 0)
                {
                        hql += (" and CU.state = '" + state + "'");
                }

                if (roleID >= 0)
                {
                        hql += (" and SU.comp_id.roleID = " + roleID);
                }

                int num = 0;

                try
                {
                        session = HibernateUtil.getSession();

                        num = ((Integer) session.iterate(hql).next()).intValue();
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return num;
        }

        public CourseUserListModel getAllCourseUsers(int relationID, int type,
                                                     int first, int max) throws CourseSysException
        {
                Session session = null;
                String hql = "select  from CourseUser CU,UserModel UU,SecUserRoleModel SU " +
                        "  WHERE CU.comp_id.userID = UU.userid and  CU.comp_id.type = " +
                        type + "  and SU.comp_id.relationID=CU.comp_id.relationID" +
                        "  and CU.comp_id.relationID = " + relationID +
                        "  and CU.comp_id.userID = SU.comp_id.userID " +
                        "  and CU.comp_id.type = SU.comp_id.type" +
                        "  order by SU.comp_id.roleID";

                System.out.println("hql=" + hql);

                ArrayList al_CourseUsers = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setFirstResult(first);
                        query.setMaxResults(max);

                        List tmplist = query.list();

                        List tempUserList = new ArrayList();
                        session.flush();
                        session.connection().commit();

                        for (Iterator iter = tmplist.iterator(); iter.hasNext();)
                        {
                                Object[] record = (Object[]) iter.next();
                                CourseUser courseUser = (CourseUser) record[0];
                                UserModel userModel = (UserModel) record[1];
                                SecUserRoleModel secUserRoleModel = (SecUserRoleModel) record[2];
                                int userID = userModel.getUserid();

                                if (!OrganHelper.isDuplicate(tempUserList, userID))
                                {
                                        tempUserList.add(new Integer(userID));

                                        ///Find userName	......
                                        String trueName = userModel.getName();
                                        String loginName = userModel.getLoginname();
                                        Date applyTime = courseUser.getApplyTime();
                                        Date joinTime = courseUser.getJoinTime();

                                        Date finishedTime = courseUser.getFinishedTime();
                                        int courseUser_Status = Integer.parseInt(courseUser.getState());

                                        // form value object - CourseRoleListModel
                                        CourseRoleListModel crlm = getUserRoles(userID, relationID,
                                                type, null);

                                        // form value object - CourseUserModel
                                        CourseUserModel cudm = new CourseUserModel(relationID,
                                                type, userID, courseUser_Status, trueName,
                                                loginName, "", crlm, joinTime, applyTime,
                                                finishedTime);
                                        al_CourseUsers.add(cudm);
                                }
                        }

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                first, max, 0);

                        return culm;
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return null;
        }

        public CourseUserListModel searchCourseUsers(UserForm uf, int relationID,
                                                     int type, int first, int max) throws CourseSysException
        {
                if (uf != null)
                {
                        Session session = null;
                        String hql = "select  from CourseUser CU,UserModel UU,SecUserRoleModel SU " +
                                "  WHERE CU.comp_id.userID = UU.userid and  CU.comp_id.type = " +
                                type + "  and SU.comp_id.relationID=CU.comp_id.relationID" +
                                "  and CU.comp_id.relationID = " + relationID +
                                "  and CU.comp_id.userID = SU.comp_id.userID " +
                                "  and UU.userid >= 100" +
                                "  and CU.comp_id.type = SU.comp_id.type";
                        String condi = "";

                        if ((uf.getLoginName() != null) &&
                                (uf.getLoginName().length() > 0))
                        {
                                condi += (" and UU.loginname like '%" +
                                        uf.getLoginName().trim() + "%'");
                        }

                        if ((uf.getName() != null) && (uf.getName().length() > 0))
                        {
                                condi += (" and UU.loginname like '%" + uf.getName().trim() +
                                        "%'");
                        }

                        if (uf.getCatalogID() > 0)
                        {
                                condi += (" and UU.catalogid=" + uf.getCatalogID());
                        }

                        if ((uf.getRemark7() != null) && (uf.getRemark7().length() != 0))
                        {
                                condi += (" and UU.remark7=" + uf.getRemark7());
                        }

                        hql += condi;

                        ArrayList al_CourseUsers = new ArrayList();

                        try
                        {
                                session = HibernateUtil.getSession();

                                Query query = session.createQuery(hql);
                                query.setFirstResult(first);
                                query.setMaxResults(max);

                                List tmplist = query.list();

                                List tempUserList = new ArrayList();
                                session.flush();
                                session.connection().commit();

                                for (Iterator iter = tmplist.iterator(); iter.hasNext();)
                                {
                                        Object[] record = (Object[]) iter.next();
                                        CourseUser courseUser = (CourseUser) record[0];
                                        UserModel userModel = (UserModel) record[1];
                                        SecUserRoleModel secUserRoleModel = (SecUserRoleModel) record[2];
                                        int userID = userModel.getUserid();

                                        if (!OrganHelper.isDuplicate(tempUserList, userID))
                                        {
                                                tempUserList.add(new Integer(userID));

                                                ///Find userName	......
                                                String trueName = userModel.getName();
                                                String loginName = userModel.getLoginname();
                                                Date applyTime = courseUser.getApplyTime();
                                                Date joinTime = courseUser.getJoinTime();

                                                Date finishedTime = courseUser.getFinishedTime();
                                                int courseUser_Status = Integer.parseInt(courseUser.getState());

                                                // form value object - CourseRoleListModel
                                                CourseRoleListModel crlm = getUserRoles(userID,
                                                        relationID, type, null);

                                                // form value object - CourseUserModel
                                                CourseUserModel cudm = new CourseUserModel(relationID,
                                                        type, userID, courseUser_Status, trueName,
                                                        loginName, "", crlm, joinTime, applyTime,
                                                        finishedTime);
                                                al_CourseUsers.add(cudm);
                                        }
                                }

                                CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                        first, max, 0);

                                return culm;
                        }
                        catch (HibernateException e)
                        {
                                e.printStackTrace();
                        }
                        catch (SQLException e)
                        {
                                e.printStackTrace();
                        }
                        finally
                        {
                                try
                                {
                                        if (session != null)
                                        {
                                                HibernateUtil.releaseSession(session);
                                        }
                                }
                                catch (HibernateException he)
                                {
                                        he.printStackTrace();
                                }
                        }
                }

                return null;
        }

        public CourseUserListModel searchCourseUsersByRole(UserForm uf, int relationID,int roleID,
                                                     int type, int first, int max) throws CourseSysException
        {
                if (uf != null)
                {
                        Session session = null;
                        String hql = "select  from CourseUser CU,UserModel UU,SecUserRoleModel SU " +
                                "  WHERE CU.comp_id.userID = UU.userid  and  CU.comp_id.type = " +
                                type + "  and SU.comp_id.relationID=CU.comp_id.relationID" +
                                "  and CU.comp_id.relationID = " + relationID +
                                "  and CU.comp_id.userID = SU.comp_id.userID " +
                                "  and SU.comp_id.roleID=" + roleID +
                                "  and UU.userid >= 100" +
                                "  and CU.comp_id.type = SU.comp_id.type";
                        String condi = "";

                        if ((uf.getLoginName() != null) &&
                                (uf.getLoginName().length() > 0))
                        {
                                condi += (" and UU.loginname like '%" +
                                        uf.getLoginName().trim() + "%'");
                        }

                        if ((uf.getName() != null) && (uf.getName().length() > 0))
                        {
                                condi += (" and UU.loginname like '%" + uf.getName().trim() +
                                        "%'");
                        }

                        if (uf.getCatalogID() > 0)
                        {
                                condi += (" and UU.catalogid=" + uf.getCatalogID());
                        }

                        if ((uf.getRemark7() != null) && (uf.getRemark7().length() != 0))
                        {
                                condi += (" and UU.remark7=" + uf.getRemark7());
                        }

                        hql += condi;

                        ArrayList al_CourseUsers = new ArrayList();

                        try
                        {
                                session = HibernateUtil.getSession();

                                Query query = session.createQuery(hql);
                                query.setFirstResult(first);
                                query.setMaxResults(max);

                                List tmplist = query.list();

                                List tempUserList = new ArrayList();
                                session.flush();
                                session.connection().commit();

                                for (Iterator iter = tmplist.iterator(); iter.hasNext();)
                                {
                                        Object[] record = (Object[]) iter.next();
                                        CourseUser courseUser = (CourseUser) record[0];
                                        UserModel userModel = (UserModel) record[1];
                                        SecUserRoleModel secUserRoleModel = (SecUserRoleModel) record[2];
                                        int userID = userModel.getUserid();

                                        if (!OrganHelper.isDuplicate(tempUserList, userID))
                                        {
                                                tempUserList.add(new Integer(userID));

                                                ///Find userName	......
                                                String trueName = userModel.getName();
                                                String loginName = userModel.getLoginname();
                                                Date applyTime = courseUser.getApplyTime();
                                                Date joinTime = courseUser.getJoinTime();

                                                Date finishedTime = courseUser.getFinishedTime();
                                                int courseUser_Status = Integer.parseInt(courseUser.getState());

                                                // form value object - CourseRoleListModel
                                                CourseRoleListModel crlm = getUserRoles(userID,
                                                        relationID, type, null);

                                                // form value object - CourseUserModel
                                                CourseUserModel cudm = new CourseUserModel(relationID,
                                                        type, userID, courseUser_Status, trueName,
                                                        loginName, "", crlm, joinTime, applyTime,
                                                        finishedTime);
                                                al_CourseUsers.add(cudm);
                                        }
                                }

                                CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                        first, max, 0);

                                return culm;
                        }
                        catch (HibernateException e)
                        {
                                e.printStackTrace();
                        }
                        catch (SQLException e)
                        {
                                e.printStackTrace();
                        }
                        finally
                        {
                                try
                                {
                                        if (session != null)
                                        {
                                                HibernateUtil.releaseSession(session);
                                        }
                                }
                                catch (HibernateException he)
                                {
                                        he.printStackTrace();
                                }
                        }
                }

                return null;
        }


        public int countSearchCourseUsers(UserForm uf, int relationID, int type)
                throws CourseSysException
        {
                int num = 0;

                if (uf != null)
                {
                        Session session = null;
                        String hql = "select count(*)  from CourseUser CU,UserModel UU,SecUserRoleModel SU " +
                                "  WHERE CU.comp_id.userID = UU.userid and  CU.comp_id.type = " +
                                type + "  and SU.comp_id.relationID=CU.comp_id.relationID" +
                                "  and CU.comp_id.relationID = " + relationID +
                                "  and CU.comp_id.userID = SU.comp_id.userID " +
                                "  and UU.userid >= 100" +
                                "  and CU.comp_id.type = SU.comp_id.type";
                        String condi = "";

                        if ((uf.getLoginName() != null) &&
                                (uf.getLoginName().length() > 0))
                        {
                                condi += (" and UU.loginname like '%" +
                                        uf.getLoginName().trim() + "%'");
                        }

                        if ((uf.getName() != null) && (uf.getName().length() > 0))
                        {
                                condi += (" and UU.loginname like '%" + uf.getName().trim() +
                                        "%'");
                        }

                        if (uf.getCatalogID() > 0)
                        {
                                condi += (" and UU.catalogid=" + uf.getCatalogID());
                        }

                        hql += condi;

                        try
                        {
                                session = HibernateUtil.getSession();
                                num = ((Integer) session.iterate(hql).next()).intValue();
                                session.flush();
                                session.connection().commit();
                        }
                        catch (HibernateException e)
                        {
                                e.printStackTrace();
                        }
                        catch (SQLException e)
                        {
                                e.printStackTrace();
                        }
                        finally
                        {
                                try
                                {
                                        if (session != null)
                                        {
                                                HibernateUtil.releaseSession(session);
                                        }
                                }
                                catch (HibernateException he)
                                {
                                        he.printStackTrace();
                                }
                        }
                }

                return num;
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
                int courseRoleID;
                String trueName;
                String courseRoleName;
                String loginName;
                String orgName;
                String sql_str = "";
                int courseUser_Status;
                ArrayList al_CourseUsers = new ArrayList();
                int userIDcount = 0;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        /*
                          sql_str = "select DISTINCT top "+ pageSize +" UU.UserID,UU.Name UserName,UU.LoginName,CU.ApplyTime CourseUser_ApplyTime," +
                                          " CU.FinishedTime CourseUser_FinishedTime,CU.JoinTime CourseUser_JoinTime,CU.State CourseUser_Status " +
                                          " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU " +
                                          "  WHERE CU.userID = UU.userID and  CU.TYPE = " + type +
                                          "  and CU.relationID = " + relationID +
                                          " and CU.userID = SU.userID " +
                                          "  and CU.type = SU.type" +
                                        " and  UU.UserID not in(select DISTINCT top "+ pageSize*pageNo+ " UU.UserID" +
                                          " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU " +
                                          "  WHERE CU.userID = UU.userID and  CU.TYPE = " + type +
                                          "  and CU.relationID = " + relationID +
                                          " and CU.userID = SU.userID " +
                                          "  and CU.type = SU.type order by  UU.UserID)"                                       +
                                          " order by  UU.UserID " ;
                        */
                        sql_str = "select DISTINCT UU.UserID,UU.Name UserName,UU.LoginName,CU.ApplyTime CourseUser_ApplyTime," +
                                " CU.FinishedTime CourseUser_FinishedTime,CU.JoinTime CourseUser_JoinTime,CU.State CourseUser_Status,sut.RoleID " +
                                " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU,Sec_UserRole_Tab sut " +
                                "  WHERE sut.relationID=cu.relationID and sut.UserID=CU.userID and sut.UserID=SU.userID and sut.UserID=UU.UserID" +
                                "  and CU.userID = UU.userID and sut.RoleID in(select DISTINCT RoleID from Sec_UserRole_Tab where UserID in(select DISTINCT  UU.UserID" +
                                " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU  WHERE CU.userID = UU.userID and  CU.TYPE = " +
                                type + " and CU.relationID = " + relationID +
                                " and CU.userID = SU.userID and CU.type = SU.type ) and RelationID=" +
                                relationID + " and type=" + type + ") and  CU.TYPE = " + type +
                                "  and CU.relationID = " + relationID +
                                " and CU.userID = SU.userID " + "  and CU.type = SU.type" +
                                " and  UU.UserID not in(select DISTINCT UU.UserID" +
                                " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU " +
                                "  WHERE CU.userID = UU.userID and  CU.TYPE = " + type +
                                "  and CU.relationID = " + relationID +
                                " and CU.userID = SU.userID " +
                                "  and CU.type = SU.type order by  UU.UserID  fetch first " +
                                (pageSize * pageNo) + " row only)" +
                                " order by  sut.RoleID fetch first " + pageSize + " row only ";

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

        public CourseUserListModel getCourseAllUsers(int relationID, int type)
                throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                Date joinTime;
                Date applyTime;
                Date finishedTime;
                int userID;
                int courseRoleID;
                String trueName;
                String courseRoleName;
                String loginName;
                String orgName;
                String sql_str = "";
                int courseUser_Status;
                ArrayList al_CourseUsers = new ArrayList();
                int userIDcount = 0;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        /*
                          sql_str = "select DISTINCT top "+ pageSize +" UU.UserID,UU.Name UserName,UU.LoginName,CU.ApplyTime CourseUser_ApplyTime," +
                                          " CU.FinishedTime CourseUser_FinishedTime,CU.JoinTime CourseUser_JoinTime,CU.State CourseUser_Status " +
                                          " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU " +
                                          "  WHERE CU.userID = UU.userID and  CU.TYPE = " + type +
                                          "  and CU.relationID = " + relationID +
                                          " and CU.userID = SU.userID " +
                                          "  and CU.type = SU.type" +
                                        " and  UU.UserID not in(select DISTINCT top "+ pageSize*pageNo+ " UU.UserID" +
                                          " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU " +
                                          "  WHERE CU.userID = UU.userID and  CU.TYPE = " + type +
                                          "  and CU.relationID = " + relationID +
                                          " and CU.userID = SU.userID " +
                                          "  and CU.type = SU.type order by  UU.UserID)"                                       +
                                          " order by  UU.UserID " ;
                        */
                        sql_str = "select DISTINCT UU.UserID,UU.Name UserName,UU.LoginName,CU.ApplyTime CourseUser_ApplyTime," +
                                " CU.FinishedTime CourseUser_FinishedTime,CU.JoinTime CourseUser_JoinTime,CU.State CourseUser_Status,sut.RoleID " +
                                " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU,Sec_UserRole_Tab sut " +
                                "  WHERE sut.relationID=cu.relationID and sut.UserID=CU.userID and sut.UserID=SU.userID and sut.UserID=UU.UserID" +
                                "  and CU.userID = UU.userID and sut.RoleID in(select DISTINCT RoleID from Sec_UserRole_Tab where UserID in(select DISTINCT  UU.UserID" +
                                " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU  WHERE CU.userID = UU.userID and  CU.TYPE = " +
                                type + " and CU.relationID = " + relationID +
                                " and CU.userID = SU.userID and CU.type = SU.type ) and RelationID=" +
                                relationID + " and type=" + type + ") and  CU.TYPE = " + type +
                                "  and CU.relationID = " + relationID +
                                " and CU.userID = SU.userID " +
                                "  and CU.type = SU.type order by sut.RoleID";

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
                                        //CourseRoleListModel crlm = getUserRoles(userID, relationID,
                                        //        type, conn);
                                        //conn = DBUtil.getConnection();

                                        // form value object - CourseUserModel
                                        CourseUserModel cudm = new CourseUserModel(relationID,
                                                type, userID, courseUser_Status, trueName,
                                                loginName, "", null, joinTime, applyTime,
                                                finishedTime);
                                        DebugUtil.print(
                                                "[CourseUserDAOImpl] userIDcount=============== add cudm");
                                        al_CourseUsers.add(cudm);
                                }
                        }

                        DebugUtil.print("course" +
                                "[CourseUserDAOImpl] userIDcount===============" + userIDcount);

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                0, 10000, 0);
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

        public int getCourseAllUserscount(int relationID, int type, int pageNo,
                                          int pageSize) throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                Date joinTime;
                Date applyTime;
                Date finishedTime;
                int userID;
                int courseRoleID;
                String trueName;
                String courseRoleName;
                String loginName;
                String orgName;
                String sql_str = "";
                int courseUser_Status;
                ArrayList al_CourseUsers = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        sql_str = "select UU.UserID,UU.Name UserName,UU.LoginName,CU.ApplyTime CourseUser_ApplyTime," +
                                " CU.FinishedTime CourseUser_FinishedTime,CU.JoinTime CourseUser_JoinTime,CU.State CourseUser_Status " +
                                " FROM C_User_Tab CU,U_User_Tab UU,Sec_UserRole_Tab SU " +
                                "  WHERE CU.userID = UU.userID and  CU.TYPE = " + type +
                                "  and CU.relationID = " + relationID +
                                " and CU.userID = SU.userID " +
                                "  and CU.type = SU.type order by SU.roleID,SU.userID";

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseAllUsers1======================##sqlstr:" +
                                        sql_str);
                        rs = stmt.executeQuery(sql_str);

                        ArrayList al_Roles = null;
                        List tempUserList = new ArrayList();

                        while (rs.next())
                        {
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

                                        al_CourseUsers.add(cudm);
                                }
                        }

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                pageNo, pageSize, 0);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseAllUsers======================Sucess in Dao: CourseUserDAO - getCourseUsers");

                        return al_CourseUsers.size();
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

        public CourseUserListModel getCourseUsers(String[] relationID, int type,
                                                  int pageNo, int pageSize) throws CourseSysException
        {
                Connection conn = null;
                Statement stmt1 = null;
                Statement stmt = null;
                ResultSet rs = null;
                ResultSet rs1 = null;

                String relationIDString = "";

                if (relationID != null)
                {
                        relationIDString = " in (";

                        for (int i = 0; i < relationID.length; i++)
                        {
                                if (i != 0)
                                {
                                        relationIDString += ("," + relationID[i]);
                                }
                                else
                                {
                                        relationIDString += relationID[i];
                                }
                        }

                        relationIDString += ") ";
                }

                java.util.Date joinTime;
                java.util.Date applyTime;
                java.util.Date finishedTime;
                int userID;
                int courseRoleID;
                String trueName;
                String courseRoleName;
                String loginName;
                String orgName;
                String sqlCount_str;
                String sql_str = "";
                int pageNo_Local = pageNo;
                int pageSize_Local = pageSize;
                int totalPageNumbers;
                int count;
                int i = 1;
                int j = 1;
                int courseID = 0;
                int courseUser_Status;
                ArrayList al_CourseUsers = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt1 = conn.createStatement();
                        stmt = conn.createStatement();

                        //Find the count of CourseUsers
                        sqlCount_str = "select count(*) as count from C_User_tab" +
                                " where relationID " + relationIDString + " and type = " +
                                type;

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================sqlStr=" +
                                        sqlCount_str);

                        stmt.execute(sqlCount_str);
                        rs = stmt.getResultSet();
                        rs.next();
                        count = rs.getInt("count");

                        if ((count % pageSize_Local) == 0)
                        {
                                totalPageNumbers = count / pageSize_Local;
                        }
                        else
                        {
                                totalPageNumbers = (count / pageSize_Local) + 1;
                        }

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================##totalPageNumbers:" +
                                        totalPageNumbers);

                        if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                //select userID from C_Course_Tab
                                sql_str = "Select DISTINCT UserID,UserName,LoginName,OrgName," +
                                        "CourseUser_ApplyTime,CourseUser_finishedTime," +
                                        "CourseUser_joinTime,courseUser_Status,courseID " +
                                        "from C_MyCourse_View" + " where courseID " +
                                        relationIDString;
                        }
                        else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                //select userID from c_user_tab
                                sql_str = "select DISTINCT UU.UserID,UU.Name UserName,UU.LoginName,T.OrgName,CU.ApplyTime CourseUser_ApplyTime," +
                                        " CU.FinishedTime CourseUser_FinishedTime,CU.JoinTime CourseUser_JoinTime,CU.State CourseUser_Status ,CU.RELATIONID courseID" +
                                        " FROM C_User_Tab CU,U_User_Tab UU,TM_Org_Tab T,Cer_Certificate_Tab CC " +
                                        " WHERE CU.userID = UU.userID and CU.RELATIONID = CC.CERTIFICATEID and CU.TYPE = " +
                                        type + " and cc.ORGID = t.ORGID and CU.relationID " +
                                        relationIDString;
                        }

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================##sqlstr:" +
                                        sql_str);

                        rs = stmt.executeQuery(sql_str);

                        if (rs.next())
                        {
                                if (pageNo_Local <= totalPageNumbers)
                                {
                                        while (i <= (pageSize_Local * (pageNo_Local - 1)))
                                        {
                                                rs.next();

                                                i++;
                                        }

                                        do
                                        {
                                                //ArrayList必须在这里声明
                                                ArrayList al_Roles = new ArrayList();

                                                userID = rs.getInt(1);
                                                courseID = rs.getInt("courseID");

                                                ///Find userName	......
                                                trueName = rs.getString("UserName");
                                                loginName = rs.getString("LoginName");
                                                orgName = rs.getString("OrgName");
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

                                                sql_str = "select Sec_UserRole_Tab.RoleID,Name from Sec_UserRole_Tab,Sec_Role_Tab where " +
                                                        "Sec_UserRole_Tab.RoleID=Sec_Role_Tab.RoleID and " +
                                                        "UserID=" + userID + " and relationID = " +
                                                        courseID + " and type=" + type;

                                                LogUtil.debug("course",
                                                        "[CourseUserDAOImpl] getCourseUsers======================sqlStr=" +
                                                                sql_str);
                                                rs1 = stmt1.executeQuery(sql_str);

                                                while (rs1.next())
                                                {
                                                        courseRoleID = rs1.getInt("RoleID");
                                                        courseRoleName = rs1.getString("Name");

                                                        CourseRoleModel crm = new CourseRoleModel(courseRoleID,
                                                                courseRoleName);
                                                        al_Roles.add(crm);
                                                }

                                                // form value object - CourseRoleListModel
                                                CourseRoleListModel crlm = new CourseRoleListModel(userID,
                                                        al_Roles);

                                                // form value object - CourseUserjoinTimeModel
                                                CourseUserModel cudm = new CourseUserModel(courseID,
                                                        type, userID, courseUser_Status, trueName,
                                                        loginName, orgName, crlm, joinTime, applyTime,
                                                        finishedTime);

                                                al_CourseUsers.add(cudm);
                                                j++;
                                        } while (rs.next() && (j <= pageSize_Local));
                                }
                                else
                                {
                                        while (i <= (pageSize_Local * (totalPageNumbers - 1)))
                                        {
                                                rs.next();

                                                i++;
                                        }

                                        do
                                        {
                                                //ArrayList必须在这里声明
                                                ArrayList al_Roles = new ArrayList();

                                                userID = rs.getInt(1);

                                                courseID = rs.getInt("courseID");
                                                trueName = rs.getString("UserName");
                                                loginName = rs.getString("LoginName");
                                                orgName = rs.getString("OrgName");
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

                                                sql_str = "select Sec_UserRole_Tab.RoleID,Name from Sec_UserRole_Tab,Sec_Role_Tab where " +
                                                        "Sec_UserRole_Tab.RoleID=Sec_Role_Tab.RoleID and " +
                                                        "UserID=" + userID + " and relationID = " +
                                                        courseID + " and type = " + type;
                                                LogUtil.debug("course",
                                                        "[CourseUserDAOImpl] getCourseUsers======================sqlStr=" +
                                                                sql_str);
                                                rs1 = stmt.executeQuery(sql_str);

                                                while (rs1.next())
                                                {
                                                        courseRoleID = rs1.getInt("RoleID");
                                                        courseRoleName = rs1.getString("Name");

                                                        CourseRoleModel crm = new CourseRoleModel(courseRoleID,
                                                                courseRoleName);
                                                        al_Roles.add(crm);
                                                }

                                                // form value object - CourseRoleListModel
                                                CourseRoleListModel crlm = new CourseRoleListModel(userID,
                                                        al_Roles);

                                                // form value object - CourseUserjoinTimeModel
                                                // form value object - CourseUserjoinTimeModel
                                                CourseUserModel cudm = new CourseUserModel(courseID,
                                                        type, userID, courseUser_Status, trueName,
                                                        loginName, orgName, crlm, joinTime, applyTime,
                                                        finishedTime);

                                                al_CourseUsers.add(cudm);
                                                j++;
                                        } while (rs.next() && (j <= pageSize_Local));
                                }
                        }

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                pageNo, pageSize, totalPageNumbers);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================Sucess in Dao: CourseUserDAO - getCourseUsers");

                        return culm;
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeResultSet(rs1);
                        DBUtil.closeStatement(stmt1);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * @param relationID
         * @param type
         * @param courseUser_Status
         * @param pageNo
         * @param pageSize
         * @return
         * @throws CourseSysException
         */
        public CourseUserListModel getCourseUsersByState(int relationID, int type,
                                                         int courseUser_Status, int pageNo, int pageSize)
                throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                String sqlStr = null;

                java.util.Date joinTime;
                java.util.Date applyTime;
                java.util.Date finishedTime;
                int userID;
                int courseRoleID;
                String trueName;
                String courseRoleName;
                String loginName;

                ArrayList al_CourseUsers = new ArrayList();
                ArrayList al_Roles = null;

                String sql_str = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //select userID from C_Course_Tab
                        sql_str = "select C_User_Tab.userID,name,loginName,joinTime, applyTime,finishedTime,state from C_User_Tab,U_User_Tab" +
                                " where C_User_Tab.userID=U_User_Tab.userID" +
                                " and relationID = " + relationID + " and type = " + type +
                                " and state = '" + courseUser_Status + "'";
                        rs = stmt.executeQuery(sql_str);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsersByState======================##sqlstr:" +
                                        sql_str);

                        while (rs.next())
                        {
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl] getCourseUsersByState======================a");
                                al_Roles = new ArrayList();
                                userID = rs.getInt(1);

                                //Find userName	......
                                trueName = rs.getString("name");
                                loginName = rs.getString("loginName");

                                applyTime = DateTimeUtil.toDate(rs.getDate("applyTime"),
                                        rs.getTime("applyTime"));
                                joinTime = DateTimeUtil.toDate(rs.getDate("joinTime"),
                                        rs.getTime("joinTime"));
                                finishedTime = DateTimeUtil.toDate(rs.getDate("finishedTime"),
                                        rs.getTime("finishedTime"));

                                // form value object - CourseRoleListModel
                                CourseRoleListModel crlm = getUserRoles(userID, relationID,
                                        type, conn);

                                // form value object - CourseUserjoinTimeModel
                                CourseUserModel cudm = new CourseUserModel(relationID, type,
                                        userID, courseUser_Status, trueName, loginName, crlm,
                                        joinTime, applyTime, finishedTime);

                                al_CourseUsers.add(cudm);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl] getCourseUsersByState======================x");
                        }

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                pageNo, pageSize, 0);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsersByState======================Sucess in Dao: CourseUserDAO - getCourseUsers");

                        return culm;
                }
                catch (SQLException se)
                {
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

        public CourseUserListModel searchC(UserForm uf, int relationID, int type,
                                           int first, int max, int groupid, boolean flag)
                throws CourseSysException
        {
                String hql = "select  from CourseUser CU,UserModel UU,SecUserRoleModel SU " +
                        "  WHERE CU.comp_id.userID = UU.userid and  CU.comp_id.type = " +
                        type + "  and SU.comp_id.relationID=CU.comp_id.relationID" +
                        "  and CU.comp_id.relationID = " + relationID +
                        "  and CU.comp_id.userID = SU.comp_id.userID " +
                        "  and UU.userid >= 100" +
                        "  and CU.comp_id.type = SU.comp_id.type";
                Session session = null;

                if (flag)
                {
                        hql = hql +
                                " and CU.comp_id.userID not in (select GU.userid from UserGroupModel GU where GU.groupid=" +
                                groupid + ")";
                }
                else
                {
                        hql = hql +
                                " and CU.comp_id.userID in (select GU.userid from UserGroupModel GU where GU.groupid=" +
                                groupid + ")";
                }

                String condi = "";

                if ((uf.getLoginName() != null) && (uf.getLoginName().length() > 0))
                {
                        condi += (" and UU.loginname like '%" + uf.getLoginName().trim() +
                                "%'");
                }

                if ((uf.getName() != null) && (uf.getName().length() > 0))
                {
                        condi += (" and UU.loginname like '%" + uf.getName().trim() + "%'");
                }

                if (uf.getCatalogID() > 0)
                {
                        condi += (" and UU.catalogid=" + uf.getCatalogID());
                }

                hql += condi;

                ArrayList al_CourseUsers = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);

                        query.setFirstResult(first);
                        query.setMaxResults(max);

                        List tmplist = query.list();

                        List tempUserList = new ArrayList();
                        session.flush();
                        session.connection().commit();

                        for (Iterator iter = tmplist.iterator(); iter.hasNext();)
                        {
                                Object[] record = (Object[]) iter.next();
                                CourseUser courseUser = (CourseUser) record[0];
                                UserModel userModel = (UserModel) record[1];
                                SecUserRoleModel secUserRoleModel = (SecUserRoleModel) record[2];
                                int userID = userModel.getUserid();

                                if (!OrganHelper.isDuplicate(tempUserList, userID))
                                {
                                        tempUserList.add(new Integer(userID));

                                        ///Find userName	......
                                        String trueName = userModel.getName();
                                        String loginName = userModel.getLoginname();
                                        Date applyTime = courseUser.getApplyTime();
                                        Date joinTime = courseUser.getJoinTime();

                                        Date finishedTime = courseUser.getFinishedTime();
                                        int courseUser_Status = Integer.parseInt(courseUser.getState());

                                        // form value object - CourseRoleListModel
                                        CourseRoleListModel crlm = getUserRoles(userID, relationID,
                                                type, null);

                                        // form value object - CourseUserModel
                                        CourseUserModel cudm = new CourseUserModel(relationID,
                                                type, userID, courseUser_Status, trueName,
                                                loginName, "", crlm, joinTime, applyTime,
                                                finishedTime);
                                        al_CourseUsers.add(cudm);
                                }
                        }

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                first, max, 0);

                        return culm;
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return null;
        }

        public CourseUserListModel getForshenpi(int relationID, int type,
                                                int courseUser_Status, int pageNo, int pageSize)
                throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                java.util.Date joinTime;
                java.util.Date applyTime;
                java.util.Date finishedTime;
                int userID;
                String trueName;
                String loginName;

                ArrayList al_CourseUsers = new ArrayList();

                String sql_str = null;

                try
                {
                        conn = DBUtil.getConnection();
                        //conn.setAutoCommit(false);
                        stmt = conn.createStatement();

                        //select userID from C_Course_Tab
                        sql_str = "select C_User_Tab.userID,name,loginName,joinTime, applyTime,finishedTime,state,F_UserAccount_TAB.uAcotTotal from C_User_Tab,U_User_Tab,F_UserAccount_TAB" +
                                " where C_User_Tab.userID=U_User_Tab.userID and U_User_Tab.Available=1 " +
                                " and relationID = " + relationID + " and type = " + type +
                                " and state = '" + courseUser_Status +
                                "' and F_UserAccount_TAB.userid=C_User_Tab.userID";
                        rs = stmt.executeQuery(sql_str);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsersByState======================##sqlstr:" +
                                        sql_str);

                        while (rs.next())
                        {
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl] getCourseUsersByState======================a");
                                userID = rs.getInt(1);

                                //Find userName	......
                                trueName = rs.getString("name");
                                loginName = rs.getString("loginName");

                                applyTime = DateTimeUtil.toDate(rs.getDate("applyTime"),
                                        rs.getTime("applyTime"));
                                joinTime = DateTimeUtil.toDate(rs.getDate("joinTime"),
                                        rs.getTime("joinTime"));
                                finishedTime = DateTimeUtil.toDate(rs.getDate("finishedTime"),
                                        rs.getTime("finishedTime"));

                                // form value object - CourseRoleListModel
                                //CourseRoleListModel crlm = getUserRoles(userID, relationID, type, conn);

                                // form value object - CourseUserjoinTimeModel
                                CourseUserModel cudm = new CourseUserModel(relationID, type,
                                        userID, courseUser_Status, trueName, loginName,
                                        joinTime, applyTime, finishedTime);
                                //暂时使用成绩ID存储客户帐户余额费用
                                cudm.setScore(rs.getString("uAcotTotal"));

                                al_CourseUsers.add(cudm);
                                LogUtil.debug("course",
                                        "[CourseUserDAOImpl] getCourseUsersByState======================x");
                        }

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                pageNo, pageSize, 0);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsersByState======================Sucess in Dao: CourseUserDAO - getCourseUsers");

                        return culm;
                }
                catch (SQLException se)
                {
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

        /**
         * return the course user list by state and role.
         *
         * @throws CourseSysException
         */
        public CourseUserListModel getCourseUsers(int relationID, int type,
                                                  int role, int state) throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                String sqlStr = null;

                java.util.Date joinTime;
                java.util.Date applyTime;
                java.util.Date finishedTime;
                int userID;
                String trueName;
                String courseRoleName;
                String loginName;

                ArrayList al_CourseUsers = new ArrayList();
                ArrayList al_Roles = null;

                String sql_str = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //select userID from C_Course_Tab
                        sql_str = "select C_User_Tab.userID,name,loginName,joinTime, applyTime,finishedTime,state from C_User_Tab,U_User_Tab" +
                                " where C_User_Tab.userID=U_User_Tab.userID" +
                                " and relationID = " + relationID + " and type = " + type +
                                " and state = '" + state + "'";
                        rs = stmt.executeQuery(sql_str);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================##sqlstr:" +
                                        sql_str);

                        while (rs.next())
                        {
                                al_Roles = new ArrayList();
                                userID = rs.getInt(1);

                                //Find userName	......
                                trueName = rs.getString("name");
                                loginName = rs.getString("loginName");

                                applyTime = DateTimeUtil.toDate(rs.getDate("applyTime"),
                                        rs.getTime("applyTime"));
                                joinTime = DateTimeUtil.toDate(rs.getDate("joinTime"),
                                        rs.getTime("joinTime"));
                                finishedTime = DateTimeUtil.toDate(rs.getDate("finishedTime"),
                                        rs.getTime("finishedTime"));

                                // form value object - CourseRoleListModel
                                CourseRoleListModel crlm = getUserRoles(userID, relationID,
                                        type, conn);

                                // form value object - CourseUserjoinTimeModel
                                CourseUserModel cudm = new CourseUserModel(relationID, type,
                                        userID, state, trueName, loginName, crlm, joinTime,
                                        applyTime, finishedTime);

                                al_CourseUsers.add(cudm);
                        }

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                0, 0, 0);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================Sucess in Dao: CourseUserDAO - getCourseUsers");

                        return culm;
                }
                catch (Exception se)
                {
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================Exception:" +
                                        se.getMessage());
                        System.out.println(
                                "[CourseUserDAOImpl] getCourseUsers======================Exception:" +
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

        /**
         * @param relationID
         * @param type
         * @param roleID
         * @param pageNo
         * @param pageSize
         * @return
         * @throws CourseSysException
         */
        public CourseUserListModel getCourseUsersByRole(int relationID, int type,
                                                        int roleID, int pageNo, int pageSize) throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                java.util.Date joinTime;
                java.util.Date applyTime;
                java.util.Date finishedTime;
                int userID;
                String trueName;
                String loginName;
                String sqlStr;
                int pageNo_Local = pageNo;
                int pageSize_Local = pageSize;
                int totalPageNumbers;
                int count;
                int i = 1;
                int j = 1;
                int state;
                ArrayList al_CourseUsers = new ArrayList();
                String typeSQL = "  and C_User_Tab.type = " + type;
                String typeSQL1 = "  and Sec_UserRole_Tab.type = " + type;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //Find the count of CourseUsers
                        sqlStr = "select count(DISTINCT C_User_Tab.UserID) as count from C_User_Tab,Sec_UserRole_Tab,Sec_Role_Tab,U_User_Tab " +
                                " where C_User_Tab.userID=U_User_Tab.userID  " +
                                "and Sec_UserRole_Tab.userID=U_User_Tab.userID " +
                                "and Sec_UserRole_Tab.RoleID=Sec_Role_Tab.RoleID  " +
                                "and Sec_UserRole_Tab.userID=C_User_Tab.userID   " +
                                "and Sec_UserRole_Tab.relationID=C_User_Tab.relationID" +
                                " and C_User_Tab.relationID = " + relationID + typeSQL +
                                "  and Sec_UserRole_Tab.roleID=" + roleID + typeSQL1;

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================sqlStr=" +
                                        sqlStr);
                        stmt.execute(sqlStr);
                        rs = stmt.getResultSet();
                        rs.next();
                        count = rs.getInt("count");

                        if ((count % pageSize_Local) == 0)
                        {
                                totalPageNumbers = count / pageSize_Local;
                        }
                        else
                        {
                                totalPageNumbers = (count / pageSize_Local) + 1;
                        }

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================##totalPageNumbers:" +
                                        totalPageNumbers);

                        //select userID from C_Course_Tab
                        sqlStr = "select DISTINCT C_User_Tab.userID,U_User_Tab.name,U_User_Tab.sex," +
                                "loginName,applyTime,joinTime,finishedTime,state,Sec_Role_Tab.name as roleName" +
                                " from " +
                                " C_User_Tab,Sec_UserRole_Tab,Sec_Role_Tab,U_User_Tab " +
                                " where  C_User_Tab.userID=U_User_Tab.userID  " +
                                "and Sec_UserRole_Tab.userID=U_User_Tab.userID " +
                                "and Sec_UserRole_Tab.RoleID=Sec_Role_Tab.RoleID  " +
                                "and Sec_UserRole_Tab.userID=C_User_Tab.userID   " +
                                "and Sec_UserRole_Tab.relationID=C_User_Tab.relationID" +
                                " and C_User_Tab.relationID=" + relationID + typeSQL +
                                " and Sec_UserRole_Tab.roleID=" + roleID + typeSQL;

                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================##sqlstr:" +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                if (pageNo_Local <= totalPageNumbers)
                                {
                                        while (i <= (pageSize_Local * (pageNo_Local - 1)))
                                        {
                                                rs.next();

                                                i++;
                                        }

                                        do
                                        {
                                                userID = rs.getInt(1);

                                                //Find userName	......
                                                trueName = StringUtil.nullToStr(rs.getString("name"));
                                                loginName = StringUtil.nullToStr(rs.getString(
                                                        "loginName"));
                                                state = rs.getInt("state");
                                                applyTime = DateTimeUtil.toDate(rs.getDate("applyTime"),
                                                        rs.getTime("applyTime"));
                                                joinTime = DateTimeUtil.toDate(rs.getDate("joinTime"),
                                                        rs.getTime("joinTime"));
                                                finishedTime = DateTimeUtil.toDate(rs.getDate(
                                                        "finishedTime"), rs.getTime("finishedTime"));

                                                // form value object - CourseUserjoinTimeModel
                                                CourseUserModel cudm = new CourseUserModel(relationID,
                                                        type, userID, state, trueName, loginName,
                                                        joinTime, applyTime, finishedTime);

                                                cudm.setSex(rs.getString("sex"));
                                                al_CourseUsers.add(cudm);
                                                j++;
                                        } while (rs.next() && (j <= pageSize_Local));
                                }
                                else
                                {
                                        while (i <= (pageSize_Local * (totalPageNumbers - 1)))
                                        {
                                                rs.next();

                                                i++;
                                        }

                                        do
                                        {
                                                userID = rs.getInt(1);

                                                //Find userName	......
                                                trueName = StringUtil.nullToStr(rs.getString("name"));
                                                loginName = StringUtil.nullToStr(rs.getString(
                                                        "loginName"));
                                                state = rs.getInt("state");
                                                applyTime = DateTimeUtil.toDate(rs.getDate("applyTime"),
                                                        rs.getTime("applyTime"));
                                                joinTime = DateTimeUtil.toDate(rs.getDate("joinTime"),
                                                        rs.getTime("joinTime"));
                                                finishedTime = DateTimeUtil.toDate(rs.getDate(
                                                        "finishedTime"), rs.getTime("finishedTime"));

                                                // form value object - CourseUserjoinTimeModel
                                                CourseUserModel cudm = new CourseUserModel(relationID,
                                                        type, userID, state, trueName, loginName,
                                                        joinTime, applyTime, finishedTime);

                                                cudm.setSex(rs.getString("sex"));
                                                al_CourseUsers.add(cudm);
                                                j++;
                                        } while (rs.next() && (j <= pageSize_Local));
                                }
                        }

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                pageNo, pageSize, totalPageNumbers);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================Sucess in Dao: CourseUserDAO - getCourseUsers");

                        return culm;
                }
                catch (SQLException se)
                {
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

        public CourseUserModel getCourseUser(int relationID, int type, int userID)
                throws CourseSysException
        {
                Connection conn = null;
                Statement stmt1 = null;
                Statement stmt = null;
                ResultSet rs = null;
                ResultSet rs1 = null;

                Date joinTime;
                Date applyTime;
                Date finishedTime;
                int courseRoleID;
                String trueName;
                String courseRoleName;
                String loginName;
                String sqlStr = null;
                int courseUser_Status;
                CourseUserModel cudm = null;

                String typeSQL = "  and C_User_Tab.type = " + type;
                String typeSQL1 = "  and Sec_UserRole_Tab.type = " + type;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        stmt1 = conn.createStatement();
                        sqlStr = "select  DISTINCT C_User_Tab.userID,name,loginName,joinTime, applyTime,finishedTime,state from C_User_Tab,U_User_Tab" +
                                " where C_User_Tab.userID = U_User_Tab.userID" +
                                " and relationID = " + relationID +
                                " and C_User_Tab.userID = " + userID + typeSQL;

                        LogUtil.info("course",
                                "[CourseUserDAOImpl] getCourseUser======================sqlStr=" +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                //ArrayList必须在这里声明
                                ArrayList al_Roles = new ArrayList();

                                userID = rs.getInt(1);

                                //Find userName	......
                                trueName = StringUtil.nullToStr(rs.getString("name"));
                                loginName = StringUtil.nullToStr(rs.getString("loginName"));

                                applyTime = DateTimeUtil.toDate(rs.getDate("applyTime"),
                                        rs.getTime("applyTime"));
                                joinTime = DateTimeUtil.toDate(rs.getDate("joinTime"),
                                        rs.getTime("joinTime"));
                                finishedTime = DateTimeUtil.toDate(rs.getDate("finishedTime"),
                                        rs.getTime("finishedTime"));
                                courseUser_Status = rs.getInt("state");

                                sqlStr = "select Sec_UserRole_Tab.RoleID,Name from Sec_UserRole_Tab,Sec_Role_Tab where " +
                                        "Sec_UserRole_Tab.RoleID=Sec_Role_Tab.RoleID" +
                                        " and userID = " + userID + " and relationID = " +
                                        relationID + typeSQL1;

                                LogUtil.info("course",
                                        "[CourseUserDAOImpl] getCourseUsers======================sqlStr=" +
                                                sqlStr);
                                rs1 = stmt1.executeQuery(sqlStr);

                                while (rs1.next())
                                {
                                        courseRoleID = rs1.getInt("RoleID");
                                        courseRoleName = rs1.getString("Name");

                                        CourseRoleModel crm = new CourseRoleModel(courseRoleID,
                                                courseRoleName);
                                        al_Roles.add(crm);
                                }

                                // form value object - CourseRoleListModel
                                CourseRoleListModel crlm = new CourseRoleListModel(userID,
                                        al_Roles);

                                // form value object - CourseUserModel
                                cudm = new CourseUserModel(relationID, type, userID,
                                        courseUser_Status, trueName, loginName, crlm, joinTime,
                                        applyTime, finishedTime);
                        }

                        return cudm;
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeResultSet(rs1);
                        DBUtil.closeStatement(stmt1);
                        DBUtil.closeConnection(conn);
                }
        }

        public CourseUserListModel getCourseStudents(int relationID, int type,
                                                     int pageNo, int pageSize) throws CourseSysException
        {
                return getCourseUsersByRole(relationID, type,
                        SecurityConstants.ROLE_COURSR_STUDENT, pageNo, pageSize);
        }

        public CourseUserListModel getCourseTeachers(int relationID, int type,
                                                     int pageNo, int pageSize) throws CourseSysException
        {
                return getCourseUsersByRole(relationID, type,
                        SecurityConstants.ROLE_COURSE_TEACHER, pageNo, pageSize);
        }

        public CourseUserListModel getCourseAdministrators(int relationID,
                                                           int type, int pageNo, int pageSize) throws CourseSysException
        {
                return getCourseUsersByRole(relationID, type,
                        SecurityConstants.ROLE_TRAINING_ADMINISTRATOR, pageNo, pageSize);
        }

        /**
         * 返回学员总数，不包含待审批的学员
         *
         * @param relationID
         * @param type
         * @return
         * @throws CourseSysException
         */
        public int getTotalNumberByCourseStudent(int relationID, int type)
                throws CourseSysException
        {
                return getCourseTotalNumberByRole(relationID, type,
                        SecurityConstants.ROLE_COURSR_STUDENT);
        }

        public int getTotalNumberByCourseStudent(int relationID, int type, int state)
                throws CourseSysException
        {
                return getCourseTotalNumberByRole(relationID, type,
                        SecurityConstants.ROLE_COURSR_STUDENT, state);
        }

        public List getCourseList(int relationID, int type)
                throws CourseSysException
        {
                List courseList = new ArrayList();

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        courseList = CertHelper.getCourseListFromCert(String.valueOf(
                                relationID), LMSConstants.LEARNING_TUTORIAL);
                }

                return courseList;
        }

        public CourseUserModel getUserCourseScore(int courseID, int userID, int type)
                throws CourseSysException
        {
                String strSql = "";
                int state = 0;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CourseUserModel cum = new CourseUserModel();

                if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        try
                        {
                                strSql = "select UU.Name,UU.LoginName,CU.applyTime,CU.joinTime,CU.finishedTime" +
                                        " ,CU.state from U_User_Tab UU , C_User_Tab CU Where " +
                                        " UU.UserID = CU.UserID " + " AND CU.UserID = " + userID +
                                        " AND CU.type = " + type + " AND CU.relationID = " +
                                        courseID;
                                // rs = operateDB.exequery(strSql);
                                conn = DBUtil.getConnection();
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(strSql);

                                if ((rs != null) && rs.next())
                                {
                                        cum.setLoginName(rs.getString("LoginName"));
                                        cum.setName(rs.getString("Name"));
                                        cum.setApplyTime(rs.getDate("applyTime"));
                                        cum.setJoinTime(rs.getDate("joinTime"));
                                        cum.setFinishedTime(rs.getDate("finishedTime"));
                                        cum.setState(rs.getInt("state"));
                                        state = rs.getInt("state");
                                }

                                if (state == CourseKeys.COURSE_USER_FINISH_STATE)
                                {
                                        strSql = "select * from TM_SCORE_TAB where " +
                                                " UserID = " + userID + " AND CourseID = " + courseID;
                                        conn = DBUtil.getConnection();
                                        stmt = conn.createStatement();
                                        rs = stmt.executeQuery(strSql);

                                        if ((rs != null) && rs.next())
                                        {
                                                cum.setIsPass(rs.getInt("isPass"));
                                                cum.setCredit(rs.getFloat("Credit"));
                                                cum.setScore(rs.getString("Score"));
                                        }
                                }
                        }
                        catch (ULMSSysException esse)
                        {
                                throw new CourseSysException(
                                        "Exception throw from [CourseUserDAOImpl] in get number of course user :" +
                                                esse);
                        }
                        catch (SQLException sqle)
                        {
                                throw new CourseSysException(
                                        "Exception throw from [CourseUserDAOImpl] in get number of course user :" +
                                                sqle);
                        }
                        finally
                        {
                                DBUtil.closeResultSet(rs);
                                DBUtil.closeStatement(stmt);
                                DBUtil.closeConnection(conn);
                        }
                }

                return cum;
        }

        /**
         * 判断是否是课程/证书创建者.<br>
         * type==SecurityConstants.USER_COURSE_RELATION: 课程 <br>
         * type==SecurityConstants.USER_CERTIFICATE_RELATION:证书 <br>
         * <br>
         * todo:证书的判断,暂时还未完善.
         *
         * @param relationID
         * @param type
         * @param userID
         * @return
         * @throws CourseSysException
         */
        public boolean isCreator(int relationID, int type, int userID)
                throws CourseSysException
        {
                boolean isCreator = false;
                int Creator = 0;

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                String strSql = "";

                if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        strSql = "Select Creator From C_Course_tab Where " +
                                " CourseID = " + relationID;
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] isCreator======================strSql=" +
                                        strSql);

                        try
                        {
                                conn = DBUtil.getConnection();
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(strSql);

                                if ((rs != null) && rs.next())
                                {
                                        Creator = rs.getInt("Creator");
                                }
                        }
                        catch (SQLException se)
                        {
                                se.printStackTrace();
                                throw new CourseSysException(se);
                        }
                        finally
                        {
                                DBUtil.closeResultSet(rs);
                                DBUtil.closeStatement(stmt);
                                DBUtil.closeConnection(conn);
                        }

                        if (Creator == userID)
                        {
                                isCreator = true;
                        }
                }

                return isCreator;
        }

        public List getAllUser(int relationID, int type) throws CourseSysException
        {
                List userList = new ArrayList();
                String hql = " from CourseUser where relationID = " + relationID +
                        " and type = " + type;

                CourseUser cu = null;

                try
                {
                        LogUtil.debug("CourseUserDAOImpl", "hql =================" + hql);

                        List tempList = HibernateDAO.find(hql);

                        for (int i = 0; (tempList != null) && (i < tempList.size()); i++)
                        {
                                cu = (CourseUser) tempList.get(i);
                                userList.add(cu.getComp_id().getUserID() + "");
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return userList;
        }

        /**
         * return the course user list by state and role.
         *
         * @throws CourseSysException
         */
        public CourseUserListModel getCourseUsersByCertId(int relationID, int type)
                throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                String sqlStr = null;

                java.util.Date joinTime;
                java.util.Date applyTime;
                java.util.Date finishedTime;
                int userID;
                String trueName;
                String courseRoleName;
                String loginName;
                String useraccount = "0"; //用户帐户金额

                ArrayList al_CourseUsers = new ArrayList();
                ArrayList al_Roles = null;

                String sql_str = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //select userID from C_Course_Tab
                        sql_str = "select C_User_Tab.userID,name,loginName,joinTime, applyTime,finishedTime,state from C_User_Tab,U_User_Tab" +
                                " where C_User_Tab.userID=U_User_Tab.userID" +
                                " and relationID = " + relationID + " and type = " + type;
                        rs = stmt.executeQuery(sql_str);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================##sqlstr:" +
                                        sql_str);

                        while (rs.next())
                        {
                                al_Roles = new ArrayList();
                                userID = rs.getInt("userID");

                                //Find userName	......
                                trueName = rs.getString("name");
                                loginName = rs.getString("loginName");

                                applyTime = DateTimeUtil.toDate(rs.getDate("applyTime"),
                                        rs.getTime("applyTime"));
                                joinTime = DateTimeUtil.toDate(rs.getDate("joinTime"),
                                        rs.getTime("joinTime"));
                                finishedTime = DateTimeUtil.toDate(rs.getDate("finishedTime"),
                                        rs.getTime("finishedTime"));

                                // form value object - CourseRoleListModel
                                //CourseRoleListModel crlm = getUserRoles(userID, relationID, type, conn);

                                // form value object - CourseUserjoinTimeModel
                                useraccount = getUserAccount(userID); //Transfer

                                CourseUserModel cudm = new CourseUserModel(relationID, userID,
                                        type, trueName, loginName, joinTime, applyTime,
                                        finishedTime, useraccount);
                                //System.out.println("LLLLLuserID=================================="+userID);
                                al_CourseUsers.add(cudm);
                        }

                        CourseUserListModel culm = new CourseUserListModel(al_CourseUsers,
                                0, 0, 0);
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================Sucess in Dao: CourseUserDAO - getCourseUsers");

                        return culm;
                }
                catch (Exception se)
                {
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl] getCourseUsers======================Exception:" +
                                        se.getMessage());
                        System.out.println(
                                "[CourseUserDAOImpl] getCourseUsers======================Exception:" +
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

        /**
         * 取用户帐户的余额
         *
         * @param userid 用户ID
         * @return
         */
        private String getUserAccount(int userid)
        {
                String useraccount = "0";
                UserAccountHelper help = new UserAccountHelper();
                useraccount = Double.toString(help.getActtotalOfId(userid));

                return useraccount;
        }

        private int getCourseTotalNumberByRole(int relationID, int type,
                                               int roleID, int state) throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                String typeSQL = "  and C_User_Tab.type = " + type +
                        " and C_User_Tab.state='" + state + "'";
                String typeSQL1 = "  and Sec_UserRole_Tab.type = " + type;

                //int creator;
                String sqlStr;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        sqlStr = "select count(*) as count from C_User_Tab,Sec_UserRole_Tab" +
                                " where Sec_UserRole_Tab.relationID = C_User_Tab.relationID" +
                                " and Sec_UserRole_Tab.userID = C_User_Tab.userID " +
                                " and roleID = " + roleID + " and C_User_Tab.relationID = " +
                                relationID + typeSQL + typeSQL1;
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]getCourseTotalNumberByRole======================sqlStr=" +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        rs.next();

                        return rs.getInt("count");
                }
                catch (SQLException se)
                {
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

        /**
         * 返回所属课程角色的用户数，不包含待审批的课程用户
         *
         * @param relationID
         * @param type
         * @param roleID
         * @return
         * @throws CourseSysException
         */
        private int getCourseTotalNumberByRole(int relationID, int type, int roleID)
                throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                String typeSQL = "  and C_User_Tab.type = " + type +
                        " and C_User_Tab.state<>'" + CourseKeys.COURSE_USER_APPLY_STATE +
                        "'";
                String typeSQL1 = "  and Sec_UserRole_Tab.type = " + type;

                //int creator;
                String sqlStr;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        sqlStr = "select count(*) as count from C_User_Tab,Sec_UserRole_Tab" +
                                " where Sec_UserRole_Tab.relationID = C_User_Tab.relationID" +
                                " and Sec_UserRole_Tab.userID = C_User_Tab.userID " +
                                " and roleID = " + roleID + " and C_User_Tab.relationID = " +
                                relationID + typeSQL + typeSQL1;
                        LogUtil.debug("course",
                                "[CourseUserDAOImpl]getCourseTotalNumberByRole======================sqlStr=" +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        rs.next();

                        return rs.getInt("count");
                }
                catch (SQLException se)
                {
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

        protected Connection getJieFoConnection() throws CourseSysException
        {
                Connection conn = null;

                try
                {
                        conn = DBUtil.getJieFoJDBCConnection();
                }
                catch (Exception se)
                {
                        throw new CourseSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return conn;
        }

        protected void closeStatement(Statement stmt) throws CourseSysException
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
                        throw new CourseSysException(se);
                }
        }

        protected void closeConnection(Connection conn) throws CourseSysException
        {
                try
                {
                        if (conn != null)
                        {
                                conn.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseSysException(se);
                }
        }

        public static void main(String[] args) throws Exception
        {
                CourseUserDAOImpl aa = new CourseUserDAOImpl();
                System.out.println(aa.getForshenpi(324, 4, 1, 1, 100));
        }

        /**
         * 根据课程ID取该课程的所有学员的经验值(计算)
         *
         * @param courseId   课程ID
         * @param courseName 课程名称
         * @param userId     用户ID
         * @return relist,一个Map集合，放置计算好的数据
         *         Map的key说明：
         *         coursename：课程名称；loginname：登录名；name：用户名；exper：合成的经验值；userid：用户ID；courseid：课程ID。
         */
        public List getCourseUserExper(String courseId, String courseName,
                                       String userId)
        {
                List relist = null;
                int type = SecurityConstants.USER_COURSE_RELATION;
                UserForm searchUser = new UserForm();
                ArrayList courseUsersList = new ArrayList();

                //调用取课程用户
                CourseUserListModel model = searchCourseUsers(searchUser,
                        Integer.parseInt(courseId), type, 0, 9999);
                courseUsersList = model.getCourseUsers();

                if (null == courseUsersList)
                {
                        return relist;
                }

                //取经验设置
                List listExper = EvaluateManageHelper.getPointConversion();
                int coe = ((ERecordPointConversionModel) listExper.get(0)).getPoint(); //系数
                int timecoe = ((ERecordPointConversionModel) listExper.get(1)).getPoint(); //时间系数
                CourseUserModel cum = null;
                relist = new ArrayList();

                Map map = new HashMap();

                for (int i = 0; i < courseUsersList.size(); i++)
                {
                        if ((null != userId) && !userId.equals(""))
                        { //只查询当前课程给定用户ID的经验值

                                if (((CourseUserModel) courseUsersList.get(i)).getUserID() == Integer.parseInt(
                                        userId))
                                {
                                        map = getuserAccess((CourseUserModel) courseUsersList.get(i),
                                                courseId, coe, timecoe);

                                        if (null != map)
                                        {
                                                map.put("coursename", courseName);
                                                map.put("courseid", courseId);
                                                relist.add(map);

                                                return relist;
                                        }
                                }
                        }
                        else
                        {
                                //合成所有课程用户信息和经验
                                map = getuserAccess((CourseUserModel) courseUsersList.get(i),
                                        courseId, coe, timecoe);

                                if (null != map)
                                {
                                        map.put("coursename", courseName);
                                        map.put("courseid", courseId);
                                        //relist.add(map);
                                        sortListMap(relist, map);
                                }
                        }
                }

                return relist;
        }

        /**
         * 计算课程用户经验值
         *
         * @param model    用户Model
         * @param courseId 课程ID
         * @param coe      经验值系数
         * @param timecoe  累计登录时间系数
         * @return Map 整理后的用户信息集合
         */
        private Map getuserAccess(CourseUserModel model, String courseId, int coe,
                                  int timecoe)
        {
                Map maps = new HashMap();
                int moduleID = 0; //0 is course
                int certificateID = -1;
                int projectID = -1;
                int orgID = -1;
                int userId = model.getUserID();
                AccessWebImpl accessWebImpl = new AccessWebImpl();
                List accesss = accessWebImpl.getAccessListGroupUserID("/" + userId,
                        moduleID, Integer.parseInt(courseId), certificateID, projectID,
                        orgID, "", "", 0, 1);

                if ((null == accesss) || (0 == accesss.size()))
                {
                        return null;
                }

                maps.put("loginname", model.getLoginName());
                maps.put("name", model.getName());
                maps.put("userid", String.valueOf(model.getUserID()));

                int lonigcoe = coe * ((UserAccess) accesss.get(0)).getCon(); //登录次数和系数的值
                int logintime = timecoe * (((UserAccess) accesss.get(0)).getUsertimes() / 3600); //登录时间和登录系数的值

                maps.put("exper", String.valueOf(lonigcoe + logintime));

                return maps;
        }

        /**
         * 建立list按降序排列
         *
         * @param list 已经建立的结果
         * @param map  要添加的新数据
         */
        private void sortListMap(List list, Map map)
        {
                Map tmap = null;
                int k = 0; //判断是否已经添加到LIST中的开头量

                if ((null != list) && (0 != list.size()))
                {
                        for (int i = 0; i < list.size(); i++)
                        {
                                tmap = (Map) list.get(i);

                                if (Integer.parseInt((String) tmap.get("exper")) <= (Integer.parseInt(
                                        (String) map.get("exper"))))
                                {
                                        list.add(i, map);
                                        k = 1;

                                        break;
                                }
                        }

                        if (0 == k)
                        {
                                list.add(map);
                        }
                }
                else
                {
                        list.add(map);
                }
        }
}
