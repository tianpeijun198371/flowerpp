/**
 * ContentAppException.java.
 * User: Fengch  Date: 05-5-30 10:45:36
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class EvaluateManageAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public EvaluateManageAppException()
        {
                super();
        }

        /**
         * Constructs an <code>ContentAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public EvaluateManageAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>ContentAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public EvaluateManageAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>ContentAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public EvaluateManageAppException(Throwable nested)
        {
                super(nested);
        }
}
