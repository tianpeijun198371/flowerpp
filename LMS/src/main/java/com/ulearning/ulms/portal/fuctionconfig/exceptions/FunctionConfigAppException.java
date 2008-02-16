/**
 * FunctionConfigAppException.java.
 * User: Fengch  Date: 2005-4-1 10:45:27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.portal.fuctionconfig.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class FunctionConfigAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public FunctionConfigAppException()
        {
                super();
        }

        /**
         * Constructs an <code>FunctionConfigAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public FunctionConfigAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>FunctionConfigAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public FunctionConfigAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>FunctionConfigAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public FunctionConfigAppException(Throwable nested)
        {
                super(nested);
        }
}
