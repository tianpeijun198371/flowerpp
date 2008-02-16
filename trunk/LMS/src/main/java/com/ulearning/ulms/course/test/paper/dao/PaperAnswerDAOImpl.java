/**
 * PaperAnswerDAOImpl.java.
 * User: huangsb  Date: 2004-6-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperAnswerForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class PaperAnswerDAOImpl implements PaperAnswerDAO
{
        Connection dbConnection = null;
        Statement stmt = null;
        ResultSet rs = null;

        public void addPaperAnswer(PaperAnswerForm paperAnswerForm)
                throws PaperDAOSysException
        {
                try
                {
                        HibernateDAO.add(paperAnswerForm.getAnswerModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PaperDAOSysException(
                                "addPaperAnswer(PaperAnswerForm paperAnswerForm) method" + e);
                }
        }

        public void updatePaperAnswer(PaperAnswerForm paperAnswerForm)
                throws PaperDAOSysException
        {
                try
                {
                        HibernateDAO.update(paperAnswerForm.getAnswerModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PaperDAOSysException(
                                "addPaperAnswer(PaperAnswerForm paperAnswerForm) method" + e);
                }
        }

        public void removePaperAnswer(int answerID) throws PaperDAOSysException
        {
        }

        public PaperAnswerForm getPaperAnswer(int userID, int paperID,
                                              int questionID) throws PaperDAOSysException
        {
                PaperAnswerForm pf = null;

                String sqlStr = "select * from T_Answer_Tab where UserID=" + userID +
                        " and QuestionID = " + questionID + " and PaperID= " + paperID;

                try
                {
                        LogUtil.debug("system",
                                "[PaperAnswerDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                pf = convertRs2Form(rs);
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperAnswer; Serial = " + paperID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperAnswer; Serial = " + paperID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return pf;
        }

        public int getPaperAnswer(int userID, int paperID)
                throws PaperDAOSysException
        {
                int count = 0;
                String sqlStr = "select DISTINCT userID,paperID from T_Answer_Tab where UserID=" +
                        userID + " and PaperID= " + paperID;

                try
                {
                        LogUtil.debug("system",
                                "[PaperAnswerDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                count = rs.getInt("userID");
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperAnswer; Serial = " + paperID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperAnswer; Serial = " + paperID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return count;
        }

        public int getPaperAnswerTimes(int userID, int paperID)
                throws PaperDAOSysException
        {
                int count = 0;
                String sqlStr = "select examTimes,userID,paperID from T_Answer_Tab where UserID=" +
                        userID + " and PaperID= " + paperID + " order by examTimes desc";

                try
                {
                        LogUtil.debug("system",
                                "[PaperAnswerDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                count = Integer.parseInt(rs.getString("examTimes"));
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperAnswer; Serial = " + paperID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperAnswer; Serial = " + paperID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return count;
        }

        /**
         * 综合查询
         *
         * @param userID     -1 忽略此条件
         * @param paperID    -1 忽略此条件
         * @param questionID -1 忽略此条件
         * @param type       -1 忽略此条件
         * @param grade      -2 忽略此条件
         * @param orderType  null 忽略此条件
         * @return
         * @throws PaperDAOSysException
         */
        public List getPaperUserAnswer(int userID, int paperID, int questionID,
                                       int type, float grade, String orderType) throws PaperDAOSysException
        {
                ArrayList list = new ArrayList();
                String sqlStr = "select * from T_Answer_Tab where 1=1";

                if (userID != -1)
                {
                        sqlStr += (" and UserID=" + userID);
                }

                if (paperID != -1)
                {
                        sqlStr += (" and paperID=" + paperID);
                }

                if (questionID != -1)
                {
                        sqlStr += (" and questionID=" + questionID);
                }

                if (type != -1)
                {
                        sqlStr += (" and type=" + type);
                }

                if (grade != -2)
                {
                        sqlStr += (" and grade=" + grade);
                }

                if (orderType != null)
                {
                        sqlStr += orderType;
                }

                try
                {
                        LogUtil.debug("system",
                                "[PaperAnswerDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        PaperAnswerForm paf = null;

                        while (rs.next())
                        {
                                paf = convertRs2Form(rs);
                                list.add(paf);
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperAnswer; Serial = " + paperID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paperAnswer; Serial = " + paperID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return list;
        }

        public float getResearchAnswer(int questionID, char answer_select)
                throws PaperDAOSysException
        {
                float rate = 0;
                ResultSet rs = null;
                String sqlStr1 = "select answer from T_Answer_Tab where questionID=" +
                        questionID;
                String sqlStr2 = "select count(*) from T_Answer_Tab where questionID=" +
                        questionID + " and Answer like '%" + answer_select + "%'";

                try
                {
                        float count1 = 0;
                        float answer = 0;
                        LogUtil.debug("system",
                                "[ResearchAnswerDAOImpl]====================the sql string is : " +
                                        sqlStr1);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr1);

                        while (rs.next())
                        {
                                count1 += (StringUtils.trimToEmpty(rs.getString("answer"))).length();
                        }

                        LogUtil.debug("system",
                                "[ResearchAnswerDAOImpl]====================the sql string is : " +
                                        sqlStr2);
                        rs = stmt.executeQuery(sqlStr2);

                        if (rs.next())
                        {
                                answer = rs.getInt(1);
                        }

                        if (count1 == 0)
                        {
                                rate = 0;
                        }
                        else
                        {
                                rate = (answer / count1) * 100;
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "Research; Serial = " + questionID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "Research; Serial = " + questionID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return rate;
        }

        public float getResearchMulAnswer(int questionID, char answer_select)
                throws PaperDAOSysException
        {
                float rate = 0;
                float count1 = 0;
                float answer = 0;
                ResultSet rs = null;

                String sqlStr1 = "select count(*) from T_Answer_Tab where questionID=" +
                        questionID;

                String sqlStr2 = "select count(*) from T_Answer_Tab where questionID=" +
                        questionID + " and Answer like '%" + answer_select + "%'";

                //String sqlStr2 = "select count(*) from T_Answer_Tab where questionID=" + questionID;
                try
                {
                        LogUtil.debug("system",
                                "[ResearchAnswerDAOImpl]====================the sql string is : " +
                                        sqlStr1);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr1);

                        //System.out.println(");
                        while (rs.next())
                        {
                                //if(rs.getString("answer") != null)
                                // count1 = (rs.getString("answer")).length();
                                count1 = rs.getInt(1);
                        }

                        LogUtil.debug("system",
                                "[ResearchAnswerDAOImpl]====================the sql string is : " +
                                        sqlStr2);
                        rs = stmt.executeQuery(sqlStr2);

                        if (rs.next())
                        {
                                answer = rs.getInt(1);
                        }

                        if (count1 == 0)
                        {
                                rate = 0;
                        }
                        else
                        {
                                rate = (answer / count1) * 100;
                        }

                        //System.out.println("count1======="+count1);
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "Research; Serial = " + questionID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "Research; Serial = " + questionID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return rate;
        }

        public List getPaperAnswerList(int paperID, int type)
                throws PaperDAOSysException
        {
                PaperAnswerForm pqf = null;
                ArrayList PaperQuestList = new ArrayList();
                String sqlStr = "select * from T_Answer_Tab where PaperID = " +
                        paperID + " and type= " + type;

                try
                {
                        LogUtil.debug("system",
                                "[PaperAnswerDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
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
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return PaperQuestList;
        }

        /**
         * Public a method for the number of paperanswer
         *
         * @return the wanted number
         * @ name = getPaperAnswerCount
         */
        public int getPaperAnswerCount(int courseID, int questionID)
                throws PaperDAOSysException
        {
                int result = 0;
                String sqlStr = "select count(*) as questionCount from T_Answer_Tab where questionID=" +
                        questionID;

                try
                {
                        LogUtil.debug("system",
                                "[PaperAnswerDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                result = rs.getInt("questionCount");
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; questionID=" + questionID + ":\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; questionID = " + questionID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return result;
        }

        /**
         * Public a method for the number of paperanswer
         *
         * @return the wanted number
         * @ name = getTheAnswerCount
         */
        public int getTheAnswerCount(int questionID, char answer_select)
                throws PaperDAOSysException
        {
                ResultSet rs = null;
                int answer1 = 0;
                String sqlStr = "select count(*) from T_Answer_Tab where questionID=" +
                        questionID + " and Answer like '%" + answer_select + "%'";

                try
                {
                        LogUtil.debug("system",
                                "[PaperAnswerDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                answer1 = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; questionID=" + questionID + ":\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; questionID = " + questionID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return answer1;
        }

        /**
         * Public a method for the number of paperanswer
         *
         * @return the wanted number
         * @ name = getAllAnswerCount
         */
        public int getAllAnswerCount(int questionID, char answer_select)
                throws PaperDAOSysException
        {
                ResultSet rs = null;
                int count1 = 0;
                String sqlStr = "select answer from T_Answer_Tab where questionID=" +
                        questionID;

                try
                {
                        LogUtil.debug("system",
                                "[PaperAnswerDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                count1 += (StringUtils.trimToEmpty(rs.getString("answer"))).length();
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; questionID=" + questionID + ":\n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while select " +
                                "PaperQuestion; questionID = " + questionID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return count1;
        }

        /**
         * Convert the resultSet object to planForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted planForm
         */
        private PaperAnswerForm convertRs2Form(ResultSet rs)
        {
                PaperAnswerForm pf = new PaperAnswerForm();
                int rsIndex = 1;

                try
                {
                        pf.setAnswerID(rs.getInt(rsIndex++));
                        pf.setUserID(rs.getInt(rsIndex++));
                        pf.setPaperID(rs.getInt(rsIndex++));
                        pf.setQuestionID(rs.getInt(rsIndex++));
                        pf.setType(rs.getInt(rsIndex++));
                        pf.setAnswer(rs.getString(rsIndex++));
                        pf.setGrade(rs.getFloat(rsIndex++));
                        pf.setExamtimes(rs.getInt(rsIndex++));
                        pf.setRemark1(rs.getString(rsIndex++));
                        pf.setRemark2(rs.getString(rsIndex++));
                        pf.setRemark3(rs.getString(rsIndex++));
                        pf.setRemark4(rs.getString(rsIndex++));
                        pf.setRemark5(rs.getString(rsIndex++));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return pf;
        }

        protected void closeConnection(Connection dbConnection)
                throws PaperDAOSysException
        {
                try
                {
                        if (dbConnection != null)
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("closeConnection" + se);
                }
        }

        protected void closeResultSet(ResultSet result) throws PaperDAOSysException
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
                        throw new PaperDAOSysException("closeResultSet" + se);
                }
        }

        protected void closeStatement(Statement stmt) throws PaperDAOSysException
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
                        throw new PaperDAOSysException("closeStatement" + se);
                }
        }
}
