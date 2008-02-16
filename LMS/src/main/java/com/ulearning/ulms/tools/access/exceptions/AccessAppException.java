/**
 * AccessAppException.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class AccessAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public AccessAppException()
        {
                super();
        }

        /**
         * Constructs an <code>AccessAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public AccessAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>AccessAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public AccessAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>AccessAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public AccessAppException(Throwable nested)
        {
                super(nested);
        }
}
