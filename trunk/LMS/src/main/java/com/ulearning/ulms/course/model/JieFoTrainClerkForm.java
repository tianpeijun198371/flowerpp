/**
 * JieFoTrainClerkForm.java.
 * User: huangsb  Date: 2004-10-14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.model;

import org.apache.struts.action.ActionForm;


public class JieFoTrainClerkForm extends ActionForm
{
        int trainClerkID = 0;
        int trainID = 0;
        int clerkID = 0;
        int trainClerkType = 0;

        public int getTrainClerkID()
        {
                return trainClerkID;
        }

        public void setTrainClerkID(int trainClerkID)
        {
                this.trainClerkID = trainClerkID;
        }

        public int getTrainID()
        {
                return trainID;
        }

        public void setTrainID(int trainID)
        {
                this.trainID = trainID;
        }

        public int getClerkID()
        {
                return clerkID;
        }

        public void setClerkID(int clerkID)
        {
                this.clerkID = clerkID;
        }

        public int getTrainClerkType()
        {
                return trainClerkType;
        }

        public void setTrainClerkType(int trainClerkType)
        {
                this.trainClerkType = trainClerkType;
        }
}
