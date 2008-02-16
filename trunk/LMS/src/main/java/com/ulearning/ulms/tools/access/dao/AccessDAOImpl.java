/** test
 * AccessDAOImpl.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.tools.access.exceptions.AccessSysException;
import com.ulearning.ulms.tools.access.model.Access;
import com.ulearning.ulms.tools.access.model.AccessModel;
import com.ulearning.ulms.tools.access.model.UserAccess;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class AccessDAOImpl implements AccessDAO
{
        /**
         * publish a new access
         *
         * @param access the new access
         * @ AccessSysException If an ulmsSys error has occurred.
         */
        public Serializable insert(Access access) throws AccessSysException
        {
                Serializable s = null;

                try
                {
                        access.setCreateDateTime(DateTimeUtil.parseDateTime(
                                DateTimeUtil.FormatDateToWeb3(new Date())));
                        s = HibernateDAO.add(access.getAccessModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AccessSysException("" + e);
                }

                return s;
        }

        public void update(Access access) throws AccessSysException
        {
                try
                {
                        HibernateDAO.update(access.getAccessModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AccessSysException("" + e);
                }
        }

        public void logoutUpdate(int userID) throws AccessSysException
        {
                Access access = getLastAccess(userID);

                if ((access != null) && (access.getUserTime() == 0))
                {
                        long createDatetime = access.getCreateDateTime().getTime();

                        //System.out.println("createDatetime==========="+createDatetime);
                        long nowDateTime = System.currentTimeMillis();

                        //System.out.println("nowDateTime==========="+nowDateTime);
                        long userTime = (nowDateTime - createDatetime) / 1000;
                        //System.out.println("userTime==========="+userTime);
                        access.setUserTime(userTime);
                        update(access);
                }
        }

        /**
         * delete some accesss
         *
         * @param accessIDs IDs of access
         * @ AccessSysException If an ulmsSys error has occurred.
         */
        public void delete(ArrayList accessIDs) throws AccessSysException
        {
                int i = 0;
                String hql = "";

                while (i < accessIDs.size())
                {
                        hql = " or AccessID=" + (Integer) accessIDs.get(i);
                        i++;
                }

                if (!hql.equals(""))
                {
                        hql = " from AccessModel where " + hql.substring(4);
                }

                try
                {
                        if (!hql.equals(""))
                        {
                                HibernateDAO.delete(hql);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AccessSysException("" + e);
                }
        }

        public void deleteByCourseID(int courseID) throws AccessSysException
        {
                String hql = "from AccessModel where courseID=" + courseID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AccessSysException("" + e);
                }
        }

        /*
          public void updateUserTime(int accessID,double userTime)
                  throws AccessSysException
          {
                  {
                  }
                  try
                  {
                          String sql_str = "update TM_Access_TAB set "
                                  + " UserTime=UserTime+" + userTime
                                  + " Where AccessID = " + accessID;
                          LogUtil.debug("access", "[AccessDAO_Oracle817] " + sql_str);
                  }
                  catch (ULMSSysException se)
                  {
                          se.printStackTrace();
                          throw new AccessSysException(se);
                  }
          }
          /**
        * get a piece of information
        *
        * @param accessID
        * @return a string contains user's basic email information
        * @throws com.ulearning.ulms.tools.access.exceptions.AccessSysException
        *          If an eulmsSys error has occurred.
        */
        public Access get(int accessID) throws AccessSysException
        {
                Access bf = new Access();
                Access res = null;
                List tmList = null;
                String hql = " from AccessModel where AccessID=" + accessID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AccessSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        AccessModel bm = (AccessModel) tmList.get(0);
                        res = bf.getAccessForm(bm);
                }

                return res;
        }

        public Access getLastAccess(int userID) throws AccessSysException
        {
                Access bf = new Access();
                Access res = null;
                List tmList = null;
                String hql = " from AccessModel where UserID=" + userID +
                        " order by accessID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AccessSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        AccessModel bm = (AccessModel) tmList.get(0);
                        res = bf.getAccessForm(bm);
                }

                return res;
        }

        /**
         * Get notifications according the varible passed in
         *
         * @param UserID
         * @param ModuleID
         * @param CourseID
         * @param CertificateID
         * @param ProjectID
         * @param OrgID
         * @param firstResult
         * @param maxResults
         * @return
         * @throws AccessSysException If an ulmsSys error has occurred.
         */
        public List getAccessList(int UserID, int ModuleID, int CourseID,
                                  int CertificateID, int ProjectID, int OrgID, int firstResult,
                                  int maxResults) throws AccessSysException
        {
                String sql_str = "";

                if (UserID != -1)
                {
                        sql_str = sql_str + " and UserID=" + UserID;
                }

                if (ModuleID != -1)
                {
                        sql_str = sql_str + " and ModuleID=" + ModuleID;
                }

                if (CourseID != -1)
                {
                        sql_str = sql_str + " and CourseID=" + CourseID;
                }

                if (CertificateID != -1)
                {
                        sql_str = sql_str + " and CertificateID=" + CertificateID;
                }

                if (ProjectID != -1)
                {
                        sql_str = sql_str + " and ProjectID=" + ProjectID;
                }

                if (OrgID != -1)
                {
                        sql_str = sql_str + " and OrgID=" + OrgID;
                }

                if (!sql_str.equals(""))
                {
                        sql_str = " where " + sql_str.substring(5);
                }

                Access bf = new Access();
                AccessModel bm = null;
                ArrayList accessList = new ArrayList();
                List tmList = null;
                String hql = " from AccessModel = " + sql_str;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Query q = session.createQuery(hql);
                        q.setFirstResult(firstResult);
                        q.setMaxResults(maxResults);
                        tmList = q.list();

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                bm = (AccessModel) tmList.get(i);
                                accessList.add(bf.getAccessForm(bm));
                        }
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

                return accessList;
        }

        public List getAccessListGroupUserID(String userIDStr, int ModuleID,
                                             int CourseID, int CertificateID, int ProjectID, int OrgID,
                                             String startDateTime, String endDateTime, int firstResult,
                                             int maxResults) throws AccessSysException
        {
                String sql_str = "";
                UserAccess userAccess = new UserAccess();
                ArrayList ac = new ArrayList();

                if (!userIDStr.equals("-1"))
                {
                        userIDStr = StringUtil.replaceString(userIDStr, "/", " or a.userID=");

                        if (userIDStr.length() > 4)
                        {
                                userIDStr = userIDStr.substring(4);
                                sql_str = sql_str + " and (" + userIDStr + ")";
                        }
                }

                if (ModuleID != -1)
                {
                        sql_str = sql_str + " and a.moduleID=" + ModuleID;
                }

                if (CourseID != -1)
                {
                        sql_str = sql_str + " and a.courseID=" + CourseID;
                }

                if (CertificateID != -1)
                {
                        sql_str = sql_str + " and a.certificateID=" + CertificateID;
                }

                if (ProjectID != -1)
                {
                        sql_str = sql_str + " and a.projectID=" + ProjectID;
                }

                if (OrgID != -1)
                {
                        sql_str = sql_str + " and a.orgID=" + OrgID;
                }

                if (!startDateTime.trim().equals(""))
                {
                        sql_str = sql_str + " and a.createDateTime >:startDateTime";
                }

                if (!endDateTime.trim().equals(""))
                {
                        sql_str = sql_str + " and a.createDateTime <:endDateTime";
                }

                if (!sql_str.equals(""))
                {
                        sql_str = " where " + sql_str.substring(5);
                }

                List tmList = null;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = "select a.userID,count(*)," +
                                " min(a.createDateTime)," + " max(a.createDateTime)," +
                                " sum(a.userTime)" + " from  AccessModel a " + sql_str +
                                " group by a.userID";
                        Query q = session.createQuery(hql);

                        if (!startDateTime.trim().equals(""))
                        {
                                q.setDate("startDateTime",
                                        DateTimeUtil.toDate(startDateTime + " 00:00:00"));
                        }

                        if (!endDateTime.trim().equals(""))
                        {
                                q.setDate("endDateTime",
                                        DateTimeUtil.toDate(endDateTime + " 23:59:59"));
                        }

                        q.setFirstResult(firstResult);
                        q.setMaxResults(maxResults);
                        tmList = q.list();

                        Iterator it1 = tmList.iterator();
                        long t1 = 0;

                        while (it1.hasNext())
                        {
                                Object[] row = (Object[]) it1.next();
                                userAccess = new UserAccess();
                                userAccess.setUserID(((Integer) row[0]).intValue());
                                userAccess.setCon(((Integer) row[1]).intValue());
                                t1 = ((Timestamp) row[2]).getTime();
                                userAccess.setStartTime(new Date(t1));
                                t1 = ((Timestamp) row[3]).getTime();
                                userAccess.setEndTime(new Date(t1));
                                userAccess.setUsertimes(((Double) row[4]).intValue());
                                ac.add(userAccess);
                        }
                }
                catch (HibernateException se)
                {
                        se.printStackTrace();
                        throw new AccessSysException(se);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException se)
                        {
                                se.printStackTrace();
                                throw new AccessSysException(se);
                        }
                }

                return ac;
        }

        /**
         * 报表处使用
         *
         * @param userIDStr
         * @param ModuleID
         * @param CourseID
         * @param CertificateID
         * @param ProjectID
         * @param OrgID
         * @param startDateTime
         * @param endDateTime
         * @param firstResult
         * @param maxResults
         * @param isSub
         * @return
         * @throws AccessSysException
         */
        public List getAccessListGroupUserIDisSub(String userIDStr, int ModuleID,
                                                  int CourseID, int CertificateID, int ProjectID, int OrgID,
                                                  String startDateTime, String endDateTime, int firstResult,
                                                  int maxResults, String isSub) throws AccessSysException
        {
                String sql_str = "";
                UserAccess userAccess = new UserAccess();
                ArrayList ac = new ArrayList();

                if (!userIDStr.equals("-1"))
                {
                        userIDStr = StringUtil.replaceString(userIDStr, "/", " or a.userID=");

                        if (userIDStr.length() > 4)
                        {
                                userIDStr = userIDStr.substring(4);
                                sql_str = sql_str + " and (" + userIDStr + ")";
                        }
                }

                if (ModuleID != -1)
                {
                        sql_str = sql_str + " and a.moduleID=" + ModuleID;
                }

                if (CourseID != -1)
                {
                        sql_str = sql_str + " and a.courseID=" + CourseID;
                }

                if (CertificateID != -1)
                {
                        sql_str = sql_str + " and a.certificateID=" + CertificateID;
                }

                if (ProjectID != -1)
                {
                        sql_str = sql_str + " and a.projectID=" + ProjectID;
                }

                String orgIDString = "";

                if ((isSub != null) && (OrgID != -1))
                {
                        List organList = OrganHelper.getOrganTree(OrgID);

                        if (organList != null)
                        {
                                for (int i = 0; i < organList.size(); i++)
                                {
                                        OrganForm of = (OrganForm) organList.get(i);
                                        orgIDString += ("," + of.getOrgID());
                                }
                        }
                }

                System.out.println("isSub===============" + isSub);

                if (OrgID != -1)
                {
                        sql_str = sql_str + " and a.orgID in (" + OrgID + orgIDString +
                                ")";
                }

                if (!startDateTime.trim().equals(""))
                {
                        sql_str = sql_str + " and a.createDateTime >:startDateTime";
                }

                if (!endDateTime.trim().equals(""))
                {
                        sql_str = sql_str + " and a.createDateTime <:endDateTime";
                }

                if (!sql_str.equals(""))
                {
                        sql_str = " where " + sql_str.substring(5);
                }

                System.out.println("sql_str===============" + sql_str);

                List tmList = null;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = "select a.userID,count(*)," +
                                " min(a.createDateTime)," + " max(a.createDateTime)," +
                                " sum(a.userTime)" + " from  AccessModel a " + sql_str +
                                " group by a.userID";
                        Query q = session.createQuery(hql);

                        if (!startDateTime.trim().equals(""))
                        {
                                q.setDate("startDateTime",
                                        DateTimeUtil.toDate(startDateTime + " 00:00:00"));
                        }

                        if (!endDateTime.trim().equals(""))
                        {
                                q.setDate("endDateTime",
                                        DateTimeUtil.toDate(endDateTime + " 23:59:59"));
                        }

                        q.setFirstResult(firstResult);
                        q.setMaxResults(maxResults);
                        tmList = q.list();

                        Iterator it1 = tmList.iterator();
                        long t1 = 0;

                        while (it1.hasNext())
                        {
                                Object[] row = (Object[]) it1.next();
                                userAccess = new UserAccess();
                                userAccess.setUserID(((Integer) row[0]).intValue());
                                userAccess.setCon(((Integer) row[1]).intValue());
                                t1 = ((Timestamp) row[2]).getTime();
                                //System.out.println("createDateTime==============="+new Date(t1));
                                userAccess.setStartTime(new Date(t1));
                                t1 = ((Timestamp) row[3]).getTime();
                                //System.out.println("EndDateTime==============="+new Date(t1));
                                userAccess.setEndTime(new Date(t1));
                                userAccess.setUsertimes(((Double) row[4]).intValue());
                                //System.out.println("usertime==============="+((Double) row[4]).intValue());
                                ac.add(userAccess);
                        }
                }
                catch (HibernateException se)
                {
                        se.printStackTrace();
                        throw new AccessSysException(se);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException se)
                        {
                                se.printStackTrace();
                                throw new AccessSysException(se);
                        }
                }

                return ac; //To change body of implemented methods use File | Settings | File Templates.
        }

        public List getAccessListByUserId(String userIDStr, int CourseID,
                                          int CertificateID, int ProjectID, int OrgID, String startDateTime,
                                          String endDateTime, int firstResult, int maxResults, String isSub)
                throws AccessSysException
        {
                String sql_str = "";
                UserAccess userAccess = new UserAccess();
                ArrayList ac = new ArrayList();

                if (!userIDStr.equals("-1"))
                {
                        userIDStr = StringUtil.replaceString(userIDStr, "/", " or a.userID=");

                        if (userIDStr.length() > 4)
                        {
                                userIDStr = userIDStr.substring(4);
                                sql_str = sql_str + " and (" + userIDStr + ")";
                        }
                }

                /*
               if (ModuleID != -1)
               {
                       sql_str = sql_str + " and a.moduleID=" + ModuleID;
               } */
                if (CourseID != -1)
                {
                        sql_str = sql_str + " and a.courseID=" + CourseID;
                }

                if (CertificateID != -1)
                {
                        sql_str = sql_str + " and a.certificateID=" + CertificateID;
                }

                if (ProjectID != -1)
                {
                        sql_str = sql_str + " and a.projectID=" + ProjectID;
                }

                String orgIDString = "";

                if ((isSub != null) && (OrgID != -1))
                {
                        List organList = OrganHelper.getOrganTree(OrgID);

                        if (organList != null)
                        {
                                for (int i = 0; i < organList.size(); i++)
                                {
                                        OrganForm of = (OrganForm) organList.get(i);
                                        orgIDString += ("," + of.getOrgID());
                                }
                        }
                }

                System.out.println("isSub===============" + isSub);

                if (OrgID != -1)
                {
                        sql_str = sql_str + " and a.orgID in (" + OrgID + orgIDString +
                                ")";
                }

                if (!startDateTime.trim().equals(""))
                {
                        sql_str = sql_str + " and a.createDateTime >:startDateTime";
                }

                if (!endDateTime.trim().equals(""))
                {
                        sql_str = sql_str + " and a.createDateTime <:endDateTime";
                }

                if (!sql_str.equals(""))
                {
                        sql_str = " where " + sql_str.substring(5);
                }

                System.out.println("sql_str===============" + sql_str);

                List tmList = null;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = "select a.userID,count(*)," +
                                " min(a.createDateTime)," + " max(a.createDateTime)," +
                                " sum(a.userTime)" + " from  AccessModel a " + sql_str +
                                " group by a.userID";
                        Query q = session.createQuery(hql);

                        if (!startDateTime.trim().equals(""))
                        {
                                q.setDate("startDateTime",
                                        DateTimeUtil.toDate(startDateTime + " 00:00:00"));
                        }

                        if (!endDateTime.trim().equals(""))
                        {
                                q.setDate("endDateTime",
                                        DateTimeUtil.toDate(endDateTime + " 24:00:00"));
                        }

                        q.setFirstResult(firstResult);
                        q.setMaxResults(maxResults);
                        tmList = q.list();

                        Iterator it1 = tmList.iterator();
                        long t1 = 0;

                        while (it1.hasNext())
                        {
                                Object[] row = (Object[]) it1.next();
                                userAccess = new UserAccess();
                                userAccess.setUserID(((Integer) row[0]).intValue());
                                userAccess.setCon(((Integer) row[1]).intValue());
                                t1 = ((Timestamp) row[2]).getTime();
                                //System.out.println("createDateTime==============="+new Date(t1));
                                userAccess.setStartTime(new Date(t1));
                                t1 = ((Timestamp) row[3]).getTime();
                                //System.out.println("EndDateTime==============="+new Date(t1));
                                userAccess.setEndTime(new Date(t1));
                                userAccess.setUsertimes(((Double) row[4]).intValue());
                                //System.out.println("usertime==============="+((Double) row[4]).intValue());
                                ac.add(userAccess);
                        }
                }
                catch (HibernateException se)
                {
                        se.printStackTrace();
                        throw new AccessSysException(se);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException se)
                        {
                                se.printStackTrace();
                                throw new AccessSysException(se);
                        }
                }

                return ac; //To change body of implemented methods use File | Settings | File Templates.
        }

        public int getAccessCount(int UserID, int ModuleID, int CourseID,
                                  int CertificateID, int ProjectID, int OrgID) throws AccessSysException
        {
                String sql_str = "";
                int count = 0;

                if (UserID != -1)
                {
                        sql_str = sql_str + " and UserID=" + UserID;
                }

                if (ModuleID != -1)
                {
                        sql_str = sql_str + " and ModuleID=" + ModuleID;
                }

                if (CourseID != -1)
                {
                        sql_str = sql_str + " and CourseID=" + CourseID;
                }

                if (CertificateID != -1)
                {
                        sql_str = sql_str + " and CertificateID=" + CertificateID;
                }

                if (ProjectID != -1)
                {
                        sql_str = sql_str + " and ProjectID=" + ProjectID;
                }

                if (OrgID != -1)
                {
                        sql_str = sql_str + " and OrgID=" + OrgID;
                }

                if (!sql_str.equals(""))
                {
                        sql_str = " where " + sql_str.substring(5);
                }

                String hql = "select count(*) from AccessModel " + sql_str;

                try
                {
                        count = HibernateDAO.count(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AccessSysException("" + e);
                }

                return count;
        }

        public int getAccessCount(int UserID, int ModuleID, int CourseID,
                                  int CertificateID, int ProjectID, int OrgID, Date startDate,
                                  Date endDate) throws AccessSysException
        {
                String sql_str = "";
                int count = 0;

                if (UserID != -1)
                {
                        sql_str = sql_str + " and UserID=" + UserID;
                }

                if (ModuleID != -1)
                {
                        sql_str = sql_str + " and ModuleID=" + ModuleID;
                }

                if (CourseID != -1)
                {
                        sql_str = sql_str + " and CourseID=" + CourseID;
                }

                if (CertificateID != -1)
                {
                        sql_str = sql_str + " and CertificateID=" + CertificateID;
                }

                if (ProjectID != -1)
                {
                        sql_str = sql_str + " and ProjectID=" + ProjectID;
                }

                if (OrgID != -1)
                {
                        sql_str = sql_str + " and OrgID=" + OrgID;
                }

                if (startDate != null)
                {
                        sql_str = sql_str + " and CreateDateTime>=:startDate";
                }

                if (endDate != null)
                {
                        sql_str = sql_str + " and CreateDateTime<:endDate";
                }

                if (!sql_str.equals(""))
                {
                        sql_str = " where " + sql_str.substring(5);
                }

                String hql = "select count(*) from AccessModel " + sql_str;
                Session session = null;
                List tmList = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Query q = session.createQuery(hql);

                        if (startDate != null)
                        {
                                q.setDate("startDate", startDate);
                        }

                        if (endDate != null)
                        {
                                q.setDate("endDate", endDate);
                        }

                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AccessSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AccessSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                Iterator it1 = tmList.iterator();

                while (it1.hasNext())
                {
                        Object row = (Object) it1.next();
                        count = ((Integer) row).intValue();
                }

                return count;
        }

        /**
         * Convert the resultSet object to assignmentForm
         * @param rs  the resultSet which needs to convert
         * @return the wanted assignmentForm
         */

        /*
          private Access convertRs2Form(ResultSet rs)
          {
              Access acm = new Access();
              int rsIndex = 1;
              try
              {
                      acm.setAccessID(rs.getInt("AccessID"));
                      acm.setStrCreateDateTime(rs.getString("strCreateDateTime"));
                      acm.setModuleID(rs.getInt("ModuleID"));
                      acm.setCourseID(rs.getInt("CourseID"));
                      acm.setCertificateID(rs.getInt("CertificateID"));
                      acm.setProjectID(rs.getInt("ProjectID"));
                      acm.setOrgID(rs.getInt("OrgID"));
                      acm.setIp(StringUtil.nullToStr(rs.getString("IP")));
                      acm.setUserTime(rs.getDouble("UserTime"));
                      if (rs.getDate("CreateDate") != null)
                      {
                              acm.setCreateDateTime(DateTimeUtil.toDate(rs.getDate("CreateDateTime"), rs.getTime("CreateDateTime")));
                      }
              } catch (SQLException sql)
              {
                      sql.printStackTrace();
              }
              return acm;
          }
        */

        /**
         * Convert the resultSet object to assignmentForm
         * @param rs  the resultSet which needs to convert
         * @return the wanted assignmentForm
         */

        /*
          private UserAccess convertRs2UserAccess(ResultSet rs)
          {
              UserAccess acm = new UserAccess();
              int rsIndex = 1;
              try
              {
                      acm.setCon(rs.getInt("con"));
                      acm.setUserID(rs.getInt("userID"));
                      acm.setUsertimes(rs.getInt("usertimes"));
                      if (rs.getDate("startTime") != null)
                      {
                              acm.setStartTime(DateTimeUtil.toDate(rs.getDate("startTime"), rs.getTime("startTime")));
                      }
                             if (rs.getDate("endTime") != null)
                      {
                              acm.setEndTime(DateTimeUtil.toDate(rs.getDate("endTime"), rs.getTime("endTime")));
                      }
              } catch (SQLException sql)
              {
                      sql.printStackTrace();
              }
              return acm;
          }
              public static void main(String[] args)
          {
          }
        */
}
