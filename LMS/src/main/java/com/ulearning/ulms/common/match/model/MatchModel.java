/**
 * MatchModel.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.model;

import org.apache.struts.action.ActionForm;


public class MatchModel extends ActionForm
{
        private int matchID;
        private String matchKey;
        private String matchKeyID;
        private String operator;
        private String matchValue;
        private int relationID;
        private int type;
        private String defaultOperator;
        private String defaultMatchValue;
        private int aspID;

        public MatchModel()
        {
        }

        public MatchModel(int matchID, String matchKey, String matchKeyID,
                          String operator, String matchValue, int relationID, int type,
                          String defaultOperator, String defaultMatchValue, int aspID)
        {
                this.matchID = matchID;
                this.matchKey = matchKey;
                this.matchKeyID = matchKeyID;
                this.operator = operator;
                this.matchValue = matchValue;
                this.relationID = relationID;
                this.type = type;
                this.defaultOperator = defaultOperator;
                this.defaultMatchValue = defaultMatchValue;
                this.aspID = aspID;
        }

        public int getMatchID()
        {
                return matchID;
        }

        public void setMatchID(int matchID)
        {
                this.matchID = matchID;
        }

        public String getMatchKey()
        {
                return this.matchKey;
        }

        public void setMatchKey(String matchKey)
        {
                this.matchKey = matchKey;
        }

        public String getMatchKeyID()
        {
                return this.matchKeyID;
        }

        public void setMatchKeyID(String matchKeyID)
        {
                this.matchKeyID = matchKeyID;
        }

        public String getOperator()
        {
                return operator;
        }

        public void setOperator(String operator)
        {
                this.operator = operator;
        }

        public String getMatchValue()
        {
                return matchValue;
        }

        public void setMatchValue(String matchValue)
        {
                this.matchValue = matchValue;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String getDefaultOperator()
        {
                return defaultOperator;
        }

        public void setDefaultOperator(String defaultOperator)
        {
                this.defaultOperator = defaultOperator;
        }

        public String getDefaultMatchValue()
        {
                return defaultMatchValue;
        }

        public void setDefaultMatchValue(String defaultMatchValue)
        {
                this.defaultMatchValue = defaultMatchValue;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }
}
