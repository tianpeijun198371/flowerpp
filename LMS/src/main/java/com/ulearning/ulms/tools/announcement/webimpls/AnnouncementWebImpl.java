/**
 * AnnouncementWebImpl.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.announcement.webimpls;

import com.ulearning.ulms.tools.announcement.dao.AnnouncementDAO;
import com.ulearning.ulms.tools.announcement.dao.AnnouncementDAOFactory;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementAppException;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException;
import com.ulearning.ulms.tools.announcement.model.Announcement;
import com.ulearning.ulms.tools.announcement.model.AnnouncementList;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AnnouncementWebImpl
{
        private AnnouncementDAO dao;

        public AnnouncementWebImpl() throws AnnouncementSysException
        {
                try
                {
                        dao = AnnouncementDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                        throw new AnnouncementSysException(ex);
                }
        }

        /**
         * get a piece of information
         *
         * @param announcementID
         * @return a string contains user's basic email information
         * @throws com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException
         *          If an ulmsSys error has occurred.
         */
        public Announcement get(int announcementID) throws AnnouncementSysException
        {
                Announcement acm;

                acm = dao.get(announcementID);

                return acm;
        }

        /**
         * get all time all the userID-related announcements according to the varible passed in
         * param   getType: ==0:get all the userID-related announcements
         * ==1:get all the platform announcements
         * ==2:get all the userID-related course announcements
         * ==3:get all the userID-related certificate announcements
         *
         * @param relationID user ID
         * @param pageNo     number of returned page
         * @param pageSize   size of returned page
         * @param type       the mark of the function
         * @return a model contains all announcements releated in past N days
         * @throws com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException
         *          If an lmsSys error has occurred.
         */
        public List getAnnouncementList(boolean isAdmin, int recentDay,
                                        int relationID, int type, int pageNo, int pageSize)
                throws AnnouncementSysException
        {
                List acm = dao.getAnnouncementList(isAdmin, recentDay, relationID,
                        type, pageNo, pageSize);

                return acm;
        }

        public List getTrainInfoList(boolean isAdmin, int recentDay,
                                     int relationID, int type) throws AnnouncementSysException
        {
                List acm = dao.getTrainInfoList(isAdmin, recentDay, relationID, type);

                return acm;
        }

        public List getAnnouncementByUserID(boolean isAdmin, int userID,
                                            int recentDay, int relationID, int type, int pageNo, int pageSize)
                throws AnnouncementSysException
        {
                List acm = dao.getAnnouncementListByUserID(isAdmin, userID, recentDay,
                        relationID, type, pageNo, pageSize);

                return acm;
        }

        public int getAnnouncementCount(boolean isAdmin, int recentDay,
                                        int relationID, int type) throws AnnouncementSysException
        {
                return dao.getAnnouncementCount(isAdmin, recentDay, relationID, type);
        }

        public int searchGetCount(boolean isAdmin, int relationID, int type,
                                  String subject, String message, Date beginCreateDate, Date endCreateDate)
                throws AnnouncementSysException
        {
                return dao.searchGetCount(isAdmin, relationID, type, subject, message,
                        beginCreateDate, endCreateDate);
        }

        public List search(boolean isAdmin, int relationID, int type,
                           String subject, String message, Date beginCreateDate,
                           Date endCreateDate, int firstResult, int maxResults)
                throws AnnouncementSysException
        {
                return dao.search(isAdmin, relationID, type, subject, message,
                        beginCreateDate, endCreateDate, firstResult, maxResults);
        }

        public List searchFromUserID(boolean isAdmin, int userID, int relationID,
                                     int type, String subject, String message, Date beginCreateDate,
                                     Date endCreateDate, int firstResult, int maxResults)
                throws AnnouncementSysException
        {
                return dao.searchFromUserID(isAdmin, userID, relationID, type, subject,
                        message, beginCreateDate, endCreateDate, firstResult, maxResults);
        }

        /**
         * delete some announcements
         *
         * @param announcementIDs IDs of announcement
         * @throws com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException
         *          If an lmsSys error has occurred.
         */
        public void delete(ArrayList announcementIDs)
                throws AnnouncementSysException
        {
                dao.delete(announcementIDs);
        }

        /**
         * publish a new announcement
         *
         * @param announcement the new announcement
         * @throws com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException
         *          If an lmsSys error has occurred.
         */
        public void insert(Announcement announcement)
                throws AnnouncementSysException
        {
                dao.insert(announcement);
        }

        /**
         * update a announcement
         *
         * @param announcement
         * @throws com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException
         *          If an lmsSys error has occurred.
         */
        public void update(Announcement announcement)
                throws AnnouncementSysException
        {
                dao.update(announcement);
        }

        public long getFoldSize(String foldPath) throws AnnouncementSysException
        {
                long s = 0;
                File fp = new File(foldPath);

                if (fp.exists() && fp.isDirectory())
                {
                        File[] f = fp.listFiles();

                        for (int i = 0; i < f.length; i++)
                        {
                                if (f[i].isDirectory())
                                {
                                        File[] twoLevel = f[i].listFiles();

                                        for (int j = 0; j < twoLevel.length; j++)
                                        {
                                                s += twoLevel[j].length();
                                        }
                                }

                                s = s + f[i].length();
                        }
                }
                else
                {
                        if (fp.isFile())
                        {
                                fp.delete();
                        }

                        fp.mkdirs();
                }

                return s;
        }

        public static boolean deleteFileAndParentDiretion(String filePath)
        {
                boolean returnValue = false;

                if ((filePath != null) && !filePath.equals(""))
                {
                        File tempFile = new File(filePath);
                        File tempDir = new File(tempFile.getParent());

                        if (tempFile.exists() && !tempFile.isDirectory())
                        {
                                returnValue = tempFile.delete();
                        }

                        returnValue = tempDir.delete();
                }

                return returnValue; //hehe
        }
}
