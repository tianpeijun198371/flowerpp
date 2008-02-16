/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.resuserecord.model;

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
public class ResuserecordModel implements Serializable
{
        private int resuseID = 0;
        private int resID = 0;
        private String resName = "";
        private int aspID = 0;
        private int orgID = 0;
        private String aspName = "";
        private String orgName = "";
        private int userID = 0;
        private String userName = "";
        private Date userbegindate = new Date();
        private Date userenddate = new Date();
        private String respurpost = "";
        private int userdel = 0;
        private int audittag = 0;
        private int audituserID = 0;
        private String audituserName = "";
        private String uresremark1 = "";
        private String uresremark2 = "";

        /**
         * default constructor
         */
        public ResuserecordModel()
        {
        }

        /**
         * full constructor
         */
        public ResuserecordModel(int resuseID, int resID, String resName,
                                 int aspID, int orgID, String aspName, String orgName, int userID,
                                 String userName, Date userbegindate, Date userenddate,
                                 String respurpost, int userdel, int audittag, int audituserID,
                                 String audituserName, String uresremark1, String uresremark2)
        {
                this.resuseID = resuseID;
                this.resID = resID;
                this.resName = resName;
                this.aspID = aspID;
                this.orgID = orgID;
                this.aspName = aspName;
                this.orgName = orgName;
                this.userID = userID;
                this.userName = userName;
                this.userbegindate = userbegindate;
                this.userenddate = userenddate;
                this.respurpost = respurpost;
                this.userdel = userdel;
                this.audittag = audittag;
                this.audituserID = audituserID;
                this.audituserName = audituserName;
                this.uresremark1 = uresremark1;
                this.uresremark2 = uresremark2;
        }

        public int getResuseID()
        {
                return resuseID;
        }

        public void setResuseID(int resuseID)
        {
                this.resuseID = resuseID;
        }

        public int getResID()
        {
                return resID;
        }

        public void setResID(int resID)
        {
                this.resID = resID;
        }

        public String getResName()
        {
                return resName;
        }

        public void setResName(String resName)
        {
                this.resName = resName;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getAspName()
        {
                return aspName;
        }

        public void setAspName(String aspName)
        {
                this.aspName = aspName;
        }

        public String getOrgName()
        {
                return orgName;
        }

        public void setOrgName(String orgName)
        {
                this.orgName = orgName;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public Date getUserbegindate()
        {
                return userbegindate;
        }

        public void setUserbegindate(Date userbegindate)
        {
                this.userbegindate = userbegindate;
        }

        public Date getUserenddate()
        {
                return userenddate;
        }

        public void setUserenddate(Date userenddate)
        {
                this.userenddate = userenddate;
        }

        public String getRespurpost()
        {
                return respurpost;
        }

        public void setRespurpost(String respurpost)
        {
                this.respurpost = respurpost;
        }

        public int getUserdel()
        {
                return userdel;
        }

        public void setUserdel(int userdel)
        {
                this.userdel = userdel;
        }

        public int getAudittag()
        {
                return audittag;
        }

        public void setAudittag(int audittag)
        {
                this.audittag = audittag;
        }

        public int getAudituserID()
        {
                return audituserID;
        }

        public void setAudituserID(int audituserID)
        {
                this.audituserID = audituserID;
        }

        public String getAudituserName()
        {
                return audituserName;
        }

        public void setAudituserName(String audituserName)
        {
                this.audituserName = audituserName;
        }

        public String getUresremark1()
        {
                return uresremark1;
        }

        public void setUresremark1(String uresremark1)
        {
                this.uresremark1 = uresremark1;
        }

        public String getUresremark2()
        {
                return uresremark2;
        }

        public void setUresremark2(String uresremark2)
        {
                this.uresremark2 = uresremark2;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("resuseID", getResuseID())
                        .toString();
        }
}
