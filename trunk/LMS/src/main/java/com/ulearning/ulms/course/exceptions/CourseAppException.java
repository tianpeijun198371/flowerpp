/**
 * CourseAppException.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class CourseAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public CourseAppException()
        {
                super();
        }

        /**
         * Constructs an <code>CourseAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public CourseAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>CourseAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public CourseAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>CourseAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public CourseAppException(Throwable nested)
        {
                super(nested);
        }
}
