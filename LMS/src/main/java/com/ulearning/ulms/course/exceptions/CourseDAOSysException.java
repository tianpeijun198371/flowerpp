/**
 * CourseDAOSysException.java.
 * User: dengj  Date: 2004-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.exceptions;

public class CourseDAOSysException extends CourseSysException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public CourseDAOSysException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public CourseDAOSysException()
        {
                super();
        }

        /**
         * Constructs an <code>CourseSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public CourseDAOSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>CourseSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public CourseDAOSysException(Throwable nested)
        {
                super(nested);
        }
}
