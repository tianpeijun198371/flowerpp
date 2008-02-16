//Source file: d:\\ulms\\source\\com\\eduedu\\ulms\\tools\\calendar\\model\\EventModel.java
package com.ulearning.ulms.tools.calendar.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class EventModel implements Serializable
{
        /**
         * identifier field
         */
        private int eventid;

        /**
         * nullable persistent field
         */
        private int relationid;

        /**
         * persistent field
         */
        private int type;

        /**
         * persistent field
         */
        private int userid;

        /**
         * persistent field
         */
        private int orgid;

        /**
         * persistent field
         */
        private Date eventdate;

        /**
         * persistent field
         */
        private int eventstarthour;

        /**
         * persistent field
         */
        private int eventstophour;

        /**
         * persistent field
         */
        private int eventstartmin;

        /**
         * persistent field
         */
        private int eventstopmin;

        /**
         * persistent field
         */
        private String eventtitle;

        /**
         * nullable persistent field
         */
        private String eventdetail;

        /**
         * nullable persistent field
         */
        private String isremind;

        /**
         * nullable persistent field
         */
        private String remindtime;

        /**
         * full constructor
         */
        public EventModel(int eventid, int relationid, int type, int userid,
                          int orgid, Date eventdate, int eventstarthour, int eventstophour,
                          int eventstartmin, int eventstopmin, String eventtitle,
                          String eventdetail, String isremind, String remindtime)
        {
                this.eventid = eventid;
                this.relationid = relationid;
                this.type = type;
                this.userid = userid;
                this.orgid = orgid;
                this.eventdate = eventdate;
                this.eventstarthour = eventstarthour;
                this.eventstophour = eventstophour;
                this.eventstartmin = eventstartmin;
                this.eventstopmin = eventstopmin;
                this.eventtitle = eventtitle;
                this.eventdetail = eventdetail;
                this.isremind = isremind;
                this.remindtime = remindtime;
        }

        /**
         * default constructor
         */
        public EventModel()
        {
        }

        /**
         * minimal constructor
         */
        public EventModel(int eventid, int type, int userid, int orgid,
                          Date eventdate, int eventstarthour, int eventstophour,
                          int eventstartmin, int eventstopmin, String eventtitle)
        {
                this.eventid = eventid;
                this.type = type;
                this.userid = userid;
                this.orgid = orgid;
                this.eventdate = eventdate;
                this.eventstarthour = eventstarthour;
                this.eventstophour = eventstophour;
                this.eventstartmin = eventstartmin;
                this.eventstopmin = eventstopmin;
                this.eventtitle = eventtitle;
        }

        public int getEventid()
        {
                return this.eventid;
        }

        public void setEventid(int eventid)
        {
                this.eventid = eventid;
        }

        public int getRelationid()
        {
                return this.relationid;
        }

        public void setRelationid(int relationid)
        {
                this.relationid = relationid;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public Date getEventdate()
        {
                return this.eventdate;
        }

        public void setEventdate(Date eventdate)
        {
                this.eventdate = eventdate;
        }

        public int getEventstarthour()
        {
                return this.eventstarthour;
        }

        public void setEventstarthour(int eventstarthour)
        {
                this.eventstarthour = eventstarthour;
        }

        public int getEventstophour()
        {
                return this.eventstophour;
        }

        public void setEventstophour(int eventstophour)
        {
                this.eventstophour = eventstophour;
        }

        public int getEventstartmin()
        {
                return this.eventstartmin;
        }

        public void setEventstartmin(int eventstartmin)
        {
                this.eventstartmin = eventstartmin;
        }

        public int getEventstopmin()
        {
                return this.eventstopmin;
        }

        public void setEventstopmin(int eventstopmin)
        {
                this.eventstopmin = eventstopmin;
        }

        public String getEventtitle()
        {
                return this.eventtitle;
        }

        public void setEventtitle(String eventtitle)
        {
                this.eventtitle = eventtitle;
        }

        public String getEventdetail()
        {
                return this.eventdetail;
        }

        public void setEventdetail(String eventdetail)
        {
                this.eventdetail = eventdetail;
        }

        public String getIsremind()
        {
                return this.isremind;
        }

        public void setIsremind(String isremind)
        {
                this.isremind = isremind;
        }

        public String getRemindtime()
        {
                return this.remindtime;
        }

        public void setRemindtime(String remindtime)
        {
                this.remindtime = remindtime;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("eventid", getEventid())
                        .toString();
        }
}
