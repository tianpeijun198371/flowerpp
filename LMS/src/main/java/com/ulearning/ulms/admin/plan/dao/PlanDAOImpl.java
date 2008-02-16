/**
 * Created by IntelliJ IDEA.
 * Plan: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.plan.dao;

import com.ulearning.ulms.admin.plan.bean.PlanHelper;
import com.ulearning.ulms.admin.plan.exceptions.PlanDAOSysException;
import com.ulearning.ulms.admin.plan.form.PlanForm;
import com.ulearning.ulms.admin.plan.model.PlanModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.*;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class PlanDAOImpl implements PlanDAO
{
        protected static SessionFactory sessionFactory = null;
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;
        private List parenttmpList = new ArrayList();
        private List planParentList = new ArrayList();

        public int addPlan(PlanForm details) throws PlanDAOSysException
        {
                try
                {
                        Serializable planID = HibernateDAO.add(details.getPlanModel());

                        return Integer.parseInt(planID.toString());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PlanDAOSysException("" + e);
                }
        }

        public void updatePlan(PlanForm details) throws PlanDAOSysException
        {
                PlanForm temp = getPlan(details.getPlanID());

                try
                {
                        HibernateDAO.update(details.getPlanModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PlanDAOSysException("" + e);
                }

                //                Session session = null;
                //                try
                //                {
                //                        session = HibernateUtil.getSession();
                //                        session.update(details.getPlanModel());
                //                        session.flush();
                //                        session.connection().commit();
                //                }
                //                catch (MappingException e)
                //                {
                //                        LogUtil.debug("system", "Mapping Exception" + e.getMessage());
                //                }
                //                catch (HibernateException e)
                //                {
                //                        LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                //                }
                //                catch (SQLException e)
                //                {
                //                        LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                //                }
                //                finally
                //                {
                //                        if (session != null)
                //                        {
                //                                try
                //                                {
                //                                        releaseSession(session);
                //
                //                                }
                //                                catch (HibernateException e)
                //                                {
                //                                        LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                //                                }
                //                        }
                //                }
        }

        /**
         * Remove the plan from database by the PlanID
         *
         * @param planID
         * @throws PlanDAOSysException
         */
        public void removePlan(String planID) throws PlanDAOSysException
        {
                String hql = " from PlanModel where planID = " + planID;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();
                        session.delete(hql);
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        LogUtil.info("system", "Mapping Exception" + e.getMessage());
                }
                catch (HibernateException e)
                {
                        LogUtil.info("system", "Hibernate Exception" + e.getMessage());
                }
                catch (SQLException e)
                {
                        LogUtil.info("system", "Hibernate Exception" + e.getMessage());
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
        }

        /**
         * Get the planID by titile for duplicate title in the same directory
         *
         * @param title
         * @return
         * @throws PlanDAOSysException
         */
        public int getPlanID(String title, int isContent, int parentID)
                throws PlanDAOSysException
        {
                int planID = 0;
                String hql = new StringBuffer().append(
                        " from PlanModel where title = '").append(title)
                        .append("' and iscontent='")
                        .append(isContent)
                        .append("' and parentid=")
                        .append(parentID).toString();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        List tmList = session.find(hql);
                        PlanModel tm = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                tm = (PlanModel) tmList.get(0);
                                planID = tm.getPlanid();
                        }
                }
                catch (HibernateException he)
                {
                        throw new PlanDAOSysException("SQLException while updating " +
                                "Plan; " + "planID = " + planID + " :\n" + he);
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

                return planID;
        }

        /**
         * Get the plan info via the unique planID
         *
         * @param planID
         * @return the prepared planForm, default is null
         * @throws PlanDAOSysException
         */
        public PlanForm getPlan(int planID) throws PlanDAOSysException
        {
                PlanForm pf = null;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        PlanModel tm = null;
                        Object plan = session.get(PlanModel.class, new Integer(planID));

                        if (plan != null)
                        {
                                tm = (PlanModel) plan;
                        }

                        pf = new PlanForm(tm);
                }
                /*catch(ObjectNotFoundException onf)
                {
                        return null;
                }*/
                catch (HibernateException he)
                {
                        he.printStackTrace();
                        throw new PlanDAOSysException("SQLException while updating " +
                                "Plan; " + "planID = " + planID + " :\n" + he);
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

                return pf;
        }

        /**
         * Get the plan list by the orgID
         *
         * @param orgID
         * @return The prepared arraylist object,default size is 0
         * @throws PlanDAOSysException
         */
        public List getPlanList(int orgID) throws PlanDAOSysException
        {
                String hql = " from PlanModel where OrgID = " + orgID;

                List planList = new ArrayList();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        List tmList = session.find(hql);
                        PlanModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (PlanModel) tmList.get(i);

                                        PlanForm pf = new PlanForm(tm);
                                        planList.add(pf);
                                }
                        }
                }
                catch (HibernateException he)
                {
                        throw new PlanDAOSysException("SQLException while updating " +
                                "Plan; " + "orgID = " + orgID + " :\n" + he);
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

                return planList;
        }

        /**
         * Get the plan list by the parentID
         *
         * @param parentID
         * @return The prepared arraylist object,default size is 0
         * @throws PlanDAOSysException
         */
        public List getPlanList(String parentID) throws PlanDAOSysException
        {
                String hql = " from PlanModel where parentID = " + parentID;

                List planList = new ArrayList();
                Session session = null;

                try
                {
                        List tmList = session.find(hql);
                        PlanModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (PlanModel) tmList.get(i);

                                        PlanForm pf = new PlanForm(tm);
                                        planList.add(pf);
                                }
                        }
                }
                catch (HibernateException he)
                {
                        throw new PlanDAOSysException("SQLException while updating " +
                                "Plan; " + "parentID = " + parentID + " :\n" + he);
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

                return planList;
        }

        /**
         * Get the plan list by the orgID,parentID
         *
         * @param orgID , parentID
         * @return The prepared arraylist object,default size is 0
         * @throws PlanDAOSysException
         */
        public List getPlanList(int orgID, int parentID) throws PlanDAOSysException
        {
                String hql = " from PlanModel where planID > 0 ";

                if (parentID >= 0)
                {
                        hql += (" and parentID = " + parentID);
                }

                if (orgID >= 0)
                {
                        hql += (" and orgID = " + orgID);
                }

                hql += "  Order by iscontent";

                List planList = new ArrayList();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        List tmList = session.find(hql);
                        PlanModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (PlanModel) tmList.get(i);

                                        PlanForm pf = new PlanForm(tm);
                                        planList.add(pf);
                                }
                        }
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                        throw new PlanDAOSysException("SQLException while updating " +
                                "Plan; " + "parentID = " + parentID + " :\n" + he);
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

                return planList;
        }

        public List getPlanPath(int planID) throws PlanDAOSysException
        {
                //List organParentList=new ArrayList();
                PlanForm of = getPlan(planID);
                int parentID = of.getParentID();

                if (parenttmpList.size() == 0)
                {
                        parenttmpList.add(new Integer(planID));
                        planParentList.add(getPlan(planID));
                }

                if ((parentID > 0) && (parentID != planID) &&
                        (!PlanHelper.isDuplicate(parenttmpList, parentID)))
                {
                        parenttmpList.add(new Integer(planID));
                        planParentList.add(getPlan(parentID));
                        getPlanPath(parentID);
                }

                return planParentList;
        }

        protected void closeConnection() throws PlanDAOSysException
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
                        throw new PlanDAOSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
                }
        }

        protected void closeResultSet(ResultSet result) throws PlanDAOSysException
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
                        throw new PlanDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        protected void closeStatement(PreparedStatement stmt)
                throws PlanDAOSysException
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
                        throw new PlanDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        protected Connection getConnection() throws PlanDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new PlanDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return dbConnection;
        }
}
