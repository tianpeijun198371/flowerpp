/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-9
 * Time: 14:28:40
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.exceptions;

import com.ulearning.ulms.course.exceptions.CourseAppException;


public class GraduationCertNoDuplicateException extends CourseAppException
{
        /**
         * Creates new <code>CourseCodeRepeatedException</code> without detail message.
         */
        public GraduationCertNoDuplicateException()
        {
                super();
        }

        /**
         * Constructs an <code>CourseCodeRepeatedException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public GraduationCertNoDuplicateException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>CourseCodeRepeatedException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public GraduationCertNoDuplicateException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>CourseCodeRepeatedException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public GraduationCertNoDuplicateException(Throwable nested)
        {
                super(nested);
        }
}
