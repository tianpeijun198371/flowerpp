/**
 * ScoreSysException.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class ScoreSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public ScoreSysException()
        {
                super();
        }

        /**
         * Constructs an <code>ScoreSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public ScoreSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>ScoreSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public ScoreSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>ScoreSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public ScoreSysException(Throwable nested)
        {
                super(nested);
        }
}
