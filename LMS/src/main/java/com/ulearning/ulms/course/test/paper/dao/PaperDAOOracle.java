/**
 * PaperDAOOracle.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;


public class PaperDAOOracle extends PaperDAOImpl
{
        /**
         * Insert a new Paper record to database
         *
         * @param paperForm the value object to be added
         * @throws PaperDAOSysException
         */
        public int addPaper(PaperForm paperForm) throws PaperDAOSysException
        {
                Statement stmt = null;
                int paperID = 0;
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String createTime = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String beginTime = paperForm.getBeginTime();

                if (beginTime == null)
                {
                        beginTime = "''";
                }
                else
                {
                        beginTime = "to_date('" + beginTime + "','yyyy-mm-dd')";
                }

                String lastTime = paperForm.getLastTime();

                if (lastTime == null)
                {
                        lastTime = "''";
                }
                else
                {
                        lastTime = "to_date('" + lastTime + "','yyyy-mm-dd')";
                }

                String sqlStr = "insert into T_Paper_Tab values(paperID.nextval," +
                        paperForm.getCourseID() + ",'" + paperForm.getTitle() + "','" +
                        paperForm.getDescription() + "','" + paperForm.getInstruction() +
                        "','" + paperForm.getType() + "'," + paperForm.getIsUploadPaper() +
                        "," + paperForm.getIsAvailable() + "," + paperForm.getIsAnnounce() +
                        "," + paperForm.getIsFeedbackGrade() + "," +
                        paperForm.getIsFeedbackAnswer() + "," +
                        paperForm.getIsFeedbackReply() + "," + beginTime + "," + lastTime +
                        "," + createTime + ",'" + paperForm.getDesc1() + "','" +
                        paperForm.getDesc2() + "','" + paperForm.getDesc3() + "','" +
                        paperForm.getDesc4() + "','" + paperForm.getDesc5() + "','" +
                        paperForm.getDesc6() + "','" + paperForm.getDesc7() + "')";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[PaperDAOOracle==============the SQL String]" + sqlStr);
                        stmt.execute(sqlStr);

                        String isql = "SELECT paperID.currval  as paperID FROM DUAL";
                        stmt.execute(isql);

                        ResultSet rs = stmt.getResultSet();

                        if (rs.next())
                        {
                                paperID = rs.getInt("paperID");
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "plan;" + " Serial=" + paperForm.getTitle() + ":\n" + se);
                }
                finally
                {
                        try
                        {
                                if (stmt != null)
                                {
                                        stmt.close();
                                        closeConnection();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }

                return paperID;
        }

        /**
         * Update PaperInfo by the new Form
         *
         * @param paperForm value object for changed
         * @throws PaperDAOSysException
         */
        public void updatePaper(PaperForm paperForm) throws PaperDAOSysException
        {
                Statement stmt = null;
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String createTime = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String beginTime = paperForm.getBeginTime();

                if (beginTime == null)
                {
                        Date da = paperForm.getStartTime();
                        beginTime = "to_date('" + da + "','yyyy-mm-dd')";
                }
                else
                {
                        beginTime = "to_date('" + beginTime + "','yyyy-mm-dd')";
                }

                String lastTime = paperForm.getLastTime();

                if (lastTime == null)
                {
                        Date db = paperForm.getStartTime();
                        lastTime = "to_date('" + db + "','yyyy-mm-dd')";
                }
                else
                {
                        lastTime = "to_date('" + lastTime + "','yyyy-mm-dd')";
                }

                String sqlStr = "update T_Paper_Tab set " + "CourseID=" +
                        paperForm.getCourseID() + "," + "Title='" + paperForm.getTitle() +
                        "'," + "Description='" + paperForm.getDescription() + "'," +
                        "Instruction='" + paperForm.getInstruction() + "'," + "Type = '" +
                        paperForm.getType() + "', " + "IsUploadPaper = " +
                        paperForm.getIsUploadPaper() + ", " + "IsAvailable = " +
                        paperForm.getIsAvailable() + ", " + "IsAnnounce = " +
                        paperForm.getIsAnnounce() + ", " + "IsFeedbackGrade = " +
                        paperForm.getIsFeedbackGrade() + ", " + "IsFeedbackAnswer = " +
                        paperForm.getIsFeedbackAnswer() + ", " + "IsFeedbackReply = " +
                        paperForm.getIsFeedbackReply() + ", " + "StartTime = " + beginTime +
                        ", " + "EndTime = " + lastTime + ", " + "CreateTime = " +
                        createTime + ", " + "Desc1 = '" + paperForm.getDesc1() + "', " +
                        "Desc2 = '" + paperForm.getDesc2() + "', " + "Desc3 = '" +
                        paperForm.getDesc3() + "', " + "Desc4 = '" + paperForm.getDesc4() +
                        "', " + "Desc5 = '" + paperForm.getDesc5() + "', " + "Desc6 = '" +
                        paperForm.getDesc6() + "', " + "Desc7 = '" + paperForm.getDesc7() +
                        "'  where PaperID = " + paperForm.getPaperID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[PaperDAOOracle==============the SQL String]" + sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paper;" + " Serial=" + paperForm.getTitle() + ":\n" + se);
                }
                finally
                {
                        try
                        {
                                if (stmt != null)
                                {
                                        stmt.close();
                                        closeConnection();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }
}
