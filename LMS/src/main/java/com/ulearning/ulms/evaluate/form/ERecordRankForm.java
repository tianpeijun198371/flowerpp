/**
 * eRecordRankModel.java.
 * User: YuD Date: 2005-6-13 10:40:52
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.form;

import com.ulearning.ulms.evaluate.model.ERecordRankModel;

import org.apache.struts.action.ActionForm;


public class ERecordRankForm extends ActionForm
{
        private int rankID;
        private String name;
        private String description;

        public ERecordRankForm()
        {
        }

        public ERecordRankModel geteRecordRankModel()
        {
                ERecordRankModel eRecordRankModel = new ERecordRankModel();
                eRecordRankModel.setRankID(this.rankID);
                eRecordRankModel.setName(this.name);
                eRecordRankModel.setDescription(this.description);

                return eRecordRankModel;
        }

        public ERecordRankForm geteRecordRankForm(ERecordRankModel eRecordRankModel)
        {
                ERecordRankForm eRecordRankForm = new ERecordRankForm();
                eRecordRankForm.setRankID(eRecordRankModel.getRankID());
                eRecordRankForm.setName(eRecordRankModel.getName());
                eRecordRankForm.setDescription(eRecordRankModel.getDescription());

                return eRecordRankForm;
        }

        public int getRankID()
        {
                return rankID;
        }

        public void setRankID(int rankID)
        {
                this.rankID = rankID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }
}
