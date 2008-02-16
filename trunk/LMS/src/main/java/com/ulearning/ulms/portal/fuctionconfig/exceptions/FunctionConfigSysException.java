/**
 * FunctionConfigSysException.java.
 * User: Fengch  Date: 2005-4-1 10:45:36
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.portal.fuctionconfig.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class FunctionConfigSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public FunctionConfigSysException()
        {
                super();
        }

        /**
         * Constructs an <code>FunctionConfigSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public FunctionConfigSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>FunctionConfigSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public FunctionConfigSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>FunctionConfigSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public FunctionConfigSysException(Throwable nested)
        {
                super(nested);
        }
}
