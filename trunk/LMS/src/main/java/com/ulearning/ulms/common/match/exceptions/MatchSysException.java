/**
 * MatchSysException.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class MatchSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public MatchSysException()
        {
                super();
        }

        /**
         * Constructs an <code>MatchSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public MatchSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>MatchSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public MatchSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>MatchSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public MatchSysException(Throwable nested)
        {
                super(nested);
        }
}
