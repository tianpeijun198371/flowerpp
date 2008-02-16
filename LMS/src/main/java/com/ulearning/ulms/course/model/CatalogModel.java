/**
 * CatalogModel.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.model;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.io.Serializable;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class CatalogModel extends ActionForm
{
        private int catalogID;
        private int parentID;
        private int orgID;
        private int aspID;
        private int type;
        private String name;
        private String description;
        private Date establishTime;
        private int level;
        private String defaultCourseIcon;
        private String icon;

        public CatalogModel(Catalog cc)
        {
                if (cc != null)
                {
                        this.catalogID = cc.getCatalogID();
                        this.parentID = cc.getParentID();
                        this.orgID = cc.getOrgID();
                        this.aspID = cc.getAspID();
                        this.type = Integer.parseInt(cc.getType());
                        this.name = cc.getName();
                        this.description = cc.getDescription();
                        this.establishTime = cc.getEstablishTime();
                        this.level = 0;
                        this.icon=cc.getIcon();
                        this.defaultCourseIcon=cc.getDefaultCourseIcon();
                }
        }

        public CatalogModel()
        {
        }

        public CatalogModel(int catalogID, String name)
        {
                this.catalogID = catalogID;
                this.name = name;
        }

        public CatalogModel(int catalogID, String name, String description)
        {
                this.catalogID = catalogID;
                this.name = name;
                this.description = description;
        }

        public CatalogModel(int catalogID, String name, String description,String icon,String defaultCourseIcon)
        {
                this.catalogID = catalogID;
                this.name = name;
                this.description = description;
                this.icon=icon;
                this.defaultCourseIcon=defaultCourseIcon;
        }

        public CatalogModel(int catalogID, int parentID, String name,
                            String description)
        {
                this.catalogID = catalogID;
                this.name = name;
                this.description = description;
                this.parentID = parentID;
        }

        public int getLevel()
        {
                return level;
        }

        public void setLevel(int level)
        {
                this.level = level;
        }

        public String getDefaultCourseIcon()
        {
                return defaultCourseIcon;
        }

        public void setDefaultCourseIcon(String defaultCourseIcon)
        {
                this.defaultCourseIcon = defaultCourseIcon;
        }

        public String getIcon()
        {
                return icon;
        }

        public void setIcon(String icon)
        {
                this.icon = icon;
        }

        public int getCatalogID()
        {
                return catalogID;
        }

        public void setCatalogID(int catalogID)
        {
                this.catalogID = catalogID;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Date getEstablishTime()
        {
                return establishTime;
        }

        public void setEstablishTime(Date establishTime)
        {
                this.establishTime = establishTime;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        /**
         * Validate the properties that have been set from this HTTP request,
         * and return an <code>ActionErrors</code> object that encapsulates any
         * validation errors that have been found.  If no errors are found, return
         * <code>null</code> or an <code>ActionErrors</code> object with no
         * recorded error messages.
         *
         * @param mapping The mapping used to select this instance
         * @param request The servlet request we are processing
         */
        public ActionErrors validate(ActionMapping mapping,
                                     HttpServletRequest request)
        {
                ActionErrors errors = new ActionErrors();

                if ((name == null) || (name.length() < 1))
                {
                        errors.add("name", new ActionError("error.catalog.name.required"));
                }

                return (errors);
        }

        public Catalog getCatalog()
        {
                Catalog cc = new Catalog();
                cc.setCatalogID(this.catalogID);
                cc.setParentID(this.parentID);
                cc.setOrgID(this.orgID);
                cc.setAspID(this.aspID);
                cc.setType(String.valueOf(this.type));
                cc.setName(this.name);
                cc.setDescription(this.description);
                cc.setEstablishTime(this.establishTime);
                cc.setIcon(this.icon);
                cc.setDefaultCourseIcon(this.defaultCourseIcon);
                return cc;
        }
}
