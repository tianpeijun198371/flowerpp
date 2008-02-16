/**
 * ERecordModel.java.
 * User: fengch Date: 2005-6-13 10:33:26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.model;

public class ERecordModel
{
        private int eRecordID;
        private int userID;
        private int point;

        public ERecordModel()
        {
        }

        public int geteRecordID()
        {
                return eRecordID;
        }

        public void seteRecordID(int eRecordID)
        {
                this.eRecordID = eRecordID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
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
