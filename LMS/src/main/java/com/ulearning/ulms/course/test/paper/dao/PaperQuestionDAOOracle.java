/**
 * PaperQuestionDAOOracle.java.
 * User: huangsb  Date: 2004-6-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.SQLException;


public class PaperQuestionDAOOracle extends PaperQuestionDAOImpl
{
        public void addPaperQuestion(PaperQuestionForm paperQuestionForm)
                throws PaperDAOSysException
        {
                String sqlStr = "insert into T_PaperQuestion_Tab values (" +
                        paperQuestionForm.getPaperID() + "," +
                        paperQuestionForm.getQuestionID() + "," +
                        paperQuestionForm.getScore() + ",'" + paperQuestionForm.getType() +
                        "')";
                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("system",
                                "[Paper PaperDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        operateDB.exeupdate(sqlStr);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "base; Serial = " + paperQuestionForm.getQuestionID() + " :\n" +
                                se);
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
