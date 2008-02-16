/**
 * AccessSysException.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class AccessSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public AccessSysException()
        {
                super();
        }

        /**
         * Constructs an <code>AccessSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public AccessSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>AccessSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public AccessSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>AccessSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public AccessSysException(Throwable nested)
        {
                super(nested);
        }
}
