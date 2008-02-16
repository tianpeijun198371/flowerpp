/**
 * ModulePropertyForm.java.
 * User: dengj  Date: 2004-5-5
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.form;

public class ModulePropertyForm
{
        private int moduleID = 0;
        private int proID = 0;
        private int type = 0;
        private int relationID = 0;
        private String description = null;

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

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
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
