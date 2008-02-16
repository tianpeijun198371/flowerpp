/**
 * JieFoCourseForm.java.
 * User: huangsb  Date: 2004-10-14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.model;

import org.apache.struts.action.ActionForm;


public class JieFoCourseForm extends ActionForm
{
        int trainID = 0;
        String trainName = "";

        public int getTrainID()
        {
                return trainID;
        }

        public void setTrainID(int trainID)
        {
                this.trainID = trainID;
        }

        public String getTrainName()
        {
                return trainName;
        }

        public void setTrainName(String trainName)
        {
                this.trainName = trainName;
        }
}
