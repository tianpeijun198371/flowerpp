/**
 * CertCourseForm.java.
 * User: dengj  Date: 2004-5-17
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.form;

import com.ulearning.ulms.admin.certificate.model.CertCourseModel;
import com.ulearning.ulms.admin.certificate.model.CertCoursePK;

import org.apache.struts.action.ActionForm;


public class CertCourseForm extends ActionForm
{
        private int certificateID = 0;
        private int courseID = 0;
        private int courseType = 0;
        private int type = 0;
        private int sequence = 1;
        private double credit = 0;
        private double period = 0;

        public CertCourseForm(CertCourseModel ccm)
        {
                this.certificateID = ((CertCoursePK) ccm.getComp_id()).getCertificateID();
                this.courseID = ((CertCoursePK) ccm.getComp_id()).getCourseID();
                this.courseType = ccm.getCourseType();
                this.type = ((CertCoursePK) ccm.getComp_id()).getType();
                this.sequence = ccm.getSequence();
                this.credit = ccm.getCredit();
                this.period = ccm.getPeriod();
        }

        public CertCourseForm()
        {
        }

        public int getCertificateID()
        {
                return certificateID;
        }

        public void setCertificateID(int crtificateID)
        {
                this.certificateID = crtificateID;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getCourseType()
        {
                return courseType;
        }

        public void setCourseType(int courseType)
        {
                this.courseType = courseType;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getSequence()
        {
                return sequence;
        }

        public void setSequence(int sequence)
        {
                this.sequence = sequence;
        }

        public double getCredit()
        {
                return credit;
        }

        public void setCredit(double credit)
        {
                this.credit = credit;
        }

        public double getPeriod()
        {
                return period;
        }

        public void setPeriod(double period)
        {
                this.period = period;
        }

        public CertCourseModel getModel()
        {
                CertCourseModel ccm = new CertCourseModel();
                CertCoursePK PK = new CertCoursePK();
                PK.setCertificateID(this.getCertificateID());
                PK.setCourseID(this.getCourseID());
                PK.setType(this.getType());
                ccm.setComp_id(PK);
                ccm.setCourseType(this.courseType);
                ccm.setCredit(this.getCredit());
                ccm.setPeriod(this.getPeriod());
                ccm.setSequence(this.getSequence());

                return ccm;
        }
}
