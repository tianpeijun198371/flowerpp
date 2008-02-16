/**
 * Created by IntelliJ IDEA.
 * Grade: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.grade.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.course.test.grade.form.GradeForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GradeDAOOracle extends GradeDAOImpl
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        /**
         * Insert a new Grade record to database
         *
         * @param GradeForm the value object to be added
         * @throws com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException
         *
         */
        public int addGrade(GradeForm GradeForm) throws GradeDAOSysException
        {
                int gradeID = 0;
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String createDate = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String updateDate = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String strSql = "insert into T_Grade_Tab values(GradeID.nextval," +
                        GradeForm.getCourseID() + ",'" + GradeForm.getTitle() + "'," +
                        GradeForm.getType() + "," + createDate + "," + updateDate + ",'" +
                        GradeForm.getDescription() + "',' ',' ',' ',' ')";

                try
                {
                        LogUtil.debug("test",
                                "[GradeDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);
                }
                catch (Exception se)
                {
                        throw new GradeDAOSysException("SQLException while updating " +
                                "grade; Serial = " + GradeForm.getTitle() + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                gradeID = getGradeID(GradeForm);

                return gradeID;
        }

        /**
         * Update GradeInfo by the new Form
         *
         * @param GradeForm value object for changed
         * @throws GradeDAOSysException
         */
        public void updateGrade(GradeForm GradeForm) throws GradeDAOSysException
        {
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String updateDate = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                String strSql = "update T_Grade_Tab set " + " Title = '" +
                        GradeForm.getTitle() + "', Description = '" +
                        GradeForm.getDescription() + "',UpdateTime = " + updateDate +
                        " where GradeID = " + GradeForm.getGradeID();

                try
                {
                        LogUtil.debug("test",
                                "[GradeDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);
                }
                catch (Exception se)
                {
                        throw new GradeDAOSysException("SQLException while modify " +
                                "grade; Serial = " + GradeForm.getTitle() + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        public void addPaper2Grade(GradeForm GradeForm) throws GradeDAOSysException
        {
                String[] paperIDs = GradeForm.getPaperID();
                boolean isExist = false;
                String strSql = "";

                try
                {
                        for (int i = 0; i < GradeForm.getPaperID().length; i++)
                        {
                                isExist = gradePaperIsExist(GradeForm.getGradeID(),
                                        Integer.parseInt(paperIDs[i]), GradeForm.getRelationID());
                                LogUtil.debug("test",
                                        "isExist of gradeID = " + GradeForm.getGradeID() +
                                                ",relationID = " + GradeForm.getRelationID() +
                                                ",paperID =" + paperIDs[i] + " is" + isExist);

                                if (!isExist)
                                {
                                        strSql = "insert into T_GradePaper_Tab(GradeID,relationID,type)" +
                                                " values(" + GradeForm.getGradeID() + "," +
                                                paperIDs[i] + "," + GradeForm.getRelationID() + ")";

                                        try
                                        {
                                                LogUtil.debug("test",
                                                        "[GradeAOOracle]====================the sql string is : " +
                                                                strSql);
                                                conn = DBUtil.getConnection();
                                                stmt = conn.createStatement();
                                                rs = stmt.executeQuery(strSql);
                                        }
                                        catch (Exception se)
                                        {
                                                throw new GradeDAOSysException(
                                                        "SQLException while insert paper of grade ; Serial = " +
                                                                GradeForm.getGradeID() + " :\n" + se);
                                        }
                                }
                        }
                }
                catch (Exception e)
                {
                        throw new GradeDAOSysException("SQLException while query " +
                                "grade; Serial = " + GradeForm.getTitle() + " :\n" + e);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * get grade if form info of gradeForm
         *
         * @param GradeForm
         * @return
         * @throws GradeDAOSysException
         */
        private int getGradeID(GradeForm GradeForm) throws GradeDAOSysException
        {
                int gradeID = 0;
                String strSql = "select * from T_Grade_Tab where CourseID =" +
                        GradeForm.getCourseID() + " and Title = '" + GradeForm.getTitle() +
                        "' and type = " + GradeForm.getType() + " and Description = '" +
                        GradeForm.getDescription() + "' order by GradeID desc";

                try
                {
                        LogUtil.debug("test",
                                "[GradeDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        if (rs.next())
                        {
                                gradeID = rs.getInt(1);
                        }
                }
                catch (SQLException e)
                {
                        throw new GradeDAOSysException("SQLException while query " +
                                "grade; Serial = " + GradeForm.getTitle() + " :\n" + e);
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException("SQLException while query " +
                                "grade; Serial = " + GradeForm.getTitle() + " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return gradeID;
        }
}
