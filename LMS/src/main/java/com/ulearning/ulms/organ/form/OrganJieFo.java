/**
 * OrganJieFo.java.
 * User: huangsb  Date: 2004-10-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.organ.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class OrganJieFo extends ActionForm
{
        int OrganID = 0;
        String OrganName = "";
        String OrganBrief = "";
        String OrganTrain = "";
        int MasterID = 1;
        String OrganLevel = "";
        String OrganFatherID = "";
        Date CreateTime = null;
        String CreateName = "";
        String OrganMemo = "";
        String reserveTxt = "";
        String reserveTime = "";

        public int getOrganID()
        {
                return OrganID;
        }

        public void setOrganID(int organID)
        {
                OrganID = organID;
        }

        public String getOrganName()
        {
                return OrganName;
        }

        public void setOrganName(String organName)
        {
                OrganName = organName;
        }

        public String getOrganBrief()
        {
                return OrganBrief;
        }

        public void setOrganBrief(String organBrief)
        {
                OrganBrief = organBrief;
        }

        public String getOrganTrain()
        {
                return OrganTrain;
        }

        public void setOrganTrain(String organTrain)
        {
                OrganTrain = organTrain;
        }

        public int getMasterID()
        {
                return MasterID;
        }

        public void setMasterID(int masterID)
        {
                MasterID = masterID;
        }

        public String getOrganLevel()
        {
                return OrganLevel;
        }

        public void setOrganLevel(String organLevel)
        {
                OrganLevel = organLevel;
        }

        public String getOrganFatherID()
        {
                return OrganFatherID;
        }

        public void setOrganFatherID(String organFatherID)
        {
                OrganFatherID = organFatherID;
        }

        public Date getCreateTime()
        {
                return CreateTime;
        }

        public void setCreateTime(Date createTime)
        {
                CreateTime = createTime;
        }

        public String getCreateName()
        {
                return CreateName;
        }

        public void setCreateName(String createName)
        {
                CreateName = createName;
        }

        public String getOrganMemo()
        {
                return OrganMemo;
        }

        public void setOrganMemo(String organMemo)
        {
                OrganMemo = organMemo;
        }

        public String getReserveTxt()
        {
                return reserveTxt;
        }

        public void setReserveTxt(String reserveTxt)
        {
                this.reserveTxt = reserveTxt;
        }

        public String getReserveTime()
        {
                return reserveTime;
        }

        public void setReserveTime(String reserveTime)
        {
                this.reserveTime = reserveTime;
        }
}
