/**
 * UserExistedInCourseException.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.exceptions;

public class UserExistedInCourseException extends CourseAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public UserExistedInCourseException()
        {
                super();
        }

        /**
         * Constructs an <code>UserExistedInCourseException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public UserExistedInCourseException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>UserExistedInCourseException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public UserExistedInCourseException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>UserExistedInCourseException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public UserExistedInCourseException(Throwable nested)
        {
                super(nested);
        }
}
