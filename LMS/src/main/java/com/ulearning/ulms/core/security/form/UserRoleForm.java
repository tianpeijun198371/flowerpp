/**
 * UserRoleForm.java.
 * User: dengj  Date: 2004-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.form;

import com.ulearning.ulms.core.security.model.UserRoleModel;
import com.ulearning.ulms.core.security.model.UserRoleModelPK;
import com.ulearning.ulms.user.form.UserForm;

import org.apache.struts.action.ActionForm;

import java.util.List;


public class UserRoleForm extends ActionForm
{
        private int userID = 0;
        private int roleID = 0;
        private int relationID = 1;
        private int type = 1;
        private String roleName = null;
        private String userLoginName = null;
        private String userEmail = null;
        private UserForm userForm = null;
        private RoleForm roleForm = null;
        private List roleList = null;

        public UserRoleForm()
        {
        }

        public UserRoleForm(UserRoleModel urm)
        {
                if (urm != null)
                {
                        this.userID = urm.getComp_id().getUserid();
                        this.roleID = urm.getComp_id().getRoleid();
                        this.relationID = urm.getComp_id().getRelationid();
                        this.type = urm.getComp_id().getType();
                }
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getRoleID()
        {
                return roleID;
        }

        public void setRoleID(int roleID)
        {
                this.roleID = roleID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public UserForm getUserForm()
        {
                return userForm;
        }

        public void setUserForm(UserForm userForm)
        {
                this.userForm = userForm;
        }

        public RoleForm getRoleForm()
        {
                return roleForm;
        }

        public void setRoleForm(RoleForm roleForm)
        {
                this.roleForm = roleForm;
        }

        public String getRoleName()
        {
                if (roleForm != null)
                {
                        return roleForm.getName();
                }

                return roleName;
        }

        public void setRoleName(String roleName)
        {
                this.roleName = roleName;
        }

        public String getUserLoginName()
        {
                if (userForm != null)
                {
                        return userForm.getLoginName();
                }

                return userLoginName;
        }

        public void setUserLoginName(String userLoginName)
        {
                this.userLoginName = userLoginName;
        }

        public String getUserEmail()
        {
                if (userForm != null)
                {
                        return userForm.getMail();
                }

                return userEmail;
        }

        public void setUserEmail(String userEmail)
        {
                this.userEmail = userEmail;
        }

        public List getRoleList()
        {
                return roleList;
        }

        public void setRoleList(List roleList)
        {
                this.roleList = roleList;
        }

        public UserRoleModel getUserRoleModel()
        {
                UserRoleModel urm = new UserRoleModel();
                UserRoleModelPK pk = new UserRoleModelPK(userID, roleID, relationID,
                        type);
                urm.setComp_id(pk);

                return urm;
        }
}
