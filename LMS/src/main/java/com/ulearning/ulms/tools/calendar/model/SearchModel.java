/**
 * SearchModel.java.
 * User: keyh  Date: 2004-8-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.model;

import org.apache.struts.action.ActionForm;

import java.io.Serializable;


public class SearchModel implements Serializable
{
        private int userID;
        private int orgID;
        private int sysID;
        private int relationID;
        private String eventTitle;
        private String eventDetail;

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getSysID()
        {
                return sysID;
        }

        public void setSysID(int sysID)
        {
                this.sysID = sysID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getEventTitle()
        {
                return eventTitle;
        }

        public void setEventTitle(String eventTitle)
        {
                this.eventTitle = eventTitle;
        }

        public String getEventDetail()
        {
                return eventDetail;
        }

        public void setEventDetail(String eventDetail)
        {
                this.eventDetail = eventDetail;
        }
}
