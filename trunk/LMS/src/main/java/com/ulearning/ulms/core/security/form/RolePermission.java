/**
 * RolePermission.java.
 * User: Fengch  Date: 2005-9-12 11:44:54
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.form;

import com.ulearning.ulms.core.security.model.UserRoleModel;

import java.io.Serializable;


public class RolePermission implements Serializable
{
        private UserRoleModel urf = null;
        private String[][] rolePermission = null;

        public UserRoleModel getUrf()
        {
                return urf;
        }

        public void setUrf(UserRoleModel urf)
        {
                this.urf = urf;
        }

        public String[][] getRolePermission()
        {
                return rolePermission;
        }

        public void setRolePermission(String[][] rolePermission)
        {
                this.rolePermission = rolePermission;
        }
}
