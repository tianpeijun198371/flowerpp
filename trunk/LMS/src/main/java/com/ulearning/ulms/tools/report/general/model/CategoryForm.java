/** * CategoryForm.java. 
 * User: xiejh  Date: 2004-7-19 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */

package com.ulearning.ulms.tools.report.general.model;

import org.apache.struts.action.ActionForm;

public class CategoryForm extends ActionForm
{
        private int categoryID = 0;
        private int modulID = 0;
        private String categoryName = null;
        private String state = null;

        public int getCategoryID()
        {
                return categoryID;
        }

        public void setCategoryID(int categoryID)
        {
                this.categoryID = categoryID;
        }

        public int getModulID()
        {
                return modulID;
        }

        public void setModulID(int modulID)
        {
                this.modulID = modulID;
        }

        public String getCategoryName()
        {
                return categoryName;
        }

        public void setCategoryName(String categoryName)
        {
                this.categoryName = categoryName;
        }

        public String getState()
        {
                return state;
        }

        public void setState(String state)
        {
                this.state = state;
        }
}