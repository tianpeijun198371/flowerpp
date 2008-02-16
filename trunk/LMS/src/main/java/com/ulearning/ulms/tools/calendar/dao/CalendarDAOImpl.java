/**
 * CalendarDAOImpl.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.CourseListModel;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.tools.calendar.exceptions.CalendarSysException;
import com.ulearning.ulms.tools.calendar.form.EventForm;
import com.ulearning.ulms.tools.calendar.form.SearchForm;
import com.ulearning.ulms.tools.calendar.model.EventModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CalendarDAOImpl implements CalendarDAO
{
        public void insert(EventForm ef) throws CalendarSysException
        {
                try
                {
                        HibernateDAO.add(ef.getEventModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CalendarSysException(e);
                }
        }

        public void delete(int eventID) throws CalendarSysException
        {
                String hql = " from EventModel where eventid=" + eventID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CalendarSysException(e);
                }
        }

        public void deleteByUserID(int userID) throws CalendarSysException
        {
                String hql = " from EventModel where userid=" + userID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CalendarSysException(e);
                }
        }

        public void delete(int type, int relationID) throws CalendarSysException
        {
                String hql = " from EventModel where type=" + type +
                        " and relationid=" + relationID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CalendarSysException(e);
                }
        }

        public void update(EventForm ef) throws CalendarSysException
        {
                try
                {
                        HibernateDAO.update(ef.getEventModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CalendarSysException(e);
                }
        }

        public List search(SearchForm sf) throws CalendarSysException
        {
                List eventList = null;
                List list = new ArrayList();
                int userID = sf.getUserID();
                int orgID = sf.getOrgID();
                int sysID = sf.getSysID();
                int type = sf.getType();
                String title = sf.getEventTitle();
                String detail = sf.getEventDetail();
                int relationID = sf.getRelationID();
                String hql = " from EventModel where";

                if (title.length() > 0)
                {
                        hql += (" eventtitle Like '%" + title + "%'");
                }

                if ((title.length() > 0) && (detail.length() > 0))
                {
                        hql += " and ";
                }

                if (detail.length() > 0)
                {
                        hql += (" eventdetail Like '%" + detail + "%'");
                }

                if (sysID == 4)
                {
                        hql += (" and userid=" + userID); //get All
                }
                else if (sysID == 3)
                {
                        hql += (" and ( (type=0 and userid=" + userID + ") or ( type=" +
                                type + " and relationid=" + relationID + "))");
                }

                try
                {
                        eventList = HibernateDAO.find(hql);

                        if (eventList != null)
                        {
                                list = getFormList(eventList);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CalendarSysException(se);
                }

                return list;
        }

        public EventForm get(int eventID) throws CalendarSysException
        {
                String hql = " from EventModel where eventid=" + eventID;
                List list = new ArrayList();
                EventForm eventForm = new EventForm();
                EventModel eventModel = new EventModel();

                try
                {
                        List eventList = HibernateDAO.find(hql);

                        if ((eventList != null) && (eventList.size() > 0))
                        {
                                eventModel = (EventModel) eventList.get(0);

                                eventForm = eventForm.getEventForm(eventModel);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CalendarSysException(se);
                }

                return eventForm;
        }

        public List getAll(int userID, int orgID, int sysID, int relationID,
                           Date date) throws CalendarSysException
        {
                String hql = " from EventModel where userid=" + userID;
                List eventList = null;
                List list = new ArrayList();

                try
                {
                        eventList = HibernateDAO.find(hql);

                        if ((eventList != null) && (eventList.size() > 0))
                        {
                                list = getFormList(eventList);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CalendarSysException(se);
                }

                return list;
        } //To change body of implemented methods use File | Settings | File Templates.

        public List getByDayView(int userID, int orgID, int sysID, int type,
                                 int relationID, Date date) throws CalendarSysException
        {
                final int courseType = SecurityConstants.USER_COURSE_RELATION;
                final int certType = SecurityConstants.USER_CERTIFICATE_RELATION;
                Calendar calB = Calendar.getInstance();
                calB.setTime(date);
                calB.set(Calendar.HOUR_OF_DAY, 0);
                calB.set(Calendar.MINUTE, 0);
                calB.set(Calendar.SECOND, 0);

                Calendar calE = Calendar.getInstance();
                calE.setTime(date);
                calE.set(Calendar.HOUR_OF_DAY, 23);
                calE.set(Calendar.MINUTE, 59);
                calE.set(Calendar.SECOND, 59);

                Date beginDate = calB.getTime();
                Date endDate = calE.getTime();

                String hql = "from  EventModel where " + " eventdate >=:beginDate " +
                        " and eventdate <=:endDate";

                if (sysID == 4)
                {
                        hql += (" and ( userid=" + userID);

                        boolean flag = false;
                        CourseWebImpl courseWebImpl = new CourseWebImpl();
                        CourseListModel clm = null;

                        try
                        {
                                clm = courseWebImpl.getMyAllCourses(userID);

                                List myCourseList = clm.getList();

                                if ((myCourseList != null) && (myCourseList.size() >= 1))
                                {
                                        hql += " or ";

                                        for (int index = 0; index < myCourseList.size(); index++)
                                        {
                                                CourseModel cm = (CourseModel) myCourseList.get(index);
                                                int courseID = cm.getCourseID();

                                                if (flag)
                                                {
                                                        hql += (" or relationid =" + courseID);
                                                }
                                                else
                                                {
                                                        hql += ("relationid =" + courseID);
                                                        flag = true;
                                                }
                                        }
                                }

                                hql += ")";
                        }
                        catch (CourseSysException e)
                        {
                                e.printStackTrace();
                        }
                }
                else if (sysID == 3)
                {
                        hql += (" and ( (type=0 and userid=" + userID + ") or ( type=" +
                                type + " and relationid=" + relationID + "))");
                }

                Session session = null;
                List eventList = null;
                List list = new ArrayList();

                try
                {
                        System.out.println("CalendarDAO:getByDayView,hql=" + hql);
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", beginDate);
                        query.setParameter("endDate", endDate);
                        eventList = query.list();

                        if ((eventList != null) && (eventList.size() > 0))
                        {
                                list = getFormList(eventList);
                        }

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

                return list;
        }

        public List getByWeekView(int userID, int orgID, int sysID, int type,
                                  int relationID, Date date) throws CalendarSysException
        {
                Calendar calSun = Calendar.getInstance();
                calSun.setTime(date);

                Calendar calSat = Calendar.getInstance();
                calSat.setTime(date);

                int i = calSun.get(Calendar.DAY_OF_WEEK);

                calSun.add(Calendar.DAY_OF_WEEK, -i + 1);
                calSat.add(Calendar.DAY_OF_WEEK, 7 - i);
                calSun.set(Calendar.HOUR_OF_DAY, 0);
                calSun.set(Calendar.MINUTE, 0);
                calSun.set(Calendar.SECOND, 0);

                calSat.set(Calendar.HOUR_OF_DAY, 23);
                calSat.set(Calendar.MINUTE, 59);
                calSat.set(Calendar.SECOND, 59);

                Date beginDate = calSun.getTime();
                Date endDate = calSat.getTime();

                String hql = " from  EventModel where " + "( eventdate>=:beginDate  " +
                        " and eventdate<=:endDate ) ";

                if (sysID == 4)
                {
                        hql += (" and ( userid=" + userID);

                        boolean flag = false;
                        CourseWebImpl courseWebImpl = new CourseWebImpl();
                        CourseListModel clm = null;

                        try
                        {
                                clm = courseWebImpl.getMyAllCourses(userID);

                                List myCourseList = clm.getList();

                                if ((myCourseList != null) && (myCourseList.size() >= 1))
                                {
                                        hql += " or ";

                                        for (int index = 0; index < myCourseList.size(); index++)
                                        {
                                                CourseModel cm = (CourseModel) myCourseList.get(index);
                                                int courseID = cm.getCourseID();

                                                if (flag)
                                                {
                                                        hql += (" or relationid =" + courseID);
                                                }
                                                else
                                                {
                                                        hql += (" relationid =" + courseID);
                                                        flag = true;
                                                }
                                        }
                                }

                                hql += ")";
                        }
                        catch (CourseSysException e)
                        {
                                e.printStackTrace();
                        }
                }
                else if (sysID == 3)
                {
                        hql += (" and ( (type=0 and userid=" + userID + ") or ( type=" +
                                type + " and relationid=" + relationID + "))");
                }

                Session session = null;
                List eventList = null;
                List list = new ArrayList();

                try
                {
                        System.out.println("CalendarDAO:getByWeekView,hql=" + hql);
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", beginDate);
                        query.setParameter("endDate", endDate);
                        eventList = query.list();

                        if ((eventList != null) && (eventList.size() > 0))
                        {
                                list = getFormList(eventList);
                        }

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

                return list;
        }

        /**
         * Gets the events according to the type and realationID
         *
         * @param userID     userID
         * @param orgID      the orgID of user ,default is 0, is not used now
         * @param sysID      sysID = 4 means personal events, sysID = 3 means course events
         * @param type       type = 3 means course events, type = 0 means personal events
         * @param relationID this value is courseID where type=3, else is 0
         * @param date       current date
         * @return
         * @throws CalendarSysException
         */
        public List getByMonthView(int userID, int orgID, int sysID, int type,
                                   int relationID, Date date) throws CalendarSysException
        {
                Calendar calB = Calendar.getInstance();
                calB.setTime(date);
                calB.set(Calendar.DAY_OF_MONTH,
                        calB.getActualMinimum(Calendar.DAY_OF_MONTH));
                calB.set(Calendar.HOUR_OF_DAY, 0);
                calB.set(Calendar.MINUTE, 0);
                calB.set(Calendar.SECOND, 0);

                Calendar calE = Calendar.getInstance();
                calE.setTime(date);
                calE.set(Calendar.DAY_OF_MONTH,
                        calE.getActualMaximum(Calendar.DAY_OF_MONTH));
                calE.set(Calendar.HOUR_OF_DAY, 23);
                calE.set(Calendar.MINUTE, 59);
                calE.set(Calendar.SECOND, 59);

                Date beginDate = calB.getTime();

                Date endDate = calE.getTime();
                String hql = "from  EventModel where" + " eventdate>= :beginDate " +
                        " and eventdate<=:endDate ";

                //default: type=0&&realtionID=0,  All event will be displayed
                if (sysID == 4)
                {
                        hql += (" and ( userid=" + userID);

                        boolean flag = false;
                        CourseWebImpl courseWebImpl = new CourseWebImpl();
                        CourseListModel clm = null;

                        try
                        {
                                clm = courseWebImpl.getMyAllCourses(userID);

                                List myCourseList = clm.getList();

                                if ((myCourseList != null) && (myCourseList.size() >= 1))
                                {
                                        hql += " or ";

                                        for (int index = 0; index < myCourseList.size(); index++)
                                        {
                                                CourseModel cm = (CourseModel) myCourseList.get(index);
                                                int courseID = cm.getCourseID();

                                                if (flag)
                                                {
                                                        hql += (" or relationid =" + courseID);
                                                }
                                                else
                                                {
                                                        hql += ("relationid =" + courseID);
                                                        flag = true;
                                                }
                                        }
                                }

                                hql += ")";
                        }
                        catch (CourseSysException e)
                        {
                                e.printStackTrace();
                        }
                }
                else if (sysID == 3)
                {
                        hql += (" and ( (type=0 and userid=" + userID + ") or ( type=" +
                                type + " and relationid=" + relationID + "))");
                }

                Session session = null;
                List modelList = new ArrayList();

                ArrayList eventList = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", beginDate);
                        query.setParameter("endDate", endDate);
                        modelList = query.list();
                        session.flush();
                        session.connection().commit();

                        for (int day = 1;
                             day <= calB.getActualMaximum(Calendar.DAY_OF_MONTH);
                             day++)
                        {
                                int year = calB.get(Calendar.YEAR);
                                int month = calB.get(Calendar.MONTH);
                                Calendar tmp_cal = Calendar.getInstance();
                                EventModel em = new EventModel();
                                int count = 0;

                                for (int j = 0; j < modelList.size(); j++)
                                {
                                        em = (EventModel) modelList.get(j);
                                        tmp_cal.setTime(em.getEventdate());

                                        int tmp_year = tmp_cal.get(Calendar.YEAR);
                                        int tmp_month = tmp_cal.get(Calendar.MONTH);
                                        int tmp_day = tmp_cal.get(Calendar.DAY_OF_MONTH);

                                        if ((year == tmp_year) && (month == tmp_month) &&
                                                (day == tmp_day))
                                        {
                                                count++;
                                        }
                                }

                                eventList.add(new Integer(count));
                        }
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

                return eventList;
        }

        public List getByYearView(int userID, int orgID, int sysID, int type,
                                  int relationID, Date date) throws CalendarSysException
        {
                /* Calendar cal = Calendar.getInstance();
                  Date beginDate = new Date(cal.get(Calendar.YEAR), 0,
                          cal.getMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
                  Date endDate = new Date(cal.get(Calendar.YEAR), 11,
                          cal.getMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
                  String hql = "from  EventModel where " +
                          " orgid=" + orgID +
                          " and eventdate>= :beginDate " +
                          " and eventdate<=:endDate ";
                  if (sysID == 4)
                  {
                          //get All
                  }
                  else if (sysID == 3)
                  {
                           hql += " and ( (type=0 and userid="+userID+") or ( type=2 and relationid=" + relationID + "))";
                  }
                  Session session = null;
                  List eventList = null;
                  List list = new ArrayList();
                  try
                  {
                          session = HibernateUtil.getSession();
                          Query query = session.createQuery(hql);
                          query.setParameter("beginDate", beginDate);
                          query.setParameter("endDate", endDate);
                          eventList = query.list();
                          if (eventList != null && eventList.size() > 0)
                          {
                                  list = getFormList(eventList);
                          }
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
                */
                return null;
        }

        private List getFormList(List modelList)
        {
                List list = new ArrayList();

                for (int i = 0; i < modelList.size(); i++)
                {
                        EventModel em = new EventModel();
                        em = (EventModel) modelList.get(i);

                        EventForm ef = new EventForm();
                        ef = ef.getEventForm(em);
                        list.add(ef);
                }

                return list;
        }

        private EventForm converRs2Model(ResultSet rs)
        {
                EventForm ef = new EventForm();

                try
                {
                        ef.setEventID(rs.getInt("EventID"));
                        ef.setRelationID(rs.getInt("RelationID"));
                        ef.setType(rs.getInt("Type"));
                        ef.setUserID(rs.getInt("UserID"));
                        ef.setOrgID(rs.getInt("OrgID"));
                        ef.setStartHour(rs.getInt("EventStartHour"));
                        ef.setStopHour(rs.getInt("EventStopHour"));
                        ef.setStartMin(rs.getInt("EventStartMin"));
                        ef.setStopMin(rs.getInt("EventStopMin"));
                        ef.setIsRemind(rs.getString("IsRemind"));
                        ef.setEventTitle(rs.getString("EventTitle"));
                        ef.setEventDetail(rs.getString("EventDetail"));
                        ef.setRemindTime(rs.getString("RemindTime"));

                        String tmp = rs.getString("EventDate");

                        if (tmp != null)
                        {
                                tmp = tmp.substring(0, 10);
                        }
                        else
                        {
                                tmp = "";
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }

                return ef;
        }

        protected void closeConnection(Connection dbConnection)
                throws CalendarSysException
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
                        throw new CalendarSysException(se);
                }
        }

        protected void closeResultSet(ResultSet result) throws CalendarSysException
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
                        throw new CalendarSysException(se);
                }
        }

        protected void closeStatement(Statement stmt) throws CalendarSysException
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
                        throw new CalendarSysException(se);
                }
        }
}
