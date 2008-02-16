/**
 * fAdminOrganAmount_Form.java.
 * User: liz  Date: 2005-12-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import org.apache.struts.action.ActionForm;


public class fAdminOrganAmount_Form extends ActionForm
{
        private String organName; //ÓÃ»§Ãû³Æ

        public String getOrganName()
        {
                return organName;
        }

        public void setOrganName(String organName)
        {
                this.organName = organName;
        }
}
