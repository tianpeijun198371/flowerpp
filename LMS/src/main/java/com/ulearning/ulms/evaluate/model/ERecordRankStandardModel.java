/**
 * ERecordModel.java.
 * User: fengch Date: 2005-6-13 10:33:26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.model;

public class ERecordRankStandardModel
{
        private int rankID;
        private int point;

        public ERecordRankStandardModel()
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

        public int getPoint()
        {
                return point;
        }

        public void setPoint(int point)
        {
                this.point = point;
        }
}
