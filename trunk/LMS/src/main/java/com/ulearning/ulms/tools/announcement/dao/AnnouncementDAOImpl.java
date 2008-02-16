/**
 * AnnouncementDAOImpl.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.announcement.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException;
import com.ulearning.ulms.tools.announcement.model.Announcement;
import com.ulearning.ulms.tools.announcement.model.AnnouncementModel;
import com.ulearning.ulms.tools.announcement.webimpls.AnnouncementWebImpl;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.util.*;


public class AnnouncementDAOImpl implements AnnouncementDAO
{
        //protected static SessionFactory sessionFactory = null;

        /**
         * publish a new announcement
         *
         * @param announcement the new announcement
         * @ AnnouncementSysException If an ulmsSys error has occurred.
         */
        public void insert(Announcement announcement)
                throws AnnouncementSysException
        {
                try
                {
                        HibernateDAO.add(announcement.getAnnouncementModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnnouncementSysException(e);
                }
        }

        /**
         * delete some announcements
         *
         * @param announcementIDs IDs of announcement
         * @ AnnouncementSysException If an ulmsSys error has occurred.
         */
        public void delete(ArrayList announcementIDs)
                throws AnnouncementSysException
        {
                String tmp = "";

                for (int n = 0; n < announcementIDs.size(); n++)
                {
                        tmp = tmp + " or announcementID=" +
                                (Integer) announcementIDs.get(n);

                        try
                        {
                                AnnouncementModel object = (AnnouncementModel) HibernateDAO.load(AnnouncementModel.class,
                                        (Integer) announcementIDs.get(n));

                                if (object.getType().equals("7"))
                                {
                                        //7 是网络笔记本，因为有空间限制，所以要删除附件；
                                        //System.out.println("[ update announceMent attachment ]"+Config.getUploadPhysicalPath()+object.getLink());
                                        AnnouncementWebImpl.deleteFileAndParentDiretion(Config.getUploadPhysicalPath() +
                                                object.getLink());
                                }
                        }
                        catch (ULMSSysException e)
                        {
                                e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                        }
                }

                if (!tmp.equals(""))
                {
                        tmp = tmp.substring(4);
                }

                String hql = " from AnnouncementModel where " + tmp;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnnouncementSysException(e);
                }
        }

        /**
         * update a announcement
         *
         * @param announcement
         * @ AnnouncementSysException If an ulmsSys error has occurred.
         */
        public void update(Announcement announcement)
                throws AnnouncementSysException
        {
                try
                {
                        // 网络笔记本用到的东西删除附件
                        if (announcement.getType() == 7)
                        {
                                AnnouncementModel object = (AnnouncementModel) HibernateDAO.load(AnnouncementModel.class,
                                        new Integer(announcement.getAnnouncementID()));

                                if (!object.getLink().equals(announcement.getLink()))
                                {
                                        //System.out.println("[ update announceMent attachment ]"+Config.getUploadPhysicalPath()+object.getLink());
                                        AnnouncementWebImpl.deleteFileAndParentDiretion(Config.getUploadPhysicalPath() +
                                                object.getLink());
                                }

                                AnnouncementModel am = (AnnouncementModel) HibernateDAO.load(AnnouncementModel.class,
                                        new Integer(announcement.getAnnouncementID()));
                                announcement.setCreateDate(am.getCreatedate());
                        }

                        //
                        HibernateDAO.update(announcement.getAnnouncementModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnnouncementSysException(e);
                }
        }

        public int countTest() throws AnnouncementSysException
        {
                int num = -1;

                try
                {
                        num = HibernateDAO.count("select count(*) from AnnouncementModel");
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnnouncementSysException(e);
                }

                return num;
        }

        /**
         * get a piece of information
         *
         * @param announcementID
         * @return a string contains user's basic email information
         * @throws com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException
         *          If an eulmsSys error has occurred.
         */
        public Announcement get(int announcementID) throws AnnouncementSysException
        {
                List tmList = null;
                Announcement announcement = new Announcement();
                Announcement at = null;
                AnnouncementModel am = null;
                String hql = " from AnnouncementModel where AnnouncementID = " +
                        announcementID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new AnnouncementSysException(e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        am = (AnnouncementModel) tmList.get(0);
                        at = announcement.getAnnouncement(am);
                }

                return at;
        }

        public List getTrainInfoList(boolean isAdmin, int recentDay,
                                     int relationID, int type) throws AnnouncementSysException
        {
                List tmList = null;
                List announcementList = new ArrayList();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = " from AnnouncementModel where    type='" + type +
                                "'";

                        if (relationID >= 0)
                        {
                                hql += ("  and relationID=" + relationID);
                        }

                        String dateConditions = "";
                        java.util.Date dayafter = null;
                        String visitSQL = "";

                        if (!isAdmin)
                        {
                                visitSQL = " and (" +
                                        "(DisplayBeginDate IS NULL and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NULL and DisplayEndDate IS NOT NULL and " +
                                        "DisplayEndDate >=:now_day)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NOT NULL and " +
                                        " DisplayEndDate >= :now_day)" + " )";
                        }

                        if (recentDay != -1) // -1 is all
                        {
                                dateConditions = " and ((DisplayBeginDate IS NULL AND  CreateDate>= :dayafter)" +
                                        " OR DisplayBeginDate >= :dayafter)";

                                Calendar calB = Calendar.getInstance();
                                calB.add(Calendar.DAY_OF_YEAR, -1 * recentDay);
                                calB.set(Calendar.HOUR_OF_DAY, 0);
                                calB.set(Calendar.MINUTE, 0);
                                calB.set(Calendar.SECOND, 0);
                                dayafter = calB.getTime();
                        }

                        hql = hql + visitSQL + dateConditions +
                                " order by modifydate desc";

                        Query q = session.createQuery(hql);

                        if ((recentDay != -1) && (dayafter != null))
                        {
                                q.setParameter("dayafter", dayafter);
                        }

                        if (!isAdmin)
                        {
                                q.setParameter("now_day", Calendar.getInstance().getTime());
                        }

                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                AnnouncementModel am = null;
                Announcement at = new Announcement();

                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (AnnouncementModel) tmList.get(i);
                        announcementList.add(at.getAnnouncement(am));
                }

                return announcementList;
        }

        public static void main(String[] args)
        {
                AnnouncementDAOImpl adi = new AnnouncementDAOImpl();
                List hello = adi.getAnnouncementList(true, -1, 0, 0, 0, 10);
                System.out.println("hello.size() = " + hello.size());
        }

        public List getAnnouncementList(boolean isAdmin, int recentDay,
                                        int relationID, int type, int firstResult, int maxResults)
                throws AnnouncementSysException
        {
                List tmList = null;
                List announcementList = new ArrayList();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = " from AnnouncementModel where    type='" + type +
                                "'";

                        if (relationID >= 0)
                        {
                                hql += ("  and relationID=" + relationID);
                        }

                        String visitSQL = "";

                        if (!isAdmin)
                        {
                                visitSQL = " and (" +
                                        "(DisplayBeginDate IS NULL and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NULL and DisplayEndDate IS NOT NULL and " +
                                        "DisplayEndDate >=:now_day)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NOT NULL and " +
                                        " DisplayEndDate >= :now_day)" + " )";
                        }

                        String dateConditions = "";
                        java.util.Date dayafter = null;

                        if (recentDay != -1) // -1 is all
                        {
                                dateConditions = " and ((DisplayBeginDate IS NULL AND  CreateDate>= :dayafter)" +
                                        " OR DisplayBeginDate >= :dayafter)";
                        }

                        hql = hql + visitSQL + dateConditions +
                                " order by modifydate desc";
                        System.out.println("hql = " + hql);

                        Query q = session.createQuery(hql);

                        if (!isAdmin)
                        {
                                Date d = new Date();
                                //d=DateTimeUtil.toDate("2004-10-16"+" 00:00:11");
                                q.setDate("now_day", d);
                                System.out.println(DateTimeUtil.FormatDateToWeb3(d));
                        }

                        if (recentDay != -1)
                        {
                                dayafter = DateTimeUtil.getRecentDay(new java.util.Date(),
                                        recentDay - 1);

                                Calendar rightNow = Calendar.getInstance();
                                rightNow.setTime(dayafter);
                                rightNow.set(Calendar.HOUR_OF_DAY, 0);
                                rightNow.set(Calendar.MINUTE, 0);
                                rightNow.set(Calendar.SECOND, 0);
                                dayafter = rightNow.getTime();
                                q.setDate("dayafter", dayafter);
                                System.out.println(DateTimeUtil.FormatDateToWeb3(dayafter));
                        }

                        q.setFirstResult(firstResult);
                        q.setMaxResults(maxResults);
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                AnnouncementModel am = null;
                Announcement at = new Announcement();

                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (AnnouncementModel) tmList.get(i);
                        announcementList.add(at.getAnnouncement(am));
                }

                return announcementList;
        }

        public List search(boolean isAdmin, int relationID, int type,
                           String subject, String message, Date beginCreateDate,
                           Date endCreateDate, int firstResult, int maxResults)
                throws AnnouncementSysException
        {
                List tmList = null;
                List announcementList = new ArrayList();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = " from AnnouncementModel where 1=1 ";

                        if (relationID != -1)
                        {
                                hql = hql + " and relationID=" + relationID;
                        }

                        if (type != -1)
                        {
                                hql = hql + " and type='" + type + "'";
                        }

                        String visitSQL = "";

                        if (!isAdmin)
                        {
                                visitSQL = " and (" +
                                        "(DisplayBeginDate IS NULL and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NULL and DisplayEndDate IS NOT NULL and " +
                                        "DisplayEndDate >=:now_day)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NOT NULL and " +
                                        " DisplayEndDate >= :now_day)" + " )";
                        }

                        String dateConditions = "";

                        if (!subject.trim().equals(""))
                        {
                                dateConditions = dateConditions + " and subject like '%" +
                                        subject + "%'";
                        }

                        if (!message.trim().equals(""))
                        {
                                dateConditions = dateConditions + " and message like '%" +
                                        message + "%'";
                        }

                        if (beginCreateDate != null)
                        {
                                dateConditions = dateConditions +
                                        " and ModifyDate >= :beginCreateDate";
                        }

                        if (endCreateDate != null)
                        {
                                dateConditions = dateConditions +
                                        " and ModifyDate <= :endCreateDate";
                        }

                        hql = hql + visitSQL + dateConditions +
                                " order by modifydate desc";

                        Query q = session.createQuery(hql);

                        if (!isAdmin)
                        {
                                Date d = new Date();
                                q.setDate("now_day", d);
                        }

                        if (beginCreateDate != null)
                        {
                                q.setDate("beginCreateDate", beginCreateDate);
                        }

                        if (endCreateDate != null)
                        {
                                q.setDate("endCreateDate", endCreateDate);
                        }

                        q.setFirstResult(firstResult);
                        q.setMaxResults(maxResults);
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                AnnouncementModel am = null;
                Announcement at = new Announcement();

                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (AnnouncementModel) tmList.get(i);
                        announcementList.add(at.getAnnouncement(am));
                }

                return announcementList;
        }

        public int searchGetCount(boolean isAdmin, int relationID, int type,
                                  String subject, String message, Date beginCreateDate, Date endCreateDate)
                throws AnnouncementSysException
        {
                List tmList = null;
                List announcementList = new ArrayList();
                Session session = null;
                int count = 0;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = " select count(*) from AnnouncementModel where 1=1 ";

                        if (relationID != -1)
                        {
                                hql = hql + " and relationID=" + relationID;
                        }

                        if (type != -1)
                        {
                                hql = hql + " and type='" + type + "'";
                        }

                        String visitSQL = "";

                        if (!isAdmin)
                        {
                                visitSQL = " and (" +
                                        "(DisplayBeginDate IS NULL and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NULL and DisplayEndDate IS NOT NULL and " +
                                        "DisplayEndDate >=:now_day)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NOT NULL and " +
                                        " DisplayEndDate >= :now_day)" + " )";
                        }

                        String dateConditions = "";

                        if (!subject.trim().equals(""))
                        {
                                dateConditions = dateConditions + " and subject like '%" +
                                        subject + "%'";
                        }

                        if (!message.trim().equals(""))
                        {
                                dateConditions = dateConditions + " and message like '%" +
                                        message + "%'";
                        }

                        if (beginCreateDate != null)
                        {
                                dateConditions = dateConditions +
                                        " and ModifyDate >= :beginCreateDate";
                        }

                        if (endCreateDate != null)
                        {
                                dateConditions = dateConditions +
                                        " and ModifyDate <= :endCreateDate";
                        }

                        hql = hql + visitSQL + dateConditions; //+ " order by modifydate desc";

                        Query q = session.createQuery(hql);

                        if (!isAdmin)
                        {
                                Date d = new Date();
                                q.setDate("now_day", d);
                        }

                        if (beginCreateDate != null)
                        {
                                q.setDate("beginCreateDate", beginCreateDate);
                        }

                        if (endCreateDate != null)
                        {
                                q.setDate("endCreateDate", endCreateDate);
                        }

                        //q.setFirstResult(firstResult);
                        //q.setMaxResults(maxResults);
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                /*AnnouncementModel am = null;
                Announcement at = new Announcement();
                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (AnnouncementModel) tmList.get(i);
                        announcementList.add(at.getAnnouncement(am));
                }*/
                Iterator it1 = tmList.iterator();

                while (it1.hasNext())
                {
                        Object row = (Object) it1.next();
                        count = ((Integer) row).intValue();
                }

                return count;

                // return announcementList;
        }

        public List searchFromUserID(boolean isAdmin, int userID, int relationID,
                                     int type, String subject, String message, Date beginCreateDate,
                                     Date endCreateDate, int firstResult, int maxResults)
                throws AnnouncementSysException
        {
                List tmList = null;
                List announcementList = new ArrayList();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = " from AnnouncementModel where userID = " + userID;

                        if (relationID != -1)
                        {
                                hql = hql + " and relationID=" + relationID;
                        }

                        if (type != -1)
                        {
                                hql = hql + " and type='" + type + "'";
                        }

                        String visitSQL = "";

                        if (!isAdmin)
                        {
                                visitSQL = " and (" +
                                        "(DisplayBeginDate IS NULL and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NULL and DisplayEndDate IS NOT NULL and " +
                                        "DisplayEndDate >=:now_day)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NOT NULL and " +
                                        " DisplayEndDate >= :now_day)" + " )";
                        }

                        String dateConditions = "";

                        if (!subject.trim().equals(""))
                        {
                                dateConditions = dateConditions + " and subject like '%" +
                                        subject + "%'";
                        }

                        if (!message.trim().equals(""))
                        {
                                dateConditions = dateConditions + " and message like '%" +
                                        message + "%'";
                        }

                        if (beginCreateDate != null)
                        {
                                dateConditions = dateConditions +
                                        " and createDate >= :beginCreateDate";
                        }

                        if (endCreateDate != null)
                        {
                                dateConditions = dateConditions +
                                        " and createDate <= :endCreateDate";
                        }

                        hql = hql + visitSQL + dateConditions +
                                " order by modifydate desc";

                        Query q = session.createQuery(hql);

                        if (!isAdmin)
                        {
                                Date d = new Date();
                                q.setDate("now_day", d);
                        }

                        if (beginCreateDate != null)
                        {
                                q.setDate("beginCreateDate", beginCreateDate);
                        }

                        if (endCreateDate != null)
                        {
                                q.setDate("endCreateDate", endCreateDate);
                        }

                        q.setFirstResult(firstResult);
                        q.setMaxResults(maxResults);
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                AnnouncementModel am = null;
                Announcement at = new Announcement();

                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (AnnouncementModel) tmList.get(i);
                        announcementList.add(at.getAnnouncement(am));
                }

                return announcementList; //To change body of implemented methods use File | Settings | File Templates.
        }

        public List getAnnouncementListByUserID(boolean isAdmin, int userID,
                                                int recentDay, int relationID, int type, int firstResult, int maxResults)
                throws AnnouncementSysException
        {
                List tmList = null;
                List announcementList = new ArrayList();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = " from AnnouncementModel where relationID=" +
                                relationID + " and type='" + type + "' and userID =" + userID;
                        String visitSQL = "";

                        if (!isAdmin)
                        {
                                visitSQL = " and (" +
                                        "(DisplayBeginDate IS NULL and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NULL and DisplayEndDate IS NOT NULL and " +
                                        "DisplayEndDate >=:now_day)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NOT NULL and " +
                                        " DisplayEndDate >= :now_day)" + " )";
                        }

                        String dateConditions = "";
                        java.util.Date dayafter = null;

                        if (recentDay != -1) // -1 is all
                        {
                                dateConditions = " and ((DisplayBeginDate IS NULL AND  CreateDate>= :dayafter)" +
                                        " OR DisplayBeginDate >= :dayafter)";
                        }

                        hql = hql + visitSQL + dateConditions +
                                " order by modifydate desc";

                        Query q = session.createQuery(hql);

                        if (!isAdmin)
                        {
                                Date d = new Date();
                                //d=DateTimeUtil.toDate("2004-10-16"+" 00:00:11");
                                q.setDate("now_day", d);
                                System.out.println(DateTimeUtil.FormatDateToWeb3(d));
                        }

                        if (recentDay != -1)
                        {
                                dayafter = DateTimeUtil.getRecentDay(new java.util.Date(),
                                        recentDay - 1);

                                Calendar rightNow = Calendar.getInstance();
                                rightNow.setTime(dayafter);
                                rightNow.set(Calendar.HOUR_OF_DAY, 0);
                                rightNow.set(Calendar.MINUTE, 0);
                                rightNow.set(Calendar.SECOND, 0);
                                dayafter = rightNow.getTime();
                                q.setDate("dayafter", dayafter);
                                System.out.println(DateTimeUtil.FormatDateToWeb3(dayafter));
                        }

                        q.setFirstResult(firstResult);
                        q.setMaxResults(maxResults);
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                AnnouncementModel am = null;
                Announcement at = new Announcement();

                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (AnnouncementModel) tmList.get(i);
                        announcementList.add(at.getAnnouncement(am));
                }

                return announcementList;
        }

        public int getAnnouncementCount(boolean isAdmin, int recentDay,
                                        int relationID, int type) throws AnnouncementSysException
        {
                List tmList = null;
                int count = 0;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String hql = " select count(*) from AnnouncementModel where type='" +
                                type + "'";

                        if (relationID >= 0)
                        {
                                hql += (" and relationID=" + relationID);
                        }

                        String visitSQL = "";

                        if (!isAdmin)
                        {
                                visitSQL = " and (" +
                                        "(DisplayBeginDate IS NULL and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NULL and DisplayEndDate IS NOT NULL and " +
                                        "DisplayEndDate >=:now_day)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NOT NULL and " +
                                        " DisplayEndDate >= :now_day)" + " )";
                        }

                        String dateConditions = "";
                        java.util.Date dayafter = null;

                        if (recentDay != -1) // -1 is all
                        {
                                dateConditions = " and ((DisplayBeginDate IS NULL AND  CreateDate>= :dayafter)" +
                                        " OR DisplayBeginDate >= :dayafter)";
                        }

                        hql = hql + visitSQL + dateConditions;

                        Query q = session.createQuery(hql);

                        if (!isAdmin)
                        {
                                Date d = new Date();
                                //d=DateTimeUtil.toDate(DateTimeUtil.FormatDateToWeb1(d)+" 00:00:00");
                                q.setDate("now_day", d);
                        }

                        if (recentDay != -1)
                        {
                                dayafter = DateTimeUtil.getRecentDay(new java.util.Date(),
                                        recentDay - 1);

                                Calendar rightNow = Calendar.getInstance();
                                rightNow.setTime(dayafter);
                                rightNow.set(Calendar.HOUR_OF_DAY, 0);
                                rightNow.set(Calendar.MINUTE, 0);
                                rightNow.set(Calendar.SECOND, 0);
                                dayafter = rightNow.getTime();
                                q.setDate("dayafter", dayafter);
                        }

                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
                                "HibernateException while getAnnouncementFormList: \n" + he);
                }

                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new AnnouncementSysException(
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

        /*
          public static void main(String[] args)
              {
                      System.out.println("Hello World!");
              String hql=" from AnnouncementModel where modifydate<?";
              try
              {
                 Session session = HibernateUtil.getSession();
                  session.delete(hql,DateTimeUtil.toDate("2004-09-22 15:33:44"),new TimestampType());
                   session.flush();
                  session.connection().commit();
                  System.out.println("sss");
              }
              catch (HibernateException he)
                      {
                            System.out.println(""+he);
                      }
              catch (SQLException he)
                      {
                            System.out.println(""+he);
                      }
              }
        */
}
