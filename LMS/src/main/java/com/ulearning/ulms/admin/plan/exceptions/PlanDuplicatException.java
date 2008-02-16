/**
 * PlanDuplicatException.java.
 * User: huangsb  Date: 2004-5-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.plan.exceptions;

public class PlanDuplicatException extends Exception
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public PlanDuplicatException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public PlanDuplicatException()
        {
                super();
        }
}
