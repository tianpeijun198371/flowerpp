/**
 * MatchItermModel.java.
 * User: zhangy Date: 2005-6-2 14:32:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.form;

import com.ulearning.ulms.match.model.MatchItermModel;
import com.ulearning.ulms.match.model.MatchItermModelPK;

import org.apache.struts.action.ActionForm;


public class MatchItermForm extends ActionForm
{
        private int matchid;
        private int ItermID;

        /**
         * nullable persistent field
         */
        private String matchvalue;

        /**
         * nullable persistent field
         */
        private String operator;

        /**
         * default constructor
         */
        public MatchItermForm()
        {
        }

        public MatchItermForm(MatchItermModel mim)
        {
                this.matchid = mim.getComp_id().getMatchid();
                this.ItermID = mim.getComp_id().getItermid();
                this.matchvalue = mim.getMatchvalue();
                this.operator = mim.getOperator();
        }

        public MatchItermModel getMatchItermModel()
        {
                MatchItermModel mim = new MatchItermModel();
                MatchItermModelPK mmpk = new MatchItermModelPK(this.matchid,
                        this.ItermID);
                mim.setComp_id(mmpk);
                mim.setMatchvalue(this.matchvalue);
                mim.setOperator(this.operator);

                return mim;
        }

        public void setMatchid(int MatchID)
        {
                this.matchid = MatchID;
        }

        public int getMatchid()
        {
                return this.matchid;
        }

        public void setItermid(int ItermID)
        {
                this.ItermID = ItermID;
        }

        public int getItermid()
        {
                return this.ItermID;
        }

        public String getMatchvalue()
        {
                return this.matchvalue;
        }

        public void setMatchvalue(String matchvalue)
        {
                this.matchvalue = matchvalue;
        }

        public String getOperator()
        {
                return this.operator;
        }

        public void setOperator(String operator)
        {
                this.operator = operator;
        }
}
