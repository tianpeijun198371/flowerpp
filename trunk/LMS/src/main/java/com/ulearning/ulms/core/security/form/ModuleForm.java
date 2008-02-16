/**
 * ModuleForm.java.
 * User: dengj  Date: 2004-7-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.form;

public class ModuleForm
{
        private int moduleID = 0;
        private String name = null;
        private int sysID = 0;
        private String description = null;

        public int getModuleID()
        {
                return moduleID;
        }

        public void setModuleID(int moduleID)
        {
                this.moduleID = moduleID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public int getSysID()
        {
                return sysID;
        }

        public void setSysID(int sysID)
        {
                this.sysID = sysID;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }
}
