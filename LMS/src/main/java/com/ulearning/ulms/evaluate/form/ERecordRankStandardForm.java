/**
 * eRecordRankStandardModel.java.
 * User: YuD Date: 2005-6-13 10:40:52
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.form;

import com.ulearning.ulms.evaluate.model.ERecordRankStandardModel;

import org.apache.struts.action.ActionForm;


public class ERecordRankStandardForm extends ActionForm
{
        private int rankID;
        private int point;

        public ERecordRankStandardForm()
        {
        }

        public ERecordRankStandardModel geteRecordRankStandardModel()
        {
                ERecordRankStandardModel eRecordRankStandardModel = new ERecordRankStandardModel();
                eRecordRankStandardModel.setRankID(this.rankID);
                eRecordRankStandardModel.setPoint(this.point);

                return eRecordRankStandardModel;
        }

        public ERecordRankStandardForm geteRecordRankStandardForm(
                ERecordRankStandardModel eRecordRankStandardModel)
        {
                ERecordRankStandardForm eRecordRankStandardForm = new ERecordRankStandardForm();
                eRecordRankStandardForm.setRankID(eRecordRankStandardModel.getRankID());
                eRecordRankStandardForm.setPoint(eRecordRankStandardModel.getPoint());

                return eRecordRankStandardForm;
        }

        public int getRankID()
        {
                return rankID;
        }

        public void setRankID(int rankID)
        {
                this.rankID = rankID;
        }

        public int getPoint()
        {
                return point;
        }

        public void setPoint(int point)
        {
                this.point = point;
        }
}
