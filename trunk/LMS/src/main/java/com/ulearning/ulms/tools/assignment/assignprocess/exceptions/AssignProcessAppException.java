/** * AssignProcessAppException.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class AssignProcessAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public AssignProcessAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public AssignProcessAppException()
        {
                super();
        }

        /**
         * Constructs an <code>AssignProcessAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public AssignProcessAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>AssignProcessAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public AssignProcessAppException(Throwable nested)
        {
                super(nested);
        }
}
