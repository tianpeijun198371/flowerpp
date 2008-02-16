/** * AssignmentAppException.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class AssignmentAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public AssignmentAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public AssignmentAppException()
        {
                super();
        }

        /**
         * Constructs an <code>AssignmentAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public AssignmentAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>AssignmentAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public AssignmentAppException(Throwable nested)
        {
                super(nested);
        }
}
