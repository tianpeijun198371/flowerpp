/**
 * PaperAnswerDAOOracle.java.
 * User: huangsb  Date: 2004-6-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperAnswerForm;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.SQLException;


public class PaperAnswerDAOOracle extends PaperAnswerDAOImpl
{
        public void addPaperAnswer(PaperAnswerForm paperAnswerForm)
                throws PaperDAOSysException
        {
                String sqlStr = "insert into T_Answer_Tab values (" +
                        paperAnswerForm.getUserID() + "," + paperAnswerForm.getPaperID() +
                        "," + paperAnswerForm.getQuestionID() + "," +
                        paperAnswerForm.getType() + ",'" + paperAnswerForm.getAnswer() +
                        "'," + paperAnswerForm.getGrade() + "," +
                        paperAnswerForm.getExamtimes() + ")";

                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("test",
                                "[PaperAnswerAOOracle]====================the sql string is : " +
                                        sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException e)
                {
                        throw new PaperDAOSysException(
                                "SQLException while insert a info to  PaperAnswer ;  :\n" + e);
                }
                finally
                {
                        try
                        {
                                operateDB.closeDB();
                        }
                        catch (SQLException e)
                        {
                        }
                }
        }

        public void updatePaperAnswer(PaperAnswerForm paperAnswerForm)
                throws PaperDAOSysException
        {
                String strStr = "update T_Answer_Tab set " + " UserID = " +
                        paperAnswerForm.getUserID() + ",QuestionID = " +
                        paperAnswerForm.getQuestionID() + ",Type = " +
                        paperAnswerForm.getType() + ",Answer = '" +
                        paperAnswerForm.getAnswer() + "',Grade = " +
                        paperAnswerForm.getGrade() + " where PaperID = " +
                        paperAnswerForm.getPaperID();

                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("test",
                                "[PaperAnswerDAOOracle]====================the sql string is : " +
                                        strStr);
                        operateDB.exeupdate(strStr);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while update " +
                                "PaperAnswer; Serial = " + paperAnswerForm.getPaperID() +
                                " :\n" + se);
                }
                finally
                {
                        try
                        {
                                operateDB.closeDB();
                        }
                        catch (SQLException e)
                        {
                        }
                }
        }
}
