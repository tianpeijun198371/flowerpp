/**
 * CourseCodeRepeatedException.java.
 * User: fengch  Date: 2004-5-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.exceptions;

public class CourseCodeRepeatedException extends CourseAppException
{
        /**
         * Creates new <code>CourseCodeRepeatedException</code> without detail message.
         */
        public CourseCodeRepeatedException()
        {
                super();
        }

        /**
         * Constructs an <code>CourseCodeRepeatedException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public CourseCodeRepeatedException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>CourseCodeRepeatedException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public CourseCodeRepeatedException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>CourseCodeRepeatedException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public CourseCodeRepeatedException(Throwable nested)
        {
                super(nested);
        }
}
