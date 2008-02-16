/**
 * FinanceStatSearch_Form.java.
 * User: liz  Date: 2005-12-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import org.apache.struts.action.ActionForm;


public class FinanceStatSearch_Form extends ActionForm
{
        private int typeID; //项目类型
        private String sname; //项目名称
        private int aspid; //aspID

        public int getAspid()
        {
                return aspid;
        }

        public void setAspid(int aspid)
        {
                this.aspid = aspid;
        }

        public int getTypeID()
        {
                return typeID;
        }

        public void setTypeID(int typeID)
        {
                this.typeID = typeID;
        }

        public String getSname()
        {
                return sname;
        }

        public void setSname(String sname)
        {
                this.sname = sname;
        }
}
