/**
 * PaperQuestionDAOImpl.java.
 * User: huangsb  Date: 2004-6-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;
import com.ulearning.ulms.course.test.paper.model.PaperQuestionModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class PaperQuestionDAOImpl implements PaperQuestionDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public void addPaperQuestion(PaperQuestionForm paperQuestionForm)
                throws PaperDAOSysException
        {
                try
                {
                        HibernateDAO.add(paperQuestionForm.getPaperQuestionModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PaperDAOSysException(
                                "addPaperAnswer(PaperAnswerForm paperAnswerForm) method" + e);
                }
        }

        public void removePaperQuestion(int paperID) throws PaperDAOSysException
        {
                String hql = " from PaperQuestionModel a where a.comp_id.paperid = " +
                        paperID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PaperDAOSysException(
                                "ULMSSysException while removePaperQuestion(int paperID) method" +
                                        paperID);
                }
        }

        public void removePaperQuestion(int paperID, String paperQuestionID)
                throws PaperDAOSysException
        {
                String sqlStr = "delete from T_PaperQuestion_Tab where paperID=" +
                        paperID + " and QuestionID = " + paperQuestionID;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperQuestion; Serial = " + paperQuestionID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        public void removePaperQuestion(int paperID, String[] paperQuestionID)
                throws PaperDAOSysException
        {
                String sqlStr = "delete from T_Question_Tab where paperID= " + paperID;
                String condition = "";

                try
                {
                        for (int i = 0; i < paperQuestionID.length; i++)
                        {
                                condition = condition + " and QuestionID=" +
                                        paperQuestionID[i];
                        }

                        if (!condition.equals(""))
                        {
                                condition = condition.substring(4);
                                sqlStr = sqlStr + condition;

                                try
                                {
                                        conn = DBUtil.getConnection();
                                        stmt = conn.createStatement();
                                        rs = stmt.executeQuery(sqlStr);
                                }
                                catch (ULMSSysException se)
                                {
                                        throw new PaperDAOSysException(
                                                "SQLException while updating " +
                                                        "paperQuestion; sqlStr = " + sqlStr + " :\n" + se);
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        public int getPaperQuestion(int paperID, String paperQuestionID)
                throws PaperDAOSysException
        {
                int questionID = 0;
                String sqlStr = "select * from T_PaperQuestion_Tab where QuestionID = " +
                        paperQuestionID + " and PaperID= " + paperID;

                try
                {
                        LogUtil.debug("system",
                                "[PaperDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                questionID = rs.getInt("QuestionID");
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperQuestion; Serial = " + paperQuestionID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperQuestionID; Serial = " + paperQuestionID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return questionID;
        }

        public int getPaperScore(int paperID) throws PaperDAOSysException
        {
                int allscore = 0;
                List tmList = null;
                String hql = "from PaperQuestionModel a where a.comp_id.paperid = " +
                        paperID;

                try
                {
                        tmList = HibernateDAO.find(hql);

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                PaperQuestionModel aa = (PaperQuestionModel) tmList.get(i);
                                allscore += aa.getScore();
                        }
                }
                catch (Exception se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; paperID=" + paperID + ":\n" + se);
                }
                finally
                {
                }

                return allscore;
        }

        public List getPaperQuestionList(int paperID, int type)
                throws PaperDAOSysException
        {
                PaperQuestionForm pqf = null;
                ArrayList PaperQuestList = new ArrayList();
                String sqlStr = "select * from T_PaperQuestion_Tab where PaperID = " +
                        paperID + " and type= '" + type + "'";

                try
                {
                        LogUtil.debug("system",
                                "[PaperQuestionDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                pqf = convertRs2Form(rs);
                                PaperQuestList.add(pqf);
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; paperID=" + paperID + ":\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; paperID = " + paperID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return PaperQuestList;
        }

        public List getPaperQuestionList(int paperID) throws PaperDAOSysException
        {
                ArrayList PaperQuestList = new ArrayList();
                String sqlStr = "select * from T_PaperQuestion_Tab where PaperID = " +
                        paperID;

                try
                {
                        // LogUtil.debug("system", "[PaperQuestionDAOImpl]====================the sql string is : " + sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                String questionID = rs.getString("QuestionID");
                                //pqf = convertRs2Form(rs);
                                PaperQuestList.add(questionID);
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; paperID=" + paperID + ":\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; paperID = " + paperID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return PaperQuestList;
        }

        public PaperQuestionForm getPaperQuestion(int paperID, int paperQuestionID)
                throws PaperDAOSysException
        {
                PaperQuestionForm pf = null;

                String sqlStr = "select * from T_PaperQuestion_Tab where QuestionID = " +
                        paperQuestionID + " and PaperID= " + paperID;

                try
                {
                        LogUtil.debug("system",
                                "[PaperDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                pf = convertRs2Form(rs);
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperQuestion; Serial = " + paperQuestionID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperQuestionID; Serial = " + paperQuestionID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return pf;
        }

        //判断试卷里是否有主观题
        public int getIsSubjectPaperQuestion(int paperID)
                throws PaperDAOSysException
        {
                int count = 0;
                String sqlStr = "select count(*) as count from t_paperquestion_tab where paperID= " +
                        paperID + " and (type=3 or type=5)";

                try
                {
                        LogUtil.debug("test",
                                "[PaperQuestionDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                count = rs.getInt("count");
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperQuestion; Serial = " + paperID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperQuestionID; Serial = " + paperID + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return count;
        }

        /**
         * Convert the resultSet object to planForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted planForm
         */
        private PaperQuestionForm convertRs2Form(ResultSet rs)
        {
                PaperQuestionForm pf = new PaperQuestionForm();
                int rsIndex = 1;

                try
                {
                        pf.setPaperID(rs.getInt(rsIndex++));
                        pf.setQuestionID(rs.getInt(rsIndex++));
                        pf.setScore(rs.getFloat(rsIndex++));
                        pf.setType(rs.getInt(rsIndex++));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return pf;
        }
}
