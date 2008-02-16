/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-23
 * Time: 11:36:24
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions;

public class DetialIsExisteException extends ColSignDetailAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public DetialIsExisteException()
        {
                super();
        }

        /**
         * Constructs an <code>UserExistedInCourseException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public DetialIsExisteException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>UserExistedInCourseException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public DetialIsExisteException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>UserExistedInCourseException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public DetialIsExisteException(Throwable nested)
        {
                super(nested);
        }
}
