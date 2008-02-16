/**
 * MatchModel.java.
 * User: zhangy Date: 2005-6-2 14:32:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.form;

import com.ulearning.ulms.match.model.MatchModel;

import org.apache.struts.action.ActionForm;


/**
 *
 */
public class MatchForm extends ActionForm
{
        private int matchid;
        private String type = "1";
        private int relationid;
        private String name;
        private String status = "0";

        public MatchForm(int matchid, String type, int relationid, String name,
                         String status)
        {
                this.matchid = matchid;
                this.type = type;
                this.relationid = relationid;
                this.name = name;
                this.status = status;
        }

        public MatchForm()
        {
        }

        public MatchForm(int matchid, int relationid, String name)
        {
                this.matchid = matchid;
                this.relationid = relationid;
                this.name = name;
        }

        public MatchForm(MatchModel matchModel)
        {
                this.matchid = matchModel.getMatchid();
                this.relationid = matchModel.getRelationid();
                this.name = matchModel.getName();
                this.type = matchModel.getType();
                this.status = matchModel.getStatus();
        }

        public MatchModel getMatchModel()
        {
                MatchModel mm = new MatchModel();
                mm.setMatchid(this.matchid);
                mm.setName(this.name);
                mm.setRelationid(this.relationid);
                mm.setStatus(this.status);
                mm.setType(this.type);

                return mm;
        }

        public int getMatchid()
        {
                return this.matchid;
        }

        public void setMatchid(int matchid)
        {
                this.matchid = matchid;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getRelationid()
        {
                return this.relationid;
        }

        public void setRelationid(int relationid)
        {
                this.relationid = relationid;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getStatus()
        {
                return this.status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }
}
