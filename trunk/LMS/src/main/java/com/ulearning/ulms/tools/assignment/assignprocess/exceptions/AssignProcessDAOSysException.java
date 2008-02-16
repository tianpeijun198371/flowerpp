/** * AssignProcessDAOSysException.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class AssignProcessDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public AssignProcessDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public AssignProcessDAOSysException()
        {
                super();
        }

        /**
         * Constructs an <code>AnnouncementSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public AssignProcessDAOSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>AnnouncementSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public AssignProcessDAOSysException(Throwable nested)
        {
                super(nested);
        }
}
