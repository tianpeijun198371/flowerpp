package com.ulearning.ulms.course.model;

import java.io.Serializable;

import java.util.ArrayList;


/**
 * Created by IntelliJ IDEA.
 * User: ff
 * Date: 2004-4-28
 * Time: 0:35:35
 * To change this template use File | Settings | File Templates.
 */
public class CourseRoleListModel implements Serializable
{
        private int userID;
        private int coursID;
        private ArrayList roles;

        public CourseRoleListModel()
        {
        }

        public CourseRoleListModel(int userID, ArrayList roles)
        {
                this.userID = userID;
                this.roles = roles;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getCoursID()
        {
                return coursID;
        }

        public void setCoursID(int coursID)
        {
                this.coursID = coursID;
        }

        public ArrayList getRoles()
        {
                return roles;
        }

        public void setRoles(ArrayList roles)
        {
                this.roles = roles;
        }
}
