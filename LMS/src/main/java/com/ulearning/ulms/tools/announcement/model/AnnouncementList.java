/**
 * Announcement.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.announcement.model;

import java.io.Serializable;

import java.util.ArrayList;


public class AnnouncementList implements Serializable
{
        private ArrayList announcements;
        private int userID; //related userID
        private int pageNo;
        private int pageSize;
        private int pageCount;

        public AnnouncementList()
        {
        }

        public AnnouncementList(ArrayList announcements, int pageNo, int pageSize,
                                int pageCount)
        {
                this.announcements = announcements;
                this.pageNo = pageNo;
                this.pageSize = pageSize;
                this.pageCount = pageCount;
        }

        //ArrayList is constructed by the DAO,and the value object is just designed to "TRANSLATE" the values.
        public ArrayList getAnnouncements()
        {
                return this.announcements;
        }

        public int getUserID()
        {
                return userID;
        }

        public int getPageNo()
        {
                return this.pageNo;
        }

        public int getPageSize()
        {
                return this.pageSize;
        }

        public int getPageCount()
        {
                return this.pageCount;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public void setAnnouncements(ArrayList announcements)
        {
                this.announcements = announcements;
        }

        public void setPageNo(int pageNo)
        {
                this.pageNo = pageNo;
        }

        public void setPageSize(int pageSize)
        {
                this.pageSize = pageSize;
        }

        public void setPageCount(int pageCount)
        {
                this.pageCount = pageCount;
        }
}
