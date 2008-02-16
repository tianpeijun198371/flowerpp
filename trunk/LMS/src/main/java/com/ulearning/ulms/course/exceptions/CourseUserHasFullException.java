/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-13
 * Time: 13:41:39
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.exceptions;

public class CourseUserHasFullException extends CourseAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public CourseUserHasFullException()
        {
                super();
        }

        /**
         * Constructs an <code>UserExistedInCourseException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public CourseUserHasFullException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>UserExistedInCourseException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public CourseUserHasFullException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>UserExistedInCourseException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public CourseUserHasFullException(Throwable nested)
        {
                super(nested);
        }
}
