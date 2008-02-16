/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-7
 * Time: 13:50:12
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.dao;

import com.ulearning.ulms.admin.graduation.exceptions.GraduationAppException;
import com.ulearning.ulms.admin.graduation.form.GraduationForm;
import com.ulearning.ulms.admin.graduation.model.GraduationModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;


public class GraduationDAOImpl implements GraduationDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public void addGraduation(GraduationForm gf) throws GraduationAppException
        {
                try
                {
                        HibernateDAO.add(gf.getModel());
                        LogUtil.debug("GraduationDAO",
                                " Add a graduation======================================");
                }
                catch (ULMSSysException es)
                {
                        es.printStackTrace();
                        modifyGraduation(gf);
                }
        }

        public void modifyGraduation(GraduationForm gf)
                throws GraduationAppException
        {
                GraduationForm newGf = getGraduation(gf.getRelationID(), gf.getType(),
                        gf.getUserID());
                gf.setStartDate(newGf.getStartDate());
                gf.setGraduateDate(newGf.getGraduateDate());

                try
                {
                        LogUtil.debug("GraduationDAO",
                                " modify a graduation======================================");
                        HibernateDAO.update(gf.getModel());
                }
                catch (ULMSSysException es)
                {
                        es.printStackTrace();
                        throw new GraduationAppException(
                                "SQLException while update graduation================" + es);
                }
        }

        public void deleteGraduation(GraduationForm gf)
                throws GraduationAppException
        {
                String hql = "";
                hql = " from GraduationModel where relationID = " + gf.getRelationID() +
                        " And type = " + gf.getType() + " And userID = " + gf.getUserID();

                try
                {
                        LogUtil.debug("GraduationDAO",
                                " delete a graduation======================================");
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException es)
                {
                        es.printStackTrace();
                        throw new GraduationAppException(
                                "SQLException while update graduation==============" + es);
                }
        }

        public List getGraduationList(int RelationID, int type, int status)
                throws GraduationAppException
        {
                String hql = "";
                String statusStr = "";

                if (status != -1)
                {
                        statusStr = " And status = '" + status + "'";
                }

                List graduationList = new ArrayList();
                GraduationForm gf = null;

                hql = " from GraduationModel where relationID = " + RelationID +
                        " And type = " + type + statusStr;

                try
                {
                        LogUtil.debug("GraduationDAO",
                                " get a graduationList relationID = " + RelationID +
                                        " , type = " + type + ",status = '" + status + "'");

                        List tmList = null;
                        GraduationModel gm = null;
                        tmList = HibernateDAO.find(hql);

                        for (int i = 0; (tmList != null) && (i < tmList.size()); i++)
                        {
                                gm = (GraduationModel) tmList.get(i);
                                gf = new GraduationForm(gm);
                                graduationList.add(gf);
                        }
                }
                catch (ULMSSysException es)
                {
                        es.printStackTrace();
                        throw new GraduationAppException(
                                "SQLException while find  graduationList ==========" + es);
                }

                return graduationList;
        }

        public GraduationForm getGraduation(int RelationID, int type, int userID)
                throws GraduationAppException
        {
                String hql = "";
                hql = " from GraduationModel where relationID = " + RelationID +
                        " And type = " + type + " And userID = " + userID;

                GraduationForm gf = null;

                try
                {
                        LogUtil.debug("GraduationDAO",
                                " get a graduationList relationID = " + RelationID +
                                        " , type = " + type + ",userID = " + userID);

                        List tmList = null;
                        GraduationModel gm = null;
                        tmList = HibernateDAO.find(hql);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                gm = (GraduationModel) tmList.get(0);
                                gf = new GraduationForm(gm);
                        }
                }
                catch (ULMSSysException es)
                {
                        es.printStackTrace();
                        throw new GraduationAppException(
                                "SQLException while find  graduationList ==========" + es);
                }

                return gf;
        }

        public double getTotalGreditOrScore(int relationID, int type, int userID,
                                            int courseType) throws GraduationAppException
        {
                String strSql = "";
                double total = 0;

                if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        LogUtil.info("Graduation",
                                "[GraduationDAOImpl]===========获得学生课程的成绩!");

                        strSql = "Select Score total from TM_Score_Tab Where CourseID = " +
                                relationID + " And userID = " + userID;
                }
                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        String courseTypeStr = "";
                        int tmType = LMSConstants.LEARNING_TUTORIAL;

                        if (courseType != -1)
                        {
                                courseTypeStr = " And Cer_Course_Tab.CourseType = " +
                                        courseType;
                        }

                        LogUtil.info("Graduation",
                                "[GraduationDAOImpl]===========获得学生本证书相应学分!");

                        strSql = " Select sum(Cer_Course_Tab.Credit) total From Cer_Course_Tab , TM_Score_Tab " +
                                " Where Cer_Course_Tab.CertificateID = " + relationID +
                                " And Cer_Course_Tab.type = " + tmType +
                                " And Cer_Course_Tab.courseID = TM_Score_Tab.RelationID " +
                                " And TM_Score_Tab.userID = " + userID +
                                " And TM_Score_Tab.isPass = '1'" + courseTypeStr;
                }

                try
                {
                        LogUtil.debug("Graduation",
                                "[GraduationDAOImpl]===========strSql = " + strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        if ((rs != null) && rs.next())
                        {
                                total = rs.getInt("total");
                        }
                }
                catch (ULMSSysException es)
                {
                        es.printStackTrace();
                        throw new GraduationAppException(
                                "SQLException while find  graduationList ==========" + es);
                }
                catch (SQLException es)
                {
                        es.printStackTrace();
                        throw new GraduationAppException(
                                "SQLException while find  graduationList ==========" + es);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return total;
        }

        public boolean isDuplicate(String certNo, int relationID, int type,
                                   int userID) throws GraduationAppException
        {
                String hql = "";
                boolean isDuplicate = false;
                hql = " from GraduationModel Where (relationID != " + relationID +
                        " Or type != " + type + " Or userID != " + userID +
                        ") And certNo = '" + certNo + "'";

                try
                {
                        LogUtil.debug("Graduation",
                                "[GraduationDAOImpl]===========判断证书号是否重复!!! hql ======" + hql);
                        LogUtil.info("Graduation",
                                "[GraduationDAOImpl]===========判断证书号是否重复!!!");

                        List tmList = null;
                        tmList = HibernateDAO.find(hql);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                isDuplicate = true;
                        }
                }
                catch (ULMSSysException es)
                {
                        es.printStackTrace();
                        throw new GraduationAppException(
                                "SQLException while find  graduationList ==========" + es);
                }

                return isDuplicate;
        }

        /**
         * 根据用户登录名搜索证书
         *
         * @param loginName
         * @return
         * @throws GraduationAppException
         */
        public List searchCert(String loginName, String userName)
                throws GraduationAppException
        {
                List userList = new ArrayList();
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "select g.relationID,g.userID,g.certNo,g.startDate,g.graduateDate," +
                        "u.loginName,u.name,u.catalogID,o.orgname " +
                        "from C_GRADUATION_TAB g,u_user_tab u,tm_org_tab o " +
                        "where g.userID=u.userID and u.catalogID=o.orgID ";

                if ((loginName != null) && !loginName.equals(""))
                {
                        sql += (" and u.loginName='" + loginName + "'");
                }

                if ((userName != null) && !userName.equals(""))
                {
                        sql += (" and u.name='" + userName + "'");
                }

                System.out.println("sql = " + sql);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);

                        while (rs.next())
                        {
                                GraduationForm gf = new GraduationForm();
                                gf.setRelationID(rs.getInt("relationID"));
                                gf.setUserID(rs.getInt("userID"));
                                gf.setCertNo(rs.getString("certNo"));
                                gf.setStartDate(rs.getString("startDate"));
                                gf.setGraduateDate(rs.getString("graduateDate"));
                                gf.setLoginName(rs.getString("loginName"));
                                gf.setStuName(rs.getString("name"));
                                gf.setOrgID(rs.getInt("catalogID"));
                                gf.setOrgName(rs.getString("orgname"));
                                userList.add(gf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new GraduationAppException();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return userList;
        }
}
