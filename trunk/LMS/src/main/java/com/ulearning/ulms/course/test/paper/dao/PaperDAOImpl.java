/**
 * PaperDAOImpl.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.course.test.paper.model.PaperModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.*;

import java.util.*;
import java.util.Date;

import javax.sql.DataSource;


public class PaperDAOImpl implements PaperDAO
{
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;

        public int addPaper(PaperForm paperForm) throws PaperDAOSysException
        {
                int paperID = 0;

                try
                {
                        String orgIDs = HibernateDAO.add(paperForm.getPaperModel())
                                .toString();
                        paperID = Integer.parseInt(orgIDs);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PaperDAOSysException(
                                "addPaper(PaperForm paperForm) method" + e);
                }

                return paperID;
        }

        public void updatePaper(PaperForm paperForm) throws PaperDAOSysException
        {
                try
                {
                        HibernateDAO.update(paperForm.getPaperModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PaperDAOSysException(
                                "updatePaper(PaperForm paperForm) method" + e);
                }
        }

        public void removePaper(int paperID) throws PaperDAOSysException
        {
                String hql = "from PaperModel where PaperID=" + paperID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PaperDAOSysException(
                                "ULMSSysException while removePaper(int  paperID) method" + e);
                }
        }

        /**
         * Get the paoer info via the unique paperID
         *
         * @param paperID
         * @return the prepared paperForm, default is null
         * @throws PaperDAOSysException
         */
        public PaperForm getPaper(int paperID) throws PaperDAOSysException
        {
                PaperForm pf = null;

                //String hql = " from PaperModel where PaperID = " + paperID;
                try
                {
                        PaperModel pm = null;
                        Object object = HibernateDAO.load(PaperModel.class,
                                new Integer(paperID));

                        if (object != null)
                        {
                                pm = (PaperModel) object;
                        }

                        pf = new PaperForm(pm);
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "paper; " + "Serial = " + paperID + " :\n" + se);
                }

                return pf;
        }

        /**
         * Get the paper list by the courseID
         *
         * @param courseID
         * @return The prepared arraylist object,default size is 0
         * @throws PaperDAOSysException
         */
        public List getPaperList(int courseID, int type)
                throws PaperDAOSysException
        {
                ArrayList paperList = new ArrayList();
                List tmpList = new ArrayList();
                String sqlStr = "";

                if (type == 0)
                {
                        sqlStr = " from PaperModel where CourseID = " + courseID;
                }
                else
                {
                        sqlStr = " from PaperModel where " + " type='" + type + "'" +
                                "  and CourseID = " + courseID;
                }

                try
                {
                        PaperModel pm = null;
                        tmpList = HibernateDAO.find(sqlStr);

                        if (tmpList != null)
                        {
                                for (int i = 0; i < tmpList.size(); i++)
                                {
                                        pm = (PaperModel) tmpList.get(i);
                                        paperList.add(new PaperForm(pm));
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while getPaperList " +
                                "Paper; " + "courseID = " + courseID + " :\n" + se);
                }

                return paperList;
        }

        /**
         * Get the paper list by the type,whitch are shown on portal
         *
         * @param courseID
         * @return The prepared arraylist object,default size is 0
         * @throws PaperDAOSysException
         */
        public List getPaperShowList(int courseID, int type)
                throws PaperDAOSysException
        {
                ArrayList paperList = new ArrayList();
                List tmpList = new ArrayList();
                String sqlStr = "";

                if (type == 0)
                {
                        sqlStr = " from PaperModel where CourseID = " + courseID +
                                " and IsFeedbackReply = '1'";
                }
                else
                {
                        sqlStr = " from PaperModel where " + " type='" + type + "'" +
                                "  and CourseID = " + courseID + " and IsFeedbackReply = '1'";
                }

                try
                {
                        PaperModel pm = null;
                        tmpList = HibernateDAO.find(sqlStr);

                        if (tmpList != null)
                        {
                                for (int i = 0; i < tmpList.size(); i++)
                                {
                                        pm = (PaperModel) tmpList.get(i);
                                        paperList.add(new PaperForm(pm));
                                }
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new PaperDAOSysException("SQLException while updating " +
                                "Paper; " + "CourseID = " + courseID + " :\n" + se);
                }

                return paperList;
        }

        /**
         * Get the paper list by the courseID
         *
         * @param courseID
         * @return The prepared arraylist object,default size is 0
         * @throws PaperDAOSysException
         */
        public List getExerciseList(int courseID, int type)
                throws PaperDAOSysException
        {
                List list = new ArrayList();
                List exerList = new ArrayList();

                //Date nowDate=;
                Session session = null;

                //q.setDate("nowDate",new Date());
                String hql = "from PaperModel where courseID =" + courseID + " " +
                        " and type='" + type + "'" + " and StartTime<=:nowDate" +
                        " and EndTime>=:nowDate" + " and IsAvailable='1'";

                try
                {
                        session = HibernateUtil.getSession();

                        Query q = session.createQuery(hql);
                        Calendar cal = Calendar.getInstance();
                        //cal.set(Calendar.HOUR_OF_DAY,0);
                        //cal.set(Calendar.MINUTE,0);
                        //cal.set(Calendar.SECOND,0);
                        //DateFormat.
                        //System.out.print("dateTime==="+I18Util.FormatDateTime(cal.getTime(),Locale.CHINA));
                        q.setParameter("nowDate", cal.getTime());
                        list = q.list();
                        session.flush();

                        //session.connection().commit();
                        PaperModel um = null;

                        if (exerList != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        um = (PaperModel) list.get(i);

                                        PaperForm pf = new PaperForm(um);
                                        exerList.add(pf);
                                }
                        }
                }
                catch (HibernateException he)
                {
                        throw new PaperDAOSysException(
                                "HibernateException while getExerciseList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new PaperDAOSysException(
                                "HibernateException while getExerciseList: \n" + he);
                }

                return exerList;
        }

        /**
         * Get the paper list by the courseID
         *
         * @param courseID
         * @return The prepared arraylist object,default size is 0
         * @throws PaperDAOSysException
         */
        public List getDistinctExerciseList(int courseID, int type)
                throws PaperDAOSysException
        {
                Set set = new HashSet();
                List list = new ArrayList();
                List exerList = new ArrayList();

                //Date nowDate=;
                Session session = null;

                //q.setDate("nowDate",new Date());
                String hql = "from PaperModel where courseID =" + courseID + " " +
                        " and type='" + type + "'" + " and StartTime<=:nowDate" +
                        " and EndTime>=:nowDate" + " and IsAvailable='1'";

                try
                {
                        session = HibernateUtil.getSession();

                        Query q = session.createQuery(hql);
                        Calendar cal = Calendar.getInstance();
                        q.setParameter("nowDate", cal.getTime());
                        list = q.list();
                        session.flush();

                        PaperModel um = null;
                        Collections.shuffle(list);

                        Random random = new Random();

                        for (int i = 0; i < list.size(); i++)
                        {
                                um = (PaperModel) list.get(random.nextInt(list.size()));

                                PaperForm pf = new PaperForm(um);
                                set.add(pf);
                        }

                        //Ôö¼Óµ½set
                        if ((set != null) && (set.size() > 0))
                        {
                                Iterator it = set.iterator();

                                if (it.hasNext())
                                {
                                        PaperForm pff = (PaperForm) it.next();
                                        exerList.add(pff);
                                }
                        }
                }
                catch (HibernateException he)
                {
                        throw new PaperDAOSysException(
                                "HibernateException while getExerciseList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new PaperDAOSysException(
                                "HibernateException while getExerciseList: \n" + he);
                }

                return exerList;
        }

        protected void closeConnection() throws PaperDAOSysException
        {
                try
                {
                        if ((dbConnection != null) && !dbConnection.isClosed())
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new PaperDAOSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
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
                        throw new PaperDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        protected void closeStatement(PreparedStatement stmt)
                throws PaperDAOSysException
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
                        throw new PaperDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        protected Connection getConnection() throws PaperDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new PaperDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return dbConnection;
        }

        /**
         * Convert the resultSet object to planForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted planForm
         */
        private PaperForm convertRs2Form(ResultSet rs)
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
                        pf.setStartTime(DateTimeUtil.toDate(rs.getDate(rsIndex),
                                rs.getTime(rsIndex++)));
                        pf.setEndTime(DateTimeUtil.toDate(rs.getDate(rsIndex),
                                rs.getTime(rsIndex++)));
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

        /**
         * Convert the resultSet object to planForm
         *
         * @return the wanted planForm
         */
        public boolean updatePaper(int paperid, Date beg, Date end)
                throws PaperDAOSysException
        {
                PaperModel pstr = this.getPaper(paperid).getPaperModel();
                pstr.setStarttime(beg);
                pstr.setEndtime(end);

                try
                {
                        HibernateDAO.update(pstr);

                        return true;
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();

                        return false;

                        //throw new PaperDAOSysException("updatePaper(PaperForm paperForm) method" + e);
                }
        }
}
