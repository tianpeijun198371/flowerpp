/**
 * CodeMaintainDAOImpl.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.dao;

import com.ulearning.ulms.common.form.PeizForm;
import com.ulearning.ulms.common.form.UpdatePeizForm;
import com.ulearning.ulms.common.model.CodeItem;
import com.ulearning.ulms.common.model.CodeModel;
import com.ulearning.ulms.common.model.Codes;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class CodeMaintainDAOImpl implements CodeMaintainDAO
{
        /*
        * get the score type'value.
        */
        public String getScoreType(int scoreType) throws ULMSSysException
        {
                String codeName = null;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select ScoreTypeValue from Code_ScoreType_Tab" +
                                " WHERE scoreType=" + scoreType;
                        LogUtil.debug("course",
                                "[CodeMaintainDAOImpl] ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        if (rs.next())
                        {
                                codeName = rs.getString("ScoreTypeValue");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ULMSSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return codeName;
        }

        /*
        * get the list of score type
        */
        public List getScoreTypes() throws ULMSSysException
        {
                String codeName;
                int codeID;
                int defaultPassValue;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CodeModel cm = null;
                List list = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select ScoreType,ScoreTypeValue,defaultPassValue from Code_ScoreType_Tab" +
                                " order by ScoreType";
                        LogUtil.debug("course",
                                "[CodeMaintainDAOImpl] ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                codeName = rs.getString("ScoreTypeValue");

                                codeID = rs.getInt("ScoreType");

                                defaultPassValue = rs.getInt("defaultPassValue");
                                cm = new CodeModel(codeID, codeName, defaultPassValue);
                                list.add(cm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ULMSSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return list;
        }

        /*
        * get the value of the SpecScore
        */
        public String getSpecScore(int specScore, int scoreType)
                throws ULMSSysException
        {
                String codeName = null;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select SpecScoreValue from Code_SpecScore_Tab" +
                                " WHERE specScore=" + specScore + " and scoreType=" +
                                scoreType;
                        LogUtil.debug("course",
                                "[CodeMaintainDAOImpl] ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        if (rs.next())
                        {
                                codeName = rs.getString("SpecScoreValue");
                        }
                        else
                        {
                                return String.valueOf(specScore);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ULMSSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return codeName;
        }

        /*
        * get the list of the scoreType
        */
        public List getSpecScores(int scoreType) throws ULMSSysException
        {
                String codeName;
                int codeID;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CodeModel cm = null;
                List list = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select SpecScoreValue,SpecScore from Code_SpecScore_Tab" +
                                " where scoreType=" + scoreType + " order by SpecScore desc";
                        LogUtil.debug("course",
                                "[CodeMaintainDAOImpl] ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                codeName = rs.getString("SpecScoreValue");

                                codeID = rs.getInt("SpecScore");
                                cm = new CodeModel(codeID, codeName);
                                list.add(cm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ULMSSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return list;
        }

        /*
        * get the TeachMode
        */
        public String getTeachMode(int teachModeID) throws ULMSSysException
        {
                String codeName = null;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select Name from Code_TeachMode_Tab" +
                                " WHERE teachModeID=" + teachModeID;
                        LogUtil.debug("course",
                                "[CodeMaintainDAOImpl] ==========queryStr1 = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        if (rs.next())
                        {
                                codeName = rs.getString("Name");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ULMSSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return codeName;
        }

        /*
        * get the TeachMode list.
        */
        public List getTeachModes() throws ULMSSysException
        {
                String codeName;
                int codeID;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CodeModel cm = null;
                List list = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select teachModeID,Name from Code_TeachMode_Tab" +
                                " order by teachModeID";
                        LogUtil.debug("course",
                                "[CodeMaintainDAOImpl] ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                codeName = rs.getString("Name");

                                codeID = rs.getInt("teachModeID");
                                cm = new CodeModel(codeID, codeName);
                                list.add(cm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ULMSSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return list;
        }

        /*
        * get the CourseType
        */
        public String getCourseType(int courseTypeID) throws ULMSSysException
        {
                String codeName = null;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select Name from Code_CourseType_Tab" +
                                " WHERE courseTypeID=" + courseTypeID;
                        LogUtil.debug("course",
                                "[CodeMaintainDAOImpl] ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        if (rs.next())
                        {
                                codeName = rs.getString("Name");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ULMSSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return codeName;
        }

        /*
        *  get the CourseType list
        */
        public List getCourseTypes() throws ULMSSysException
        {
                String codeName;
                int codeID;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CodeModel cm = null;
                List list = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select courseTypeID,Name from Code_CourseType_Tab" +
                                " order by courseTypeID";
                        LogUtil.debug("course",
                                "[CodeMaintainDAOImpl] ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                codeName = rs.getString("Name");

                                codeID = rs.getInt("courseTypeID");

                                cm = new CodeModel(codeID, codeName);
                                list.add(cm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ULMSSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return list;
        }

        public void delete(ArrayList codeID) throws ULMSSysException
        {
                String tmp = "";

                for (int n = 0; n < codeID.size(); n++)
                {
                        tmp = tmp + " or codeID=" + (Integer) codeID.get(n);
                }

                if (!tmp.equals(""))
                {
                        tmp = tmp.substring(4);
                }

                String hql = " from Codes where " + tmp;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ULMSSysException(e);
                }
        }

        public void deleteitem(ArrayList codeItemID) throws ULMSSysException
        {
                String tmp = "";

                for (int n = 0; n < codeItemID.size(); n++)
                {
                        tmp = tmp + " or CodeItemID=" + (Integer) codeItemID.get(n);
                }

                if (!tmp.equals(""))
                {
                        tmp = tmp.substring(4);
                }

                String hql = " from CodeItem where " + tmp;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ULMSSysException(e);
                }
        }

        //todo please yuhj implement this inteface
        public List getCodeInfo(int codeID, int aspID) throws ULMSSysException
        {
                String hql = " from Codes where codeID=" + codeID + " and relationid=" +
                        aspID;
                List list = null;
                Codes codes = null;

                //Session session = null;
                try
                {
                        //session = HibernateUtil.getSession();
                        list = HibernateDAO.find(hql);

                        //System.out.println("dddddddddddddddddddddddd"+list);
                        if ((list != null) && (list.size() > 0))
                        {
                                codes = (Codes) list.get(0);

                                int codeIDs = codes.getCodeid();
                                hql = " from CodeItem where codeid=" + codeIDs;
                                //hql = " from CodeItem";
                                // System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeee"+hql);
                                list = HibernateDAO.find(hql);

                                // System.out.println("fffffffffffffffffffff"+list.size());
                        }
                }
                catch (ULMSSysException he)
                {
                        LogUtil.debug("system",
                                "ULMSSysException Exception" + he.getMessage());
                }

                return list;
        }

        public List getCodes(int aspID) throws ULMSSysException
        {
                String hql = " from Codes as c  where c.relationid=" + aspID;

                //System.out.println(aspID);
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("system",
                                "ULMSSysException Exception " + e.getMessage());
                        throw e;
                }

                return list;
        }

        protected void closeConnection(Connection dbConnection)
                throws ULMSSysException
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
                        throw new ULMSSysException(se);
                }
        }

        protected void closeResultSet(ResultSet result) throws ULMSSysException
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
                        throw new ULMSSysException(se);
                }
        }

        protected void closeStatement(Statement stmt) throws ULMSSysException
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
                        throw new ULMSSysException(se);
                }
        }

        public Serializable addpeiz(PeizForm details) throws ULMSSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getCodeItem());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ULMSSysException("" + e);
                }

                return s;
        }

        public void updatepeiz(UpdatePeizForm details) throws ULMSSysException
        {
                try
                {
                        HibernateDAO.update(details.getCodeItem());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ULMSSysException("" + e);
                }
        }

        public UpdatePeizForm getCodeItemByID(int id) throws ULMSSysException
        {
                UpdatePeizForm uf = null;
                UpdatePeizForm uf1 = null;

                try
                {
                        CodeItem object = (CodeItem) HibernateDAO.load(CodeItem.class,
                                new Integer(id));
                        uf = new UpdatePeizForm();

                        uf1 = uf.getUpdatePeizForm(object);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ULMSSysException("" + e);
                }

                return uf1;
        }
}
