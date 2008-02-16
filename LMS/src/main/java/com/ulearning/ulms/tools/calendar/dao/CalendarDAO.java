//Source file: d:\\ulms\\source\\com\\eduedu\\ulms\\tools\\calendar\\dao\\Calendar.java
package com.ulearning.ulms.tools.calendar.dao;

import com.ulearning.ulms.tools.calendar.exceptions.CalendarSysException;
import com.ulearning.ulms.tools.calendar.form.EventForm;
import com.ulearning.ulms.tools.calendar.form.SearchForm;
import com.ulearning.ulms.tools.calendar.model.EventModel;

import java.util.Date;
import java.util.List;


public interface CalendarDAO
{
        /**
         * @param ef
         */
        public void insert(EventForm ef) throws CalendarSysException;

        /**
         * @param eventID
         */
        public void delete(int eventID) throws CalendarSysException;

        public void deleteByUserID(int userID) throws CalendarSysException;

        public void delete(int type, int relationID) throws CalendarSysException;

        public void update(EventForm ef) throws CalendarSysException;

        public List search(SearchForm sf) throws CalendarSysException;

        public EventForm get(int eventID) throws CalendarSysException;

        /**
         * @param userID
         * @param relationID
         * @param date
         * @return List
         */
        public List getAll(int userID, int orgID, int sysID, int relationID,
                           Date date) throws CalendarSysException;

        /**
         * @param userID
         * @param relationID
         * @param date
         * @return List
         */
        public List getByDayView(int userID, int orgID, int sysID, int type,
                                 int relationID, Date date) throws CalendarSysException;

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
        public List getByWeekView(int userID, int orgID, int sysID, int type,
                                  int relationID, Date date) throws CalendarSysException;

        /**
         * @param userID
         * @param type
         * @param relationID
         * @param date
         * @return List
         */
        public List getByMonthView(int userID, int orgID, int sysID, int type,
                                   int relationID, Date date) throws CalendarSysException;

        /**
         * @param userID
         * @param type
         * @param relationID
         * @param date
         * @return List
         */
        public List getByYearView(int userID, int orgID, int sysID, int type,
                                  int relationID, Date date) throws CalendarSysException;
}
