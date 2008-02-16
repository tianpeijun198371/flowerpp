/**
 * certChangeStat_form.java.
 * User: liz  Date: 2006-1-9
 * Stat condition of course of Certificate
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.stat.action;

import org.apache.struts.action.ActionForm;


public class certChangeStat_form extends ActionForm
{
        private String cername; //班级名称
        private String cerid; //班级ID
        private int typeid; //1 返回证书对应的发布课程    2 返回证书末使用范本的课程

        public String getCername()
        {
                return cername;
        }

        public void setCername(String cername)
        {
                this.cername = cername;
        }

        public String getCerid()
        {
                return cerid;
        }

        public void setCerid(String cerid)
        {
                this.cerid = cerid;
        }

        public int getTypeid()
        {
                return typeid;
        }

        public void setTypeid(int typeid)
        {
                this.typeid = typeid;
        }
}
