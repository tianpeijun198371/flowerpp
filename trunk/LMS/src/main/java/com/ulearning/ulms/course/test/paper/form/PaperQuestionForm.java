/**
 * PaperQuestionForm.java.
 * User: huangsb  Date: 2004-6-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.form;

import com.ulearning.ulms.course.test.paper.model.PaperQuestionModel;
import com.ulearning.ulms.course.test.paper.model.PaperQuestionModelPK;

import org.apache.struts.action.ActionForm;


public class PaperQuestionForm extends ActionForm
{
        private int paperID = 0;
        private int questionID = 0;
        private float score = 0;
        private int type = 0;

        public PaperQuestionForm()
        {
        }

        public PaperQuestionForm(PaperQuestionModel pqm)
        {
                if (pqm != null)
                {
                        this.paperID = pqm.getComp_id().getPaperid();
                        this.questionID = pqm.getComp_id().getQuestionid();
                        this.score = pqm.getScore();
                        this.type = Integer.parseInt(pqm.getType());
                }
        }

        public PaperQuestionModel getPaperQuestionModel()
        {
                PaperQuestionModel pqm = new PaperQuestionModel();
                PaperQuestionModelPK pk = new PaperQuestionModelPK(paperID, questionID);
                pqm.setComp_id(pk);
                pqm.setScore(this.score);
                pqm.setType(new Integer(this.type).toString());

                return pqm;
        }

        public int getPaperID()
        {
                return paperID;
        }

        public void setPaperID(int paperID)
        {
                this.paperID = paperID;
        }

        public int getQuestionID()
        {
                return questionID;
        }

        public void setQuestionID(int questionID)
        {
                this.questionID = questionID;
        }

        public float getScore()
        {
                return score;
        }

        public void setScore(float score)
        {
                this.score = score;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }
}
