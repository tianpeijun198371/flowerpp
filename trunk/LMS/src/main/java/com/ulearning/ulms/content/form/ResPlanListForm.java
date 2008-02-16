/**
 * ResPlanListForm.java.
 * User: liz  Date: 2006-2-24
 * 设施使用情况列表Form
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.form;

import org.apache.struts.action.ActionForm;

import java.util.List;


public class ResPlanListForm extends ActionForm
{
        private String resid; //资源ID
        private List sunday; //星期日的安排ID
        private List monday; //星期一的安排ID
        private List tuesday; //星期二的安排ID
        private List wednesday; //星期三的安排ID
        private List thursday; //星期四的安排ID
        private List friday; //星期五的安排ID
        private List saturday; //星期六的安排ID

        public String getResid()
        {
                return resid;
        }

        public void setResid(String resid)
        {
                this.resid = resid;
        }

        public List getSunday()
        {
                return sunday;
        }

        public void setSunday(List sunday)
        {
                this.sunday = sunday;
        }

        public List getMonday()
        {
                return monday;
        }

        public void setMonday(List monday)
        {
                this.monday = monday;
        }

        public List getTuesday()
        {
                return tuesday;
        }

        public void setTuesday(List tuesday)
        {
                this.tuesday = tuesday;
        }

        public List getWednesday()
        {
                return wednesday;
        }

        public void setWednesday(List wednesday)
        {
                this.wednesday = wednesday;
        }

        public List getThursday()
        {
                return thursday;
        }

        public void setThursday(List thursday)
        {
                this.thursday = thursday;
        }

        public List getFriday()
        {
                return friday;
        }

        public void setFriday(List friday)
        {
                this.friday = friday;
        }

        public List getSaturday()
        {
                return saturday;
        }

        public void setSaturday(List saturday)
        {
                this.saturday = saturday;
        }
}
