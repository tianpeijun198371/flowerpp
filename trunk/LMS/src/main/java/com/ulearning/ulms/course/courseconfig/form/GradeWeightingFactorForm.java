/**
 * GradeWeightingFactorForm.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.form;

import com.ulearning.ulms.course.courseconfig.model.GradeWeightingFactorModel;
import com.ulearning.ulms.course.courseconfig.model.TypeRelationIDPK;

import org.apache.struts.action.ActionForm;


public class GradeWeightingFactorForm extends ActionForm
{
        private int typeID;
        private int relationID;
        private float exerciseWF;
        private float testWF;
        private float examWF;

        public GradeWeightingFactorForm()
        {
        }

        public GradeWeightingFactorForm(int typeID, int relationID,
                                        float exerciseWF, float testWF, float examWF)
        {
                this.typeID = typeID;
                this.relationID = relationID;
                this.exerciseWF = exerciseWF;
                this.testWF = testWF;
                this.examWF = examWF;
        }

        public GradeWeightingFactorModel getGradeWeightingFactorModel()
        {
                GradeWeightingFactorModel gwfm = new GradeWeightingFactorModel();
                TypeRelationIDPK comp_id = new TypeRelationIDPK();

                comp_id.setTypeid(this.typeID);
                comp_id.setRelationid(this.relationID);
                gwfm.setComp_id(comp_id);
                gwfm.setExersicewf(this.exerciseWF);
                gwfm.setTestwf(this.testWF);
                gwfm.setExamwf(this.examWF);

                return gwfm;
        }

        public GradeWeightingFactorForm getGradeWeightingFactorForm(
                GradeWeightingFactorModel gwfm)
        {
                GradeWeightingFactorForm gwff = new GradeWeightingFactorForm();
                gwff.setTypeID(gwfm.getComp_id().getTypeid());
                gwff.setRelationID(gwfm.getComp_id().getRelationid());
                gwff.setExerciseWF(gwfm.getExersicewf());
                gwff.setTestWF(gwfm.getTestwf());
                gwff.setExamWF(gwfm.getExamwf());

                return gwff;
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

        public float getExerciseWF()
        {
                return exerciseWF;
        }

        public void setExerciseWF(float exerciseWF)
        {
                this.exerciseWF = exerciseWF;
        }

        public float getTestWF()
        {
                return testWF;
        }

        public void setTestWF(float testWF)
        {
                this.testWF = testWF;
        }

        public float getExamWF()
        {
                return examWF;
        }

        public void setExamWF(float examWF)
        {
                this.examWF = examWF;
        }
}
