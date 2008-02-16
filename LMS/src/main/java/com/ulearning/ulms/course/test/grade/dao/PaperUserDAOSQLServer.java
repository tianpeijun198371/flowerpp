/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-18
 * Time: 15:41:37
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.test.grade.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.SQLException;


public class PaperUserDAOSQLServer extends PaperUserDAOImpl
{
        /**
         * Modify student answer score
         *
         * @param questionID
         * @param score
         * @param userID
         * @throws com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException
         *
         */
        public void modifyStudentQuestionScore(int questionID, float score,
                                               int userID, int paperID) throws GradeDAOSysException
        {
                String strSql = "update T_Answer_Tab set Grade = " + score +
                        " where questionID = " + questionID + " and userID = " + userID +
                        " and paperID = " + paperID;
                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("test",
                                "[PaperUserAOOracle]====================the sql string is : " +
                                        strSql);
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException e)
                {
                        throw new GradeDAOSysException(
                                "SQLException while modify studentAnswer ;  :\n" + e);
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

        /**
         * Modfiy score of a student's paper
         *
         * @param paperUserForm
         * @throws GradeDAOSysException
         */
        public void modifyPaperUser(PaperUserForm paperUserForm)
                throws GradeDAOSysException
        {
                String strSql = "update T_PaperUser_Tab set Subjective = " +
                        paperUserForm.getSubjective() + " , Objective = " +
                        paperUserForm.getObjective() + " , Grade = " +
                        paperUserForm.getDesc1() + " , Status = " +
                        paperUserForm.getStatus() + " where paperID = " +
                        paperUserForm.getPaperID() + " and UserID = " +
                        paperUserForm.getUserID() + " and Grade = " +
                        paperUserForm.getGrade();

                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("test",
                                "[PaperUserAOOracle]====================the sql string is : " +
                                        strSql);
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException e)
                {
                        throw new GradeDAOSysException(
                                "SQLException while modify PaperUser ;  :\n" + e);
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

        /**
         * add a paper user info
         *
         * @param paperUserForm
         * @throws GradeDAOSysException
         */
        public void addPaperUser(PaperUserForm paperUserForm)
                throws GradeDAOSysException
        {
                String strSql = "insert into T_PaperUser_Tab values(" +
                        paperUserForm.getPaperID() + "," + paperUserForm.getUserID() + "," +
                        paperUserForm.getType() + "," + paperUserForm.getSubjective() +
                        "," + paperUserForm.getObjective() + "," +
                        paperUserForm.getGrade() + "," + paperUserForm.getStatus() + "," +
                        paperUserForm.getStartTime() + "," + paperUserForm.getEndTime() +
                        ",'" + paperUserForm.getDescription() + "','" +
                        paperUserForm.getDesc1() + "','" + paperUserForm.getDesc2() +
                        "','" + paperUserForm.getDesc3() + "','" +
                        paperUserForm.getDesc4() + "')";
                OperateDB operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("test",
                                "[PaperUserAOOracle]====================the sql string is : " +
                                        strSql);
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException e)
                {
                        throw new GradeDAOSysException(
                                "SQLException while insert a info to  PaperUser ;  :\n" + e);
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
