package com.ulearning.ulms.course.model;

import java.io.Serializable;

import java.util.ArrayList;


/**
 * Created by IntelliJ IDEA.
 * User: ff
 * Date: 2004-4-28
 * Time: 0:37:44
 * To change this template use File | Settings | File Templates.
 */
public class CourseUserListModel implements Serializable
{
        private int pageNo;
        private int pageSize;
        private int pageCount;
        private ArrayList courseUsers;

        public CourseUserListModel()
        {
        }

        public CourseUserListModel(ArrayList courseUsers, int pageNo, int pageSize,
                                   int pageCount)
        {
                this.pageNo = pageNo;
                this.pageSize = pageSize;
                this.pageCount = pageCount;
                this.courseUsers = courseUsers;
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

        public ArrayList getCourseUsers()
        {
                return this.courseUsers;
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

        public void setCourseUsers(ArrayList courseUsers)
        {
                this.courseUsers = courseUsers;
        }
}
