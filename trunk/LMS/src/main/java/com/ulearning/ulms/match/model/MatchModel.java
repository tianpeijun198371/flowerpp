/**
 * MatchModel.java.
 * User: zhangy Date: 2005-6-2 14:32:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 *
 */
public class MatchModel implements Serializable
{
        private int matchid;
        private String type;
        private int relationid;
        private String name = "";
        private String status;

        public MatchModel(int matchid, String type, int relationid, String name,
                          String status)
        {
                this.matchid = matchid;
                this.type = type;
                this.relationid = relationid;
                this.name = name;
                this.status = status;
        }

        public MatchModel()
        {
        }

        public MatchModel(int matchid, int relationid, String name)
        {
                this.matchid = matchid;
                this.relationid = relationid;
                this.name = name;
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

        public String toString()
        {
                return new ToStringBuilder(this).append("matchid", getMatchid())
                        .toString();
        }
}
