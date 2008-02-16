/**
 * JobModel.java.
 * User: fengch  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.model;

import java.util.List;


public class JobModel
{
        private int relationID;
        private int type;
        private int aspID;
        private List mms;

        public JobModel()
        {
        }

        public JobModel(int relationID, int type, int aspID)
        {
                this.relationID = relationID;
                this.type = type;
                this.aspID = aspID;
        }

        public JobModel(int relationID, int type, List mms)
        {
                this.relationID = relationID;
                this.type = type;
                this.mms = mms;
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

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public List getMms()
        {
                return mms;
        }

        public void setMms(List mm)
        {
                this.mms = mm;
        }
}
