/**
 * JieFoClerkForm.java. 
 * User: huangsb  Date: 2004-10-12 
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved. 
 */
package com.ulearning.ulms.user.form;

import org.apache.struts.action.ActionForm;

import java.util.*;

public class JieFoClerkForm extends ActionForm
{
        int userID = 0;
        int organID = 0;
        String clerk_name = "";
        String name = "";
        String clerk_pwd = "";
        String clerk_sex = "";
        String clerk_job = "";
        String clerk_address = "";
        String clerk_telephone = "";
        String clerk_email = "";
        String clerk_BM = "";
        String clerk_BornDate = "";
        String clerk_xl = "";
        String clerk_WorkType = "";
        String clerk_Number = "";
        String clerk_SFZNumber = "";
        String clerk_post = "";
        String clerk_bz = "";
        int leibie = 0;
        int clerk_Status = 0;
        Date createTime = null;
        String createName = "";
        int loginTimes = 0;
        String clerk_Train = "";
        String clerk_TrainPlace = "";
        String otherC1 = "";
        String otherC2 = "";
        String otherD1 = "";
        float otherF1 = 0;
        int b_level = 0;

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getOrganID()
        {
                return organID;
        }

        public void setOrganID(int organID)
        {
                this.organID = organID;
        }

        public String getClerk_name()
        {
                return clerk_name;
        }

        public void setClerk_name(String clerk_name)
        {
                this.clerk_name = clerk_name;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getClerk_pwd()
        {
                return clerk_pwd;
        }

        public void setClerk_pwd(String clerk_pwd)
        {
                this.clerk_pwd = clerk_pwd;
        }

        public String getClerk_sex()
        {
                return clerk_sex;
        }

        public void setClerk_sex(String clerk_sex)
        {
                this.clerk_sex = clerk_sex;
        }

        public String getClerk_job()
        {
                return clerk_job;
        }

        public void setClerk_job(String clerk_job)
        {
                this.clerk_job = clerk_job;
        }

        public String getClerk_address()
        {
                return clerk_address;
        }

        public void setClerk_address(String clerk_address)
        {
                this.clerk_address = clerk_address;
        }

        public String getClerk_telephone()
        {
                return clerk_telephone;
        }

        public void setClerk_telephone(String clerk_telephone)
        {
                this.clerk_telephone = clerk_telephone;
        }

        public String getClerk_email()
        {
                return clerk_email;
        }

        public void setClerk_email(String clerk_email)
        {
                this.clerk_email = clerk_email;
        }

        public String getClerk_BM()
        {
                return clerk_BM;
        }

        public void setClerk_BM(String clerk_BM)
        {
                this.clerk_BM = clerk_BM;
        }

        public String getClerk_BornDate()
        {
                return clerk_BornDate;
        }

        public void setClerk_BornDate(String clerk_BornDate)
        {
                this.clerk_BornDate = clerk_BornDate;
        }

        public String getClerk_xl()
        {
                return clerk_xl;
        }

        public void setClerk_xl(String clerk_xl)
        {
                this.clerk_xl = clerk_xl;
        }

        public String getClerk_WorkType()
        {
                return clerk_WorkType;
        }

        public void setClerk_WorkType(String clerk_WorkType)
        {
                this.clerk_WorkType = clerk_WorkType;
        }

        public String getClerk_Number()
        {
                return clerk_Number;
        }

        public void setClerk_Number(String clerk_Number)
        {
                this.clerk_Number = clerk_Number;
        }

        public String getClerk_SFZNumber()
        {
                return clerk_SFZNumber;
        }

        public void setClerk_SFZNumber(String clerk_SFZNumber)
        {
                this.clerk_SFZNumber = clerk_SFZNumber;
        }

        public String getClerk_post()
        {
                return clerk_post;
        }

        public void setClerk_post(String clerk_post)
        {
                this.clerk_post = clerk_post;
        }

        public String getClerk_bz()
        {
                return clerk_bz;
        }

        public void setClerk_bz(String clerk_bz)
        {
                this.clerk_bz = clerk_bz;
        }

        public int getLeibie()
        {
                return leibie;
        }

        public void setLeibie(int leibie)
        {
                this.leibie = leibie;
        }

        public int getClerk_Status()
        {
                return clerk_Status;
        }

        public void setClerk_Status(int clerk_Status)
        {
                this.clerk_Status = clerk_Status;
        }

        public Date getCreateTime()
        {
                return createTime;
        }

        public void setCreateTime(Date createTime)
        {
                this.createTime = createTime;
        }

        public String getCreateName()
        {
                return createName;
        }

        public void setCreateName(String createName)
        {
                this.createName = createName;
        }

        public int getLoginTimes()
        {
                return loginTimes;
        }

        public void setLoginTimes(int loginTimes)
        {
                this.loginTimes = loginTimes;
        }

        public String getClerk_Train()
        {
                return clerk_Train;
        }

        public void setClerk_Train(String clerk_Train)
        {
                this.clerk_Train = clerk_Train;
        }

        public String getClerk_TrainPlace()
        {
                return clerk_TrainPlace;
        }

        public void setClerk_TrainPlace(String clerk_TrainPlace)
        {
                this.clerk_TrainPlace = clerk_TrainPlace;
        }

        public String getOtherC1()
        {
                return otherC1;
        }

        public void setOtherC1(String otherC1)
        {
                this.otherC1 = otherC1;
        }

        public String getOtherC2()
        {
                return otherC2;
        }

        public void setOtherC2(String otherC2)
        {
                this.otherC2 = otherC2;
        }

        public String getOtherD1()
        {
                return otherD1;
        }

        public void setOtherD1(String otherD1)
        {
                this.otherD1 = otherD1;
        }

        public float getOtherF1()
        {
                return otherF1;
        }

        public void setOtherF1(float otherF1)
        {
                this.otherF1 = otherF1;
        }

        public int getB_level()
        {
                return b_level;
        }

        public void setB_level(int b_level)
        {
                this.b_level = b_level;
        }

}
