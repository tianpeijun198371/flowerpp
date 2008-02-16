/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:10:53
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class ColSignDetailAppException extends ULMSAppException
{
        /**
         * Constructor
         *
         * @param str a string that explains what the exception condition is
         */
        public ColSignDetailAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public ColSignDetailAppException()
        {
                super();
        }

        /**
         * Constructs an <code>CourseAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public ColSignDetailAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>CourseAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public ColSignDetailAppException(Throwable nested)
        {
                super(nested);
        }
}
