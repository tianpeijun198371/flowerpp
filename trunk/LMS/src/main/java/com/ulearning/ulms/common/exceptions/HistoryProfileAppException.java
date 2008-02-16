/**
 * HistoryProfileAppException.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class HistoryProfileAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public HistoryProfileAppException()
        {
                super();
        }

        /**
         * Constructs an <code>HistoryProfileAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public HistoryProfileAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>HistoryProfileAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public HistoryProfileAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>HistoryProfileAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public HistoryProfileAppException(Throwable nested)
        {
                super(nested);
        }
}
