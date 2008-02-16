/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.cerdangan.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class CerDanganModel implements Serializable
{
        private int danganID;
        private int adminID;
        private String certName;
        private Date dateTime;
        private int page;
        private String remark0;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;

        /**
         * default constructor
         */
        public CerDanganModel()
        {
        }

        /**
         * full constructor
         */
        public CerDanganModel(int danganID, int adminID, String certName,
                              Date dateTime, int page, String remark0, String remark1,
                              String remark2, String remark3, String remark4)
        {
                this.danganID = danganID;
                this.adminID = adminID;
                this.certName = certName;
                this.dateTime = dateTime;
                this.page = page;
                this.remark0 = remark0;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
        }

        public int getDanganID()
        {
                return danganID;
        }

        public void setDanganID(int danganID)
        {
                this.danganID = danganID;
        }

        public int getAdminID()
        {
                return adminID;
        }

        public void setAdminID(int adminID)
        {
                this.adminID = adminID;
        }

        public String getCertName()
        {
                return certName;
        }

        public void setCertName(String certName)
        {
                this.certName = certName;
        }

        public Date getDateTime()
        {
                return dateTime;
        }

        public void setDateTime(Date dateTime)
        {
                this.dateTime = dateTime;
        }

        public int getPage()
        {
                return page;
        }

        public void setPage(int page)
        {
                this.page = page;
        }

        public String getRemark0()
        {
                return remark0;
        }

        public void setRemark0(String remark0)
        {
                this.remark0 = remark0;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("danganID", getDanganID())
                        .toString();
        }
}
