/**
 * ulmsException.java      2004-4-19 16:39
 * Author:cunhua.feng
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Huaxia Dadi Distance Learning Services Co.,Ltd.
 * You shall not disclose such Confidential Information.
 *
 * Modified by: name, date, comments for this modification
 * Modified by: name, date, comments for this modification
 *
 ***/
package com.ulearning.ulms.core.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * Base ulms Exception, provides nested exceptions, etc
 */
public class ULMSException extends RuntimeException
{
        private Throwable nested = null;
        private String errorKey = null;

        /**
         * Creates new <code>ulmsException</code> without detail message.
         */
        public ULMSException()
        {
                super();
        }

        /**
         * Constructs an <code>ulmsException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public ULMSException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>ulmsException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public ULMSException(String msg, Throwable nested)
        {
                super(msg);
                this.nested = nested;
        }

        /**
         * Constructs an <code>ulmsException</code> with the specified detail message,errorKey and nested Exception.
         *
         * @param msg the detail message.
         */
        public ULMSException(String msg, String errorKey)
        {
                super(msg);
                this.errorKey = errorKey;
        }

        /**
         * Constructs an <code>ulmsException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public ULMSException(Throwable nested)
        {
                super();
                this.nested = nested;
        }

        public String getErrorKey()
        {
                return errorKey;
        }

        public void setErrorKey(String errorKey)
        {
                this.errorKey = errorKey;
        }

        /**
         * Returns the detail message, including the message from the nested exception if there is one.
         */
        public String getMessage()
        {
                if (nested != null)
                {
                        return super.getMessage() + " (" + nested.getMessage() + ")";
                }
                else
                {
                        return super.getMessage();
                }
        }

        /**
         * Returns the detail message, NOT including the message from the nested exception.
         */
        public String getNonNestedMessage()
        {
                return super.getMessage();
        }

        /**
         * Returns the nested exception if there is one, null if there is not.
         */
        public Throwable getNested()
        {
                if (nested == null)
                {
                        return this;
                }

                return nested;
        }

        /**
         * Prints the composite message to System.err.
         */
        public void printStackTrace()
        {
                super.printStackTrace();

                if (nested != null)
                {
                        nested.printStackTrace();
                }
        }

        /**
         * Prints the composite message and the embedded stack trace to the specified stream ps.
         */
        public void printStackTrace(PrintStream ps)
        {
                super.printStackTrace(ps);

                if (nested != null)
                {
                        nested.printStackTrace(ps);
                }
        }

        /**
         * Prints the composite message and the embedded stack trace to the specified print writer pw.
         */
        public void printStackTrace(PrintWriter pw)
        {
                super.printStackTrace(pw);

                if (nested != null)
                {
                        nested.printStackTrace(pw);
                }
        }
}
