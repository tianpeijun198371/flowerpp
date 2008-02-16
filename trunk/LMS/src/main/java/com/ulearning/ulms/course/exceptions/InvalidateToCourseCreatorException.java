/**
 * InvalidateToCourseCreatorException.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.exceptions;

public class InvalidateToCourseCreatorException extends CourseAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public InvalidateToCourseCreatorException()
        {
                super();
        }

        /**
         * Constructs an <code>InvalidateToCourseCreatorException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public InvalidateToCourseCreatorException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>InvalidateToCourseCreatorException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public InvalidateToCourseCreatorException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>InvalidateToCourseCreatorException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public InvalidateToCourseCreatorException(Throwable nested)
        {
                super(nested);
        }
}
