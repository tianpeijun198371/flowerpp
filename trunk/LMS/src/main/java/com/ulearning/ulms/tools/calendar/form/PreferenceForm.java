/**
 * PreferenceForm.java.
 * User: keyh  Date: 2004-8-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.form;

import com.ulearning.ulms.tools.calendar.model.PreferenceModel;

import org.apache.struts.action.ActionForm;


public class PreferenceForm extends ActionForm
{
        private int userID;
        private int LoginView;
        private String timeZone;
        private int firstDayOfWeek;
        private String hourStyle;
        private String isViewLunarCalendar;

        public PreferenceForm()
        {
        }

        public PreferenceForm(int userID, int loginView, String timeZone,
                              int firstDayOfWeek, String hourStyle, String viewLunarCalendar)
        {
                this.userID = userID;
                LoginView = loginView;
                this.timeZone = timeZone;
                this.firstDayOfWeek = firstDayOfWeek;
                this.hourStyle = hourStyle;
                isViewLunarCalendar = viewLunarCalendar;
        }

        public PreferenceModel getPreferenceModel()
        {
                PreferenceModel pm = new PreferenceModel();
                pm.setUserid(this.userID);
                pm.setLoginview(this.LoginView);
                pm.setTimezone(this.timeZone);
                pm.setFirstdayofweek(this.firstDayOfWeek);
                pm.setHourstyle(this.hourStyle);
                pm.setIsviewlunarcalendar(this.isViewLunarCalendar);

                return pm;
        }

        public PreferenceForm getPreferenceForm(PreferenceModel pm)
        {
                PreferenceForm pf = new PreferenceForm();
                pf.setUserID(pm.getUserid());
                pf.setLoginView(pm.getLoginview());
                pf.setTimeZone(pm.getTimezone());
                pf.setFirstDayOfWeek(pm.getFirstdayofweek());
                pf.setHourStyle(pm.getHourstyle());
                pf.setIsViewLunarCalendar(pm.getIsviewlunarcalendar());

                return pf;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getLoginView()
        {
                return LoginView;
        }

        public void setLoginView(int loginView)
        {
                LoginView = loginView;
        }

        public String getTimeZone()
        {
                return timeZone;
        }

        public void setTimeZone(String timeZone)
        {
                this.timeZone = timeZone;
        }

        public int getFirstDayOfWeek()
        {
                return firstDayOfWeek;
        }

        public void setFirstDayOfWeek(int firstDayOfWeek)
        {
                this.firstDayOfWeek = firstDayOfWeek;
        }

        public String getHourStyle()
        {
                return hourStyle;
        }

        public void setHourStyle(String hourStyle)
        {
                this.hourStyle = hourStyle;
        }

        public String getIsViewLunarCalendar()
        {
                return isViewLunarCalendar;
        }

        public void setIsViewLunarCalendar(String viewLunarCalendar)
        {
                isViewLunarCalendar = viewLunarCalendar;
        }
}
