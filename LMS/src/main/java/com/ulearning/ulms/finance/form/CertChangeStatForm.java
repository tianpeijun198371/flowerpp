/**
 * CertChangeStatForm.java.
 * User: liz  Date: 2006-1-9
 * Stat course of certificate resule
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.form;

import org.apache.struts.action.ActionForm;


public class CertChangeStatForm extends ActionForm
{
        private String coursename; //�γ�����
        private String coursetype; //�γ�����
        private String coursePeriod; //��ʱ
        private double coursecharge; //�շ�

        public String getCoursename()
        {
                return coursename;
        }

        public void setCoursename(String coursename)
        {
                this.coursename = coursename;
        }

        public String getCoursetype()
        {
                return coursetype;
        }

        public void setCoursetype(String coursetype)
        {
                this.coursetype = coursetype;
        }

        public String getCoursePeriod()
        {
                return coursePeriod;
        }

        public void setCoursePeriod(String coursePeriod)
        {
                this.coursePeriod = coursePeriod;
        }

        public double getCoursecharge()
        {
                return coursecharge;
        }

        public void setCoursecharge(double coursecharge)
        {
                this.coursecharge = coursecharge;
        }
}
