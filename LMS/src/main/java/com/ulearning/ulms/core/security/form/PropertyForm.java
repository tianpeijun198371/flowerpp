/**
 * PropertyForm.java.
 * User: dengj  Date: 2004-5-6
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.form;

import java.util.List;


public class PropertyForm
{
        private int ModuleID = 0;
        private int ProID = 0;
        private int IsAvailable = 0;
        private String Name = null;
        private String ProNo = null;
        private String Description = null;
        private List permitList = null;

        public int getModuleID()
        {
                return ModuleID;
        }

        public void setModuleID(int moduleID)
        {
                ModuleID = moduleID;
        }

        public int getProID()
        {
                return ProID;
        }

        public void setProID(int proID)
        {
                ProID = proID;
        }

        public int getIsAvailable()
        {
                return IsAvailable;
        }

        public void setIsAvailable(int isAvailable)
        {
                IsAvailable = isAvailable;
        }

        public String getName()
        {
                return Name;
        }

        public void setName(String name)
        {
                Name = name;
        }

        public String getProNo()
        {
                return ProNo;
        }

        public void setProNo(String proNo)
        {
                ProNo = proNo;
        }

        public String getDescription()
        {
                return Description;
        }

        public void setDescription(String description)
        {
                Description = description;
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
