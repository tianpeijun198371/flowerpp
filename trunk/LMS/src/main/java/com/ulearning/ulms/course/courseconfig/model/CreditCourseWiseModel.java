/**
 * CreditCourseOrderModel.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.model;

import java.io.Serializable;


public class CreditCourseWiseModel implements Serializable
{
        private TypeRelationIDPK comp_id;
        private int wise;

        public CreditCourseWiseModel()
        {
        }

        public CreditCourseWiseModel(TypeRelationIDPK comp_id, int wise)
        {
                this.comp_id = comp_id;
                this.wise = wise;
        }

        public TypeRelationIDPK getComp_id()
        {
                return comp_id;
        }

        public void setComp_id(TypeRelationIDPK comp_id)
        {
                this.comp_id = comp_id;
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
