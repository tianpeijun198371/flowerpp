/**
 * GradeWeightingFactorModel.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.model;

import java.io.Serializable;


public class GradeWeightingFactorModel implements Serializable
{
        private TypeRelationIDPK comp_id;
        private float exersicewf;
        private float testwf;
        private float examwf;

        public GradeWeightingFactorModel()
        {
        }

        public GradeWeightingFactorModel(TypeRelationIDPK comp_id,
                                         float exersicewf, float testwf, float examwf)
        {
                this.comp_id = comp_id;
                this.exersicewf = exersicewf;
                this.testwf = testwf;
                this.examwf = examwf;
        }

        public TypeRelationIDPK getComp_id()
        {
                return comp_id;
        }

        public void setComp_id(TypeRelationIDPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public float getExersicewf()
        {
                return exersicewf;
        }

        public void setExersicewf(float exersicewf)
        {
                this.exersicewf = exersicewf;
        }

        public float getTestwf()
        {
                return testwf;
        }

        public void setTestwf(float testwf)
        {
                this.testwf = testwf;
        }

        public float getExamwf()
        {
                return examwf;
        }

        public void setExamwf(float examwf)
        {
                this.examwf = examwf;
        }
}
