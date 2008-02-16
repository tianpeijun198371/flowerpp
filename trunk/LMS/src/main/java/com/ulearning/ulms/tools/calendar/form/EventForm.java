/**
 * EventForm.java.
 * User: keyh  Date: 2004-8-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.calendar.model.EventModel;

import org.apache.struts.action.ActionForm;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class EventForm extends ActionForm
{
        private int eventID;
        private int relationID;
        private int type;
        private int userID;
        private int orgID;
        private String eventDate;
        private int startHour;
        private int stopHour;
        private int startMin;
        private int stopMin;
        private String eventTitle;
        private String eventDetail;
        private String isRemind;
        private String remindTime;

        public EventForm()
        {
        }

        public EventForm(int eventID, int relationID, int type, int userID,
                         int orgID, Date dispEventDate, String eventDate, int startHour,
                         int stopHour, int startMin, int stopMin, String eventTitle,
                         String eventDetail, String remind, String remindTime)
        {
                this.eventID = eventID;
                this.relationID = relationID;
                this.type = type;
                this.userID = userID;
                this.orgID = orgID;
                this.eventDate = eventDate;
                this.startHour = startHour;
                this.stopHour = stopHour;
                this.startMin = startMin;
                this.stopMin = stopMin;
                this.eventTitle = eventTitle;
                this.eventDetail = eventDetail;
                isRemind = remind;
                this.remindTime = remindTime;
        }

        public EventModel getEventModel()
        {
                EventModel eventModel = new EventModel();
                eventModel.setEventid(this.eventID);
                eventModel.setRelationid(this.relationID);
                eventModel.setType(this.type);
                eventModel.setUserid(this.userID);
                eventModel.setOrgid(this.orgID);

                List times = StringUtil.split(this.eventDate, "-");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, Integer.parseInt((String) times.get(0)));
                cal.set(Calendar.MONTH, Integer.parseInt((String) times.get(1)) - 1);
                cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt((String) times.get(2)));

                eventModel.setEventdate(cal.getTime());
                eventModel.setEventstarthour(this.startHour);
                eventModel.setEventstartmin(this.startMin);
                eventModel.setEventstophour(this.stopHour);
                eventModel.setEventstopmin(this.stopMin);
                eventModel.setEventtitle(this.eventTitle);
                eventModel.setEventdetail(this.eventDetail);
                eventModel.setIsremind(this.isRemind);
                eventModel.setRemindtime(this.remindTime);

                return eventModel;
        }

        public EventForm getEventForm(EventModel em)
        {
                EventForm ef = new EventForm();
                ef.setEventID(em.getEventid());
                ef.setRelationID(em.getRelationid());
                ef.setType(em.getType());
                ef.setUserID(em.getUserid());
                ef.setOrgID(em.getOrgid());

                Calendar cal = Calendar.getInstance();
                cal.setTime(em.getEventdate());

                int yy = cal.get(Calendar.YEAR);
                int mm = cal.get(Calendar.MONTH) + 1;
                int dd = cal.get(Calendar.DAY_OF_MONTH);
                ef.setEventDate("" + yy + "-" + mm + "-" + dd);
                ef.setStartHour(em.getEventstarthour());
                ef.setStartMin(em.getEventstartmin());
                ef.setStopHour(em.getEventstophour());
                ef.setStopMin(em.getEventstopmin());
                ef.setEventTitle(em.getEventtitle());
                ef.setEventDetail(em.getEventdetail());
                ef.setIsRemind(em.getIsremind());
                ef.setRemindTime(em.getRemindtime());

                return ef;
        }

        public int getEventID()
        {
                return eventID;
        }

        public void setEventID(int eventID)
        {
                this.eventID = eventID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
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

        public String getEventDate()
        {
                return eventDate;
        }

        public void setEventDate(String eventDate)
        {
                this.eventDate = eventDate;
        }

        public String getRemind()
        {
                return isRemind;
        }

        public void setRemind(String remind)
        {
                isRemind = remind;
        }

        public int getStartHour()
        {
                return startHour;
        }

        public void setStartHour(int startHour)
        {
                this.startHour = startHour;
        }

        public int getStopHour()
        {
                return stopHour;
        }

        public void setStopHour(int stopHour)
        {
                this.stopHour = stopHour;
        }

        public int getStartMin()
        {
                return startMin;
        }

        public void setStartMin(int startMin)
        {
                this.startMin = startMin;
        }

        public int getStopMin()
        {
                return stopMin;
        }

        public void setStopMin(int stopMin)
        {
                this.stopMin = stopMin;
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

        public String getIsRemind()
        {
                return isRemind;
        }

        public void setIsRemind(String remind)
        {
                isRemind = remind;
        }

        public String getRemindTime()
        {
                return remindTime;
        }

        public void setRemindTime(String remindTime)
        {
                this.remindTime = remindTime;
        }

        /**
         * 　根据上面事件的时间合成需要的时间形式。
         *
         * @return 合成的按排日期：HH:mm--HH:mm
         * @ author Liz
         * @ date 2005.11.22
         */
        public StringBuffer ComposeDate()
        {
                String getStartHour = "";
                String getStartMin = "";
                String getStopHour = "";
                String getStopMin = "";
                StringBuffer sRen = new StringBuffer();

                if (getStartHour() < 10)
                {
                        getStartHour = "0" + getStartHour();
                }
                else
                {
                        getStartHour = getStartHour() + "";
                }

                if (getStartMin() < 10)
                {
                        getStartMin = "0" + getStartMin();
                }
                else
                {
                        getStartMin = getStartMin() + "";
                }

                if (getStopHour() < 10)
                {
                        getStopHour = "0" + getStopHour();
                }
                else
                {
                        getStopHour = getStopHour() + "";
                }

                if (getStopMin() < 10)
                {
                        getStopMin = "0" + getStopMin();
                }
                else
                {
                        getStopMin = getStopMin() + "";
                }

                //合成
                sRen.append(getStartHour);
                sRen.append(":");
                sRen.append(getStartMin);
                sRen.append("--");
                sRen.append(getStopHour);
                sRen.append(":");
                sRen.append(getStopMin);

                return sRen;
        }
}
