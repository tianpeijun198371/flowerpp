/**
 * MaxLengthExceededAppException.java.
 * User: fengch Date: 2005-4-29 13:46:31
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.exceptions;

public class MaxLengthExceededAppException extends ULMSAppException
{
        /**
         * Creates new <code>ELMSAppException</code> without detail message.
         */
        public MaxLengthExceededAppException()
        {
                super();
        }

        /**
         * Constructs an <code>MaxLengthExceededAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public MaxLengthExceededAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>MaxLengthExceededAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public MaxLengthExceededAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>MaxLengthExceededAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public MaxLengthExceededAppException(Throwable nested)
        {
                super(nested);
        }
}
