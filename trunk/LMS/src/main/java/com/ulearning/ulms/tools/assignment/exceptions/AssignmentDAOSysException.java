/** * AssignmentDAOSysException.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class AssignmentDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public AssignmentDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public AssignmentDAOSysException()
        {
                super();
        }

        /**
         * Constructs an <code>AnnouncementSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public AssignmentDAOSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>AnnouncementSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public AssignmentDAOSysException(Throwable nested)
        {
                super(nested);
        }
}
