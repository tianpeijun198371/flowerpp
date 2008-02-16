/**
 * CreditCourseOrderModel.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.form;

import com.ulearning.ulms.course.courseconfig.model.CreditCourseWiseModel;
import com.ulearning.ulms.course.courseconfig.model.TypeRelationIDPK;

import org.apache.struts.action.ActionForm;


public class CreditCourseWiseForm extends ActionForm
{
        private int typeID;
        private int relationID;
        private int wise; //1:coursefirst;0:creditfirst;

        public CreditCourseWiseForm(int typeID, int relationID, int wise)
        {
                this.typeID = typeID;
                this.relationID = relationID;
                this.wise = wise;
        }

        public CreditCourseWiseForm()
        {
        }

        public CreditCourseWiseModel getCreditCourseWiseModel()
        {
                CreditCourseWiseModel ccwm = new CreditCourseWiseModel();
                TypeRelationIDPK comp_id = new TypeRelationIDPK();

                comp_id.setTypeid(this.typeID);
                comp_id.setRelationid(this.relationID);
                ccwm.setWise(this.wise);
                ccwm.setComp_id(comp_id);

                return ccwm;
        }

        public CreditCourseWiseForm getCreditCourseWiseForm(
                CreditCourseWiseModel ccwm)
        {
                CreditCourseWiseForm ccwf = new CreditCourseWiseForm();
                ccwf.setTypeID(ccwm.getComp_id().getTypeid());
                ccwf.setRelationID(ccwm.getComp_id().getRelationid());
                ccwf.setWise(ccwm.getWise());

                return ccwf;
        }

        public int getTypeID()
        {
                return typeID;
        }

        public void setTypeID(int typeID)
        {
                this.typeID = typeID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getWise()
        {
                return wise;
        }

        public void setWise(int wise)
        {
                this.wise = wise;
        }
}
