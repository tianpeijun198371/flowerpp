/**
 * Created by IntelliJ IDEA.
 * Grade: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.grade.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.course.test.grade.form.GradeForm;
import com.ulearning.ulms.course.test.grade.model.GradeModel;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GradeDAOImpl implements GradeDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        /**
         * add a new grade info
         *
         * @param details
         * @return
         * @throws GradeDAOSysException
         */
        public int addGrade(GradeForm details) throws GradeDAOSysException
        {
                details.setCreateTime(new Date());

                String key = null;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();
                        key = session.save(details.getGradeModel()).toString();
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        LogUtil.debug("system", "Mapping Exception" + e.getMessage());
                }
                catch (HibernateException e)
                {
                        LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                }
                catch (SQLException e)
                {
                        LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                }
                finally
                {
                        if (session != null)
                        {
                                try
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                                catch (HibernateException e)
                                {
                                        LogUtil.debug("system",
                                                "Hibernate Exception" + e.getMessage());
                                }
                        }
                }

                int gradeID = 0;

                if (key != null)
                {
                        gradeID = Integer.parseInt(key);
                }

                return gradeID;
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
                                        catch (ULMSSysException se)
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
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * Remove paper from grade
         *
         * @param details
         * @throws GradeDAOSysException
         */
        public void RemoveGradePaper(GradeForm details) throws GradeDAOSysException
        {
                String[] paperIDS = details.getPaperID();
                String strSql = "";

                try
                {
                        for (int i = 0; i < paperIDS.length; i++)
                        {
                                strSql = "delete from T_GradePaper_Tab where GradeID = " +
                                        details.getGradeID() + " and RelationID = " + paperIDS[i];

                                try
                                {
                                        LogUtil.debug("system",
                                                "[BookDAOImpl]====================the sql string is : " +
                                                        strSql);
                                        conn = DBUtil.getConnection();
                                        stmt = conn.createStatement();
                                        rs = stmt.executeQuery(strSql);
                                }
                                catch (ULMSSysException se)
                                {
                                        throw new GradeDAOSysException(
                                                "SQLException while delete grade paperID; ID = " +
                                                        paperIDS[i] + " :\n" + se);
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

        /**
         * update grade infomation for modify
         *
         * @param details
         * @throws GradeDAOSysException
         */
        public void updateGrade(GradeForm details) throws GradeDAOSysException
        {
                int gradeID = details.getGradeID();
                GradeForm gf = getGrade(gradeID);
                details.setCreateTime(gf.getCreateTime());
                details.setUpdateTime(new Date());

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();
                        session.update(details.getGradeModel());
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        LogUtil.debug("system", "Mapping Exception" + e.getMessage());
                }
                catch (HibernateException e)
                {
                        LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                }
                catch (SQLException e)
                {
                        LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                }
                finally
                {
                        if (session != null)
                        {
                                try
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                                catch (HibernateException e)
                                {
                                        LogUtil.debug("system",
                                                "Hibernate Exception" + e.getMessage());
                                }
                        }
                }
        }

        /**
         * Remove the Grade from database by the GradeID
         *
         * @param GradeID
         * @throws com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException
         *
         */
        public void removeGrade(String GradeID) throws GradeDAOSysException
        {
                //String shql = " from GradeModel where GradeID = " + GradeID;
                String hql = " from GradeModel where GradeID = " + GradeID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GradeDAOSysException(
                                "ULMSSysException while removeGrade(String GradeID) method" +
                                        e);
                }
        }

        /**
         * Get the Grade info via the unique GradeID
         *
         * @param GradeID
         * @return the prepared GradeForm, default is null
         * @throws GradeDAOSysException
         */
        public GradeForm getGrade(int GradeID) throws GradeDAOSysException
        {
                String hql = " from GradeModel where GradeID = " + GradeID;
                GradeForm gf = null;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        List tmList = session.find(hql);
                        GradeModel tm = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                tm = (GradeModel) tmList.get(0);
                                gf = new GradeForm(tm);
                                gf.setPaperID(paperListOfGrade(gf.getGradeID()));
                        }
                }
                catch (HibernateException he)
                {
                        throw new GradeDAOSysException("SQLException while updating " +
                                "Plan; " + "GradeID = " + GradeID + " :\n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }

                return gf;
        }

        /**
         * Get  grade info list of  a course From CourseID
         *
         * @param courseID
         * @return
         * @throws GradeDAOSysException
         */
        public List getGradeList(int courseID) throws GradeDAOSysException
        {
                String hql = " from GradeModel where courseID = " + courseID;
                List gradeList = new ArrayList();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        List tmList = session.find(hql);
                        GradeModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (GradeModel) tmList.get(i);

                                        GradeForm gf = new GradeForm(tm);
                                        gf.setPaperID(paperListOfGrade(gf.getGradeID()));
                                        gradeList.add(gf);
                                }
                        }
                }
                catch (HibernateException he)
                {
                        throw new GradeDAOSysException("SQLException while Query List " +
                                "Grade; " + "courseID = " + courseID + " :\n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }

                return gradeList;
        }

        /**
         * get all one grade no selected paper info
         *
         * @param gradeID
         * @param type
         * @param courseID
         * @return
         * @throws GradeDAOSysException
         */
        public List getGradeNoSelectedPaperID(int gradeID, int type, int courseID)
                throws GradeDAOSysException
        {
                ArrayList paperList = new ArrayList();
                PaperForm pf = null;
                String tempSqlString = "";

                if (type != 0)
                {
                        tempSqlString = " and type = '" + type + "'";
                }

                String strSql = " select * from T_Paper_Tab where" + " courseID = " +
                        courseID + tempSqlString +
                        " and paperID not in( select relationID from T_GradePaper_Tab where" +
                        " gradeID = " + gradeID + " and type = 1)";

                try
                {
                        //   System.out.println("strSql ="+strSql);
                        LogUtil.debug("Grade",
                                "[GradeDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while (rs.next())
                        {
                                pf = convertRs2PaperForm(rs);
                                paperList.add(pf);
                        }
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query GradeList; CourseID = " + courseID +
                                        " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return paperList;
        }

        /**
         * @param gradeID
         * @param courseID
         * @param type
         * @return
         * @throws GradeDAOSysException
         */
        public List getGradeSelectePaperID(int gradeID, int type, int courseID)
                throws GradeDAOSysException
        {
                ArrayList paperList = new ArrayList();
                PaperForm pf = null;
                String tempSqlString = "";

                if (type != 0)
                {
                        tempSqlString = " and p.type = '" + type + "'";
                }

                String strSql = "select p.* from T_Paper_Tab p,T_GradePaper_Tab gp where" +
                        " p.courseID = " + courseID + tempSqlString +
                        " and p.PaperID = gp.relationID and gp.type =1" +
                        " and gp.gradeID = " + gradeID;

                try
                {
                        //   System.out.println("strSql ="+strSql);
                        LogUtil.debug("Grade",
                                "[GradeDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while (rs.next())
                        {
                                pf = convertRs2PaperForm(rs);
                                paperList.add(pf);
                        }
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query GradeList; CourseID = " + courseID +
                                        " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return paperList;
        }

        public boolean gradePaperIsExist(int gradeID, int relationID, int type)
                throws GradeDAOSysException
        {
                boolean isExist = false;
                String strSql = "select * from T_GradePaper_Tab where gradeID = " +
                        gradeID + " and relationID = " + relationID + " and type = " +
                        type;

                try
                {
                        LogUtil.debug("Grade",
                                "[GradeDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        if ((rs != null) && rs.next())
                        {
                                isExist = true;
                        }
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query GradePaper; gradeID = " + gradeID +
                                        " relationID = " + relationID + " type =  " + type + " :\n" +
                                        se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return isExist;
        }

        /**
         * Convert the resultSet object to GradeForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted GradeForm
         */
        private GradeForm convertRs2Form(ResultSet rs) throws GradeDAOSysException
        {
                GradeForm gf = new GradeForm();
                int rsIndex = 1;

                try
                {
                        gf.setGradeID(rs.getInt(rsIndex++));
                        gf.setCourseID(rs.getInt(rsIndex++));
                        gf.setTitle(rs.getString(rsIndex++));
                        gf.setType(rs.getInt(rsIndex++));
                        gf.setPaperID(paperListOfGrade(rs.getInt(1)));
                        gf.setCreateTime(rs.getDate(rsIndex++));
                        gf.setUpdateTime(rs.getDate(rsIndex++));
                        gf.setDescription(rs.getString(rsIndex++));
                }
                catch (SQLException sql)
                {
                        throw new GradeDAOSysException("SQLException while query :\n" +
                                sql);
                }

                return gf;
        }

        /**
         * @param GradeID
         * @return
         * @throws GradeDAOSysException
         */
        private String[] paperListOfGrade(int GradeID) throws GradeDAOSysException
        {
                int count = 0;
                String strSql = "select count(*) from T_GradePaper_Tab where GradeID = " +
                        GradeID;
                count = countRow(strSql);

                String[] paperIDS = new String[count];
                int i = 0;
                strSql = "select * from T_GradePaper_Tab where GradeID = " + GradeID;

                try
                {
                        LogUtil.debug("Grade",
                                "[GradeDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                paperIDS[i] = rs.getString(2);
                                i++;
                        }
                }
                catch (SQLException e)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query paperList; GradeID = " + GradeID +
                                        " :\n" + e);
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException(
                                "SQLException while query paperList; GradeID = " + GradeID +
                                        " :\n" + se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return paperIDS;
        }

        private int countRow(String strSql) throws GradeDAOSysException
        {
                int count = 0;

                try
                {
                        LogUtil.debug("Grade",
                                "[GradeDAOOracle]====================the sql string is : " +
                                        strSql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strSql);

                        if (rs.next())
                        {
                                count = rs.getInt(1);
                        }
                }
                catch (SQLException e)
                {
                        throw new GradeDAOSysException("SQLException while countRow :\n" +
                                e);
                }
                catch (ULMSSysException se)
                {
                        throw new GradeDAOSysException("SQLException while countRow :\n" +
                                se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return count;
        }

        private PaperForm convertRs2PaperForm(ResultSet rs)
        {
                PaperForm pf = new PaperForm();
                int rsIndex = 1;

                try
                {
                        pf.setPaperID(rs.getInt(rsIndex++));
                        pf.setCourseID(rs.getInt(rsIndex++));
                        pf.setTitle(rs.getString(rsIndex++));
                        pf.setDescription(rs.getString(rsIndex++));
                        pf.setInstruction(rs.getString(rsIndex++));
                        pf.setType(rs.getInt(rsIndex++));
                        pf.setIsUploadPaper(rs.getInt(rsIndex++));
                        pf.setIsAvailable(rs.getInt(rsIndex++));
                        pf.setIsAnnounce(rs.getInt(rsIndex++));
                        pf.setIsFeedbackGrade(rs.getInt(rsIndex++));
                        pf.setIsFeedbackAnswer(rs.getInt(rsIndex++));
                        pf.setIsFeedbackReply(rs.getInt(rsIndex++));
                        pf.setStartTime(rs.getDate(rsIndex++));
                        pf.setEndTime(rs.getDate(rsIndex++));
                        pf.setCreateTime(rs.getDate(rsIndex++));
                        pf.setDesc1(rs.getString(rsIndex++));
                        pf.setDesc2(rs.getString(rsIndex++));
                        pf.setDesc3(rs.getString(rsIndex++));
                        pf.setDesc4(rs.getString(rsIndex++));
                        pf.setDesc5(rs.getString(rsIndex++));
                        pf.setDesc6(rs.getString(rsIndex++));
                        pf.setDesc7(rs.getString(rsIndex++));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return pf;
        }
}
