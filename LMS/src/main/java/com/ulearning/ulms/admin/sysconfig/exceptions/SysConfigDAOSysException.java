/**
 * SysConfigDAOSysException.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class SysConfigDAOSysException extends ULMSSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public SysConfigDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public SysConfigDAOSysException()
        {
                super();
        }

        /**
         * Constructs an <code>CourseSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public SysConfigDAOSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>CourseSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public SysConfigDAOSysException(Throwable nested)
        {
                super(nested);
        }
}
