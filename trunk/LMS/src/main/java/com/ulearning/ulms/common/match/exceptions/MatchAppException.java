/**
 * MatchAppException.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class MatchAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public MatchAppException()
        {
                super();
        }

        /**
         * Constructs an <code>MatchAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public MatchAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>MatchAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public MatchAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>MatchAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public MatchAppException(Throwable nested)
        {
                super(nested);
        }
}
