/**
 * ERecordModel.java.
 * User: fengch Date: 2005-6-13 10:33:26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.model;

public class ERecordRankModel
{
        private int rankID;
        private String name;
        private String description;

        public ERecordRankModel()
        {
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
