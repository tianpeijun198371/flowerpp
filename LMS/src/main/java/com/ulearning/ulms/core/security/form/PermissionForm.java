/**
 * PermissionForm.java.
 * User: dengj  Date: 2004-5-6
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.form;

public class PermissionForm
{
        private int moduleID = 0;
        private int proID = 0;
        private int permitID = 0;
        private int isAvailable = 0;
        private int type = 0;
        private int defautCheck = 0;
        private String name = null;
        private String permitNO = null;
        private String[] permitList = null;
        private String description = null;
        private int status = 0;

        public int getModuleID()
        {
                return moduleID;
        }

        public void setModuleID(int moduleID)
        {
                this.moduleID = moduleID;
        }

        public int getProID()
        {
                return proID;
        }

        public void setProID(int proID)
        {
                this.proID = proID;
        }

        public int getPermitID()
        {
                return permitID;
        }

        public void setPermitID(int permitID)
        {
                this.permitID = permitID;
        }

        public int getAvailable()
        {
                return isAvailable;
        }

        public void setAvailable(int available)
        {
                isAvailable = available;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getDefautCheck()
        {
                return defautCheck;
        }

        public void setDefautCheck(int defautCheck)
        {
                this.defautCheck = defautCheck;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getPermitNO()
        {
                return permitNO;
        }

        public void setPermitNO(String permitNO)
        {
                this.permitNO = permitNO;
        }

        public String[] getPermitList()
        {
                return permitList;
        }

        public void setPermitList(String[] permitList)
        {
                this.permitList = permitList;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public int getStatus()
        {
                return status;
        }

        public void setStatus(int status)
        {
                this.status = status;
        }
}
