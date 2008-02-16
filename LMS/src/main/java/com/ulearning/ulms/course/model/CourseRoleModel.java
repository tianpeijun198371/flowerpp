package com.ulearning.ulms.course.model;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: ff
 * Date: 2004-4-28
 * Time: 0:32:29
 * To change this template use File | Settings | File Templates.
 */
public class CourseRoleModel implements Serializable
{
        private int roleID;
        private String roleName;

        public CourseRoleModel()
        {
        }

        public CourseRoleModel(int roleID, String roleName)
        {
                this.roleID = roleID;
                this.roleName = roleName;
        }

        public int getRoleID()
        {
                return roleID;
        }

        public void setRoleID(int roleID)
        {
                this.roleID = roleID;
        }

        public String getRoleName()
        {
                return roleName;
        }

        public void setRoleName(String roleName)
        {
                this.roleName = roleName;
        }
}
