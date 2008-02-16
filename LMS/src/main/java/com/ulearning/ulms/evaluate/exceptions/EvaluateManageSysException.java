/**
 * EvaluateManageSysException.java.
 * User: Fengch  Date: 2005-5-30 10:45:36
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class EvaluateManageSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public EvaluateManageSysException()
        {
                super();
        }

        /**
         * Constructs an <code>EvaluateManageSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public EvaluateManageSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>EvaluateManageSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public EvaluateManageSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>EvaluateManageSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public EvaluateManageSysException(Throwable nested)
        {
                super(nested);
        }
}
