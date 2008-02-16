/**
 * MatchClassModel.java.
 * User: zhangy Date: 2005-6-2 14:32:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.form;

import com.ulearning.ulms.match.model.MatchClassModel;
import com.ulearning.ulms.match.model.MatchClassModelPK;

import org.apache.struts.action.ActionForm;


public class MatchClassForm extends ActionForm
{
        private int matchid;
        private int modeleid;

        //Type==0: Modele=ASP，针对ASP进行匹配
        //Type==1: Modele=Course，针对课程进行匹配
        //Type==2: Modele=Certificate，针对证书进行匹配
        private String type;
        private int frequenceid = 1;

        public MatchClassForm()
        {
        }

        public MatchClassForm(MatchClassModel mcm)
        {
                this.setMatchClassModel(mcm.getComp_id(), mcm.getType(),
                        mcm.getFrequenceid());
        }

        public MatchClassModel getMatchClassModel()
        {
                MatchClassModelPK mcmPK = new MatchClassModelPK(this.matchid,
                        this.modeleid);
                MatchClassModel mcm = new MatchClassModel(mcmPK, this.type,
                        this.frequenceid);

                return mcm;
        }

        public void setMatchClassModel(MatchClassModelPK comp_id, String type,
                                       int frequenceid)
        {
                this.matchid = comp_id.getMatchid();
                this.modeleid = comp_id.getModeleid();
                this.type = type;
                this.frequenceid = frequenceid;
        }

        public int getMatchid()
        {
                return this.matchid;
        }

        public void setMatchid(int matchid)
        {
                this.matchid = matchid;
        }

        public int getModeleid()
        {
                return this.modeleid;
        }

        public void setModeleid(int modeleid)
        {
                this.modeleid = modeleid;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getFrequenceid()
        {
                return this.frequenceid;
        }

        public void setFrequenceid(int frequenceid)
        {
                this.frequenceid = frequenceid;
        }
}
