/**
 * SearchForm.java.
 * User: keyh  Date: 2004-8-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.form;

import org.apache.struts.action.ActionForm;


public class SearchForm extends ActionForm
{
        private int userID;
        private int orgID;
        private int sysID;
        private int type;
        private int relationID;
        private String eventTitle;
        private String eventDetail;

        public SearchForm()
        {
        }

        public SearchForm(int userID, int orgID, int sysID, int type,
                          int relationID, String eventTitle, String eventDetail)
        {
                this.userID = userID;
                this.orgID = orgID;
                this.sysID = sysID;
                this.type = type;
                this.relationID = relationID;
                this.eventTitle = eventTitle;
                this.eventDetail = eventDetail;
        }

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

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
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
