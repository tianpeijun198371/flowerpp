/**
 * ResPlanListForm.java.
 * User: liz  Date: 2006-2-24
 * ��ʩʹ������б�Form
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.form;

import org.apache.struts.action.ActionForm;

import java.util.List;


public class ResPlanListForm extends ActionForm
{
        private String resid; //��ԴID
        private List sunday; //�����յİ���ID
        private List monday; //����һ�İ���ID
        private List tuesday; //���ڶ��İ���ID
        private List wednesday; //�������İ���ID
        private List thursday; //�����ĵİ���ID
        private List friday; //������İ���ID
        private List saturday; //�������İ���ID

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
