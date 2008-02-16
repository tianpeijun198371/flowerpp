/**
 * MatchDAOImpl.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.dao;

import com.ulearning.ulms.common.match.exceptions.MatchSysException;
import com.ulearning.ulms.common.match.model.JobModel;
import com.ulearning.ulms.common.match.model.MatchModel;
import com.ulearning.ulms.common.match.util.MatchConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class MatchDAOImpl implements MatchDAO
{
        /**
         * get  the match.
         * type
         * ==0:master
         * ==1:course
         * ==2:certificate
         * ==3:project
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public MatchModel get(int matchID) throws MatchSysException
        {
                MatchModel mm = null;

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select * from C_Match_Tab where matchID=" +
                                matchID;

                        LogUtil.debug("common",
                                "[MatchDAOImpl]get ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        if (rs.next())
                        {
                                mm = new MatchModel(rs.getInt("matchID"),
                                        StringUtil.nullToStr(rs.getString("matchKey")),
                                        StringUtil.nullToStr(rs.getString("matchKeyID")), "",
                                        "", 0, 0,
                                        StringUtil.nullToStr(rs.getString("defaultOperator")),
                                        StringUtil.nullToStr(rs.getString("defaultMatchValue")),
                                        0);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return mm;
        }

        /**
         * get  the relational match list
         * type
         * ==0:master
         * ==1:course
         * ==2:certificate
         * ==3:project
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public List get(int type, int relationID) throws MatchSysException
        {
                List list = new ArrayList();
                MatchModel mm = null;

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select * from C_Match_View where relationID=" +
                                relationID + " and type='" + type + "'";

                        LogUtil.debug("common",
                                "[MatchDAOImpl]get1 ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                mm = new MatchModel(rs.getInt("matchID"),
                                        StringUtil.nullToStr(rs.getString("matchKey")),
                                        StringUtil.nullToStr(rs.getString("matchKeyID")),
                                        StringUtil.nullToStr(rs.getString("operator")),
                                        StringUtil.nullToStr(rs.getString("matchValue")),
                                        rs.getInt("relationID"),
                                        StringUtil.parseInt(rs.getString("type")),
                                        StringUtil.nullToStr(rs.getString("defaultOperator")),
                                        StringUtil.nullToStr(rs.getString("defaultMatchValue")),
                                        rs.getInt("AspID"));
                                list.add(mm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return list;
        }

        /**
         * get  all of the relational job list
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public List getAllJobs() throws MatchSysException
        {
                List list = new ArrayList();
                List mml = null;
                MatchModel mm = null;
                JobModel jm = null;

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select DISTINCT TYPE,RelationID,AspID from C_Match_View" +
                                " ORDER BY RelationID";

                        LogUtil.debug("common",
                                "[MatchDAOImpl] getAllJobs==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                jm = new JobModel(rs.getInt("RelationID"),
                                        StringUtil.parseInt(rs.getString("type")),
                                        rs.getInt("AspID"));
                                list.add(jm);
                        }

                        for (int i = 0; i < list.size(); i++)
                        {
                                jm = (JobModel) list.get(i);

                                mml = new ArrayList();
                                jm.setMms(mml);

                                queryStr = "select * from C_Match_View where type='" +
                                        jm.getType() + "' and RelationID=" + jm.getRelationID();
                                LogUtil.debug("common",
                                        "[MatchDAOImpl]getAllJobs ==========queryStr1 = " +
                                                queryStr);

                                rs = stmt.executeQuery(queryStr);

                                while (rs.next())
                                {
                                        mm = new MatchModel(rs.getInt("MatchID"),
                                                StringUtil.nullToStr(rs.getString("MatchKey")),
                                                StringUtil.nullToStr(rs.getString("MatchKeyID")),
                                                StringUtil.nullToStr(rs.getString("Operator")),
                                                StringUtil.nullToStr(rs.getString("MatchValue")),
                                                jm.getRelationID(), jm.getType(),
                                                StringUtil.nullToStr(rs.getString("DefaultOperator")),
                                                StringUtil.nullToStr(rs.getString(
                                                        "DefaultMatchValue")), rs.getInt("AspID"));
                                        mml.add(mm);
                                }
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return list;
        }

        /**
         * get   the relational job list according to the specified relationID,type
         *
         * @param relationID
         * @param type
         * @return
         * @throws MatchSysException
         */
        public List getAllJobs(int relationID, int type) throws MatchSysException
        {
                List list = new ArrayList();
                List mml = null;
                MatchModel mm = null;
                JobModel jm = null;

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select DISTINCT TYPE,RelationID,AspID from C_Match_View" +
                                " where relationID=" + relationID + " and type='" + type + "'";

                        LogUtil.debug("common",
                                "[MatchDAOImpl] getAllJobs==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        if (rs.next())
                        {
                                jm = new JobModel(rs.getInt("RelationID"),
                                        StringUtil.parseInt(rs.getString("type")),
                                        rs.getInt("AspID"));
                                list.add(jm);
                        }

                        for (int i = 0; i < list.size(); i++)
                        {
                                jm = (JobModel) list.get(i);

                                mml = new ArrayList();
                                jm.setMms(mml);

                                queryStr = "select * from C_Match_View where type='" +
                                        jm.getType() + "' and RelationID=" + jm.getRelationID();
                                LogUtil.debug("common",
                                        "[MatchDAOImpl]getAllJobs ==========queryStr1 = " +
                                                queryStr);

                                rs = stmt.executeQuery(queryStr);

                                while (rs.next())
                                {
                                        mm = new MatchModel(rs.getInt("MatchID"),
                                                StringUtil.nullToStr(rs.getString("MatchKey")),
                                                StringUtil.nullToStr(rs.getString("MatchKeyID")),
                                                StringUtil.nullToStr(rs.getString("Operator")),
                                                StringUtil.nullToStr(rs.getString("MatchValue")),
                                                jm.getRelationID(), jm.getType(),
                                                StringUtil.nullToStr(rs.getString("DefaultOperator")),
                                                StringUtil.nullToStr(rs.getString(
                                                        "DefaultMatchValue")), rs.getInt("AspID"));
                                        mml.add(mm);
                                }
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return list;
        }

        /**
         * judge if the match exisited.
         *
         * @throws MatchSysException
         */
        public boolean isExisit(int matchID, int relationID, int type)
                throws MatchSysException
        {
                String sql_str;

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //prepare the sql string to insert
                        sql_str = "select matchID from  " +
                                "C_AutoMatch_Tab where matchID=" + matchID +
                                " and relationID=" + relationID + " and type='" + type + "'";

                        LogUtil.debug("common", "[MatchDAOImpl]isExisit sql_str=" +
                                sql_str);

                        rs = stmt.executeQuery(sql_str);

                        return rs.next();
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * delete a match
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public void delete(int matchID, int relationID, int type)
                throws MatchSysException
        {
                String sql_str;

                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //prepare the sql string to insert
                        sql_str = "delete from  " + "C_AutoMatch_Tab where matchID=" +
                                matchID + " and relationID=" + relationID + " and type='" +
                                type + "'";

                        LogUtil.debug("common", "[MatchDAOImpl]delete sql_str=" + sql_str);

                        int totalCountDeleted = stmt.executeUpdate(sql_str);
                        LogUtil.debug("common",
                                "[MatchDAOImpl]delete " + "一共删除 " + totalCountDeleted + "行");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * delete a match
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public void deleteAll(int relationID, int type) throws MatchSysException
        {
                String sql_str;

                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //prepare the sql string to insert
                        sql_str = "delete from  " + "C_AutoMatch_Tab where relationID=" +
                                relationID + " and type='" + type + "'";

                        LogUtil.debug("common", "[MatchDAOImpl]deleteAll sql_str=" +
                                sql_str);

                        int totalCountDeleted = stmt.executeUpdate(sql_str);
                        LogUtil.debug("common",
                                "[MatchDAOImpl]deleteAll " + "一共删除 " + totalCountDeleted + "行");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * insert a match.
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public void insert(MatchModel mm) throws MatchSysException
        {
                String sql_str;

                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //prepare the sql string to insert
                        sql_str = "insert into " + "C_AutoMatch_Tab" +
                                "(type,RelationID,MatchID,MatchValue,Operator,AspID) " +
                                "values(" + mm.getType() + "," + mm.getRelationID() + "," +
                                mm.getMatchID() + ",'" + mm.getMatchValue() + "','" +
                                mm.getOperator() + "'," + mm.getAspID() + ")";

                        LogUtil.debug("common", "[MatchDAOImpl]insert sql_str=" + sql_str);

                        int totalCountInserted = stmt.executeUpdate(sql_str);
                        LogUtil.debug("common",
                                "[MatchDAOImpl]sql_str " + "一共插入 " + totalCountInserted + "行");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * update  a match.
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public void update(MatchModel mm) throws MatchSysException
        {
                String sql_str;

                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //prepare the sql string to insert
                        sql_str = "update " + "C_AutoMatch_Tab " + "set MatchValue='" +
                                mm.getMatchValue() + "',Operator='" + mm.getOperator() +
                                "' where RelationID=" + mm.getRelationID() + " and type='" +
                                mm.getType() + "' and MatchID=" + mm.getMatchID();

                        LogUtil.debug("common", "[MatchDAOImpl]update sql_str=" + sql_str);

                        stmt.executeUpdate(sql_str);
                        LogUtil.debug("common", "[MatchDAOImpl]update " + "update ok!");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * get  all of the match list
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public List getAllMatches() throws MatchSysException
        {
                List mml = new ArrayList();
                MatchModel mm = null;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select * from C_Match_Tab";
                        LogUtil.debug("common",
                                "[MatchDAOImpl]getAllMatches ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                LogUtil.debug("common",
                                        "[MatchDAOImpl]getAllMatches ==========MatchKey = " +
                                                StringUtil.nullToStr(rs.getString("MatchKey")));

                                mm = new MatchModel(rs.getInt("matchID"),
                                        StringUtil.nullToStr(rs.getString("matchKey")),
                                        StringUtil.nullToStr(rs.getString("matchKeyID")), "",
                                        "", 0, 0,
                                        StringUtil.nullToStr(rs.getString("defaultOperator")),
                                        StringUtil.nullToStr(rs.getString("defaultMatchValue")),
                                        0);
                                mml.add(mm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return mml;
        }

        /*
        * get relational Users.
        */
        public List getMatchUsers(JobModel jm) throws MatchSysException
        {
                List listUsers = new ArrayList();
                List listMatches = jm.getMms();

                //假如有部门匹配,这样做.因为部门匹配需要递归子部门下的用户
                String isCD = isContainDepartMatch(listMatches);

                if (isCD != null)
                {
                        LogUtil.info("common", "[MacthDAOImpl]getMatchUsers--有部门匹配!");

                        int orgID = OrganHelper.getOrgID(isCD);
                        LogUtil.info("common", "[MacthDAOImpl]getMatchUsers--orgID=" +
                                orgID);

                        return getMatchUsersFromDepart(orgID, listMatches);
                }

                LogUtil.info("common", "[MacthDAOImpl]getMatchUsers--没有部门匹配!");

                MatchModel mm = null;

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                //getMatchUsersFromDepart
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //init the sql condition
                        String condition = " where AspID=" + jm.getAspID();
                        String operator = null;

                        //if no match,return no users
                        if (listMatches.isEmpty())
                        {
                                return listUsers;
                        }

                        String mv = null;
                        LogUtil.info("common",
                                "[MacthDAOImpl]getMatchUsers--listMatches.size()=" +
                                        listMatches.size());

                        for (int i = 0; i < listMatches.size(); i++)
                        {
                                mm = (MatchModel) listMatches.get(i);
                                operator = mm.getOperator();
                                condition += " and ";

                                if (operator.equals("like"))
                                {
                                        condition += ("(" + mm.getMatchKeyID() + " like '%" +
                                                mm.getMatchValue() + "%'" + ")");
                                }
                                else
                                {
                                        if (mm.getMatchID() == MatchConstants.MATCH_SEX)
                                        {
                                                mv = mm.getMatchValue();

                                                if ((mv.equals("男")) || mv.equals("male"))
                                                {
                                                        mm.setMatchValue(MatchConstants.MATCH_SEX_MALE);
                                                }
                                                else
                                                {
                                                        mm.setMatchValue(MatchConstants.MATCH_SEX_FEMALE);
                                                }
                                        }

                                        condition += (mm.getMatchKeyID() + mm.getOperator() + "'" +
                                                mm.getMatchValue() + "' ");
                                }
                        }

                        String queryStr = "select distinct userID from TM_User_View" +
                                condition;

                        LogUtil.debug("common",
                                "[MatchDAOImpl]getMatchUsers ==========queryStr user= " +
                                        queryStr);

                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                listUsers.add(new Integer(rs.getInt("userID")));
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new MatchSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return listUsers;
        }

        /**
         * 判断是否有部门匹配
         *
         * @return if have,return the depart ID,else return null.
         */
        private String isContainDepartMatch(List listMatches)
        {
                MatchModel mm = null;

                for (int i = 0; i < listMatches.size(); i++)
                {
                        mm = (MatchModel) listMatches.get(i);

                        if (mm.getMatchID() == MatchConstants.MATCH_DEPART)
                        {
                                return mm.getMatchValue();
                        }
                }

                return null;
        }

        /**
         * get relational Users FromDepart
         * Just so, Because fetching users from depart need recursive.
         *
         * @param orgID
         * @return all users
         * @throws MatchSysException
         */
        private List getMatchUsersFromDepart(int orgID, List listMatches)
        {
                List listUsers = new ArrayList();

                List listUsers1 = UserHelper.getAllOrganUser(orgID);
                LogUtil.info("common",
                        "[MacthDAOImpl]getMatchUsersFromDepart--listUsers1.size()=" +
                                listUsers1.size());

                for (int i = 0; i < listUsers1.size(); i++)
                {
                        UserForm uf = (UserForm) listUsers1.get(i);

                        if (isMatchFromDepart(uf, listMatches))
                        {
                                LogUtil.info("common",
                                        "[MacthDAOImpl]getMatchUsersFromDepart--userID=" +
                                                uf.getUserID() + " match other matches!,add!");
                                listUsers.add(new Integer(uf.getUserID()));
                        }

                        LogUtil.info("common",
                                "[MacthDAOImpl]getMatchUsersFromDepart--userID=" +
                                        uf.getUserID() + " not match other matches!");
                }

                LogUtil.info("common",
                        "[MacthDAOImpl]getMatchUsersFromDepart--listUsers.size()=" +
                                listUsers.size());

                return listUsers;
        }

        /**
         * filter
         *
         * @param uf
         * @param listMatches
         * @return
         */
        private boolean isMatchFromDepart(UserForm uf, List listMatches)
        {
                MatchModel mm = null;
                List filterList = null;
                String mv = null;
                boolean isRoleMatch = true;
                boolean isSexMatch = true;
                boolean isEduLevelMatch = true;
                boolean isPositionMatch = true;

                for (int i = 0; i < listMatches.size(); i++)
                {
                        mm = (MatchModel) listMatches.get(i);

                        if (mm.getMatchID() == MatchConstants.MATCH_SEX)
                        { //系统内之匹配规则-'性别'
                                mv = mm.getMatchValue();

                                if ((mv.equals("男")) || mv.equals("male"))
                                {
                                        mv = MatchConstants.MATCH_SEX_MALE;
                                }
                                else
                                {
                                        mv = MatchConstants.MATCH_SEX_FEMALE;
                                }

                                if (!uf.getSex().equals(mv))
                                {
                                        isSexMatch = false;
                                }
                        }
                        else if (mm.getMatchID() == MatchConstants.MATCH_ROLE)
                        { //系统内之匹配规则-'角色'
                                mv = mm.getMatchValue();
                                //todo
                                isRoleMatch = true;
                        }
                        else if (mm.getMatchID() == MatchConstants.MATCH_POSITION)
                        { //系统内之匹配规则-''职务''
                                mv = mm.getMatchValue();

                                if (!uf.getDesc1().equals(mv))
                                {
                                        isPositionMatch = false;
                                }
                        }
                        else if (mm.getMatchID() == MatchConstants.MATCH_EDULEVEL)
                        { //系统内之匹配规则-'教育程度'
                                mv = mm.getMatchValue();

                                if (!uf.getEduLevel().equals(mv))
                                {
                                        isEduLevelMatch = false;
                                }
                        }
                        else
                        { //系统内之匹配规则-'部门'
                        }
                }

                return isRoleMatch && isSexMatch && isEduLevelMatch && isPositionMatch;
        }
}
