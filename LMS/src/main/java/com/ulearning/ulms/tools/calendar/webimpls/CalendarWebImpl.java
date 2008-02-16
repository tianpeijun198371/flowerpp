/**
 * CalendarWebImpl.java.
 * User: keyh  Date: 2004-8-6
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.webimpls;

import com.ulearning.ulms.tools.calendar.dao.CalendarDAO;
import com.ulearning.ulms.tools.calendar.dao.CalendarDAOFactory;
import com.ulearning.ulms.tools.calendar.exceptions.CalendarSysException;
import com.ulearning.ulms.tools.calendar.form.EventForm;
import com.ulearning.ulms.tools.calendar.form.SearchForm;
import com.ulearning.ulms.tools.calendar.model.EventModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CalendarWebImpl
{
        public void delete(int eventID) throws CalendarSysException
        {
                try
                {
                        CalendarDAO dao = CalendarDAOFactory.getDAO();
                        dao.delete(eventID);
                }
                catch (CalendarSysException cse)
                {
                        cse.printStackTrace();
                }
        }

        public List search(SearchForm sf) throws CalendarSysException
        {
                List emList = new ArrayList();

                try
                {
                        CalendarDAO dao = CalendarDAOFactory.getDAO();
                        emList = dao.search(sf);
                }
                catch (CalendarSysException cse)
                {
                        cse.printStackTrace();
                }

                return emList;
        }

        public EventForm get(int eventID) throws CalendarSysException
        {
                EventForm ef = null;

                try
                {
                        CalendarDAO dao = CalendarDAOFactory.getDAO();
                        ef = dao.get(eventID);
                }
                catch (CalendarSysException cse)
                {
                        cse.printStackTrace();
                }

                return ef;
        }

        public List getByDayView(int userID, int orgID, int sysID, int type,
                                 int relationID, Date date) throws CalendarSysException
        {
                List calendarList = null;

                try
                {
                        CalendarDAO dao = CalendarDAOFactory.getDAO();
                        calendarList = dao.getByDayView(userID, orgID, sysID, type,
                                relationID, date);
                }
                catch (CalendarSysException gse)
                {
                        gse.printStackTrace();
                }

                return calendarList;
        }

        public List getByWeekView(int userID, int orgID, int sysID, int type,
                                  int relationID, Date date) throws CalendarSysException
        {
                List calendarList = null;

                try
                {
                        CalendarDAO dao = CalendarDAOFactory.getDAO();
                        calendarList = dao.getByWeekView(userID, orgID, sysID, type,
                                relationID, date);
                }
                catch (CalendarSysException gse)
                {
                        gse.printStackTrace();
                }

                return calendarList;
        }

        public List getByMonthView(int userID, int orgID, int sysID, int type,
                                   int relationID, Date date) throws CalendarSysException
        {
                List calendarList = null;

                try
                {
                        CalendarDAO dao = CalendarDAOFactory.getDAO();
                        calendarList = dao.getByMonthView(userID, orgID, sysID, type,
                                relationID, date);
                }
                catch (CalendarSysException gse)
                {
                        gse.printStackTrace();
                }

                return calendarList;
        }

        public List getByYearView(int userID, int orgID, int sysID, int type,
                                  int relationID, Date date) throws CalendarSysException
        {
                List calendarList = null;

                try
                {
                        CalendarDAO dao = CalendarDAOFactory.getDAO();
                        calendarList = dao.getByYearView(userID, orgID, sysID, type,
                                relationID, date);
                }
                catch (CalendarSysException gse)
                {
                        gse.printStackTrace();
                }

                return calendarList;
        }
}
