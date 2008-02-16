/**
 * AnnouncementDAO.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.announcement.dao;

import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementAppException;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException;
import com.ulearning.ulms.tools.announcement.model.Announcement;
import com.ulearning.ulms.tools.announcement.model.AnnouncementList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public interface AnnouncementDAO
{
        /**
         * publish a new announcement
         *
         * @param announcement the new announcement
         * @throws AnnouncementSysException If an lmsSys error has occurred.
         */
        public void insert(Announcement announcement)
                throws AnnouncementSysException;

        /**
         * delete some announcements
         *
         * @param announcementIDs IDs of announcement
         * @throws AnnouncementSysException If an lmsSys error has occurred.
         */
        public void delete(ArrayList announcementIDs)
                throws AnnouncementSysException;

        /**
         * update a announcement
         *
         * @param announcement
         * @throws AnnouncementSysException If an lmsSys error has occurred.
         */
        public void update(Announcement announcement)
                throws AnnouncementSysException;

        /**
         * get a piece of information
         *
         * @param announcementID user's ID
         * @return a string contains user's basic email information
         * @throws com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException
         *          If an ulmsSys error has occurred.
         */
        public Announcement get(int announcementID) throws AnnouncementSysException;

        /**
         * get all announcement according to type,and return one page
         *
         * @param relationID  relational ID
         * @param type        the type of announcement
         * @param firstResult number of returned page
         * @param maxResults  size of returned page
         * @return a model contains all announcements
         * @throws AnnouncementSysException If an lmsSys error has occurred.
         */
        public List getAnnouncementList(boolean isAdmin, int recentDay,
                                        int relationID, int type, int firstResult, int maxResults)
                throws AnnouncementSysException;

        public List getTrainInfoList(boolean isAdmin, int recentDay,
                                     int relationID, int type) throws AnnouncementSysException;

        public int getAnnouncementCount(boolean isAdmin, int recentDay,
                                        int relationID, int type) throws AnnouncementSysException;

        public List search(boolean isAdmin, int relationID, int type,
                           String subject, String message, Date beginCreateDate,
                           Date endCreateDate, int firstResult, int maxResults)
                throws AnnouncementSysException;

        public int searchGetCount(boolean isAdmin, int relationID, int type,
                                  String subject, String message, Date beginCreateDate, Date endCreateDate)
                throws AnnouncementSysException;

        public List searchFromUserID(boolean isAdmin, int userID, int relationID,
                                     int type, String subject, String message, Date beginCreateDate,
                                     Date endCreateDate, int firstResult, int maxResults)
                throws AnnouncementSysException;

        public List getAnnouncementListByUserID(boolean isAdmin, int userID,
                                                int recentDay, int relationID, int type, int firstResult, int maxResults)
                throws AnnouncementSysException;
}
