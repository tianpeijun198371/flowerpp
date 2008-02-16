/**
 * LmslogDAOImpl.java.
 * User: keyh  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.lmslog.exceptions.LmslogSysException;
import com.ulearning.ulms.lmslog.form.LmslogForm;
import com.ulearning.ulms.lmslog.model.LmslogModel;
import com.ulearning.ulms.lmslog.model.LmslogOperDescModel;
import com.ulearning.ulms.lmslog.model.LmslogTypeDescModel;
import com.ulearning.ulms.lmslog.util.LmslogConstants;
import com.ulearning.ulms.organ.model.OrganModel;
import com.ulearning.ulms.user.model.UserModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;


public class LmslogDAOImpl implements LmslogDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public void insert(LmslogForm lmslogForm) throws LmslogSysException
        {
                try
                {
                        lmslogForm.setOperationTime(new Date());
                        HibernateDAO.add(lmslogForm.getLmslogModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new LmslogSysException(e);
                }

                //To change body of implemented methods use File | Settings | File Templates.
        }

        public LmslogForm get(int logID) throws LmslogSysException
        {
                String hql = " from  LmslogModel as lm, UserModel as um ," +
                        " LmslogOperDescModel as lodm ,OrganModel as om" +
                        " where lm.userid=um.userid " +
                        " and lm.operationtypeid=lodm.operationtypeid" +
                        " and lm.orgid=om.orgid  and lm.logid=" + logID;
                List logList = null;
                List list = new ArrayList();
                LmslogForm lf = new LmslogForm();
                LmslogModel lm = new LmslogModel();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        logList = query.list();
                        session.flush();
                        session.connection().commit();
                        list = getFormList(logList);
                        lf = (LmslogForm) list.get(0);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return lf;
        }

        public void delete(int logID) throws LmslogSysException
        {
                String hql = " from LmslogModel where logid=" + logID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new LmslogSysException(e);
                }
        }

        public void deleteByUserID(int userID) throws LmslogSysException
        {
                String hql = " from LmslogModel where userid=" + userID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new LmslogSysException(e);
                }
        }

        public List get(int logTypeID, int operationTypeID, int userID, int orgID,
                        String time, String time2) throws LmslogSysException
        {
                List times = StringUtil.split(time, "-");
                System.out.println("time1--time2" + time + "--" + time2);

                Calendar calB = Calendar.getInstance();
                calB.set(Calendar.YEAR, Integer.parseInt((String) times.get(0)));
                calB.set(Calendar.MONTH, Integer.parseInt((String) times.get(1)) - 1);
                calB.set(Calendar.DAY_OF_MONTH, Integer.parseInt((String) times.get(2)));
                calB.set(Calendar.HOUR_OF_DAY, 0);
                calB.set(Calendar.MINUTE, 0);
                calB.set(Calendar.SECOND, 0);

                Date beginTime = calB.getTime();

                if (time2.length() > 1)
                {
                        times = StringUtil.split(time2, "-");
                }

                Calendar calE = Calendar.getInstance();
                calE.set(Calendar.YEAR, Integer.parseInt((String) times.get(0)));
                calE.set(Calendar.MONTH, Integer.parseInt((String) times.get(1)) - 1);
                calE.set(Calendar.DAY_OF_MONTH, Integer.parseInt((String) times.get(2)));
                calE.set(Calendar.HOUR_OF_DAY, 23);
                calE.set(Calendar.MINUTE, 59);
                calE.set(Calendar.SECOND, 59);

                Date endTime = calE.getTime();

                String hql = " from  LmslogModel as lm, UserModel as um ," +
                        " LmslogOperDescModel as lodm ,OrganModel as om" +
                        " where lm.userid=um.userid " +
                        " and lm.operationtypeid=lodm.operationtypeid" +
                        " and lm.orgid=om.orgid ";

                if (time.length() > 1)
                {
                        hql += (" and  lm.operationtime>=:beginTime" +
                                " and  lm.operationtime<=:endTime");
                }

                if (logTypeID != LmslogConstants.LOGTYPE_ALL)
                {
                        hql += (" and lm.logtypeid=" + logTypeID);
                }

                if (operationTypeID != LmslogConstants.OPERATION_ALL)
                {
                        hql += (" and lm.operationtypeid=" + operationTypeID);
                }

                if (userID != 0)
                {
                        hql += (" and lm.userid=" + userID);
                }

                hql += " order by lm.operationtime Desc";

                Session session = null;
                List logList = null;
                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginTime", beginTime);
                        query.setParameter("endTime", endTime);
                        logList = query.list();
                        session.flush();
                        session.connection().commit();
                        list = getFormList(logList);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        public List getAllUser(int logTypeID, int operationTypeID, int orgID,
                               String time, String time2) throws LmslogSysException
        {
                List times = StringUtil.split(time, "-");
                System.out.println("time1--time2" + time + "--" + time2);

                Calendar calB = Calendar.getInstance();
                calB.set(Calendar.YEAR, Integer.parseInt((String) times.get(0)));
                calB.set(Calendar.MONTH, Integer.parseInt((String) times.get(1)) - 1);
                calB.set(Calendar.DAY_OF_MONTH, Integer.parseInt((String) times.get(2)));
                calB.set(Calendar.HOUR_OF_DAY, 0);
                calB.set(Calendar.MINUTE, 0);
                calB.set(Calendar.SECOND, 0);

                Date beginTime = calB.getTime();

                if (time2.length() > 1)
                {
                        times = StringUtil.split(time2, "-");
                }

                Calendar calE = Calendar.getInstance();
                calE.set(Calendar.YEAR, Integer.parseInt((String) times.get(0)));
                calE.set(Calendar.MONTH, Integer.parseInt((String) times.get(1)) - 1);
                calE.set(Calendar.DAY_OF_MONTH, Integer.parseInt((String) times.get(2)));
                calE.set(Calendar.HOUR_OF_DAY, 23);
                calE.set(Calendar.MINUTE, 59);
                calE.set(Calendar.SECOND, 59);

                Date endTime = calE.getTime();

                String hql = " from  LmslogModel as lm, UserModel as um ," +
                        " LmslogOperDescModel as lodm ,OrganModel as om" +
                        " where lm.userid=um.userid " +
                        " and lm.operationtypeid=lodm.operationtypeid" +
                        " and lm.orgid=om.orgid ";

                if (time.length() > 1)
                {
                        hql += (" and  lm.operationtime>=:beginTime" +
                                " and  lm.operationtime<=:endTime");
                }

                if (logTypeID != LmslogConstants.LOGTYPE_ALL)
                {
                        hql += (" and lm.logtypeid=" + logTypeID);
                }

                if (operationTypeID != LmslogConstants.OPERATION_ALL)
                {
                        hql += (" and lm.operationtypeid=" + operationTypeID);
                }

                hql += " order by lm.operationtime Desc";

                Session session = null;
                List logList = null;
                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginTime", beginTime);
                        query.setParameter("endTime", endTime);
                        logList = query.list();
                        session.flush();
                        session.connection().commit();
                        list = getFormList(logList);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        public void update(LmslogForm lmslogForm) throws LmslogSysException
        {
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();
                        session.update(lmslogForm.getLmslogModel());
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                //To change body of impllmslogFormented methods use File | Settings | File TlmslogFormplates.
        }

        private List getFormList(List modelList)
        {
                ArrayList list = new ArrayList();
                Object[] record = null;

                for (Iterator iter = modelList.iterator(); iter.hasNext();)
                {
                        record = (Object[]) iter.next();

                        LmslogModel lm = new LmslogModel();
                        LmslogTypeDescModel ltm = new LmslogTypeDescModel();
                        LmslogOperDescModel lom = new LmslogOperDescModel();
                        UserModel um = new UserModel();
                        OrganModel om = new OrganModel();
                        lm = (LmslogModel) record[0];
                        um = (UserModel) record[1];
                        lom = (LmslogOperDescModel) record[2];
                        om = (OrganModel) record[3];

                        LmslogForm lf = new LmslogForm();

                        lf.setLogID(lm.getLogid());
                        lf.setLogTypeID(lm.getLogtypeid());
                        lf.setUserID(um.getUserid());
                        lf.setOrgID(om.getOrgid());
                        lf.setUserIP(lm.getUserip());
                        lf.setOperationTypeID(lom.getOperationtypeid());
                        lf.setOperationTable(lm.getOperationtable());
                        lf.setOperationObjectID(lm.getOperationobjectid());
                        lf.setOperationTime(lm.getOperationtime());
                        lf.setDescription(lm.getDescription());

                        lf.setUserName(um.getName());
                        lf.setUserLoginName(um.getLoginname());
                        lf.setOrgName(om.getOrgname());
                        lf.setOperationName(lom.getOperationname());
                        lf.setOperationDesc(lom.getOperationdesc());
                        list.add(lf);
                }

                return list;
        }

        private LmslogForm converRs2Model(ResultSet rs)
        {
                LmslogForm lmslogForm = new LmslogForm();

                try
                {
                        lmslogForm.setLogID(rs.getInt("LogID"));
                        lmslogForm.setLogTypeID(rs.getInt("LogTypeID"));
                        lmslogForm.setUserID(rs.getInt("UserID"));
                        lmslogForm.setOrgID(rs.getInt("OrgID"));
                        lmslogForm.setUserIP(rs.getString("UserIP"));
                        lmslogForm.setOperationTypeID(rs.getInt("OperationTypeID"));
                        lmslogForm.setOperationTable(rs.getString("OperationTable"));
                        lmslogForm.setOperationObjectID(rs.getInt("OperationObjectID"));
                        lmslogForm.setOperationTime(DateTimeUtil.toDate(rs.getDate(
                                "OperationTime"), rs.getTime("OperationTime")));
                        lmslogForm.setDescription(rs.getString("Description"));
                        lmslogForm.setUserLoginName(rs.getString("LoginName"));
                        lmslogForm.setUserName(rs.getString("Name"));
                        lmslogForm.setOperationName(rs.getString("OperationName"));
                        lmslogForm.setOperationDesc(rs.getString("OperationDesc"));
                        lmslogForm.setOrgName(rs.getString("OrgName"));
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }

                return lmslogForm;
        }

        protected void closeConnection(Connection dbConnection)
                throws LmslogSysException
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
                        throw new LmslogSysException(se);
                }
        }

        protected void closeResultSet(ResultSet result) throws LmslogSysException
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
                        throw new LmslogSysException(se);
                }
        }

        protected void closeStatement(Statement stmt) throws LmslogSysException
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
                        throw new LmslogSysException(se);
                }
        }

        public int countAll(int aspID)
        {
                String sql = "select count(*) as num  from t_lmslog_tab ";
                int count = -1;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);

                        while (rs.next())
                        {
                                count = rs.getInt("num");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return count;
        }

        public List getAllLogInfoOfUser(int userID, int logTypeID,
                                        int operationTypeID) throws LmslogSysException
        {
                String hql = " from  LmslogModel as lm, UserModel as um ," +
                        " LmslogOperDescModel as lodm ,OrganModel as om" +
                        " where lm.userid=um.userid " +
                        " and lm.operationtypeid=lodm.operationtypeid" +
                        " and lm.orgid=om.orgid ";
                List logList = null;
                List list = new ArrayList();

                if (logTypeID != LmslogConstants.LOGTYPE_ALL)
                {
                        hql += (" and lm.logtypeid=" + logTypeID);
                }

                if (operationTypeID != LmslogConstants.OPERATION_ALL)
                {
                        hql += (" and lm.operationtypeid=" + operationTypeID);
                }

                hql += " order by lm.operationtime Desc";

                return list;
        }
}
