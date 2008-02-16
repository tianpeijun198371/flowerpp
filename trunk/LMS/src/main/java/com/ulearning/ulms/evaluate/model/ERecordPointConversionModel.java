/**
 * ERecordModel.java.
 * User: fengch Date: 2005-6-13 10:33:26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.model;

public class ERecordPointConversionModel
{
        private int actionID;
        private int point;

        public ERecordPointConversionModel()
        {
        }

        public int getActionID()
        {
                return actionID;
        }

        public void setActionID(int actionID)
        {
                this.actionID = actionID;
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
