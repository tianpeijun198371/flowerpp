/**
 * RoleForm.java.
 * User: dengj  Date: 2004-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.form;

import com.ulearning.ulms.core.security.model.RoleModel;

import org.apache.struts.action.ActionForm;

import java.util.List;


public class RoleForm extends ActionForm
{
        private int SysID = 0;
        private int RoleID = 0;
        private String Name = null;
        private String Description = null;
        private String[] permissionList = null;
        private List permitList = null;

        public RoleForm()
        {
        }

        public RoleForm(RoleModel rm)
        {
                if (rm != null)
                {
                        this.SysID = rm.getSysid();
                        this.RoleID = rm.getRoleid();
                        this.Name = rm.getName();
                        this.Description = rm.getDescription();
                }
        }

        public RoleModel getRoleModel()
        {
                RoleModel rm = new RoleModel();
                rm.setSysid(this.SysID);
                rm.setRoleid(this.RoleID);
                rm.setName(this.Name);
                rm.setDescription(this.Description);

                return rm;
        }

        public int getSysID()
        {
                return SysID;
        }

        public void setSysID(int sysID)
        {
                SysID = sysID;
        }

        public int getRoleID()
        {
                return RoleID;
        }

        public void setRoleID(int roleID)
        {
                RoleID = roleID;
        }

        public String getName()
        {
                return Name;
        }

        public void setName(String name)
        {
                Name = name;
        }

        public String getDescription()
        {
                return Description;
        }

        public void setDescription(String description)
        {
                Description = description;
        }

        public String[] getPermissionList()
        {
                return permissionList;
        }

        public void setPermissionList(String[] permissionList)
        {
                this.permissionList = permissionList;
        }

        public List getPermitList()
        {
                return permitList;
        }

        public void setPermitList(List permitList)
        {
                this.permitList = permitList;
        }
}
